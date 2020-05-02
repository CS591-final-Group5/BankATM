package BankATMDAO;

import java.sql.*;
import java.util.ArrayList;

import BankATMCommon.*;

public class AccountDAO extends Database {

	private Connection conn;
	
	public AccountDAO() {
		try {
			Class.forName(JDBC);
			conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String openNewAccount(String username, String type, String password) {
		int accountNumber = 0;
		try {
			Statement stmt = conn.createStatement();
			boolean repeated = true;
			do {
				accountNumber = (int) ((Math.random() * 9 + 1) * 10000000);
				String  sql_1 = "select * from accounts where accountnumber='" + 
				                String.valueOf(accountNumber) + "'";
	            ResultSet res = stmt.executeQuery(sql_1);
	            if(res.next() == false){
	            	repeated = false;
	            }
			} while (repeated);
			
			String sql_2 = "insert into accounts " + 
					     "values ('" + username + "', '" + String.valueOf(accountNumber) + 
			             "', '" + password + "', " + String.valueOf(0) + ", '" + 
					     type + "')";
            stmt.executeUpdate(sql_2);
            // add a transaction
			ManagerDAO managerDAO = new ManagerDAO();
			String sql_3 = "insert into transactions " +
			               "values ('" + username + 
			               "', '" + generateTid() + "', '" + String.valueOf(accountNumber) + 
			               "', '" + type + 
			               "', '" + managerDAO.getDate(1) + 
			               "', '" + "Open a new account" +
			               "', '" + "" + 
			               "', " + "NULL" + ")";
			stmt.executeUpdate(sql_3);
			managerDAO.closeConn();
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(accountNumber);
	}
	
	public boolean closeAccount(String accountNumber) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from accounts where accountnumber='" + accountNumber + "'";
			if (stmt.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Accounts> getAccounts(String username) {
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
					if (res.getString("type").compareTo("Checking") == 0) {
						c = new CheckingAccounts(res.getDouble("balance"), 
								res.getString("username"), res.getString("accountnumber"));
					}
					else if (res.getString("type").compareTo("Savings") == 0) {
						c = new SavingsAccounts(res.getDouble("balance"), 
								res.getString("username"), res.getString("accountnumber"));
					}
					else if (res.getString("type").compareTo("Securities") == 0) {
						c = new SecuritiesAccounts(res.getDouble("balance"), 
								res.getString("username"), res.getString("accountnumber"));
					}
					accounts.add(c);
				} while(res.next());
				res.close();
	            stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public boolean authenticate(String accountNumber, String password) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts where accountnumber='" + accountNumber + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
	            return false;
			}
			else {
				String strPassword = res.getString("password");
				if (strPassword.compareTo(password) == 0) {
					res.close();
		            stmt.close();
					return true;
				}
				else {
					res.close();
		            stmt.close();
		            return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String generateTid() {
		int cid = 0;
		try {
			Statement stmt = conn.createStatement();
			boolean repeated = true;
			do {
				cid = (int) ((Math.random() * 9 + 1) * 1000000);
				String  sql = "select * from transactions where tid='" + 
				                String.valueOf(cid) + "'";
	            ResultSet res = stmt.executeQuery(sql);
	            if(res.next() == false){
	            	repeated = false;
	            }
			} while (repeated);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(cid);
	}
	
	public boolean depositMoney(String accountNumber, double amount, Transactions transaction) {
		/*
		 * deposit/ request loan: amount > 0
		 * withdrawal: amount < 0
		 */
		try {
			Statement stmt = conn.createStatement();
			String sql_1 = "select * from accounts where accountnumber='" + accountNumber + "'";
			ResultSet res = stmt.executeQuery(sql_1);
			if(res.next() == false){
				res.close();
	            stmt.close();
	            return false;
			}
			else {
				// update balance
				double balance = res.getDouble("balance");
				balance += amount;
				String sql_2 = "update accounts set balance=" + String.valueOf(balance) +
						       " where accountnumber='" + accountNumber + "'";
				stmt.executeUpdate(sql_2);
				// update transaction
				if (transaction != null) {
					ManagerDAO managerDAO = new ManagerDAO();
					String sql_3 = "insert into transactions " +
					               "values ('" + transaction.getUsername() + 
					               "', '" + generateTid() + "', '" + accountNumber + 
					               "', '" + transaction.getType() + 
					               "', '" + managerDAO.getDate(1) + 
					               "', '" + transaction.getDetails() +
					               "', '" + transaction.getTarget() + 
					               "', " + String.valueOf(transaction.getAmount()) + ")";
					stmt.executeUpdate(sql_3);
					// update loans
					if (transaction.getDetails().compareTo(Transactions.TYPE_3) == 0) {
						String sql_4 = "insert into loans " + 
					                   "values ('" + accountNumber + 
					                   "', " + String.valueOf(transaction.getAmount()) + ") " + 
					                   "on duplicate key update accountNumber='" + accountNumber + 
					                   "', amount=amount + " + 
					                   String.valueOf(transaction.getAmount());
						stmt.executeUpdate(sql_4);
					}
					managerDAO.closeConn();
				}
				res.close();
	            stmt.close();
	            return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Loans> getLoans(String username) {
		ArrayList<Loans> loans = new ArrayList<Loans> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts A, loans B " + 
			             "where A.username='" + username + "' " +
					     "and A.accountnumber = B.accountnumber";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
	            System.out.println("None");
			}
			else {
				do {
					Loans l = null;
					l = new Loans(res.getString("accountnumber"), res.getDouble("amount"));
					loans.add(l);
				} while(res.next());
				res.close();
	            stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;
	}
	
}
