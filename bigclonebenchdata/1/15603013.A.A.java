public class A{
    public static URLConnection createConnection(URL url) throws java.io.IOException {
        URLConnection urlConn = url.openConnection();
        if (urlConn instanceof HttpURLConnection) {
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setRequestMethod("POST");
        }
        urlConn.setDoInput(true);
        urlConn.setDoOutput(true);
        urlConn.setUseCaches(false);
        urlConn.setDefaultUseCaches(false);
        return urlConn;
    }
}