public class A{
    private ProgramYek getYek(String keyFilename) {
        File f = new File(keyFilename);
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (java.io.FileNotFoundException ee) {
        } catch (Exception e) {
            System.out.println("** Exception reading key: " + e);
        }
        if (is == null) {
            try {
                URL url = ChiselResources.getResourceByName(ProgramYek.getVidSys(), ChiselResources.LOADFROMCLASSPATH);
                if (url == null) {
                } else {
                    is = url.openStream();
                }
            } catch (Exception e) {
                System.out.println("** Exception reading key: " + e);
            }
        }
        ProgramYek y = null;
        if (is != null) {
            try {
                y = ProgramYek.read(is);
            } catch (Exception e) {
                System.out.println("** Exception reading key: " + e);
            }
        } else {
            File chk = new File(checkFilename);
            if (chk.exists()) {
                System.out.println("This is the evaluation version of " + appname);
                y = new ProgramYek(appname, "Evaluation", "", 15);
                ProgramYek.serialize(y, keyFilename);
            }
        }
        return y;
    }
}