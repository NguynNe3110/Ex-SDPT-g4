/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi;

/**
 *
 * @author TUF
 */
import com.mycompany.quan_li_sieu_thi.Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDonNhapHangForm extends JDialog {
    private DonNhapHangModel controller;
    private List<ChiTietDonNhapHangModel> chiTietList;
    private DefaultTableModel tableModel;
    private JComboBox<String> cboMatHang;
    private Map<String, Integer> mapMatHang = new HashMap<>();
    private JComboBox<String> cboNhaCungCap;
    private Map<String, Integer> mapNhaCungCap = new HashMap<>();
    private JSpinner spnSoLuong;
    private JTextField txtDonGia,txtMaTK;
    private boolean success = false;
    private JTable table;
    
    public AddDonNhapHangForm(JFrame parent, DonNhapHangModel controller) {
        super(parent, "Thêm đơn nhập hàng", true);
        this.controller = controller;
        this.chiTietList = new ArrayList<>();
        initComponents();
        loadMatHang();
        loadNhaCungCap();
        String tenTaiKhoan = PhienTaiKhoan.getTenTaiKhoan();
    }
    
    private void initComponents() {
        setSize(800, 500);
        setLocationRelativeTo(getParent());
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng"));
        
        infoPanel.add(new JLabel("Nhà cung cấp:"));
        cboNhaCungCap = new JComboBox<>();
        infoPanel.add(cboNhaCungCap);
        
        infoPanel.add(new JLabel("Mã tài khoản:"));
        txtMaTK = new JTextField();
        txtMaTK.setEditable(false);
        infoPanel.add(txtMaTK);
        String tenTaiKhoan = PhienTaiKhoan.getTenTaiKhoan();
        Integer maTK = controller.getMaTaiKhoanByTen(tenTaiKhoan);
        if (maTK != null) {
            txtMaTK.setText(String.valueOf(maTK));
        } else {
            JOptionPane.showMessageDialog(this, "Không lấy được mã tài khoản!");
            dispose();
        }
        
        infoPanel.add(new JLabel("Ghi chú:"));
        JTextField txtGhiChu = new JTextField("Nhập hàng mới");
        infoPanel.add(txtGhiChu);
        
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        
        // Chi tiết panel
        JPanel detailPanel = new JPanel(new BorderLayout(5, 5));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết mặt hàng"));
        
        // Add item panel
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.add(new JLabel("Mặt hàng:"));
        cboMatHang = new JComboBox<>();
        cboMatHang.setPreferredSize(new Dimension(200, 25));
        addPanel.add(cboMatHang);
        
        addPanel.add(new JLabel("Số lượng:"));
        spnSoLuong = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spnSoLuong.setPreferredSize(new Dimension(80, 25));
        addPanel.add(spnSoLuong);
        
        addPanel.add(new JLabel("Đơn giá:"));
        txtDonGia = new JTextField(10);
        txtDonGia.setText("0");
        addPanel.add(txtDonGia);
        
        
        JButton btnDelete = new JButton("Xoá");
        btnDelete.addActionListener(e -> deleteItem());
        addPanel.add(btnDelete);
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> addItem());
        addPanel.add(btnAdd);
        
        detailPanel.add(addPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Mã MH", "Tên mặt hàng", "Số lượng", "Đơn giá", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        detailPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Total
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("Tổng tiền: 0 VNĐ"));
        detailPanel.add(totalPanel, BorderLayout.SOUTH);
        
        mainPanel.add(detailPanel, BorderLayout.CENTER);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            try {
                String tenNCC = (String) cboNhaCungCap.getSelectedItem();
                int maNCC = mapNhaCungCap.get(tenNCC);
                String ghiChu = txtGhiChu.getText();
               
                // Tạo đơn
                DonNhapHangModel don = new DonNhapHangModel();
                don.setMaNhaCungCap(maNCC);
                don.setMaTaiKhoan(maTK);
                don.setGhiChu(ghiChu);
                don.setNgayNhapHang(LocalDate.now());
                
                int maDon = controller.addDonNhapHang(don);
                if (maDon > 0) {
                    // Thêm chi tiết
                    for (ChiTietDonNhapHangModel ct : chiTietList) {
                        ct.setMaDonNhapHang(maDon);
                        controller.addChiTietDon(ct);
                    }
                    success = true;
                    JOptionPane.showMessageDialog(this, "Thêm đơn thành công! Mã đơn: " + maDon);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm đơn thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!");
            }
        });
        buttonPanel.add(btnSave);
        
        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(e -> dispose());
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadNhaCungCap() {
        List<NhaCungCapModel> list = controller.getAllNhaCungCap();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        mapNhaCungCap.clear();

        for (NhaCungCapModel ncc : list) {
            model.addElement(ncc.getTenNhaCungCap());
            mapNhaCungCap.put(ncc.getTenNhaCungCap(), ncc.getMaNhaCungCap());
        }
        cboNhaCungCap.setModel(model);
    }

    
    private void loadMatHang() {
        List<MatHangModel> list = controller.getAllMatHang();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        mapMatHang.clear();

        for (MatHangModel mh : list) {
            model.addElement(mh.getTenMatHang());
            mapMatHang.put(mh.getTenMatHang(), mh.getMaMatHang()); 
        }
        cboMatHang.setModel(model);
    }
    
    private void addItem() {
        String tenMH = (String) cboMatHang.getSelectedItem();
        if (tenMH == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mặt hàng!");
            return;
        }

        int soLuong = (int) spnSoLuong.getValue();
        int donGiaNhap = Integer.parseInt(txtDonGia.getText());

        Integer maMH = mapMatHang.get(tenMH);
        if (maMH == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã mặt hàng!");
            return;
        }

        ChiTietDonNhapHangModel ct = new ChiTietDonNhapHangModel();
        ct.setMaMatHang(maMH);
        ct.setSoLuong(soLuong);
        ct.setDonGiaNhap(donGiaNhap);

        chiTietList.add(ct);

        int thanhTien = soLuong * donGiaNhap;
        tableModel.addRow(new Object[]{
            maMH,
            tenMH,
            soLuong,
            String.format("%,d", donGiaNhap),
            String.format("%,d", thanhTien)
        });

        updateTotal();
    }
    private void deleteItem() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mặt hàng cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn xóa mặt hàng này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        // Xóa khỏi danh sách chi tiết
        chiTietList.remove(selectedRow);

        // Xóa khỏi bảng
        tableModel.removeRow(selectedRow);

        // Cập nhật tổng tiền
        updateTotal();
    }


    
    private void updateTotal() {
        double total = 0;
        for (ChiTietDonNhapHangModel ct : chiTietList) {
            total += ct.getSoLuong() * ct.getDonGiaNhap();
        }
        updateTotalLabel(total);
    }

    // Phương thức mới để cập nhật label tổng tiền
    private void updateTotalLabel(double total) {
        // Lấy panel chi tiết
        JPanel detailPanel = (JPanel) ((BorderLayout) getContentPane().getLayout())
                .getLayoutComponent(BorderLayout.CENTER);

        if (detailPanel != null) {
            // Tìm panel tổng tiền (panel cuối cùng trong BorderLayout.SOUTH của detailPanel)
            Component southComp = ((BorderLayout) detailPanel.getLayout())
                    .getLayoutComponent(BorderLayout.SOUTH);

            if (southComp instanceof JPanel) {
                JPanel totalPanel = (JPanel) southComp;
                // Tìm label tổng tiền
                for (Component comp : totalPanel.getComponents()) {
                    if (comp instanceof JLabel) {
                        JLabel label = (JLabel) comp;
                        if (label.getText().startsWith("Tổng tiền:")) {
                            label.setText("Tổng tiền: " + String.format("%,.0f VNĐ", total));
                            return;
                        }
                    }
                }
            }
        }
    }
    
}
