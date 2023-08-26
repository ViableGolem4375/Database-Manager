package userInterfacePackage;

import java.util.ArrayList;

public class ValidateUser {
	private static ArrayList<String> users;
	static String duplicateAccountTester;
	
	enum Status {
		OK (""),
		MISMATCH ("These passwords do not match, please try again."),
		EMPTY ("One or more fields was left blank, please try again."),
		ADMIN ("Your username and/or password cannot be admin, please try again."),
		DUPLICATE ("This account already exists, please try again.");
		private final String message;
		
		Status(String message) {
			this.message = message;
		}
	}
	
	public ValidateUser() {
		//users = UserFileReader.readUserFile();
		//String duplicateAccountTester;
	}
	
	static Status validateUser(String userCreate, String passCreate, String passConfirm) {
		//String duplicateAccountTester = ";
		/**
		users = UserFileReader.readUserFile();
		for (int count = 0; count < users.size(); count++) {
			duplicateAccountTester = users.get(count);
		}
		*/
		//userCreate = AccountPage.username.getText();
		//passCreate = AccountPage.password.getText();
		//passConfirm = AccountPage.passConfirm.getText();
		String userData2 = userCreate+"/"+passCreate;
		String userDataAdmin2 = userCreate+"/"+passCreate+"/admin";
		
		if (!(passCreate.equals(passConfirm))) {
			return Status.MISMATCH;
		}
		else if (passCreate.length() < 1 || userCreate.length() < 1) {
			return Status.EMPTY;
		}
		else if (passCreate.equals("admin") || userCreate.equals("/admin")) {
			return Status.ADMIN;
		}
		/**
		else if (duplicateAccountTester.equals(userData2) || duplicateAccountTester.equals(userDataAdmin2)) {
			return Status.DUPLICATE;
		}*/
		else {
			return Status.OK;
		}
	}
	
	public ArrayList<String> getUsers() {
		if (users == null) {
			setUsers(UserFileReader.readUserFile());
		}
		return users;
	}
	
	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}
}
