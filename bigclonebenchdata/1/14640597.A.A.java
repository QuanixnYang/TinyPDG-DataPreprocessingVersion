public class A{
    private void reloadData(String dataSourceUrl) {
        try {
            URL url = new URL(dataSourceUrl);
            InputStream is = url.openStream();
            if (progressMonitor.isCanceled() == false) {
                progressMonitor.setNote("Building classifications...");
                progressMonitor.setProgress(2);
                mediator.loadClassificationTree(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}