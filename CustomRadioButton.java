//This class exists to act as a constructor for new radio button objects.
//It creates the new button and then assigns various variables to the button for visuals
//and functionality purposes.
package userInterfacePackage;

//Imports
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;

//Class
public class CustomRadioButton extends RadioButton {
	//Variables to be assigned to the button.
	String htmlColorChoice2;
	ToggleGroup radioButtonGroup;
	Font radioButtonFont;
	CustomRadioButton newRadioButton;
	boolean selectedButton;
	
	//Constructor for the radio button
	CustomRadioButton(String radioButtonName, radioButtonStyle thisRadioButton) {
		radioButtonFont = Font.font(thisRadioButton.fontChoice2, FontWeight.BOLD, thisRadioButton.fontSize2);
		this.setStyle("-fx-background-radius: 5;-fx-background-color:" + thisRadioButton.htmlColor2);
		this.setText(radioButtonName);
		this.setTextFill(thisRadioButton.fontColor2);
		this.setFont(radioButtonFont);
		this.setToggleGroup(thisRadioButton.radioButtonGroup);
		this.setSelected(thisRadioButton.selectedButton);
	}
	
	//Method to return the new radio button for use in other classes.
	public static CustomRadioButton customStaticRadioButton(String radioButtonName,  radioButtonStyle thisRadioButton) {
		CustomRadioButton newRadioButton = new CustomRadioButton(radioButtonName, thisRadioButton);
		return newRadioButton;
	}
}
