public class A{
    public static List<String> retrieveLanguages() throws Exception {
        List<String> result = new ArrayList<String>();
        URL url = new URL("http://translatewiki.net/w/i.php?title=Special:MessageGroupStats&group=out-osm-site");
        String str = StreamUtil.toString(url.openStream());
        Pattern pattern = Pattern.compile(".*language=([^;\"]+).*");
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            String lang = m.group(1);
            if (!result.contains(lang)) {
                result.add(lang);
            }
        }
        return result;
    }
}