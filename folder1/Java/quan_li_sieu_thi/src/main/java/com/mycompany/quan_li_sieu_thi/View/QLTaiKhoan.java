/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import com.mycompany.quan_li_sieu_thi.Model.NhanVienModel;
import com.mycompany.quan_li_sieu_thi.Model.TaiKhoanModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * 
 * @author PhanAnh
 */
public class QLTaiKhoan extends JPanel {
    private JLabel lb_MaTaiKhoan = new JLabel("Mã tài khoản");
    private JLabel lb_TenTaiKhoan = new JLabel("Tên tài khoản");
    private JLabel lb_MatKhau = new JLabel("Mật khẩu");
    private JLabel lb_MaNhanVien = new JLabel("Mã nhân viên");
    private JLabel lb_ChucVu = new JLabel("Chức vụ");
    private JLabel lb_TrangThai = new JLabel("Trạng thái");
    
    private JTextField tf_MaTaiKhoan = new JTextField(5);
    private JTextField tf_TenTaiKhoan = new JTextField(15);
    private JTextField tf_MatKhau = new JTextField(15);
    private JComboBox<String> cbo_ChucVu = new JComboBox<>();
    private JComboBox<String> cbo_MaNhanVien = new JComboBox<>();
    private JComboBox<String> cbo_TrangThai = new JComboBox<>();

    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Sua = new JButton("Sửa");
    private JButton btn_Xoa = new JButton("Xóa");
    private JButton btn_Huy = new JButton("Hủy");
    
    private JTable tb_TaiKhoan = new JTable();
    
    public QLTaiKhoan() {
        initUI();
        initTable("Select * from taikhoan", null);
        initCombobox();
        setClick();
    }
    
    private void initUI() {
        this.setLayout(new BorderLayout(10,10));
        
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_form.add(lb_MaTaiKhoan);
        jp_form.add(tf_MaTaiKhoan);
        tf_MaTaiKhoan.setEditable(false);
        
        jp_form.add(lb_TenTaiKhoan);
        jp_form.add(tf_TenTaiKhoan);

        jp_form.add(lb_MatKhau);
        jp_form.add(tf_MatKhau);

        jp_form.add(lb_ChucVu);
        jp_form.add(cbo_ChucVu);

        jp_form.add(lb_MaNhanVien);
        jp_form.add(cbo_MaNhanVien);
        
        jp_form.add(lb_TrangThai);
        jp_form.add(cbo_TrangThai);
        this.add(jp_form, BorderLayout.NORTH);
        
        JPanel jp_btn = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_btn.add(btn_TimKiem);
        jp_btn.add(btn_Them);
        jp_btn.add(btn_Sua);
        jp_btn.add(btn_Xoa);
        jp_btn.add(btn_Huy);
        this.add(jp_btn,BorderLayout.CENTER);
        
        JScrollPane scp = new JScrollPane(tb_TaiKhoan);
        this.add(scp,BorderLayout.SOUTH);
    }
    
    private void initTable(String sql, Object[] params){
        String[] columns = {"Mã Tài Khoản", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Chức vụ", "Trạng thái"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa tất cả ô
            }
        };
        List<TaiKhoanModel> taiKhoanModels = TaiKhoanModel.getTaiKhoan(sql, params);
        for (TaiKhoanModel tk : taiKhoanModels) {
            dtm.addRow(new Object[]{
                tk.getMaTaiKhoan(),
                tk.getTenTaiKhoan(),
                tk.getMatKhau(),
                tk.getMaNhanVien(),
                tk.getChucVu(),
                tk.getTrangThai()
            });  
        }
        tb_TaiKhoan.setModel(dtm);
    }
   
    private void initCombobox(){
        String[] chuVu = {"Chọn chức vụ","Admin","Bán hàng","Kho"};
        for (String string : chuVu) {
            cbo_ChucVu.addItem(string);
        }
        
        String[] trangThai = {"Chọn trạng thái","Đang hoạt động","Ngừng hoạt động"};
        for (String string : trangThai) {
            cbo_TrangThai.addItem(string);
        }
        
        cbo_MaNhanVien.addItem("Chọn mã nhân viên");
        List<NhanVienModel> nhanVienModels = NhanVienModel.getNhanVien("Select * from nhanvien",null);
        for (NhanVienModel nhanVienModel : nhanVienModels) {
            cbo_MaNhanVien.addItem(nhanVienModel.getMaNhanVien());
        }
    }
    
    private void setClick(){
        btn_Huy.addActionListener((e) -> {
            tf_MaTaiKhoan.setText("");
            tf_TenTaiKhoan.setText("");
            tf_MatKhau.setText("");
            cbo_ChucVu.setSelectedIndex(0);
            cbo_MaNhanVien.setSelectedIndex(0);
            cbo_TrangThai.setSelectedIndex(0);
        });
        
        cbo_ChucVu.addItemListener((e) -> {
            if(cbo_ChucVu.getSelectedItem().toString().equals("Admin")){
                cbo_MaNhanVien.setSelectedIndex(0);
                cbo_MaNhanVien.setEnabled(false);
            }
            else{
                cbo_MaNhanVien.setEnabled(true);
            }
        });
        
        btn_TimKiem.addActionListener((e) -> {
            String tenTaiKhoan = tf_TenTaiKhoan.getText().trim();
            String matKhau = tf_MatKhau.getText().trim();
            String chucVu = cbo_ChucVu.getSelectedItem().toString();
            String maNhanVien = cbo_MaNhanVien.getSelectedItem().toString();
            String trangThai = cbo_TrangThai.getSelectedItem().toString();
            
            StringBuilder sql = new StringBuilder("SELECT * FROM taikhoan WHERE 1=1 ");
            List<Object> params = new ArrayList<>();

            if (!tenTaiKhoan.isEmpty()) {
                sql.append(" AND tentaikhoan LIKE ? ");
                params.add("%" + tenTaiKhoan + "%");
            }

            if (!matKhau.isEmpty()) {
                sql.append(" AND matkhau LIKE ? ");
                params.add("%" + matKhau + "%");
            }

            if (!chucVu.equals("Chọn chức vụ")) {
                sql.append(" AND chucvu = ? ");
                params.add(chucVu);
            }

            if (!maNhanVien.equals("Chọn mã nhân viên")) {
                sql.append(" AND manhanvien = ? ");
                params.add(maNhanVien);
            }

            if (!trangThai.equals("Chọn trạng thái")) {
                sql.append(" AND trangthai = ? ");
                params.add(trangThai);
            }
            
            initTable(sql.toString(), params.toArray());
        });
        
        btn_Them.addActionListener((e) -> {
                String tenTaiKhoan = tf_TenTaiKhoan.getText().trim();
                String matKhau = tf_MatKhau.getText().trim();
                String chucVu = cbo_ChucVu.getSelectedItem().toString();
                String maNhanVien = cbo_MaNhanVien.getSelectedItem().toString();
                String trangThai = cbo_TrangThai.getSelectedItem().toString();
                
                if(tenTaiKhoan.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (matKhau.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (chucVu.equals("Chọn chức vụ")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (maNhanVien.equals("Chọn mã nhân viên") && !chucVu.equals("Admin")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn mã nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (chucVu.equals("Admin")) {
                    maNhanVien = null;
                }
                
                if (trangThai.equals("Chọn trạng thái")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO taikhoan (tentaikhoan, matkhau, manhanvien, chucvu, trangthai) VALUES (?,?,?,?,?)";
                List<Object> params = new ArrayList<>();
                params.add(tenTaiKhoan);
                params.add(matKhau);
                params.add(maNhanVien);
                params.add(chucVu);
                params.add(trangThai);
                
                int row = ConnectDB.write(sql, params.toArray());
                if(row>0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    initTable("Select * from taikhoan", null);
                    btn_Huy.doClick();
                }
                else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
        });
        
        tb_TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {  // double click
                    int row = tb_TaiKhoan.getSelectedRow(); // dòng đang chọn
                    if (row != -1) {
                        // Lấy dữ liệu từ dòng
                        String maTaiKhoan = tb_TaiKhoan.getValueAt(row, 0).toString(); // cột 0
                        String tenTaiKhoan = tb_TaiKhoan.getValueAt(row, 1).toString();
                        String matKhau = tb_TaiKhoan.getValueAt(row, 2).toString();
                        Object maNhanVien = tb_TaiKhoan.getValueAt(row, 3);
                        String chucVu = tb_TaiKhoan.getValueAt(row, 4).toString();
                        String trangThai = tb_TaiKhoan.getValueAt(row, 5).toString(); 
                        
                        tf_MaTaiKhoan.setText(maTaiKhoan);
                        tf_TenTaiKhoan.setText(tenTaiKhoan);
                        tf_MatKhau.setText(matKhau);
                        
                        if(maNhanVien != null)cbo_MaNhanVien.setSelectedItem(maNhanVien.toString());
                        else cbo_MaNhanVien.setSelectedIndex(0);
                        
                        cbo_ChucVu.setSelectedItem(chucVu);
                        cbo_TrangThai.setSelectedItem(trangThai);
                    }
                }
            }
        });

        
        btn_Sua.addActionListener((e) -> {
            String maTaiKhoan = tf_MaTaiKhoan.getText().trim();
            String tenTaiKhoan = tf_TenTaiKhoan.getText().trim();
            String matKhau = tf_MatKhau.getText().trim();
            String chucVu = cbo_ChucVu.getSelectedItem().toString();
            String maNhanVien = cbo_MaNhanVien.getSelectedItem().toString();
            String trangThai = cbo_TrangThai.getSelectedItem().toString();    
            
            if (maTaiKhoan.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(tenTaiKhoan.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (chucVu.equals("Chọn chức vụ")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (maNhanVien.equals("Chọn mã nhân viên") && !chucVu.equals("Admin")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (chucVu.equals("Admin")) {
                maNhanVien = null;
            }
            
            if (trangThai.equals("Chọn trạng thái")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
            }
            
            String sql = "UPDATE taikhoan SET tentaikhoan=?,matkhau=?,manhanvien=?, chucvu=?,trangthai=? where mataikhoan=?";
            List<Object> params = new ArrayList<>();
            params.add(tenTaiKhoan);
            params.add(matKhau);
            params.add(maNhanVien);
            params.add(chucVu);
            params.add(trangThai);
            params.add(maTaiKhoan);
            
            int row = ConnectDB.write(sql, params.toArray());
            if(row>0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("Select * from taikhoan", null);
                btn_Huy.doClick();
            }
            else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btn_Xoa.addActionListener((e) -> {
            String maTaiKhoan = tf_MaTaiKhoan.getText().trim();
            if (maTaiKhoan.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String sql = "Delete from taikhoan where mataikhoan=?";
            List<Object> params = new ArrayList<>();
            params.add(maTaiKhoan);
            
            int row = ConnectDB.write(sql, params.toArray());
            if(row>0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("Select * from taikhoan", null);
                btn_Huy.doClick();
            }
            else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
