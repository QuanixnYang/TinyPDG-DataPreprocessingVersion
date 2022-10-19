public class A{
    private static void login(HttpClient client, String token) throws Exception {
        HttpPost login = new HttpPost("https://libsys.arlingtonva.us/iii/cas/login?service=http&amp;scope=1");
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("code", "202002686286"));
        formParams.add(new BasicNameValuePair("pin", "3128"));
        formParams.add(new BasicNameValuePair("_eventId", "submit"));
        formParams.add(new BasicNameValuePair("lt", token));
        UrlEncodedFormEntity form = new UrlEncodedFormEntity(formParams, "UTF-8");
        login.setEntity(form);
        HttpResponse response = client.execute(login);
        EntityUtils.consume(response.getEntity());
        Header[] headers = response.getAllHeaders();
        System.out.println("HEADERS: ");
        for (Header hdr : headers) {
            System.out.println(hdr.getName() + ": " + hdr.getValue());
        }
    }
}