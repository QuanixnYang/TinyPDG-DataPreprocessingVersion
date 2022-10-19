public class A{
    public void testJPEGBuffImage() throws MalformedURLException, IOException {
        System.out.println("JPEGCodec BufferedImage:");
        long start = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < images.length; i++) {
            String url = Constants.getDefaultURIMediaConnectorBasePath() + "albums/hund/" + images[i];
            InputStream istream = (new URL(url)).openStream();
            JPEGImageDecoder dec = JPEGCodec.createJPEGDecoder(istream);
            BufferedImage image = dec.decodeAsBufferedImage();
            int width = image.getWidth();
            int height = image.getHeight();
            istream.close();
            System.out.println("w: " + width + " - h: " + height);
        }
        long stop = Calendar.getInstance().getTimeInMillis();
        System.out.println("zeit: " + (stop - start));
    }
}