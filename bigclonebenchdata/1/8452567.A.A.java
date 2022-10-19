public class A{
    private static JSONObject sendCouchRequest(HttpUriRequest request) {
        try {
            HttpResponse httpResponse = (HttpResponse) new DefaultHttpClient().execute(request);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String resultString = convertStreamToString(instream);
                instream.close();
                JSONObject jsonResult = new JSONObject(resultString);
                return jsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}