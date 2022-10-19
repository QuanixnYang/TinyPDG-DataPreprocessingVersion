public class A{
    public static OutputStream getOutputStream(String path) throws ResourceException {
        URL url = getURL(path);
        if (url != null) {
            try {
                return url.openConnection().getOutputStream();
            } catch (IOException e) {
                throw new ResourceException(e);
            }
        } else {
            throw new ResourceException("Error obtaining resource, invalid path: " + path);
        }
    }
}