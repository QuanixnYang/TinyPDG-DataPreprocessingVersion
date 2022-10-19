public class A{
    public static InputStream getNotCacheResourceAsStream(final String fileName) {
        if ((fileName.indexOf("file:") >= 0) || (fileName.indexOf(":/") > 0)) {
            try {
                URL url = new URL(fileName);
                return new BufferedInputStream(url.openStream());
            } catch (Exception e) {
                return null;
            }
        }
        return new ByteArrayInputStream(getNotCacheResource(fileName).getData());
    }
}