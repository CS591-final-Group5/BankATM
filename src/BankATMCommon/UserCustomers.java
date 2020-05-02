
package BankATMCommon;

import java.util.ArrayList;

public class UserCustomers extends Users implements DailyReport {
	
	private ArrayList<Accounts> alAccounts;
	private ArrayList<Transactions> transactions;
	
	public UserCustomers(String Username, String Password, String Email, String Name) {
		super(Username, Password, Email, Name);
		alAccounts = new ArrayList<Accounts>();
		transactions = new ArrayList<Transactions>();
	}
	
	public ArrayList<Accounts> getAccounts() {
		return alAccounts;
	}
	
	public void addNewAccounts(Accounts account) {
		alAccounts.add(account);
	}
	
	public ArrayList<Transactions> getTransactions() {
		return transactions;
	}
	
	public addNewTransactions(Transactions transaction) {
		transactions.add(transaction);
	}
	
	public dailyReport(Date time) {
		ArrayList<Transactions> report = new ArrayList<Transactions>()
		for(int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getTime().equals(time)) {
				report.add(transactions.get(i));
			}
		}
		return report;
	}
}
