public class A{
    private void resolveFileDeclarations(Query query, Map<String, URL> sqlDeclarations) {
        Statement stmt = query.getStatement();
        String fileDeclaration = stmt.getFile();
        boolean hasFileDeclaration = fileDeclaration != null && !"".equals(fileDeclaration);
        if (hasFileDeclaration) {
            try {
                URL url = sqlDeclarations.get(stmt.getFile());
                if (url != null) {
                    URLConnection conn = url.openConnection();
                    InputStream input = conn.getInputStream();
                    String sqlDeclaration = StreamUtils.obtainStreamContents(input);
                    stmt.setValue(sqlDeclaration);
                    input.close();
                }
            } catch (Exception e) {
            }
        }
    }
}