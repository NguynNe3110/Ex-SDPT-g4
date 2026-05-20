/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.ConnectDB;


import com.mycompany.quan_li_sieu_thi.Model.TaiKhoanModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PhanAnh
 */
public class ConnectDB {
    private static String host ="localhost";
    private static String port="3307";
    private static String dbName="sieuthi";
    private static String url="jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC";
    private static String userName="root";
    private static String password="123456";
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static int write(String sql, Object[] params){
        int rowsEffect = 0;
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rowsEffect = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsEffect;
    }
    
    public static int writeReturnId(String sql, Object[] params){
        int id = -1;
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
