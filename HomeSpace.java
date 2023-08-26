package userInterfacePackage;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class HomeSpace extends VBox{
	private final String labelColor = "0x372F2F";
	//public final static String backgroundColorSet = "-fx-background-color: #C9595F";
	public final static String backgroundColorSet = "-fx-background-color: transparent;";
	public final static String borderColorSet = "-fx-border-color: white;";
	private final TextFieldStyle homeFieldStyle = new TextFieldStyle("transparent", "transparent", "E0D0B8");
	UserField idBox = new UserField("ID: ", labelColor, homeFieldStyle);
	UserField quantityBox = new UserField("Quantity: ", labelColor, homeFieldStyle);
	UserField descriptionBox = new UserField("Description: ", labelColor, homeFieldStyle);
	ButtonTray buttonTray = ButtonTray.buttonTray();
	int index;
	
	public HomeSpace() {}
	
	VBox buildHomeSpace() {
		idBox = idBox.userField();
		descriptionBox = descriptionBox.userField();
		quantityBox = quantityBox.userField();
		idBox.setAlignment(Pos.BASELINE_LEFT);
		descriptionBox.setAlignment(Pos.BASELINE_LEFT);
		quantityBox.setAlignment(Pos.BASELINE_LEFT);
		VBox homeSpace = new VBox();
		homeSpace.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-width: 2; -fx-padding: 20;" + backgroundColorSet + borderColorSet);
		homeSpace.setPrefWidth(900);
		homeSpace.getChildren().addAll(descriptionBox, quantityBox, idBox, buttonTray);
		homeSpace.setAlignment(Pos.CENTER);
		return homeSpace;	
	}
	
	public void setDescription(String desc) {
		descriptionBox.userTextField.setText(desc);
		descriptionBox.userTextField.setEditable(false);
	}
	
	public void setID(String idNum) {
		idBox.userTextField.setText(idNum);
		idBox.userTextField.setEditable(false);
	}
	
	public void setQuantity(String quantVal) {
		quantityBox.userTextField.setText(quantVal);
		quantityBox.userTextField.setEditable(false);
	}
	
	public void setIndex(int theIndex) {
		index = theIndex;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getDescription() {
		return descriptionBox.getText();
	}
	
	public String getQuantity() {
		return quantityBox.getText();
	} 
	
	public String getID() {
		return idBox.getText();
	}
	
	public CustomButton getEditButton() {
		return buttonTray.editButton;
	}
	
	public CustomButton getDeleteButton() {
		return buttonTray.deleteButton;
	}
	
	public CustomButton getSaveButton() {
		return buttonTray.saveButton;
	}
}
