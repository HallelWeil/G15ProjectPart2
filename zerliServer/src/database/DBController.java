package database;

import java.sql.ResultSet;
import java.sql.Timestamp;
import order.Order;

/**
 * The database controller, manage connection to database using DBBoundary,
 * create queries and manage the result
 *
 */
public class DBController {
	/**
	 * The database name
	 */
	private String DBname;
	/**
	 * The database boundary
	 */
	private DBBoundry dbBoundry;

	public DBController() {
		dbBoundry = new DBBoundry();
		DBname = "";
	}

	/**
	 * Connect to the Driver and on success to the database
	 * 
	 * @param DBName : the database name('root' for example)
	 * @param DBUser : the database username
	 * @param DBPass : the database password
	 * @throws Exception with relevant message
	 */
	public void connectToDB(String DBName, String DBUser, String DBPass) throws Exception {
		this.DBname = DBName;
		if (!dbBoundry.ConnectDriver()) {
			throw new Exception("Error - can't connect to JDBC driver");
		}
		try {
			dbBoundry.ConnectDB(DBName, DBUser, DBPass);
		} catch (Exception e) {
			dbBoundry.disconnectDB();// disconnect the driver
			throw new Exception("Error - can't connect to the database");
		}
	}

	/**
	 * Disconnect from the database
	 */
	public void disConnectFromDB() {
		dbBoundry.disconnectDB();
	}

	/**
	 * Get order from the database
	 * 
	 * @param orderNum the order number
	 * @return the order, null if no such order exist
	 */
	public Order getOrdrFromDB(int orderNum) {
		Order order = new Order();
		// create the query
		String s = "SELECT * FROM " + DBname + ".orders WHERE (orderNumber = " + orderNum + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (res.next()) {
				order.setOrderID(res.getInt("orderNumber"));
				order.setPrice(res.getDouble("price"));
				order.setGreetingCard(res.getString("greetingCard"));
				order.setColor(res.getString("color"));
				order.setShop(res.getString("shop"));
				order.setDate(res.getTimestamp("date"));
				order.setOrderDate(res.getTimestamp("orderDate"));
				order.setOrderData(res.getString("dOrder"));
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return order;
	}

	/**
	 * Update existing order color and date
	 * 
	 * @param order with updated color and date
	 * @return true if the order was updated
	 */
	public boolean updateOrder(Order order) {
		// calculate the time
		Timestamp temp = order.getDate();
		temp.setTime(temp.getTime() + 60000 * 150);
		// create the query
		String s = "UPDATE  " + DBname + ".orders  SET color = '" + order.getColor() + "', date = '" + temp.toString()
				+ "' WHERE orderNumber = " + order.getOrderID() + " ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	// for future use
	public boolean saveOrderToDB(Order order) {
		String s = "INSERT INTO " + DBname + "orders VALUES ('" + order.getColor() + "','" + order.getGreetingCard()
				+ "','" + " " + "','" + order.getPrice() + "','" + order.getShop() + "','" + order.getDate() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	// for future use
	public void deleteOrder() {

	}
}
