package filmdb;

import com.mysql.jdbc.PreparedStatement;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModelFilm {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/film";
    static final String USER = "root"; 
    static final String PASS = "";    
    
    Connection koneksi;
    Statement statement;
    
    public ModelFilm() {
      try{
          Class.forName(JDBC_DRIVER);
          koneksi = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
          System.out.println("Koneksi Berhasil");
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex.getMessage());
          System.out.println("Koneksi gagal");
      }
  }
    
    public void insertFilm(String judul, String tipe, String ep, String genre, String status, String rating){
        try{
           String query = "INSERT INTO `movie` (`judul`, `tipe`, `ep`,`genre`,`status`,`rating`) "
                   + "VALUES ('"+judul+"','"+tipe+"','"+ep+"','"+genre+"','"+status+"','"+rating+"')";
          statement = (Statement) koneksi.createStatement();
          statement.executeUpdate(query);
          System.out.println("Berhasil Ditambahkan");
          JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        }
        catch(SQLException | HeadlessException sql){
          System.out.println(sql.getMessage());
          JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public String[][] readFilm(){
        try{
          int jmlData = 0;
          String data[][] = new String[getBanyakData()][8];
          String query = "SELECT * from `movie`";
          ResultSet resultSet = statement.executeQuery(query);
          while(resultSet.next()){
              data[jmlData][0] = resultSet.getString("#");
              data[jmlData][1] = resultSet.getString("id");
              data[jmlData][2] = resultSet.getString("judul");
              data[jmlData][3] = resultSet.getString("tipe");
              data[jmlData][4] = resultSet.getString("ep");
              data[jmlData][5] = resultSet.getString("genre");
              data[jmlData][6] = resultSet.getString("status");
              data[jmlData][7] = resultSet.getString("rating");
              jmlData++;
          }
          return data;
          
      }catch(SQLException e){
          System.out.println(e.getMessage());
          System.out.println("SQL ERROR");
          return null;
      }
    }
    
    public int getBanyakData(){
      int jmlData = 0;
      try{
          statement = koneksi.createStatement();
          String query = "Select * from `movie`";
          ResultSet resultSet = statement.executeQuery(query);
          while(resultSet.next()){  
              jmlData++;
          }
          return jmlData;
        }
      catch(SQLException e){
          System.out.println(e.getMessage());
          System.out.println("SQL ERROR");
          return 0;
       }
    } 
      
    public void deleteFilm (String judul){
      try{
          String query = "DELETE FROM `movie` WHERE `judul` = '"+judul+"'";
          statement = koneksi.createStatement();
          statement.executeUpdate(query);
          JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
      }catch(SQLException sql){
          System.out.println(sql.getMessage());
      }
    }
    
     void searchFilm(String search) {
         try{
          String sql = "SELECT * FROM `movie` WHERE `judul` LIKE '"+search+"%'";
          statement = koneksi.createStatement();
          statement.executeQuery(sql);
          JOptionPane.showMessageDialog(null, "Berhasil DiCari");
      }catch(SQLException sql){
          System.out.println(sql.getMessage());
      }
    }
     
    void updateFilm(String judul, String tipe, String ep, String genre, String status, String rating){
        try{
            String sql = "UPDATE `movie` SET judul = '"+judul+"',tipe = '"+tipe+"', ep = '"+ep+"', "
                    + "genre = '"+genre+"', status = '"+status+"', rating = '"+rating+"'";
            java.sql.PreparedStatement pst = koneksi.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "berhasil disimpan");
        }catch(SQLException | HeadlessException e){
             JOptionPane.showMessageDialog(null, e);
        }    
    }
  }
    
