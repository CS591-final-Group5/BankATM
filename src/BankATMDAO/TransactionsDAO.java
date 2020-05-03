package BankATMDAO;

import java.sql.*;
import java.util.ArrayList;

import BankATMCommon.*;

public class TransactionsDAO extends Database {

private Connection conn;
	
	public TransactionsDAO() {
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
	
	public ArrayList<Transactions> getTransactions(String username) {
		ArrayList<Transactions> transactions = new ArrayList<Transactions> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from transactions where username='" + username + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
			}
			else {
				do {
					Transactions t = null;
					t = new Transactions(res.getString("username"), res.getString("tid"), 
							res.getString("accountnumber"), res.getString("type"), 
							res.getDate("time"), res.getString("details"), 
							res.getString("target"), res.getDouble("amount"));
					transactions.add(t);
				} while(res.next());
				res.close();
	            stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
}
