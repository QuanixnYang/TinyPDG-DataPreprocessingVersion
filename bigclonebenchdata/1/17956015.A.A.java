public class A{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/jar");
        byte[] bufor = new byte[BUF_LEN];
        ServletContext context = getServletContext();
        URL url = context.getResource(FILE_NAME);
        InputStream in = url.openStream();
        OutputStream out = response.getOutputStream();
        while (in.read(bufor) != -1) out.write(bufor);
        in.close();
        out.close();
    }
}