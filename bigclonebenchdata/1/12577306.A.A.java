public class A{
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "http://jdkcn.com/checkUpdateNew.jsp?ver=" + blogFacade.getDatabaseSiteConfig().getAppVersion();
        response.setCharacterEncoding("UTF-8");
        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line).append("\r\n");
            line = reader.readLine();
        }
        response.getWriter().println(sb.toString());
        return null;
    }
}