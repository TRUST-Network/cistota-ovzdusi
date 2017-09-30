package cz.tomaspexa.android.ovzdusi;


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.params.BasicHttpParams;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.JSONTokener;

        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.app.Activity;

        import static android.media.CamcorderProfile.get;


public class Hlavni_aktivita extends Activity {

    TextView etResponse;
    TextView tvIsConnected;
    static String sURL = "http://portal.chmi.cz/files/portal/docs/uoco/web_generator/aqindex_cze.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlavni_aktivita);

        // get reference to the views
        etResponse = (TextView) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute(sURL);
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
        String line = "";
        String result = "";
		StringBuilder sb = new StringBuilder();

        while((line = bufferedReader.readLine()) != null)
            sb.append( line + "\n" );

        String sJson = null;
		sJson = sb.toString ();
		inputStream.close();


        
        return sJson;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    public boolean ismyArray(int index)  {
        Object object = get(index);
        if (object instanceof JSONArray) {
            return true;
        } else {
            return false;
        }
    }
    public StringBuilder parse ( String result, String objekt, String item) {
        JSONObject jsonObject;
        List<String> allNames = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        //sb.append(objekt + " - " + item + "\n");
        sb.append("{\"" + item + "\":");
        try
        {

            jsonObject = new JSONObject(result);

            JSONArray cast = jsonObject.getJSONArray(objekt);


            for (int i=0; i<cast.length(); i++) {

                JSONObject index = cast.getJSONObject(i);

                if ( index.getJSONArray("Regions")instanceof JSONArray ) {
                    sb.append( i + " - array\n" );
                }
                else  {
					result = index.getString(item);
                    sb.append( result + "\n"  );
                }


            }
            sb.append(  "}"  ); // konec json
            try {
                JSONObject lesMots = jsonObject.getJSONObject("States");
                Iterator<?> keys = lesMots.keys();

                while(keys.hasNext()) {
                    String key = (String)keys.next();
                    if (lesMots.get(key) instanceof JSONObject ) {
                        JSONObject obj = (string) lesMots.get(key);
                        result = obj.getString(item);
                        sb.append( result + "\n"  );
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e +"",Toast.LENGTH_LONG).show();
            }

        }
        catch ( JSONException e ) {
            e.printStackTrace();
        }
        return sb;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

            StringBuilder text = parse(result,"Legend","Description");
            text.append(parse(result,"Legend","Color"));
            text.append(parse(result,"States","Name"));
            text.append(parse(result,"States","Regions"));

           //StringBuilder stations = parse(result,"Regions","Name");
           // text.append(parse(stations.toString(),"Regions","Stations"));

            //text.append(stations);
            etResponse.setText(text);
        }
    }
}
