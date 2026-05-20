/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ytpubg;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
/**
 *
 * @author PhanAnh
 */
public class DataBase extends SQLServerDataSource {
    private final String server = "DESKTOP-KAMG14I";
    private final String user = "sa";
    private final String pass = "admin";     
    private final String dbname = "YtPubg";
    private final int port = 1433;

    public DataBase() {
        this.setServerName(server);
        this.setUser(user);
        this.setPassword(pass);
        this.setDatabaseName(dbname);
        this.setPortNumber(port);
        this.setTrustServerCertificate(true);
    }   
    
    public int GhiDL(String sql) throws SQLServerException, SQLException{
        Connection conn = this.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeUpdate();
    }
    
    public ResultSet DocDL(String sql) throws SQLServerException, SQLException{
        Connection conn = this.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs == null) return null;
        return rs;
    }
}
