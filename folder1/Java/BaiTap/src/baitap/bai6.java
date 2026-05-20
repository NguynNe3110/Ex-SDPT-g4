/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class bai6 extends JFrame {
    private final JList<String> list1 = new JList<String>();
    private final JList<String> list2= new JList<String>();
    private final JScrollPane sp = new JScrollPane(list1);
    private final JButton bt_copy = new JButton("Copy");
    
    public bai6(){
        this.setSize(200,150);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(sp,BorderLayout.WEST);
        this.add(bt_copy,BorderLayout.CENTER);
        this.add(list2,BorderLayout.EAST);
        
        DefaultListModel<String> lm1 = new DefaultListModel<>();
        lm1.addElement("Black");
        lm1.addElement("Blue");
        lm1.addElement("Gray");
        lm1.addElement("Cyan");
        lm1.addElement("Dark");
        lm1.addElement("White");
        lm1.addElement("Dark Gray");
        lm1.addElement("Red");
        list1.setModel(lm1);
        
        click();
        
        this.setVisible(true);
    }
    
    private void click(){
        bt_copy.addActionListener((e) -> {
            List l = list1.getSelectedValuesList();
            DefaultListModel<String> lm3 = new DefaultListModel<>();
            lm3.addAll(l);
            list2.setModel(lm3);
        });
    }
    
    
    public static void main(String[] args) {
        bai6 b = new bai6();
    }
}
