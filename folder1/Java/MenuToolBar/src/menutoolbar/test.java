/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menutoolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author PhanAnh
 */
public class test extends JFrame{
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

    public test(){
        initFrame();
        initMenuBar();
        this.setVisible(true);
    }
    
    private void initFrame(){
        this.setSize(450,200);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }
    
    private void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(initMenu("File", new ArrayList<>(Arrays.asList("New Project", "New File","Open Project","Open Recent Project","Close project"))));
        jMenuBar.add(initMenu("Edit", new ArrayList<>(Arrays.asList("Uno", "New File","Open Project","Open Recent Project","Close project"))));
        this.setJMenuBar(jMenuBar);
    }
    
    private JMenu initMenu(String tenMenu, List<String> tenMenuItem){
        JMenu jMenu = new JMenu(tenMenu);
        for (int n = 0; n < tenMenuItem.size(); n++) {
            JMenuItem item = new JMenuItem(tenMenuItem.get(n));
            jMenu.add(item);
        }
        return jMenu;
    }
    
    public static void main(String[] args) {
        test t = new test();
    }
}