package com.mycompany.quan_li_sieu_thi.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PhanAnh
 */
public class ChiTietDonNhapHangModel {
    private Integer maChiTietDonNhapHang;
    private Integer maMatHang;
    private Integer maDonNhapHang;
    private Integer soLuong;
    private Integer donGiaNhap;

    public ChiTietDonNhapHangModel() {
    }

    public ChiTietDonNhapHangModel(Integer maChiTietDonNhapHang, Integer maMatHang, Integer maDonNhapHang, Integer soLuong, Integer donGiaNhap) {
        this.maChiTietDonNhapHang = maChiTietDonNhapHang;
        this.maMatHang = maMatHang;
        this.maDonNhapHang = maDonNhapHang;
        this.soLuong = soLuong;
        this.donGiaNhap = donGiaNhap;
    }
    
    

    public Integer getMaChiTietDonNhapHang() {
        return maChiTietDonNhapHang;
    }

    public void setMaChiTietDonNhapHang(Integer maChiTietDonNhapHang) {
        this.maChiTietDonNhapHang = maChiTietDonNhapHang;
    }

    public Integer getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(Integer maMatHang) {
        this.maMatHang = maMatHang;
    }

    public Integer getMaDonNhapHang() {
        return maDonNhapHang;
    }

    public void setMaDonNhapHang(Integer maDonNhapHang) {
        this.maDonNhapHang = maDonNhapHang;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(Integer donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }
    
}
