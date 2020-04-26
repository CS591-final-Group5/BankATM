
package BankATMCommon;

import java.util.ArrayList;

public class UserCustomers extends Users {
	
	private ArrayList<Accounts> alAccounts;
	
	public UserCustomers(String Username, String Password, String Email) {
		super(Username, Password, Email);
		alAccounts = new ArrayList<Accounts> ();
	}
	
	public ArrayList<Accounts> getAccounts() {
		return alAccounts;
	}
	
	public void addNewAccounts(Accounts account) {
		alAccounts.add(account);
	}
	
}
