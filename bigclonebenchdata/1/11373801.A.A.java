public class A{
    public boolean resolve(String parameters, Reader in, Writer out, DataFieldResolver dataFieldResolver, int[] arrayPositioner) throws IOException {
        PrintWriter printOut = new PrintWriter(out);
        URL url = new URL(parameters);
        Reader urlIn = new InputStreamReader(url.openStream());
        int ch = urlIn.read();
        while (ch != -1) {
            out.write(ch);
            ch = urlIn.read();
        }
        out.flush();
        return false;
    }
}