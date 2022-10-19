public class A{
    protected Scanner createScanner(InputSource source) {
        documentURI = source.getURI();
        if (documentURI == null) {
            documentURI = "";
        }
        Reader r = source.getCharacterStream();
        if (r != null) {
            return new Scanner(r);
        }
        InputStream is = source.getByteStream();
        if (is != null) {
            return new Scanner(is, source.getEncoding());
        }
        String uri = source.getURI();
        if (uri == null) {
            throw new CSSException(formatMessage("empty.source", null));
        }
        try {
            ParsedURL purl = new ParsedURL(uri);
            is = purl.openStreamRaw(CSSConstants.CSS_MIME_TYPE);
            return new Scanner(is, source.getEncoding());
        } catch (IOException e) {
            throw new CSSException(e);
        }
    }
}