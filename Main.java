package userInterfacePackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	protected Scene homeScene;
	private Scene loginScene;
	protected Scene currentScene;
	LoginScreen login;
	Stage currentStage;
	AccountPage account;
	Scene accountScene;
	HomePage home;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//currentStage = primaryStage;
		login = new LoginScreen();
		loginScene = login.getScene();
		home = new HomePage();
		home.buildHomeArray();
		account = new AccountPage(primaryStage);
		accountScene = account.getScene();
		currentScene = loginScene;
		primaryStage.setFullScreen(false);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setScene(loginScene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
		    home.testdb2.close();
		    // Save file
		});
		
		
		LoginTransition.accountButtonPress(primaryStage, accountScene, login);
		LoginTransition.loginButtonPress(primaryStage, login, home);	
		LoginTransition.handleLogout(primaryStage, home);
	}
	
	public void setLogin(LoginScreen login) {
		this.login = login;
	}
	
	//Handler for account creation button.
		
}
