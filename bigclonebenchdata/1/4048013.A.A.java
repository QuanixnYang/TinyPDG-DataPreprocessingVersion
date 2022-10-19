public class A{
    public InputStream getParameterAsInputStream(String key) throws UndefinedParameterError, IOException {
        String urlString = getParameter(key);
        if (urlString == null) return null;
        try {
            URL url = new URL(urlString);
            InputStream stream = url.openStream();
            return stream;
        } catch (MalformedURLException e) {
            File file = getParameterAsFile(key);
            if (file != null) {
                return new FileInputStream(file);
            } else {
                return null;
            }
        }
    }
}