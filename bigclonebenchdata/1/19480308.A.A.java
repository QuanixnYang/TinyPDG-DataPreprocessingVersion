public class A{
        @Override
        @SuppressWarnings("empty-statement")
        public void run() {
            String server = System.getProperty("server.downsampler");
            if (server == null) server = FALLBACK;
            String url = server + "cgi-bin/downsample.cgi?" + this._uri.toString();
            url = url.replaceAll("\\?#$", "");
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setDoInput(true);
                this._input_stream = connection.getInputStream();
                while (this._input_stream.read() != '\n') ;
                this._complete = true;
            } catch (Exception e) {
                new ErrorEvent().send(e);
            }
        }
}