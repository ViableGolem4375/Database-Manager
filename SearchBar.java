package userInterfacePackage;

import javafx.scene.layout.HBox;

public class SearchBar extends HBox{
	CustomTextField bar;
	CustomButton theButton;
	
	public SearchBar(CustomTextField search, CustomButton searchButton, int size) {
		super(size);
		bar = search;
		theButton = searchButton;
	}
	
	public static SearchBar createBar(ButtonStyle thisStyle, TextFieldStyle thisField) {
		TextFieldStyle thisTStyle = thisField;
		thisTStyle.setColor("transparent");
		thisTStyle.setBG("transparent");
		thisTStyle.setBorder("white");
		CustomTextField searchField = CustomTextField.customTextField(thisTStyle);
		
		CustomButton searchButton = CustomButton.customStaticButton("Search", thisStyle);
		searchButton.setFontSize(18);
		
		SearchBar thisBar = new SearchBar(searchField, searchButton, 10);
		thisBar.setStyle("-fx-padding: 20");
		thisBar.getChildren().addAll(searchField, searchButton);
		return thisBar;
	}
	
	public String getBarValue() {
		return bar.getText();
	}

}
