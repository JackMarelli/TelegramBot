/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import org.json.*;

/**
 *
 * @author Giacomo
 */
public class jsonParser {

    public jsonParser() {
    }

    public String parseUpdates(String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        boolean ok = obj.getBoolean("ok");
        JSONArray result = obj.getJSONArray("result");
        return ok + "\n" + result.toString();
    }

    public String parseMe(String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        boolean ok = obj.getBoolean("ok");
        JSONObject result = obj.getJSONObject("result");
        return ok + "\n" + result.toString();
    }

    public String parseUpdate(JSONObject obj) {

        int update_id = obj.getInt("update_id");
        JSONObject message = obj.getJSONObject("message");
        int message_id = message.getInt("message_id");

        /*
        //parse from
        JSONObject from = message.getJSONObject("from");
        int from_id = from.getInt("id");
        boolean from_is_bot = from.getBoolean("is_bot");
        String from_first_name = from.getString("first_name");
        String from_last_name = from.getString("last_name");
        String language_code = from.getString("language_code");

        //parse chat
        JSONObject chat = message.getJSONObject("chat");
        int chat_id = from.getInt("id");
        String chat_first_name = from.getString("first_name");
        String chat_last_name = from.getString("last_name");
        String type = from.getString("type");
         */
        //other
        int date = obj.getInt("date");
        String text = getMessageText(obj);

        //TODO: leggere "entities" eventualmente
        //check if key exists in obj obj.has("entities");
        
        return text;
    }

    public String getMessageText(JSONObject message) {
        return message.getString("text");
    }

}
