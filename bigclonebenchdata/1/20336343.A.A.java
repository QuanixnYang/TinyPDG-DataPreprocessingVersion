public class A{
    public static Properties loadProperties() {
        try {
            if (url == null) url = ClassLoader.getSystemResource("application.properties");
            if (url == null) throw new FileNotFoundException("application.properties");
            props = new Properties();
            props.load(url.openStream());
            Enumeration e = System.getProperties().propertyNames();
            String key;
            while (e.hasMoreElements()) {
                key = (String) e.nextElement();
                props.setProperty(key, System.getProperty(key));
            }
            return props;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}