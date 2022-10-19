public class A{
    public void testBeAbleToDownloadAndUpload() throws IOException {
        OutputStream outputStream = fileSystem.createOutputStream(_("hello"), OutputMode.OVERWRITE);
        outputStream.write(new byte[] { 1, 2, 3 });
        outputStream.close();
        InputStream inputStream = fileSystem.createInputStream(_("hello"));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, buffer);
        inputStream.close();
        ensure.that(buffer.toByteArray()).eq(new byte[] { 1, 2, 3 });
    }
}