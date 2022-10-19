public class A{
    public static final String getUniqueId() {
        String digest = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String timeVal = "" + (System.currentTimeMillis() + 1);
            String localHost = "";
            try {
                localHost = InetAddress.getLocalHost().toString();
            } catch (UnknownHostException e) {
                throw new RuntimeException("Error trying to get localhost" + e.getMessage());
            }
            String randVal = "" + new Random().nextInt();
            String val = timeVal + localHost + randVal;
            md.reset();
            md.update(val.getBytes());
            digest = toHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException : " + e.getMessage());
        }
        return digest;
    }
}