package userInterfacePackage;

import java.sql.*;
public class AddItem {
    public static void AddItem(DBOperations operation,Connection connection) {
        try {
            String sql = "INSERT INTO items (id, count, description) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, operation.getItem().getID());
            statement.setInt(2, operation.getItem().getQuantity());
            statement.setString(3, operation.getItem().getDescription());

            statement.executeUpdate();

            System.out.println("Item added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
