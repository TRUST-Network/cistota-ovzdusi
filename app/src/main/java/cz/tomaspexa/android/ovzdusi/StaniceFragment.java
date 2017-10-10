package cz.tomaspexa.android.ovzdusi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pexik on 10.10.17.
 */

public class StaniceFragment extends Fragment {
    public static final String INDEX = "index";

    protected static String[] details = new String[] {
            "Nějaký text...",
            "Nějaký text...",
            "Nějaký text...",
            "Nějaký text...",
            "Nějaký text...",
            "Karel IV.\nNarozen 1316 v Praze, zemřel 1378 tamtéž.\n\n11. český král (1346 - 1378), "
                    + "lombardský král (1355), "
                    + "římský král (1346 - 1355), římský císař (1355 - 1378), arelatský král (1365), markrabě "
                    + "moravský (1333 - 1349), hrabě lucemburský (1346 - 1353).\n\nLucemburk.\n\nKřtěný Václav.\n\n"
                    + "Syn Elišky Přemyslovny a Jana Lucemburského.\n\nManželky - Blanka z Valois, Anna Falcká, "
                    + "Anna Svídnická, Alžběta Pomořanská.\n\nZaložil - Nové Město pražské, Karlovu univerzitu.\n\n"
                    + "Nechal postavit - Karlův most, Hladovou zeď.\n\nNechal vyrobit - Svatováclavskou korunu.\n\n"
                    + "Vita Caroli - vlastní životopis psaný latinsky.",
            "Nějaký text...",
            "Nějaký text...",
            "Nějaký text...",
            "Nějaký text..."
    };

    public static StaniceFragment newInstance(int index) {
        StaniceFragment f = new StaniceFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_hlavni_aktivita, container, false);

        int index = getArguments().getInt(INDEX, 0);

        TextView tv = (TextView) v.findViewById(R.id.etResponse);
        tv.setText(details[index]);

        return v;
    }
}