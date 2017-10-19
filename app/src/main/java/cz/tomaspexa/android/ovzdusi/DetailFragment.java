package cz.tomaspexa.android.ovzdusi;

import android.graphics.Color;
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
import java.util.HashMap;
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
        HashMap <String,Map> component = d.vypisStanici(code);
       // Map <String,String> componentO3 = d.vypisStanici(code).get("O3");
        Map <String,String> componentSO2 = d.vypisStanici(code).get("SO2");
        StringBuilder sb = new StringBuilder();
        sb.append(stanice);
        //sb.append(component);
        System.out.println(sb);

        View v = inflater.inflate(R.layout.stanice_detail, container, false);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView NO2 = (TextView) v.findViewById(R.id.NO2);
        TextView O3 = (TextView) v.findViewById(R.id.O3);
        TextView SO2 = (TextView) v.findViewById(R.id.SO2);
        TextView CO = (TextView) v.findViewById(R.id.CO);
        TextView PM2_5 = (TextView) v.findViewById(R.id.PM2_5);
        TextView PM10 = (TextView) v.findViewById(R.id.PM10);

        name.setText(sb);


        NO2.setText(d.getUnitName(component.get("NO2").get("code").toString()) + " " + component.get("NO2").get("val").toString() + " "
                + d.getUnitUnit(component.get("NO2").get("code").toString()) + " " +d.getLegendDesc(component.get("NO2").get("ix").toString()));
        // Color	"9BD3AE"
        NO2.setBackgroundColor(Color.parseColor("#"+d.getLegendColor(component.get("NO2").get("ix").toString())));

        //O3.setText(componentO3.get("code").toString());

        SO2.setText(componentSO2.get("code").toString());

        for (Map.Entry<String,Map> i : component.entrySet()){
            System.out.println(d.getUnitName(i.getValue().get("code").toString()) + " "+ i.getValue().get("val") +" " + d.getUnitUnit(i.getValue().get("code").toString())+
                    " " + d.getLegendDesc(i.getValue().get("ix").toString()));
            //i.getValue().get("code").setText();
        }
        /*String[] nazvyAtributu = {"val","code"};
        int [] idAtributu = {R.id.val,R.id.code};
        List<Map<String,?>>  componenty = d.vypisComponentHash(code);

        SimpleAdapter adapter = new SimpleAdapter(getContext(), componenty,R.layout.stanice_detail,nazvyAtributu,idAtributu);
        setListAdapter(adapter);
        */
        //TextView value = (TextView) v.findViewById(R.id.val);
       // value.setText(component.get(i).get("val"));
        return v;
    }
}
