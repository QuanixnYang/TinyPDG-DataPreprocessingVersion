public class A{
    public String readURL(URL url) throws JasenException {
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = null;
        String html = null;
        NonBlockingStreamReader reader = null;
        try {
            in = url.openStream();
            reader = new NonBlockingStreamReader();
            reader.read(in, out, readBufferSize, readTimeout, null);
            html = new String(((ByteArrayOutputStream) out).toByteArray());
        } catch (IOException e) {
            throw new JasenException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                }
            }
        }
        return html;
    }
}