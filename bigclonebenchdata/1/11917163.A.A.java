public class A{
    private void addConfigurationResource(final String fileName, NotFoundPolicy notFoundPolicy) {
        try {
            final ClassLoader cl = this.getClass().getClassLoader();
            final Properties p = new Properties();
            final URL url = cl.getResource(fileName);
            if (url == null) {
                throw new NakedObjectException("Failed to load configuration resource: " + fileName);
            }
            p.load(url.openStream());
            LOG.info("configuration resource " + fileName + " loaded");
            configuration.add(p);
        } catch (final Exception e) {
            if (notFoundPolicy == NotFoundPolicy.FAIL_FAST) {
                throw new NakedObjectException(e);
            }
            LOG.info("configuration resource " + fileName + " not found, but not needed");
        }
    }
}