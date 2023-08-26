package userInterfacePackage;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;

public class HomeArray {
	ArrayList<HomeSpace> theArray;
	HomeArray homeArray;
	HomePage page;
	GridPane root;
	InventoryManager theInventory;
	ArrayList<DBItem> theItemArray;
	int numObjects;
	
	public HomeArray(GridPane root, HomePage thePage) {
		this.page = thePage;
		this.root = root;	
	}
	
	public HomeArray() {
		
	}
	
	public void setItemArray(ArrayList <HomeSpace> theArray) {
		this.theArray = theArray; 
	}
	
	public ArrayList<HomeSpace> buildArray(ArrayList<DBItem> databaseArray) {
		ArrayList<HomeSpace> homeArray = new ArrayList<HomeSpace>();
		for(int i = 0; i < databaseArray.size(); i++) {
			homeArray.add(new HomeSpace());
			HomeSpace newSpace = homeArray.get(i);
	    	newSpace.setDescription(databaseArray.get(i).getDescription());
	    	newSpace.setID(databaseArray.get(i).getID());
	    	newSpace.setQuantity(String.valueOf(databaseArray.get(i).getQuantity()));
	    	newSpace.setIndex(i);
	    	newSpace.buttonTray.saveButton.setDisable(true);
		}
		this.theItemArray = databaseArray;
		return homeArray;
	}
	
	public static HomeArray newArray(GridPane root, HomePage thePage) {
		HomeArray thisArray = new HomeArray(root, thePage);
		return thisArray;
	}
	
	void setInventoryManager(InventoryManager thisInventory) {
		theInventory = thisInventory;
	}
	
	public void interactWithArray(int index, ArrayList<HomeSpace> theArray){
		EventProcessor theseActions = EventProcessor.createEventProcessor();
		theseActions.setPage(page);
		theseActions.setHomeSpace(theArray.get(index));
		theseActions.setHomeRoot(root);
		theseActions.setArray(this);
		theseActions.setVisArray(theArray);
		theseActions.setManager(theInventory);
		EventProcessorHelper theHelper = new EventProcessorHelper(theseActions);
		theHelper.handleSave();
		theHelper.handleEdit();
    	theHelper.deleteHomeSpace();
    	theHelper.addNewHomeSpace();
    	theHelper.searchDynamically();
    	theHelper.callSearch();
    	theHelper.handleExport();
	}
	
	public void destroyRootGridPane() {
		root.getChildren().clear();
	}
	
	public void alterGrid(ArrayList<HomeSpace> theArray, int newSize) {
		for(int i = 0; i < newSize; i++) {
			root.add(theArray.get(i).buildHomeSpace(),0,i);
		}
	}
	
	public void deleteArrayElement(HomeSpace theSpace) {
		theArray.remove(theArray.indexOf(theSpace));
	}
	
	public void blankArray(ArrayList<HomeSpace> theArray) {
		EventProcessor theseActions = EventProcessor.createEventProcessor();
		theseActions.setManager(theInventory);
		theseActions.setPage(page);
		theseActions.setArray(this);
		EventProcessorHelper theHelper = new EventProcessorHelper(theseActions);
		theHelper.addNewHomeSpace();
	}
}
