public class A{
    public void run() {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Osm2Model osm = new Osm2Model(pedestrian, filterCyclic);
            osm.progress.connect(this, "progress(int)");
            osm.parseFile(con.getInputStream(), con.getContentLength());
            if (osm.somethingImported()) {
                done.emit();
            } else {
                nothing.emit();
            }
        } catch (Exception e) {
            failed.emit();
        }
    }
}