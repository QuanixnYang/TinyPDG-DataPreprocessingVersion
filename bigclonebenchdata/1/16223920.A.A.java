public class A{
    public AudioFileFormat getAudioFileFormat(URL url) throws UnsupportedAudioFileException, IOException {
        InputStream urlStream = url.openStream();
        AudioFileFormat fileFormat = null;
        try {
            fileFormat = getFMT(urlStream, false);
        } finally {
            urlStream.close();
        }
        return fileFormat;
    }
}