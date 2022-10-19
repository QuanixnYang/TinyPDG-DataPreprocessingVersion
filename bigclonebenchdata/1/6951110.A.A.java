public class A{
    public ValidEPoint[] split(EPoint o, EPoint e1, long v1, EPoint e2) throws MalformedURLException, IOException, NoSuchAlgorithmException, InvalidEPointCertificateException, InvalidKeyException, SignatureException {
        URLConnection u = new URL(url, "action").openConnection();
        OutputStream os;
        InputStream is;
        ValidEPoint[] v = new ValidEPoint[2];
        u.setDoOutput(true);
        u.setDoInput(true);
        u.setAllowUserInteraction(false);
        os = u.getOutputStream();
        os.write(("B=" + URLEncoder.encode(o.toString(), "UTF-8") + "&D=" + Base16.encode(e1.getMD()) + "&F=" + Long.toString(v1) + "&C=" + Base16.encode(e2.getMD())).getBytes());
        os.close();
        is = u.getInputStream();
        v[1] = new ValidEPoint(this, e2, is);
        is.close();
        v[0] = validate(e1);
        return v;
    }
}