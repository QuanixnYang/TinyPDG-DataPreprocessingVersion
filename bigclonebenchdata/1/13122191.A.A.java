public class A{
    public static void copy(String inputFile, String outputFile) throws EDITSException {
        try {
            FileReader in = new FileReader(inputFile);
            FileWriter out = new FileWriter(outputFile);
            int c;
            while ((c = in.read()) != -1) out.write(c);
            in.close();
            out.close();
        } catch (Exception e) {
            throw new EDITSException("Could not copy " + inputFile + " into " + outputFile + " because:\n" + e.getMessage());
        }
    }
}