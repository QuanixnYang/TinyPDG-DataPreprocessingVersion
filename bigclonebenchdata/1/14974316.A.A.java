public class A{
    public void connect() throws ClientProtocolException, IOException {
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        inputStream = entity.getContent();
        Header contentEncodingHeader = entity.getContentEncoding();
        if (contentEncodingHeader != null) {
            HeaderElement[] codecs = contentEncodingHeader.getElements();
            for (HeaderElement encoding : codecs) {
                if (encoding.getName().equalsIgnoreCase("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
            }
        }
        inputStream = new BufferedInputStream(inputStream, 2048);
    }
}