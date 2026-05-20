/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package borderlayout;

import java.awt.BorderLayout;
import javax.swing.*;


/**
 *
 * @author PhanAnh
 */
public class Border extends JFrame {

    public Border() {
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JLabel tb_1 = new JLabel("aloalo1");
        JLabel tb_2 = new JLabel("lololo2");
        JLabel tb_3 = new JLabel("eeeeeeee3");
        
        this.add(tb_1, BorderLayout.WEST);
        this.add(tb_2, BorderLayout.CENTER);
        this.add(tb_3, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Border b = new Border();
    }
    
}
