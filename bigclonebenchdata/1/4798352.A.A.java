public class A{
    public static void readProperties() throws IOException {
        URL url1 = cl.getResource("conf/soapuddi.config");
        Properties props = new Properties();
        if (url1 == null) throw new IOException("soapuddi.config not found");
        props.load(url1.openStream());
        className = props.getProperty("Class");
        url = props.getProperty("URL");
        user = props.getProperty("user");
        password = props.getProperty("passwd");
        operatorName = props.getProperty("operator");
        authorisedName = props.getProperty("authorisedName");
        isUpdated = true;
    }
}