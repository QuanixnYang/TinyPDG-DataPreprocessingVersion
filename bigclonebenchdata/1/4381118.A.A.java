public class A{
    private void loadFile(File file) throws Exception {
        Edl edl = new Edl("file:///" + file.getAbsolutePath());
        URL url = ExtractaHelper.retrieveUrl(edl.getUrlRetrievalDescriptor());
        String sUrlString = url.toExternalForm();
        if (sUrlString.startsWith("file:/") && (sUrlString.charAt(6) != '/')) {
            sUrlString = sUrlString.substring(0, 6) + "//" + sUrlString.substring(6);
        }
        Document document = DomHelper.parseHtml(url.openStream());
        m_inputPanel.setDocument(document);
        m_resultPanel.setContext(new ResultContext(edl, document, url));
        initNameCounters(edl.getItemDescriptors());
        m_outputFile = file;
        m_sUrlString = sUrlString;
        m_urlTF.setText(m_sUrlString);
        updateHistroy(m_outputFile);
        setModified(false);
    }
}