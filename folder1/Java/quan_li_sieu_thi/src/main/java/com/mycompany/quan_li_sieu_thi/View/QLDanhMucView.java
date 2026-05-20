/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import com.mycompany.quan_li_sieu_thi.Model.DanhMucModel;
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
public class QLDanhMucView extends JPanel {

    private JLabel lb_MaDanhMuc = new JLabel("Mã danh mục");
    private JLabel lb_TenDanhMuc = new JLabel("Tên danh mục");
    private JLabel lb_GhiChu = new JLabel("Ghi chú");
    
    private JTextField tf_MaDanhMuc = new JTextField(15);
    private JTextField tf_TenDanhMuc = new JTextField(15);
    private JTextField tf_GhiChu = new JTextField(15);

    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Sua = new JButton("Sửa");
    private JButton btn_Xoa = new JButton("Xoá");
    private JButton btn_Huy = new JButton("Hủy");
    
    private JTable tb_DanhMuc = new JTable();

    public QLDanhMucView() {
        initFrame();
        initTable("SELECT * FROM danhmuc", null);
        setClick();
    }
    
    private void initFrame() {
        this.setLayout(new BorderLayout(10,10));
        
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_form.add(lb_MaDanhMuc);
        jp_form.add(tf_MaDanhMuc);
        tf_MaDanhMuc.setEditable(false);
        
        jp_form.add(lb_TenDanhMuc);
        jp_form.add(tf_TenDanhMuc);

        jp_form.add(lb_GhiChu);
        jp_form.add(tf_GhiChu);
        tf_GhiChu.setCaretPosition(0);


        this.add(jp_form, BorderLayout.NORTH);
        
        JScrollPane scp = new JScrollPane(tb_DanhMuc);
        this.add(scp, BorderLayout.CENTER);
        
        JPanel jp_btn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jp_btn.add(btn_TimKiem);
        jp_btn.add(btn_Them);
        jp_btn.add(btn_Sua);
        jp_btn.add(btn_Xoa);
        jp_btn.add(btn_Huy);
        this.add(jp_btn, BorderLayout.SOUTH);
    }
    
    private void initTable(String sql, Object[] params){
        String[] columns = {"Mã Danh Mục", "Tên Danh Mục", "Ghi chú"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        List<DanhMucModel> danhMucModels = DanhMucModel.getDanhMuc(sql, params);
        for (DanhMucModel dm : danhMucModels) {
            dtm.addRow(new Object[]{
                dm.getMaDanhMuc(),
                dm.getTenDanhMuc(),
                dm.getGhiChu()
            });
        }
        tb_DanhMuc.setModel(dtm);
    }

    private void setClick(){

        tb_DanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = tb_DanhMuc.getSelectedRow();
                    if (row != -1) {
                        tf_MaDanhMuc.setText(tb_DanhMuc.getValueAt(row, 0).toString());
                        tf_TenDanhMuc.setText(tb_DanhMuc.getValueAt(row, 1).toString());
                        tf_GhiChu.setText(tb_DanhMuc.getValueAt(row, 2).toString());
                        tf_GhiChu.setCaretPosition(0);

                    }
                }
            }
        });

        btn_TimKiem.addActionListener(e -> {
            String tenDanhMuc = tf_TenDanhMuc.getText().trim();
            String ghiChu = tf_GhiChu.getText().trim();

            StringBuilder sql = new StringBuilder("SELECT * FROM danhmuc WHERE 1=1 ");
            List<Object> params = new ArrayList<>();

            if (!tenDanhMuc.isEmpty()) {
                sql.append(" AND tendanhmuc LIKE ? ");
                params.add("%" + tenDanhMuc + "%");
            }

            if (!ghiChu.isEmpty()) {
                sql.append(" AND ghichu LIKE ? ");
                params.add("%" + ghiChu + "%");
            }

            initTable(sql.toString(), params.toArray());
        });

        btn_Them.addActionListener(e -> {
            String tenDanhMuc = tf_TenDanhMuc.getText().trim();
            String ghiChu = tf_GhiChu.getText().trim();

            if (tenDanhMuc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên danh mục", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO danhmuc (tendanhmuc, ghichu) VALUES (?,?)";
            Object[] params = {tenDanhMuc, ghiChu};

            int row = ConnectDB.write(sql, params);
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("SELECT * FROM danhmuc", null);
                btn_Huy.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_Sua.addActionListener(e -> {
            String maDanhMuc = tf_MaDanhMuc.getText().trim();
            String tenDanhMuc = tf_TenDanhMuc.getText().trim();
            String ghiChu = tf_GhiChu.getText().trim();

            if (maDanhMuc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn danh mục cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (tenDanhMuc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên danh mục", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "UPDATE danhmuc SET tendanhmuc=?, ghichu=? WHERE madanhmuc=?";
            Object[] params = {tenDanhMuc, ghiChu, maDanhMuc};

            int row = ConnectDB.write(sql, params);
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("SELECT * FROM danhmuc", null);
                btn_Huy.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btn_Xoa.addActionListener(e -> {
            String maDanhMuc = tf_MaDanhMuc.getText().trim();
            String tenDanhMuc = tf_TenDanhMuc.getText().trim();
            String ghiChu = tf_GhiChu.getText().trim();

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xoá danh mục này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM danhmuc WHERE madanhmuc=?";
                Object[] params = { maDanhMuc };
                int row = ConnectDB.write(sql, params);
                if (row > 0) {
                    JOptionPane.showMessageDialog(this, "Xoá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    btn_Huy.doClick(); // Reset form và load lại bảng
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btn_Huy.addActionListener(e -> {
            tf_MaDanhMuc.setText("");
            tf_TenDanhMuc.setText("");
            tf_GhiChu.setText("");
            initTable("Select * from danhmuc", null);
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Test QLDanhMucView");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 500);
        f.add(new QLDanhMucView());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
