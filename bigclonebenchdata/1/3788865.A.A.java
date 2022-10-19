public class A{
    public static void logout4www() throws NetworkException {
        HttpClient client = HttpUtil.newInstance();
        HttpGet get = new HttpGet(HttpUtil.KAIXIN_WWW_LOGOUT_URL);
        HttpUtil.setHeader(get);
        try {
            HttpResponse response = client.execute(get);
            if (response != null && response.getEntity() != null) {
                HTTPUtil.consume(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NetworkException(e);
        }
    }
}