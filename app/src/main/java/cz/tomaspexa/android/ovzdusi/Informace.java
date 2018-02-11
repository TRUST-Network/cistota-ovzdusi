package cz.tomaspexa.android.ovzdusi;

/**
 * Created by Admin on 18.1.2018.
 */

public class Informace {
    private String code;
    private String name;

    public Informace (String name ) {
        this.code = code;
        this.name = name;

    }
    public String getCode () {
        return code;
    }
    public String getName () {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
