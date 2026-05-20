/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btmenubar;

/**
 *
 * @author PhanAnh
 */

import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class bau2 extends JFrame {

    public bau2() {
        initFrame();
        initMenuBar();
        this.setVisible(true);
    }
    
    private void initFrame(){
        this.setSize(800,500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }
    
    private void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        jMenuBar.add(fileMenu); 
        fileMenu.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                System.out.println("File Menu Changed");
            }
        });
        
        this.setJMenuBar(jMenuBar);
    }
    
    public static void main(String[] args) {
        bau2 b = new bau2();
    }
}
