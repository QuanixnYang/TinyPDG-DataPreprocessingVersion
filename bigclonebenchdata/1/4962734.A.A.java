public class A{
    public static JSONObject fromUrl(String url) throws Throwable {
        Validate.notEmpty(url);
        InputStream stream = null;
        HttpClient httpclient = null;
        try {
            httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            if (response != null) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try {
                        stream = entity.getContent();
                        return fromStream(stream);
                    } finally {
                        try {
                            if (stream != null) stream.close();
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        } catch (Throwable tr) {
            Logger.e(TAG, "fromUrl", tr);
            throw tr;
        } finally {
            if (httpclient != null) httpclient.getConnectionManager().shutdown();
        }
        return null;
    }
}