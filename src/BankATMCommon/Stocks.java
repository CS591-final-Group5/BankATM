package BankATMCommon;

public class Stocks {

	private String stockName;
	private int amount;
	private double price;
	
	public Stocks(String stockName, int amount, double price) {
		this.stockName = stockName;
		this.amount = amount;
		this.price = price;
	}

	public String getStockName() {
		return stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
