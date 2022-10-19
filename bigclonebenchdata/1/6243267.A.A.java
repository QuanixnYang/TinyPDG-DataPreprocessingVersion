public class A{
    public Graph<N, E> read(final URL url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("url must not be null");
        }
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
            return read(inputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }
}