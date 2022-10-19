public class A{
            public void run() {
                String baseUrl;
                if (_rdoSRTM3FtpUrl.getSelection()) {
                } else {
                    baseUrl = _txtSRTM3HttpUrl.getText().trim();
                    try {
                        final URL url = new URL(baseUrl);
                        final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                        urlConn.connect();
                        final int response = urlConn.getResponseCode();
                        final String responseMessage = urlConn.getResponseMessage();
                        final String message = response == HttpURLConnection.HTTP_OK ? NLS.bind(Messages.prefPage_srtm_checkHTTPConnectionOK_message, baseUrl) : NLS.bind(Messages.prefPage_srtm_checkHTTPConnectionFAILED_message, new Object[] { baseUrl, Integer.toString(response), responseMessage == null ? UI.EMPTY_STRING : responseMessage });
                        MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.prefPage_srtm_checkHTTPConnection_title, message);
                    } catch (final IOException e) {
                        MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.prefPage_srtm_checkHTTPConnection_title, NLS.bind(Messages.prefPage_srtm_checkHTTPConnection_message, baseUrl));
                        e.printStackTrace();
                    }
                }
            }
}