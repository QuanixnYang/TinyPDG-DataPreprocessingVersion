public class A{
    protected static File UrlGzipToFile(File dir, String urlSt, String suffix) throws CaughtException {
        try {
            URL url = new URL(urlSt);
            InputStream zipped = url.openStream();
            InputStream unzipped = new GZIPInputStream(zipped);
            File tempFile = File.createTempFile("input", suffix, dir);
            copyFile(tempFile, unzipped);
            return tempFile;
        } catch (IOException e) {
            throw new CaughtException(e, logger);
        }
    }
}