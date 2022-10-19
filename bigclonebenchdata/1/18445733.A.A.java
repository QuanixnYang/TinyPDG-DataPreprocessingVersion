public class A{
    public DicomReader(URL url) throws java.io.IOException {
        final URLConnection u = url.openConnection();
        final int size = u.getContentLength();
        final byte[] array = new byte[size];
        int bytes_read = 0;
        final DataInputStream in = new DataInputStream(u.getInputStream());
        while (bytes_read < size) {
            bytes_read += in.read(array, bytes_read, size - bytes_read);
        }
        in.close();
        this.dHR = new DicomHeaderReader(array);
        h = dHR.getRows();
        w = dHR.getColumns();
        highBit = dHR.getHighBit();
        bitsStored = dHR.getBitStored();
        bitsAllocated = dHR.getBitAllocated();
        n = (bitsAllocated / 8);
        signed = (dHR.getPixelRepresentation() == 1);
        this.pixData = dHR.getPixels();
        ignoreNegValues = true;
        samplesPerPixel = dHR.getSamplesPerPixel();
        numberOfFrames = dHR.getNumberOfFrames();
        dbg("Number of Frames " + numberOfFrames);
    }
}