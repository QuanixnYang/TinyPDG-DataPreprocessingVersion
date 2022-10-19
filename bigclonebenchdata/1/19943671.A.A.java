public class A{
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
        try {
            URL url = new URL(uri.toString());
            final URLConnection urlConnection = url.openConnection();
            InputStream result = urlConnection.getInputStream();
            Map<Object, Object> response = getResponse(options);
            if (response != null) {
                response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, urlConnection.getLastModified());
            }
            return result;
        } catch (RuntimeException exception) {
            throw new Resource.IOWrappedException(exception);
        }
    }
}