/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testdb;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PhanAnh
 */

public class TestDB extends JFrame {
    
    private final JLabel lb_MaSinhVien = new JLabel("Mã sinh viên");
    private final JTextField tf_MaSinhVien = new JTextField(25);
    private final JLabel lb_HoTen = new JLabel("Họ tên");
    private final JTextField tf_HoTen = new JTextField(25);
    private final JLabel lb_QueQuan = new JLabel("Quê quán");
    private final JTextField tf_QueQuan = new JTextField(25);
    
    private final JButton btn_TimKiem = new JButton("Tìm kiếm");
    private final JButton btn_Them = new JButton("Thêm");
    private final JButton btn_Sua = new JButton("Sửa");
    private final JButton btn_Xoa = new JButton("Xóa");
    
    private final JTable tb = new JTable();
    private final JScrollPane sp = new JScrollPane(tb);
    
    public TestDB(){
        this.setTitle("Quản lí sinh viên");
        this.setSize(900,600);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        this.add(lb_MaSinhVien);
        this.add(tf_MaSinhVien);
        this.add(lb_HoTen);
        this.add(tf_HoTen);
        this.add(lb_QueQuan);
        this.add(tf_QueQuan);
        
        this.add(btn_TimKiem);
        this.add(btn_Them);
        this.add(btn_Sua);
        this.add(btn_Xoa);
        
        this.add(sp);
        
        TranDL("Select * from sinhvien");
        clickTimKiem();
        clickThem();
        clickXoa();
        clickSua();
        
        this.setVisible(true);
    }
    
    private void TranDL(String sql){
        MyConnection db = new MyConnection();
        ResultSet rs = db.DocDl(sql);
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        dtm.addColumn("Mã sinh viên");
        dtm.addColumn("Họ tên");
        dtm.addColumn("Quê quán");
        try {
            while(rs.next()){
                dtm.addRow(new Object[]{
                    rs.getString("masv"),
                    rs.getString("hoten"),  
                    rs.getString("quequan")
                });
            }
        } catch (SQLException ex) {
            System.getLogger(TestDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        tb.setModel(dtm);
    }
    
    private void clickTimKiem(){
        btn_TimKiem.addActionListener((e) -> {
            String MaSinhVien = tf_MaSinhVien.getText().trim();
            String HoTen = tf_HoTen.getText().trim();
            String QueQuan = tf_QueQuan.getText().trim();
            String sql = "Select * from sinhvien where 1=1";
            if(!MaSinhVien.isEmpty()) sql += "and masv like '%" + MaSinhVien + "%'";
            if(!HoTen.isEmpty()) sql += "and hoten like '%" + HoTen + "%'";
            if(!QueQuan.isEmpty()) sql += "and quequan like '%" + QueQuan + "%'";
            TranDL(sql);
        });
    }
    
    private void clickThem(){
        btn_Them.addActionListener((e) -> {
            String MaSinhVien = tf_MaSinhVien.getText().trim();
            String HoTen = tf_HoTen.getText().trim();
            String QueQuan = tf_QueQuan.getText().trim();
            String sql = "Insert sinhvien values ('" + MaSinhVien + "',N'" + HoTen + "',N'" + QueQuan + "')";
            MyConnection db = new MyConnection();
            int rows = db.GhiDl(sql);
            if(rows == 0) JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else TranDL("Select * from sinhvien");
        });
    }
    
    private void clickSua(){
        btn_Sua.addActionListener((e) -> {
            String MaSinhVien = tf_MaSinhVien.getText().trim();
            String HoTen = tf_HoTen.getText().trim();
            String QueQuan = tf_QueQuan.getText().trim();
            String sql = "Update sinhvien set hoten = N'" + HoTen + "',quequan = N'" + QueQuan + "' where masv ='" + MaSinhVien + "'";
            MyConnection db = new MyConnection();
            int rows = db.GhiDl(sql);
            if(rows == 0) JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else TranDL("Select * from sinhvien");
        });
    }
    
    private void clickXoa(){
        btn_Xoa.addActionListener((e) -> {
            String MaSinhVien = tf_MaSinhVien.getText().trim();
            String sql = "Delete from sinhvien where masv = '" + MaSinhVien + "'";
            MyConnection db = new MyConnection();
            int rows = db.GhiDl(sql);
            if(rows == 0) JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            else TranDL("Select * from sinhvien");
        });
    }
    
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/estatebasic";
	String username = "root";
	String pass = "123456";
        try (
                Connection connection = DriverManager.getConnection(url, username, pass);
             Statement statement = connection.createStatement();
	     ResultSet rs = statement.executeQuery("SELECT name, street, ward FROM building")) {
	     System.out.println("ok");
	} catch (SQLException e) {
        	// TODO Auto-generated catch block
	e.printStackTrace();
        }
    }
}
