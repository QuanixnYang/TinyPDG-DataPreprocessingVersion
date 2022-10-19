public class A{
    protected Document loadDocument() throws MalformedURLException, DocumentException, IOException {
        if (jiraFilterURL.startsWith("file")) {
            URL url = getSourceURL();
            return parseDocument(url);
        } else {
            HttpClient httpClient = new DefaultHttpClient();
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("os_username", jiraUser));
            formparams.add(new BasicNameValuePair("os_password", jiraPassword));
            formparams.add(new BasicNameValuePair("os_cookie", "true"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            HttpPost post = new HttpPost(getJiraRootUrl() + "/secure/login.jsp");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            response.getEntity().consumeContent();
            String url_str = StringEscapeUtils.unescapeXml(jiraFilterURL);
            HttpGet get = new HttpGet(url_str);
            response = httpClient.execute(get);
            return parseDocument(response.getEntity().getContent());
        }
    }
}