public class A{
    public void setUp() {
        try {
            String excelFile = "result" + java.io.File.separator + "input" + java.io.File.separator + "conextech.xls";
            java.io.File f1 = new java.io.File(excelFile);
            URL url = new URL("file:test/result/input/checksun.xls");
            InputStream is = url.openStream();
            workbook = Workbook.getWorkbook(is);
        } catch (MalformedURLException urlEx) {
            urlEx.printStackTrace();
            fail();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            fail();
        } catch (BiffException biffEx) {
            biffEx.printStackTrace();
            fail();
        }
    }
}