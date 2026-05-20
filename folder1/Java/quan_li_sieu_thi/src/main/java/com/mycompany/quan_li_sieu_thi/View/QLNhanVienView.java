package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import com.mycompany.quan_li_sieu_thi.Model.NhanVienModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class QLNhanVienView extends JPanel {

    private JLabel lb_MaNV = new JLabel("Mã nhân viên");
    private JLabel lb_TenNV = new JLabel("Tên nhân viên");
    private JLabel lb_NgaySinh = new JLabel("Ngày sinh");
    private JLabel lb_SDT = new JLabel("SĐT");
    private JLabel lb_GioiTinh = new JLabel("Giới tính");
    private JLabel lb_QueQuan = new JLabel("Quê quán");
    private JLabel lb_MaBoPhan = new JLabel("Mã bộ phận");
    private JLabel lb_TrangThai = new JLabel("Trang thái");

    private JTextField tf_MaNV = new JTextField();
    private JTextField tf_TenNV = new JTextField();
    private JTextField tf_NgaySinh = new JTextField();
    private JTextField tf_SDT = new JTextField();
    private JTextField tf_GioiTinh = new JTextField();
    private JTextField tf_QueQuan = new JTextField();
    private JTextField tf_MaBoPhan = new JTextField();
    private JTextField tf_TrangThai = new JTextField();

    private JButton btn_TimKiem = new JButton("Tìm kiếm");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Sua = new JButton("Sửa");
    private JButton btn_Xoa = new JButton("Xoá");
    private JButton btn_Huy = new JButton("Hủy");

    private JTable tb_NhanVien = new JTable();

    public QLNhanVienView() {
        initFrame();
        initTable("SELECT * FROM nhanvien", null);
        setClick();
    }

    private void initFrame() {
        setLayout(new BorderLayout(10, 10));

        JPanel jp_form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y;
        jp_form.add(lb_MaNV, gbc);
        gbc.gridx = 1;
        jp_form.add(tf_MaNV, gbc);
//        tf_MaNV.setEditable(false);

        gbc.gridx = 2;
        jp_form.add(lb_TenNV, gbc);
        gbc.gridx = 3;
        jp_form.add(tf_TenNV, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        jp_form.add(lb_NgaySinh, gbc);
        gbc.gridx = 1;
        jp_form.add(tf_NgaySinh, gbc);

        gbc.gridx = 2;
        jp_form.add(lb_SDT, gbc);
        gbc.gridx = 3;
        jp_form.add(tf_SDT, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        jp_form.add(lb_GioiTinh, gbc);
        gbc.gridx = 1;
        jp_form.add(tf_GioiTinh, gbc);

        gbc.gridx = 2;
        jp_form.add(lb_QueQuan, gbc);
        gbc.gridx = 3;
        jp_form.add(tf_QueQuan, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        jp_form.add(lb_MaBoPhan, gbc);
        gbc.gridx = 1;
        jp_form.add(tf_MaBoPhan, gbc);
        
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        jp_form.add(lb_TrangThai, gbc);
        gbc.gridx = 1;
        jp_form.add(tf_TrangThai, gbc);

        add(jp_form, BorderLayout.NORTH);

        JScrollPane scp = new JScrollPane(tb_NhanVien);
        add(scp, BorderLayout.CENTER);

        JPanel jp_btn = new JPanel();
        jp_btn.add(btn_TimKiem);
        jp_btn.add(btn_Them);
        jp_btn.add(btn_Sua);
        jp_btn.add(btn_Xoa);
        jp_btn.add(btn_Huy);
        add(jp_btn, BorderLayout.SOUTH);
    }

    private void initTable(String sql, Object[] params) {
        String[] columns = {
            "Mã NV", "Tên NV", "Ngày sinh", "SĐT",
            "Giới tính", "Quê quán", "Mã bộ phận", "Trạng thái"
        };

        DefaultTableModel dtm = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<NhanVienModel> list = NhanVienModel.getNhanVien(sql, params);
        for (NhanVienModel nv : list) {
            dtm.addRow(new Object[]{
                nv.getMaNhanVien(),
                nv.getTenNhanVien(),
                nv.getNgaySinh() != null ? nv.getNgaySinh().format(df) : "",
                nv.getSoDienThoai(),
                nv.getGioiTinh(),
                nv.getQueQuan(),
                nv.getMaBoPhan(),
                nv.getTrangThai()
            });
        }

        tb_NhanVien.setModel(dtm);
    }

    private void setClick() {

        tb_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tb_NhanVien.getSelectedRow();
                if (row != -1) {
                    tf_MaNV.setText(tb_NhanVien.getValueAt(row, 0).toString());
                    tf_TenNV.setText(tb_NhanVien.getValueAt(row, 1).toString());
                    tf_NgaySinh.setText(tb_NhanVien.getValueAt(row, 2).toString());
                    tf_SDT.setText(tb_NhanVien.getValueAt(row, 3).toString());
                    tf_GioiTinh.setText(tb_NhanVien.getValueAt(row, 4).toString());
                    tf_QueQuan.setText(tb_NhanVien.getValueAt(row, 5).toString());
                    tf_MaBoPhan.setText(tb_NhanVien.getValueAt(row, 6).toString());
                    tf_TrangThai.setText(tb_NhanVien.getValueAt(row, 7).toString());
                }
            }
        });
        
        btn_TimKiem.addActionListener(e -> {
            
            String maNhanVien = tf_MaNV.getText().trim();
            String tenNhanVien = tf_TenNV.getText().trim();
            String ngaySinh = tf_NgaySinh.getText().trim();
            String sdt = tf_SDT.getText().trim();
            String gioiTinh = tf_GioiTinh.getText().trim();
            String queQuan = tf_QueQuan.getText().trim();
            String maBoPhan = tf_MaBoPhan.getText().trim();
            String trangThai = tf_TrangThai.getText().trim();

            StringBuilder sql = new StringBuilder("SELECT * FROM nhanvien WHERE 1=1 ");
            List<Object> params = new ArrayList<>();

            if (!maNhanVien.isEmpty()) {
                sql.append(" AND manhanvien LIKE ? ");
                params.add("%" + maNhanVien + "%");
            }

            if (!tenNhanVien.isEmpty()) {
                sql.append(" AND tennhanvien LIKE ? ");
                params.add("%" + tenNhanVien + "%");
            }
            
//            if (!ngaySinh.isEmpty()) {
//                sql.append(" AND ngaysinh = ? ");
//                params.add(ngaySinh);
//            }
//            
            if (!ngaySinh.isEmpty()) {
                sql.append(" AND ngaysinh LIKE ? ");
                params.add("%" + ngaySinh + "%");
            }

            if (!sdt.isEmpty()) {
                sql.append(" AND sodienthoai LIKE ? ");
                params.add("%" + sdt + "%");
            }

            if (!gioiTinh.isEmpty()) {
                sql.append(" AND gioitinh = ? ");
                params.add(gioiTinh);
            }
            
            if (!queQuan.isEmpty()) {
                sql.append(" AND quequan LIKE ? ");
                params.add("%" + queQuan + "%");
            }
            
            if (!maBoPhan.isEmpty()) {
                sql.append(" AND mabophan LIKE ? ");
                params.add("%" + maBoPhan + "%");
            }
            
            if (!trangThai.isEmpty()) {
                sql.append(" AND trangthai LIKE ? ");
                params.add("%" + trangThai + "%");
            }

            initTable(sql.toString(), params.toArray());
        });
        
        btn_Them.addActionListener(e -> {
            String maNhanVien = tf_MaNV.getText().trim();
            String tenNhanVien = tf_TenNV.getText().trim();
            String ngaySinh = tf_NgaySinh.getText().trim();
            String sdt = tf_SDT.getText().trim();
            String gioiTinh = tf_GioiTinh.getText().trim();
            String queQuan = tf_QueQuan.getText().trim();
            String maBoPhan = tf_MaBoPhan.getText().trim();
            String trangThai = tf_TrangThai.getText().trim();
            
            
            LocalDate ngaySinhDate;
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            try {
                ngaySinhDate = LocalDate.parse(ngaySinh, df);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Ngày sinh phải đúng định dạng dd/MM/yyyy",
                    "Lỗi dữ liệu",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            if (maNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (tenNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (ngaySinh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Ngày sinh", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Số điện thoại", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (gioiTinh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Giới tính", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (queQuan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Quê quán", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (maBoPhan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã bộ phận", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (trangThai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Trạng thái", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO nhanvien (manhanvien, tennhanvien, ngaysinh, sodienthoai, gioitinh, quequan, mabophan, trangthai) "
                            + "VALUES (?,?,?,?,?,?,?,?)";
            Object[] params = {
                maNhanVien,
                tenNhanVien,
                java.sql.Date.valueOf(ngaySinhDate),
                sdt,
                gioiTinh,
                queQuan,
                maBoPhan,
                trangThai
            };


            int row = ConnectDB.write(sql, params);
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("SELECT * FROM nhanvien", null);
                btn_Huy.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btn_Sua.addActionListener(e -> {
            String maNhanVien = tf_MaNV.getText().trim();
            String tenNhanVien = tf_TenNV.getText().trim();
            String ngaySinh = tf_NgaySinh.getText().trim();
            String sdt = tf_SDT.getText().trim();
            String gioiTinh = tf_GioiTinh.getText().trim();
            String queQuan = tf_QueQuan.getText().trim();
            String maBoPhan = tf_MaBoPhan.getText().trim();
            String trangThai = tf_TrangThai.getText().trim();

            LocalDate ngaySinhDate;
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            try {
                ngaySinhDate = LocalDate.parse(ngaySinh, df);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Ngày sinh phải đúng định dạng dd/MM/yyyy",
                    "Lỗi dữ liệu",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            if (maNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (tenNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên nhân viên", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (ngaySinh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Ngày sinh", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Số điện thoại", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (gioiTinh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Giới tính", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (queQuan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Quê quán", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (maBoPhan.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã bộ phận", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (trangThai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Trạng thái", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "UPDATE nhanvien SET manhanvien=?, tennhanvien=?, ngaysinh=?, sodienthoai=?, gioitinh=?,"
                    + " quequan=?, mabophan=?, trangthai=? WHERE manhanvien=?";
            Object[] params = {
                maNhanVien,
                tenNhanVien,
                java.sql.Date.valueOf(ngaySinhDate),
                sdt,
                gioiTinh,
                queQuan,
                maBoPhan,
                trangThai,
                maNhanVien
            };

            int row = ConnectDB.write(sql, params);
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                initTable("SELECT * FROM nhanvien", null);
                btn_Huy.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btn_Xoa.addActionListener(e -> {
            String maNhanVien = tf_MaNV.getText().trim();

            if (maNhanVien.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Mã nhân viên để xoá", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xoá nhân viên này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM nhanvien WHERE manhanvien=?";
                Object[] params = { maNhanVien };
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
            tf_MaNV.setText("");
            tf_TenNV.setText("");
            tf_NgaySinh.setText("");
            tf_SDT.setText("");
            tf_GioiTinh.setText("");
            tf_QueQuan.setText("");
            tf_MaBoPhan.setText("");
            tf_TrangThai.setText("");
            initTable("Select * from nhanvien", null);
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("QL Nhân Viên");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 500);
        f.add(new QLNhanVienView());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
