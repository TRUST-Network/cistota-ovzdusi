/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tomaspexa.android.ovzdusi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tom
 */
public class Json {
        public static boolean hloubka = false;
        private String skupina;
        private Object region = "";
        private Object station = "";
        public static Databaze d;

        
        public void setDatabaze (Databaze d) {
            this.d = d;
        }
        
    public void Json(String data, String skupina) throws JSONException {
        this.skupina = skupina;
        // oznameni
        System.out.println(":test class TEST skupina: "+ skupina );
        // hierarchical data in a flattened list
       // String data = "";
        Iterator<?> keys_iter;
         String key ;
         
         JSONObject object = new JSONObject(data);
        keys_iter = object.keys();


        while (keys_iter.hasNext())
        {
            key =  (String) keys_iter.next();
           Object value = object.get(key);
           if ( value instanceof JSONArray) {
                // It's an array
             //   System.out.println(key + " / root path array");
                JSONArray p;
               p = object.getJSONArray(key);
                String text = cyklusA (p, key);
                //interventionJsonArray = (JSONArray)intervention;
            } 
           if ( value instanceof JSONObject ) {
                // na zacatku nejcasteji neni object
              //  System.out.println(value + " / root path object");
                JSONObject o;
               o = object.getJSONObject(key);
                String text = cyklusO (o,"");
                //interventionJsonArray = (JSONArray)intervention;
            } 

        }
       // ArrayList<Region> regiony = d.vypisRegiony();
       // ArrayList<Station> stanice = d.vypisStanice();
       
         
 

       // treeManager.getData(data[0]).putValue("MPG", 38);
       // treeManager.getData(data[1]).putValue("MPG", 28);

       // Gson gson = new Gson();

       // System.out.println(gson.toJson(treeManager));
       
       //System.out.println(treeManager);
    }
        /*
        @parent - nazev pole
        */
        public  String cyklusA ( JSONArray data, String parent ) throws JSONException {
            
         //JSONArray object = new JSONArray(data);

        for (int i=0; i<data.length(); i++) 
        {
           Object value = data.get(i);

            //System.out.println( parent );
        if ( value instanceof JSONObject ) {
            // It's an array
          //  System.out.println(value); vypise
            JSONObject o;
            o = data.getJSONObject(i);
            String text = cyklusO (o, parent);
            // System.out.println(text);
 //interventionJsonArray = (JSONArray)intervention;
            } 

           //System.out.println(object.length());
        }
        return "";
       }

    private String cyklusO(JSONObject o, String parent) throws JSONException {

        Iterator<?> keys_iter;
        String key ;
        keys_iter = o.keys();

        Component c = null;
        if ( parent.equals("Regions")) {
           // System.out.println(o.get("Code")+ " - " +o.get("Name") );
                
                d.pridejRegion(o.get("Code").toString(),o.get("Name").toString());
                this.region = o.get("Code");
        }
        if ( parent.equals("Stations")) {
           // System.out.println(o.get("Code")+ " - " +o.get("Name") + " - " + o.get("Classif") );
                 
                d.pridejStation(o.get("Code").toString(),o.get("Name").toString(),o.get("Classif").toString(),o.get("Ix").toString(),region.toString());
                this.station = o.get("Code");
        }        
         if ( parent.equals("Components")) {   
            Object codeC = o.get("Code");
            c = d.pridejComponent(codeC.toString(), station.toString() );
            
         }
         
        while (keys_iter.hasNext())
        {
            key = (String) keys_iter.next();
           Object value = o.get(key);
           if ( value instanceof JSONArray ) {
                // It's an array
                // System.out.println(key + " - "  );
               //  if ( key.equals(skupina)) {
                    // System.out.println(key );
                    JSONArray p;
                    p = o.getJSONArray(key);
                    String text = cyklusA (p, key);
                   
              //   }
            } 
            if ( parent.equals("Components")) {   
                Object codeC = o.get("Code");
               if (key.equals("Val")){ c.setVal(value.toString());}
               if (key.equals("Int")){ c.setInt(value.toString());}
               if (key.equals("Ix")){ c.setIx(value.toString());}
            }             
            if ( parent.equals("skupina") && !(value instanceof JSONArray)) { // jiz nema dalsi potomky
                System.out.println(key + " - " + value + " / child path, ");

                //return (key + " - " + value + " / child path, " + hloubka);
            }
           //System.out.println(object.length());
        }
        
        if ( parent.equals("Components")) { 
            d.ulozComponent(c);       
         }
        return "";
         
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
          
    }

    
}
