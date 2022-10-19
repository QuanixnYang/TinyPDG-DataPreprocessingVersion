public class A{
    private String doGet(String identifier) throws IOException, MalformedURLException {
        URL url = new URL(baseurl.toString() + "/" + identifier);
        logger.debug("get " + url.toString());
        HttpURLConnection huc = (HttpURLConnection) (url.openConnection());
        BufferedReader reader = new BufferedReader(new InputStreamReader(huc.getInputStream()));
        StringWriter writer = new StringWriter();
        char[] buffer = new char[BUFFER_SIZE];
        int count = 0;
        while ((count = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, count);
        }
        writer.close();
        reader.close();
        int code = huc.getResponseCode();
        logger.debug(" get result" + code);
        if (code == 200) {
            return writer.toString();
        } else throw new IOException("cannot get " + url.toString());
    }
}