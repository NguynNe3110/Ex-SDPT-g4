/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class bai5 extends JFrame {
    private final JLabel lb = new JLabel("Sunday");
    private final JList<String> list = new JList<String>();
    public bai5(){
        this.setSize(100,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        lb.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lb,BorderLayout.NORTH);
        DefaultListModel<String> lm = new DefaultListModel<>();
        lm.addElement("Sunday");
        lm.addElement("Monday");
        lm.addElement("Tuesday");
        lm.addElement("Wednesday");
        lm.addElement("Thusday");
        lm.addElement("Friday");
        lm.addElement("Saturday");
        list.setModel(lm);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(list,BorderLayout.CENTER);
        clickList();
        
        this.setVisible(true);
    }
    
    private void clickList(){
        list.addListSelectionListener((e) -> {
            String selected = list.getSelectedValue();
            lb.setText(selected);
        });
    }
    
    public static void main(String[] args) {
        bai5 b = new bai5();
    }
}
