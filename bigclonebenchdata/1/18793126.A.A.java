public class A{
    private Image2D resolvePNG(URI uri) {
        Image2D image2D = null;
        if (uri.getScheme() != null) {
            try {
                URL url = new URL(uri.toString());
                InputStream is = url.openStream();
                BufferedImage image = PNGDecoder.decode(is);
                int imageType = Image2D.RGB;
                if (image.getType() == BufferedImage.TYPE_INT_RGB) imageType = Image2D.RGB; else if (image.getType() == BufferedImage.TYPE_INT_ARGB) imageType = Image2D.RGBA;
                image2D = new Image2D(imageType, image);
            } catch (IOException ex) {
            }
        } else {
            String path = uri.getPath();
            File file = new File(path);
            if (file.getAbsoluteFile().exists()) {
                try {
                    FileInputStream is = new FileInputStream(file);
                    BufferedImage image = PNGDecoder.decode(is);
                    int imageType = Image2D.RGB;
                    if (image.getType() == BufferedImage.TYPE_INT_RGB) imageType = Image2D.RGB; else if (image.getType() == BufferedImage.TYPE_INT_ARGB) imageType = Image2D.RGBA;
                    image2D = new Image2D(imageType, image);
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
            }
        }
        return image2D;
    }
}