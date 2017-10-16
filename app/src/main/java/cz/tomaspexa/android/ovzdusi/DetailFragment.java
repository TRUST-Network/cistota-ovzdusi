package cz.tomaspexa.android.ovzdusi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cz.tomaspexa.android.ovzdusi.Json.d;

/*
/
/ Vypise dataily merici stanice
//
 */
public class DetailFragment extends Fragment {
    public static final String INDEX = "index";
    public static final String CODE = "code";


    public static DetailFragment newInstance(int index, String code) {
        DetailFragment f = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        args.putString( CODE,code);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {

        //int index = getArguments().getInt(INDEX, 0);
        String code = getArguments().getString(CODE);


        String stanice = d.getStationName(code);
        List<Map<String,?>> component = d.vypisComponentHash(code);
        StringBuilder sb = new StringBuilder();
        sb.append(stanice);
        sb.append(component);
        System.out.println(sb);

        View v = inflater.inflate(R.layout.stanice_detail, container, false);

        TextView name = (TextView) v.findViewById(R.id.name);
        name.setText(stanice);
        /*String[] nazvyAtributu = {"val","code"};
        int [] idAtributu = {R.id.val,R.id.code};
        List<Map<String,?>>  componenty = d.vypisComponentHash(code);

        SimpleAdapter adapter = new SimpleAdapter(getContext(), componenty,R.layout.stanice_detail,nazvyAtributu,idAtributu);
        setListAdapter(adapter);
        */
        TextView value = (TextView) v.findViewById(R.id.val);
        value.setText(sb);
        return v;
    }
}
