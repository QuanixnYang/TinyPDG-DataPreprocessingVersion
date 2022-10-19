public class A{
    public static void readUrlWriteFileTest(String url, String fileName) throws Exception {
        System.out.println("Initiated reading source queue URL: " + url);
        InputStream instream = new URL(url).openStream();
        Serializer serializer = new Serializer();
        Response response = (Response) serializer.parse(instream);
        Queue queue = response.getQueue();
        instream.close();
        System.out.println("Completed reading source queue URL (jobs=" + queue.size() + ")");
        System.out.println("Initiated writing target queue File: " + fileName);
        OutputStream outstream = new FileOutputStream(fileName);
        serializer.write(response, outstream);
        outstream.close();
        System.out.println("Completed writing target queue file.");
    }
}