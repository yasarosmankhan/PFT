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

    public static void getthestuff(String s1) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
                stat.executeUpdate("drop table if exists people;");
                stat.executeUpdate("create table people (name, occupation);");
                PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");

                prep.setString(1, s1);
                prep.setString(2, "politics");
                prep.addBatch();
                prep.setString(1, "Yasar");
                prep.setString(2, "CS");
                prep.addBatch();
                prep.setString(1, "Fam");
                prep.setString(2, "smartypants");
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
            double contracts, double travel, double otherOut, double flexiTravel,double flexiTravelOther, 
            double miscellaneous) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
                stat.executeUpdate("drop table if exists Transactions;");
                stat.executeUpdate("create table Transactions (Expense, Amount);");
                PreparedStatement prep = conn.prepareStatement("insert into Transactions values (?,?);");

                prep.setString(1, "Budget");
                prep.setDouble(2, Budget);
                prep.addBatch();
                prep.setString(1, "Salary");
                prep.setDouble(2, Salary);
                prep.addBatch();
                prep.setString(1, "OtherIn");
                prep.setDouble(2, otherIn);
                prep.addBatch();
                prep.setString(1, "Rent");
                prep.setDouble(2, rent);
                prep.addBatch();
                prep.setString(1, "Contracts");
                prep.setDouble(2, contracts);
                prep.addBatch();
                prep.setString(1, "Travel");
                prep.setDouble(2, travel);
                prep.addBatch();
                prep.setString(1, "OtherOut");
                prep.setDouble(2, otherOut);
                prep.addBatch();
                prep.setString(1, "FlexiTravel");
                prep.setDouble(2, flexiTravel);
                prep.addBatch();
                prep.setString(1, "FlexiTravelOther");
                prep.setDouble(2, flexiTravelOther);
                prep.addBatch();
                prep.setString(1, "Miscellaneous");
                prep.setDouble(2, miscellaneous);
                prep.addBatch();
                //System.out.println(s1);
                conn.setAutoCommit(false);
                prep.executeBatch();
                conn.setAutoCommit(true);

                try (ResultSet rs = stat.executeQuery("select * from Transactions;")) {
                    while (rs.next()) {
                        System.out.println("Expense = " + rs.getString("Expense"));
                        System.out.println("Amount = " + rs.getString("Amount"));
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
