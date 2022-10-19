public class A{
    public String doGet() throws MalformedURLException, IOException {
        uc = (HttpURLConnection) url.openConnection();
        BufferedInputStream buffer = new BufferedInputStream(uc.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int c;
        while ((c = buffer.read()) != -1) {
            bos.write(c);
        }
        bos.close();
        headers = uc.getHeaderFields();
        status = uc.getResponseCode();
        return bos.toString();
    }
}