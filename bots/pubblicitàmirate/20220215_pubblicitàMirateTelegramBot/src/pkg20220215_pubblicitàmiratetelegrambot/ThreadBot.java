package pkg20220215_pubblicitĂ miratetelegrambot;

import OSM.OSMManager;
import OSM.Place;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import mytelegramapi.Objects.Update;
import mytelegramapi.Objects.User;
import mytelegramapi.Objects.UserList;
import mytelegramapi.TelegramBotManager;
import mytelegramapi.jsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import pkg20220215_pubblicitĂ miratetelegrambot.Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Giacomo
 */
public class ThreadBot extends Thread {

    long cycle;

    public ThreadBot() {
        cycle = 0;
    }

    @Override
    public void run() {
        String token = "5275943838:AAGcv3Ma63vSiY4-r-q1qS-qdwnHrewlWII";
        TelegramBotManager tbm = new TelegramBotManager(token);
        jsonParser jp = new jsonParser();

        UserList ul = null;
        try {
            ul = new UserList();
            GUI gui = new GUI(ul, tbm);
            System.out.println(ul.toString());
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
        while (true) {
            System.out.println("\nThreadBot CYCLE N. " + cycle);
            try {
                String jsonString = "";
                if (tbm.getOffset() != 0) {
                    jsonString = tbm.getUpdatesWithOffset();
                } else {
                    jsonString = tbm.getUpdates();
                }

                JSONObject updatesObj = new JSONObject(jsonString);
                JSONArray arr = updatesObj.getJSONArray("result");
                //System.out.println(arr.toString());

                //userlist toString
                //System.out.println(ul.toString());
                //sleep & cycle increment
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadBot.class.getName()).log(Level.SEVERE, null, ex);
                }

                cycle++;

                //stampo solo ultimo update (se ci sono update)
                if (arr.length() > 0) {
                    JSONObject obj = arr.getJSONObject(arr.length() - 1);
                    Update update = jp.parseUpdate(obj);
                    tbm.setOffset(update.getUpdate_id() + 1);
                    //System.out.println(update.toString());

                    if (update.isCommand()) {
                        String citta = update.getCitta();

                        //invio messaggio a chi mi ha mandato l'update
                        OSMManager osm = new OSMManager();
                        try {
                            tbm.sendMessage(update.getChat_id(), "Ricevuto :D");

                            //prendo il primo posto e aggiungo o aggiorno l'utente
                            Place p = osm.getPlace(citta);
                            User u = new User(p, update.getFrom_id());
                            int findUserResult = ul.findUser(u);
                            if (findUserResult == -1) {
                                ul.add(u);
                            } else {
                                ul.updateUser(findUserResult, u);
                            }
                            ul.updateFile();
                        } catch (ParserConfigurationException | SAXException | IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        System.out.println("Non Ă¨ un comando");
                    }
                } else {
                    System.out.println("Nessun update nelle ultime 24 ore :)");
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadBot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
