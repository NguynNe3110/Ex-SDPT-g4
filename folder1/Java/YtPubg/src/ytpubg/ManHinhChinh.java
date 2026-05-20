/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ytpubg;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PhanAnh
 */
public class ManHinhChinh extends JFrame {
    
    private final JLabel lb_TenTuyenThu = new JLabel("Tên tuyển thủ");
    private final JTextField tf_TenTuyenThu = new JTextField(25);
    private final JLabel lb_DoiTuyen = new JLabel("Đội tuyển");
    private final JTextField tf_DoiTuyen = new JTextField(25);
    private final JButton btn_TimKiem = new JButton("Tìm kiếm");
    
    private final JTable tb_TuyenThu = new JTable();
    private final JScrollPane sp = new JScrollPane(tb_TuyenThu);
    
    private final JPopupMenu pm = new JPopupMenu();
    private final JMenuItem mi_Xem = new JMenuItem("Xem");
    private final JMenuItem mi_Them = new JMenuItem("Thêm");
    private final JMenuItem mi_Sua = new JMenuItem("Sửa");
    private final JMenuItem mi_Xoa = new JMenuItem("Xoa");
    
    public ManHinhChinh() throws SQLException{
        this.setTitle("Danh sách tuyển thủ");
        this.setSize(900,600);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        this.add(lb_TenTuyenThu);
        this.add(tf_TenTuyenThu);
       
        this.add(lb_DoiTuyen);
        this.add(tf_DoiTuyen);
        
        this.add(btn_TimKiem);
        
        pm.add(mi_Xem);
        pm.add(mi_Them);
        pm.add(mi_Sua);
        pm.add(mi_Xoa);
        tb_TuyenThu.setComponentPopupMenu(pm);
        clickPopupMenu();
        
        TranDL("Select TenTuyenThu, TenDoiTuyen, count(MaVideo) as 'Số video' "
                + "from DoiTuyen dt join TuyenThu tt on dt.MaDoiTuyen = tt.MaDoiTuyen left join Video v on tt.MaTuyenThu = v.MaTuyenThu "
                + "group by TenTuyenThu, TenDoiTuyen order by TenTuyenThu, TenDoiTuyen");
        clickTimKiem();
       
        this.add(sp);
        
        this.setVisible(true);
    }
    
    private void clickPopupMenu (){
        mi_Xem.addActionListener((e) -> {
            int row = tb_TuyenThu.getSelectedRow(); 
            if (row != -1){
                String TenTuyenThu = tb_TuyenThu.getValueAt(row, 0).toString();
                String TenDoiTuyen = tb_TuyenThu.getValueAt(row, 1).toString();
                String SoVideo = tb_TuyenThu.getValueAt(row, 2).toString();
                
                JDialog dl_ChiTietTuyenThu = new JDialog(this,"Chi tiết tuyển thủ",true);
                dl_ChiTietTuyenThu.setLocationRelativeTo(this);
                dl_ChiTietTuyenThu.setSize(600,300);
                try {
                    dl_ChiTietTuyenThu.add(new ManHinhChiTiet(TenTuyenThu, TenDoiTuyen, SoVideo));
                    dl_ChiTietTuyenThu.setVisible(true);
                } catch (SQLException ex) {
                    System.getLogger(ManHinhChinh.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }    
        });
        
        mi_Them.addActionListener((e) -> {
            
        });
    }
    
    private void clickTimKiem(){
        btn_TimKiem.addActionListener((e) -> {
            String TenTuyenThu = tf_TenTuyenThu.getText().trim();
            String TenDoiTuyen = tf_DoiTuyen.getText().trim();
            String sql = "Select TenTuyenThu, TenDoiTuyen, count(MaVideo) as 'Số video' "
                    + "from DoiTuyen dt join TuyenThu tt on dt.MaDoiTuyen = tt.MaDoiTuyen left join Video v on tt.MaTuyenThu = v.MaTuyenThu "
                    + "where 1=1 ";
            if(!TenTuyenThu.isEmpty()) sql += "and TenTuyenThu like '" + TenTuyenThu + "%' ";
            if(!TenDoiTuyen.isEmpty()) sql += "and TenDoiTuyen like '" + TenDoiTuyen + "%' ";
            sql += "group by TenTuyenThu, TenDoiTuyen order by TenTuyenThu, TenDoiTuyen";
            try {
                TranDL(sql);
            } catch (SQLException ex) {
                System.getLogger(ManHinhChinh.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }
    
    private void TranDL(String sql) throws SQLException{
        DataBase db = new DataBase();
        ResultSet rs = db.DocDL(sql);
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.addColumn("Tên tuyển thủ");
        dtm.addColumn("Đội tuyển");
        dtm.addColumn("Số video");
        while(rs.next()){
            dtm.addRow(new Object[]{
                rs.getString("TenTuyenThu"),
                rs.getString("TenDoiTuyen"),
                rs.getString("Số video")  
                });
        }
        tb_TuyenThu.setModel(dtm);
    }
    
    public static void main(String[] args){
        ManHinhChinh m;
        try {
            m = new ManHinhChinh();
        } catch (SQLException ex) {
            System.getLogger(ManHinhChinh.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }        
    }
}
