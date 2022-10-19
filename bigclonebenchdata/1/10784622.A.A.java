public class A{
    public static InputStream getPropertyFileInputStream(String propertyFileURLStr) {
        InputStream in = null;
        String errmsg = "Fatal error: Unable to open specified properties file: " + propertyFileURLStr;
        try {
            URL url = new URL(propertyFileURLStr);
            in = url.openStream();
        } catch (IOException e) {
            throw new IllegalArgumentException(errmsg);
        }
        return (in);
    }
}