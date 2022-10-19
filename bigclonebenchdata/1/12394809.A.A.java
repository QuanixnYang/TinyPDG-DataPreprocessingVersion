public class A{
    public static final File getFile(final URL url) throws IOException {
        final File shortcutFile;
        final File currentFile = files.get(url);
        if (currentFile == null || !currentFile.exists()) {
            shortcutFile = File.createTempFile("windowsIsLame", ".vbs");
            shortcutFile.deleteOnExit();
            files.put(url, shortcutFile);
            final InputStream stream = url.openStream();
            final FileOutputStream out = new FileOutputStream(shortcutFile);
            try {
                StreamUtils.copy(stream, out);
            } finally {
                out.close();
                stream.close();
            }
        } else shortcutFile = currentFile;
        return shortcutFile;
    }
}