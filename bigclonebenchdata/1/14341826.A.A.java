public class A{
    public static String exchangeForSessionToken(String protocol, String domain, String onetimeUseToken, PrivateKey key) throws IOException, GeneralSecurityException, AuthenticationException {
        String sessionUrl = getSessionTokenUrl(protocol, domain);
        URL url = new URL(sessionUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        String header = formAuthorizationHeader(onetimeUseToken, key, url, "GET");
        httpConn.setRequestProperty("Authorization", header);
        if (httpConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new AuthenticationException(httpConn.getResponseCode() + ": " + httpConn.getResponseMessage());
        }
        String body = IOUtils.toString(httpConn.getInputStream());
        Map parsedTokens = StringUtils.string2Map(body, "\n", "=", true);
        parsedTokens = StringUtils.lowercaseKeys(parsedTokens);
        return (String) parsedTokens.get("token");
    }
}