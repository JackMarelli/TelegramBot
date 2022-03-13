/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20220215_pubblicitàmiratetelegrambot;

import OSM.OSMManager;
import OSM.Place;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import mytelegramapi.*;
import mytelegramapi.Objects.Update;
import mytelegramapi.Objects.User;
import mytelegramapi.Objects.UserList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

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
        TelegramBotManager rm = new TelegramBotManager(token);
        jsonParser jp = new jsonParser();

        String jsonString = rm.request("getUpdates");
        JSONObject updatesObj = new JSONObject(jsonString);

        JSONArray arr = updatesObj.getJSONArray("result");

        UserList ul = null;
        try {
            ul = new UserList();
            System.out.println("Creata UserList");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //stampo tutti gli update
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
            JSONObject obj = arr.getJSONObject(arr.length() - 1);
            Update update = jp.parseUpdate(obj);
            System.out.println(update.toString());

            if (update.isCommand()) {
                String citta = update.getCitta();

                //invio messaggio a chi mi ha mandato l'update
                rm.sendMessage(update.getChat_id(), "Ricevuto :D");
                OSMManager osm = new OSMManager();
                try {
                    Place p = osm.getPlace(citta);
                    User u = new User(p, Integer.toString(update.getFrom_id()));
                    ul.add(u);
                    System.out.println("utente" + Integer.toString(update.getFrom_id()) + "aggiunto");
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("Non è un comando");
            }
        } else {
            System.out.println("Nessun update nelle ultime 24 ore :)");
        }
    }
}
