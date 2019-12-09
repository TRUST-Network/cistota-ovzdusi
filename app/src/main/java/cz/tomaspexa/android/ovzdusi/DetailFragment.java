package cz.tomaspexa.android.ovzdusi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btn;

    public static DetailFragment newInstance(int index, String code) {
        DetailFragment f = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        args.putString( CODE,code);
        f.setArguments(args);
        return f;
    }
    protected void refresh (View r){

        //new Hlavni_aktivita.HttpAsyncTask().execute(sURL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {

        //int index = getArguments().getInt(INDEX, 0);
        final String code = getArguments().getString(CODE);


        String stanice = d.getStationName(code);
        String info = d.getInfoName();


        HashMap <String,Map> component = d.vypisStanici(code);
        StringBuilder sb = new StringBuilder();
        sb.append(stanice);
        //sb.append(component);

        final View v = inflater.inflate(R.layout.stanice_detail, container, false);

        // kliknuti na refresh
        btn = (Button) v.findViewById(R.id.refresh);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.refresh:
                        Toast.makeText(getContext(),"refresh", Toast.LENGTH_LONG).show();
                        //System.out.println("klik na refresh");
                        Intent i = new Intent(getContext(),Hlavni_aktivita.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        i.putExtra(CODE,code);
                        startActivity(i);
                        break;
                }
            }
        };
        btn.setOnClickListener(listener);





        TextView name = (TextView) v.findViewById(R.id.name);
        TextView time = (TextView) v.findViewById(R.id.time);
        LinearLayout NO2 =  (LinearLayout)v.findViewById(R.id.NO2);
        TextView NO2Name = (TextView) v.findViewById(R.id.NO2Name);
        TextView NO2Desc = (TextView) v.findViewById(R.id.NO2Desc);
        TextView NO2text = (TextView) v.findViewById(R.id.NO2text);
        LinearLayout O3 =  (LinearLayout)v.findViewById(R.id.O3);
        TextView O3Name = (TextView) v.findViewById(R.id.O3Name);
        TextView O3Desc = (TextView) v.findViewById(R.id.O3Desc);
        TextView O3text = (TextView) v.findViewById(R.id.O3text);
        LinearLayout SO2 =  (LinearLayout)v.findViewById(R.id.SO2);
        TextView SO2Name = (TextView) v.findViewById(R.id.SO2Name);
        TextView SO2Desc = (TextView) v.findViewById(R.id.SO2Desc);
        TextView SO2text = (TextView) v.findViewById(R.id.SO2text);
        LinearLayout CO =  (LinearLayout)v.findViewById(R.id.CO);
        TextView COName = (TextView) v.findViewById(R.id.COName);
        TextView CODesc = (TextView) v.findViewById(R.id.CODesc);
        TextView COtext = (TextView) v.findViewById(R.id.COtext);
        LinearLayout PM2_5 =  (LinearLayout)v.findViewById(R.id.PM2_5);
        TextView PM2_5Name = (TextView) v.findViewById(R.id.PM2_5Name);
        TextView PM2_5Desc = (TextView) v.findViewById(R.id.PM2_5Desc);
        TextView PM2_5text = (TextView) v.findViewById(R.id.PM2_5text);
        LinearLayout PM10 =  (LinearLayout)v.findViewById(R.id.PM10);
        TextView PM10Name = (TextView) v.findViewById(R.id.PM10Name);
        TextView PM10Desc = (TextView) v.findViewById(R.id.PM10Desc);
        TextView PM10text = (TextView) v.findViewById(R.id.PM10text);
        LinearLayout PM1024h =  (LinearLayout)v.findViewById(R.id.PM1024h);
        TextView PM1024hname = (TextView) v.findViewById(R.id.PM1024hname);
        TextView PM1024hDesc = (TextView) v.findViewById(R.id.PM1024hDesc);
        TextView PM1024htext = (TextView) v.findViewById(R.id.PM1024htext);
        TextView PM1024hInt = (TextView) v.findViewById(R.id.PM1024hInt);


        name.setText(sb);
        time.setText(info);


        for (Map.Entry<String,Map> i : component.entrySet()){

            System.out.println(d.getUnitName(i.getValue().get("code").toString()) + " "+ i.getValue().get("val") +" " + d.getUnitUnit(i.getValue().get("code").toString())+
                    " " + d.getLegendDesc(i.getValue().get("ix").toString()) + d.getLegendColor(i.getValue().get("ix").toString()));

            String text = i.getValue().get("val") +" " + d.getUnitUnit(i.getValue().get("code").toString());
            String UnitName =  d.getUnitName(i.getValue().get("code").toString());
            String UnitDesc =  d.getLegendFlagsDesc(i.getValue().get("flags").toString());
           // String Int = i.getValue().get("ïnterval");
            int color = Color.parseColor("#"+d.getLegendFlagsColor(i.getValue().get("flags").toString()));

            switch (i.getValue().get("code").toString()){
                case "NO2":
                    NO2Name.setText(UnitName);
                    NO2text.setText(text);
                    NO2Desc.setText(UnitDesc);
                    NO2.setBackgroundColor(color);
                    break;
                case "O3":
                    O3Name.setText(UnitName);
                    O3text.setText(text);
                    O3Desc.setText(UnitDesc);
                    O3.setBackgroundColor(color);
                    break;
                case "SO2":
                    SO2Name.setText(UnitName);
                    SO2text.setText(text);
                    SO2Desc.setText(UnitDesc);
                    SO2.setBackgroundColor(color);
                    break;
                case "CO":
                    COName.setText(UnitName);
                    COtext.setText(text);
                    CODesc.setText(UnitDesc);
                    CO.setBackgroundColor(color);
                    break;
                case "PM2_5":
                    PM2_5Name.setText(UnitName);
                    PM2_5text.setText(text);
                    PM2_5Desc.setText(UnitDesc);
                    PM2_5.setBackgroundColor(color);
                    break;
                case "PM10":
                    if ( i.getValue().get("interval").toString().equals("1h")) {
                        PM10Name.setText(UnitName);
                        PM10text.setText(text);
                        PM10Desc.setText(UnitDesc);
                        PM10.setBackgroundColor(color);
                    } else {
                        PM1024hInt.setText("24h");
                        PM1024hname.setText(UnitName);
                        PM1024htext.setText(text);
                        PM1024hDesc.setText(UnitDesc);
                        PM1024h.setBackgroundColor(color);
                    }
                    break;
            }

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
