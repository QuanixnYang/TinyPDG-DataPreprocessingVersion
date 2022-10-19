public class A{
    private URLConnection openConnection(URL url) throws MalformedURLException, IOException {
        URLConnection connection = url.openConnection();
        if (connection instanceof HttpURLConnection) ((HttpURLConnection) connection).setInstanceFollowRedirects(false);
        connection.setUseCaches(false);
        return connection;
    }
}