package cz.tomaspexa.android.ovzdusi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Hlavni_aktivita extends AppCompatActivity {
    static String sURL = "http://portal.chmi.cz/files/portal/docs/uoco/web_generator/aqindex_cze.json";
    static String sFirma = "";
    static String sDenniMinimum = "";
    static String sDenniMaximum = "";
    static String sZmena = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlavni_aktivita);
        new NactiUdajeAsyncTask().execute();
    }

    private class NactiUdajeAsyncTask extends AsyncTask<String, String, String>
    {
        protected String doInBackground(String... arg0)
        {
            DefaultHttpClient clientHTTP =
                    new DefaultHttpClient(new BasicHttpParams());

            HttpPost httppost = new HttpPost(sURL);
            httppost.setHeader("Content-type", "application/json");
            InputStream inputStream = null;

            try {
                HttpResponse response = clientHTTP.execute(httppost);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),8);
                StringBuilder sb = new StringBuilder();
                String radek = null;
            }
        }

    }
}
