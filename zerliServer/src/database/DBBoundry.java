package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * The database boundary, manage connection to the driver+ database and sending
 * queries + receiving results
 * 
 */
public class DBBoundry {
	/**
	 * The connection to the database
	 */
	static Connection conn;

	/**
	 * Send query to the database. Support select and update
	 * 
	 * @param query
	 * @return : RsultSet on select, boolean on update
	 */
	public Object sendQueary(String query) {
		Statement stmt;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			// get the query type from the first word in the string
			switch (query.split(" ")[0]) {
			case "SELECT":
				res = stmt.executeQuery(query);
				return res;
			case "UPDATE":
				int res2 = stmt.executeUpdate(query);
				return res2 == 0 ? false : true;
			default:
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Connect the JDBC driver
	 * 
	 * @return false if failed
	 */
	public boolean ConnectDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Connect to the database
	 * 
	 * @param DBName : the database name('root' for example)
	 * @param DBUser : the database username
	 * @param DBPass : the database password
	 * @throws SQLException : if the connection failed
	 */
	public void ConnectDB(String DBName, String DBUser, String DBPass) throws SQLException {

		conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DBName + "?serverTimezone=IST", DBUser, DBPass);
	}

	/**
	 * disconnect from the database
	 * 
	 * @return false if the disconnect failed
	 */
	public boolean disconnectDB() {
		try {
			DriverManager.drivers().close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
