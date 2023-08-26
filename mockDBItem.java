package userInterfacePackage;

public class mockDBItem {
	String ID;
	String Description;
	String Quantity;
	
	public mockDBItem(String iDval, String dVal, String qVal) {
		ID = iDval;
		Description = dVal;
		Quantity = qVal;
	}
	
	public void printValues() {
		System.out.println(ID + Description + Quantity);
		
	}
}
