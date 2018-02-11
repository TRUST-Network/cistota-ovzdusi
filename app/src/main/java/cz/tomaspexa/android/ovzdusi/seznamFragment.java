package cz.tomaspexa.android.ovzdusi;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pexik on 10.10.17.
 */

public class seznamFragment extends ListFragment{
    private OnRulerSelectedListener mOnRulerSelectedListener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context ctx = getActivity();
        //setListAdapter(new ArrayAdapter<String>(ctx, R.layout.name, names));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Donutíme kontejnerovou Activitu implementovat naše rozhraní
        try {
            mOnRulerSelectedListener = (OnRulerSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRulerSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Chytře se zbavíme zodpovědnosti za vybrání
        mOnRulerSelectedListener.onRulerSelected(position);
    }

    public interface OnRulerSelectedListener {
        public void onRulerSelected(int index);
    }
}
