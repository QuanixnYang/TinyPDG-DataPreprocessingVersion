public class A{
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("usage: PutFromFile [properties file] [file with pmpxml]");
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        Reader is = new FileReader(args[1]);
        char[] b = new char[1024];
        StringBuffer sb = new StringBuffer();
        int n;
        while ((n = is.read(b)) > 0) {
            sb.append(b, 0, n);
        }
        String test = sb.toString();
        System.out.println(test);
        String strurl = args[0];
        String data = "verb=PutRecord&xml=" + URLEncoder.encode(test, "UTF-8");
        URL url = new URL(strurl);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
        wr.close();
        rd.close();
    }
}