/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bai9;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhanAnh
 */
public class SanPham {
    private String idSanPham;
    private String DanhMuc;
    private String TenSanPham;
    private Integer Gia;
    private Integer SoLuong;
    private String MoTa;

    public SanPham(String idSanPham, String DanhMuc, String TenSanPham, Integer Gia, Integer SoLuong, String MoTa) {
        this.idSanPham = idSanPham;
        this.DanhMuc = DanhMuc;
        this.TenSanPham = TenSanPham;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.MoTa = MoTa;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getDanhMuc() {
        return DanhMuc;
    }

    public void setDanhMuc(String DanhMuc) {
        this.DanhMuc = DanhMuc;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public Integer getGia() {
        return Gia;
    }

    public void setGia(Integer Gia) {
        this.Gia = Gia;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    
    public static List<SanPham> getAllProduct(){
        MyConnection myconnection = new MyConnection();
        ResultSet rs = myconnection.DocDl("Select ProductID, CategoryName, ProductName, UnitPrice, Quantity, "
                + "Descriptions from Product p join Category c on p.CategoryID = c.CategoryID");
        List<SanPham> list = new ArrayList<>();
        try {
            while(rs.next()){
                SanPham sanPham = new SanPham(rs.getString("ProductID"),rs.getString("CategoryName"),rs.getString("ProductName"),
                        rs.getInt("UnitPrice"),rs.getInt("Quantity"),rs.getString("Descriptions"));
                list.add(sanPham);
            }
        } catch (SQLException ex) {
            System.getLogger(SanPham.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return list;
    }
    
}

