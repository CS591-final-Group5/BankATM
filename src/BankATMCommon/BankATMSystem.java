
package BankATMCommon;

import java.util.ArrayList;

public class BankATMSystem {
	
	public static final UserBankManager bankManager = new UserBankManager("BMcpk", "BMcpk", "cpk@bu.edu", "Prof cpk");
	
	private ArrayList<UserCustomers> allUsers;
	
	public BankATMSystem() {
		allUsers = new ArrayList<UserCustomers> ();
	}
	
	public void addNewUser(UserCustomers customer) {
		allUsers.add(customer);
	}
	
	
}
