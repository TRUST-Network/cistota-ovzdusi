/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tomaspexa.android.ovzdusi;

/**
 *
 * @author Tom
 */
public class Component {
    private String code;
    private String interval;
    private String value;
    private String ix;
    private String stanice;
    
    public Component ( String code, String stanice ) {
    this.code = code;
    this.stanice = stanice;
                
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
    public String getStanice () {
        return stanice;
    }        
    @Override
    public String toString() {
        return code + " " + interval + " " + value + " " + ix + " " + stanice;
    }  
    
}
