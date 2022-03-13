/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import mytelegramapi.Objects.Update;
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

    public Update parseUpdate(JSONObject obj) {

        //update
        Update update = new Update();
        update.setUpdate_id(obj.getInt("update_id"));

        //message
        JSONObject message = obj.getJSONObject("message");
        update.setMessage_id(message.getInt("message_id"));

        // from
        JSONObject from = message.getJSONObject("from");
        update.setFrom_id(from.getInt("id"));
        update.setIs_bot(from.getBoolean("is_bot"));
        update.setFrom_first_name(from.getString("first_name"));
        update.setFrom_id(from.getInt("id"));

        // chat
        JSONObject chat = message.getJSONObject("chat");
        update.setChat_id(chat.getInt("id"));
        update.setChat_first_name(chat.getString("first_name"));
        update.setChat_type(chat.getString("type"));

        //dated
        update.setDate(message.getInt("date"));

        //text
        update.setText(message.getString("text"));

        //TODO: leggere "entities" eventualmente
        if (message.has("entities")) {
            JSONObject entitiesObj = message.getJSONArray("entities").getJSONObject(0);
            update.setOffset(entitiesObj.getInt("offset"));
            update.setLength(entitiesObj.getInt("length"));
            update.setEntities_type(entitiesObj.getString("type"));
        }

        return update;
    }

}
