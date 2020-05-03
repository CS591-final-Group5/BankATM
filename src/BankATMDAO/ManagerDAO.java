package BankATMDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import BankATMCommon.*;

public class ManagerDAO extends Database {

	private Connection conn;
	private String bankManagerUsername = "BMcpk";
	private String bankManagerPassword = "CS591";
	
	public ManagerDAO() {
		try {
			Class.forName(JDBC);
			conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
			String sql = "select * from manager";
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				System.out.println("ahhahaha");
				createAccount();
			}
			res.close();
			stmt.close();
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

	public Date getDate(int param) {
		/*
		 * @param
		 * return start date if param = 0
		 * return current date if param = 1
		 */
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from manager";
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			return param == 0 ? res.getDate("starttime") : res.getDate("currenttime");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addDate() {
		Date date = getDate(1);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		Date newDate = new Date(c.getTimeInMillis());
		try {
			Statement stmt = conn.createStatement();
			String sql = "update manager " +
		                 "set currenttime='" + newDate + "' " +
				         "where username='" + bankManagerUsername + "'";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createAccount() {
		try {
			Statement stmt = conn.createStatement();
			java.util.Date jd = new java.util.Date();
			Date date = new Date(jd.getTime()); // sql date
			String sql = "insert into manager " + 
				         "values ('" + bankManagerUsername + "', '" + bankManagerPassword + 
				         "', '" + "CHRISTINE PAPADAKIS-KANARIS" + 
				         "', '" + "cpk@bu.edu" + "', '" + date + 
				         "', '" + date + "', " + 0 + ")";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Date> getAllDates() {
		Date start_date = null;
		Date current_date = null;
		ArrayList<Date> dates = new ArrayList<Date> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from manager";
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			start_date = res.getDate("starttime");
			current_date = res.getDate("currenttime");
			res.close();
            stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(start_date);
		boolean halt = false;
		do {
			Date date = new Date(c.getTimeInMillis());
			dates.add(date);
			if (date.toString().compareTo(current_date.toString()) == 0) {
				halt = true;
			}
			else {
				System.out.println("in");
				c.add(Calendar.DAY_OF_MONTH, 1);
			}
		} while (!halt);
		return dates;
	}
	
	public boolean authenticate(String username, String password) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from manager";
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			if (res.getString("username").compareTo(username) == 0 &&
					res.getString("password").compareTo(password) == 0) {
				return true;
			}
			res.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void changePassword(String username, String password) {
		/*
		 * Change the password of manager's account
		 */
		try {
			Statement stmt = conn.createStatement();
			String sql = "update manager " +
			             "set password='" + password + "' " +
					     "where username='" + username + "'";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chargeFee(double fee) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "update manager " +
			             "set profit=profit + " + String.valueOf(fee);
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public double getProfit() {
		/*
		 * Get the profit you have earned
		 */
		double profit = 0;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from manager";
            ResultSet res = stmt.executeQuery(sql);
            res.next();
            profit = res.getDouble("profit");
            res.close();
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profit;
	}
	
	public ArrayList<String> getAllUser() {
		/*
		 * Get all users
		 */
		ArrayList<String> users = new ArrayList<String> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users";
            ResultSet res = stmt.executeQuery(sql);
            if(res.next() == false){
            	res.close();
                stmt.close();
            	return users;
            }
            else {
            	do {
            		users.add(res.getString("username"));;
            	} while (res.next());
            	res.close();
                stmt.close();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public String getBankMangerUsername() {
		return bankManagerUsername;
	}

	public String getBankMangerPassword() {
		return bankManagerPassword;
	}

	public void setBankMangerPassword(String bankMangerPassword) {
		this.bankManagerPassword = bankMangerPassword;
	}
	
}
