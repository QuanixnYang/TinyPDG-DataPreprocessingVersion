public class A{
    @Override
    public void parse() throws IOException {
        URL url = new URL(getDataUrl());
        URLConnection con = url.openConnection();
        BufferedReader bStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String str;
        while ((str = bStream.readLine()) != null) {
            String[] tokens = str.split(",");
            CurrencyUnit unit = new CurrencyUnit(tokens[1], Float.valueOf(tokens[3]), Integer.valueOf(tokens[2]));
            this.set.add(unit);
        }
    }
}