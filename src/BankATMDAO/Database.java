package BankATMDAO;

import java.sql.*;
import BankATMCommon.*;

public class Database {

	static final String JDBC = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost?useSSL=false&serverTimezone=EST";
	static final String URL_DB = "jdbc:mysql://localhost:3306/bankdb?useSSL=false&serverTimezone=EST";
	/*
	 * MySQL
	 *   - root
	 *   - 123456
	 */
	static final String USERNAME = "root";
	static final String PASSWORD = "123456";
	static final String DBNAME = "bankdb";
	
	public Database() {
		try {
			Class.forName(JDBC);
			Connection conn = null;
			Statement stmt = null;
			/*
			 * Check if the bankdb exists 
			 * This method comes from stackoverflow.com
			 */
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			boolean dbExist = false;
			ResultSet res = conn.getMetaData().getCatalogs();
			while (res.next()) {
				String databaseName = res.getString(1);
				if (databaseName.compareTo("bankdb") == 0) {
					dbExist = true;
					System.out.println("There exists a bankdb db!");
					break;
				}
			}
			res.close();
			/*
			 * If the bankdb exists, just use it
			 * Else, create a bankdb
			 */
			
			if (dbExist) {
				// 
			}
			else {
				System.out.println("There is no bankdb db!");
				stmt = conn.createStatement();
				/*
				 * Create a database called "bankdb"
				 */
				String sql_1 = "create database bankdb";
				stmt.executeUpdate(sql_1);
				stmt.close(); // If dbExist, stmt is null and doesn't need to be closed
				
				Connection connDB = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);;
				Statement stmtDB = connDB.createStatement();
				/*
				 * Create table "users"
				 */
				String sql_2 = "create table users (" +
						       "username varchar(255) not null, " +
						       "password varchar(255) not null, " +
						       "fullname varchar(255) not null, " +
						       "email varchar(255) not null, " + 
						       "primary key( username ))";
				stmtDB.executeUpdate(sql_2);
				/*
				 * Create table "accounts"
				 */
				String sql_3 = "create table accounts (" +
					           "username varchar(255) not null, " +
					           "accountnumber varchar(255) not null, " +
					           "password varchar(255) not null, " +
					           "balance double, " + 
					           "type varchar(255) not null, " +
					           "primary key( accountnumber ))";
				stmtDB.executeUpdate(sql_3);
				/*
				 * Create table "transactions"
				 */
				String sql_4 = "create table transactions (" +
				               "username varchar(255) not null, " +
				               "tid varchar(255) not null, " +
				               "accountnumber varchar(255) not null, " +
				               "type varchar(255) not null, " +
				               "time date, " +
				               "details varchar(255) not null, " +
				               "target varchar(255) not null, " +
				               "amount double not null, " + 
				               "primary key( tid ))";
				stmtDB.executeUpdate(sql_4);
				/*
				 * Create table "collaterals"
				 */
				String sql_5 = "create table collaterals (" +
				               "username varchar(255) not null, " +
				               "cid varchar(255) not null, " +
				               "name varchar(255) not null, " +
				               "primary key( cid ))";
				stmtDB.executeUpdate(sql_5);
				/*
				 * Create table "manager"
				 */
				String sql_6 = "create table manager (" + 
						       "username varchar(255) not null, " +
						       "password varchar(255) not null, " +
						       "fullname varchar(255) not null, " +
						       "email varchar(255) not null, " +
						       "starttime date, " +
						       "currenttime date, " +
						       "primary key( username ))";
				stmtDB.executeUpdate(sql_6);
				
				stmtDB.close();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean findUser(String username) {
		try {
			Class.forName(JDBC);
			Connection conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String  sql = "select * from users where username='" + username + "'";
            ResultSet res = stmt.executeQuery(sql);
            if(res.next() == false){
            	// check if res == null
            	return false;
            }
            res.close();
            stmt.close();
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createUser(String username, String password, String email, String fullname) {
		try {
			Class.forName(JDBC);
			Connection conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String sql = "insert into users " + 
					     "values ('" + username + "', '" + password + 
			             "', '" + email + "', '" + fullname + "')";
            stmt.executeUpdate(sql);
            conn.close();
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean userLogin(String username, String password) {
		try {
			Class.forName(JDBC);
			Connection conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String sql = "select * from users where username='" + username + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
				conn.close();
	            stmt.close();
				return false;
			}
			else {
				String dbPassword = res.getString("password");
				res.close();
				conn.close();
	            stmt.close();
				return dbPassword.compareTo(password) == 0 ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void changePassword(String username, String password) {
		/*
		 * Change the password of your BankATM account
		 */
		try {
			Class.forName(JDBC);
			Connection conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String sql = "update users " +
			             "set password='" + password + "' " +
					     "where username='" + username + "'";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void changePassword(String username, String password, int cardId) {
		/*
		 * Change the password of one card in your BankATM account
		 */
		
	}
	
}
