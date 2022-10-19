public class A{
    public static String doGet(String http_url, String get_data) {
        URL url;
        try {
            if ((get_data != "") && (get_data != null)) {
                url = new URL(http_url + "?" + get_data);
            } else {
                url = new URL(http_url);
            }
            URLConnection conn = url.openConnection();
            InputStream stream = new BufferedInputStream(conn.getInputStream());
            try {
                StringBuffer b = new StringBuffer();
                int ch;
                while ((ch = stream.read()) != -1) b.append((char) ch);
                return b.toString();
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            ;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}