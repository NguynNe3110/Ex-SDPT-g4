/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grid;


import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author PhanAnh
 */
public class Grid extends JFrame {

    public Grid() {
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2,2));
        JLabel tb_1 = new JLabel("aloalo1");
        JLabel tb_2 = new JLabel("lololo2");
        JLabel tb_3 = new JLabel("eeeeeeee3");
        
        this.add(tb_1);
        this.add(tb_2);
        this.add(tb_3);
        
        this.setVisible(true);
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grid g = new Grid();
    }
    
}
