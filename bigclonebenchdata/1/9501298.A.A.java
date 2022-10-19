public class A{
    public InputStream getDaoConfig(String connectionType) throws IOException {
        URL url = null;
        if (connectionType.equals(SQL.ORACLE)) {
            url = DBCreateConfig.class.getResource("oracle.xml");
        } else if (connectionType.equals(SQL.SQL2K)) {
            url = DBCreateConfig.class.getResource("sql2k.xml");
        }
        return url.openStream();
    }
}