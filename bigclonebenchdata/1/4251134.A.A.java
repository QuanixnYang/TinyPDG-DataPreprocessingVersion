public class A{
    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        if (url.getQuery() == null) throw new IllegalStateException("Missing TemplateAccount number in rest URL " + url);
        MSResource msResource = null;
        try {
            long templateAccountId = Long.parseLong(url.getQuery());
            msResource = menuBean.findMSResource(templateAccountId, url.getPath());
        } catch (Exception e) {
            throw new IllegalStateException("Resource not found in database: " + url, e);
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(msResource.getValue());
        return new RestConnection(url, bais);
    }
}