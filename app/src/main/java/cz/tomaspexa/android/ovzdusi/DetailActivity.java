package cz.tomaspexa.android.ovzdusi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.HashMap;

import static cz.tomaspexa.android.ovzdusi.Json.d;

/**
 * Created by pexik on 15.10.17.
 */

public class DetailActivity extends FragmentActivity {
    public static final String INDEX = "index";
    public static final String CODE = "code";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stanice_activity);

        Intent i = getIntent();
        int index = i.getIntExtra(INDEX, 0);
        String code = i.getStringExtra(CODE);

        //HashMap o = (HashMap) a.getItemAtPosition(pozice);


        DetailFragment f = DetailFragment.newInstance(index,code);

        // Přidá fragment do View s id detail
        getSupportFragmentManager().beginTransaction().add(R.id.detail, f).commit();
    }
}
