public class A{
    byte[] loadUrlByteArray(String szName, int offset, int size) {
        byte[] baBuffer = new byte[size];
        try {
            URL url = new URL(waba.applet.Applet.currentApplet.getCodeBase(), szName);
            try {
                InputStream file = url.openStream();
                if (size == 0) {
                    int n = file.available();
                    baBuffer = new byte[n - offset];
                }
                DataInputStream dataFile = new DataInputStream(file);
                try {
                    dataFile.skip(offset);
                    dataFile.readFully(baBuffer);
                } catch (EOFException e) {
                    System.err.print(e.getMessage());
                }
                file.close();
            } catch (IOException e) {
                System.err.print(e.getMessage());
            }
        } catch (MalformedURLException e) {
            System.err.print(e.getMessage());
        }
        return baBuffer;
    }
}