package userInterfacePackage;

import javafx.scene.paint.Color;
import javafx.scene.control.ToggleGroup;

public class radioButtonStyle {
	public String htmlColor2;
	public Color fontColor2;
	public String fontChoice2;
	public int fontSize2;
	ToggleGroup radioButtonGroup;
	boolean selectedButton;
	
	public radioButtonStyle() {}
	
	public void setRadioBackground(String backColor) {
		htmlColor2 = backColor;
	}
	public void setRadioFontColor(Color colorFont) {
		fontColor2 = colorFont;
	}
	public void setRadioFontChoice(String font) {
		fontChoice2 = font;
	}
	public void setRadioFontSize(int size) {
		fontSize2 = size;
	}
	public void setToggleGroup(ToggleGroup toggleGroup) {
		radioButtonGroup = toggleGroup;
	}
	public void setSelected(boolean selected) {
		selectedButton = selected;
	}
}
