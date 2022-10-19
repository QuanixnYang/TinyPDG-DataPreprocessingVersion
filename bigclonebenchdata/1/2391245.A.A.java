public class A{
    public ObjectInputStream sendObject(String sMethod, Serializable obj) {
        String sServlet = System.getProperty("servlet.jstat");
        if (sServlet != null) {
            try {
                URL url = new URL(sServlet);
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                ObjectOutputStream out = new ObjectOutputStream(conn.getOutputStream());
                out.writeObject(sMethod);
                out.writeObject(obj);
                out.flush();
                out.close();
                return new ObjectInputStream(conn.getInputStream());
            } catch (Exception exc) {
                System.out.println("Error on SpaceJobRunner::sendObjectPOST() --> " + exc);
            }
        } else {
        }
        return null;
    }
}