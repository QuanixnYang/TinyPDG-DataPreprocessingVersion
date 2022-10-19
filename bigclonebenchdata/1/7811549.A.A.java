public class A{
    public AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
        if (TDebug.TraceAudioFileReader) {
            TDebug.out("TAudioFileReader.getAudioInputStream(URL): begin");
        }
        long lFileLengthInBytes = AudioSystem.NOT_SPECIFIED;
        InputStream inputStream = url.openStream();
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = getAudioInputStream(inputStream, lFileLengthInBytes);
        } catch (UnsupportedAudioFileException e) {
            inputStream.close();
            throw e;
        } catch (IOException e) {
            inputStream.close();
            throw e;
        }
        if (TDebug.TraceAudioFileReader) {
            TDebug.out("TAudioFileReader.getAudioInputStream(URL): end");
        }
        return audioInputStream;
    }
}