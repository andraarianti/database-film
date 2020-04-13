package filmdb;

import com.mysql.jdbc.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ControllerFilm {
    
    koneksi koneksi;
    ModelFilm modelFilm;
    ViewFilm viewFilm;
    
    public ControllerFilm(ModelFilm modelFilm, ViewFilm viewFilm) {
    this.modelFilm = modelFilm;
    this.viewFilm = viewFilm;
    
    if(modelFilm.getBanyakData() != 0){
            String dataFilm[][] = modelFilm.readFilm();
            viewFilm.table.setModel((new JTable(dataFilm, viewFilm.namaKolom)).getModel());
            
        } else{
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
    
        viewFilm.btnAdd.addActionListener((ActionEvent e) -> {
            if(viewFilm.getjudul().equals("")
                    || viewFilm.getjudul().equals("")
                    || viewFilm.gettipe().equals("")
                    || viewFilm.getep().equals("")
                    || viewFilm.getgenre().equals("")
                    || viewFilm.getstatus().equals("")
                    || viewFilm.getrating().equals("")
                    ) {
                JOptionPane.showMessageDialog(null, "Field tdk boleh kosong");
            } else{
                
                String judul = viewFilm.getjudul();
                String tipe = viewFilm.gettipe();
                String ep = viewFilm.getep();
                String genre = viewFilm.getgenre();
                String status = viewFilm.getstatus();
                String rating = viewFilm.getrating();
                
                modelFilm.insertFilm(judul, tipe, ep, genre, status, rating);
                String dataFilm[][] = modelFilm.readFilm();
                viewFilm.table.setModel(new JTable(dataFilm, viewFilm.namaKolom).getModel());
            }
    });
        
    viewFilm.btnRefresh.addActionListener((ActionEvent e) -> {
        viewFilm.fjudul.setText("");
        viewFilm.fep.setText("");
        viewFilm.fgenre.setText("");
        viewFilm.ftipe.setText("");
        viewFilm.frating.setText("");
    });  
       
    viewFilm.btnDelete.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ex) {
            int baris = viewFilm.table.getSelectedRow();
            int kolom = viewFilm.table.getSelectedColumn();

            String dataterpilih = viewFilm.table.getValueAt(baris, 2).toString();
           
            System.out.println(dataterpilih);
            modelFilm.deleteFilm(dataterpilih);
            String dataFilm[][] = modelFilm.readFilm();
            viewFilm.table.setModel(new JTable(dataFilm, viewFilm.namaKolom).getModel());
        }
    });
    
    //search belum keluar hasil searching nya
    viewFilm.btnSearch.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int baris = viewFilm.table.getSelectedRow();
            int kolom = viewFilm.table.getSelectedColumn();
            
            String search = viewFilm.getsearch();
            System.out.println(search);
            modelFilm.searchFilm(search);
            String dataFilm[][] = modelFilm.readFilm();
            viewFilm.table.setModel(new JTable(dataFilm, viewFilm.namaKolom).getModel());
        }
    });  

    viewFilm.table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e){
            int baris = viewFilm.table.getSelectedRow();
            int kolom = viewFilm.table.getSelectedColumn();
            
            String judul = viewFilm.table.getValueAt(baris, 2).toString();
            viewFilm.fjudul.setText(judul);
            String tipe = viewFilm.table.getValueAt(baris, 3).toString();
            viewFilm.ftipe.setText(tipe);
            String ep = viewFilm.table.getValueAt(baris, 4).toString();
            viewFilm.fep.setText(ep);
            String genre = viewFilm.table.getValueAt(baris, 5).toString();
            viewFilm.fgenre.setText(genre);
            String status = viewFilm.table.getValueAt(baris, 6).toString();
            viewFilm.cmbStatus.setSelectedItem(status);
            String rating = viewFilm.table.getValueAt(baris, 7).toString();
            viewFilm.frating.setText(rating);
            
            viewFilm.btnUpdate.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e){ 
                    String judul = viewFilm.getjudul();
                    String tipe = viewFilm.gettipe();
                    String ep = viewFilm.getep();
                    String genre = viewFilm.getgenre();
                    String status = viewFilm.getstatus();
                    String rating = viewFilm.getrating();
                    modelFilm.updateFilm(judul, tipe, ep, genre, status,rating);
                
                String dataFilm[][]= modelFilm.readFilm();
                viewFilm.table.setModel(new JTable(dataFilm, viewFilm.namaKolom).getModel());

            }            
         });
      }
        
    });
    
      
    viewFilm.btnExit.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    }
}
