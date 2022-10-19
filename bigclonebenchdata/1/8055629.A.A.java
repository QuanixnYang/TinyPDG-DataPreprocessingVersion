public class A{
    void setURLString(String path, boolean forceLoad) {
        if (path != null) {
            if (this.url != null || inputStream != null) throw new IllegalArgumentException(Ding3dI18N.getString("MediaContainer5"));
            try {
                URL url = new URL(path);
                InputStream stream;
                stream = url.openStream();
                stream.close();
            } catch (Exception e) {
                throw new SoundException(javax.media.ding3d.Ding3dI18N.getString("MediaContainer0"));
            }
        }
        this.urlString = path;
        if (forceLoad) dispatchMessage();
    }
}