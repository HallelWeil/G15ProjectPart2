package server;

import java.sql.Date;

import order.Order;

//temp class for dev only
public class DBController {
	/**
	 * temp database controller, doest really do anything
	 * 
	 * @param dBname
	 * @param dBuser
	 * @param dBpassword
	 */
	public DBController(String dBname, String dBuser, String dBpassword) {
		// TODO Auto-generated constructor stub
	}

	public Order getOrdrFromDB(int orderNum) {
		Order order = new Order();
		order.setColor("blue");
		order.setDate(new Date(12122012));
		order.setGreetingCard("i greet you");
		order.setOrderDate(new Date(10012012));
		order.setOrderID(orderNum);
		order.setPrice(100);
		order.setShop("ort braude zerli branch");
		System.out.println("the order is " + order);
		return order;
	}

	public boolean deleteOrder(int oldOrderNumber) {
		System.out.println("deleted order number " + oldOrderNumber);
		return true;
	}

	public boolean saveOrderToDB(Order order) {
		System.out.println("saved order  " + order);
		return true;
	}

}
