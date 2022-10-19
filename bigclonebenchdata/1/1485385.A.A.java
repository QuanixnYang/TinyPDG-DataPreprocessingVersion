public class A{
    private void runUpdateAppListing() {
        DataStorage.clearListedAppListing();
        GenericUrl url = new GoogleUrl(EnterpriseMarketplaceUrl.generateAppListingUrl() + DataStorage.getVendorProfile().vendorId);
        AppListing appListingBody = buildAppListing(appsMarketplaceProject);
        JsonHttpContent content = new JsonHttpContent();
        content.jsonFactory = jsonFactory;
        if (appListingBody != null) {
            content.data = appListingBody;
        }
        AppListing appListing;
        try {
            HttpRequest request = requestFactory.buildPutRequest(url, content);
            request.addParser(jsonHttpParser);
            request.readTimeout = readTimeout;
            HttpResponse response = request.execute();
            appListing = response.parseAs(AppListing.class);
            operationStatus = validateAppListing(appListing, appListingBody);
            if (operationStatus) {
                DataStorage.setListedAppListing(appListing);
            }
            response.getContent().close();
        } catch (IOException e) {
            AppsMarketplacePluginLog.logError(e);
        }
    }
}