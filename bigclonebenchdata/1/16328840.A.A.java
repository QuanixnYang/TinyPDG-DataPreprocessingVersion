public class A{
    protected InputStream transform(URL url) throws IOException {
        TransformerFactory tf = TransformerFactory.newInstance();
        InputStream xsl_is = null;
        InputStream url_is = null;
        ByteArrayOutputStream os = null;
        byte[] output;
        try {
            xsl_is = Classes.getThreadClassLoader().getResourceAsStream(getStylesheet());
            url_is = new BufferedInputStream(url.openStream());
            os = new ByteArrayOutputStream();
            Transformer tr = tf.newTransformer(new StreamSource(xsl_is));
            tr.transform(new StreamSource(url_is), new StreamResult(os));
            output = os.toByteArray();
        } catch (TransformerConfigurationException tce) {
            throw new IOException(tce.getLocalizedMessage());
        } catch (TransformerException te) {
            throw new IOException(te.getLocalizedMessage());
        } finally {
            try {
                if (os != null) os.close();
            } catch (Throwable t) {
            }
            try {
                if (url_is != null) url_is.close();
            } catch (Throwable t) {
            }
            try {
                if (xsl_is != null) xsl_is.close();
            } catch (Throwable t) {
            }
        }
        if (logService.isEnabledFor(LogLevel.TRACE)) logService.log(LogLevel.TRACE, new String(output));
        return new ByteArrayInputStream(output);
    }
}