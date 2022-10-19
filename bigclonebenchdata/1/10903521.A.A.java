public class A{
    private synchronized void awaitResponse() throws BOSHException {
        HttpEntity entity = null;
        try {
            HttpResponse httpResp = client.execute(post, context);
            entity = httpResp.getEntity();
            byte[] data = EntityUtils.toByteArray(entity);
            String encoding = entity.getContentEncoding() != null ? entity.getContentEncoding().getValue() : null;
            if (ZLIBCodec.getID().equalsIgnoreCase(encoding)) {
                data = ZLIBCodec.decode(data);
            } else if (GZIPCodec.getID().equalsIgnoreCase(encoding)) {
                data = GZIPCodec.decode(data);
            }
            body = StaticBody.fromString(new String(data, CHARSET));
            statusCode = httpResp.getStatusLine().getStatusCode();
            sent = true;
        } catch (IOException iox) {
            abort();
            toThrow = new BOSHException("Could not obtain response", iox);
            throw (toThrow);
        } catch (RuntimeException ex) {
            abort();
            throw (ex);
        }
    }
}