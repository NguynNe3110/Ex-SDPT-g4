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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhanAnh
 */
public class HoaDonModel {
    private Integer maHoaDon;
    private LocalDate ngayXuat;
    private Integer maTaiKhoan;
    private String tenTaiKhoan;
    private Integer tongTien;

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }
    
    
    public Integer getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }
   
    public static List<HoaDonModel> getHoaDon(String sql, Object[] params){
        List<HoaDonModel> list = new ArrayList<>();
       
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    HoaDonModel hd = new HoaDonModel();
                    hd.setMaHoaDon(rs.getInt("mahoadon"));
                    if(rs.getDate("ngayxuat")!= null) hd.setNgayXuat(rs.getDate("ngayxuat").toLocalDate());
                    hd.setMaTaiKhoan(rs.getInt("mataikhoan"));
                    hd.setTenTaiKhoan(rs.getString("tentaikhoan"));
                    hd.setTongTien(rs.getInt("tongtien"));
                    list.add(hd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return list;
    } 
}
