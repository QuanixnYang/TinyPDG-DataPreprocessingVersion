public class A{
    public void read(Model m, String url) throws JenaException {
        try {
            URLConnection conn = new URL(url).openConnection();
            String encoding = conn.getContentEncoding();
            if (encoding == null) read(m, conn.getInputStream(), url); else read(m, new InputStreamReader(conn.getInputStream(), encoding), url);
        } catch (FileNotFoundException e) {
            throw new DoesNotExistException(url);
        } catch (IOException e) {
            throw new JenaException(e);
        }
    }
}