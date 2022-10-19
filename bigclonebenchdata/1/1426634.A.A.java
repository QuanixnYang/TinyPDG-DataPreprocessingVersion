public class A{
    @Override
    public URLConnection openConnection(URL url, Proxy proxy) throws IOException {
        if (null == url) {
            throw new IllegalArgumentException(Messages.getString("luni.1B"));
        }
        String host = url.getHost();
        if (host == null || host.length() == 0 || host.equalsIgnoreCase("localhost")) {
            return new FileURLConnection(url);
        }
        URL ftpURL = new URL("ftp", host, url.getFile());
        return (proxy == null) ? ftpURL.openConnection() : ftpURL.openConnection(proxy);
    }
}