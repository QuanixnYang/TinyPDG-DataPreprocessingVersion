public class A{
    public static void readConfigFromResource(String resname) throws Exception {
        URL url = ConfigXMLReader.class.getClassLoader().getResource(resname);
        if (url == null) throw new FileNotFoundException("Couldn't find the config resource:" + resname);
        System.out.println("Reading config from resource: " + url.toString());
        readConfig(url.openStream());
    }
}