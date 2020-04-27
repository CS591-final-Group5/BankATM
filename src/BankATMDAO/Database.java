package BankATMDAO;

import java.sql.*;

public class Database {

	static final String JDBC = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/mysql";
	static final String USERNAME = "cs591";
	static final String PASSWORD = "591cs";
	static final String DBNAME = "bankdb";
	
	public Database() {
		
	}
	
	public void test() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection connection = DriverManager.getConnection(URL);
//		/*
//		 * Check if the bankdb exists 
//		 */
//		boolean dbExist = false;
//		ResultSet resultSet = connection.getMetaData().getCatalogs();
//		while (resultSet.next()) {
//			String databaseName = resultSet.getString(1);
//			if (databaseName.compareTo("mysql") == 0) {
//				dbExist = true;
//				System.out.println("There exists a mysql db!");
//				break;
//			}
//		}
//		resultSet.close();
	}
	
}
