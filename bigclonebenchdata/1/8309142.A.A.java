public class A{
    public boolean check(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(username.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(realm.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(password.getBytes("ISO-8859-1"));
        byte[] ha1 = md.digest();
        String hexHa1 = new String(Hex.encodeHex(ha1));
        md.reset();
        md.update(method.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(uri.getBytes("ISO-8859-1"));
        byte[] ha2 = md.digest();
        String hexHa2 = new String(Hex.encodeHex(ha2));
        md.reset();
        md.update(hexHa1.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(nonce.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(nc.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(cnonce.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(qop.getBytes("ISO-8859-1"));
        md.update((byte) ':');
        md.update(hexHa2.getBytes("ISO-8859-1"));
        byte[] digest = md.digest();
        String hexDigest = new String(Hex.encodeHex(digest));
        return (hexDigest.equalsIgnoreCase(response));
    }
}