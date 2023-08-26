/**
 * @author Matthew Langton
 * This class' purpose is to function as an account creation screen for the software.
 * It contains 3 data entry fields for a username and password, along with a button
 * for creating admin accounts, a button to save the data to an external file, and
 * a button to return to the login screen.
 */

//Imports
package userInterfacePackage;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import userInterfacePackage.ValidateUser.Status;
import javafx.scene.control.RadioButton;
import java.io.IOException;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class AccountPage extends Main {
	/**
	 * Below are all of the variable declarations for the class.
	 */
	Button accountCreateButton;
	CustomButton accountCreateB;
	CustomButton returnB;
	CustomRadioButton adminCheckTrue;
	CustomRadioButton adminCheckFalse;
	Label mainLabel;
	Label userCreateLabel;
	Label passCreateLabel;
	Label passConfirmLabel;
	Label warningLabelShort;
	Label warningLabelNotSame;
	static Label warningLabel;
	static UserField password;
	static UserField username;
	static UserField passConfirm;
	HBox passField;
	HBox usernameField;
	HBox passConfirmField;
	ToggleGroup admin = new ToggleGroup();
	static Font font = Font.font("Roboto", FontWeight.BOLD, 40);
	static Font buttonFont = Font.font("Roboto", FontWeight.BOLD, 15);
	private final String backgroundColorSet = "-fx-background-color: #C9595F";
	private final String idColorSet = "-fx-background-color: #C9595F;";
	static TextFieldStyle searchField = new TextFieldStyle("#372F2F", "#C9595F", "E0D0B8");
	Stage thisStage;
	String duplicateAccountTester;
    public static final String MESSAGE_PASSWORD_MISMATCH = "These passwords do not match, please try again.";
    public static final String MESSAGE_PASSWORD_EMPTY = "One or more fields was left blank, please try again.";
    public static final String MESSAGE_PASSWORD_ADMIN = "Your username and/or password cannot be admin, please try again.";
    public static final String MESSAGE_PASSWORD_DUPLICATE = "This account already exists, please try again.";
    public static final String MESSAGE_ACC_CREATED = "Account created successfully.";
    public static final String MESSAGE_ADMIN_ACC_CREATED = "Administrator account created successfully.";
		
	/**
	 * Below is the constructor for the class which applies all of the below methods.
	 */
		public AccountPage(Stage primaryStage) {	
			username = new UserField("Enter Username: ", "#E0D0B8", AccountPage.searchField);
			password = new UserField("Enter Password: ", "#E0D0B8", AccountPage.searchField);
			passConfirm = new UserField("Confirm Password: ", "#E0D0B8", AccountPage.searchField);
			username = username.userField();
			password = password.userField();
			passConfirm = passConfirm.userField();
			buildAccountCreateButton();
			buildReturnButton();
			buildAdminTrueButton();
			buildAdminFalseButton();
			buildLabelOne();
			buildLabelFour();
			//accountCreateButtonPress(primaryStage);
			accountButtonPress(primaryStage);
			returnButtonPress(primaryStage);
		}
		
		/**
		 * These methods create all of the visual objects on screen.
		 * The first two create the buttons for account creation and going back to the login screen.
		 */
		private void buildAccountCreateButton() {
			ButtonStyle accountCreateButton = new ButtonStyle();
			accountCreateButton.setBackground("#FBCE3A");
			accountCreateButton.setFontChoice("Roboto");
			accountCreateButton.setFontColor(Color.BEIGE);
			accountCreateButton.setFontSize(28);
			accountCreateB = CustomButton.customStaticButton("Create Account", accountCreateButton);
		}

		private void buildReturnButton() {
			ButtonStyle returnButton = new ButtonStyle();
			returnButton.setBackground("#FBCE3A");
			returnButton.setFontChoice("Roboto");
			returnButton.setFontColor(Color.BEIGE);
			returnButton.setFontSize(20);
			returnB = CustomButton.customStaticButton("Return to Main Menu", returnButton);
		}
		
		/**
		 * These next two methods create the radio buttons for creating admin accounts.
		 */
		private void buildAdminTrueButton() {
			radioButtonStyle trueButton = new radioButtonStyle();
			trueButton.setRadioFontChoice("Roboto");
			trueButton.setRadioFontColor(Color.BEIGE);
			trueButton.setRadioFontSize(15);
			trueButton.setToggleGroup(admin);
			adminCheckTrue = CustomRadioButton.customStaticRadioButton("<- Yes this is an admin account", trueButton);
			
		}
		
		private void buildAdminFalseButton() {
			radioButtonStyle falseButton = new radioButtonStyle();
			falseButton.setRadioFontChoice("Roboto");
			falseButton.setRadioFontColor(Color.BEIGE);
			falseButton.setRadioFontSize(15);
			falseButton.setToggleGroup(admin);
			falseButton.setSelected(true);
			adminCheckFalse = CustomRadioButton.customStaticRadioButton("<- No this is not an admin account", falseButton);
		}
		
		/**
		 * This label creator adds the text at the top of the window.
		 */
		private void buildLabelOne() {
			this.mainLabel = new Label("Create Account");
			this.mainLabel.setFont(font);
			this.mainLabel.setTextFill(Color.BEIGE);
		}
		
		/**
		 * This label creator exists as a blank and is assigned a string value for different purposes.
		 */
		private void buildLabelFour() {
			this.warningLabel = new Label("");
			this.warningLabel.setFont(buttonFont);
			this.warningLabel.setTextFill(Color.BEIGE);
		}
		
		/**
		 * This method is for unit testing purposes to create mock labels.
		 */
		public static void setWarningLabel(Label label) {
			warningLabel = label;
		}
		
		/**
		 * These methods create the window.
		 */
		public Scene getScene() {
			Scene loginScreen = new Scene(buildUserSpace(), 500, 450);
			return loginScreen;
		}
		
		private VBox buildUserSpace() {
			VBox loginSpace2 = new VBox(10, mainLabel, username, password, passConfirm, accountCreateB, returnB, adminCheckTrue, adminCheckFalse, warningLabel);
			loginSpace2.setStyle(backgroundColorSet);
			loginSpace2.setAlignment(Pos.CENTER);
			return loginSpace2;
		}

		/**
		 * This method is what allows the user to create a new account. The method takes in
		 * data from the user entered into the 3 text fields and inserts that data into 
		 * variables. These variables are then tested to ensure they meet the requirements
		 * (i.e. not blank, not a duplicate account, etc.) and then the entered data is
		 * inserted into an external file which will be read by the main class when a user
		 * attempts to log in.
		 */
		/**
		public void accountCreateButtonPress(Stage primaryStage) {
			accountCreateB.setOnAction(new EventHandler<ActionEvent>() {
				@Override
					public void handle(ActionEvent aei) {
					File userFile = new File ("nuserinfo.txt");
					String userCreateValue = username.getText();
					String passCreateValue = password.getText();
					String passConfirmValue = passConfirm.getText();
					String userData2 = userCreateValue+"/"+passCreateValue;
					String userDataAdmin2 = userCreateValue+"/"+passCreateValue+"/admin";
					Scanner scanner;
					
					Status returnMessage = ValidateUser.validateUser(userCreateValue, passCreateValue, passConfirmValue);
					String warningMessage = returnMessage.toString();
					
					if (warningMessage == "MISMATCH") {
						warningLabel.setText(MESSAGE_PASSWORD_MISMATCH);
						}
					else if (warningMessage == "EMPTY") {
						warningLabel.setText(MESSAGE_PASSWORD_EMPTY);
					}
					else if (warningMessage == "ADMIN") {
						warningLabel.setText(MESSAGE_PASSWORD_ADMIN);
					}
					else if (warningMessage == "DUPLICATE") {
						warningLabel.setText(MESSAGE_PASSWORD_DUPLICATE);
					}
					else {
						try {
							userFile.createNewFile();
							if (userFile.createNewFile()) {
								System.out.println("work");
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true));
							if (aei.getSource() == accountCreateB && adminCheckTrue.isSelected() == true && adminCheckFalse.isSelected() == false) {
								writer.write(userCreateValue + "/" + new String(passCreateValue + "/admin"));
								writer.write(System.getProperty("line.separator"));
								writer.close();
								warningLabel.setText(MESSAGE_ADMIN_ACC_CREATED);
							}
							else if (aei.getSource() == accountCreateB && adminCheckTrue.isSelected() == false && adminCheckFalse.isSelected() == true) {
								writer.write(userCreateValue + "/" + new String(passCreateValue));
								writer.write(System.getProperty("line.separator"));
								writer.close();
								warningLabel.setText(MESSAGE_ACC_CREATED);
								}
						}
						catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			
			});
		}
		*/
		
		public void accountButtonPress(Stage primaryStage) {
			accountCreateB.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					InventoryManager testdb = new InventoryManager("test_3.db");
					
					
					if(reportFailure(testdb)) {
						warningLabel.setText(MESSAGE_ACC_CREATED);
						testdb.getManager().addUser(username.getText(), password.getText());
					}
				}
			});
		}
		
		public boolean reportFailure(InventoryManager testdb) {
			boolean passed = true;
			Status returnMessage = ValidateUser.validateUser(username.getText(), password.getText(), passConfirm.getText());
			String warningMessage = returnMessage.toString();
			if(DatabaseSearch.isMatchingUser(username.getText(), testdb.getManager().accessConnection())) {
				warningMessage = "DUPLICATE";
			}
			
			if (warningMessage == "MISMATCH") {
				warningLabel.setText(MESSAGE_PASSWORD_MISMATCH);
				passed = false;
				}
			
			else if (warningMessage == "EMPTY") {
				warningLabel.setText(MESSAGE_PASSWORD_EMPTY);
				passed = false;
			}
			else if (warningMessage == "ADMIN") {
				warningLabel.setText(MESSAGE_PASSWORD_ADMIN);
				passed = false;
			}
			else if (warningMessage == "DUPLICATE") {
				warningLabel.setText(MESSAGE_PASSWORD_DUPLICATE);
				passed = false;
			}
			return passed;
		}
		
		/**
		 * Method to return the user to the main menu, note that this restarts the program 
		 * to do so.
		 */
		private void returnButtonPress(Stage primaryStage) {
			returnB.setOnAction(new EventHandler<ActionEvent>( ) {
				@Override
					public void handle(ActionEvent aeo) {
					primaryStage.close();
					Platform.runLater( () -> {
						try {
							new Main().start(new Stage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
				}
			});
		}
}
