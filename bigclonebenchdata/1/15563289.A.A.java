public class A{
    public AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
        InputStream urlStream = null;
        BufferedInputStream bis = null;
        AudioFileFormat fileFormat = null;
        urlStream = url.openStream();
        AudioInputStream result = null;
        try {
            bis = new BufferedInputStream(urlStream, bisBufferSize);
            result = getAudioInputStream((InputStream) bis);
        } finally {
            if (result == null) {
                urlStream.close();
            }
        }
        return result;
    }
}