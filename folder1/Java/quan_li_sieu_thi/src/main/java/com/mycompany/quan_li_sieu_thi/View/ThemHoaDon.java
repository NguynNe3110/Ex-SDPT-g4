/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.ConnectDB.ConnectDB;
import com.mycompany.quan_li_sieu_thi.Model.MatHangModel;
import com.mycompany.quan_li_sieu_thi.PhienTaiKhoan;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class ThemHoaDon extends JFrame {
    private QLHoaDon parent;
    private JLabel lb_TenMatHang = new JLabel("Tên mặt hàng");
    private JLabel lb_DonGia = new JLabel("Đơn giá");
    private JLabel lb_SoLuong = new JLabel("Số lượng");
    private JLabel lb_TongTien = new JLabel("Tổng tiền: 0");
    
    private JComboBox<String> cbo_TenMatHang = new JComboBox<String>();
    private JTextField tf_DonGia = new JTextField(15);
    private JTextField tf_SoLuong = new JTextField(15);
    
    private JButton btn_Xoa = new JButton("Xóa");
    private JButton btn_Them = new JButton("Thêm");
    private JButton btn_Luu = new JButton("Lưu");
    private JButton btn_Huy = new JButton("Hủy");
    
    private JTable tb_cthd = new JTable();
    
    public ThemHoaDon(QLHoaDon parent) {
        this.parent = parent;
        initUI();
        initTable();
        initCombobox();
        setClick();
    }
    
    private void initUI(){
        this.setSize(1000,500);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,10));
        
        JPanel jp_form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_form.add(lb_TenMatHang);
        jp_form.add(cbo_TenMatHang);
        
        jp_form.add(lb_DonGia);
        jp_form.add(tf_DonGia);
        tf_DonGia.setEnabled(false);
        
        jp_form.add(lb_SoLuong);
        jp_form.add(tf_SoLuong);
        
        jp_form.add(btn_Xoa);
        jp_form.add(btn_Them);
        this.add(jp_form, BorderLayout.NORTH);
        
        JScrollPane scp = new JScrollPane(tb_cthd);
        this.add(scp,BorderLayout.CENTER);
        
        JPanel jp_btn = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jp_btn.add(btn_Huy);
        jp_btn.add(btn_Luu);
        jp_btn.add(lb_TongTien);
        this.add(jp_btn,BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    private void initTable(){
        String[] columns = {"Tên mặt hàng", "Đơn giá", "Số lượng",  "Thành tiền"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa tất cả ô
            }
        };
        
        tb_cthd.setModel(dtm);
    }
    
    private void initCombobox(){
        List<MatHangModel> matHangModels = MatHangModel.getMatHang("Select *from mathang", null);
        for (MatHangModel matHangModel : matHangModels) {
            cbo_TenMatHang.addItem(matHangModel.getTenMatHang());
        }
    }
    
    private void setClick(){
        btn_Huy.addActionListener((e) -> {
            this.dispose();
        });
        
        cbo_TenMatHang.addActionListener((e) -> {
            String tenMatHang = (String) cbo_TenMatHang.getSelectedItem();
            List<MatHangModel> matHangModels = MatHangModel.getMatHang("Select *from mathang where tenmathang = '" + tenMatHang + "'", null);
            tf_DonGia.setText(matHangModels.get(0).getDonGiaBan().toString());
        });
        
        btn_Them.addActionListener((e) -> {
            String tenMatHang = (String) cbo_TenMatHang.getSelectedItem();
            String donGiaStr = tf_DonGia.getText();
            String soLuongStr = tf_SoLuong.getText().trim();
            
            if (soLuongStr.isEmpty() || donGiaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ số lượng và đơn giá");
                return;
            }

            Integer soLuong = Integer.parseInt(soLuongStr);
            Integer donGia  = Integer.parseInt(donGiaStr);
            Integer thanhTien = soLuong * donGia;

            DefaultTableModel model = (DefaultTableModel) tb_cthd.getModel();
            model.addRow(new Object[]{
                tenMatHang,
                donGia,
                soLuong,
                thanhTien
            });

            Integer tongTien = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object value = model.getValueAt(i, 3); // cột thành tiền (index 3)
                tongTien += Integer.parseInt(value.toString());
            }
            lb_TongTien.setText("Tổng tiền: " + tongTien.toString());
            // clear ô số lượng
            tf_SoLuong.setText("");            
        });
        
        btn_Luu.addActionListener((e) -> {
            DefaultTableModel model = (DefaultTableModel) tb_cthd.getModel();

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa có mặt hàng nào trong hóa đơn!");
                return;
            }
            
            Integer maHoaDon = ConnectDB.writeReturnId("INSERT INTO hoadon(ngayxuat, mataikhoan) VALUES (CURDATE(), ?)", new Object[]{PhienTaiKhoan.getMaTaiKhoan()});

            int rows=0;
            for (int i = 0; i < model.getRowCount(); i++) {
                String tenMatHang = model.getValueAt(i, 0).toString();
                Integer soLuong = Integer.parseInt(model.getValueAt(i, 1).toString());
                Integer donGia = Integer.parseInt(model.getValueAt(i, 2).toString());
                
                List<MatHangModel> ds = MatHangModel.getMatHang("SELECT * FROM mathang WHERE tenmathang = ?",new Object[]{tenMatHang});
                Integer maMatHang = ds.get(0).getMaMatHang();
                
                Object[] params = {maHoaDon,maMatHang,soLuong,donGia};
                rows += ConnectDB.write("INSERT INTO chitiethoadon(mahoadon, mamathang, soluong, dongiaban) VALUES (?, ?, ?, ?)", params); 
            }
            if(rows==model.getRowCount()){
                JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công! Mã hóa đơn: " + maHoaDon);
                this.dispose();
                
                //cập nhật bảng hóa đơn
                parent.initTable("SELECT hd.*, tentaikhoan, sum(soluong*dongiaban) as 'tongtien'" +
                " from hoadon hd join chitiethoadon cthd on hd.mahoadon = cthd.mahoadon join taikhoan tk on tk.mataikhoan = hd.mataikhoan" +
                " group by hd.mahoadon", null);
            }
        });
        
        tb_cthd.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {  // double click
                    int row = tb_cthd.getSelectedRow(); // dòng đang chọn
                    if (row != -1) {
                        // Lấy dữ liệu từ dòng
                        String tenMatHang = tb_cthd.getValueAt(row, 0).toString(); // cột 0
                        String donGia = tb_cthd.getValueAt(row, 1).toString();
                        String soLuong = tb_cthd.getValueAt(row, 2).toString();
                        
                        cbo_TenMatHang.setSelectedItem(tenMatHang);
                        tf_DonGia.setText(donGia);
                        tf_SoLuong.setText(soLuong);
                    }
                }
            }
        });
        
        btn_Xoa.addActionListener((e) -> {
            DefaultTableModel model = (DefaultTableModel) tb_cthd.getModel();
            String tenMatHang = (String) cbo_TenMatHang.getSelectedItem();
            for (int i = 0; i < model.getRowCount(); i++) {
                if(model.getValueAt(i, 0) == tenMatHang) model.removeRow(i);
            }
            
            //tính lại tổng tiền
            Integer tongTien = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object value = model.getValueAt(i, 3); // cột thành tiền (index 3)
                tongTien += Integer.parseInt(value.toString());
            }
            lb_TongTien.setText("Tổng tiền: " + tongTien.toString());
        });
    }
}
