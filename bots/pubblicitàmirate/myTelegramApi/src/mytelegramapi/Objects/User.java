/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi.Objects;

import OSM.Place;

/**
 *
 * @author JMatter
 */
public class User {
    
    Place place;
    String id;

    public User() {
    }

    public User(Place place, String chatId) {
        this.place = place;
        this.id = chatId;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getChatId() {
        return id;
    }

    public void setChatId(String chatId) {
        this.id = chatId;
    }
    
}
