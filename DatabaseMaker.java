package userInterfacePackage;

import java.sql.*;

public class DatabaseMaker {

    public static void makeTables(Connection conn) {
        System.out.println("DatabaseMaker called");
        makeItems(conn);
        makeUsers(conn);
        makeOperations(conn);
        System.out.println("DatabaseMaker done");
    }

    private static void makeItems(Connection conn) {

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("create table items (" +
                    "id int primary key  not null," +
                    "count int not null," +
                    "description text not null," +
                    "update_time text null)");
            statement.close();
            System.out.println("items created");
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // THE TABLE ONLY HAS AN ID COL BC WE HAVE NOT DECIDED WHAT TO INCLUDE IN IT
    private static void makeUsers(Connection conn) {

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("create table users (" +
                    "id int primary key  null," +
                    "username text not null," +
                    "password text not null)");
            statement.close();
            System.out.println("users created");
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void makeOperations(Connection conn) {

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("create table operations (" +
                    "timestamp text not null," +
                    "mod_type text not null," +
                    "item_id text null," +
                    "quantity_change int null," +
                    "notes text null," +
                    "user_name text null)");
            statement.close();
            System.out.println("transactions created");
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
