
package BankATMCommon;

public class SavingsAccounts extends Accounts {

	public static double MINSUM = 2500;
	
	public SavingsAccounts(double balance, String username, String accountNumber) {
		super("Savings", balance, username, accountNumber);
	}

}
