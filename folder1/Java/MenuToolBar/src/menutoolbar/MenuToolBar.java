/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menutoolbar;


import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 *
 * @author PhanAnh
 */
public class MenuToolBar extends JFrame {

    public MenuToolBar(){
        initFrame();
        initMenuBar();
        initToolBar();
        this.setVisible(true);
    }
    
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }
    
    private void initFrame(){
        this.setSize(800,500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }
    
    private void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(initFileMenu());
        jMenuBar.add(initEditMenu());
        jMenuBar.add(initViewMenu());
        jMenuBar.add(initNavigateMenu());
        this.setJMenuBar(jMenuBar);
    }
    
    private JMenu initFileMenu(){
        ImageIcon iconNewProject1 = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\newproject.png");
        iconNewProject1 = resizeIcon(iconNewProject1, 16, 16);
        JMenuItem j1 = new JMenuItem("New Project",iconNewProject1);
        j1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
    
        ImageIcon iconNewProject2 = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\newfile.png");
        iconNewProject2 = resizeIcon(iconNewProject2, 16, 16);
        JMenuItem j2 = new JMenuItem("New File",iconNewProject2);
        j2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
        
        ImageIcon iconNewProject3 = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\openproject.png");
        iconNewProject3 = resizeIcon(iconNewProject3, 16, 16);
        JMenuItem j3 = new JMenuItem("Open Project",iconNewProject3);
        
        JMenu j4 = new JMenu("Open Recent Project");
        JMenuItem j17 = new JMenuItem("Javaproject");
        j4.add(j17);
        
        JMenuItem j5 = new JMenuItem("Close Project");
        JMenuItem j6 = new JMenuItem("Close All Project");
        JMenuItem j7 = new JMenuItem("Open File");
        JMenu j8 = new JMenu("Open Recent File");
        JMenuItem j9 = new JMenuItem("Project Group");
        JMenuItem j10 = new JMenuItem("Project Properties");
        
        JMenu j11 = new JMenu("Import Project");
        JMenuItem j12 = new JMenuItem("Eclipse Project");
        JMenuItem j13 = new JMenuItem("Resychorize Project");
        JMenuItem j14 = new JMenuItem("From Zip...");
        j11.add(j12);
        j11.add(j13);
        j11.add(j14);
        
        JMenu j15 = new JMenu("Export Project");
        JMenuItem j16 = new JMenuItem("To Zip...");
        j15.add(j16);
        
        JMenu jMenu = new JMenu("File");
        jMenu.add(j1);
        jMenu.add(j2);
        jMenu.addSeparator();
        jMenu.add(j3);
        jMenu.add(j4);
        jMenu.add(j5);
        jMenu.add(j6);
        jMenu.add(j7);
        jMenu.add(j8);
        jMenu.addSeparator();
        jMenu.add(j9);
        jMenu.add(j10);
        jMenu.add(j11);
        jMenu.add(j15);
                
        return jMenu;
    }
    
    private JMenu initEditMenu(){
        JMenuItem j1 = new JMenuItem("Undo");
        JMenuItem j2 = new JMenuItem("Redo");
        JMenuItem j3 = new JMenuItem("Cut");
        JMenuItem j4 = new JMenuItem("Copy");
        JMenuItem j5 = new JMenuItem("Paste");
        JMenuItem j6 = new JMenuItem("Paste Fomat");
        JMenuItem j7 = new JMenuItem("Paste from History");
        JMenuItem j8 = new JMenuItem("Paste Lines");
        JMenuItem j9 = new JMenuItem("Delete");
        JMenuItem j10 = new JMenuItem("Select All");
        JMenuItem j11 = new JMenuItem("Select Identifiter");
        
        JMenu jMenu = new JMenu("Edit");
        jMenu.add(j1);
        jMenu.add(j2);
        jMenu.addSeparator();
        jMenu.add(j3);
        jMenu.add(j4);
        jMenu.add(j5);
        jMenu.add(j6);
        jMenu.add(j7);
        jMenu.add(j8);
        jMenu.add(j9);
        jMenu.add(j10);
        jMenu.add(j11);
        
        return jMenu;
    }
    
    private JMenu initViewMenu(){
        JMenu j1 = new JMenu("Editor");
        JMenuItem j2 = new JMenuItem("Source");
        JMenuItem j3 = new JMenuItem("History");
        j1.add(j2);
        j1.add(j3);
        
        JMenu j4 = new JMenu("Split");
        JMenuItem j5 = new JMenuItem("Vertically");
        JMenuItem j6 = new JMenuItem("Horizontally");
        JMenuItem j7 = new JMenuItem("Clear");
        j4.add(j5);
        j4.add(j6);
        j4.add(j7);

        JMenuItem j8 = new JMenuItem("IDE Log");
        JMenuItem j9 = new JMenuItem("Show Editor Bar");
        JMenuItem j10 = new JMenuItem("Show Lines Numbers");
        JMenuItem j11 = new JMenuItem("Show Non-printable Character");
        JMenuItem j12 = new JMenuItem("Show Breadcrumbs");
        
        JMenu jMenu = new JMenu("View");
        jMenu.add(j1);
        jMenu.add(j4);
        jMenu.addSeparator();
        jMenu.add(j8);
        jMenu.addSeparator();
        jMenu.add(j9);
        jMenu.add(j10);
        jMenu.add(j11);
        jMenu.add(j12);
        
        return jMenu;
    }
    
    private JMenu initNavigateMenu(){
        JMenuItem j1 = new JMenuItem("Go to File");
        j1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        
        JMenuItem j2 = new JMenuItem("Go to Type");
        JMenuItem j3 = new JMenuItem("Go to Symbol");
        JMenuItem j4 = new JMenuItem("Spring Bean");
        JMenuItem j5 = new JMenuItem("Go to Test");
        JMenuItem j6 = new JMenuItem("Go to Previous Window");
        JMenuItem j7 = new JMenuItem("Go to Source");
        JMenuItem j8 = new JMenuItem("Go to Declaration");
        JMenuItem j9 = new JMenuItem("Go to Super Implementation");
        JMenuItem j10 = new JMenuItem("Back");
        JMenuItem j11 = new JMenuItem("Forward");
        
        JMenu jMenu = new JMenu("Navigate");
        jMenu.add(j1);
        jMenu.add(j2);
        jMenu.add(j3);
        jMenu.add(j4);
        jMenu.add(j5);
        jMenu.add(j6);
        jMenu.addSeparator();
        jMenu.add(j7);
        jMenu.add(j8);
        jMenu.add(j9);
        jMenu.addSeparator();
        jMenu.add(j10);
        jMenu.add(j11);
        
        return jMenu;
    }
    

    
    private void initToolBar(){
        ImageIcon iconNewFile = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\newfile.png");
        iconNewFile = resizeIcon(iconNewFile, 30, 30);
        JButton btn_NewFile = new JButton(iconNewFile);
        btn_NewFile.setToolTipText("New File ...... Crtl + N");
        
        ImageIcon iconNewProject = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\newproject.png");
        iconNewProject = resizeIcon(iconNewProject, 30, 30);
        JButton btn_NewProject = new JButton(iconNewProject);
        btn_NewProject.setToolTipText("New Project ...... Crtl + Shift + N");
        
        ImageIcon iconOpenProject = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\openproject.png");
        iconOpenProject = resizeIcon(iconOpenProject, 30, 30);
        JButton btn_OpenProject = new JButton(iconOpenProject);
        btn_OpenProject.setToolTipText("Open Project ...... Crtl + Shift + O");
        
        ImageIcon iconUndo = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\undo.png");
        iconUndo = resizeIcon(iconUndo, 30, 30);
        JButton btn_Undo = new JButton(iconUndo);
        btn_Undo.setToolTipText("Undo");
        
        ImageIcon iconRedo = new ImageIcon("C:\\Users\\PhanAnh\\Desktop\\Java\\photo\\redo.png");
        iconRedo = resizeIcon(iconRedo, 30, 30);
        JButton btn_Redo = new JButton(iconRedo);
        btn_Redo.setToolTipText("Redo");
        
        String[] items1 = {"<default config>","customize"};
        JComboBox<String> cb1 = new JComboBox<>(items1);
        cb1.setToolTipText("Set Project Configuration");
        
        
        JToolBar jTB = new JToolBar();
        jTB.setOrientation(JToolBar.HORIZONTAL);
        jTB.setFloatable(false);
        jTB.add(btn_NewFile);
        jTB.add(btn_NewProject);
        jTB.add(btn_OpenProject);
        jTB.add(btn_Undo);
        jTB.add(btn_Redo);
        jTB.add(cb1);
        
        this.add(jTB,BorderLayout.NORTH);
    }
    
    public static void main(String[] args) {
        MenuToolBar m = new MenuToolBar();
    }
}
