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
public class Promozione {

    public Place place;
    public String desc;
    public double range;

    public Promozione(Place place, String desc, double range) {
        this.place = place;
        this.desc = desc;
        this.range = range;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }
}
