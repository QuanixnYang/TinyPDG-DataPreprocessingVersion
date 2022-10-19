public class A{
    public InputStream getConfResourceAsInputStream(String name) {
        try {
            URL url = getResource(name);
            if (url == null) {
                LOG.info(name + " not found");
                return null;
            } else {
                LOG.info("found resource " + name + " at " + url);
            }
            return url.openStream();
        } catch (Exception e) {
            return null;
        }
    }
}