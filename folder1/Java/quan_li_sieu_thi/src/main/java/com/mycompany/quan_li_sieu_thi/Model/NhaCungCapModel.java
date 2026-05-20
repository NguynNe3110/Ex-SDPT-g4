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
public class NhaCungCapModel {
    private Integer maNhaCungCap;
    private String tenNhaCungCap;
    private String maSoThue;
    private String soDienThoai;
    private String diaChi;
    private String ghiChu;

    public Integer getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(Integer maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public static List<NhaCungCapModel> getNCC(String sql, Object params[]){
        List<NhaCungCapModel> list = new ArrayList<>();
        
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    NhaCungCapModel tk = new NhaCungCapModel();
                    tk.setMaNhaCungCap(rs.getInt("manhacungcap"));
                    tk.setTenNhaCungCap(rs.getString("tennhacungcap"));
                    tk.setMaSoThue(rs.getString("masothue"));
                    tk.setSoDienThoai(rs.getString("sodienthoai"));
                    tk.setDiaChi(rs.getString("diachi"));
                    tk.setGhiChu(rs.getString("ghichu"));
                    list.add(tk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
