public class A{
    public void compareResult(String path, String expected) throws IOException {
        if (path.length() == 0 || path.charAt(0) != '/') path = "/" + path;
        URL url = new URL(getBase() + path);
        String actual = IOUtils.toString(url.openStream());
        Assert.assertEquals(url.toString(), expected, actual);
    }
}