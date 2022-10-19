public class A{
    private byte[] getCharImage(long chrId) {
        byte[] imgData = null;
        try {
            URL url = new URL("http://img.eve.is/serv.asp?s=256&c=" + chrId);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int data;
            try {
                while ((data = is.read()) >= 0) {
                    os.write(data);
                }
            } finally {
                is.close();
            }
            imgData = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgData;
    }
}