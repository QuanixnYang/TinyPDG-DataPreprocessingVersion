public class A{
    @Override
    public InputStream getResourceAsStream(String path) {
        try {
            URL url = this.getResource(path);
            if (url == null) return null;
            return url.openStream();
        } catch (Exception e) {
            log(e.getMessage(), e);
            return null;
        }
    }
}