public class A{
    @Override
    public void loadTest(StoryCardModel story) {
        String strUrl = story.getStoryCard().getAcceptanceTestUrl();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader;
        try {
            URL url = new URL(strUrl);
            loader = factory.newDocumentBuilder();
            Document document;
            document = loader.parse(url.openStream());
            this.numPass = Integer.parseInt(((Element) document.getElementsByTagName("num-pass").item(0)).getFirstChild().getNodeValue());
            this.numFail = Integer.parseInt(((Element) document.getElementsByTagName("num-fail").item(0)).getFirstChild().getNodeValue());
            this.numRuns = Integer.parseInt(((Element) document.getElementsByTagName("num-runs").item(0)).getFirstChild().getNodeValue());
            this.numExceptions = Integer.parseInt(((Element) document.getElementsByTagName("num-exceptions").item(0)).getFirstChild().getNodeValue());
            this.wikiText = ((Element) document.getElementsByTagName("wiki").item(0)).getFirstChild().getNodeValue();
        } catch (Exception e) {
            util.Logger.singleton().error(e);
        }
    }
}