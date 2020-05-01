
package BankATMCommon;

public class Accounts {
	
	private double balance;
	private String username;
	private String accountNumber;

	public Accounts(double balance, String username, String accountNumber) {
		this.balance = balance;
		this.username = username;
		this.accountNumber = accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
