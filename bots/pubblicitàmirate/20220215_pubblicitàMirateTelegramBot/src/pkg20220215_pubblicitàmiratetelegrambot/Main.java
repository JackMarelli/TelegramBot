/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20220215_pubblicitàmiratetelegrambot;

import OSM.OSMManager;
import mytelegramapi.*;
import mytelegramapi.Objects.Update;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Giacomo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String token = "5275943838:AAGcv3Ma63vSiY4-r-q1qS-qdwnHrewlWII";
        requestManager rm = new requestManager(token);
        jsonParser jp = new jsonParser();

        String jsonString = rm.request("getUpdates");
        JSONObject updatesObj = new JSONObject(jsonString);

        JSONArray arr = updatesObj.getJSONArray("result");

        /*
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            System.out.println("\n\nUPDATE N: " + i);
            Update update = jp.parseUpdate(obj);
            System.out.println(update.toString());
        }
         */
        //stampo solo ultimo update (se ci sono update)
        if (arr.length() > 0) {
            JSONObject obj = arr.getJSONObject(arr.length()-1);
            Update update = jp.parseUpdate(obj);
            System.out.println(update.toString());
            
            if (update.isCommand()) {
                System.out.println(update.getCitta());
                
                String toAdd = update.getChat_id() + ";" + update.getChat_first_name()+ ";" +update.getChat_last_name()+ "\n"; //da aggiungere coordinate da OSM (TODO: OSM Manager)
                //invio messaggio a chi mi ha mandato l'update
                //rm.sendMessage(update.getChat_id(), "Ricevuto+:D");
                OSMManager osm = new OSMManager();
                
            }
            else {
                System.out.println("Non è un comando");
            }
        } else {
            System.out.println("Nessun Update :)");
        }
    }
}
