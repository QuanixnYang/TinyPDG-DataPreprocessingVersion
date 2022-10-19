public class A{
    private RatingServiceSelectionResponseType contactService(String xmlInputString) throws Exception {
        OutputStream outputStream = null;
        RatingServiceSelectionResponseType rType = null;
        try {
            URL url = new URL(ENDPOINT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            outputStream = connection.getOutputStream();
            outputStream.write(xmlInputString.getBytes());
            outputStream.flush();
            outputStream.close();
            rType = readURLConnection(connection);
            connection.disconnect();
        } catch (Exception e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
                outputStream = null;
            }
        }
        return rType;
    }
}