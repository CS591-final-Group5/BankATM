
package BankATMCommon;

import java.util.ArrayList;

public class UserCustomers extends Users {
	
	private ArrayList<Accounts> alAccounts;
	private Transactions transactions;
	
	public UserCustomers(String Username, String Password, String Email, String Name) {
		super(Username, Password, Email, Name);
		alAccounts = new ArrayList<Accounts> ();
	}
	
	public ArrayList<Accounts> getAccounts() {
		return alAccounts;
	}
	
	public void addNewAccounts(Accounts account) {
		alAccounts.add(account);
	}
	
}
