/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai9;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class Bai9 extends JFrame{
    
    private JMenuBar menu = new JMenuBar();
    private JMenu mn_file = new JMenu("File");
    private JMenuItem mi_luuDuLieu = new JMenuItem("Write data to disk");
    private JMenuItem mi_docDuLieu = new JMenuItem("Open data from disk");
    private JMenuItem mi_exit = new JMenuItem("Exit");
    
    private JLabel lb_chuDe = new JLabel("Quản lí sản phẩm");
    
    private JPanel pn_list = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
    private JList<String> listSanPham = new JList<>();
    private JButton btn_new_pn_list = new JButton("New");
    private JButton btn_update_pn_list = new JButton("Update");
    private JButton btn_remove_pn_list = new JButton("Remove");
    
    private JPanel pn_chiTiet = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
    private ScrollPane sp = new ScrollPane();
    private JTable tb_chiTietSanPham = new JTable();
    private JLabel lb_danhMuc = new JLabel("Category");
    private JComboBox<String> cb_danhMuc = new JComboBox<>();
    private JLabel lb_idSanPham = new JLabel("Product ID");
    private JTextField tf_idSanPham = new JTextField(25);
    private JLabel lb_tenSanPham = new JLabel("Product Name");
    private JTextField tf_tenSanPham = new JTextField(25);
    private JLabel lb_gia = new JLabel("Unit price");
    private JTextField tf_gia = new JTextField(25);
    private JLabel lb_soLuong = new JLabel("Quantity");
    private JTextField tf_soLuong = new JTextField(25);
    private JLabel lb_moTa = new JLabel("Description");
    private JTextArea tf_moTa = new JTextArea(3, 25);
    private JButton btn_new_pn_chiTiet = new JButton("New");
    private JButton btn_save_pn_chiTiet = new JButton("Save");
    private JButton btn_remove_pn_chiTiet = new JButton("Remove");
    
    public Bai9(){
        this.setTitle("Quản lí sản phẩm");
        this.setSize(600,400);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        
        mn_file.add(mi_luuDuLieu);
        mn_file.add(mi_docDuLieu);
        mn_file.add(mi_exit);
        menu.add(mn_file);
        this.setJMenuBar(menu);
        
        this.add(lb_chuDe,BorderLayout.NORTH);
        
        pn_list.add(listSanPham);
        pn_list.add(btn_new_pn_list);
        pn_list.add(btn_update_pn_list);
        pn_list.add(btn_remove_pn_list);
        this.add(pn_list,BorderLayout.WEST);
        
        sp.add(tb_chiTietSanPham);
        pn_chiTiet.add(sp);
        pn_chiTiet.add(lb_danhMuc);
        pn_chiTiet.add(cb_danhMuc);
        pn_chiTiet.add(lb_idSanPham);
        pn_chiTiet.add(tf_idSanPham);
        pn_chiTiet.add(lb_tenSanPham);
        pn_chiTiet.add(tf_tenSanPham);
        pn_chiTiet.add(lb_gia);
        pn_chiTiet.add(tf_gia);
        pn_chiTiet.add(lb_soLuong);
        pn_chiTiet.add(tf_soLuong);
        pn_chiTiet.add(lb_moTa);
        pn_chiTiet.add(tf_moTa);
        pn_chiTiet.add(btn_new_pn_chiTiet);
        pn_chiTiet.add(btn_save_pn_chiTiet);
        pn_chiTiet.add(btn_remove_pn_chiTiet);
        this.add(pn_chiTiet,BorderLayout.EAST);
        
        this.setVisible(true);
    }
    
    private void TranDl(){
        
    }
    
    public static void main(String[] args) {
        Bai9 b =new Bai9();
    }
}
