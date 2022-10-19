public class A{
    public static void download(URL url, File file, String userAgent) throws IOException {
        URLConnection conn = url.openConnection();
        if (userAgent != null) {
            conn.setRequestProperty("User-Agent", userAgent);
        }
        InputStream in = conn.getInputStream();
        FileOutputStream out = new FileOutputStream(file);
        StreamUtil.copyThenClose(in, out);
    }
}