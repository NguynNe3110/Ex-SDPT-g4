/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.Model.HoaDonModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PhanAnh
 */
public class QLHoaDon extends JPanel{
    private JLabel lb_Ngay = new JLabel("Ngày");
    private JLabel lb_Thang = new JLabel("Tháng");
    private JLabel lb_Nam = new JLabel("Năm");
    private JLabel lb_TongTienTu = new JLabel("Tổng tiền từ");
    private JLabel lb_TongTienDen = new JLabel("đến");
    private JLabel lb_TaiKhoanTaoDon = new JLabel("Tài khoản tạo đơn");
    
    private JTextField tf_Ngay = new JTextField(5);
    private JTextField tf_Thang = new JTextField(5);
    private JTextField tf_Nam = new JTextField(5);
    private JTextField tf_TongTienTu = new JTextField(15);
    private JTextField tf_TongTienDen = new JTextField(15);
    private JTextField tf_TaiKhoanTaoDon = new JTextField(15);
    
    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Huy = new JButton("Hủy");
    private JButton btn_ThemHD = new JButton("Thêm hóa đơn");
    
    private JTable tb_HoaDon = new JTable();
    
    public QLHoaDon() {
        initUI();
        initTable("SELECT hd.*, tentaikhoan, sum(soluong*dongiaban) as 'tongtien'" +
            " from hoadon hd join chitiethoadon cthd on hd.mahoadon = cthd.mahoadon join taikhoan tk on tk.mataikhoan = hd.mataikhoan" +
            " group by hd.mahoadon", null);
        setClick();
    }
    
    private void initUI(){
        this.setLayout(new BorderLayout(10,10));
        
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_form.add(lb_Ngay);
        jp_form.add(tf_Ngay);
        
        jp_form.add(lb_Thang);
        jp_form.add(tf_Thang);
        
        jp_form.add(lb_Nam);
        jp_form.add(tf_Nam);
        
        jp_form.add(lb_TongTienTu);
        jp_form.add(tf_TongTienTu);
        
        jp_form.add(lb_TongTienDen);
        jp_form.add(tf_TongTienDen);
        
        jp_form.add(lb_TaiKhoanTaoDon);
        jp_form.add(tf_TaiKhoanTaoDon);
        
        this.add(jp_form, BorderLayout.NORTH);
        
        JPanel jp_btn = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_btn.add(btn_TimKiem);
        jp_btn.add(btn_Huy);
        jp_btn.add(btn_ThemHD);
        this.add(jp_btn,BorderLayout.CENTER);
        
        JScrollPane scp = new JScrollPane(tb_HoaDon);
        this.add(scp,BorderLayout.SOUTH);
    }
    
    public void initTable(String sql, Object[] params){
        String[] columns = {"Mã hóa đơn", "Ngày xuất", "Tài khoản tạo", "Tổng tiền"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa tất cả ô
            }
        };
        
        List<HoaDonModel> list = HoaDonModel.getHoaDon(sql, params); //lấy dữ liệu hóa đơn từ csdl

        for (HoaDonModel hoaDonModel : list) {
            dtm.addRow(new Object[]{
                hoaDonModel.getMaHoaDon(),
                hoaDonModel.getNgayXuat(),
                hoaDonModel.getTenTaiKhoan(),
                hoaDonModel.getTongTien()
            });
        }
        
        tb_HoaDon.setModel(dtm);
    }

    
    private boolean checkNumber(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void setClick(){
        btn_Huy.addActionListener((e) -> {
            tf_Ngay.setText("");
            tf_Thang.setText("");
            tf_Nam.setText("");
            tf_TongTienTu.setText("");
            tf_TongTienDen.setText("");
            tf_TaiKhoanTaoDon.setText("");
        });
        
        
        btn_TimKiem.addActionListener((e) -> {
            String ngay = tf_Ngay.getText().trim();
            String thang = tf_Thang.getText().trim();
            String nam = tf_Nam.getText().trim();
            String tongTienTu = tf_TongTienTu.getText().trim();
            String tongTienDen = tf_TongTienDen.getText().trim();
            String taiKhoanTaoDon = tf_TaiKhoanTaoDon.getText().trim();
            
            Integer int_ngay = null;
            Integer int_thang = null;
            Integer int_nam = null;
            Integer int_tongTienTu = null;
            Integer int_tongTienDen = null;
            
            if (!ngay.isEmpty()) {
                if (!checkNumber(ngay)) {
                    JOptionPane.showMessageDialog(this, "Ngày phải là số!");
                    return;
                }
                int_ngay = Integer.parseInt(ngay);
            }
            
            if (!thang.isEmpty()) {
                if (!checkNumber(thang)) {
                    JOptionPane.showMessageDialog(this, "Tháng phải là số!");
                    return;
                }
                int_thang = Integer.parseInt(thang);
            }

            if (!nam.isEmpty()) {
                if (!checkNumber(nam)) {
                    JOptionPane.showMessageDialog(this, "Năm phải là số!");
                    return;
                }
                int_nam = Integer.parseInt(nam);
            }
    
            if (!tongTienTu.isEmpty()) {
                if (!checkNumber(tongTienTu)) {
                    JOptionPane.showMessageDialog(this, "Tổng tiền phải là số!");
                    return;
                }
                int_tongTienTu = Integer.parseInt(tongTienTu);
            }

            if (!tongTienDen.isEmpty()) {
                if (!checkNumber(tongTienDen)) {
                    JOptionPane.showMessageDialog(this, "Tổng tiền phải là số!");
                    return;
                }
                int_tongTienDen = Integer.parseInt(tongTienDen);
            }
            
            StringBuilder sql = new StringBuilder("SELECT hd.*, tentaikhoan, sum(soluong*dongiaban) as 'tongtien'" +
            " from hoadon hd join chitiethoadon cthd on hd.mahoadon = cthd.mahoadon join taikhoan tk on tk.mataikhoan = hd.mataikhoan" +
            " group by hd.mahoadon having 1=1");
            List<Object> params = new ArrayList<>();
            
            if (int_ngay!=null) {
                sql.append(" AND day(ngayxuat) = ? ");
                params.add(int_ngay);
            }

            if (int_thang!=null) {
                sql.append(" AND month(ngayxuat) = ? ");
                params.add(int_thang);
            }
            
            if (int_nam!=null) {
                sql.append(" AND year(ngayxuat) = ? ");
                params.add(int_nam);
            }
            
            if (int_tongTienTu!=null) {
                sql.append(" AND tongtien >= ? ");
                params.add(int_tongTienTu);
            }
            
            if (int_tongTienDen!=null) {
                sql.append(" AND tongtien <= ? ");
                params.add(int_tongTienDen);
            }
            
            if(!taiKhoanTaoDon.isEmpty()){
                sql.append(" AND tentaikhoan like ? ");
                params.add("%" + taiKhoanTaoDon + "%");
            }
            
            initTable(sql.toString(), params.toArray());
        });
        
        btn_ThemHD.addActionListener((e) -> {
            ThemHoaDon themHoaDon = new ThemHoaDon(this);
        });
    }
}
