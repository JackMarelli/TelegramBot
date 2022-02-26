/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 *
 * @author Giacomo
 */
public class requestManager {

    String token;
    
    public requestManager(String token) {
        this.token = token;
    }
    
    public String getUrlWithToken() {
        return "https://api.telegram.org/bot"+token+"/";
    }

    /**
     * PARAMETERS: method_name: name of the request (ex. "getUpdates") 
     * RETURN: jsonString: string containing response json, null: exeption
     */
    public String request(String method_name) {
        try {
            //get url with token, add request name 
            URL url = new URL(getUrlWithToken() + method_name);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            System.out.println(url.toString());
            
            switch (method_name) {
                    case "getUpdates":
            }
            
            

            //read response
            String jsonString = br.lines().collect(Collectors.joining());
            
            //close buffered reader, return json
            br.close();
            return jsonString;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public Boolean sendMessage(String message) {
        return false;
    }
}
