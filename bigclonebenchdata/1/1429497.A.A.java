public class A{
    private Scanner getUrlScanner(String strUrl) {
        URL urlParticipants = null;
        Scanner scannerParticipants;
        try {
            urlParticipants = new URL(strUrl);
            URLConnection connParticipants;
            if (StringUtils.isBlank(this.configProxyIp)) {
                connParticipants = urlParticipants.openConnection();
            } else {
                SocketAddress address = new InetSocketAddress(this.configProxyIp, this.configProxyPort);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
                connParticipants = urlParticipants.openConnection(proxy);
            }
            InputStream streamParticipant = connParticipants.getInputStream();
            String charSet = StringUtils.substringAfterLast(connParticipants.getContentType(), "charset=");
            scannerParticipants = new Scanner(streamParticipant, charSet);
        } catch (MalformedURLException e) {
            throw new IcehorsetoolsRuntimeException(MessageFormat.format(Lang.get(this.getClass(), "MalformedURLException"), new Object[] { urlParticipants.toString() }));
        } catch (IOException e) {
            throw new IcehorsetoolsRuntimeException(MessageFormat.format(Lang.get(this.getClass(), "IOException"), new Object[] { urlParticipants.toString() }));
        }
        return scannerParticipants;
    }
}