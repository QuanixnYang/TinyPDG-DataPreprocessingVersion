public class A{
    private static void identify(ContentNetwork cn, String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            UrlUtils.setBrowserHeaders(con, null);
            String key = "cn." + cn.getID() + ".identify.cookie";
            String cookie = COConfigurationManager.getStringParameter(key, null);
            if (cookie != null) {
                con.setRequestProperty("Cookie", cookie + ";");
            }
            con.setRequestProperty("Connection", "close");
            con.getResponseCode();
            cookie = con.getHeaderField("Set-Cookie");
            if (cookie != null) {
                String[] bits = cookie.split(";");
                if (bits.length > 0 && bits[0].length() > 0) {
                    COConfigurationManager.setParameter(key, bits[0]);
                }
            }
        } catch (Throwable e) {
        }
    }
}