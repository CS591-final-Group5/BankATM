package BankATMDAO;

import java.sql.*;

public class Database {

	static final String JDBC = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false&serverTimezone=EST";
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			/*
			 * Check if the bankdb exists 
			 * This method comes from stackoverflow.com
			 */
			boolean dbExist = false;
			ResultSet resultSet = connection.getMetaData().getCatalogs();
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.compareTo("mysql") == 0) {
					dbExist = true;
					System.out.println("There exists a mysql db!");
					break;
				}
			}
			resultSet.close();
			
			/*
			 * If the bankdb exists, just use it
			 * Else, create bankdb
			 */
			if (dbExist) {
				// 
			}
			else {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
