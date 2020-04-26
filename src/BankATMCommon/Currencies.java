/*
 * 
 * Provide APIs that help convert:
 *    USD to CNY;
 *    USD to RUB;
 *    CNY to USD;
 *    RUB to USD;
 * 
 */


package BankATMCommon;

public class Currencies implements ConvertCurrency {

	protected String abbr;
	
	public Currencies(String abbr) {
		this.abbr = abbr;
	}

	@Override
	public void convert() {
		// TODO Auto-generated method stub
		
	}
	
}
