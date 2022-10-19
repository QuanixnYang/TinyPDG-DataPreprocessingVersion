public class A{
    public InputStream getInputStream() throws java.io.IOException {
        if (!_urlString.endsWith("!/")) return super.getInputStream();
        URL url = new URL(_urlString.substring(4, _urlString.length() - 2));
        return url.openStream();
    }
}