/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.Model;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhanAnh
 */
public class TaiKhoanModel {
    private Integer maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;
    private String maNhanVien;
    private String chucVu;
    private String trangThai;

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    public static TaiKhoanModel checkTaiKhoan(String tenTaiKhoan, String matKhau){
        TaiKhoanModel tk = null;
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement("Select * from taikhoan where tentaikhoan = ? and matkhau = ?")){
            ps.setString(1, tenTaiKhoan);
            ps.setString(2, matKhau);
            
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    tk = new TaiKhoanModel();
                    tk.setMaTaiKhoan(rs.getInt("mataikhoan"));
                    tk.setTenTaiKhoan(rs.getString("tentaikhoan"));
                    tk.setMatKhau(rs.getString("matkhau"));
                    tk.setMaNhanVien(rs.getString("manhanvien"));
                    tk.setChucVu(rs.getString("chucvu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
    
    public static List<TaiKhoanModel> getTaiKhoan(String sql, Object[] params){
        List<TaiKhoanModel> list = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    TaiKhoanModel tk = new TaiKhoanModel();
                    tk.setMaTaiKhoan(rs.getInt("mataikhoan"));
                    tk.setTenTaiKhoan(rs.getString("tentaikhoan"));
                    tk.setMatKhau(rs.getString("matkhau"));
                    tk.setMaNhanVien(rs.getString("manhanvien"));
                    tk.setChucVu(rs.getString("chucvu"));
                    tk.setTrangThai(rs.getString("trangthai"));
                    list.add(tk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        List<TaiKhoanModel> kq= TaiKhoanModel.getAllTaiKhoan();
//    }
}
