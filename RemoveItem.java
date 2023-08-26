package userInterfacePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RemoveItem {
    public static void RemoveItem(DBOperations operation, Connection connection) {
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

}
