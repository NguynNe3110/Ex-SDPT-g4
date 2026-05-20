/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package card;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class Card extends JFrame {

    public Card(){
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        
        CardLayout c = new CardLayout();
        JPanel p = new JPanel(c);
        this.add(p);
        
        JPanel p1 = new JPanel();
        p1.add(new JLabel("aloalo1"));
        JPanel p2 = new JPanel();
        p2.add(new JLabel("lololo2"));
        JPanel p3 = new JPanel();
        p3.add(new JLabel("eeeeeeee3"));
        p.add(p1,"p1");
        p.add(p2,"p2");
        p.add(p3,"p3");
        
        JButton b1 = new JButton("Chuyen card tiep");
        b1.addActionListener((e) -> {
            c.next(p);
        });
        JButton b2 = new JButton("Lui card");
        b2.addActionListener((e) -> {
            c.previous(p);
        });
        this.add(b1);
        this.add(b2);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Card c = new Card();
    }
    
}
