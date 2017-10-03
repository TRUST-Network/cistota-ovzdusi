package cz.tomaspexa.android.ovzdusi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 3.10.2017.
 */

public class Stanice_aktivita extends Fragment {
    public static final String VYBER = "vyber";
    public static Stanice_aktivita newInstance (int index) {
        Stanice_aktivita f = new Stanice_aktivita();
        Bundle argumennty = new Bundle();
        argumennty.putInt(VYBER,index);
        f.setArguments(argumennty);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.stanice_list,container,false);
        int index = getArguments().getInt(VYBER,0);
        TextView tv = (TextView) vw.findViewById(R.id.code);
        tv.setText("informace");
        return vw;
    }
}
