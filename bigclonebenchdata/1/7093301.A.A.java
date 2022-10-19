public class A{
	public static InputStream executePost(String path, Map<String, String> params) throws Exception {
		HttpPost httpPost = new HttpPost(path);
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> param : params.entrySet()) {
			postParams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}
		HttpEntity entity = new UrlEncodedFormEntity(postParams, "UTF-8");
		httpPost.setEntity(entity);

		HttpResponse httpResponse = sClient.execute(httpPost);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return httpResponse.getEntity().getContent();
		} else {
			return null;
		}
	}
}