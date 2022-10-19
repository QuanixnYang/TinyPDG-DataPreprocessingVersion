public class A{
            @Override
            public void onClick(View v) {
                username = textusername.getText().toString();
                password = textpassword.getText().toString();
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new name_value("uname", username));
                nameValuePairs.add(new name_value("upass", password));
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://www.gotrackit.net/server/check_user.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                } catch (Exception e) {
                    Log.e("log_tag", "Error in http connection" + e.toString());
                }
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    sb = new StringBuilder();
                    sb.append(reader.readLine() + "\n");
                    String line = "0";
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                } catch (Exception e) {
                    Log.e("log_tag", "Error converting result " + e.toString());
                }
                if (result.contains("reject")) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    String wrong = "Invalid  Username or Password";
                    Toast toast = Toast.makeText(context, wrong, duration);
                    toast.show();
                } else {
                    MyApp uid = (MyApp) getApplicationContext();
                    uid.setStringValue(result);
                    Intent myintent = new Intent(v.getContext(), UserMap.class);
                    startActivityForResult(myintent, 0);
                }
            }
}