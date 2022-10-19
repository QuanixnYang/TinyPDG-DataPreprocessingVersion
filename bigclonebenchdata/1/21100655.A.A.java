public class A{
    public static void init(Locale language) throws IOException {
        URL url = ClassLoader.getSystemResource("locales/" + language.getISO3Language() + ".properties");
        if (url == null) {
            throw new IOException("Could not load resource locales/" + language.getISO3Language() + ".properties");
        }
        PROPS.clear();
        PROPS.load(url.openStream());
    }
}