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
public class DanhMucModel {
    private Integer maDanhMuc;
    private String tenDanhMuc;
    private String ghiChu;

    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public static List<DanhMucModel> getDanhMuc(String sql, Object[] params){
        List<DanhMucModel> list = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    DanhMucModel dm = new DanhMucModel();
                    dm.setMaDanhMuc(rs.getInt("madanhmuc"));
                    dm.setTenDanhMuc(rs.getString("tendanhmuc"));
                    dm.setGhiChu(rs.getString("ghichu"));

                    list.add(dm);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
