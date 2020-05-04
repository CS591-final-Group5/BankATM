package BankATMDAO;

import java.sql.*;
import java.util.ArrayList;

import BankATMCommon.*;

public class CollateralDAO extends Database {

	private Connection conn;
	
	public CollateralDAO() {
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
	
	public void addCollateral(String username, String collateral) {
		try {
			Statement stmt = conn.createStatement();
			boolean repeated = true;
			int cid = 0;
			do {
				cid = (int) ((Math.random() * 9 + 1) * 100000);
				String  sql_1 = "select * FROM collaterals where cid='" + 
				                String.valueOf(cid) + "'";
	            ResultSet res = stmt.executeQuery(sql_1);
	            if(res.next() == false){
	            	repeated = false;
	            }
			} while (repeated);

			String sql_2 = "insert into collaterals " + 
					     "values ('" + username + "', '" +
					     String.valueOf(cid) + "', '" + 
					     collateral + "')";
            stmt.executeUpdate(sql_2);
            stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Collaterals> getCollaterals(String username) {
		ArrayList<Collaterals> collaterals = new ArrayList<Collaterals> ();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from collaterals where username='" + username + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
			}
			else {
				do {
					Collaterals c = new Collaterals(res.getString("username"), 
							res.getString("cid"), res.getString("name"));
					collaterals.add(c);
				} while(res.next());
				res.close();
	            stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collaterals;
	}
	
	public boolean deleteCollateral(String cid) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from collaterals where cid='" + cid + "'";
			if (stmt.executeUpdate(sql) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean hasCollaterals(String username) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from collaterals where username='" + username + "'";
			ResultSet res = stmt.executeQuery(sql);
			if(res.next() == false){
				res.close();
	            stmt.close();
	            return false;
			}
			else {
				res.close();
	            stmt.close();
	            return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
