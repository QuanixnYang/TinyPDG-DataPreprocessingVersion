public class A{
    public void configureLogging() {
        try {
            PreferenceStore preferences = new PreferenceStore();
            IExtensionRegistry registry = Platform.getExtensionRegistry();
            IExtensionPoint extensionPoint = registry.getExtensionPoint(CorePlugin.LOGGER_PREFERENCES_EXTENSION_POINT);
            IConfigurationElement[] members = extensionPoint.getConfigurationElements();
            for (int i = 0; i < members.length; i++) {
                IConfigurationElement element = members[i];
                if (element.getName().equals("logger")) {
                    if (element.getAttribute("defaultValue") != null) {
                        String[] item = element.getAttribute("name").split(";");
                        for (int x = 0; x < item.length; x++) preferences.setDefault("log4j.logger." + item[x], element.getAttribute("defaultValue"));
                    }
                }
            }
            try {
                URL url = CorePlugin.getDefault().getBundle().getResource("log4j.properties");
                Properties properties = new Properties();
                properties.load(url.openStream());
                for (Iterator iter = properties.keySet().iterator(); iter.hasNext(); ) {
                    String key = (String) iter.next();
                    preferences.setDefault(key, (String) properties.get(key));
                }
                File file = CorePlugin.getDefault().getStateLocation().append("log4j.properties").toFile();
                if (file.exists()) preferences.load(new FileInputStream(file));
            } catch (Exception e) {
                CorePlugin.logException(e);
            }
            Properties properties = new Properties();
            String[] names = preferences.preferenceNames();
            for (int i = 0; i < names.length; i++) properties.put(names[i], preferences.getString(names[i]));
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            BasicConfigurator.configure();
            logException(e);
        }
    }
}