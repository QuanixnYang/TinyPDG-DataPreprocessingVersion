public class A{
    public void testSimpleQuery() throws Exception {
        JCRNodeSource dummySource = (JCRNodeSource) resolveSource(BASE_URL + "users/alexander.klimetschek");
        assertNotNull(dummySource);
        OutputStream os = ((ModifiableSource) dummySource).getOutputStream();
        assertNotNull(os);
        String dummyContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user><id>alexander</id><teamspace>cyclr</teamspace><teamspace>mindquarryTooLong</teamspace></user>";
        os.write(dummyContent.getBytes());
        os.flush();
        os.close();
        JCRNodeSource source = (JCRNodeSource) resolveSource(BASE_URL + "users/bastian");
        assertNotNull(source);
        os = ((ModifiableSource) source).getOutputStream();
        assertNotNull(os);
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user><id>bastian</id><teamspace>mindquarry</teamspace></user>";
        os.write(content.getBytes());
        os.flush();
        os.close();
        QueryResultSource qResult = (QueryResultSource) resolveSource(BASE_URL + "users?/*[.//user/teamspace='mindquarry']");
        assertNotNull(qResult);
        Collection results = qResult.getChildren();
        assertEquals(1, results.size());
        Iterator it = results.iterator();
        JCRNodeSource rSrc = (JCRNodeSource) it.next();
        InputStream rSrcIn = rSrc.getInputStream();
        ByteArrayOutputStream actualOut = new ByteArrayOutputStream();
        IOUtils.copy(rSrcIn, actualOut);
        rSrcIn.close();
        assertEquals(content, actualOut.toString());
        actualOut.close();
        rSrc.delete();
    }
}