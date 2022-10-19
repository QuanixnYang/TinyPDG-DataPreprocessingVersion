public class A{
    private static GSP loadGSP(URL url) {
        try {
            InputStream input = url.openStream();
            int c;
            while ((c = input.read()) != -1) {
                result = result + (char) c;
            }
            Unmarshaller unmarshaller = getUnmarshaller();
            unmarshaller.setValidation(false);
            GSP gsp = (GSP) unmarshaller.unmarshal(new InputSource());
            return gsp;
        } catch (Exception e) {
            System.out.println("loadGSP " + e);
            e.printStackTrace();
            return null;
        }
    }
}