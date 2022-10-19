public class A{
    public ActionResponse executeAction(ActionRequest request) throws Exception {
        ActionResponse resp = new ActionResponse();
        BufferedReader in = null;
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int status = conn.getResponseCode();
            if (status == 200) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    String line = in.readLine();
                    if (line == null) break;
                    resp.addResult("REMOTEVERSION", line);
                }
            } else {
                resp.setErrorCode(ActionResponse.GENERAL_ERROR);
                resp.setErrorMessage("HTTP Error [" + status + "]");
            }
        } catch (Exception e) {
            resp.setErrorCode(ActionResponse.GENERAL_ERROR);
            resp.setErrorMessage(e.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return resp;
    }
}