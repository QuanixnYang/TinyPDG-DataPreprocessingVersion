public class A{
    public void send(String payload, TransportReceiver receiver) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost();
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setHeader("Cookie", cookie);
        post.setURI(uri);
        Throwable ex;
        try {
            post.setEntity(new StringEntity(payload, "UTF-8"));
            HttpResponse response = client.execute(post);
            if (200 == response.getStatusLine().getStatusCode()) {
                String contents = readStreamAsString(response.getEntity().getContent());
                receiver.onTransportSuccess(contents);
            } else {
                receiver.onTransportFailure(new ServerFailure(response.getStatusLine().getReasonPhrase()));
            }
            return;
        } catch (UnsupportedEncodingException e) {
            ex = e;
        } catch (ClientProtocolException e) {
            ex = e;
        } catch (IOException e) {
            ex = e;
        }
        receiver.onTransportFailure(new ServerFailure(ex.getMessage()));
    }
}