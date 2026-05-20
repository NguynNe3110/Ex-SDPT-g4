/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author PhanAnh
 */
public class bai4 extends JFrame {
    private final JLabel lb_FullName = new JLabel("Full name:");
    private final JTextField tf_fullname = new JTextField(25);
    private final JPanel p = new JPanel();
    private final JPanel p1 = new JPanel();
    private final JPanel p2= new JPanel();
    private final JPanel p3 = new JPanel();
    private final ButtonGroup bg = new ButtonGroup();
    private final JRadioButton rb1 = new JRadioButton("Mr.");
    private final JRadioButton rb2 = new JRadioButton("Miss");
    private final JLabel lb_Result = new JLabel("Result:");
    private final JTextField tf_result = new JTextField(25);
    private final JButton bt_wel = new JButton("Welcome");
    private final JButton bt_cle = new JButton("Clear");
    private final JButton bt_can = new JButton("Cancel");
    public bai4(){
        this.setSize(450,300);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        p.add(lb_FullName);
        p.add(tf_fullname);
        TitledBorder tb = BorderFactory.createTitledBorder("Gener");
        p1.setBorder(tb);
        bg.add(rb1);
        bg.add(rb2);
        p1.add(rb1);
        rb1.setSelected(true);
        p1.add(rb2);
        p2.add(lb_Result);
        p2.add(tf_result);
        p3.add(bt_wel);
        p3.add(bt_cle);
        p3.add(bt_can);
        this.add(p);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        setClick();
        
        this.setVisible(true);
    }
    
    private void setClick(){
        bt_wel.addActionListener((e) -> {
            String fullname = tf_fullname.getText();
            if(rb1.isSelected()) tf_result.setText("Welcome Mr." + fullname);
            if(rb2.isSelected()) tf_result.setText("Welcome Miss " + fullname);
        });
        bt_cle.addActionListener((e) -> {
            tf_fullname.setText("");
            tf_result.setText("");
        });
    }
    
    public static void main(String[] args) {
        bai4 b = new bai4();
        
    }
}
