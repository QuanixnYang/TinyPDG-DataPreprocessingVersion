public class A{
    public HttpURLConnection connect() throws IOException {
        if (url == null) {
            return null;
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (previousETag != null) {
            connection.addRequestProperty("If-None-Match", previousETag);
        }
        if (previousLastModified != null) {
            connection.addRequestProperty("If-Modified-Since", previousLastModified);
        }
        return connection;
    }
}