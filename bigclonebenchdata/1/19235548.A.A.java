public class A{
    protected BufferedImage handleICCException() {
        if (params.uri.startsWith("http://vacani.icc.cat") || params.uri.startsWith("http://louisdl.louislibraries.org")) try {
            params.uri = params.uri.replace("cdm4/item_viewer.php", "cgi-bin/getimage.exe") + "&DMSCALE=3";
            params.uri = params.uri.replace("/u?", "cgi-bin/getimage.exe?CISOROOT=").replace(",", "&CISOPTR=") + "&DMSCALE=3";
            URL url = new URL(params.uri);
            URLConnection connection = url.openConnection();
            return processNewUri(connection);
        } catch (Exception e) {
        }
        return null;
    }
}