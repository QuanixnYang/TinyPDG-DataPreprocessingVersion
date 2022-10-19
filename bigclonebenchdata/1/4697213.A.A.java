public class A{
    protected void downloadJar(URL downloadURL, File jarFile, IProgressListener pl) {
        BufferedOutputStream out = null;
        InputStream in = null;
        URLConnection urlConnection = null;
        try {
            urlConnection = downloadURL.openConnection();
            out = new BufferedOutputStream(new FileOutputStream(jarFile));
            in = urlConnection.getInputStream();
            int len = in.available();
            Log.log("downloading jar with size: " + urlConnection.getContentLength());
            if (len < 1) len = 1024;
            byte[] buffer = new byte[len];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ignore) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}