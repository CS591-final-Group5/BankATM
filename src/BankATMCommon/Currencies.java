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

public abstract class Currencies implements ConvertCurrency {

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
	
	public void setAmount(double amount) {
		this.amount = amount; 
	}
	
}
