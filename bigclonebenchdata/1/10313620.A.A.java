public class A{
    public void read(Model model, String url) {
        try {
            URLConnection conn = new URL(url).openConnection();
            String encoding = conn.getContentEncoding();
            if (encoding == null) {
                read(model, conn.getInputStream(), url);
            } else {
                read(model, new InputStreamReader(conn.getInputStream(), encoding), url);
            }
        } catch (IOException e) {
            throw new JenaException(e);
        }
    }
}