public class A{
    public static byte[] readUrl(URL url) {
        BufferedInputStream in = null;
        try {
            class Part {

                byte[] partData;

                int len;
            }
            in = new BufferedInputStream(url.openStream());
            LinkedList<Part> parts = new LinkedList<Part>();
            int len = 1;
            while (len > 0) {
                byte[] data = new byte[1024];
                len = in.read(data);
                if (len > 0) {
                    Part part = new Part();
                    part.partData = data;
                    part.len = len;
                    parts.add(part);
                }
            }
            int length = 0;
            for (Part part : parts) length += part.len;
            byte[] result = new byte[length];
            int pos = 0;
            for (Part part : parts) {
                System.arraycopy(part.partData, 0, result, pos, part.len);
                pos += part.len;
            }
            return result;
        } catch (IOException e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }
}