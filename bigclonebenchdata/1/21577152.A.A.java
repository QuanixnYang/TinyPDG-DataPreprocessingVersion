public class A{
    private InputStream callService(String text) {
        InputStream in = null;
        try {
            URL url = new URL(SERVLET_URL);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.connect();
            DataOutputStream dataStream = new DataOutputStream(conn.getOutputStream());
            dataStream.writeBytes(text);
            dataStream.flush();
            dataStream.close();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return in;
    }
}