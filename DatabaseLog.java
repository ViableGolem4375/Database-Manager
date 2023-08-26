package userInterfacePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseLog {


    static public void logAddItem(DBOperations op, Connection connection) {

        String sql = "INSERT INTO operations (timestamp, mod_type, item_id, quantity_change, notes, user_name)" + "VALUES (?, 'AddItem', ?, ?, NULL, ?)";

        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setString(1, op.getTimeStamp());
            pstmnt.setString(2, op.getItem().getID());
            pstmnt.setInt(3, op.getItem().getQuantity());
            pstmnt.setString(4, op.getUser());
            pstmnt.executeUpdate();
            pstmnt.close();
            System.out.println("operation was logged: add.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
    static public void logRemoveItem(DBOperations op, Connection connection) {

        String sql = "INSERT INTO operations (timestamp, mod_type, item_id, quantity_change, notes, user_name)" + "VALUES (?, 'RemoveItem', ?, NULL, NULL, ?)";

        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setString(1, op.getTimeStamp());
            pstmnt.setString(2, op.getItem().getID());
            pstmnt.setString(3, op.getUser());
            pstmnt.executeUpdate();
            pstmnt.close();
            System.out.println("operation was logged: Remove.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    static public void logModifyItemQuantity(DBOperations op, Connection connection) {

        String sql = "INSERT INTO operations (timestamp, mod_type, item_id, quantity_change, notes, user_name)" + "VALUES (?, 'ModifyItemQuantity', ?, ?, NULL, ?)";

        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setString(1, op.getTimeStamp());
            pstmnt.setString(2, op.getItem().getID());
            pstmnt.setInt(3, op.getQuantityChange());
            pstmnt.setString(4, op.getUser());
            pstmnt.executeUpdate();
            pstmnt.close();
            System.out.println("operation was logged: ModifyQuantity.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    static public void logModifyItemDesc(DBOperations op, Connection connection) {

        String sql = "INSERT INTO operations (timestamp, mod_type, item_id, quantity_change, notes, user_name)" + "VALUES (?, 'ModifyItemDescription', ?, NULL, ?, ?)";

        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setString(1, op.getTimeStamp());
            pstmnt.setString(2, op.getItem().getID());
            pstmnt.setString(3, op.getNewDescription());
            pstmnt.setString(4, op.getUser());
            pstmnt.executeUpdate();
            pstmnt.close();
            System.out.println("operation was logged: ModifyDescription.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }




}
