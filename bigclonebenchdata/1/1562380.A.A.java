public class A{
    protected URLConnection openConnection(URL url) throws IOException {
        URLStreamHandler handler = factory.findAuthorizedURLStreamHandler(protocol);
        if (handler != null) {
            try {
                return (URLConnection) openConnectionMethod.invoke(handler, new Object[] { url });
            } catch (Exception e) {
                factory.adaptor.getFrameworkLog().log(new FrameworkLogEntry(MultiplexingURLStreamHandler.class.getName(), "openConnection", FrameworkLogEntry.ERROR, e, null));
                throw new RuntimeException(e.getMessage());
            }
        }
        throw new MalformedURLException();
    }
}