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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Handler {
    
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);


    public static void getthestuff(String s1) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
//                stat.executeUpdate("drop table if exists people;");
//                stat.executeUpdate("create table people (Customerid, name, occupation);");
//                  if statement to update or insert if table already exists ***ADD HERE***
                PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?, ?);");

                int customerID = ID_GENERATOR.getAndIncrement();

                prep.setInt(1, customerID);
                prep.setString(2, s1);
                prep.setString(3, "politics");
                prep.addBatch();
                prep.setInt(1, customerID);
                prep.setString(2, "CS");
                prep.setString(3, "Yasar");
                prep.addBatch();
                prep.setInt(1, customerID++);
                prep.setString(2, "smartypants");
                prep.setString(3, "FAM");
                prep.addBatch();
                //System.out.println(s1);
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
//                stat.executeUpdate("drop table if exists Transactions;");
//                stat.executeUpdate("create table Transactions (Date, Expense, Amount);");
                PreparedStatement prep = conn.prepareStatement("insert into Transactions values (?,?,?);");

                
                prep.setString(1, date);
                prep.setString(2, "Budget");
                prep.setDouble(3, Budget);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "Salary");
                prep.setDouble(3, Salary);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "OtherIn");
                prep.setDouble(3, otherIn);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "Rent");
                prep.setDouble(3, rent);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "Contracts");
                prep.setDouble(3, contracts);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "Travel");
                prep.setDouble(3, travel);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "OtherOut");
                prep.setDouble(3, otherOut);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "FlexiTravel");
                prep.setDouble(3, flexiTravel);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "FlexiTravelOther");
                prep.setDouble(3, flexiTravelOther);
                prep.addBatch();
                prep.setString(1, date);
                prep.setString(2, "Miscellaneous");
                prep.setDouble(3, miscellaneous);
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
