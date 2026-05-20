/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;


import com.mycompany.quan_li_sieu_thi.Model.TaiKhoanModel;
import com.mycompany.quan_li_sieu_thi.PhienTaiKhoan;
import java.awt.FlowLayout;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class DangNhap extends JFrame {
    private JLabel lb_TenTaiKhoan = new JLabel("Tên tài khoản");
    private JTextField tf_TenTaiKhoan = new JTextField(10);
    private JLabel lb_MatKhau = new JLabel("Mật khẩu");
    private JPasswordField pf_MatKhau = new JPasswordField(10);
    private JButton btn_DangNhap = new JButton("Đăng nhập");
    private JLabel lb_ThongBao = new JLabel();
    
    public DangNhap(){
        initFrame();
        
        btn_DangNhap.addActionListener((e) -> {
            String tentaikhoan = tf_TenTaiKhoan.getText();
            
            char[] mk = pf_MatKhau.getPassword();
            String matkhau = String.valueOf(mk);
            Arrays.fill(mk, '0');//xóa mật khẩu
            
            TaiKhoanModel taiKhoanModel = TaiKhoanModel.checkTaiKhoan(tentaikhoan, matkhau);
            if(taiKhoanModel != null){
                String tenTaiKhoan = taiKhoanModel.getTenTaiKhoan();
                String chucVu = taiKhoanModel.getChucVu();
                PhienTaiKhoan.setTenTaiKhoan(tenTaiKhoan);
                PhienTaiKhoan.setChucVu(chucVu);
                
                TrangChu tc = new TrangChu();
                this.dispose(); 
            }
            else lb_ThongBao.setText("Sai tài khoản, mật khẩu");
        });
        
        this.setVisible(true);
    }
    
    private void initFrame(){
        this.setTitle("Siêu thị");
        this.setSize(240,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        this.add(lb_TenTaiKhoan);
        this.add(tf_TenTaiKhoan);
        this.add(lb_MatKhau);
        this.add(pf_MatKhau);
        this.add(btn_DangNhap);
        this.add(lb_ThongBao);
    }
    
    public static void main(String[] args) {
        DangNhap dangNhap = new DangNhap();
    }
}
