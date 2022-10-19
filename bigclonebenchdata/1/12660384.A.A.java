public class A{
    public static void main(String[] args) {
        final String filePath1 = "e:\\mysite\\data\\up\\itsite";
        final String filePath2 = "d:\\works\\itsite\\itsite";
        IOUtils.listAllFilesNoRs(new File(filePath2), new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.getName().equals(".svn")) {
                    return false;
                }
                final long modify = file.lastModified();
                final long time = DateUtils.toDate("2012-03-21 17:43", "yyyy-MM-dd HH:mm").getTime();
                if (modify >= time) {
                    if (file.isFile()) {
                        File f = new File(StringsUtils.replace(file.getAbsolutePath(), filePath2, filePath1));
                        f.getParentFile().mkdirs();
                        try {
                            IOUtils.copyFile(file, f);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(f.getName());
                    }
                }
                return true;
            }
        });
    }
}