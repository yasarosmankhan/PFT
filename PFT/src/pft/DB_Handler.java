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
    

    public static void executeSavingGoals(String s1) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
//                stat.executeUpdate("drop table if exists people;");
                stat.executeUpdate("create table people (Customerid INTEGER PRIMARY KEY AUTOINCREMENT, name, occupation);");
//                  if statement to update or insert if table already exists ***ADD HERE***
//                stat.executeUpdate("ALTER TABLE people AUTO_INCREMENT = value;");

                PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?, ?);");
        
                prep.setString(2, s1);
                prep.setString(3, "politics");
                prep.addBatch();

                prep.setString(2, "CS");
                prep.setString(3, "Yasar");
                prep.addBatch();

                prep.setString(2, "smartypants");
                prep.setString(3, "FAM");
                prep.addBatch();

                conn.setAutoCommit(false);
                prep.executeBatch();
                conn.setAutoCommit(true);

                try (ResultSet rs = stat.executeQuery("select * from people;")) {
                    while (rs.next()) {
                        System.out.println("name = " + rs.getString("name"));
                        System.out.println("job = " + rs.getString("occupation"));
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void transactionsEntry(double Budget, double Salary, double otherIn, double rent,
            double contracts, double travel, double otherOut, double flexiTravel, double flexiTravelOther,
            double miscellaneous, String date) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
                stat.executeUpdate("drop table if exists Transactions;");
                stat.executeUpdate("create table Transactions (TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, Date, Expense, Amount);");
                PreparedStatement prep = conn.prepareStatement("insert into Transactions values (?,?,?,?);");

                prep.setString(2, date);
                prep.setString(3, "Budget");
                prep.setDouble(4, Budget);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "Salary");
                prep.setDouble(4, Salary);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "OtherIn");
                prep.setDouble(4, otherIn);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "Rent");
                prep.setDouble(4, rent);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "Contracts");
                prep.setDouble(4, contracts);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "Travel");
                prep.setDouble(4, travel);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "OtherOut");
                prep.setDouble(4, otherOut);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "FlexiTravel");
                prep.setDouble(4, flexiTravel);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "FlexiTravelOther");
                prep.setDouble(4, flexiTravelOther);
                prep.addBatch();
                prep.setString(2, date);
                prep.setString(3, "Miscellaneous");
                prep.setDouble(4, miscellaneous);
                prep.addBatch();
                //System.out.println(s1);
                conn.setAutoCommit(false);
                prep.executeBatch();
                conn.setAutoCommit(true);

                try (ResultSet rs = stat.executeQuery("select * from Transactions;")) {
                    while (rs.next()) {
                        System.out.println("Expense = " + rs.getString("Expense"));
                        System.out.println("Amount = " + rs.getString("Amount"));
                        System.out.println("Date = " + rs.getString("Date"));
                        System.out.println("________________________________________");
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
