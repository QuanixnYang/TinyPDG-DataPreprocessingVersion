public class A{
    public static String hash(String toEncripty) {
        if (toEncripty != null) {
            try {
                synchronized (toEncripty) {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(toEncripty.getBytes());
                    byte[] hash = md.digest();
                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < hash.length; i++) {
                        if ((0xff & hash[i]) < 0x10) hexString.append("0" + Integer.toHexString((0xFF & hash[i]))); else hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                    toEncripty = hexString.toString();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return toEncripty;
    }
}