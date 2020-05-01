
package BankATMCommon;

public class CurrencyRUB extends Currencies {

	public static String abbr = "RUB";
	
	public CurrencyRUB(double amount) {
		super(amount);
	}

	public CurrencyRUB(CurrencyUSD USD) {
		super(USD.getAmount());
		this.convert(USDtoRUB);
	}
	
}
