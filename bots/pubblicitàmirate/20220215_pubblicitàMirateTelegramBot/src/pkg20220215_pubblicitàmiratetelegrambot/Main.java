/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20220215_pubblicitàmiratetelegrambot;
import mytelegramapi.*;
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

        
        
        //String json = rm.request("getUpdates");
        //System.out.println(json);
        //System.out.println(jp.parseUpdates(json));
        
        rm.sendMessage("578041481", "sciaobelo");
    }
}
