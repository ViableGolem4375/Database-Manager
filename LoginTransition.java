package userInterfacePackage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginTransition {

	public static void loginButtonPress(Stage primaryStage, LoginScreen currentScene, HomePage desiredScene) {
		currentScene.loginB.setOnAction(new EventHandler<ActionEvent>() {
		   @Override 
		   public void handle(ActionEvent e) {
			   boolean userMatch = DatabaseSearch.isMatchingUser(currentScene.userName.getText(), desiredScene.testdb.getManager().accessConnection());
			   boolean passMatch = DatabaseSearch.isMatchingPassword(currentScene.passWord.getText(), desiredScene.testdb.getManager().accessConnection());
			   if(userMatch && passMatch) {
				   desiredScene.setName(currentScene.userName.getText());
				   desiredScene.testdb.setUserName(currentScene.userName.getText());
				   primaryStage.setScene(desiredScene.getScene());
				   primaryStage.setFullScreen(true);
			   }
		    }    
		});	
	}
	static void accountButtonPress(Stage primaryStage, Scene accountScene, LoginScreen login) {
		login.accountB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				primaryStage.setScene(accountScene);
				primaryStage.centerOnScreen();
			}
		});
	}
	
	public static void handleLogout(Stage primary, HomePage current) {
		current.logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primary.close();
				Platform.runLater( () -> {
					try {
						new Main().start(new Stage());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				});	
			}	
		});
	}
}
