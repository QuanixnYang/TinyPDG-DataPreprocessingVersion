public class A{
    public static Hashtable DefaultLoginValues(String firstName, String lastName, String password, String mac, String startLocation, int major, int minor, int patch, int build, String platform, String viewerDigest, String userAgent, String author) throws Exception {
        Hashtable values = new Hashtable();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes("ASCII"), 0, password.length());
        byte[] raw_digest = md5.digest();
        String passwordDigest = Helpers.toHexText(raw_digest);
        values.put("first", firstName);
        values.put("last", lastName);
        values.put("passwd", "" + password);
        values.put("start", startLocation);
        values.put("major", major);
        values.put("minor", minor);
        values.put("patch", patch);
        values.put("build", build);
        values.put("platform", platform);
        values.put("mac", mac);
        values.put("agree_to_tos", "true");
        values.put("viewer_digest", viewerDigest);
        values.put("user-agent", userAgent + " (" + Helpers.VERSION + ")");
        values.put("author", author);
        Vector optionsArray = new Vector();
        optionsArray.addElement("inventory-root");
        optionsArray.addElement("inventory-skeleton");
        optionsArray.addElement("inventory-lib-root");
        optionsArray.addElement("inventory-lib-owner");
        optionsArray.addElement("inventory-skel-lib");
        optionsArray.addElement("initial-outfit");
        optionsArray.addElement("gestures");
        optionsArray.addElement("event_categories");
        optionsArray.addElement("event_notifications");
        optionsArray.addElement("classified_categories");
        optionsArray.addElement("buddy-list");
        optionsArray.addElement("ui-config");
        optionsArray.addElement("login-flags");
        optionsArray.addElement("global-textures");
        values.put("options", optionsArray);
        return values;
    }
}