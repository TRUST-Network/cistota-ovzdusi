/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tomaspexa.android.ovzdusi;

/**
 *
 * @author Tom
 * componenty u stabic taktez detail stanice
 */
public class Component {
    private int index; // index poradi v poli
    private String code;
    private String interval = "null";;
    private String value = "null";
    private String ix = "null";
    private String flags = "hovno";
    private String stanice;
    
    public Component ( int index, String code, String stanice ) {
        this.index = index;
        this.code = code;
        this.stanice = stanice;

                
    }
    public void setIndex ( int index) {
        this.index = index;
    }
    public void setInt ( String interval) {
        this.interval = interval;
    }
    public void setVal ( String value) {
        this.value = value;
    }    
    public void setIx ( String ix) {
        this.ix = ix;
    }
    public void setFlags ( String flags) {this.flags = flags; }
    public String getIndex () {
        return Integer.toString(index);
    }
    public String getCode () {
        return code;
    }
    public String getInt () {
        return interval;
    }   
    public String getVal () {
        return value;
    }     
    public String getIx () {
        return ix;
    }
    public String getFlags () {
        return flags;
    }
    public String getStanice () {
        return stanice;
    }        
    @Override
    public String toString() {
        return index + " " + code + " " + interval + " " + value + " " + ix + " " + stanice;
    }  
    
}
