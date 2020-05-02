package BankATMDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDAO extends Database {

	private Connection conn;
	private String bankMangerUsername = "BMcpk";
	private String bankMangerPassword = "CS591";
	
	public ManagerDAO() {
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

	public String getBankMangerUsername() {
		return bankMangerUsername;
	}

	public String getBankMangerPassword() {
		return bankMangerPassword;
	}

	public void setBankMangerPassword(String bankMangerPassword) {
		this.bankMangerPassword = bankMangerPassword;
	}
	
	public boolean verification(String username, String password) {
		if (username.compareTo(bankMangerUsername) == 0 &&
				password.compareTo(bankMangerPassword) == 0) {
			return true;
		}
		return false;
	}
	
}
