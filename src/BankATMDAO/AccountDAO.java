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
	
	public void closeConn () {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean findUser(String username) {
		/*
		 * Find if there is a user who has the same UserName as 'username' 
		 */
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users where username='" + username + "'";
            ResultSet res = stmt.executeQuery(sql);
            if(res.next() == false){
            	// check if res == null
            	return false;
            }
            res.close();
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String getUsername(String accountNumber) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts where accountnumber='" + accountNumber + "'";
            ResultSet res = stmt.executeQuery(sql);
            res.next();
            String resUsername = res.getString("username");
            res.close();
            stmt.close();
            return resUsername;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void createUser(String username, String password, String email, String fullname) {
		/*
		 * Create a new user account that could sign in BanKATM system
		 */
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into users " + 
					     "values ('" + username + "', '" + password + 
			             "', '" + email + "', '" + fullname + "')";
            stmt.executeUpdate(sql);
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean userLogin(String username, String password) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users where username='" + username + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
				return false;
			}
			else {
				String dbPassword = res.getString("password");
				res.close();
	            stmt.close();
				return dbPassword.compareTo(password) == 0 ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public void changePassword(String username, String password) {
		/*
		 * Change the password of your BankATM account
		 */
		try {
			Statement stmt = conn.createStatement();
			String sql = "update users " +
			             "set password='" + password + "' " +
					     "where username='" + username + "'";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String openNewAccount(String username, String type, String password) {
		/*
		 * Open a savings/checking/securities account
		 */
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
	
	public boolean closeAccount(String username, String accountNumber, String type) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from accounts where accountnumber='" + accountNumber + "'";
			if (stmt.executeUpdate(sql) > 0) {
				ManagerDAO managerDAO = new ManagerDAO();
				String sql_3 = "insert into transactions " +
				               "values ('" + username + 
				               "', '" + generateTid() + "', '" + accountNumber + 
				               "', '" + type + 
				               "', '" + managerDAO.getDate(1) + 
				               "', '" + "Close a account" +
				               "', '" + "" + 
				               "', " + "NULL" + ")";
				stmt.executeUpdate(sql_3);
				managerDAO.closeConn();
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
		int tid = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from transactions";
			ResultSet res = stmt.executeQuery(sql);
			if (res.next() == false) {
				res.close();
				stmt.close();
				return "1000000";
			}
			else {
				do {
					String TID = res.getString("tid");
					if (Integer.valueOf(TID) > tid) {
						tid = Integer.valueOf(TID);
					}
				} while(res.next());
				res.close();
				stmt.close();
				return String.valueOf(tid + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
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
	
	public double getBalance(String accountNumber) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts A where accountNumber='" + 
					accountNumber + "'";
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			double resBalance = res.getDouble("balance");
			res.close();
			stmt.close();
			return resBalance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public double getAllBalanceOfSavings(String username) {
		double sum = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts A where username='" + 
					username + "' and type='Savings'";
			ResultSet res = stmt.executeQuery(sql);
			if (res.next() == false) {
				res.close();
				stmt.close();
				return 0;
			}
			else {
				do {
					double resBalance = res.getDouble("balance");
					sum += resBalance;
				} while (res.next());
				res.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
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
	
	public ArrayList<Loans> getAllLoans() {
		ArrayList<Loans> loans = new ArrayList<Loans> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts A, loans B " + 
			             "where A.accountnumber = B.accountnumber";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
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
