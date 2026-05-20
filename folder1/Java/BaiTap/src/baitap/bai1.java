/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baitap;

import java.util.Random;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class bai1 extends JFrame {

    public static void main(String[] args) {
        String so1 = JOptionPane.showInputDialog("Nhập số 1");
        String so2 = JOptionPane.showInputDialog("Nhập số 2");
        int so_1 = Integer.parseInt(so1);
        int so_2 = Integer.parseInt(so2);
        
        Random rd = new Random();
        int so_3 = rd.nextInt(so_2 - so_1 + 1) + so_1;
        JOptionPane.showMessageDialog(null, so_3, "Số ngẫu nhiên",1);
    }
    
}
