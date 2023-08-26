package userInterfacePackage;

import java.sql.*;
import java.util.ArrayList;

// Main reference used: https://www.tutorialspoint.com/jdbc/jdbc-statements.htm
// Creates and returns a connection to the SQLite database associated with nameDB
public class DatabaseManager {

    String nameDB;
    Connection connection = null;

    public DatabaseManager(String nameDB) {

        this.nameDB = nameDB;
        this.connection = DatabaseConnection.getConnection(this.nameDB);

        // Since getConnection makes a new database if one does not exist,
        // this checks if the db is empty & creates the db tables
        // this check can be improved upon
        if (!tablesExist("items")) {
            DatabaseMaker.makeTables(this.connection);
        }

    }
    public void close() {
        DatabaseConnection.closeConnection(this.connection);
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
            rs.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return tableRes;
    }

    public void executeDBOperation(DBOperations operation) {
    	System.out.println(operation.getType());/**
        switch(operation.getType()) {

            case ADD:
                AddItem.AddItem(operation,connection);
                //DatabaseLog.logAddItem(operation, this.connection);
                break;
            case REMOVE:
                RemoveItem.RemoveItem(operation,connection);
                //DatabaseLog.logRemoveItem(operation, this.connection);
                break;
            case MODIFY_QUANTITY:
                ModifyItem.ModifyItem(operation,connection);
                //DatabaseLog.logModifyItemQunatity(operation, this.connection);
                break;
            case MODIFY_DESC:
            	ModifyItem.ModifyItem(operation, connection);
                break;
            default:
                System.out.println("DEFINE THE DBOperation TYPE");
        }*/
    }
    
    public ArrayList<DBItem> executeIndexQuery(int start, int number) {

        return DatabaseSearch.indexSearch(start, number, this.connection);
    }

    public ArrayList<DBItem> executeQuantitySearch(int min, int max) {

        return DatabaseSearch.quantitySearch(min, max, this.connection);
    }
    

}
