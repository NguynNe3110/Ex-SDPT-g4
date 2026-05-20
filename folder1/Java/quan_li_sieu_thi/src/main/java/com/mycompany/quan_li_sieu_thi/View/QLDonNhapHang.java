/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

/**
 *
 * @author TUF
 */

import com.mycompany.quan_li_sieu_thi.View.AddDonNhapHangForm;
import com.mycompany.quan_li_sieu_thi.Model.*;
import com.mycompany.quan_li_sieu_thi.PhienTaiKhoan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QLDonNhapHang extends JPanel {
    private DonNhapHangModel controller;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtSearch;
    
    public QLDonNhapHang() {
        String chucVu = PhienTaiKhoan.getChucVu();
        setPreferredSize(new Dimension(1100, 500));
        controller = new DonNhapHangModel();
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Tìm kiếm:"));
        txtSearch = new JTextField(20);
        searchPanel.add(txtSearch);
        
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> search());
        searchPanel.add(btnSearch);
        
        JButton btnReload = new JButton("Làm mới");
        btnReload.addActionListener(e -> loadData());
        searchPanel.add(btnReload);
        
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Mã Đơn", "Ngày Nhập", "Tên NCC", "Tên TK", "Ghi Chú"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(870, 300));
        // Set width từng cột
        table.getColumnModel().getColumn(0).setPreferredWidth(80); 
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(250); 

        
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton btnAdd = new JButton("Thêm đơn mới");
        btnAdd.addActionListener(e -> openAddForm());
        buttonPanel.add(btnAdd);
        
        JButton btnDelete = new JButton("Xóa đơn");
        btnDelete.addActionListener(e -> deleteDon());
        buttonPanel.add(btnDelete);
        
        JButton btnDetail = new JButton("Xem chi tiết");
        btnDetail.addActionListener(e -> viewDetail());
        buttonPanel.add(btnDetail);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<DonNhapHangModel> list = controller.getAllDonNhapHang();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (DonNhapHangModel don : list) {
            
            Object[] row = {
                don.getMaDonNhapHang(),
                don.getNgayNhapHang().format(dtf), 
                controller.getTenNhaCungCap(don.getMaNhaCungCap()),
                controller.getTenTaiKhoan(don.getMaTaiKhoan()),
                don.getGhiChu()
            };
            tableModel.addRow(row);
        }
    }

    
    private void search() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadData();
            return;
        }
        tableModel.setRowCount(0);
        List<DonNhapHangModel> list = controller.searchDonNhapHang(keyword);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (DonNhapHangModel don : list) {
            Object[] row = {
                don.getMaDonNhapHang(),
                don.getNgayNhapHang().format(dtf),
                controller.getTenNhaCungCap(don.getMaNhaCungCap()),
                controller.getTenTaiKhoan(don.getMaTaiKhoan()),
                don.getGhiChu()
            };
            tableModel.addRow(row);
        }
    }


    private void openAddForm() {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddDonNhapHangForm dialog = new AddDonNhapHangForm(owner,controller);
        dialog.setVisible(true);
    }

    
    private void deleteDon() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int maDon = (int) tableModel.getValueAt(selectedRow, 0);
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa đơn " + maDon + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.deleteDonNhapHang(maDon)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn cần xóa!");
        }
    }
    
    private void viewDetail() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int maDon = (int) tableModel.getValueAt(selectedRow, 0);
            List<ChiTietDonNhapHangModel> chiTiet = controller.getChiTietDon(maDon);
            
            String[] columns = {"Mã MH", "Số lượng", "Đơn giá", "Thành tiền"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            
            double tongTien = 0;
            for (ChiTietDonNhapHangModel ct : chiTiet) {
                double thanhTien = ct.getSoLuong() * ct.getDonGiaNhap();
                tongTien += thanhTien;
                model.addRow(new Object[]{
                    controller.getTenMatHang(ct.getMaMatHang()),
                    ct.getSoLuong(),
                    String.format("%,d", ct.getDonGiaNhap()),
                    String.format("%,.0f", thanhTien)
                });
            }
            
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Chi tiết đơn " + maDon, JLabel.CENTER), BorderLayout.NORTH);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(new JLabel("Tổng tiền: " + String.format("%,.0f VNĐ", tongTien)), BorderLayout.SOUTH);
            
            JOptionPane.showMessageDialog(this, panel, "Chi tiết đơn nhập hàng", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
