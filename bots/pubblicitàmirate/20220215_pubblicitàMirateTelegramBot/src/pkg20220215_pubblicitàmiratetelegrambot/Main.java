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

       ThreadBot tb = new ThreadBot();
       tb.start();
    }
}

//struttura di una riga di "users.csv";
//[Place ID];[Lat];[Lon];[User Id]\n