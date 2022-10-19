public class A{
    private static URL handleRedirectUrl(URL url) {
        try {
            URLConnection cn = url.openConnection();
            Utils.setHeader(cn);
            if (!(cn instanceof HttpURLConnection)) {
                return url;
            }
            HttpURLConnection hcn = (HttpURLConnection) cn;
            hcn.setInstanceFollowRedirects(false);
            int resCode = hcn.getResponseCode();
            if (resCode == 200) {
                System.out.println("URL: " + url);
                return url;
            }
            String location = hcn.getHeaderField("Location");
            hcn.disconnect();
            return handleRedirectUrl(new URL(location.replace(" ", "%20")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}