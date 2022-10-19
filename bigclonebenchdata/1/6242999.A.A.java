public class A{
    private boolean sendMsg(TACMessage msg) {
        try {
            String msgStr = msg.getMessageString();
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Content-Length", "" + msgStr.length());
            conn.setDoOutput(true);
            OutputStream output = conn.getOutputStream();
            output.write(msgStr.getBytes());
            output.flush();
            InputStream input = conn.getInputStream();
            int len = conn.getContentLength();
            int totalRead = 0;
            int read;
            byte[] content = new byte[len];
            while ((len > totalRead) && (read = input.read(content, totalRead, len - totalRead)) > 0) {
                totalRead += read;
            }
            output.close();
            input.close();
            if (len < totalRead) {
                log.severe("truncated message response for " + msg.getType());
                return false;
            } else {
                msgStr = new String(content);
                msg.setReceivedMessage(msgStr);
                msg.deliverMessage();
            }
            return true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "could not send message", e);
            return false;
        }
    }
}