/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package box;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class Box extends JFrame {
    
    public Box(){
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        
        JPanel p = new JPanel();
        this.add(p);
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        
        p.add(new JLabel("aaaaaaaaaaaaaaaaaaaa"));
        p.add(new JLabel("bbbbbbbbbbbbbbbbbbbb"));
        p.add(new JLabel("ccccccccccccccccccccccccccccccccc"));
        
        JPanel p1 = new JPanel();
        this.add(p1);
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        p1.add(new JLabel("aaaaaaaaaaaaaaaaaaaa"));
        p1.add(new JLabel("bbbbbbbbbbbbbbbbbbbb"));
        p1.add(new JLabel("ccccccccccccccccccccccccccccccccc"));
        
        this.setVisible(true);
    }

    
    public static void main(String[] args) {
        Box b = new Box();
    }
    
}
