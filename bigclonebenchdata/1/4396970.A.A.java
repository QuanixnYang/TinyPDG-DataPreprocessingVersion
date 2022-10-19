public class A{
    public HttpURLConnection proxiedURLConnection(URL url, String serverName) throws IOException, PussycatException {
        if (this.httpProxy == null || this.httpProxy.equals("") || PussycatUtils.isLocalURL(url.toString()) || url.toString().contains(serverName)) {
            System.getProperties().put("proxySet", "false");
        } else {
            System.getProperties().put("proxySet", "true");
        }
        if (System.getProperties().getProperty("proxySet").equals("true")) {
            return (java.net.HttpURLConnection) url.openConnection(new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(this.httpProxy, this.httpProxyPort)));
        } else {
            return (java.net.HttpURLConnection) url.openConnection();
        }
    }
}