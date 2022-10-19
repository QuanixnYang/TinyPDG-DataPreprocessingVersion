public class A{
    public static String getWebContent(String remoteUrl, String encoding) {
        StringBuffer sb = new StringBuffer();
        try {
            java.net.URL url = new java.net.URL(remoteUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
        } catch (Exception e) {
            logger.error("获取远程网址内容失败 - " + remoteUrl, e);
        }
        return sb.toString();
    }
}