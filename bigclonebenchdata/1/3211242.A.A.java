public class A{
    public static String callService(String urlString) throws NoItemException, ServiceException {
        if (urlString == null || urlString.length() < 1) return null;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            HttpURLConnection htpc = (HttpURLConnection) connection;
            int responseCode = htpc.getResponseCode();
            String responseMessage = htpc.getResponseMessage();
            String contentType = htpc.getContentType();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteBuffer buffer = WWIO.readStreamToBuffer(inputStream);
                String charsetName = getCharsetName(contentType);
                return decodeBuffer(buffer, charsetName);
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                throw new NoItemException(responseMessage);
            } else {
                throw new ServiceException(responseMessage);
            }
        } catch (MalformedURLException e) {
            String msg = Logging.getMessage("generic.MalformedURL", urlString);
            Logging.logger().log(java.util.logging.Level.SEVERE, msg);
            throw new WWRuntimeException(msg);
        } catch (IOException e) {
            String msg = Logging.getMessage("POI.ServiceError", urlString);
            Logging.logger().log(java.util.logging.Level.SEVERE, msg);
            throw new ServiceException(msg);
        } finally {
            WWIO.closeStream(inputStream, urlString);
        }
    }
}