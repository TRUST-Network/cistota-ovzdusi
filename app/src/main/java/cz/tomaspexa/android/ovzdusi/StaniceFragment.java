package cz.tomaspexa.android.ovzdusi;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.style.BackgroundColorSpan;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cz.tomaspexa.android.ovzdusi.Json.d;

/**
 * Created by pexik on 10.10.17.
 *
 * Zobrazí seznam stanic v regionu
 */

public class StaniceFragment extends ListFragment {
    public static final String INDEX = "index";
    public static final String CODE = "code";


    public static StaniceFragment newInstance(int index, String code) {
        StaniceFragment f = new StaniceFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        args.putString( CODE,code);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
        View header = getActivity().getLayoutInflater().inflate(R.layout.stanice_header, null);
        TextView header_text = (TextView) header.findViewById(R.id.region_header);
        ListView listView = getListView();
        listView.addHeaderView(header);

        int index = getArguments().getInt(INDEX, 0);
        String code = getArguments().getString(CODE);
        String region = d.vypisRegionString(code);
        final List<Map<String,?>> stanice = d.vypisStaniceHash(code);
        System.out.println("[debug] Stanice fragment - nazev regionu " + d.vypisRegionString(code));
        header_text.setText("test");

        // int color = Color.parseColor("#"+d.getLegendColor(d.vypisStanice()));
       // NO2.setBackgroundColor(color);
        // názvy jednotlivých položek
        String[] nazvyAtributu = {"name","ix"};
        int [] idAtributu = {R.id.name,R.id.classif};
        System.out.println("[debug] Stanice fragment " + stanice);
        final SimpleAdapter adapter = new SimpleAdapter(getContext(), stanice,R.layout.stanice_list,nazvyAtributu,idAtributu){
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                // obarvi LL podle barvy indexu znecisteni
                View view = super.getView(position,convertView,parent);
                LinearLayout tv = (LinearLayout) view.findViewById(R.id.llStanice);
                tv.setBackgroundColor(Color.parseColor(stanice.get(position).get("color").toString()));
                return view;
            }
        };
        setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener( new ListView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> a, View v, int pozice, long l) {
                HashMap o = (HashMap) a.getItemAtPosition(pozice);
                System.out.println("[debug] Stanice fragment f click" + o.get("code"));





               Intent i = new Intent(getContext(),DetailActivity.class);
                i.putExtra(DataListFragment.INDEX, pozice);
                i.putExtra(DataListFragment.CODE,(String)o.get("code") );
                startActivity(i);


            }
        });

    }
}