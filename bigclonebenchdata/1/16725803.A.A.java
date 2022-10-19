public class A{
    public void validateXml(InputStream inputData, ErrorHandler errorHandler) throws SAXException, IOException, Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        spf.setNamespaceAware(true);
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            URL schemaURL = new URL(schemeUrl);
            InputStream urlStream = null;
            try {
                urlStream = schemaURL.openStream();
            } catch (IOException ex) {
                if (defaultUrl != null) {
                    schemaURL = new URL(defaultUrl);
                    urlStream = schemaURL.openStream();
                } else {
                    throw ex;
                }
            }
            LOGGER.debug("Uses schema url : " + schemaURL);
            StreamSource sss = new StreamSource(urlStream);
            Schema schema = schemaFactory.newSchema(sss);
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(errorHandler);
            reader.parse(new InputSource(inputData));
        } catch (ParserConfigurationException e) {
            throw new SAXException(e);
        }
    }
}