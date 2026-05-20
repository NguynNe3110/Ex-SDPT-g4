/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.ConnectDB;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PhanAnh
 */
public class ConnectDB {
    private static String host ="localhost";
    private static String port="3306";
    private static String dbName="sieuthi";
    private static String url="jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC";
    private static String userName="root";
    private static String password="";
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
