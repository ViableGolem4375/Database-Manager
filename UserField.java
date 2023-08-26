package userInterfacePackage;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class UserField extends HBox {
	HBox userField = new HBox();
	String usrLabel = new String();
	String textValue = new String();
	String textFieldColor = "#E0D0B8";
	String textColor = "#C9595F";
	CustomTextField userTextField;
	
	public UserField(String label, String labelColor, TextFieldStyle fieldStyle) {
		super();
		userTextField  = CustomTextField.customTextField(fieldStyle);
		this.setStyle("-fx-background-color: transparent;-fx-font-weight:bold; -fx-padding:5; -fx-font-size: 24");
		this.getChildren().addAll(this.styleLabel(label, labelColor), userTextField);
		this.setAlignment(Pos.CENTER);
		textValue = userTextField.getText();
	}
	
	public UserField userField() {
		return this;
	}
	
	private Label styleLabel(String label, String labelColor) {
		Label userLabel = new Label(label);
		userLabel.setTextFill(Color.web(labelColor));
		userLabel.setStyle("-fx-font-family: Roboto;-fx-font-size: 24px");
		return userLabel;
	}
	
	public String getText() {
		return userTextField.getText();
	}
	
	public void disable() {
		this.userTextField.setEditable(false);
	}
	
	public void enable() {
		this.userTextField.setEditable(true);
	}
}
