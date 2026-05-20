/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi;

/**
 *
 * @author TUF
 */
import com.mycompany.quan_li_sieu_thi.Model.MatHangModel;
import com.mycompany.quan_li_sieu_thi.View.QLMatHang;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Map;

public class AddEditMatHangForm extends JDialog {
    private JTextField txtTenMH, txtdonGiaBan, txtSoLuong, txtMaDonNhap;
    private JComboBox<String> cboDanhMuc;
    private JTextArea txtGhiChu;
    private JButton btnSave, btnCancel;
    private MatHangModel matHang;
    private QLMatHang qlMatHang;
    private MatHangModel service;
    
    public AddEditMatHangForm(JFrame owner, QLMatHang qlMatHang, MatHangModel matHang) {
        super(owner, true);
        this.qlMatHang = qlMatHang;
        this.matHang = matHang;
        this.service = new MatHangModel();

        initComponents();
        if (matHang != null) {
            loadData();
        }
    }

    
    private void initComponents() {
        setTitle(matHang == null ? "Thêm mặt hàng mới" : "Sửa thông tin mặt hàng");
        setSize(500, 500);
        setLocationRelativeTo(getOwner());
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        // Tên mặt hàng
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tên mặt hàng *:"), gbc);
        txtTenMH = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(txtTenMH, gbc);
        
        // Danh mục
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Danh mục:"), gbc);
        cboDanhMuc = new JComboBox<>();
        // Load danh mục (bỏ item đầu tiên)
        service.getDanhMuc().forEach((k, v) -> {
            if (k > 0) cboDanhMuc.addItem(v);
        });
        gbc.gridx = 1;
        formPanel.add(cboDanhMuc, gbc);
       
        // Giá niêm yết
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Giá bán:"), gbc);
        txtdonGiaBan = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(txtdonGiaBan, gbc);
        
        // Số lượng
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Số lượng:"), gbc);
        txtSoLuong = new JTextField(10);
        txtSoLuong.setText("0");
        gbc.gridx = 1;
        formPanel.add(txtSoLuong, gbc);

        // Ghi chú
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Ghi chú:"), gbc);
        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtGhiChu);
        gbc.gridx = 1;
        formPanel.add(scrollPane, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");
        
        btnSave.addActionListener(e -> saveMatHang());
        btnCancel.addActionListener(e -> dispose());
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadData() {
        if (matHang != null) {
            txtTenMH.setText(matHang.getTenMatHang());
            txtdonGiaBan.setText(String.valueOf(matHang.getDonGiaBan()));
            txtSoLuong.setText(String.valueOf(matHang.getSoLuong()));
            // Set danh mục
            String tenDanhMuc = service.getTenDanhMuc(matHang.getMaDanhMuc());
            if (!tenDanhMuc.isEmpty()) {
                cboDanhMuc.setSelectedItem(tenDanhMuc);
            }
            
            txtGhiChu.setText(matHang.getGhiChu());
        }
    }
    
    private void saveMatHang() {
        // Validate
        if (txtTenMH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên mặt hàng!");
            txtTenMH.requestFocus();
            return;
        }
        
        try {
            MatHangModel mh = matHang != null ? matHang : new MatHangModel();
            
            mh.setTenMatHang(txtTenMH.getText().trim());
            mh.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
            mh.setDonGiaBan(Integer.parseInt(txtdonGiaBan.getText().trim()));
            mh.setMaDanhMuc(getSelectedId(cboDanhMuc, service.getDanhMuc()));
            mh.setGhiChu(txtGhiChu.getText().trim());
            
            boolean success;
            if (matHang == null) {
                success = service.addMatHang(mh);
            } else {
                success = service.updateMatHang(mh);
            }
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu thành công!");
                qlMatHang.refreshData();
                dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private int getSelectedId(JComboBox<String> combo, Map<Integer, String> map) {
        String selected = (String) combo.getSelectedItem();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(selected)) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
