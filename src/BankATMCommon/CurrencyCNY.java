
package BankATMCommon;

public class CurrencyCNY extends Currencies {

	public static String abbr = "CNY";
	
	public CurrencyCNY(double amount) {
		super(amount);
	}
	
	public CurrencyCNY(CurrencyUSD USD) {
		super(USD.getAmount());
		this.convert(USDtoCNY);
	}

}
