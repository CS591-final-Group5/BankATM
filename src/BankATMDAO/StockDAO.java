package BankATMDAO;

import java.sql.*;
import java.util.ArrayList;

import BankATMCommon.*; 

public class StockDAO extends Database {

	private Connection conn;
	
	public StockDAO() {
		try {
			Class.forName(JDBC);
			conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn () {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean findStock(String stockName) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from stocks where stockname='" + 
			             stockName + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
	            return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void createStock(Stocks stock) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into stocks " + 
					     "values ('" + stock.getStockName() + 
					     "', " + String.valueOf(stock.getAmount()) + 
			             ", " + String.valueOf(stock.getPrice()) + ")";
            stmt.executeUpdate(sql);
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrice(String stockName, double price) {
		try {
			Statement stmt = conn.createStatement();
			String sql_1 = "update stocks " +
			               "set price=" + String.valueOf(price) + " " +
					       "where stockname='" + stockName + "'";
			stmt.executeUpdate(sql_1);
			
			String sql_2 = "update stockdeal " +
		                   "set currentprice=" + String.valueOf(price) + " " +
				           "where stockname='" + stockName + "' and " +
		                   "type='Hold'";
			stmt.executeUpdate(sql_2);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Stocks> getAllStocks() {
		ArrayList<Stocks> stocks = new ArrayList<Stocks> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from stocks";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
			}
			else {
				do {
					Stocks s = null;
					s = new Stocks(res.getString("stockname"), 
							res.getInt("amount"), 
							res.getDouble("price"));
					stocks.add(s);
				} while(res.next());
				res.close();
	            stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stocks;
	}
	
	public String generateSid() {
		int sid = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from stockdeal";
			ResultSet res = stmt.executeQuery(sql);
			if (res.next() == false) {
				res.close();
				stmt.close();
				return "8000000";
			}
			else {
				do {
					String SID = res.getString("sid");
					if (Integer.valueOf(SID) > sid) {
						sid = Integer.valueOf(SID);
					}
				} while(res.next());
				res.close();
				stmt.close();
				return String.valueOf(sid + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public ArrayList<Accounts> getSecuritiesAccounts(String username) {
		ArrayList<Accounts> accounts = new ArrayList<Accounts> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts where username='" + username + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
			}
			else {
				do {
					Accounts c = null;
					if (res.getString("type").compareTo("Securities") == 0) {
						c = new SecuritiesAccounts(res.getDouble("balance"), 
								res.getString("username"), res.getString("accountnumber"));
						accounts.add(c);
					}
				} while(res.next());
				res.close();
	            stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public ArrayList<StockDeal> getStockDeal(String username) {
		ArrayList<StockDeal> stockDeal = new ArrayList<StockDeal> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts A, stockdeal B " + 
			             "where A.username='" + username + "' " +
					     "and A.accountnumber=B.accountnumber ";
			ResultSet res = stmt.executeQuery(sql);
			if (res.next() == false) {
				res.close();
				stmt.close();
				return stockDeal;
			}
			else {
				do {
					StockDeal s = new StockDeal(res.getString("sid"), res.getString("accountnumber"), 
							res.getString("stockname"), res.getInt("amount"), res.getDouble("bidprice"), 
							res.getDouble("currentprice"), res.getString("B.type"));
					stockDeal.add(s);
				} while(res.next());
				res.close();
				stmt.close();
				return stockDeal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockDeal;
	}
	
	public void buyStock(StockDeal stockDeal) {
		try {
			Statement stmt = conn.createStatement();
			String sql_1 = "insert into stockdeal " + 
				     "values ('" + stockDeal.getSid() + 
				     "', '" + stockDeal.getAccountNumber() + 
				     "', '" + stockDeal.getStockName() + 
				     "', " + stockDeal.getAmount() + 
				     ", " + stockDeal.getBidPrice() +
				     ", " + stockDeal.getCurrentPrice() + ", 'Hold')";
			stmt.executeUpdate(sql_1);
			
			String sql_2 = "update accounts " +
			               "set balance=balance-" + 
					       String.valueOf(stockDeal.getAmount() * stockDeal.getCurrentPrice()) +
					       " where accountnumber='" + stockDeal.getAccountNumber() + "'";
			stmt.executeUpdate(sql_2);
			
			String sql_3 = "update stocks " +
			               "set amount=amount-" + String.valueOf(stockDeal.getAmount()) +
			               " where stockname='" + stockDeal.getStockName() + "'";
			stmt.executeUpdate(sql_3);
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void sellStock(StockDeal stockDeal) {
		try {
			Statement stmt = conn.createStatement();

			String sql_1 = "update stockdeal " +
			               "set type='Sold' " +
					       "where sid='" + stockDeal.getSid() + "'";
			stmt.executeUpdate(sql_1);
			
			String sql_2 = "update accounts " +
			               "set balance=balance+" + 
					       String.valueOf(stockDeal.getAmount() * stockDeal.getCurrentPrice()) +
					       " where accountnumber='" + stockDeal.getAccountNumber() + "'";
			stmt.executeUpdate(sql_2);
			
			String sql_3 = "update stocks " +
			               "set amount=amount+" + String.valueOf(stockDeal.getAmount()) +
			               " where stockname='" + stockDeal.getStockName() + "'";
			stmt.executeUpdate(sql_3);
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public StockDeal getStockDealWithSID(String sid) {
		StockDeal stockdeal = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from stockdeal " + 
			             "where sid='" + sid + "'";
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			stockdeal = new StockDeal(res.getString("sid"), res.getString("accountnumber"), 
					res.getString("stockname"), res.getInt("amount"), res.getDouble("bidprice"), 
					res.getDouble("currentprice"), res.getString("type"));
			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockdeal;
	}
	
	public double getRealizedProfit(String username) {
		ArrayList<StockDeal> stockDeal = getStockDeal(username);
		double realizedProfit = 0;
		for (StockDeal s: stockDeal) {
			if (s.getType().compareTo("Sold") == 0) {
				realizedProfit += s.getAmount() * (s.getCurrentPrice() - s.getBidPrice());
			}
		}
		return realizedProfit;
	}
	
	public double getUnrealizedProfit(String username) {
		ArrayList<StockDeal> stockDeal = getStockDeal(username);
		double unrealizedProfit = 0;
		for (StockDeal s: stockDeal) {
			if (s.getType().compareTo("Hold") == 0) {
				unrealizedProfit += s.getAmount() * (s.getCurrentPrice() - s.getBidPrice());
			}
		}
		return unrealizedProfit;
	}
	
}
