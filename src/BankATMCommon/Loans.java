package BankATMCommon;

public class Loans {
	
	private String accountNumber;
	private double amount;

	public Loans(String accountNumber, double amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountnumber) {
		this.accountNumber = accountnumber;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
