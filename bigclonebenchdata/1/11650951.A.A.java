public class A{
    private Properties loadProperties(final String propertiesName) throws IOException {
        Properties bundle = null;
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final URL url = loader.getResource(propertiesName);
        if (url == null) {
            throw new IOException("Properties file " + propertiesName + " not found");
        }
        final InputStream is = url.openStream();
        if (is != null) {
            bundle = new Properties();
            bundle.load(is);
        } else {
            throw new IOException("Properties file " + propertiesName + " not avilable");
        }
        return bundle;
    }
}