/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import com.mycompany.quan_li_sieu_thi.Model.NhaCungCapModel;
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
public class QLNhaCungCap extends JPanel {
    private JLabel lb_MaNCC = new JLabel("Mã nhà cung cấp:");
    private JLabel lb_TenNCC = new JLabel("Tên nhà cung cấp:");
    private JLabel lb_MaSothue = new JLabel("Mã số thuế:");
    private JLabel lb_Sdt = new JLabel("Số điện thoại:");
    private JLabel lb_Diachi = new JLabel("Địa chỉ:");
    private JLabel lb_Ghichu = new JLabel("Ghi chú:");
    
    private JTextField tf_MaNCC = new JTextField(15);
    private JTextField tf_TenNCC = new JTextField(15);
    private JTextField tf_MaSothue = new JTextField(15);
    private JTextField tf_Sdt = new JTextField(15);
    private JTextField tf_Diachi = new JTextField(15);
    private JTextField tf_Ghichu = new JTextField(15);

    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Sua = new JButton("Sửa");
    private JButton btn_Xoa = new JButton("Xóa");
    private JButton btn_Huy = new JButton("Hủy");
    
    private JTable tb_NCC=new JTable();
    
    public QLNhaCungCap(){
        initUI();
        initTable("Select * from nhacungcap", null);
        setClick();
    }
    
    private void initUI() {
        this.setLayout(new BorderLayout(10, 10));

        // ======= FORM PANEL (NORTH) =======
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        jp_form.add(lb_MaNCC);
        jp_form.add(tf_MaNCC);
        tf_MaNCC.setEditable(false);

        jp_form.add(lb_TenNCC);
        jp_form.add(tf_TenNCC);

        jp_form.add(lb_MaSothue);
        jp_form.add(tf_MaSothue);

        jp_form.add(lb_Sdt);
        jp_form.add(tf_Sdt);

        jp_form.add(lb_Diachi);
        jp_form.add(tf_Diachi);

        jp_form.add(lb_Ghichu);
        jp_form.add(tf_Ghichu);

        this.add(jp_form, BorderLayout.NORTH);

        // ======= BUTTON + SEARCH PANEL (CENTER) =======
        JPanel jp_btn = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        jp_btn.add(btn_TimKiem);
        jp_btn.add(btn_Them);
        jp_btn.add(btn_Sua);
        jp_btn.add(btn_Xoa);
        jp_btn.add(btn_Huy);

        this.add(jp_btn, BorderLayout.CENTER);

        // ======= TABLE PANEL (SOUTH) =======
        JScrollPane scp = new JScrollPane(tb_NCC);
        this.add(scp, BorderLayout.SOUTH);
    }

    private void initTable(String sql, Object[] params){
        String[] columns = {"Mã NCC", "Tên NCC", "Mã số thuế", "Số điện thoại", "Địa chỉ", "Ghi chú"};

        DefaultTableModel dtm = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa dữ liệu trong bảng
            }
        };

        try {
            List<NhaCungCapModel> nccModels = NhaCungCapModel.getNCC(sql, params);

            for (NhaCungCapModel ncc : nccModels) {
                dtm.addRow(new Object[]{
                    ncc.getMaNhaCungCap(),
                    ncc.getTenNhaCungCap(),
                    ncc.getMaSoThue(),
                    ncc.getSoDienThoai(),
                    ncc.getDiaChi(),
                    ncc.getGhiChu()
                });
            }

            tb_NCC.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu nhà cung cấp!");
        }
    }
    
private void setClick() {

    // ===== HỦY (clear form) =====
    btn_Huy.addActionListener((e) -> {
        tf_MaNCC.setText("");
        tf_TenNCC.setText("");
        tf_MaSothue.setText("");
        tf_Sdt.setText("");
        tf_Diachi.setText("");
        tf_Ghichu.setText("");
    });

    // ===== TÌM KIẾM =====
    btn_TimKiem.addActionListener((e) -> {

        String maNCC = tf_MaNCC.getText().trim();
        String tenNCC = tf_TenNCC.getText().trim();
        String maSoThue = tf_MaSothue.getText().trim();
        String sdt = tf_Sdt.getText().trim();
        String diaChi = tf_Diachi.getText().trim();
        String ghiChu = tf_Ghichu.getText().trim();

        StringBuilder sql = new StringBuilder("SELECT * FROM nhacungcap WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (!maNCC.isEmpty()) {
            sql.append(" AND manhacungcap = ? ");
            params.add(maNCC);
        }

        if (!tenNCC.isEmpty()) {
            sql.append(" AND tennhacungcap LIKE ? ");
            params.add("%" + tenNCC + "%");
        }

        if (!maSoThue.isEmpty()) {
            sql.append(" AND masothue LIKE ? ");
            params.add("%" + maSoThue + "%");
        }

        if (!sdt.isEmpty()) {
            sql.append(" AND sodienthoai LIKE ? ");
            params.add("%" + sdt + "%");
        }

        if (!diaChi.isEmpty()) {
            sql.append(" AND diachi LIKE ? ");
            params.add("%" + diaChi + "%");
        }

        if (!ghiChu.isEmpty()) {
            sql.append(" AND ghichu LIKE ? ");
            params.add("%" + ghiChu + "%");
        }

        initTable(sql.toString(), params.toArray());
    });


    // ===== THÊM =====
    btn_Them.addActionListener((e) -> {

        String tenNCC = tf_TenNCC.getText().trim();
        String maSoThue = tf_MaSothue.getText().trim();
        String sdt = tf_Sdt.getText().trim();
        String diaChi = tf_Diachi.getText().trim();
        String ghiChu = tf_Ghichu.getText().trim();

        if (tenNCC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhà cung cấp!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (maSoThue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã số thuế!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO nhacungcap (tennhacungcap, masothue, sodienthoai, diachi, ghichu) VALUES (?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(tenNCC);
        params.add(maSoThue);
        params.add(sdt);
        params.add(diaChi);
        params.add(ghiChu);

        int row = ConnectDB.write(sql, params.toArray());
        if (row > 0) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            initTable("SELECT * FROM nhacungcap",null);
            btn_Huy.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    });

    // ===== DOUBLE CLICK TABLE: ĐỔ DỮ LIỆU LÊN FORM =====
    tb_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (e.getClickCount() == 2) {
                int row = tb_NCC.getSelectedRow();
                if (row != -1) {
                    tf_MaNCC.setText(tb_NCC.getValueAt(row, 0).toString());
                    tf_TenNCC.setText(tb_NCC.getValueAt(row, 1).toString());
                    tf_MaSothue.setText(tb_NCC.getValueAt(row, 2).toString());
                    tf_Sdt.setText(tb_NCC.getValueAt(row, 3).toString());
                    tf_Diachi.setText(tb_NCC.getValueAt(row, 4).toString());

                    Object gc = tb_NCC.getValueAt(row, 5);
                    tf_Ghichu.setText(gc != null ? gc.toString() : "");
                }
            }
        }
    });

    // ===== SỬA =====
    btn_Sua.addActionListener((e) -> {

        String maNCC = tf_MaNCC.getText().trim();
        String tenNCC = tf_TenNCC.getText().trim();
        String maSoThue = tf_MaSothue.getText().trim();
        String sdt = tf_Sdt.getText().trim();
        String diaChi = tf_Diachi.getText().trim();
        String ghiChu = tf_Ghichu.getText().trim();

        if (maNCC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE nhacungcap SET tennhacungcap=?, masothue=?, sodienthoai=?, diachi=?, ghichu=? WHERE manhacungcap=?";
        List<Object> params = new ArrayList<>();
        params.add(tenNCC);
        params.add(maSoThue);
        params.add(sdt);
        params.add(diaChi);
        params.add(ghiChu);
        params.add(maNCC);

        int row = ConnectDB.write(sql, params.toArray());
        if (row > 0) {
            JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            initTable("SELECT * FROM nhacungcap",null);
            btn_Huy.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    });

    // ===== XÓA =====
    btn_Xoa.addActionListener((e) -> {

        String maNCC = tf_MaNCC.getText().trim();

        if (maNCC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhà cung cấp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM nhacungcap WHERE manhacungcap=?";
        List<Object> params = new ArrayList<>();
        params.add(maNCC);

        int row = ConnectDB.write(sql, params.toArray());
        if (row > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            initTable("SELECT * FROM nhacungcap",null);
            btn_Huy.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    });
}

}
