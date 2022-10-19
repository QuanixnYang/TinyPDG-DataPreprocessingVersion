public class A{
    @Override
    public Object getImage(String key) {
        if (key.indexOf("exhibition/") != -1) {
            InputStream inputStream = null;
            try {
                URL url = new URL(getBaseURL() + "icons/" + key + ".png");
                inputStream = url.openStream();
                return url;
            } catch (Exception e) {
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        ExceptionHandlingService.INSTANCE.handleException(e);
                    }
                }
            }
        }
        return super.getImage(key);
    }
}