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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author PhanAnh
 */
public class MatHangModel {
    private Integer maMatHang;
    private String tenMatHang;
    private Integer soLuong;
    private Integer donGiaBan;
    private Integer maDanhMuc;
    private String ghiChu;

    public MatHangModel() {
    }
    
    public MatHangModel(Integer maMatHang, String tenMatHang, Integer soLuong, Integer donGiaBan, Integer maDanhMuc, String ghiChu) {
        this.maMatHang = maMatHang;
        this.tenMatHang = tenMatHang;
        this.soLuong = soLuong;
        this.donGiaBan = donGiaBan;
        this.maDanhMuc = maDanhMuc;
        this.ghiChu = ghiChu;
    }
    

    public Integer getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(Integer maMatHang) {
        this.maMatHang = maMatHang;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
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

    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    //Function 
    public List<MatHangModel> getAllMatHang() {
        List<MatHangModel> list = new ArrayList<>();
        String sql = "SELECT mh.*, dm.TenDanhMuc " +
                    "FROM MatHang mh " +
                    "LEFT JOIN DanhMuc dm ON mh.MaDanhMuc = dm.MaDanhMuc " +
                    "ORDER BY mh.tenmathang";
        
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                MatHangModel mh = new MatHangModel();
                mh.setMaMatHang(rs.getInt("mamathang"));
                mh.setTenMatHang(rs.getString("tenmathang"));
                mh.setSoLuong(rs.getInt("SoLuong"));
                mh.setDonGiaBan(rs.getInt("donGiaBan"));
                mh.setMaDanhMuc(rs.getInt("MaDanhMuc"));
                mh.setGhiChu(rs.getString("GhiChu"));
                
                list.add(mh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu: " + e.getMessage());
        }
        return list;
    }
    
    public boolean addMatHang(MatHangModel mh) {
        String sql = "INSERT INTO MatHang (tenmathang,SoLuong,donGiaBan,MaDanhMuc,GhiChu) " +
                    " VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, mh.getTenMatHang());
            pstmt.setInt(2, mh.getSoLuong());
            pstmt.setInt(3, mh.getDonGiaBan());
            pstmt.setInt(4, mh.getMaDanhMuc());
            pstmt.setString(5, mh.getGhiChu());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm mặt hàng: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateMatHang(MatHangModel mh) {
        String sql = "UPDATE MatHang SET tenmathang=?, SoLuong=?,  " +
                    "donGiaBan=?,MaDanhMuc=?, GhiChu=? " +
                    "WHERE mamathang=?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, mh.getTenMatHang());
            pstmt.setInt(2, mh.getSoLuong());
            pstmt.setInt(3, mh.getDonGiaBan());
            pstmt.setInt(4, mh.getMaDanhMuc());
            pstmt.setString(5, mh.getGhiChu());
            pstmt.setInt(6, mh.getMaMatHang());

            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteMatHang(int maMH) {
        String sql = "DELETE from MatHang WHERE mamathang=?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maMH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa mặt hàng: " + e.getMessage());
            return false;
        }
    }
    
    // Tìm kiếm mặt hàng
    public List<MatHangModel> searchMatHang(String keyword, int maDanhMuc) {
        List<MatHangModel> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT mh.*, dm.tendanhmuc FROM MatHang mh " +
            "LEFT JOIN DanhMuc dm ON mh.MaDanhMuc = dm.MaDanhMuc " +
            "WHERE 1 = 1 "
        );
        
        List<Object> params = new ArrayList<>();
        
        if (keyword != null && !keyword.isEmpty()) {
            sql.append("AND (mh.tenmathang LIKE ? OR mh.GhiChu LIKE ?) ");
            params.add("%" + keyword + "%");
            params.add("%" + keyword + "%");
        }
        
        if (maDanhMuc > 0) {
            sql.append("AND mh.MaDanhMuc = ? ");
            params.add(maDanhMuc);
        }
        
        sql.append("ORDER BY mh.tenmathang");
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MatHangModel mh = new MatHangModel();
                mh.setMaMatHang(rs.getInt("mamathang"));
                mh.setTenMatHang(rs.getString("Tenmathang"));
                mh.setSoLuong(rs.getInt("SoLuong"));
                mh.setDonGiaBan(rs.getInt("donGiaBan"));
                mh.setMaDanhMuc(rs.getInt("MaDanhMuc"));
                mh.setGhiChu(rs.getString("GhiChu"));
                
                list.add(mh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + e.getMessage());
        }
        return list;
    }
 
    // Lấy danh sách danh mục
    public Map<Integer, String> getDanhMuc() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(0, "-- Tất cả danh mục --");
        
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM DanhMuc ORDER BY TenDanhMuc")) {
            
            while (rs.next()) {
                map.put(rs.getInt("MaDanhMuc"), rs.getString("TenDanhMuc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getTenDanhMuc(int maDanhMuc) {
        String sql = "SELECT tendanhmuc FROM danhmuc WHERE madanhmuc = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maDanhMuc);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("tendanhmuc");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
