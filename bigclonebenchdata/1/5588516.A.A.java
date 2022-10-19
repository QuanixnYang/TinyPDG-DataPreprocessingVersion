public class A{
    public LineIterator iterator() {
        LineIterator ret;
        final String charsetname;
        final Charset charset;
        final CharsetDecoder charsetDecoder;
        synchronized (this) {
            charsetname = this.charsetname;
            charset = this.charset;
            charsetDecoder = this.charsetDecoder;
        }
        try {
            if (charsetDecoder != null) ret = new LineIterator(this, url.openStream(), returnNullUponEof, charsetDecoder); else if (charset != null) ret = new LineIterator(this, url.openStream(), returnNullUponEof, charset); else if (charsetname != null) ret = new LineIterator(this, url.openStream(), returnNullUponEof, Charset.forName(charsetname)); else ret = new LineIterator(this, url.openStream(), returnNullUponEof, (Charset) null);
            synchronized (openedIterators) {
                openedIterators.add(ret);
            }
            return ret;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}