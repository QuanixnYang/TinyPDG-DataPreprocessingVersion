public class A{
    protected void downloadFile(String filename, java.io.File targetFile, File partFile, ProgressMonitor monitor) throws java.io.IOException {
        FileOutputStream out = null;
        InputStream is = null;
        try {
            filename = toCanonicalFilename(filename);
            URL url = new URL(root + filename.substring(1));
            URLConnection urlc = url.openConnection();
            int i = urlc.getContentLength();
            monitor.setTaskSize(i);
            out = new FileOutputStream(partFile);
            is = urlc.getInputStream();
            monitor.started();
            copyStream(is, out, monitor);
            monitor.finished();
            out.close();
            is.close();
            if (!partFile.renameTo(targetFile)) {
                throw new IllegalArgumentException("unable to rename " + partFile + " to " + targetFile);
            }
        } catch (IOException e) {
            if (out != null) out.close();
            if (is != null) is.close();
            if (partFile.exists() && !partFile.delete()) {
                throw new IllegalArgumentException("unable to delete " + partFile);
            }
            throw e;
        }
    }
}