package userInterfacePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DatabaseSearch {


    static public ArrayList<DBItem> indexSearch(int start, int number, Connection connection) {

        ArrayList<DBItem> results = new ArrayList<>();

        String sql = "SELECT id, count, description, update_time FROM items ORDER BY id LIMIT ? OFFSET ?;";
        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setInt(1, number);
            pstmnt.setInt(2, start);
            ResultSet rs = pstmnt.executeQuery();
            
            while (rs.next()) { // should be a separate function
                int id = rs.getInt("id");
                int quantity  = rs.getInt("count");
                String desc = rs.getString("description");
                String time = rs.getString("update_time");
                results.add(new DBItem(quantity, Integer.toString(id), desc, time));
            }
            rs.close();
            pstmnt.close();
            System.out.println("Search GOOD");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return results;

    }
    static public ArrayList<DBItem> quantitySearch(int min, int max, Connection connection) {

        ArrayList<DBItem> results = new ArrayList<>();

        String sql = "SELECT id, count, description, update_time FROM items WHERE count >= ? and count <= ?;";
        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setInt(1, min);
            pstmnt.setInt(2, max);
            ResultSet rs = pstmnt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity  = rs.getInt("count");
                String desc = rs.getString("description");
                String time = rs.getString("update_time");
                results.add(new DBItem(quantity, Integer.toString(id), desc, time));
            }
            rs.close();
            pstmnt.close();
            System.out.println("Search GOOD");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return results;

    }
    
    static public ArrayList<DBItem> indexOpSearch(int start, int number, Connection connection) {

        ArrayList<DBItem> results = new ArrayList<>();

        String sql = "SELECT timestamp, mod_type, item_id, quantity_change, notes, user_name FROM operations ORDER BY item_id LIMIT ? OFFSET ?;";
        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setInt(1, number);
            pstmnt.setInt(2, start);
            ResultSet rs = pstmnt.executeQuery();

            while (rs.next()) { // should be a separate function
            	String ts = rs.getString("timestamp");
            	String mt = rs.getString("mod_type");
                String id = rs.getString("item_id");
                int quantity  = rs.getInt("quantity_change");
                String desc = rs.getString("notes");
                String user = rs.getString("user_name");
                System.out.println(ts + " " + mt +" " + id + " " + quantity + " " + desc + " " + user + "\n");
            }
            rs.close();
            pstmnt.close();
            System.out.println("Search GOOD");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return results;

    }
    
    static public boolean isMatchingUser(String username, Connection connection) {
    	boolean isMatching = false;
    	String sql = "SELECT username FROM users ORDER BY username LIMIT ? OFFSET ?;";
        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setInt(1, 1000);
            pstmnt.setInt(2, 0);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
            	String user = rs.getString("username");
            	if(user.equals(username)) {
            		isMatching = true;
            	}
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    	return isMatching;
    }
    
    static public boolean isMatchingPassword(String password, Connection connection) {
    	boolean isMatching = false;
    	String sql = "SELECT password FROM users ORDER BY username LIMIT ? OFFSET ?;";
        try {
            PreparedStatement pstmnt = connection.prepareStatement(sql);
            pstmnt.setInt(1, 1000);
            pstmnt.setInt(2, 0);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
            	String user = rs.getString("password");
            	if(user.equals(password)) {
            		isMatching = true;
            	}
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    	return isMatching;
    }

}
