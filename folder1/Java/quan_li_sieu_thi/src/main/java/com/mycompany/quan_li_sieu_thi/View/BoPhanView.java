/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import com.mycompany.quan_li_sieu_thi.Model.BoPhanModel;
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
public class BoPhanView extends JPanel {

    private JLabel lb_MaBoPhan = new JLabel("Mã bộ phận");
    private JLabel lb_TenBoPhan = new JLabel("Tên bộ phận");
    
    
    private JTextField tf_MaBoPhan = new JTextField(15);
    private JTextField tf_TenBoPhan = new JTextField(15);
    
    
    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Sua = new JButton("Sửa");
    private JButton btn_Xoa = new JButton("Xoá");
    private JButton btn_Huy = new JButton("Hủy");
    
    private JTable tb_BoPhan = new JTable();

    public BoPhanView() {
        initFrame();
        initTable("SELECT * FROM BoPhan", null);
        setClick();
    }
    
    private void initFrame() {
        this.setLayout(new BorderLayout(10,10));
        
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_form.add(lb_MaBoPhan);
        jp_form.add(tf_MaBoPhan);
        tf_MaBoPhan.setEditable(false);
        
        jp_form.add(lb_TenBoPhan);
        jp_form.add(tf_TenBoPhan);

        this.add(jp_form, BorderLayout.NORTH);
        
        JScrollPane scp = new JScrollPane(tb_BoPhan);
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
        String[] columns = {"Mã Bộ Phận", "Tên Bộ Phận"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        List<BoPhanModel> BoPhanModels = BoPhanModel.getBoPhan(sql, params);
        for (BoPhanModel dm : BoPhanModels) {
            dtm.addRow(new Object[]{
                dm.getMaBoPhan(),
                dm.getTenBoPhan(),
            });
        }
        tb_BoPhan.setModel(dtm);
    }

    private void setClick(){

        tb_BoPhan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = tb_BoPhan.getSelectedRow();
                    if (row != -1) {
                        tf_MaBoPhan.setText(tb_BoPhan.getValueAt(row, 0).toString());
                        tf_TenBoPhan.setText(tb_BoPhan.getValueAt(row, 1).toString());
                    }
                }
            }
        });

        btn_TimKiem.addActionListener(e -> {
            String tenBoPhan = tf_TenBoPhan.getText().trim();

            StringBuilder sql = new StringBuilder("SELECT * FROM bophan WHERE 1=1 ");
            List<Object> params = new ArrayList<>();

            if (!tenBoPhan.isEmpty()) {
                sql.append(" AND tenBoPhan LIKE ? ");
                params.add("%" + tenBoPhan + "%");
            }

            initTable(sql.toString(), params.toArray());
        });

        btn_Them.addActionListener(e -> {
            String tenBoPhan = tf_TenBoPhan.getText().trim();

            if (tenBoPhan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bộ phận", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO BoPhan (tenBoPhan) VALUES (?)";
            Object[] params = {tenBoPhan};

            int row = ConnectDB.write(sql, params);
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("SELECT * FROM bophan", null);
                btn_Huy.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_Sua.addActionListener(e -> {
            String maBoPhan = tf_MaBoPhan.getText().trim();
            String tenBoPhan = tf_TenBoPhan.getText().trim();

            if (maBoPhan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bộ phận cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Integer int_maBoPhan = Integer.parseInt(maBoPhan);

            if (tenBoPhan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bộ phận", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "UPDATE bophan SET tenbophan=? WHERE mabophan=?";
            Object[] params = {tenBoPhan, int_maBoPhan};

            int row = ConnectDB.write(sql, params);
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("SELECT * FROM bophan", null);
                btn_Huy.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btn_Xoa.addActionListener(e -> {
            String maBoPhan = tf_MaBoPhan.getText().trim();
            String tenBoPhan = tf_TenBoPhan.getText().trim();

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xoá danh mục này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM bophan WHERE mabophan=?";
                Object[] params = { maBoPhan };
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
            tf_MaBoPhan.setText("");
            tf_TenBoPhan.setText("");
            initTable("Select * from bophan", null);
        });
    }

//    public static void main(String[] args) {
//        JFrame f = new JFrame("Test BoPhanView");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setSize(800, 500);
//        f.add(new BoPhanView());
//        f.setLocationRelativeTo(null);
//        f.setVisible(true);
//    }
}
