/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quan_li_sieu_thi.View;

import com.mycompany.quan_li_sieu_thi.AddEditMatHangForm;
import com.mycompany.quan_li_sieu_thi.Model.MatHangModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author TUF
 */
public class QLMatHang extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTimKiem;
    private JComboBox<String> cboDanhMuc;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem;
    private MatHangModel service;
    private Map<Integer, String> danhMucMap;
    
    public QLMatHang() {
        setPreferredSize(new Dimension(1100, 500));
        service = new MatHangModel();
        initComponents();
        loadData();
        loadComboboxData();
    }
    
    private void initComponents() {
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel - Search
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        
        topPanel.add(new JLabel("Từ khóa:"));
        txtTimKiem = new JTextField(20);
        topPanel.add(txtTimKiem);
        
        topPanel.add(new JLabel("Danh mục:"));
        cboDanhMuc = new JComboBox<>();
        cboDanhMuc.setPreferredSize(new Dimension(150, 25));
        topPanel.add(cboDanhMuc);
  
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addActionListener(e -> searchMatHang());
        topPanel.add(btnTimKiem);
        
        // Center panel - Table
        String[] columns = {"Mã MH", "Tên mặt hàng", "Số lượng", "Giá Bán", 
                            "Danh mục","Ghi chú"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Set header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        
        // Add row selection listener
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                enableButtons();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách mặt hàng"));
        
        // Bottom panel - Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnThem = new JButton("Thêm mới");
        btnThem.addActionListener(e -> showAddEditForm(null));
        
        btnSua = new JButton("Sửa");
        btnSua.setEnabled(false);
        btnSua.addActionListener(e -> showEditForm());
        
        btnXoa = new JButton("Xóa");
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(e -> deleteMatHang());
        
        for (JButton btn : new JButton[]{btnThem, btnSua, btnXoa}) {
            btn.setFont(new Font("Arial", Font.BOLD, 12));
        }
        
        bottomPanel.add(btnThem);
        bottomPanel.add(btnSua);
        bottomPanel.add(btnXoa);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.EAST);
        
        add(mainPanel);
        
        txtTimKiem.addActionListener(e -> searchMatHang());
}
    private void loadComboboxData() {
        // Load danh mục
        cboDanhMuc.removeAllItems();
        danhMucMap = service.getDanhMuc();
        for (String value : danhMucMap.values()) {
            cboDanhMuc.addItem(value);
        }
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<MatHangModel> list;
        list = service.getAllMatHang();
        
        for (MatHangModel mh : list) {
            Object[] row = {
                mh.getMaMatHang(),
                mh.getTenMatHang(),
                mh.getSoLuong(),
                String.format("%,d VNĐ", mh.getDonGiaBan()),
                service.getTenDanhMuc(mh.getMaDanhMuc()),
                mh.getGhiChu()
            };
            tableModel.addRow(row);
        }
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }
    
    private void searchMatHang() {
        String keyword = txtTimKiem.getText().trim();
        int maDanhMuc = getKeyFromValue(danhMucMap, cboDanhMuc.getSelectedItem().toString());
        
        tableModel.setRowCount(0);
        List<MatHangModel> list = service.searchMatHang(keyword, maDanhMuc);
        
        for (MatHangModel mh : list) {
            Object[] row = {
                mh.getMaMatHang(),
                mh.getTenMatHang(),
                mh.getSoLuong(),
                String.format("%,d VNĐ", mh.getDonGiaBan()),
                service.getTenDanhMuc(mh.getMaDanhMuc()),
                mh.getGhiChu()
            };
            tableModel.addRow(row);
        }
    }
    
    private void enableButtons() {
        int selectedRow = table.getSelectedRow();
        boolean hasSelection = selectedRow >= 0;
        
        btnSua.setEnabled(hasSelection);
        btnXoa.setEnabled(hasSelection);
//        btnNhapHang.setEnabled(hasSelection);
//        btnXuatHang.setEnabled(hasSelection);
    }
    
    private int getSelectedMaMH() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            return (int) tableModel.getValueAt(row, 0);
        }
        return -1;
    }
    
    private void showAddEditForm(MatHangModel mh) {
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddEditMatHangForm form = new AddEditMatHangForm(owner, this, mh);
        form.setVisible(true);
    }
    
    private void showEditForm() {
        int maMH = getSelectedMaMH();
        if (maMH > 0) {
            // Tìm mặt hàng trong danh sách
            List<MatHangModel> list = service.getAllMatHang();
            for (MatHangModel mh : list) {
                if (mh.getMaMatHang() == maMH) {
                    showAddEditForm(mh);
                    break;
                }
            }
        }
    }
    
    private void deleteMatHang() {
        int maMH = getSelectedMaMH();
        if (maMH > 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa mặt hàng này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (service.deleteMatHang(maMH)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadData();
                }
            }
        }
    }
    
    private int getKeyFromValue(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }
    
    public void refreshData() {
        loadData();
    }
}
