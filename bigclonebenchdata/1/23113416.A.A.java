public class A{
    public TreeNode fetch(TreeNode owner, String pattern, String fetchChilds, String fetchAttributes, String flags, boolean updateOwner) throws Exception {
        builder.start(owner, updateOwner);
        parser.setDocumentHandler(builder);
        pattern = URLEncoder.encode(pattern);
        String arg = server + "?todo=fetch&db=" + db + "&document=" + document + "&pattern=" + pattern;
        if (fetchChilds != null) {
            arg += "&fetch-childs=" + URLEncoder.encode(fetchChilds);
        }
        if (fetchAttributes != null) {
            arg += "&fetch-attributes=" + URLEncoder.encode(fetchAttributes);
        }
        if (flags != null) {
            arg += "&flags=" + URLEncoder.encode(flags);
        }
        URL url = new URL(arg);
        URLConnection con = url.openConnection();
        con.setUseCaches(false);
        con.connect();
        InputSource xmlInput = new InputSource(new InputStreamReader(con.getInputStream(), "ISO-8859-1"));
        parser.parse(xmlInput);
        return owner;
    }
}