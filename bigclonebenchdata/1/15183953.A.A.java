public class A{
        private byte[] cacheInputStream(URL url) throws IOException {
            InputStream ins = url.openStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buf = new byte[256];
            while (true) {
                int n = ins.read(buf);
                if (n == -1) break;
                bout.write(buf, 0, n);
            }
            return bout.toByteArray();
        }
}