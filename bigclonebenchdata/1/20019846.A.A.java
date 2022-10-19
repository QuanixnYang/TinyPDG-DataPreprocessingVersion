public class A{
    public static byte[] downloadHttpFile(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) throw new IOException("Invalid HTTP response: " + responseCode + " for url " + conn.getURL());
        InputStream in = conn.getInputStream();
        try {
            return Utilities.getInputBytes(in);
        } finally {
            in.close();
        }
    }
}