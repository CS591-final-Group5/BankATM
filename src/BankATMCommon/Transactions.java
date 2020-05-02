package BankATMCommon;

import java.sql.Date;

public class Transactions {
	
	public static final String TYPE_1 = "Deposit";
	public static final String TYPE_2 = "Withdrawal";
	public static final String TYPE_3 = "Request A Loan";
	public static final String TYPE_4 = "Transfer";
	
	private String username;
	private String tid;
	private String accountnumber;
	private String type;
	private Date time;
	private String details;
	private String target;
	private double amount;
	
	

	public Transactions(String username, String tid, String accountnumber, String type, 
			Date time, String details, String target, double amount) {
		this.username = username;
		this.tid = tid;
		this.accountnumber = accountnumber;
		this.type = type;
		this.time = time;
		this.details = details;
		this.target = target;
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
