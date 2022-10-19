public class A{
    public static boolean isImageLinkReachable(WebImage image) {
        if (image.getUrl() == null) return false;
        try {
            URL url = new URL(image.getUrl());
            url.openStream().close();
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}