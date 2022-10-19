public class A{
    private Source getStylesheetSource(String stylesheetResource) throws ApplicationContextException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Loading XSLT stylesheet from " + stylesheetResource);
        }
        try {
            URL url = this.getClass().getClassLoader().getResource(stylesheetResource);
            String urlPath = url.toString();
            String systemId = urlPath.substring(0, urlPath.lastIndexOf('/') + 1);
            return new StreamSource(url.openStream(), systemId);
        } catch (IOException e) {
            throw new RuntimeException("Can't load XSLT stylesheet from " + stylesheetResource, e);
        }
    }
}