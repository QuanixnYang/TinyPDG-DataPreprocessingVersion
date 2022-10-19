public class A{
    private String send(String method, String contentType, String urlStr, String body) throws MalformedURLException, IOException {
        HttpURLConnection postCon = (HttpURLConnection) new URL(getUrl(urlStr)).openConnection();
        postCon.setRequestMethod(method);
        postCon.setDoOutput(true);
        postCon.setDoInput(true);
        if (cookie != null) {
            postCon.setRequestProperty("Cookie", cookie);
            if (contentType != null) {
                postCon.setRequestProperty("Content-type", contentType);
            }
            postCon.setRequestProperty("Content-Length", body == null ? "0" : Integer.toString(body.length()));
        }
        if (body != null) {
            OutputStream os = postCon.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(os);
            out.write(body);
            out.close();
        }
        InputStream is = null;
        try {
            is = postCon.getInputStream();
        } catch (IOException ioe) {
            is = postCon.getErrorStream();
        }
        int resCode = postCon.getResponseCode();
        if (resCode == 201 || resCode == 202) {
            String loc = postCon.getHeaderField("Location");
            System.out.println("loc:" + loc);
            return loc;
        }
        StringBuffer sb = StreamUtil.readStream(is);
        return sb.toString();
    }
}