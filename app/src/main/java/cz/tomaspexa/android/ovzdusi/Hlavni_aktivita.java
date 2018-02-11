package cz.tomaspexa.android.ovzdusi;


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.json.JSONException;

        import android.app.ListActivity;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.util.ArrayMap;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.FrameLayout;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.support.v4.app.ListFragment;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.app.Activity;

        import static android.media.CamcorderProfile.get;


public class Hlavni_aktivita extends ListActivity {

    TextView etResponse;
    TextView tvIsConnected;
    static String sURL = "http://portal.chmi.cz/files/portal/docs/uoco/web_generator/aqindex_cze.json";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mDualPane = findViewById(R.id.detail) != null;

        //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

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



/* NAjit kde to bzlo pouzito
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
*/


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_SHORT).show();

            Json test = new Json();

            Databaze d = new Databaze();
            test.setDatabaze(d);
            try {
                test.Json(result,"Stations"); // natahne data
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // priradi atributy do hash mapy
            String[] nazvyAtributu = {"name"};
            int [] idAtributu = {R.id.name};
            List<Map<String,?>>  regiony = d.vypisRegionyHash();

            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), regiony,R.layout.regiony_list,nazvyAtributu,idAtributu);
            setListAdapter(adapter);
            ListView lv = getListView();
            lv.setOnItemClickListener( new ListView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> a, View v, int pozice, long l) {
                    HashMap o = (HashMap) a.getItemAtPosition(pozice);
                    System.out.println("Hlavni aktivita " + o.get("code"));

                    //Toast.makeText(getBaseContext(),"klik" + pozice , Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getBaseContext(),DataListFragment.class);
                    i.putExtra(DataListFragment.INDEX, pozice);
                    i.putExtra(DataListFragment.CODE,(String)o.get("code") );
                    startActivity(i);

                }
            });
        }

    }
}
