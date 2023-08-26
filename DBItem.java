package userInterfacePackage;

public class DBItem {
    private int quantity;
    private String ID;
    private String description;
    private String lastUpdated;


    public DBItem(int quantity, String ID, String description) {
        this.quantity = quantity;
        this.ID = ID;
        this.description = description;
    }
    
    public DBItem(int quantity, String ID, String description, String updateTime) {
        this.quantity = quantity;
        this.ID = ID;
        this.description = description;
        this.lastUpdated = updateTime;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getID() {
        return this.ID;
    }

    public String getDescription() {
        return this.description;
    }

    public char[] getCount() {
        return null;
    }
    
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
    
    public String getLastUpdated() {
    	return lastUpdated;
    }
    
    public void setLastUpdated(String updateDate) {
    	lastUpdated = updateDate;
    }
}
