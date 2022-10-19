public class A{
    public static IBiopaxModel read(URL url) throws ReactionException, IOException {
        IBiopaxModel model = null;
        InputStream in = null;
        try {
            in = url.openStream();
            model = read(in);
        } catch (IOException e) {
            LOGGER.error("Unable to read from URL " + url, e);
        } finally {
            if (in != null) in.close();
        }
        return model;
    }
}