public class A{
    private void connect(byte[] bData) {
        System.out.println("Connecting to: " + url.toString());
        String SOAPAction = "";
        URLConnection connection = null;
        try {
            connection = url.openConnection();
            httpConn = (HttpURLConnection) connection;
            httpConn.setRequestProperty("Content-Length", String.valueOf(bData.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
        } catch (IOException ioExp) {
            CommonLogger.error(this, "Error while connecting to  SOAP server !", ioExp);
        }
    }
}