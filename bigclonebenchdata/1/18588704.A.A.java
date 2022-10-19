public class A{
    @SuppressWarnings("unchecked")
    public static synchronized MetaDataBean getMetaDataByUrl(URL url) {
        if (url == null) throw new IllegalArgumentException("Properties url for meta data is null");
        MetaDataBean mdb = metaDataByUrl.get(url);
        if (mdb != null) return mdb;
        log.info("Loading metadata " + url);
        Properties properties = new Properties();
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mdb = new MetaDataBean((Map) properties);
        metaDataByUrl.put(url, mdb);
        mdb.instanceValue = url.toString();
        return mdb;
    }
}