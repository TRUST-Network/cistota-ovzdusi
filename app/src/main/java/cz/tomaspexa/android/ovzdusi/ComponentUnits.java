package cz.tomaspexa.android.ovzdusi;

/**
 * Created by Admin on 19.10.2017.
 */

public class ComponentUnits {
    private String code;
    private String name;
    private String unit;

    public ComponentUnits ( String code, String name, String unit ) {
        this.code = code;
        this.name = name;
        this.unit = unit;

    }

    public String getCode () {
        return code;
    }
    public String getName () {
        return name;
    }
    public String getUnit () {
        return unit;
    }

    @Override
    public String toString() {
        return code + " " + name + " " + unit ;
    }
}
