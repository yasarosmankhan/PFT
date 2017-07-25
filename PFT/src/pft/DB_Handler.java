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
import javax.swing.JTextField;
import javax.swing.table.*;

public class DB_Handler {

    static int counter = 0;

    /**
     *
     * @param s1
     * @throws Exception
     * @savingGoalsEntry - database connection SQLite, for DROP/CREATE/UPDATE
     * FUNCTIONALITY DROP - remove any existing local db CREATE - start the db
     * from fresh, include the PRIMARY KEY with AUTOINCREMENT UPDATE - this is
     * used for updating current records but will need to be cross-checked
     * whether the record already exists
     */
    public static void savingGoalsEntry(String s1) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
//                stat.executeUpdate("drop table if exists people;");

                DatabaseMetaData md = conn.getMetaData();
                ResultSet rs1 = md.getTables(null, null, "people", null);

                if (rs1.next()) {
                    // Table exists
                    System.out.println("It's done nothing! :("); //**TEMP**
                } else {
                    // Table does not exist
                    stat.executeUpdate("create table people (Customerid INTEGER PRIMARY KEY AUTOINCREMENT, name, occupation);");
                }

                PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?, ?);"); //condition for placeholder INSERTION statement

                prep.setString(2, s1);
                prep.setString(3, "politics");
                prep.addBatch(); //batch insert values in columns defined

                prep.setString(2, "CS");
                prep.setString(3, "Yasar");
                prep.addBatch();

                prep.setString(2, "smartypants");
                prep.setString(3, "FAM");
                prep.addBatch();

                conn.setAutoCommit(false);
                prep.executeBatch();
                conn.setAutoCommit(true);

                try (ResultSet rs = stat.executeQuery("select * from people;")) { //bring all columns from the database table
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

    /**
     *
     * @param Budget
     * @param Salary
     * @param otherIn
     * @param rent
     * @param contracts
     * @param travel
     * @param otherOut
     * @param flexiTravel
     * @param flexiTravelOther
     * @param miscellaneous
     * @param date - Julian-day format
     * @throws Exception
     * @savingGoalsEntry - database connection SQLite, for DROP/CREATE/UPDATE
     * FUNCTIONALITY DROP - remove any existing local db CREATE - start the db
     * from fresh, include the PRIMARY KEY with AUTOINCREMENT UPDATE - this is
     * used for updating current records but will need to be cross-checked
     * whether the record already exists
     */
    public static void transactionsEntry(double Budget, double Salary, double otherIn, double rent,
            double contracts, double travel, double otherOut, double flexiTravel, double flexiTravelOther,
            double miscellaneous, String date) throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
//                stat.executeUpdate("drop table if exists Transactions;");
//                stat.executeUpdate("create table Transactions (TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, Date DATE, Expense, Amount);");

                DatabaseMetaData md = conn.getMetaData();
                ResultSet rs1 = md.getTables(null, null, "Transactions", null);

                if (rs1.next()) {
                    // Table exists
                    System.out.println("It's done nothing! :("); //**TEMP**
                } else {
                    // Table does not exist
                    stat.executeUpdate("create table Transactions (TransactionID INTEGER PRIMARY KEY AUTOINCREMENT, Date, Expense, Amount);");
                }

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
                conn.setAutoCommit(false);
                prep.executeBatch();
                conn.setAutoCommit(true);

                try (ResultSet rs = stat.executeQuery("select * from Transactions;")) {
                    while (rs.next()) {
                        System.out.println("Expense = " + rs.getString("Expense"));
                        System.out.println("Amount = " + rs.getString("Amount"));
                        System.out.println("Date = " + rs.getString("Date")); // when you want to read the date use getDate method, SQLite standard stores date in julianday
                        System.out.println("________________________________________");
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void getTransactions() throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
                DefaultTableModel model = (DefaultTableModel) MainClass.statementTable.getModel();
                model.setRowCount(0); //clear the table
                counter = 0;

                try (ResultSet rs = stat.executeQuery("select * from Transactions order by Date desc;")) {
                    while (rs.next()) {

                        model.insertRow(model.getRowCount(), new Object[]{rs.getString("Date"), rs.getString("Expense"), rs.getDouble("Amount"), "In/Out"}); //Insert new rows
                        MainClass.statementTable.getRowSorter().toggleSortOrder(0); //Sort the columns by default
                        counter++;

                    }
                }
                checkCount(counter);

            }
        } catch (SQLException sql) {
            System.out.println("System cannot get data");
        }

    }

    public static void transactionFilter() throws Exception {

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stat = conn.createStatement();
                DefaultTableModel model = (DefaultTableModel) MainClass.statementTable.getModel();
                model.setRowCount(0); //clear the table

                String dateSelected = ((JTextField) MainClass.statementDatePicker.getDateEditor().getUiComponent()).getText();

                counter = 0;

                try (ResultSet rs = stat.executeQuery("select * from Transactions Where Date = '" + dateSelected + "';")) {
                    while (rs.next()) {

                        model.insertRow(model.getRowCount(), new Object[]{rs.getString("Date"), rs.getString("Expense"), rs.getDouble("Amount"), "In/Out"}); //Insert new rows
                        MainClass.statementTable.getRowSorter().toggleSortOrder(0); //Sort the columns by default
                        counter++;
                    }
                }

                checkCount(counter);

            }
        } catch (SQLException sql) {
            System.out.println("System cannot get data");
        }

    }
    
    /**
     *
     * @param count
     */
    public static void checkCount(int count) {

        if (count == 0) {

            MainClass.recordCount.setText("No Records");
        } else {

            MainClass.recordCount.setText("Record Count: " + count);
        }

    }

}
