/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class bai2 extends JFrame {

    private final JLabel lb_F = new JLabel("Nhập độ F:");
    private final JTextField tf_F = new JTextField(25);
    private final JLabel lb_C = new JLabel("Độ C tương ứng:");
    private final JTextField tf_C = new JTextField(25);
    private final JButton bt_Tinh = new JButton("Tính");
    private final JButton bt_TiepTuc = new JButton("Tiếp tục");
    private final JButton bt_Thoat = new JButton("Thoát");
    public bai2() {
        this.setTitle("Convert from F to C");
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        this.add(lb_F);
        this.add(tf_F);
        this.add(lb_C);
        this.add(tf_C);
        this.add(bt_Tinh);
        this.add(bt_TiepTuc);
        this.add(bt_Thoat);
        
        bt_Tinh.addActionListener((e) -> {
            String do_F = tf_F.getText();
            if(do_F.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
            }
            else{
                try {
                Float F = Float.parseFloat(do_F);
                Float C = (F-32)/1.8f;
                tf_C.setText(C.toString());
                } catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
                }
            }
        });
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        bai2 b2 = new bai2();
    }
}
