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
				               "accountnumber varchar(255), " +
				               "type varchar(255), " +
				               "time date, " +
				               "details varchar(255), " +
				               "target varchar(255), " +
				               "amount double, " + 
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
						       "profit double, " +
						       "primary key( username ))";
				stmtDB.executeUpdate(sql_6);
				/*
				 * Create table "loans"
				 */
				String sql_7 = "create table loans (" + 
						       "accountnumber varchar(255) not null, " +
						       "amount double, " + 
						       "primary key( accountnumber ))";
				stmtDB.executeUpdate(sql_7);
				/*
				 * Create table stocks
				 */
				String sql_8 = "create table stocks (" + 
						       "stockname varchar(255) not null, " +
					           "amount integer not null, " +
						       "price double not null, " +
					           "primary key( stockname ))";
				stmtDB.executeUpdate(sql_8);
				/*
				 * Create table stockdeal
				 */
				String sql_9 = "create table stockdeal (" + 
						       "sid varchar(255) not null, " +
						       "accountnumber varchar(255) not null, " +
					           "stockname varchar(255) not null, " +
				               "amount integer not null, " +
					           "bidprice double not null, " +
				               "currentprice double not null, " +
				               "type varchar(255) not null, " +
				               "primary key( sid ))";
				stmtDB.executeUpdate(sql_9);
				stmtDB.close();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
