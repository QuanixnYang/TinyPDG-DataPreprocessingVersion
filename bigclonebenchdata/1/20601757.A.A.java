public class A{
    public static File copyFileAs(String path, String newName) {
        File src = new File(path);
        File dest = new File(newName);
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
            FileChannel source = new FileInputStream(src).getChannel();
            FileChannel destination = new FileOutputStream(dest).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}