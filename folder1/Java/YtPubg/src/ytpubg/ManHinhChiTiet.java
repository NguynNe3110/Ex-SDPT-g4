/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ytpubg;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;


/**
 *
 * @author PhanAnh
 */
public class ManHinhChiTiet extends JLabel {
    private final JLabel lb_TenTuyenThu = new JLabel();
    private final JLabel lb_DoiTuyen = new JLabel();
    private final JLabel lb_SoVideo = new JLabel();
    
    private final JList list_LinkKenh = new JList();

    public ManHinhChiTiet(String tenTuyenThu, String doiTuyen, String soVideo) throws SQLException {
        lb_TenTuyenThu.setText("Tên tuyển thủ: " + tenTuyenThu);
        lb_DoiTuyen.setText("Đội tuyển: " + doiTuyen);
        lb_SoVideo.setText("Số video: " + soVideo);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        
        this.add(lb_TenTuyenThu);
        this.add(lb_DoiTuyen);
        this.add(lb_SoVideo);
        
        this.add(list_LinkKenh);
        TranDL("Select LinkYT, LinkDY, LinkSL, LinkTW from TuyenThu tt join DoiTuyen dt on tt.MaDoiTuyen = dt.MaDoiTuyen "
                + "where TenTuyenThu = '" + tenTuyenThu + "' and TenDoiTuyen = '" + doiTuyen + "'");
        clickItemListOpenLink();
        
    }
    
    private void TranDL(String sql) throws SQLException{
        DataBase db = new DataBase();
        ResultSet rs = db.DocDL(sql);
        DefaultListModel<String> dlm = new DefaultListModel<>();
        while(rs.next()){
            dlm.addElement(rs.getString("LinkYT"));
            dlm.addElement(rs.getString("LinkDY"));
            dlm.addElement(rs.getString("LinkSL"));
            dlm.addElement(rs.getString("LinkTW"));
        }
        list_LinkKenh.setModel(dlm);
    }
    
    private void clickItemListOpenLink(){
        list_LinkKenh.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    int index = list_LinkKenh.getSelectedIndex();
                    System.out.println(index);
                    if(index != 1){
                        String url = (String) list_LinkKenh.getSelectedValue();
                        try {
                            openLink(url);
                        } catch (URISyntaxException | IOException ex) {
                            System.getLogger(ManHinhChiTiet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    }
                }
            } 
        });
    }
    
    private void openLink (String url) throws URISyntaxException, IOException{
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            Desktop.getDesktop().browse(new URI(url));
        }
        else{
            System.out.println("Hệ thống không hỗ trợ mở trình duyệt!");
        }
    }
}
