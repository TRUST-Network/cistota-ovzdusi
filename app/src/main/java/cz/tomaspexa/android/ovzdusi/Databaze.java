/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tomaspexa.android.ovzdusi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tom
 */
public class Databaze {
    private ArrayList<Region> regiony;
    private ArrayList<Station> stanice;
    private ArrayList<Component> component;
    
    public Databaze () {
        regiony = new ArrayList<>();
        stanice = new ArrayList<>();
        component = new ArrayList<>();
        
    }
    public void pridejRegion (String code, String name) {
        regiony.add (new Region ( code, name));
    }
    public void pridejStation (String code, String name, String classif,String ix,String region) {
        stanice.add (new Station ( code, name,classif,ix,region));
    }    
    public Component pridejComponent (String code, String stanice) {
        return new Component ( code,stanice);
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
                nalezene.add(polozkyMap);
            }
        }
        return nalezene;
    }
    public ArrayList<Station>  vypisStanici( String code) {
        ArrayList<Station> nalezene = new ArrayList<>();
        for (Station z : stanice) {
            if (z.getCode().equals(code)) {                
                System.out.println(z.toString());
                for (Component c : component ){
                   if (c.getStanice().equals(z.getCode())) {
                       // vypise komponenty stanice
                       // System.out.println(c.toString());
                       //
                   }
                }
                   nalezene.add(z); 
            }
                
        }
        return nalezene;
    }  
    public List<Map<String,?>>   vypisComponentHash( String code) {
        List<Map<String, ?>> nalezene = new ArrayList<Map<String, ?>>();
        for (Component z : component) {
            Map<String, String> polozkyMap = new HashMap<String, String>();
            if (z.getStanice().equals(code)) {
               // System.out.println(z.toString());
                polozkyMap.put("val", z.getVal());
                polozkyMap.put("code", z.getCode());
                polozkyMap.put("interval", z.getInt());
                nalezene.add(polozkyMap);
            }
                
        }
        return nalezene;
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
