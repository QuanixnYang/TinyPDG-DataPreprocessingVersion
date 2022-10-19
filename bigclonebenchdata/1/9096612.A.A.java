public class A{
    public void include(String href) throws ProteuException {
        try {
            if (href.toLowerCase().startsWith("http://")) {
                java.net.URLConnection urlConn = (new java.net.URL(href)).openConnection();
                Download.sendInputStream(this, urlConn.getInputStream());
            } else {
                requestHead.set("JCN_URL_INCLUDE", href);
                Url.build(this);
            }
        } catch (ProteuException pe) {
            throw pe;
        } catch (Throwable t) {
            logger.error("Include", t);
            throw new ProteuException(t.getMessage(), t);
        }
    }
}