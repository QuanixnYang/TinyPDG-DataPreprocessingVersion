public class A{
    public void chmod(String remoteFile, String mode) {
        String remotePath = connector.getRemoteDirectory();
        remotePath += PATH_SEPARATOR + remoteFile;
        FTPClient ftp = new FTPClient();
        try {
            String hostname = connector.getUrl().getHost();
            ftp.connect(hostname);
            log.info("Connected to " + hostname);
            log.info(ftp.getReplyString());
            boolean loggedIn = ftp.login(connector.getUsername(), connector.getPassword());
            if (loggedIn) {
                String parameters = "chmod " + mode + " " + remotePath;
                ftp.site(parameters);
                ftp.logout();
            }
            ftp.disconnect();
        } catch (SocketException e) {
            log.error("File chmod failed with message: " + e.getMessage());
        } catch (IOException e) {
            log.error("File chmod failed with message: " + e.getMessage());
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }
}