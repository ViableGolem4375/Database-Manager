package userInterfacePackage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class UserFileReader {

	static final String DEFAULT_USER_FILE = "nuserinfo.txt";

	public static ArrayList<String> readUserFile() {
		return readUserFile(DEFAULT_USER_FILE);
	}

	public static ArrayList<String> readUserFile(String fileName) {
		Scanner scanner;
		ArrayList<String> list = new ArrayList<String>();
		try {
			scanner = new Scanner(new File (fileName));
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				list.add(line);	
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		return list;
	}
}
