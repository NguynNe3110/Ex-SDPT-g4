/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.PhienTaiKhoan;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class TrangChu extends JFrame{
    private JToolBar toolBar = new JToolBar();
    private JPanel pannelMain = new JPanel(new FlowLayout());
    private JButton btn_QLMatHang = new JButton("Mặt hàng");
    private JButton btn_QLDanhMuc = new JButton("Danh Mục");
    private JButton btn_QLNhaCungCap = new JButton("Nhà cung cấp");
    private JButton btn_QLDonNhapHang = new JButton("Đơn nhập hàng");
    private JButton btn_QLHoaDon = new JButton("Hóa đơn");
    private JButton btn_QLThongKe = new JButton("Thống kê");
    private JButton btn_QLNhanVien = new JButton("Nhân viên");
    private JButton btn_QLBoPhan = new JButton("Bộ phận");
    private JButton btn_QLTaiKhoan = new JButton("Tài khoản");
    private JButton btn_DangXuat = new JButton("Đăng xuất");
    
    public TrangChu(){
        String chucVu = PhienTaiKhoan.getChucVu();
        initFrame(chucVu);
        initToolBar(chucVu);
        setClick();
        this.setVisible(true);
    }
    
    private void initFrame(String chucVu){
        this.setTitle("Chức vụ: " + chucVu);
        this.setSize(1200,600);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.add(toolBar);
        this.add(pannelMain,BorderLayout.CENTER);
    }
    
    private void initToolBar(String role){
        
        ImageIcon iconMatHang = new ImageIcon("src/main/java/pic/mathang.png");
        iconMatHang = resizeIcon(iconMatHang, 30, 30);
        btn_QLMatHang.setIcon(iconMatHang);
        
        ImageIcon iconDanhMuc = new ImageIcon("src/main/java/pic/danhmuc.png");
        iconDanhMuc = resizeIcon(iconDanhMuc, 30, 30);
        btn_QLDanhMuc.setIcon(iconDanhMuc);
        
        ImageIcon iconNhaCungCap = new ImageIcon("src/main/java/pic/nhacungcap.png");
        iconNhaCungCap = resizeIcon(iconNhaCungCap, 30, 30);
        btn_QLNhaCungCap.setIcon(iconNhaCungCap);
        
        ImageIcon iconDonNhapHang = new ImageIcon("src/main/java/pic/donnhaphang.jpg");
        iconDonNhapHang = resizeIcon(iconDonNhapHang, 30, 30);
        btn_QLDonNhapHang.setIcon(iconDonNhapHang);
        
        ImageIcon iconHoaDon = new ImageIcon("src/main/java/pic/hoadon.jpg");
        iconHoaDon = resizeIcon(iconHoaDon, 30, 30);
        btn_QLHoaDon.setIcon(iconHoaDon);
        
        ImageIcon iconThongKe = new ImageIcon("src/main/java/pic/thongke.jpg");
        iconThongKe = resizeIcon(iconThongKe, 30, 30);
        btn_QLThongKe.setIcon(iconThongKe);

        ImageIcon iconNhanVien = new ImageIcon("src/main/java/pic/nhanvien.jpg");
        iconNhanVien = resizeIcon(iconNhanVien, 30, 30);
        btn_QLNhanVien.setIcon(iconNhanVien);

        ImageIcon iconBoPhan = new ImageIcon("src/main/java/pic/bophan.jpg");
        iconBoPhan = resizeIcon(iconBoPhan, 30, 30);
        btn_QLBoPhan.setIcon(iconBoPhan);
        
        ImageIcon iconTaiKhoan = new ImageIcon("src/main/java/pic/taikhoan.jpg");
        iconTaiKhoan = resizeIcon(iconTaiKhoan, 30, 30);
        btn_QLTaiKhoan.setIcon(iconTaiKhoan);

        ImageIcon iconDangXuat = new ImageIcon("src/main/java/pic/dangxuat.jpg");
        iconDangXuat = resizeIcon(iconDangXuat, 30, 30);
        btn_DangXuat.setIcon(iconDangXuat);
        
        toolBar.add(btn_QLMatHang);
        toolBar.add(btn_QLDanhMuc);
        toolBar.add(btn_QLNhaCungCap);
        toolBar.add(btn_QLDonNhapHang);
        toolBar.add(btn_QLHoaDon);
        toolBar.add(btn_QLThongKe);
        toolBar.add(btn_QLNhanVien);
        toolBar.add(btn_QLBoPhan);
        toolBar.add(btn_QLTaiKhoan);
        toolBar.add(btn_DangXuat);
        
        toolBar.setFloatable(false);
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        this.add(toolBar,BorderLayout.NORTH);
    }
    
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }
    
    private void setClick(){
        btn_QLTaiKhoan.addActionListener((e) -> {
            pannelMain.removeAll();               
            pannelMain.add(new QLTaiKhoan());
            pannelMain.revalidate();                
            pannelMain.repaint();
        });
        
        btn_QLMatHang.addActionListener((e) -> {
            pannelMain.removeAll();
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setPreferredSize(pannelMain.getSize());
            wrapper.add(new QLMatHang(), BorderLayout.CENTER);
            pannelMain.add(wrapper);
            pannelMain.revalidate();
            pannelMain.repaint();
        });
        
        btn_QLDonNhapHang.addActionListener((e) -> {
            pannelMain.removeAll();
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setPreferredSize(pannelMain.getSize()); 
            wrapper.add(new QLDonNhapHang(), BorderLayout.CENTER);
            pannelMain.add(wrapper);
            pannelMain.revalidate();
            pannelMain.repaint();
        });

    }
    
    public static void main(String[] args) {
        TrangChu chu = new TrangChu();
    }
}
