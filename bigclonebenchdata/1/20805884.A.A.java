public class A{
    public String preProcessHTML(String uri) {
        final StringBuffer buf = new StringBuffer();
        try {
            HTMLDocument doc = new HTMLDocument() {

                public HTMLEditorKit.ParserCallback getReader(int pos) {
                    return new HTMLEditorKit.ParserCallback() {

                        public void handleText(char[] data, int pos) {
                            buf.append(data);
                            buf.append('\n');
                        }
                    };
                }
            };
            URL url = new URI(uri).toURL();
            URLConnection conn = url.openConnection();
            Reader rd = new InputStreamReader(conn.getInputStream());
            new ParserDelegator().parse(rd, doc.getReader(0), Boolean.TRUE);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return buf.toString();
    }
}