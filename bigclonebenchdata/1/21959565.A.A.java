public class A{
    public Font(String path, String fontName) {
        this();
        StringTokenizer tok = new StringTokenizer(path, ";");
        NybbleInputStream str = null;
        while (str == null & tok.hasMoreTokens()) {
            try {
                URL url = new URL(tok.nextToken() + "/");
                url = new URL(url, fontName);
                System.out.println(url.toString());
                str = new NybbleInputStream(url.openStream());
                parsePkStream(str);
                str.close();
                name = fontName;
            } catch (java.io.IOException e) {
            }
        }
    }
}