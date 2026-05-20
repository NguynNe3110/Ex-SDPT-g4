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
public class ChiTietHoaDonModel {
    private Integer maChiTietHoaDon;
    private Integer maHoaDon;
    private Integer maMatHang;
    private Integer soLuong;
    private Integer donGiaBan;

    public Integer getMaChiTietHoaDon() {
        return maChiTietHoaDon;
    }

    public void setMaChiTietHoaDon(Integer maChiTietHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
    }

    public Integer getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Integer getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(Integer maMatHang) {
        this.maMatHang = maMatHang;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(Integer donGiaBan) {
        this.donGiaBan = donGiaBan;
    }
    
    public static List<ChiTietHoaDonModel> getCTHD(String sql, String[] params){
        List<ChiTietHoaDonModel> list = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    ChiTietHoaDonModel cthd = new ChiTietHoaDonModel();
                    cthd.setMaChiTietHoaDon(rs.getInt("machitiethoadon"));
                    cthd.setMaHoaDon(rs.getInt("mahoadon"));
                    cthd.setMaMatHang(rs.getInt("mamathang"));
                    cthd.setSoLuong(rs.getInt("soluong"));
                    cthd.setDonGiaBan(rs.getInt("dongiaban"));
                    list.add(cthd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
