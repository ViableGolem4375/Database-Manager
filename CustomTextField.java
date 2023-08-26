package userInterfacePackage;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField{ 
	
	CustomTextField(String mainBackgroundColor, String lineColor, String textColor, String border){
		super();
		String textFieldStyle = "-fx-padding:0.9;-fx-font-weight:bold;-fx-text-fill:"+ textColor +";-fx-font-size:21px;"
						+ "-fx-background-color: "+mainBackgroundColor+","+lineColor+" ;-fx-background-insets: 0, 0 0 1 0; -fx-border-color:"+border+"; -fx-border-radius: 8";
		this.setStyle(textFieldStyle);
	}
	
	public static CustomTextField customTextField(TextFieldStyle thisStyle){
		CustomTextField newField = new CustomTextField(thisStyle.primary, thisStyle.lineColor, thisStyle.textColor, thisStyle.border);
		return newField;
	}
	
	public void changeSize(int size) {
		this.setStyle("-fx-font-size:" + size + "px;");
	}
}
