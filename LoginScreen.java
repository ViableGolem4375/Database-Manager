package userInterfacePackage;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginScreen extends Main {
	static Font font = Font.font("Roboto", FontWeight.BOLD, 40);
	private final String backgroundColorSet = "-fx-background-color: #C9595F";
	UserField userName;
	UserField passWord;
	CustomButton loginB;
	CustomButton accountB;
	Label welcomeLabel;
	HBox userNameField;
	HBox passWordField;
	private TextFieldStyle loginFieldStyle = new TextFieldStyle("#FBCE3A","C9595F", "#E0D0B8");
	
	public LoginScreen() {	
		userName = new UserField("Username: ", "#E0D0B8", loginFieldStyle);
		passWord = new UserField("Password: ", "#E0D0B8", loginFieldStyle);
		userName = userName.userField();
		passWord = passWord.userField();
		buildButton();
		buildAccountButton();
		buildLabel();
	}
	
	private void buildButton() {
		ButtonStyle loginButton = new ButtonStyle();
		loginButton.setBackground("#FBCE3A");
		loginButton.setFontChoice("Roboto");
		loginButton.setFontColor(Color.BEIGE);
		loginButton.setFontSize(28);
		loginB = CustomButton.customStaticButton("Login", loginButton);
	}
	
	private void buildAccountButton() {
		ButtonStyle loginButton = new ButtonStyle();
		loginButton.setBackground("#FBCE3A");
		loginButton.setFontChoice("Roboto");
		loginButton.setFontColor(Color.BEIGE);
		loginButton.setFontSize(28);
		accountB = CustomButton.customStaticButton("Create Account", loginButton);
		accountB.setFontSize(16);
	}
	
	private void buildLabel() {
		this.welcomeLabel = new Label("Welcome");
		this.welcomeLabel.setFont(font);
		this.welcomeLabel.setTextFill(Color.BEIGE);
	}
	
	public Scene getScene() {
		Scene loginScreen = new Scene(buildUserSpace(), 500, 450);
		return loginScreen;
	}
	
	private VBox buildUserSpace() {
		VBox loginSpace = new VBox(25, welcomeLabel, userName, passWord, loginB, accountB);
		loginSpace.setStyle(backgroundColorSet);
		loginSpace.setAlignment(Pos.CENTER);
		return loginSpace;
	}
}
