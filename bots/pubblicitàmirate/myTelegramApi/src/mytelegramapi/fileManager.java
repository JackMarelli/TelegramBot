/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytelegramapi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author JMatter
 */
public class fileManager {

    String filename;
 
    File file;
    FileWriter fw;
    BufferedWriter bw;

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

    public void addLine(String line, boolean acapo) throws IOException {
        if (acapo) {
            bw.write(line +"\n");
        } else {
            bw.write(line);
        }
    }
}
