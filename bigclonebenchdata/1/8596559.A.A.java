public class A{
    public boolean connect() {
        if (connectStatus > -1) return (connectStatus == 1);
        connectStatus = 0;
        try {
            URL url = new URL(getURL());
            m_connection = (HttpURLConnection) url.openConnection();
            m_connection.connect();
            processHeaders();
            m_inputStream = m_connection.getInputStream();
        } catch (MalformedURLException e) {
            newError("connect failed", e, true);
        } catch (IOException e) {
            newError("connect failed", e, true);
        }
        return (connectStatus == 1);
    }
}