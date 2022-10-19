public class A{
    public byte[] generatePassword(String clave) {
        byte[] password = { 00 };
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(clave.getBytes());
            password = md5.digest();
            return password;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }
}