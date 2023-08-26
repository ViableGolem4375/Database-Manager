package userInterfacePackage;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButton extends Button {
	String htmlColorChoice;
	Font buttonFont;
	CustomButton newButton;
	ButtonStyle thisStyle;
	
	CustomButton(String buttonName, ButtonStyle thisButton){
		thisStyle = thisButton;
		buttonFont = Font.font(thisButton.fontChoice, FontWeight.BOLD, thisButton.fontSize);
		this.setStyle("-fx-background-radius: 5;-fx-background-color:" + thisButton.htmlColor);
		this.setText(buttonName);
		this.setTextFill(thisButton.fontColor);
		this.setFont(buttonFont);
	}
	
	public static CustomButton customStaticButton(String buttonName,  ButtonStyle thisButton) {
		CustomButton newButton = new CustomButton(buttonName, thisButton);
		return newButton;
	}
	
	public void setFontSize(int size) {
		buttonFont = Font.font(thisStyle.fontChoice, FontWeight.BOLD, size);
		this.setFont(buttonFont);
	}
}

