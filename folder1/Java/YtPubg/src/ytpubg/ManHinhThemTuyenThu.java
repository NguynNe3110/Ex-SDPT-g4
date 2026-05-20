/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ytpubg;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class ManHinhThemTuyenThu extends JLabel{
    private final JLabel lb_TenTuyenThu = new JLabel("Tên tuyển thủ");
    private final JTextField tf_TenTuyenThu = new JTextField(15);
    private final JLabel lb_DoiTuyen = new JLabel("Đội tuyển");
    private final JComboBox<String> cb_DoiTuyen = new JComboBox<>();
    private final JButton bt_Them = new JButton("Thêm");
    private final JLabel lb_LinkYoutube = new JLabel("Link youtube");
    private final JTextField tf_LinkYoutube = new JTextField(25);
    private final JLabel lb_LinkTwitch = new JLabel("Link twitch");
    private final JTextField tf_LinkTwitch = new JTextField(25);
    private final JLabel lb_LinkDouyu = new JLabel("Link douyu");
    private final JTextField tf_LinkDouyu = new JTextField(25);
    private final JLabel lb_LinkSooplive = new JLabel("Link sooplive");
    private final JTextField tf_LinkSooplive = new JTextField(25);
    
    public ManHinhThemTuyenThu() throws SQLException {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        
        this.add(lb_TenTuyenThu);
        this.add(tf_TenTuyenThu);
        this.add(lb_DoiTuyen);
        this.add(cb_DoiTuyen);
        this.add(bt_Them);
        this.add(lb_LinkYoutube);
        this.add(tf_LinkYoutube);
        this.add(lb_LinkTwitch);
        this.add(tf_LinkTwitch);
        this.add(lb_LinkDouyu);
        this.add(tf_LinkDouyu);
        this.add(lb_LinkSooplive);
        this.add(tf_LinkSooplive);
        
        TranDlCb();
    }
    
    private void clickThem(){
        bt_Them.addActionListener((e) -> {
            String TenTuyenThu = tf_TenTuyenThu.getText().trim();
            String DoiTuyen = cb_DoiTuyen.getSelectedItem().toString();
            String LinkYt = tf_LinkYoutube.getText().trim();
            String LinkTw = tf_LinkTwitch.getText().trim();
            String LinkDy = tf_LinkDouyu.getText().trim();
            String LinkSl = tf_LinkSooplive.getText().trim();
            DataBase db = new DataBase();
            ResultSet rs;
            int rows = 0;
            try {
                rs = db.DocDL("Select MaDoiTuyen from DoiTuyen where TenDoiTuyen = '" + DoiTuyen + "'");
                rs.next();
                String MaDoiTuyen = rs.getString("MaDoiTuyen");
                String sql = "Insert TuyenThu (TenTuyenThu,MaDoiTuyen,LinkYT,LinkTW,LinkDY,LinkSL) values ('" + TenTuyenThu + "',"
                        + MaDoiTuyen + ",";
                sql += (!LinkYt.isEmpty()) ? "'" + LinkYt + "'," : "null,";
                sql += (!LinkTw.isEmpty()) ? "'" + LinkTw + "'," : "null,";
                sql += (!LinkDy.isEmpty()) ? "'" + LinkDy + "'," : "null,";
                sql += (!LinkSl.isEmpty()) ? "'" + LinkSl + "'," : "null,";
                rows = db.GhiDL(sql);
            } catch (SQLException ex) {
                System.getLogger(ManHinhThemTuyenThu.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            if (rows == 0) JOptionPane.showMessageDialog(this,"Không thêm được dữ liệu","Thông báo",1);
        });
    }
    
    private void TranDlCb() throws SQLException {
        DataBase db = new DataBase();
        ResultSet rs = db.DocDL("select TenDoiTuyen from DoiTuyen");
        List<String> listTenDoiTuyen = new ArrayList<>();
        while(rs.next()){
            listTenDoiTuyen.add(rs.getString("TenDoiTuyen"));
        }
        String[] TenDoiTuyen = listTenDoiTuyen.toArray(String[]::new);        
        for (String t : TenDoiTuyen) {
            cb_DoiTuyen.addItem(t);
        }
    }
    
    public static void main(String[] args) {
        try {
            ManHinhThemTuyenThu m = new ManHinhThemTuyenThu();
        } catch (SQLException ex) {
            System.getLogger(ManHinhThemTuyenThu.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
