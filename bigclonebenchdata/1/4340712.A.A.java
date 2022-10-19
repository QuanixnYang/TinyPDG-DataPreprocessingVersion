public class A{
    public static Map<VariableLengthInteger, ElementDescriptor> readDescriptors(URL url) throws IOException, XMLStreamException {
        if (url == null) {
            throw new IllegalArgumentException("url is null");
        }
        InputStream stream = new BufferedInputStream(url.openStream());
        try {
            return readDescriptors(stream);
        } finally {
            try {
                stream.close();
            } catch (IOException ignored) {
            }
        }
    }
}