package userInterfacePackage;


import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class HomePage extends Main {
	String usersName;
	HomeSpace topBox = new HomeSpace();
	HomeSpace topBox2 = new HomeSpace();
	ButtonStyle trayStyle = ButtonTray.getButtonStyle();
	CustomButton addButton = CustomButton.customStaticButton("Add Item", trayStyle);
	CustomButton exportButton = CustomButton.customStaticButton("Export To CSV", trayStyle);
	CustomButton logoutButton = CustomButton.customStaticButton("Log Out", trayStyle);
	ScrollPane pane = new ScrollPane();
	GridPane root = new GridPane();
	GridPane root2 = new GridPane();
	InventoryManager testdb = new InventoryManager("test_3.db");
	DatabaseManager testdb2 = new DatabaseManager("test_3.db");
	static TextFieldStyle searchField = new TextFieldStyle("#372F2F", "#C9595F", "E0D0B8");
	SearchBar theSearch = SearchBar.createBar(trayStyle, searchField);;
	
	public HomePage() {	
	}
	
	public Scene getScene() {
		DropShadow drop = new DropShadow();
		drop.setRadius(100);
		
		
		
		pane.setPrefSize(1000, 800);
		pane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-background-radius: 10; -fx-border-radius: 10");
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane.setVbarPolicy(ScrollBarPolicy.NEVER);
		root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #C9595F, #FFBF00); -fx-background-radius: 10");
		root.setPrefSize(1000, 800); 
	    root.setPadding(new Insets(10, 10, 10, 10)); 
	    root.setVgap(10); 
	    root.setHgap(10);       
	    root.setAlignment(Pos.CENTER);
	    pane.setContent(root);
	    pane.setEffect(drop);
	    root2.setVgap(30); 
	    root2.setPrefSize(1200, 800);
	    //root2.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #C9595F, #FFBF00)");
	    root2.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #abdb42, #484fd4)");
	    //root2.setStyle("-fx-background-color: #2e2e27");
	    Label welcomeLabel = new Label("Hello, " + usersName);
	   
	    HBox topPage = new HBox();
		HBox bottomPage = new HBox(220);
		
		AnchorPane apLeft = new AnchorPane();
        HBox.setHgrow(apLeft, Priority.ALWAYS);//Make AnchorPane apLeft grow horizontally
        AnchorPane apRight = new AnchorPane();
        topPage.getChildren().add(apLeft);
        topPage.getChildren().add(apRight);
        
		
		topPage.setPrefWidth(800);
		bottomPage.setPrefWidth(800);
	    welcomeLabel.setStyle("-fx-font-family: Roboto; -fx-font-weight:bold; -fx-font-size: 42px; -fx-padding: 20; -fx-text-fill: #FFFFFF");
	    theSearch.setAlignment(Pos.BASELINE_RIGHT);
	    welcomeLabel.setAlignment(Pos.BASELINE_LEFT);
	    addButton.setAlignment(Pos.BASELINE_LEFT);
	    exportButton.setAlignment(Pos.BASELINE_RIGHT);
	    logoutButton.setAlignment(Pos.BASELINE_CENTER);
	    root2.setAlignment(Pos.CENTER);
	    apLeft.getChildren().add(welcomeLabel);
	    apRight.getChildren().add(theSearch);
	    bottomPage.getChildren().addAll(addButton, exportButton, logoutButton);
	    root2.add(topPage, 0, 0);
	    
	    root2.add(pane, 0, 1);
	    root2.add(bottomPage, 0, 2);
	    
		Scene homePage = new Scene(root2);
		return homePage;
	}
	
	public void setName(String userName) {
		usersName = userName;
	}
	
	public void buildHomeArray() {
		HomeArray array = HomeArray.newArray(root, this);
		ArrayList<DBItem> res = testdb2.executeIndexQuery(0, 1000);
		ArrayList<HomeSpace> newHomeArray = array.buildArray(res);
		array.setItemArray(newHomeArray);
		array.setInventoryManager(testdb);
		
		if(newHomeArray.size() > 0){
			for(int i = 0; i < res.size(); i++) {
				root.add(newHomeArray.get(i).buildHomeSpace(), 0, i);
				array.interactWithArray(i, newHomeArray);
			}	
		}
		else {
			array.blankArray(newHomeArray);
		}
	}
	
}


