public class A{
    List<String> options(String path) throws TwinException {
        try {
            BasicHttpRequest request = new BasicHttpRequest("OPTIONS", url + path);
            HttpClient client = getClient();
            HttpResponse response = client.execute(new HttpHost(url.getHost(), url.getPort()), request);
            Header hdr = response.getFirstHeader("Allow");
            if (hdr == null || hdr.getValue().isEmpty()) return Collections.emptyList();
            return Arrays.asList(hdr.getValue().split("\\s*,\\s*"));
        } catch (IOException e) {
            throw TwinError.UnknownError.create("IOException when accessing RC", e);
        }
    }
}