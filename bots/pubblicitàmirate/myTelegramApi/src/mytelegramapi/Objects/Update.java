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
    long update_id;
    long message_id;
    long date;
    String text;

    //from
    String from_id;
    boolean is_bot;
    String from_first_name;

    //chat
    long chat_id;
    String chat_first_name;
    String chat_type;

    //entities
    int offset;
    int length;
    String entities_type;

    //utils
    String citta;

    public Update() {

    }

    public long getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public long getDate() {
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

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
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
    public long getChat_id() {
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

    //ritorna true solo se il comando è /citta
    public boolean isCommand() {
        if (text.substring(0, 1).equals("/")) {
            if (text.substring(0, text.indexOf(' ')).equals("/citta")) {
                return true;
            }
        }
        return false;
    }

    public String getCitta() {
        if (this.isCommand()) {
            int pos1 = text.indexOf(' ') + 1;
            int pos2 = text.length();

            String citta = text.substring(pos1, pos2);
            this.citta = citta;
            return citta;
        }
        return "NA";
    }

    public String toString() {
        String s = "update_id: " + update_id
                + "\nmessage_id: " + message_id
                + "\n(from)id: " + from_id
                + "\n(from)first_name: " + from_first_name
                + "\n(chat)id: " + chat_id
                + "\n(chat)first_name: " + chat_first_name
                + "\n(chat)type: " + chat_type
                + "\ndate: " + date
                + "\ntext: " + text
                + "\n(entities)offset: " + offset
                + "\n(entities)length: " + length
                + "\n(entities)type: " + entities_type;

        return s;
    }
}
