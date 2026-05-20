/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bai8;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhanAnh
 */
public class SinhVien {
    private String masv;
    private String hoTen;
    private String queQuan;

    public SinhVien(String masv, String hoTen, String queQuan) {
        this.masv = masv;
        this.hoTen = hoTen;
        this.queQuan = queQuan;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
    
    public static List<SinhVien> getAllSinhVien(){
        MyConnection myconnection = new MyConnection();
        ResultSet rs = myconnection.DocDl("Select masv,hoten,quequan from sinhvien");
        List<SinhVien> list = new ArrayList<>();
        try {
            while(rs.next()){
                list.add(new SinhVien(rs.getString("masv"),rs.getString("hoten"),rs.getString("quequan")));
            }
        } catch (SQLException ex) {
            System.getLogger(SinhVien.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return list;
    }
}
