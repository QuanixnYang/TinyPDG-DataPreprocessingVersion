public class A{
    private static StringBuffer downloadHTTPPage(URL url) throws Exception {
        URLConnection con = url.openConnection();
        con.setReadTimeout(0);
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;
        while (null != (line = br.readLine())) {
            sb.append(line);
        }
        br.close();
        return sb;
    }
}