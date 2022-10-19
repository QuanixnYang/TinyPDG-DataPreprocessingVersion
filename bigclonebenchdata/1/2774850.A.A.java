public class A{
    protected Scanner loadSource(String country) {
        if (httpProxy != null && httpProxy.isEnabled()) {
            System.setProperty("proxySet", "true");
            System.setProperty("http.proxyHost", httpProxy.getUrl());
            System.setProperty("http.proxyPort", Integer.toString(httpProxy.getPort()));
        }
        try {
            URL url = new URL(this.url + country);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            return new Scanner(connection.getInputStream());
        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}