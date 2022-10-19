public class A{
    public static void checkForUpdate(String version) {
        try {
            URL url = new URL(WiimoteWhiteboard.getProperty("updateURL"));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            final String current = in.readLine();
            if (compare(version, current)) showUpdateNotification(version, current);
            in.close();
        } catch (Exception e) {
        }
    }
}