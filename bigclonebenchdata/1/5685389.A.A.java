public class A{
    private void extractSpecifications(String id, File specification) {
        Object resource = getClass().getResource(id + ".xml");
        if (resource instanceof URL) {
            URL url = (URL) resource;
            try {
                InputStream istream = url.openStream();
                try {
                    OutputStream ostream = new FileOutputStream(specification);
                    try {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = istream.read(buffer)) > 0) {
                            ostream.write(buffer, 0, length);
                        }
                    } finally {
                        ostream.close();
                    }
                } finally {
                    istream.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException("Failed to open " + url, ex);
            }
        }
    }
}