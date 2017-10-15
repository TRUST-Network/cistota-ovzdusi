package cz.tomaspexa.android.ovzdusi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cz.tomaspexa.android.ovzdusi.Json.d;

/**
 * Created by pexik on 10.10.17.
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


        int index = getArguments().getInt(INDEX, 0);
        String code = getArguments().getString(CODE);
        List<Map<String,?>> stanice = d.vypisStaniceHash(code);

        String[] nazvyAtributu = {"name","code"};
        int [] idAtributu = {R.id.name,R.id.code};
        System.out.println(stanice);
        SimpleAdapter adapter = new SimpleAdapter(getContext(), stanice,R.layout.stanice_list,nazvyAtributu,idAtributu);
        setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener( new ListView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> a, View v, int pozice, long l) {
                HashMap o = (HashMap) a.getItemAtPosition(pozice);
                System.out.println(o.get("code"));

               // Toast.makeText(getBaseContext(),"klik" + pozice , Toast.LENGTH_LONG).show();

               Intent i = new Intent(getContext(),DetailActivity.class);
                i.putExtra(DataListFragment.INDEX, pozice);
                i.putExtra(DataListFragment.CODE,(String)o.get("code") );
                startActivity(i);


            }
        });

    }
}