public class A{
    private void bokActionPerformed(java.awt.event.ActionEvent evt) {
        Vector vret = this.uniformtitlepanel.getEnteredValuesKeys();
        String[] patlib = newgen.presentation.NewGenMain.getAppletInstance().getPatronLibraryIds();
        String xmlreq = newgen.presentation.administration.AdministrationXMLGenerator.getInstance().saveUniformTitleSH("2", vret, patlib);
        System.out.println(xmlreq);
        try {
            java.net.URL url = new java.net.URL(ResourceBundle.getBundle("Administration").getString("ServerURL") + ResourceBundle.getBundle("Administration").getString("ServletSubPath") + "UniformTitleSubjectHeadingServlet");
            java.net.URLConnection urlconn = (java.net.URLConnection) url.openConnection();
            urlconn.setDoOutput(true);
            java.io.OutputStream dos = urlconn.getOutputStream();
            dos.write(xmlreq.getBytes());
            java.io.InputStream ios = urlconn.getInputStream();
            SAXBuilder saxb = new SAXBuilder();
            Document retdoc = saxb.build(ios);
            Element rootelement = retdoc.getRootElement();
            if (rootelement.getChild("Error") == null) {
                this.showInformationMessage(ResourceBundle.getBundle("Administration").getString("DataSavedInDatabase"));
            } else {
                this.showErrorMessage(ResourceBundle.getBundle("Administration").getString("ErrorPleaseContactTheVendor"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}