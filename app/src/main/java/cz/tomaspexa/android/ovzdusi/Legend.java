package cz.tomaspexa.android.ovzdusi;

/**
 * Created by Admin on 19.10.2017.
 */

public class Legend {
    private String ix;
    private String color;
    private String colorText;
    private String description;

    public Legend ( String ix, String color, String colorText, String description ) {
        this.ix = ix;
        this.color = color;
        this.colorText = colorText;
        this.description = description;
    }

    public String getIx () {
        return ix;
    }
    public String getColor () {
        return color;
    }
    public String getColorText () {
        return colorText;
    }
    public String getDesc () {
        return description;
    }

    @Override
    public String toString() {
        return ix + " " + color + " " + colorText + " " + description ;
    }
}
