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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PhanAnh
 */
public class DonNhapHangModel {
    private Integer maDonNhapHang;
    private Integer maNhaCungCap;
    private LocalDate ngayNhapHang;
    private Integer maTaiKhoan;
    private String ghiChu;

    public DonNhapHangModel() {
    }
    
    public DonNhapHangModel(Integer maDonNhapHang, Integer maNhaCungCap, LocalDate ngayNhapHang, Integer maTaiKhoan, String ghiChu) {
        this.maDonNhapHang = maDonNhapHang;
        this.maNhaCungCap = maNhaCungCap;
        this.ngayNhapHang = ngayNhapHang;
        this.maTaiKhoan = maTaiKhoan;
        this.ghiChu = ghiChu;
    }
    

    public Integer getMaDonNhapHang() {
        return maDonNhapHang;
    }

    public void setMaDonNhapHang(Integer maDonNhapHang) {
        this.maDonNhapHang = maDonNhapHang;
    }

    public Integer getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(Integer maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public LocalDate getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(LocalDate ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    //Function
    
        // Lấy tất cả đơn nhập hàng
    public List<DonNhapHangModel> getAllDonNhapHang() {
        List<DonNhapHangModel> list = new ArrayList<>();
        String sql = "SELECT * "
                + " FROM donnhaphang "
                + " ORDER BY ngaynhaphang DESC";
        
        try {
            Connection conn = ConnectDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                LocalDate ngayNhapHang = rs.getDate("ngaynhaphang").toLocalDate();
                DonNhapHangModel don = new DonNhapHangModel(
                    rs.getInt("madonnhaphang"),
                    rs.getInt("manhacungcap"),
                    ngayNhapHang,
                    rs.getInt("mataikhoan"),
                    rs.getString("ghichu")
                );
                list.add(don);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    // Thêm đơn nhập hàng
    public int addDonNhapHang(DonNhapHangModel don) {
        String sql = "INSERT INTO donnhaphang (manhacungcap, ngaynhaphang, mataikhoan, ghichu) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectDB.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, don.getMaNhaCungCap());
            pstmt.setDate(2, java.sql.Date.valueOf(don.getNgayNhapHang()));
            pstmt.setInt(3, don.getMaTaiKhoan());
            pstmt.setString(4, don.getGhiChu());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Return generated ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // Xóa đơn nhập hàng
    public boolean deleteDonNhapHang(int maDon) {
        Connection conn = null;
        
        try {
            conn = ConnectDB.getConnection();
            conn.setAutoCommit(false);
            
            // Xóa chi tiết trước
            String sql1 = "DELETE FROM chitietdonnhaphang WHERE madonnhaphang = ?";
            try (PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
                pstmt1.setInt(1, maDon);
                pstmt1.executeUpdate();
            }
            // Xóa đơn
            String sql2 = "DELETE FROM donnhaphang WHERE madonnhaphang = ?";
            try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                pstmt2.setInt(1, maDon);
                int affected = pstmt2.executeUpdate();
                
                if (affected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) {}
            }
            e.printStackTrace();
        }
        return false;
    }
    
    // Tìm kiếm đơn nhập hàng
    public List<DonNhapHangModel> searchDonNhapHang(String keyword) {
        List<DonNhapHangModel> list = new ArrayList<>();
        String sql = "SELECT * FROM donnhaphang WHERE ghichu LIKE ? OR madonnhaphang = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectDB.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            
            try {
                pstmt.setInt(2, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                pstmt.setInt(2, 0);
            }
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LocalDate ngayNhapHang = rs.getDate("ngaynhaphang").toLocalDate();
                DonNhapHangModel don = new DonNhapHangModel(
                    rs.getInt("madonnhaphang"),
                    rs.getInt("manhacungcap"),
                    ngayNhapHang,
                    rs.getInt("mataikhoan"),
                    rs.getString("ghichu")
                );
                list.add(don);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    // Lấy chi tiết đơn
    public List<ChiTietDonNhapHangModel> getChiTietDon(int maDon) {
        List<ChiTietDonNhapHangModel> list = new ArrayList<>();
        String sql ="SELECT * " +
            "FROM chitietdonnhaphang " +
            "WHERE madonnhaphang = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectDB.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maDon);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ChiTietDonNhapHangModel ct = new ChiTietDonNhapHangModel(
                    rs.getInt("machitietdonnhaphang"),
                    rs.getInt("mamathang"),
                    rs.getInt("madonnhaphang"),
                    rs.getInt("soluong"),
                    rs.getInt("dongianhap")
                );
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Thêm chi tiết đơn
    public boolean addChiTietDon(ChiTietDonNhapHangModel ct) {
        String sql = "INSERT INTO chitietdonnhaphang (mamathang, madonnhaphang, soluong, dongianhap) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConnectDB.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ct.getMaMatHang());
            pstmt.setInt(2, ct.getMaDonNhapHang());
            pstmt.setInt(3, ct.getSoLuong());
            pstmt.setInt(4, ct.getDonGiaNhap());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Lấy danh sách mặt hàng
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
    
    public List<NhaCungCapModel> getAllNhaCungCap() {
        List<NhaCungCapModel> list = new ArrayList<>();
        String sql = "SELECT * " +
                    "FROM nhacungcap " +
                    "ORDER BY tennhacungcap";
        
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                NhaCungCapModel mh = new NhaCungCapModel();
                mh.setMaNhaCungCap(rs.getInt("manhacungcap"));
                mh.setTenNhaCungCap(rs.getString("tennhacungcap"));
                mh.setMaSoThue(rs.getString("masothue"));
                mh.setSoDienThoai(rs.getString("sodienthoai"));
                mh.setDiaChi(rs.getString("diachi"));
                mh.setGhiChu(rs.getString("GhiChu"));
                list.add(mh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu: " + e.getMessage());
        }
        return list;
    }
    
    public String getTenNhaCungCap(int maNhaCungCap) {
        String sql = "SELECT tenNhaCungCap FROM NhaCungCap WHERE manhacungcap = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maNhaCungCap);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("tennhacungcap");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public String getTenTaiKhoan(int maTaiKhoan) {
        String sql = "SELECT tenTaiKhoan FROM taiKhoan WHERE maTaiKhoan = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maTaiKhoan);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("tentaikhoan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public String getTenMatHang(int maTaiKhoan) {
        String sql = "SELECT tenmathang FROM mathang WHERE mamathang = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maTaiKhoan);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("tenmathang");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static Integer getMaTaiKhoanByTen(String tenTaiKhoan) {
        Integer maTaiKhoan = null;

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                 "SELECT mataikhoan FROM taikhoan WHERE tentaikhoan = ?"
             )) {

            ps.setString(1, tenTaiKhoan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    maTaiKhoan = rs.getInt("mataikhoan");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maTaiKhoan;
    }

}
