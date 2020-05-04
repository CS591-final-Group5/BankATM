package BankATMCommon;

public class StockDeal {
	
	private String sid;
	private String accountNumber;
	private String stockName;
	private int amount;
	private double bidPrice;
	private double currentPrice;
	private String type;
	
	public StockDeal(String sid, String accountNumber, String stockName, int amount, double bidPrice,
			double currentPrice, String type) {
		super();
		this.sid = sid;
		this.accountNumber = accountNumber;
		this.stockName = stockName;
		this.amount = amount;
		this.bidPrice = bidPrice;
		this.currentPrice = currentPrice;
		this.type = type;
	}

	public String getSid() {
		return sid;
	}
	
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	
	public double getBidPrice() {
		return bidPrice;
	}
	
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
