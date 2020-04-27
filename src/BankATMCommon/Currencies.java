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

	protected String abbr;
	protected double amount;
	
	public Currencies(String abbr, double amount) {
		this.abbr = abbr;
		this.amount = amount;
	}

	@Override
	public void convert() {
		// TODO Auto-generated method stub
		
	}
	
}
