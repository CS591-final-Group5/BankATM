/*
 * 
 * Provide APIs that help convert:
 *    USD to CNY;
 *    USD to RUB;
 *    CNY to USD;
 *    RUB to USD;
 * 
 * Default currency type: USD
 * 
 */


package BankATMCommon;

public class Currencies implements ConvertCurrency {

	protected double amount;
	
	public Currencies(double amount) {
		this.amount = amount;
	}

	@Override
	public void convert(double param) {
		amount *= param;
	}
	
	public double getAmount() {
		return amount;
	}
	
}
