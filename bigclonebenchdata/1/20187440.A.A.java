public class A{
    public String getLastReleaseVersion() throws TransferException {
        try {
            URL url = new URL("http://jtbdivelogbook.sourceforge.net/version.properties");
            URLConnection urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);
            urlConn.setReadTimeout(20000);
            urlConn.setConnectTimeout(10000);
            Properties props = new Properties();
            InputStream is = urlConn.getInputStream();
            props.load(is);
            is.close();
            String lastVersion = props.getProperty(PROPERTY_LAST_RELEASE);
            if (lastVersion == null) {
                LOGGER.warn("Couldn't find property " + PROPERTY_LAST_RELEASE);
            }
            return lastVersion;
        } catch (MalformedURLException e) {
            LOGGER.error(e);
            throw new TransferException(e);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new TransferException(e);
        }
    }
}