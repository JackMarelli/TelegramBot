/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20220215_pubblicitàmiratetelegrambot;

import OSM.OSMManager;
import OSM.Place;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import mytelegramapi.Objects.UserList;
import mytelegramapi.TelegramBotManager;
import org.xml.sax.SAXException;

/**
 *
 * @author JMatter
 */
public class GUI extends javax.swing.JFrame {

    public UserList ul;
    OSMManager osm;
    TelegramBotManager tbm;

    public GUI() {
    }

    public GUI(UserList ul, TelegramBotManager tbm) {
        osm = new OSMManager();
        this.tbm = tbm;
        this.ul = ul;
        this.setLayout(null);

        JLabel l_citta = new JLabel("Città:");
        l_citta.setBounds(10, 10, 100, 30);
        this.add(l_citta);

        JTextField tf_citta = new JTextField();
        tf_citta.setBounds(110, 10, 200, 30);
        this.add(tf_citta);
        
        JLabel l_range = new JLabel("Range (km):");
        l_range.setBounds(10, 40, 200, 30);
        this.add(l_range);

        JTextField tf_range = new JTextField();
        tf_range.setBounds(110, 40, 200, 30);
        this.add(tf_range);

        JLabel l_desc = new JLabel("Descrizione: ");
        l_desc.setBounds(10, 80, 200, 30);
        this.add(l_desc);
        
        JTextArea ta_desc = new JTextArea();
        ta_desc.setBounds(110, 80, 200, 100);
        this.add(ta_desc);

        JButton btn_invia = new JButton("Invia");
        btn_invia.setBounds(10, 190, 300, 30);
        this.add(btn_invia);
        
        btn_invia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("button clicked");
                if ((!(tf_citta.getText().trim()).equals("")) && (!(tf_range.getText().trim()).equals(""))) {
                    String message = "NUOVA PROMOZIONE NELLA TUA ZONA\n" + ta_desc.getText();
                    
                    Place p;
                    try {
                        p = osm.getPlace(tf_citta.getText());
                    } catch (ParserConfigurationException | SAXException | IOException ex) {
                        p = new Place();
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    for (int i = 0; i < ul.getUserList().size(); i++) {
                        if (ul.getUserList().get(i).getPlace().isInRange(p, Double.valueOf(tf_range.getText()))) {
                            try {
                                tbm.sendMessage(Long.valueOf(ul.getUserList().get(i).getId()), message);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        });
        
        //TODO: eventlistener bottone invio promozione asd
        
        this.setTitle("TelegramBot - Interfaccia inserimento promozioni");
        this.setSize(335, 265);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
