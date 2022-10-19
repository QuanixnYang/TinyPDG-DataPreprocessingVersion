public class A{
    public Map readMap(String filename) throws Exception {
        xmlPath = filename.substring(0, filename.lastIndexOf(File.separatorChar) + 1);
        String xmlFile = makeUrl(filename);
        URL url = new URL(xmlFile);
        InputStream is = url.openStream();
        if (filename.endsWith(".gz")) {
            is = new GZIPInputStream(is);
        }
        Map unmarshalledMap = unmarshal(is);
        unmarshalledMap.setFilename(filename);
        map = null;
        return unmarshalledMap;
    }
}