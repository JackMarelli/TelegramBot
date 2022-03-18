/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi.Objects;

import OSM.Place;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JMatter
 */
public class UserList {

    List<User> userList;
    String filename;

    public UserList() throws IOException {
        userList = new ArrayList<User>();
        filename = "users.csv";
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
            Place p = new Place(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]));
            User u = new User(p, data[0]);

            userList.add(u);
        }
        csvReader.close();
        System.out.println("[UserList-readFromFile] LISTA CARICATA DA FILE");
    }

    public void updateFile() throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        String s = "";

        for (int i = 0; i < userList.size(); i++) {
            s += (userList.get(i).toCSV() + "\n");
        }

        bw.write(s);
        bw.flush();
        bw.close();
        System.out.println("[UserList-updateFile] FILE UPDATED");
    }

    public void add(User u) {
        userList.add(u);
        System.out.println("[UserList-add] USER ADDED");
    }

    public String toString() {
        String s = "USERLIST ATTUALE:\n";
        for (int i = 0; i < userList.size(); i++) {
            s += (userList.get(i).toString() + "\n");
        }
        return s;
    }
    
    //ritorna -1 se non trova l'id, altrimenti la posizione nella lista
    public int findUser(String id){
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    
    //ritorna -1 se non trova l'id, altrimenti la posizione nella lista
    public int findUser(User u){
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(u.getId())) {
                System.out.println("[UserList-findUser] USER FOUND AT INDEX: " + i + "\n");
                return i;
            }
        }
        System.out.println("[UserList-findUser] USER NOT FOUND");

        return -1;
    }
    
    //update di uno user in base alla posizione
    public void updateUser(int index, User u) {
        userList.set(index, u);
        System.out.println("[UserList-updateUser] USER UPDATED");
    }
}
