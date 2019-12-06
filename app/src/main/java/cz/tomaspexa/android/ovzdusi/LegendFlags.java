package cz.tomaspexa.android.ovzdusi;

public class LegendFlags {
    private String flags;
    private String color;
    private String colorText;
    private String description;

    public LegendFlags ( String flags, String color, String colorText, String description ) {
        this.flags = flags;
        this.color = color;
        this.colorText = colorText;
        this.description = description;
    }

    public String getFlags () {
        return flags;
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
        return flags + " " + color + " " + colorText + " " + description ;
    }
}
