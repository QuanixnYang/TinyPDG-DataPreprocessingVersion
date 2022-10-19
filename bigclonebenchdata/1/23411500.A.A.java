public class A{
    public void playSIDFromHVSC(String name) {
        player.reset();
        player.setStatus("Loading song: " + name);
        URL url;
        try {
            if (name.startsWith("/")) {
                name = name.substring(1);
            }
            url = getResource(hvscBase + name);
            if (player.readSID(url.openConnection().getInputStream())) {
                player.playSID();
            }
        } catch (IOException ioe) {
            System.out.println("Could not load: ");
            ioe.printStackTrace();
            player.setStatus("Could not load SID: " + ioe.getMessage());
        }
    }
}