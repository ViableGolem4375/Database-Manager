package userInterfacePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ModifyItem {
    public static void ModifyItem(DBOperations operation, Connection connection) {
        try {
            String sql = "UPDATE items SET count = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, operation.getItem().getQuantity());
            statement.setString(2, operation.getItem().getID());

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
}
