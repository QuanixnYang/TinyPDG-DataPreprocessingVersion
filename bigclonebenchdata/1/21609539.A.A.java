public class A{
    public static JSONArray getFriends(long[] uids) throws ClientProtocolException, IOException, JSONException {
        HttpClient client = new DefaultHttpClient(params);
        HttpPost post = new HttpPost(FRIENDS_URI);
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("uids", arrayToString(uids, ",")));
        post.setEntity(new UrlEncodedFormEntity(parameters));
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            String res = EntityUtils.toString(response.getEntity());
            return new JSONArray(res);
        }
        throw new IOException("bad http response:" + response.getStatusLine().getReasonPhrase());
    }
}