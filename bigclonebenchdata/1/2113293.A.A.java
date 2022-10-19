public class A{
    protected void setUp() throws Exception {
        testOutputDirectory = new File(getClass().getResource("/").getPath());
        zipFile = new File(this.testOutputDirectory, "/plugin.zip");
        zipOutputDirectory = new File(this.testOutputDirectory, "zip");
        zipOutputDirectory.mkdir();
        logger.fine("zip dir created");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
        zos.putNextEntry(new ZipEntry("css/"));
        zos.putNextEntry(new ZipEntry("css/system.properties"));
        System.getProperties().store(zos, null);
        zos.closeEntry();
        zos.putNextEntry(new ZipEntry("js/"));
        zos.putNextEntry(new ZipEntry("js/system.properties"));
        System.getProperties().store(zos, null);
        zos.closeEntry();
        zos.putNextEntry(new ZipEntry("WEB-INF/"));
        zos.putNextEntry(new ZipEntry("WEB-INF/classes/"));
        zos.putNextEntry(new ZipEntry("WEB-INF/classes/system.properties"));
        System.getProperties().store(zos, null);
        zos.closeEntry();
        zos.putNextEntry(new ZipEntry("WEB-INF/lib/"));
        zos.putNextEntry(new ZipEntry("WEB-INF/lib/mylib.jar"));
        File jarFile = new File(this.testOutputDirectory.getPath() + "/mylib.jar");
        JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFile));
        jos.putNextEntry(new ZipEntry("vqwiki/"));
        jos.putNextEntry(new ZipEntry("vqwiki/plugins/"));
        jos.putNextEntry(new ZipEntry("vqwiki/plugins/system.properties"));
        System.getProperties().store(jos, null);
        jos.closeEntry();
        jos.close();
        IOUtils.copy(new FileInputStream(jarFile), zos);
        zos.closeEntry();
        zos.close();
        jarFile.delete();
    }
}