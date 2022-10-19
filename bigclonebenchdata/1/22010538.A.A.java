public class A{
    private byte[] getBytes(String resource) throws IOException {
        InputStream is = HttpServletFileDownloadTest.class.getResourceAsStream(resource);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(is, out);
        IOUtils.closeQuietly(is);
        return out.toByteArray();
    }
}