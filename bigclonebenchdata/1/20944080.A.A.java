public class A{
    public InputStream loadDriver(String id) throws IOException {
        Hashtable drivers = loadDriverDB();
        DriverInfo di = (DriverInfo) drivers.get(id);
        InputStream stream = null;
        if (di == null) {
            log.warn("No id" + id);
            throw new IOException("No driver id '" + id + "'");
        }
        try {
            String strURL = di.url;
            if (strURL.indexOf(":") == -1) {
                strURL = jarbase + strURL;
            }
            URL url = new URL(strURL);
            stream = url.openStream();
        } catch (MalformedURLException e) {
            log.error("bad URL for in " + di, e);
            throw new IOException("Bad driver URL " + e);
        } catch (IOException e) {
            log.error("can't connect to URL in " + di, e);
            throw e;
        }
        return stream;
    }
}