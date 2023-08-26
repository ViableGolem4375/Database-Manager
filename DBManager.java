package userInterfacePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DBManager {

    private String nameDB;
    private String url;
    private Connection connection;

    public DBManager(String nameDB) {
        this.nameDB = nameDB;
        this.url = "jdbc:sqlite:" + nameDB;
        getConnection();
        createTablesIfNeeded();
    }

    private void getConnection() {
        try {
            this.connection = DriverManager.getConnection(url);
            System.out.println("Connected to database: " + nameDB);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createTablesIfNeeded() {
        if (!tablesExist("items")) {
            //DatabaseMaker maker = new DatabaseMaker();
            DatabaseMaker.makeTables(this.connection);
        }
    }

    private boolean tablesExist(String tableName) {
        boolean tableRes = false;

        String sqlStr = "SELECT count(*)" +
                " FROM sqlite_master" +
                " WHERE type = 'table' AND" +
                " name = ?";
        try {
            PreparedStatement stmnt = this.connection.prepareStatement(sqlStr);
            stmnt.setString(1, tableName);
            stmnt.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = stmnt.executeQuery();

            if (rs.getInt(1) == 1) {
                tableRes = true;
            }
            stmnt.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return tableRes;
    }

    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            System.out.println("Disconnected from database: " + nameDB);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void executeDBOperation(DBOperations operation) {
        switch (operation.getType()) {
            case ADD:
                addItem(operation);
                break;
            case MODIFY_QUANTITY:
                modifyItem(operation);
                break;
            case MODIFY_DESC:
                modifyItemDesc(operation);
                break;
            case REMOVE:
                removeItem(operation);
                break;
            default:
                System.out.println("DEFINE THE DBOperation TYPE");
        }
    }

    private void addItem(DBOperations operation) {
        try {
            String sql = "INSERT INTO items (id, count, description, update_time) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, operation.getItem().getID());
            statement.setInt(2, operation.getItem().getQuantity());
            statement.setString(3, operation.getItem().getDescription());
            statement.setString(4, operation.getTimeStamp());
            statement.executeUpdate();

            System.out.println("Item added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifyItem(DBOperations operation) {
        try {
            String sql = "UPDATE items SET count = ?, update_time = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, operation.getItem().getQuantity());
            statement.setString(2, operation.getTimeStamp());
            statement.setString(3, operation.getItem().getID());
            System.out.println(operation.getTimeStamp());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item modified successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void modifyItemDesc(DBOperations operation) {
        try {
            String sql = "UPDATE items SET description = ?, update_time = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, operation.getItem().getDescription());
            statement.setString(2, operation.getTimeStamp());
            statement.setString(3, operation.getItem().getID());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item modified successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeItem(DBOperations operation) {
        try {
            String sql = "DELETE FROM items WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, operation.getItem().getID());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item removed successfully.");
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addUser(String username, String password) {
    	try {
    		String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
    		Random newRandom = new Random();
    		int randID = newRandom.nextInt(1000);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, randID);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection accessConnection() {
    	return connection;
    }
    
}
