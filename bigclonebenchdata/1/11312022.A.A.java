public class A{
    public static void downloadFromUrl(URL url, String localFilename, String userAgent) throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;
        System.setProperty("java.net.useSystemProxies", "true");
        try {
            URLConnection urlConn = url.openConnection();
            if (userAgent != null) {
                urlConn.setRequestProperty("User-Agent", userAgent);
            }
            is = urlConn.getInputStream();
            fos = new FileOutputStream(localFilename);
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }
    }
}