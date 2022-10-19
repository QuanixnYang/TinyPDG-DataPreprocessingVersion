public class A{
    protected InputStream getAudioStream() {
        InputStream in = null;
        try {
            URL url = getAudioURL();
            if (url != null) in = url.openStream();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return in;
    }
}