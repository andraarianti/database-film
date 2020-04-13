/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmdb;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ViewFilm extends JFrame {
       
    JLabel ljudul = new JLabel("Judul");
    JTextField fjudul = new JTextField();
    JLabel ltipe = new JLabel("Tipe");
    JTextField ftipe = new JTextField();
    JLabel lep = new JLabel("Episode");
    JTextField fep = new JTextField();
    JLabel lstatus = new JLabel("Status");
    String status[] = { "Selesai", "Belum" };
    JComboBox cmbStatus = new JComboBox(status);
    JLabel lgenre = new JLabel("Genre");
    JTextField fgenre = new JTextField();
    JLabel lrating = new JLabel("Rating");
    JTextField frating = new JTextField();
    
    JTextField fsearch = new JTextField();
    JButton btnSearch = new JButton("Search");
    
    JButton btnUpdate = new JButton("Update");
    JButton btnRefresh = new JButton("Refresh");
    JButton btnAdd = new JButton("Add");
    JButton btnDelete = new JButton("Delete");
    JButton btnExit = new JButton("Exit");
    
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"#","ID","Judul","Tipe","Episode","Genre","Status","Rating"};
    
    public ViewFilm(){
        tableModel = new DefaultTableModel(namaKolom,0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(800, 500);
        
         //TABEL
        add(scrollPane);
        scrollPane.setBounds(20, 20, 580, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollpane-nya vertikal
        
        add(ljudul);
        ljudul.setBounds(20, 250, 40, 20);
        add(fjudul);
        fjudul.setBounds(20, 270, 300, 20);
        add(ltipe);
        ltipe.setBounds(20, 290, 40, 20);
        add(ftipe);
        ftipe.setBounds(20, 310, 100, 20);
        add(lep);
        lep.setBounds(150, 290, 100, 20);
        add(fep);
        fep.setBounds(150, 310, 100, 20);
        add(lrating);
        lgenre.setBounds(20, 330, 40, 20);
        add(frating);
        fgenre.setBounds(20, 350, 100, 20);
        add(lstatus);
        lstatus.setBounds(150, 330, 100, 20);
        add(cmbStatus);
        cmbStatus.setBounds(150, 350, 100, 20);
        add(lgenre);
        lrating.setBounds(20, 370, 100, 20);
        add(fgenre);
        frating.setBounds(20, 390, 300, 20);
        
        add(fsearch);
        fsearch.setBounds(350, 270, 100, 20);
        add(btnSearch);
        btnSearch.setBounds(460, 270, 100, 20);
        add(btnRefresh);
        btnRefresh.setBounds(350, 390, 80, 20);
        add(btnAdd);
        btnAdd.setBounds(440, 390, 60, 20);
        add(btnDelete);
        btnDelete.setBounds(510, 390, 70, 20);
        add(btnUpdate);
        btnUpdate.setBounds(590, 390, 90, 20);   
        add(btnExit);
        btnExit.setBounds(690, 390, 70, 20);        
       }
 
     public String getjudul(){
         return fjudul.getText();
     }       
     public String gettipe(){
         return ftipe.getText();
     }
     public String getstatus(){
         return cmbStatus.getSelectedItem().toString();
     }
     public String getep(){
         return fep.getText();
     }
     public String getgenre(){
         return fgenre.getText();
     }
     public String getrating(){
         return frating.getText();
     }
      public String getsearch(){
         return fsearch.getText();
     }
}
