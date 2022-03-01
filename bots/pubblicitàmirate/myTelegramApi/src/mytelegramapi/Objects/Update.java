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
    String update_id;
    String message_id;
    String date;
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
    int lenght;
    String entities_type;
    
    public Update() {
        
    }
    
    
}
