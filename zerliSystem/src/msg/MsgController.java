package msg;

import java.io.Serializable;
import java.util.ArrayList;

import order.Order;

public class MsgController {

	private Order order;
	private String type;
	private int orderNum;
	private int OldOrderNumber;

	public MsgController() {
		resetParser();
	}

	public void resetParser() {
		order = null;
		type = "none";
		orderNum = 0;
		OldOrderNumber = 0;
	}

	public boolean msgParser(Msg msg) {
		type = msg.type;
		switch (type) {
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
		default:
			return false;
		}
		return true;
	}

	public Order getOrder() {
		return order;
	}

	public String getType() {
		return type;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public int getOldOrderNumber() {
		return OldOrderNumber;
	}

	
	public Msg createSaveMsg(Order oldOrder, Order newOrder) {
		Msg msg = new Msg();
		msg.type = "save order";
		ArrayList<Serializable> arr = new ArrayList<Serializable>();
		arr.add(oldOrder.getOrderID());
		arr.add(newOrder);
		msg.data = arr;
		return msg;
	}

	public Msg createSendMsg(Order newOrder) {
		Msg msg = new Msg();
		msg.type = "Send order";
		msg.data = newOrder;
		return msg;
	}

	public Msg createGetOrderMsg(int orderID) {
		Msg msg = new Msg();
		msg.type = "get order request";
		msg.data = orderID;
		return msg;
	}
}
