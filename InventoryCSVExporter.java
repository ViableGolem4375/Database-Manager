package userInterfacePackage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
//import java.util.List;

public class InventoryCSVExporter {
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/path/to/inventory";
    //private static final String DB_USERNAME = "username";
    //private static final String DB_PASSWORD = "password";

    
    /**
    public void exportInventoryToCSV(List<InventoryItem> inventoryItems, String filePath ){
        FileWriter writer = null;

        try {
            writer = new FileWriter(filePath);
            writer.append("Item Name,Description,Quantity,Last Updated\n");

            for (InventoryItem item : inventoryItems) {
                writer.append(item.getName()).append(",");
                writer.append(item.getDescription()).append(",");
                writer.append(String.valueOf(item.getQuantity())).append(",");
                writer.append(item.getLastUpdated().toString()).append("\n");
            }

            System.out.println("Inventory data exported successfully");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
    
    
    public static void exportInventoryToCSV(String filePath, Connection conn){
        FileWriter writer = null;
        ArrayList<DBItem> inventoryItems = DatabaseSearch.indexSearch(0, 1000, conn);
        try {
            writer = new FileWriter(filePath);
            writer.append("Description,Quantity,Last Updated\n");

            for (DBItem item : inventoryItems) {
                writer.append(item.getDescription()).append(",");
                writer.append(String.valueOf(item.getQuantity())).append(",");
                writer.append(item.getLastUpdated()).append("\n");
            }

            System.out.println("Inventory data exported successfully");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
