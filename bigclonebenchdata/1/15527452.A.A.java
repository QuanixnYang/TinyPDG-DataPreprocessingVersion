public class A{
    public static void copy(File source, File dest) throws IOException {
        if (dest.isDirectory()) {
            dest = new File(dest + File.separator + source.getName());
        }
        FileChannel in = null, out = null;
        try {
            in = new FileInputStream(source).getChannel();
            out = new FileOutputStream(dest).getChannel();
            long size = in.size();
            MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
            out.write(buf);
        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }
}