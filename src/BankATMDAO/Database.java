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
	static final String bankMangerUsername = "BMcpk";
	static final String bankMangerPassword = "CS591";
	
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
						       "email varchar(255) not null, " + 
						       "primary key( username ))";
				stmtDB.executeUpdate(sql_2);
				/*
				 * Create table "accounts"
				 */
				String sql_3 = "create table accounts (" +
					           "username varchar(255) not null, " +
					           "accountnumber varchar(255) not null, " +
					           "balance double, " + 
					           "primary key( accountnumber ))";
				stmtDB.executeUpdate(sql_3);
				/*
				 * Create table "transactions"
				 */
				String sql_4 = "create table transactions (" +
				           "username varchar(255) not null, " +
				           "id varchar(255) not null, " +
				           "time date, " +
				           "amount double not null, " + 
				           "primary key( id ))";
				stmtDB.executeUpdate(sql_4);
				
				
				
				
				
				
				
				
				
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
			String sql;
            sql = "select * FROM users where username='" + username + "'";
            ResultSet res = stmt.executeQuery(sql);
            if(res.isBeforeFirst() == false){
            	// check if res == null
            	return false;
            }
            res.close();
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createUser(String username, String password, String email) {
		try {
			Class.forName(JDBC);
			Connection conn = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String sql;
            sql = "insert into users " + 
			      "values ('" + username + "', '" + password + "', '" + email + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void character() {
		try {
			
		} catch (Exception e) {
			
		}
	}
	
}
