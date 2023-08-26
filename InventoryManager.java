package userInterfacePackage;

public class InventoryManager {
    private DBManager dbManager;
    private String userName;

    public InventoryManager(String dbName) {
        this.dbManager = new DBManager(dbName);
        DatabaseSearch.indexOpSearch(0, 10, dbManager.accessConnection());
    }
    
    public void setUserName(String user) {
    	this.userName = user;
    }
    
    
    public void addItem(int id, String name, int quantity) {
        DBItem item = new DBItem(quantity, String.valueOf(id), name);
        DBOperations operation = new DBOperations(DBOperations.DBOperationsType.ADD, item);
        operation.setUser(userName);
        DatabaseLog.logAddItem(operation, dbManager.accessConnection());
        dbManager.executeDBOperation(operation);
    }

    public void modifyItemQuantity(int id, int quantityChange) {
        DBItem item = new DBItem(quantityChange, String.valueOf(id), null);
        DBOperations operation = new DBOperations(DBOperations.DBOperationsType.MODIFY_QUANTITY, item);
        operation.setUser(userName);
        DatabaseLog.logModifyItemQuantity(operation, dbManager.accessConnection());
        dbManager.executeDBOperation(operation);
    }

    public void removeItem(int id) {
        DBItem item = new DBItem(0, String.valueOf(id), null);
        DBOperations operation = new DBOperations(DBOperations.DBOperationsType.REMOVE, item);
        operation.setUser(userName);
        DatabaseLog.logRemoveItem(operation, dbManager.accessConnection());
        dbManager.executeDBOperation(operation);
    }

    public void modifyItemDescription(int id, String newDescription) {
        DBItem item = new DBItem(0, String.valueOf(id), newDescription);
        DBOperations operation = new DBOperations(DBOperations.DBOperationsType.MODIFY_DESC, item);
        operation.setUser(userName);
        DatabaseLog.logModifyItemDesc(operation, dbManager.accessConnection());
        dbManager.executeDBOperation(operation);
    }

    public void closeInventory() {
        dbManager.closeConnection();
    }
    
    public DBManager getManager() {
    	return dbManager;
    }
    /**
    public static void main(String[] args) {
        String dbName = "inventory.db";
        InventoryManager inventoryManager = new InventoryManager(dbName);

        inventoryManager.addItem(1, "Product 1", 10);
        inventoryManager.modifyItemQuantity(1, 5);
        inventoryManager.removeItem(1);
        inventoryManager.modifyItemDescription(1, "New Description");

        inventoryManager.closeInventory();
    }*/
}
