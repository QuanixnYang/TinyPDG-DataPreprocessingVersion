public class A{
    private static void testIfModified() throws IOException {
        HttpURLConnection c2 = (HttpURLConnection) url.openConnection();
        c2.setIfModifiedSince(System.currentTimeMillis() + 1000);
        c2.connect();
        int code = c2.getResponseCode();
        System.out.print("If-Modified-Since     : ");
        boolean supported = (code == 304);
        System.out.println(b2s(supported) + " - " + code + " (" + c2.getResponseMessage() + ")");
    }
}