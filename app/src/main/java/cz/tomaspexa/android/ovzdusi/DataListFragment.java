package cz.tomaspexa.android.ovzdusi;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * Created by pexik on 13.10.17.
 */

public class DataListFragment extends FragmentActivity {
    private boolean mDualPane;


    public void onRulerSelected(int index) {
        if (mDualPane) { // Dvousloupcový layout
            StaniceFragment f = StaniceFragment.newInstance(index);

            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.detail, f);
            // Voláním FragmentTransaction.addToBackStack dosáhneme toho,
            // že při stisknutí tlačítka zpět se Fragment vymění s tím,
            // co v R.id.detail bylo předtím (jiný DetailFragment nebo nic).
            ft.addToBackStack(null);
            ft.commit();
        } else { // Jednosloupcový layout
            Intent i = new Intent(this, Stanice_aktivita.class);
            i.putExtra(Stanice_aktivita.INDEX, index);
            startActivity(i);
        }
    }
}
