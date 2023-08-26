package userInterfacePackage;

import javafx.scene.paint.Color;

public class ButtonStyle {
	public String htmlColor;
	public Color fontColor;
	public String fontChoice;
	public int fontSize;
	
	public ButtonStyle() {}
	
	public void setBackground(String backColor) {
		htmlColor = backColor;
	}
	public void setFontColor(Color colorFont) {
		fontColor = colorFont;
	}
	public void setFontChoice(String font) {
		fontChoice = font;
	}
	public void setFontSize(int size) {
		fontSize = size;
	}
}
