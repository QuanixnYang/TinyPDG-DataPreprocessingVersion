public class A{
    public static final synchronized String hash(String data) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nsae) {
                System.err.println("Failed to load the MD5 MessageDigest. " + "We will be unable to function normally.");
                nsae.printStackTrace();
            }
        }
        digest.update(data.getBytes());
        return encodeHex(digest.digest());
    }
}