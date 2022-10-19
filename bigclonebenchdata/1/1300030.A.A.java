public class A{
    public PhoneDurationsImpl(URL url) throws IOException {
        BufferedReader reader;
        String line;
        phoneDurations = new HashMap();
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        line = reader.readLine();
        while (line != null) {
            if (!line.startsWith("***")) {
                parseAndAdd(line);
            }
            line = reader.readLine();
        }
        reader.close();
    }
}