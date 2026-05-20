/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testdb;

import java.sql.*;

/**
 *
 * @author PhanAnh
 */
public class MyConnection {
    private String host;
    private String port;
    private String dbName;
    private String url;
    private String userName;
    private String password;
    Connection conn;

    public MyConnection() {
        host = "DESKTOP-KAMG14I";
        port = "1433";
        dbName = "sinh_vien";
        url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
        userName = "sa";
        password = "admin";
    }
    
    public Connection getConnection(){
        try {
            if (conn != null) {
               return conn; 
            }
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public ResultSet DocDl(String sql){
        ResultSet rs = null;
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.getLogger(MyConnection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        if(rs == null) return null;
        return rs;
    }
    
    public int GhiDl(String sql){
        int rows = 0;
         try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            rows = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.getLogger(MyConnection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return rows;
    }
}
