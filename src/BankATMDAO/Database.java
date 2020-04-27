package BankATMDAO;

import java.sql.*;

public class Database {

	static final String JDBC = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost?useSSL=false&serverTimezone=EST";
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
			ResultSet resultSet = conn.getMetaData().getCatalogs();
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.compareTo("bankdb") == 0) {
					dbExist = true;
					System.out.println("There exists a bankdb db!");
					break;
				}
			}
			resultSet.close();
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
				
				String sql = "create database bankdb";
				stmt.executeUpdate(sql);
				stmt.close(); // If dbExist, stmt is null and doesn't need to be closed
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
