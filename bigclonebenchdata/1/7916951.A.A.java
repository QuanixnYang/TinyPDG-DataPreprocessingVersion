public class A{
    @Override
    public List<WebSearchResult> search(String term) {
        List<GoogleResult> results = null;
        try {
            URL url = new URL(GoogleWebSearch.GOOGLE_URL + URLEncoder.encode(term, GoogleWebSearch.CHARSET));
            Reader reader = new InputStreamReader(url.openStream(), GoogleWebSearch.CHARSET);
            GoogleResponse jsonResults = new Gson().fromJson(reader, GoogleResponse.class);
            results = jsonResults.getResponseData().getResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WebSearchResult> googleResults = new ArrayList<WebSearchResult>();
        if (results != null) {
            googleResults.addAll(results);
        }
        return googleResults;
    }
}