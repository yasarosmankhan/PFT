/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pft;

/**
 *
 * @author AlamMac
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Handler {
    
    public static void getthestuff(String s1)throws Exception{
    
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stat = conn.createStatement();
            stat.executeUpdate("drop table if exists people;");
            stat.executeUpdate("create table people (name, occupation);");
            PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");
            
            prep.setString(1, s1);
            prep.setString(2, "politics");
            prep.addBatch();
            prep.setString(1, "Time");
            prep.setString(2, "computers");
            prep.addBatch();
            prep.setString(1, "Fam");
            prep.setString(2, "smartypants");
            prep.addBatch();
            
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            
            ResultSet rs = stat.executeQuery("select * from people;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name"));
                System.out.println("job = " + rs.getString("occupation"));
            }
            
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    
        
    }
    
    
//        public static void main(String[] args) {
//        
//        try {
//            getthestuff();
//        } catch (Exception ex) {
//            Logger.getLogger(DB_Handler.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
//    }
    
}