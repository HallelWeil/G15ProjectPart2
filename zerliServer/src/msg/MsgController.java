package msg;

import java.io.Serializable;
import java.util.ArrayList;

import order.Order;

/**
 * manages messages, msg parser to get the data from each msg, methods to create
 * messages
 * 
 * @author halel
 * @version 01
 */
public class MsgController {
	// Class variables *********************************************************
	/**
	 * the received msg type
	 */

	private String type;
	/**
	 * the received msg data if relevant
	 */
	private Order order;
	/**
	 * the received msg data if relevant
	 */
	private int orderNum;
	/**
	 * the received msg data if relevant
	 */
	private int OldOrderNumber;

	/**
	 * construct new msg controller
	 */
	public MsgController() {
		resetParser();
	}

	/**
	 * Reset the parser, must be used before calling the msg parser
	 */
	public void resetParser() {
		order = null;
		type = "none";
		orderNum = 0;
		OldOrderNumber = 0;
	}

	/**
	 * Parse the msg, save the relevant data in the class variables. use get to get
	 * the data. caller need to check if the type found was the expected type
	 * 
	 * @param msg
	 * @return true if a legal msg type was found
	 */
	public boolean msgParser(Msg msg) {
		if (msg == null)
			return false;
		type = msg.type;
		switch (type) {// save the relevant data for each type
		case "Send order":
			order = (Order) msg.data;
			break;
		case "get order request":
			orderNum = (int) msg.data;
			break;
		case "save order":
			ArrayList<Serializable> arr = (ArrayList<Serializable>) msg.data;
			OldOrderNumber = (int) arr.get(0);
			order = (Order) arr.get(1);
			orderNum = order.getOrderID();
			break;
		case "error":
		case "completed":
			break;
		default:// no type was found, return false
			return false;
		}
		return true;
	}

	/**
	 * use for msg types "Send order" and "save order"
	 * 
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * use for every msg type
	 * 
	 * @return the msg type
	 */
	public String getType() {
		return type;
	}

	/**
	 * use for msg type "get order request"
	 * 
	 * @return the order id
	 */
	public int getOrderNum() {
		return orderNum;
	}

	/**
	 * use for msg type "save order"
	 * 
	 * @return the old order id
	 */
	public int getOldOrderNumber() {
		return OldOrderNumber;
	}

	/**
	 * create msg type "save order", usnig the old order and a new order
	 * 
	 * @param oldOrder
	 * @param newOrder
	 * @return the msg
	 */
	public static Msg createSaveMsg(Order oldOrder, Order newOrder) {
		Msg msg = new Msg();
		msg.type = "save order";
		ArrayList<Serializable> arr = new ArrayList<Serializable>();
		arr.add(oldOrder.getOrderID());
		arr.add(newOrder);
		msg.data = arr;
		return msg;
	}

	/**
	 * create msg type "Send order" with order as data
	 * 
	 * @param newOrder
	 * @return the msg
	 */
	public Msg createSendMsg(Order newOrder) {
		Msg msg = new Msg();
		msg.type = "Send order";
		msg.data = newOrder;
		return msg;
	}

	/**
	 * create msg type "get order request"
	 * 
	 * @param orderID
	 * @return the msg
	 */
	public Msg createGetOrderMsg(int orderID) {
		Msg msg = new Msg();
		msg.type = "get order request";
		msg.data = orderID;
		return msg;
	}
}
