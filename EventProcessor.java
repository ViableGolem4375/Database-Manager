package userInterfacePackage;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class provides a set of public methods which provide an interop between the front end
 * and the back end. These methods can be used in order to bridge the from the front end to the back end
 * by providing an interface which calls the methods from each set of components. The primary reason for this
 * middleware's existence is to avoid directly binding front and back-end elements. 
 * @author Beckett
 *
 */
public class EventProcessor {
	HomeSpace theSpace;
	HomePage activePage;
	GridPane rootPane;
	InventoryManager newManager;
	HomeArray theHomeArray;
	ArrayList <HomeSpace> theVisualArray;
	int initQuantity;
	String initDescription;
	DatabaseLog newDBLog;
	
	/**
	 * No arg constructor forces the fields to be mutated with getters and setters.
	 * This is beneficial because the properties of this class are meant to be defined explicitly
	 * after its creation
	 */
	public EventProcessor() {	
	}
	/**
	 * This static factory method returns an EventProcessor object, which eliminates
	 * the need for object instantiation in external code 
	 * @return thisEvent - an EventProcessor object
	 */
	public static EventProcessor createEventProcessor() {
		EventProcessor thisEvent = new EventProcessor();
		return thisEvent;
	}
	/**
	 * Sets up the HomeSpace object the user will interact with - this connection must be established in order for the
	 * system to correctly handle calls
	 * @param theSpace - a HomeSpace object
	 */
	public void setHomeSpace(HomeSpace theSpace) {
		this.theSpace = theSpace;
	}
	/**
	 * Sets up the GridPane the user will interact with
	 * @param root - a GridPane object
	 */
	public void setHomeRoot (GridPane root) {
		this.rootPane = root;
	}
	/**
	 * Sets the InventoryManager object that the class will use to change the state of the database
	 * @param thisManager - an InventoryManager object
	 */
	public void setManager (InventoryManager thisManager) {
		this.newManager = thisManager;
	}
	/**
	 * Sets the current page, which is a HomePage object
	 * @param currentPage - a HomePage object
	 */
	public void setPage(HomePage currentPage) {
		this.activePage = currentPage;
	}
	
	public void setVisArray(ArrayList<HomeSpace> visArray) {
		theVisualArray = visArray;
	}
	
	public void setArray(HomeArray thisHomeArray) {
		theHomeArray = thisHomeArray;
	}
	
	/**
	 * This function allows for the enabling and disabling of the text fields on the active HomeSpace
	 */
	public void enableText() {
		theSpace.descriptionBox.enable();
	    theSpace.quantityBox.enable();
	    theSpace.buttonTray.saveButton.setDisable(false);
	}
	/**
	 * this function causes the text fields on the active space to be disabled
	 */
	public void disableText() {
		theSpace.descriptionBox.disable();
		theSpace.quantityBox.disable();
		theSpace.buttonTray.saveButton.setDisable(true);
	}
	/**
	 * This function calls the getID, getDescription, and getQuantity functions of the HomeSpace class
	 * to get the parameters necessary to alter the database entry. The function then calls the InventoryManager
	 * functions which alter the entries in the database
	 */
	public void alterDatabaseEntry() {
		int IDNum = Integer.valueOf(theSpace.getID());
		int quant = Integer.valueOf(theSpace.getQuantity());
		String desc = theSpace.getDescription();
		if(initQuantity != quant) {
			newManager.modifyItemQuantity(IDNum, quant);
		
		}
		if(!(initDescription.equals(desc))) {
			newManager.modifyItemDescription(IDNum, desc);	
		}
	}
	
	public void setInitDescription() {
		initDescription = "initialized";
	}
	
	private boolean searchFindsSpace(String searchText, HomeSpace indexedSpace){
	    return (indexedSpace.getDescription().toLowerCase().contains(searchText.toLowerCase())) ||
	            (indexedSpace.getID().contains(searchText));
	}
	
	ArrayList<HomeSpace> filterList(ArrayList<HomeSpace> list, String searchText){
	    ArrayList<HomeSpace> filteredList = new ArrayList<HomeSpace>();
	    for (HomeSpace thisSpace : list){
	        if(searchFindsSpace(searchText, thisSpace)) {
	        	filteredList.add(thisSpace);
	        }
	    }
	    return filteredList;
	}
	
	/**
	 * This method invokes the deleteArrayElement function of the HomeArray class to remove
	 * the current HomeSpace object from the array. It then invokes the clear method of the GridPane
	 * class in order to cease displaying the deleted HomeSpace. It also removes the item from the database by
	 * invoking the removeItemFromDatabase method of this class
	 */
	public void removeVisualSpace() {
		theHomeArray.deleteArrayElement(theSpace);
		removeItemFromDatabase();
		theHomeArray.destroyRootGridPane();
		activePage.buildHomeArray();
	}
	
	/**
	 * This method invokes the HomePage's buildHomeArray method which constructs the entire visual space
	 * by indexing the number of items in the database
	 */
	public void displayAddedSpace() {
		theHomeArray.destroyRootGridPane();
		activePage.buildHomeArray();
	}
	
	/**
	 * This method writes an item to the database with basic attributes which can be edited by the user later
	 */
	public void writeItemToDatabase() {
		Random randVal = new Random();
		int desiredID = randVal.nextInt(899999) + 100000;
		newManager.addItem(desiredID, "Empty", 0);
	}
	
	/**
	 * This method deletes an item from the database by invoking the removeItem method of the InventoryManager
	 * class 
	 */
	public void removeItemFromDatabase() {
		newManager.removeItem(Integer.valueOf(theSpace.getID()));
	}
	
	/**
	 * This method returns the current space object to be used by the helper class to identify the button press
	 * @return theSpace - the active HomeSpace object
	 */
	public HomeSpace getThisSpace() {
		return theSpace;
	}
	
	/**
	 * This method returns the active page object for the helper class
	 * @return activePage - a HomePage object
	 */
	public HomePage getThePage() {
		return activePage;
	}
	
	
	public void getInitQuantity() {
		initQuantity = Integer.valueOf(theSpace.getID());
	}
	
	public void getInitDescription() {
		initDescription = theSpace.getDescription();
	}
}

/**
 * This class is used to interact between the firing of events from the front end and calls the functions in the EventProcessor 
 * itself. This allows the event processor to be a set of public methods which can be used for general purpose
 * connections between the front and back end without needing to rely on the implementation detail of handling
 * button firing
 * @author Beckett
 *
 */
class EventProcessorHelper {
	EventProcessor thisProcessor;
	HomeSpace theSpace;
	HomePage activePage;
	Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	
	public EventProcessorHelper(EventProcessor theProcessor) {
		thisProcessor = theProcessor;
		theSpace = thisProcessor.getThisSpace();
		activePage = thisProcessor.getThePage();
	}
	
	public void handleEdit() {
		theSpace.getEditButton().setOnAction(new EventHandler<ActionEvent>() {
		   @Override 
		    public void handle(ActionEvent e) {
			   thisProcessor.enableText();
			   thisProcessor.getInitDescription();
			   thisProcessor.getInitQuantity();
		    }    
		});	
	}
	
	public void handleSave() {
		theSpace.getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				thisProcessor.disableText();
				thisProcessor.alterDatabaseEntry();
			}    
		});	
	}
	
	public void deleteHomeSpace() {
		theSpace.getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				thisProcessor.removeItemFromDatabase();
				thisProcessor.removeVisualSpace();
			 }    
		});		   
	}
	
	public void addNewHomeSpace() {
		activePage.addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				thisProcessor.writeItemToDatabase();
				thisProcessor.displayAddedSpace();
			}
		});
	}
	
	public void searchDynamically() {
		activePage.theSearch.bar.textProperty().addListener((observable, oldValue, newValue) -> searchFunction(newValue));
	}
	
	public void callSearch() {
		activePage.theSearch.theButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String newValue = activePage.theSearch.bar.getText();
				searchFunction(newValue);
			}
		});
	}
	
	public void searchFunction(String newValue) {
		thisProcessor.theHomeArray.destroyRootGridPane();
		ArrayList<HomeSpace> thing = new ArrayList<HomeSpace>();
		thing = thisProcessor.filterList(thisProcessor.theVisualArray, newValue);
		if(thing.size() > 0) {
			for(int i = 0; i< thing.size(); i++) {
				thisProcessor.theHomeArray.alterGrid(thing, thing.size());	
			}
		}	
	}
	
	public void handleExport() {
		activePage.exportButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				InventoryCSVExporter.exportInventoryToCSV("test.csv", thisProcessor.newManager.getManager().accessConnection());
			}
		});
	}
	
	
	
}



