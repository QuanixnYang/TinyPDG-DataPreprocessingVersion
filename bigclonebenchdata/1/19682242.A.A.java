public class A{
    private boolean getRemoteFiles() throws Exception {
        boolean resp = false;
        int respCode = 0;
        URL url = new URL(storageUrlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        RequestUtils requestUtils = new RequestUtils();
        requestUtils.preRequestAddParameter("senderObj", "FileGetter");
        requestUtils.preRequestAddParameter("wfiType", "zen");
        requestUtils.preRequestAddParameter("portalID", this.portalID);
        requestUtils.preRequestAddParameter("userID", this.userID);
        addRenameFileParameters(requestUtils);
        requestUtils.createPostRequest();
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + requestUtils.getBoundary());
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        try {
            httpURLConnection.connect();
            OutputStream out = httpURLConnection.getOutputStream();
            byte[] preBytes = requestUtils.getPreRequestStringBytes();
            out.write(preBytes);
            out.flush();
            byte[] postBytes = requestUtils.getPostRequestStringBytes();
            out.write(postBytes);
            out.flush();
            out.close();
            respCode = httpURLConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == respCode) {
                resp = true;
                InputStream in = httpURLConnection.getInputStream();
                ZipUtils.getInstance().getFilesFromStream(in, getFilesDir);
                in.close();
            }
            if (respCode == 500) {
                resp = false;
            }
            if (respCode == 560) {
                resp = false;
                throw new Exception("Server Side Remote Exeption !!! respCode = (" + respCode + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Cannot connect to: " + storageUrlString, e);
        }
        return resp;
    }
}