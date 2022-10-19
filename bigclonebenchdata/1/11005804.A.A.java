public class A{
    public String kodetu(String testusoila) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            new MezuLeiho("Ez da zifraketa algoritmoa aurkitu", "Ados", "Zifraketa Arazoa", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        try {
            md.update(testusoila.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            new MezuLeiho("Errorea kodetzerakoan", "Ados", "Kodeketa Errorea", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        byte raw[] = md.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;
    }
}