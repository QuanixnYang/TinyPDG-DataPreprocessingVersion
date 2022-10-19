public class A{
    public ResponseStatus nowPlaying(String artist, String track, String album, int length, int tracknumber) throws IOException {
        if (sessionId == null) throw new IllegalStateException("Perform successful handshake first.");
        String b = album != null ? encode(album) : "";
        String l = length == -1 ? "" : String.valueOf(length);
        String n = tracknumber == -1 ? "" : String.valueOf(tracknumber);
        String body = String.format("s=%s&a=%s&t=%s&b=%s&l=%s&n=%s&m=", sessionId, encode(artist), encode(track), b, l, n);
        if (Caller.getInstance().isDebugMode()) System.out.println("now playing: " + body);
        Proxy proxy = Caller.getInstance().getProxy();
        HttpURLConnection urlConnection = Caller.getInstance().openConnection(nowPlayingUrl);
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        OutputStream outputStream = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(body);
        writer.close();
        InputStream is = urlConnection.getInputStream();
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        String status = r.readLine();
        r.close();
        return new ResponseStatus(ResponseStatus.codeForStatus(status));
    }
}