public class A{
    public static boolean napiUserCheck(String user, String pass) throws TimeoutException, InterruptedException, IOException {
        URLConnection conn = null;
        InputStream in = null;
        URL url = new URL("http://www.napiprojekt.pl/users_check.php?nick=" + user + "&pswd=" + pass);
        conn = url.openConnection(Global.getProxy());
        in = Timeouts.getInputStream(conn);
        byte[] buffer = new byte[1024];
        in.read(buffer, 0, 1024);
        if (in != null) {
            in.close();
        }
        String response = new String(buffer);
        if (response.indexOf("ok") == 0) {
            return true;
        } else {
            return false;
        }
    }
}