package userInterfacePackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;


class EventProcessorTest {
	
	@BeforeAll
	static void initJfxRuntime() {
	    Platform.startup(() -> {});
	}
	
	/**
	 * This method tests the ability of the EventProcessor to call the correct functions in the InventoryManager
	 * Since the default behavior of an add operation is to create the description "Empty" 
	 */
	@Test
	@DisplayName("The processor correctly routes calls to add items to the inventory.")
	void testAddItem() {
		EventProcessor testProcessor = EventProcessor.createEventProcessor();
		FakeInventoryManager fakeManager = new FakeInventoryManager("test");
		testProcessor.setManager(fakeManager);
		testProcessor.writeItemToDatabase();
		assertTrue(fakeManager.description.equals("Empty"));
	}
	
	/**
	 * This tests the ability of the EventProcessor to correctly call the InventoryManager methods which 
	 * are used to remove an item from the database.
	 */
	@Test
	@DisplayName("The processor correctly routes calls to delete an item from the inventory.")
	void testRemoveItem() {
		int desiredIDNum = 2;
		EventProcessor testProcessor = EventProcessor.createEventProcessor();
		FakeInventoryManager fakeManager = new FakeInventoryManager("test");
		fakeManager.setID(desiredIDNum);
		testProcessor.setHomeSpace(new FakeHomeSpace(String.valueOf(desiredIDNum), "test", "2"));
		testProcessor.setManager(fakeManager);
		testProcessor.removeItemFromDatabase();
		assertTrue(fakeManager.isRemoved);
	}
	
	/**
	 * This test simulates changing an existing item in the database. The InventoryManager stub is 
	 * defined with a set of initial values. A fake HomeSpace object is then passed to the testProcessor 
	 * and the EventProcessor communicates the new data to the fake InventoryManager. The test passes if the
	 * new data matches the data from the passed space
	*/
	@Test
	@DisplayName("The Processor correctly routes calls to alter an inventory item.")
	void testAlterItemWithStubClass() {
		int desiredIDNum = 2;
		EventProcessor testProcessor = EventProcessor.createEventProcessor();
		FakeInventoryManager fakeManager = new FakeInventoryManager("test");
		FakeHomeSpace initSpace = new FakeHomeSpace(String.valueOf(desiredIDNum), "test_passed", "2");
		fakeManager.setID(desiredIDNum);
		fakeManager.setDescription("Fail_Case");
		fakeManager.setQuantity(5);
		testProcessor.setHomeSpace(initSpace);
		testProcessor.setManager(fakeManager);
		testProcessor.setInitDescription();
		testProcessor.alterDatabaseEntry();
		System.out.println(fakeManager.description);
		assertTrue(fakeManager.description.equals("test_passed") && fakeManager.quantity == 2);
	}
	/**
	 * This test ensures that the requested HomeSpace object can be removed from the front-end element
	 * list as well as from the database item list
	 */
	@Test
	@DisplayName("The Processor correctly routes calls to delete the visual elements from the element list.")
	void testRemoveVisualSpace() {
		EventProcessor testProcessor = EventProcessor.createEventProcessor();
		FakeHomeSpace initSpace = new FakeHomeSpace("0", "test_passed", "2");
		FakeHomeArray fakeArray = new FakeHomeArray();
		FakeInventoryManager fakeManager = new FakeInventoryManager("test");
		FakeHomePage fakePage = new FakeHomePage();
		testProcessor.setHomeSpace(initSpace);
		testProcessor.setManager(fakeManager);
		testProcessor.setPage(fakePage);
		testProcessor.setArray(fakeArray);
		testProcessor.removeVisualSpace();
		assertTrue(fakeArray.isDeleted && fakeArray.isRemovedFromPane && fakePage.arrayRebuilt);
	}
	
	/**
	 * Because the processor and outer system interact by re-indexing the database in order to display
	 * added database elements, the only necessary test here is to make sure that the buildHomeArray method
	 * is called in the HomePage class
	 */
	@Test
	void testAddedVisualElement() {
		EventProcessor testProcessor = EventProcessor.createEventProcessor();
		FakeHomeSpace initSpace = new FakeHomeSpace("0", "test_passed", "2");
		FakeHomeArray fakeArray = new FakeHomeArray();
		FakeInventoryManager fakeManager = new FakeInventoryManager("test");
		FakeHomePage fakePage = new FakeHomePage();
		testProcessor.setHomeSpace(initSpace);
		testProcessor.setManager(fakeManager);
		testProcessor.setPage(fakePage);
		testProcessor.setArray(fakeArray);
		testProcessor.displayAddedSpace();
		assertTrue(fakePage.arrayRebuilt);
	}
	/**
	 * These tests function much more like integration tests. I wrote them to make sure that the
	 * end results of the api connections actually function properly. These DO NOT make use of mocks
	 * or stubs and therefore function very poorly as unit tests for this specific class.
	 */
	@Test
	void testTextFieldDisable() {
		EventProcessor newProcessor = EventProcessor.createEventProcessor();
		HomeSpace newSpace = new HomeSpace();
		newProcessor.setHomeSpace(newSpace);
		newProcessor.disableText();
		System.out.println(newSpace.getDescription());
		boolean descBox = newSpace.descriptionBox.userTextField.isEditable();
		boolean quantBox = newSpace.quantityBox.userTextField.isEditable();
		System.out.println(newSpace.descriptionBox.userTextField.isEditable());
		assertFalse(descBox && quantBox);
	}
	@Test
	void testTextFieldEnable() {
		EventProcessor newProcessor = EventProcessor.createEventProcessor();
		HomeSpace newSpace = new HomeSpace();
		newProcessor.setHomeSpace(newSpace);
		newProcessor.enableText();
		boolean descBox = newSpace.descriptionBox.userTextField.isEditable();
		boolean quantBox = newSpace.quantityBox.userTextField.isEditable();
		assertTrue(descBox && quantBox);
	}
	
}

/**
 * This is a stub for the Inventory manager class which is used to ensure that the InventoryManager functions
 * can be called correctly
 * @author Beckett
 *
 */
class FakeInventoryManager extends InventoryManager{
	public int ID;
	public String description;
	public int quantity;
	public boolean isRemoved;
	public FakeInventoryManager(String dbName) {
		super(dbName);
		isRemoved = false;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addItem(int ID, String description, int quantity) {
		this.ID = ID;
		this.description = description;
		this.quantity = quantity; 
	}
	
	@Override
	public void removeItem(int ID) {
		if(ID == this.ID) {
			isRemoved = true;
		}
	}
	
	@Override
	public void modifyItemDescription(int id, String newDescription) {
		if(id == this.ID) {
			description = newDescription;
		}
	}
	@Override
	public void modifyItemQuantity(int id, int quantityChange) {
		if(id == this.ID) {
			quantity = quantityChange;
		}
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setDescription(String desc) {
		description = desc;
	}
	public void setQuantity(int quant) {
		quantity = quant;
	}
}

/**
 * This is a stub for the HomeSpace object which provides the necessary attributes to pass to the methods
 * of the EventProcessor class
 * @author Beckett
 *
 */
class FakeHomeSpace extends HomeSpace{
	public String ID;
	public String description;
	public String quantity;
	
	public FakeHomeSpace(String ID, String description, String quantity) {
		this.ID = ID;
		this.description = description;
		this.quantity = quantity; 
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public String getQuantity() {
		return quantity;
	} 
	@Override
	public String getID() {
		return ID;
	}
}

/**
 * This is a stub for the HomeArray class with methods which will show if the processor called the
 * correct method and altered the necessary data.
 * @author Beckett
 *
 */
class FakeHomeArray extends HomeArray{
	boolean isDeleted;
	boolean isRemovedFromPane;

	public FakeHomeArray() {
		super();
		isDeleted = false;
		isRemovedFromPane = false;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void deleteArrayElement(HomeSpace theSpace) {
		isDeleted = true;
	}
	
	@Override
	public void destroyRootGridPane() {
		isRemovedFromPane = true;
	}
}

/**
 * This is a stub for the HomePage class which can be used to test the EventProcessor's ability to add 
 * new objects to the page for display.
 * @author Beckett
 *
 */
class FakeHomePage extends HomePage {
	boolean arrayRebuilt;
	
	public FakeHomePage() {
		arrayRebuilt = false;
	}
	@Override
	public void buildHomeArray(){
		arrayRebuilt = true;
	}
}

