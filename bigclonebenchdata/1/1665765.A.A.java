public class A{
    public InputStream openFileInputStream(String fileName) throws IOException {
        if (fileName.indexOf(':') > 1) {
            URL url = new URL(fileName);
            InputStream in = url.openStream();
            return in;
        }
        fileName = translateFileName(fileName);
        FileInputStream in = new FileInputStream(fileName);
        trace("openFileInputStream", fileName, in);
        return in;
    }
}