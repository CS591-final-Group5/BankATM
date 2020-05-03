
package BankATMCommon;

public class CurrencyUSD extends Currencies {

	public static String abbr = "USD";
	
	public CurrencyUSD(double amount) {
		super(amount);
	}
	
	public CurrencyUSD(double amount, String type) {
		super(amount);
		if (type.compareTo(CurrencyCNY.abbr) == 0) {
			setAmount(amount / ConvertCurrency.USDtoCNY);
		}
		else if (type.compareTo(CurrencyRUB.abbr) == 0) {
			setAmount(amount / ConvertCurrency.USDtoRUB);
		}
	}

}
