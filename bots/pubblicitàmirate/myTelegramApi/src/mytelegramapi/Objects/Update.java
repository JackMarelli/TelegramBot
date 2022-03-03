/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi.Objects;

/**
 *
 * @author Giacomo
 */
public class Update {

    //general
    int update_id;
    int message_id;
    int date;
    String text;

    //from
    int from_id;
    boolean is_bot;
    String from_first_name;
    String from_last_name;
    String language_code;

    //chat
    int chat_id;
    String chat_first_name;
    String chat_last_name;
    String chat_type;

    //entities
    int offset;
    int length;
    String entities_type;

    public Update() {

    }

    public int getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public boolean isIs_bot() {
        return is_bot;
    }

    public void setIs_bot(boolean is_bot) {
        this.is_bot = is_bot;
    }

    public String getFrom_first_name() {
        return from_first_name;
    }

    public void setFrom_first_name(String from_first_name) {
        this.from_first_name = from_first_name;
    }

    public String getFrom_last_name() {
        return from_last_name;
    }

    public void setFrom_last_name(String from_last_name) {
        this.from_last_name = from_last_name;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_first_name() {
        return chat_first_name;
    }

    public void setChat_first_name(String chat_first_name) {
        this.chat_first_name = chat_first_name;
    }

    public String getChat_last_name() {
        return chat_last_name;
    }

    public void setChat_last_name(String chat_last_name) {
        this.chat_last_name = chat_last_name;
    }

    public String getChat_type() {
        return chat_type;
    }

    public void setChat_type(String chat_type) {
        this.chat_type = chat_type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int lenght) {
        this.length = lenght;
    }

    public String getEntities_type() {
        return entities_type;
    }

    public void setEntities_type(String entities_type) {
        this.entities_type = entities_type;
    }
    
    public String toString() {
        String s = "update_id: " + update_id +
                "\nmessage_id: " + message_id +
                "\n(from)id: " + from_id +
                "\n(from)first_name: " + from_first_name +
                "\n(from)last_name: " + from_last_name +
                "\n(from)language_code: " + language_code +
                "\n(chat)id: " + chat_id +
                "\n(chat)first_name: " + chat_first_name +
                "\n(chat)last_name: " + chat_last_name +
                "\n(chat)type: " + chat_type +
                "\ndate: " + date +
                "\ntext: " + text +
                "\n(entities)offset: " + offset +
                "\n(entities)length: " + length +            
                "\n(entities)type: " + entities_type;

        
        return s;
    }
}
