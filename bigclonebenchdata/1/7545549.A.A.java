public class A{
    public static long getLastModified(URL url) throws IOException {
        if ("file".equals(url.getProtocol())) {
            String externalForm = url.toExternalForm();
            File file = new File(externalForm.substring(5));
            return file.lastModified();
        } else {
            URLConnection connection = url.openConnection();
            long modified = connection.getLastModified();
            try {
                InputStream is = connection.getInputStream();
                if (is != null) is.close();
            } catch (UnknownServiceException use) {
            } catch (IOException ioe) {
            }
            return modified;
        }
    }
}