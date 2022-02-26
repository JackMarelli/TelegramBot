/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.json.*;

/**
 *
 * @author Giacomo
 */
public class jsonParser {

    public jsonParser() {
    }
    
    public Boolean parseUpdates(String json) {
        JSONObject obj = new JSONObject(json);
        boolean ok = obj.getBoolean("ok");
        return ok;
    }
    
}
