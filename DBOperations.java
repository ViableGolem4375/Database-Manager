package userInterfacePackage;

import java.text.SimpleDateFormat;

public class DBOperations {
    public enum DBOperationsType {
        ADD,
        REMOVE,
        MODIFY_QUANTITY,
        MODIFY_DESC
    }

    private DBItem item;
    private int quantityChange;
    private String newDescription;
    private DBOperationsType type;
    private String timeStamp;
    private String userName;

    public DBOperations(DBOperationsType type, DBItem item) {
        this.type = type;
        this.item = item;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

    public DBOperations(DBOperationsType type, DBItem item, int quantityChange) {
        this.type = type;
        this.item = item;
        this.quantityChange = quantityChange;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

    public DBOperations(DBOperationsType type, DBItem item, String newDescription) {
        this.type = type;
        this.item = item;
        this.newDescription = newDescription;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

    // Getter methods
    public DBOperationsType getType() {
        return type;
    }

    public void setUser(String theUser) {
    	userName = theUser;
    }
    
    public String getUser() {
    	return userName;
    }
    
    public DBItem getItem() {
        return item;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
