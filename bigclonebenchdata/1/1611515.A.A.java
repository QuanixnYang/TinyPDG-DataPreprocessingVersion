public class A{
    public static Image getPluginImage(Object plugin, String name) {
        try {
            try {
                URL url = getPluginImageURL(plugin, name);
                if (mURLImageMap.containsKey(url)) {
                    return mURLImageMap.get(url);
                }
                InputStream is = url.openStream();
                Image image;
                try {
                    image = getImage(is);
                    mURLImageMap.put(url, image);
                } finally {
                    is.close();
                }
                return image;
            } catch (Throwable e) {
                LOG.debug("Ignore any exceptions");
            }
        } catch (Throwable e) {
            LOG.debug("Ignore any exceptions");
        }
        return null;
    }
}