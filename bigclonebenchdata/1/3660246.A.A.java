public class A{
    private Object query(String json) throws IOException, ParseException {
        String envelope = "{\"qname1\":{\"query\":" + json + "}}";
        String urlStr = MQLREADURL + "?queries=" + URLEncoder.encode(envelope, "UTF-8");
        if (isDebugging()) {
            if (echoRequest) System.err.println("Sending:" + envelope);
        }
        URL url = new URL(urlStr);
        URLConnection con = url.openConnection();
        con.setRequestProperty("Cookie", COOKIE + "=" + "\"" + getMetawebCookie() + "\"");
        con.connect();
        InputStream in = con.getInputStream();
        Object item = new JSONParser(echoRequest ? new EchoReader(in) : in).object();
        in.close();
        String code = getString(item, "code");
        if (!"/api/status/ok".equals(code)) {
            throw new IOException("Bad code " + item);
        }
        code = getString(item, "qname1.code");
        if (!"/api/status/ok".equals(code)) {
            throw new IOException("Bad code " + item);
        }
        return item;
    }
}