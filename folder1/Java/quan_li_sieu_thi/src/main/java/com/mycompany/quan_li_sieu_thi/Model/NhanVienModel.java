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
public class NhanVienModel {
    private String maNhanVien;
    private String tenNhanVien;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String gioiTinh;
    private String queQuan;
    private Integer maBoPhan;
    private String trangThai;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public Integer getMaBoPhan() {
        return maBoPhan;
    }

    public void setMaBoPhan(Integer maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    public static List<NhanVienModel> getNhanVien(String sql, Object[] params){
        List<NhanVienModel> list = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    NhanVienModel nv = new NhanVienModel();
                    nv.setMaNhanVien(rs.getString("manhanvien"));
                    nv.setTenNhanVien(rs.getString("tennhanvien"));

                    if (rs.getDate("ngaysinh") != null) {
                        nv.setNgaySinh(rs.getDate("ngaysinh").toLocalDate());
                    }

                    nv.setSoDienThoai(rs.getString("sodienthoai"));
                    nv.setGioiTinh(rs.getString("gioitinh"));
                    nv.setQueQuan(rs.getString("quequan"));
                    nv.setMaBoPhan(rs.getInt("mabophan"));
                    nv.setTrangThai(rs.getString("trangthai"));

                    list.add(nv);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
