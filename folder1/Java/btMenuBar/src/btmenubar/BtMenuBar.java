/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btmenubar;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author PhanAnh
 */
public class BtMenuBar extends JFrame {

    public BtMenuBar() {
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
        jMenuBar.add(initAMenu());
        jMenuBar.add(initAnotherMenu());
        this.setJMenuBar(jMenuBar);
    }
    
    private JMenu initAMenu(){
        JMenuItem j1 = new JMenuItem("A text-only menu item");
        j1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,InputEvent.ALT_DOWN_MASK));
        
        JMenuItem j2 = new JMenuItem("Border text and icon");
        JMenuItem j3 = new JMenuItem("");
        JRadioButtonMenuItem j4 = new JRadioButtonMenuItem("A radio button menu item");
        JRadioButtonMenuItem j5 = new JRadioButtonMenuItem("Another one");
        JCheckBoxMenuItem j6 = new JCheckBoxMenuItem("A check box menu item");
        JCheckBoxMenuItem j7 = new JCheckBoxMenuItem("Another one");
        
        JMenu j8 = new JMenu("A submenu");
        JMenuItem j9 = new JMenuItem("An item in the submenu");
        j9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,InputEvent.ALT_DOWN_MASK));
        JMenuItem j10 = new JMenuItem("Another item");
        j8.add(j9);
        j8.add(j10);
        
        JMenu jMenu = new JMenu("A Menu");
        jMenu.add(j1);
        jMenu.add(j2);
        jMenu.add(j3);
        jMenu.addSeparator();
        jMenu.add(j4);
        jMenu.add(j5);
        jMenu.addSeparator();
        jMenu.add(j6);
        jMenu.add(j7);
        jMenu.add(j8);

        return jMenu;
    }
    
    private JMenu initAnotherMenu(){
        JMenu jMenu = new JMenu("A another Menu");
        return jMenu;
    }

    public static void main(String[] args) {
        BtMenuBar b = new BtMenuBar();
    }
    
}
