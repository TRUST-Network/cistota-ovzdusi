/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tomaspexa.android.ovzdusi;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cz.tomaspexa.android.ovzdusi.Json.d;

/**
 *
 * @author Tom
 *
 * Metody databaze
 */
public class Databaze {
    private ArrayList<Informace> info;
    private ArrayList<Region> regiony;
    private ArrayList<Station> stanice;
    private ArrayList<Component> component;
    private ArrayList<ComponentUnits> unitA;
    private ArrayList<Legend> legend;
    private ArrayList<LegendFlags> legendFlags;
    
    public Databaze () {
        info = new ArrayList<>();
        regiony = new ArrayList<>();
        stanice = new ArrayList<>();
        component = new ArrayList<>();
        unitA = new ArrayList<>();
        legend = new ArrayList<>();
        legendFlags = new ArrayList<>();
        
    }
    public void pridejInformace (String name) {
        info.add (new Informace ( name));
    }
    public void pridejRegion (String code, String name) {
        regiony.add (new Region ( code, name));
    }
    public void pridejStation (String code, String name, String classif,String ix,String region) {
        stanice.add (new Station ( code, name,classif,ix,region));
    }    
    public Component pridejComponent (int index,String code, String stanice) {
        return new Component ( index, code,stanice);
    }
    public void pridejComponentUnits (String code, String name, String unit) {
        unitA.add ( new ComponentUnits ( code,name,unit));
    }
    public void pridejLegend (String ix, String color, String colorText, String description) {
        legend.add ( new Legend ( ix, color, colorText, description));
    }
    public void pridejLegendFlags (String flags, String color, String colorText, String description) {
        legendFlags.add ( new LegendFlags ( flags, color, colorText, description));
    }
    public void ulozComponent (Component o) {
        component.add(o);
    }

    public String getStationName (String code) {
        for (Station z : stanice){
            if (z.getCode().equals(code)) {
               return z.getName();
            }
        }
        return "";
    }
    public String getInfoName () {

        return info.get(0).getName();
    }
    // vlozi regiony do hash mapy
    public List<Map<String,?>> vypisRegionyHash() {

            List<Map<String, ?>> nalezene = new ArrayList<Map<String, ?>>();
            for (Region z : regiony) {
                    System.out.println(z.toString());
                    Map<String,String> polozkyMap = new HashMap<String, String>();
                    polozkyMap.put("name",z.getName());
                    polozkyMap.put("code",z.getCode());
                    nalezene.add(polozkyMap);
        }
        return nalezene;
    }  
    public ArrayList<Station> vypisStanice() {
        ArrayList<Station> nalezene = new ArrayList<>();
        for (Station z : stanice) {
                 System.out.println(z.toString());
                   nalezene.add(z);
        }
        return nalezene;
    } 
    public ArrayList<Station> vypisStanice(String code) {
        ArrayList<Station> nalezene = new ArrayList<>();
        for (Station z : stanice) {
                if (z.getRegion().equals(code)){
                 System.out.println(z.toString());
                   nalezene.add(z);
                }
        }
        return nalezene;
    }
    // vypise stanice dle code regionu
    public List<Map<String,?>>  vypisStaniceHash(String code) {
        List<Map<String, ?>> nalezene = new ArrayList<Map<String, ?>>();
        for (Station z : stanice) {
            Map<String, String> polozkyMap = new HashMap<String, String>();
            if (z.getRegion().equals(code)) {
                polozkyMap.put("name", z.getName());
                polozkyMap.put("code", z.getCode());
                polozkyMap.put("ix", z.getIx());
                polozkyMap.put("color", "#"+d.getLegendColor(z.getIx()));
                nalezene.add(polozkyMap);
            }
        }
        return nalezene;
    }
    // vypise komponenty stanice
    public HashMap<String,Map> vypisStanici( String code) {
        //ArrayList<Station> nalezene = new ArrayList<>();
        HashMap<String,Map> nalezene = new HashMap<>();
        for (Station z : stanice) {
            if (z.getCode().equals(code)) {
                System.out.println(z.toString());

                for (Component c : component ){
                    Map<String, String> words = new HashMap<>();
                    if (c.getStanice().equals(z.getCode())) {
                        //System.out.println(c.toString());
                        words.put("index", c.getIndex());
                        words.put("code", c.getCode());
                        words.put("val",c.getVal ());
                        words.put("interval",c.getInt ());
                        words.put("ix",c.getIx ());
                        nalezene.put(c.getIndex(),words);
                    }
                }

                //nalezene.add(z);
            }

        }
        /*for (Map.Entry<String,Map> i : nalezene.entrySet()){
            System.out.println(this.getUnitName(i.getValue().get("code").toString()) + " "+ i.getValue().get("val") +" " + this.getUnitUnit(i.getValue().get("code").toString())+
                    " " + this.getLegendDesc(i.getValue().get("ix").toString()));
        }*/

        return nalezene;
    }
    public String getUnitName(String code) {
        for (ComponentUnits z :  unitA) {

            if (z.getCode().equals(code)) {
                //System.out.println(z.toString());
                return z.getName().toString();
            }
        }
        return "null";
    }

    public String getUnitUnit(String code) {
        for (ComponentUnits z :  unitA) {
            if (z.getCode().equals(code)) {
                //System.out.println(z.toString());
                return z.getUnit().toString();
            }
        }
        return "null";
    }
    public String getLegendDesc(String ix) {
        for (Legend z :  legend) {
            //System.out.println(z.getIx().toString());
            if (z.getIx().equals(ix)) {

                return z.getDesc();
            }
        }
        return "";
    }
    public String getLegendColor(String ix) {
        for (Legend z :  legend) {
            //System.out.println(z.getIx().toString());
            if (z.getIx().equals(ix)) {

                return z.getColor().toString();
            }
        }
        return "80FFFFFF";
    }
    public ArrayList<Region>  vypisRegion( String code) {
        ArrayList<Region> nalezene = new ArrayList<>();
        for (Region z : regiony) {
            if (z.getCode().equals(code)) {
                System.out.println(z.toString());
                nalezene.add(z);
            }

        }
        return nalezene;
    }
    @Override
    public String toString() {
        return  "nevim";
    }
}
