package cz.tomaspexa.android.ovzdusi;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Admin on 3.10.2017.
 */

public class Stanice_aktivita extends FragmentActivity {
    public static final String INDEX = "index";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stanice_activity);

        Intent i = getIntent();
        int index = i.getIntExtra(INDEX, 0);

        StaniceFragment f = StaniceFragment.newInstance(index);

        // Přidá fragment do View s id detail
        getSupportFragmentManager().beginTransaction().add(R.id.detail, f).commit();
    }
}
