/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.Model.TaiKhoanModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * 
 * @author PhanAnh
 */
public class QLTaiKhoan extends JPanel {
    private JLabel lb_MaTaiKhoan = new JLabel("Mã tài khoản");
    private JLabel lb_TenTaiKhoan = new JLabel("Tên tài khoản");
    private JLabel lb_MatKhau = new JLabel("Mật khẩu");
    private JLabel lb_MaNhanVien = new JLabel("Mã nhân viên");
    private JLabel lb_ChucVu = new JLabel("Chức vụ");
    
    private JTextField tf_MaTaiKhoan = new JTextField(15);
    private JTextField tf_TenTaiKhoan = new JTextField(15);
    private JTextField tf_MatKhau = new JTextField(15);
    private JComboBox<String> cbo_ChucVu = new JComboBox<>();
    private JComboBox<String> cbo_MaNhanVien = new JComboBox<>();

    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Sua = new JButton("Sửa");
    private JButton btn_Xoa = new JButton("Xóa");
    private JButton btn_Huy = new JButton("Hủy");
    
    private JTable tb_TaiKhoan = new JTable();
    
    public QLTaiKhoan() {
        initFrame();
        initTable();
    }
    
    private void initFrame() {
        this.setLayout(new BorderLayout(10,10));
        
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_form.add(lb_MaTaiKhoan);
        jp_form.add(tf_MaTaiKhoan);
        tf_MaTaiKhoan.setEditable(false);
        
        jp_form.add(lb_TenTaiKhoan);
        jp_form.add(tf_TenTaiKhoan);

        jp_form.add(lb_MatKhau);
        jp_form.add(tf_MatKhau);

        jp_form.add(lb_ChucVu);
        jp_form.add(cbo_ChucVu);

        jp_form.add(lb_MaNhanVien);
        jp_form.add(cbo_MaNhanVien);
        this.add(jp_form, BorderLayout.NORTH);
        
        JPanel jp_btn = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_btn.add(btn_TimKiem);
        jp_btn.add(btn_Them);
        jp_btn.add(btn_Sua);
        jp_btn.add(btn_Xoa);
        jp_btn.add(btn_Huy);
        this.add(jp_btn,BorderLayout.CENTER);
        
        JScrollPane scp = new JScrollPane(tb_TaiKhoan);
        this.add(scp,BorderLayout.SOUTH);
    }
    
    private void initTable(){
        String[] columns = {"Mã Tài Khoản", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Chức vụ"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa tất cả ô
            }
        };
        List<TaiKhoanModel> taiKhoanModels = TaiKhoanModel.getAllTaiKhoan();
        for (TaiKhoanModel tk : taiKhoanModels) {
            dtm.addRow(new Object[]{
                tk.getMaTaiKhoan(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau(),
                tk.getMaNhanVien(),
                tk.getChucVu()
            });
        }
        tb_TaiKhoan.setModel(dtm);
    }
   
}
