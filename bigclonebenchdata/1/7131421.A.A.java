public class A{
    private void getEventHeapsFromWeb() {
        try {
            URL url = getServersURL();
            InputStream in = url.openStream();
            Document doc = factory.newDocumentBuilder().parse(in);
            readFromDocument(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}