public class A{
    InputStream createInputStream(FileInfo fi) throws IOException, MalformedURLException {
        if (fi.inputStream != null) return fi.inputStream; else if (fi.url != null && !fi.url.equals("")) return new URL(fi.url + fi.fileName).openStream(); else {
            File f = new File(fi.directory + fi.fileName);
            if (f == null || f.isDirectory()) return null; else {
                InputStream is = new FileInputStream(f);
                if (fi.compression >= FileInfo.LZW) is = new RandomAccessStream(is);
                return is;
            }
        }
    }
}