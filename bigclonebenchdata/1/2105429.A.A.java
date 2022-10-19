public class A{
    private byte[] pullMapBytes(String directoryLocation) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            URL url = new URL(directoryLocation);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = httpURLConnection.getInputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toByteArray();
    }
}