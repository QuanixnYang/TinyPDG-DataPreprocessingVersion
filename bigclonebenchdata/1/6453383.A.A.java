public class A{
    @Override
    public TDSScene loadScene(URL url) throws IOException, IncorrectFormatException, ParsingErrorException {
        boolean baseURLWasNull = setBaseURLFromModelURL(url);
        TDSScene scene = loadScene(url.openStream());
        if (baseURLWasNull) {
            popBaseURL();
        }
        return (scene);
    }
}