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
	
}
