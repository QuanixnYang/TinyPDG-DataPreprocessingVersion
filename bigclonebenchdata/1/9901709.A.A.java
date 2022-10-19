public class A{
    public static IEntity readFromFile(File resourceName) {
        InputStream inputStream = null;
        try {
            URL urlResource = ModelLoader.solveResource(resourceName.getPath());
            if (urlResource != null) {
                inputStream = urlResource.openStream();
                return (IEntity) new ObjectInputStream(inputStream).readObject();
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            if (inputStream != null) try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
        return null;
    }
}