/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class bai3 extends JFrame {
    private final JCheckBox cb2= new JCheckBox("In đậm");
    private final JCheckBox cb1 = new JCheckBox("In nghiêng");
    private final JTextField tf = new JTextField(25);
    
    public bai3(){
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        this.add(tf);
        
        this.add(cb1);
        this.add(cb2);
        
        ItemListener il = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int style = Font.PLAIN;  
                if(cb1.isSelected())style += Font.ITALIC;
                if(cb2.isSelected())style += Font.BOLD;
                tf.setFont(new Font("alo",style,12));   
            }
        };
        
        cb1.addItemListener(il);    
        cb2.addItemListener(il);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        bai3 b = new bai3();
    }
}
