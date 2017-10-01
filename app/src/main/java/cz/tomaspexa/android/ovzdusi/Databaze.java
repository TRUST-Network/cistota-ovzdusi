/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tomaspexa.android.ovzdusi;

import java.util.ArrayList;

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
    public ArrayList<Region> vypisRegiony() {
        ArrayList<Region> nalezene = new ArrayList<>();
        for (Region z : regiony) {
                 System.out.println(z.toString());
                   nalezene.add(z);
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
    public ArrayList<Station>  vypisStanici( String code) {
        ArrayList<Station> nalezene = new ArrayList<>();
        for (Station z : stanice) {
            if (z.getCode().equals(code)) {                
                System.out.println(z.toString());
                for (Component c : component ){
                   if (c.getStanice().equals(z.getCode())) {                
                        System.out.println(c.toString()); 
                   }
                }
                   nalezene.add(z); 
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
}
