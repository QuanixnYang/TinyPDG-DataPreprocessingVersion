public class A{
    public StringBuffer get(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer page = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String utf = new String(line.getBytes("UTF-8"), "UTF-8");
            page.append(utf);
        }
        bufferedReader.close();
        return page;
    }
}