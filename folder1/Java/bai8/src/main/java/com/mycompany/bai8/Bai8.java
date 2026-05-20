/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai8;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PhanAnh
 */
public class Bai8 extends JFrame {
    
    private JLabel lb_tieuDe = new JLabel("Danh sách sinh viên");
    private JList<String> jList = new JList<>();
    private JLabel lb_masv = new JLabel("Mã sinh viên");
    private JLabel lb_hoTen = new JLabel("Họ và tên");
    private JLabel lb_queQuan = new JLabel("Quê quán");
    private JTextField tf_masv = new JTextField(25);
    private JTextField tf_hoTen = new JTextField(25);
    private JTextField tf_queQuan = new JTextField(25);
    private JButton btn_add = new JButton("Add");
    private JButton btn_remove = new JButton("Remove");
    private JButton btn_update = new JButton("Update");
    private JButton btn_clear = new JButton("Clear");
    
    public Bai8 (){
        this.setTitle("Quản lí sinh viên");
        this.setSize(600,400);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        
        this.add(lb_tieuDe,BorderLayout.NORTH);
        this.add(jList,BorderLayout.WEST);
        TranDl();
        ClickJlist();
        
        JPanel jPanel= new JPanel();
        jPanel.add(lb_masv);
        jPanel.add(tf_masv);
        jPanel.add(lb_hoTen);
        jPanel.add(tf_hoTen);
        jPanel.add(lb_queQuan);
        jPanel.add(tf_queQuan);
        jPanel.add(btn_add);
        jPanel.add(btn_remove);
        jPanel.add(btn_update);
        jPanel.add(btn_clear);
        this.add(jPanel,BorderLayout.CENTER);
        clickButton();
        
        this.setVisible(true);
    }
    
    private void TranDl(){
        List<SinhVien> list = SinhVien.getAllSinhVien();
        List<String> listName = new ArrayList<>();
        for (SinhVien sinhVien : list) {
            listName.add(sinhVien.getHoTen());
        }
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(listName);
        jList.setModel(defaultListModel);
    }
    
    private void ClickJlist(){
        jList.addListSelectionListener((e) -> {
            int i = jList.getSelectedIndex();
            List<SinhVien> list = SinhVien.getAllSinhVien();
            tf_masv.setText(list.get(i).getMasv());
            tf_hoTen.setText(list.get(i).getHoTen());
            tf_queQuan.setText(list.get(i).getQueQuan());
        });
    }
    
    private void clickButton(){
        btn_clear.addActionListener((e) -> {
            tf_masv.setText("");
            tf_hoTen.setText("");
            tf_queQuan.setText("");
        });
        
        btn_add.addActionListener((e) -> {
            String masv = tf_masv.getText();
            String hoTen = tf_hoTen.getText();
            String queQuan = tf_queQuan.getText();
            if(masv.isBlank()||hoTen.isBlank()||queQuan.isBlank()){
                JOptionPane.showMessageDialog(null,"Không được bỏ trống");
                return;
            }
            String sql = "Insert sinhvien values ('" + masv + "',N'" + hoTen + "',N'" + queQuan + "')";
            MyConnection connection = new MyConnection();
            int row = connection.GhiDl(sql);
            if(row > 0){ 
                JOptionPane.showMessageDialog(null,"Thêm thành công");
                TranDl();
            }
            else JOptionPane.showMessageDialog(null,"Thêm thất bại");
        });
        
        btn_remove.addActionListener((e) -> {
            String masv = tf_masv.getText();
            if(masv.isBlank()){ 
                JOptionPane.showMessageDialog(null,"Phải điền mã sinh viên");
                return;
            }
            String sql = "Delete from sinhvien where masv = '" + masv + "'";
            MyConnection connection = new MyConnection();
            int row = connection.GhiDl(sql);
            if(row > 0){ 
                JOptionPane.showMessageDialog(null,"Xóa thành công");
                TranDl();
            }
            else JOptionPane.showMessageDialog(null,"Xóa thất bại");
        });
        
        btn_update.addActionListener((e) -> {
            String masv = tf_masv.getText();
            String hoTen = tf_hoTen.getText();
            String queQuan = tf_queQuan.getText();
            if(masv.isBlank()||hoTen.isBlank()||queQuan.isBlank()){
                JOptionPane.showMessageDialog(null,"Không được bỏ trống");
                return;
            }
            String sql = "Update sinhvien set hoten = N'" + hoTen + "', queQuan = N'" + queQuan + "' where masv = '" + masv + "'";
            MyConnection connection = new MyConnection();
            int row = connection.GhiDl(sql);
            if(row > 0){ 
                JOptionPane.showMessageDialog(null,"Sửa thành công");
                TranDl();
            }
            else JOptionPane.showMessageDialog(null,"Sửa thất bại");
        });
    }
    
    public static void main(String[] args) {
        Bai8 b = new Bai8();
    }
}
