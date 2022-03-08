/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author JMatter
 */
public class fileManager {

    String filename;

    File file;
    FileWriter fw;
    BufferedWriter bw;
    BufferedReader br;

    boolean append;

    public fileManager() {
    }

    public fileManager(String filename, boolean append) throws IOException {
        this.append = append;
        this.filename = filename;
        file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        fw = new FileWriter(file, append);
        bw = new BufferedWriter(fw);
    }

    public void add(String toAdd, boolean acapo) throws IOException {
        if (acapo) {
            bw.write(toAdd + "\n");
        } else {
            bw.write(toAdd);
        }
    }

    public String getString() throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(file.getAbsolutePath()));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
    
    public File getFile() {
        return file;
    }
}
