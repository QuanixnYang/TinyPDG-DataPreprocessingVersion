public class A{
    public void checkVersion(boolean showOnlyDiff) {
        try {
            DataInputStream di = null;
            byte[] b = new byte[1];
            URL url = new URL("http://lanslim.sourceforge.net/version.txt");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            di = new DataInputStream(con.getInputStream());
            StringBuffer lBuffer = new StringBuffer();
            while (-1 != di.read(b, 0, 1)) {
                lBuffer.append(new String(b));
            }
            String lLastStr = lBuffer.toString().trim();
            boolean equals = VERSION.equals(lLastStr);
            String lMessage = Externalizer.getString("LANSLIM.199", VERSION, lLastStr);
            if (!equals) {
                lMessage = lMessage + StringConstants.NEW_LINE + Externalizer.getString("LANSLIM.131") + StringConstants.NEW_LINE;
            }
            if (!equals || !showOnlyDiff) {
                JOptionPane.showMessageDialog(getRootPane().getParent(), lMessage, Externalizer.getString("LANSLIM.118"), JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(getRootPane().getParent(), Externalizer.getString("LANSLIM.200", SlimLogger.shortFormatException(e)), Externalizer.getString("LANSLIM.118"), JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(getRootPane().getParent(), Externalizer.getString("LANSLIM.200", SlimLogger.shortFormatException(e)), Externalizer.getString("LANSLIM.118"), JOptionPane.WARNING_MESSAGE);
        }
    }
}