public class A{
    public void open(final String method, final java.net.URL url, boolean asyncFlag, final String userName, final String password) throws java.io.IOException {
        this.abort();
        Proxy proxy = this.proxy;
        URLConnection c = proxy == null || proxy == Proxy.NO_PROXY ? url.openConnection() : url.openConnection(proxy);
        synchronized (this) {
            this.connection = c;
            this.isAsync = asyncFlag;
            this.requestMethod = method;
            this.requestURL = url;
            this.requestUserName = userName;
            this.requestPassword = password;
        }
        this.changeState(HttpRequest.STATE_LOADING, 0, null, null);
    }
}