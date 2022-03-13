/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi.Objects;

import OSM.Place;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import mytelegramapi.fileManager;

/**
 *
 * @author JMatter
 */
public class UserList {

    List<User> userList;
    fileManager fm;
    String filename;

    public UserList() throws IOException {
        filename = "users.csv";
        fileManager fm = new fileManager(filename, true);
        readFromFile();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void readFromFile() throws IOException {
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";"); 
            Place p = new Place(data[1],Double.parseDouble(data[2]), Double.parseDouble(data[3]));
            User u = new User(p, data[0]);
            userList.add(u);
        }
        csvReader.close();
    }
    
    public void add(User u) {
        userList.add(u);
    }
}
