public class A{
    public static JSONObject delete(String uid) throws ClientProtocolException, IOException, JSONException {
        HttpClient client = new DefaultHttpClient(params);
        HttpGet get = new HttpGet(DELETE_URI + "?uid=" + uid);
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() == 200) {
            String res = EntityUtils.toString(response.getEntity());
            return new JSONObject(res);
        }
        throw new IOException("bad http response:" + response.getStatusLine().getReasonPhrase());
    }
}