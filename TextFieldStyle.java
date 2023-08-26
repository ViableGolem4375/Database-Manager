package userInterfacePackage;

public class TextFieldStyle {
	String primary;
	String lineColor;
	String textColor;
	String border;
	
	public TextFieldStyle(String mainBackgroundColor, String lineColor, String textColor) {
		primary = mainBackgroundColor;
		this.lineColor = lineColor;
		this.textColor = textColor;	
		this.border = "transparent";
	}
	
	public void setColor(String mainColor) {
		lineColor = mainColor;
	}
	public void setBG(String color) {
		primary = color;
	}
	public void setBorder(String color) {
		border = color;
	}
}
