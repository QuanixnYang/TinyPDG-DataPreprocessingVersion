public class A{
    private URLConnection getURLConnection(String str) {
        try {
            if (isHttps) {
                System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
                if (isProxy) {
                    System.setProperty("https.proxyHost", proxyHost);
                    System.setProperty("https.proxyPort", proxyPort);
                }
            } else {
                if (isProxy) {
                    System.setProperty("http.proxyHost", proxyHost);
                    System.setProperty("http.proxyPort", proxyPort);
                }
            }
            URL url = new URL(str);
            return (url.openConnection());
        } catch (MalformedURLException me) {
            System.out.println("Malformed URL");
            me.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}