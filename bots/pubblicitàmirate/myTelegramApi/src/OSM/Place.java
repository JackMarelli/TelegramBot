/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OSM;

/**
 *
 * @author JMatter
 */
public class Place {

    private String id;
    private double lat;
    private double lon;

    public Place() {
    }

    public Place(String id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return ("Place ID: " + id + "\nLAT: " + lat + "\nLON: " + lon);
    }

    public String toCSV() {
        return (id + ";" + lat + ";" + lon);
    }

    //range in km
    public boolean isInRange(Place p, double range) {
        double theta = p.getLon() - lon;
        double dist = Math.sin(Math.toRadians(p.getLat())) * Math.sin(Math.toRadians(lat)) + Math.cos(Math.toRadians(p.getLat())) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.853155; //convert to km
        System.out.println("[Place - isInRange] DIST: " + dist);
        return (dist <= range);
    }
}
