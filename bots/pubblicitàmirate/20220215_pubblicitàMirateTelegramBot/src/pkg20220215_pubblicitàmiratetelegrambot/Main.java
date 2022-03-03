/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20220215_pubblicitàmiratetelegrambot;

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

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            System.out.println("\n\nUPDATE N: " + i);
            Update update = jp.parseUpdate(obj);
            System.out.println(update.toString());
        }

    }
}
