public class A{
        public void readFully(String urlS) throws Exception {
            URL url = new URL(urlS);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            byte[] data = new byte[10240];
            int b = is.read(data);
            while (b > 0) {
                size += b;
                b = is.read(data);
            }
            is.close();
        }
}