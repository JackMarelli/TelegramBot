/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paci
 */
public class myFileManager {

    private File file;

    public myFileManager(File file) throws FileNotFoundException {
        this.file = file;
    }

    public myFileManager(String path) throws FileNotFoundException {
        this.file = new File(path);
    }

    public File getFile() {
        return file;
    }
    
    public boolean exists(){
        return file.exists();
    }
    
    public void createFile() throws IOException{
        file.createNewFile();
    }

    public synchronized void write(String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(s);
        bw.newLine();
        bw.close();
    }

    public synchronized void write(String[] s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < s.length; i++) {
            bw.write(s[i]);
            bw.newLine();
        }
        bw.close();
    }

    public synchronized void append(String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.append(s);
        bw.newLine();
        bw.close();
    }

    public String[] read() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        List<String> lineList = new ArrayList();
        while ((line = br.readLine()) != null) {
            lineList.add(line);
        }
        String[] lines = new String[lineList.size()];
        return lineList.toArray(lines);
    }

    public static String getCSV(String... data) {
        String csv = "";
        for (int i = 0; i < data.length; i++) {
            csv += data[i];
            if (i != data.length - 1) {
                csv += ";";
            }
        }
        return csv;
    }
}