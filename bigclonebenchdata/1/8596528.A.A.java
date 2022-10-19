public class A{
    @Override
    public DaeScene loadScene(URL url) throws IOException, IncorrectFormatException, ParsingErrorException {
        NullArgumentException.check(url);
        boolean baseURLWasNull = setBaseURLFromModelURL(url);
        DaeScene scene = loadScene(url.openStream());
        if (baseURLWasNull) {
            popBaseURL();
        }
        return (scene);
    }
}