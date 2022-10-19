public class A{
    private static boolean loadResources(String ext) {
        InputStream in;
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("bg/plambis/dict/local/i18n" + ext + ".xml");
            if (url == null) return false;
            in = url.openStream();
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
        try {
            Serializer serializer = new Persister();
            resources = serializer.read(TextResource.class, in);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}