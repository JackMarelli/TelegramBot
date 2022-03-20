/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20220215_pubblicitàmiratetelegrambot;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mytelegramapi.Objects.UserList;

/**
 *
 * @author JMatter
 */
public class GUI extends javax.swing.JFrame {

    public UserList ul;

    public GUI() {
    }

    public GUI(UserList ul) {
        this.ul = ul;

        this.setLayout(null);

        JLabel l_citta = new JLabel("Città:");
        l_citta.setBounds(10, 10, 100, 30);
        this.add(l_citta);

        JTextField tf_luogo = new JTextField();
        tf_luogo.setBounds(110, 10, 200, 30);
        this.add(tf_luogo);
        
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

        JButton formGetUpdates = new JButton("Invia");
        formGetUpdates.setBounds(10, 190, 300, 30);
        this.add(formGetUpdates);
        
        //TODO: eventlistener bottone invio promozione asd
        
        this.setTitle("TelegramBot - Interfaccia inserimento promozioni");
        this.setSize(335, 265);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
