public class A{
    protected KMLRoot parseCachedKMLFile(URL url, String linkBase, String contentType, boolean namespaceAware) throws IOException, XMLStreamException {
        KMLDoc kmlDoc;
        InputStream refStream = url.openStream();
        if (KMLConstants.KMZ_MIME_TYPE.equals(contentType)) kmlDoc = new KMZInputStream(refStream); else kmlDoc = new KMLInputStream(refStream, WWIO.makeURI(linkBase));
        try {
            KMLRoot refRoot = new KMLRoot(kmlDoc, namespaceAware);
            refRoot.parse();
            return refRoot;
        } catch (XMLStreamException e) {
            refStream.close();
            throw e;
        }
    }
}