package userInterfacePackage;

import java.sql.*;

public class DatabaseConnection {


    public static Connection getConnection(String nameDB) {
        // This string is needs to proceed nameDB for the JDBC SQLite driver to work
        String url = "jdbc:sqlite:";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url + nameDB);
            System.out.println("Connected to database: " + nameDB);
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return conn;
    }


    public static void closeConnection(Connection conn) {

        try {
            if(conn != null) {
                conn.close();
            }
            System.out.println("Disconnected from database");
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
