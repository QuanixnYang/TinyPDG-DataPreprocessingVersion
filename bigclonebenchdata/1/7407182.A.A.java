public class A{
    public static void redirect(String strRequest, PrintWriter sortie) throws Exception {
        String level = "info.";
        if (ConnectorServlet.debug) level = "debug.";
        Log log = LogFactory.getLog(level + "fr.brgm.exows.gml2gsml.GFI");
        URL url2Request = new URL(strRequest);
        URLConnection conn = url2Request.openConnection();
        DataInputStream buffin = new DataInputStream(new BufferedInputStream(conn.getInputStream()));
        String strLine = null;
        while ((strLine = buffin.readLine()) != null) {
            sortie.println(strLine);
        }
        buffin.close();
    }
}