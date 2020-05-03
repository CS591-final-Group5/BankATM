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
			if (date == current_date) {
				halt = true;
			}
			else {
				c.set(Calendar.DAY_OF_MONTH, 1);
			}
		} while (halt);
		return dates;
	}
	
	public boolean compareDates(Date day1, Date day2) {
		/*
		 * Only compare year, month and day
		 */
		//String str1 = Date.valueOf(day1.toString());
		
		return false;
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
	
	public boolean verification(String username, String password) {
		if (username.compareTo(bankManagerUsername) == 0 &&
				password.compareTo(bankManagerPassword) == 0) {
			return true;
		}
		return false;
	}
	
}
