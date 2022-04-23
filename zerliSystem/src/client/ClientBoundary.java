package client;

import java.io.IOException;

import msg.Msg;
import msg.MsgController;
import order.Order;

/**
 * boundary of the client , make the zerliClientController,class contains 3
 * methods and received a requests from Client UI and send to
 * zerliClientController instance to handling msg and send to server
 * 
 * @author Ronen
 *
 */
public class ClientBoundary {

	// Instance variables **********************************************

	private Msg msg;
	private zerliClientController client;

	// Constructors ****************************************************
	/**
	 * this constructor construct a ClientBoundary
	 */
	public ClientBoundary() {
		msg = new Msg();

	}

	/**
	 * connect to the server
	 * 
	 * @param host The host to connect to
	 * @param port The port to connect on.
	 * @return true if connected to server
	 */
	public boolean connect(String host, int port) {
		try {
			client = new zerliClientController(host, port, this);
			System.out.println("connected to server");
			return true;
		} catch (IOException e) {
			System.out.println("Error cant connect to server");
			return false;
		}
	}

	// Instance methods ************************************************
	/**
	 * this method get a order from Client UI and prepare a requestOrder message by
	 * MsgController Class and send the msg request to handlingMsgFromClientUI
	 * method to send to server and waited to save the Details in
	 * zerliClientController.CreateMsg and return the order
	 * 
	 * @param OrderID the ID(Number) of order
	 * @return return the order that it number is orderID
	 */
	public Order RequestOrder(int Order) {
		msg = MsgController.createGetOrderMsg(Order);
		client.handleMessageFromClientUI((Object) msg);
		if (zerliClientController.CreateMsg.getType().equals("Send order"))
			return zerliClientController.CreateMsg.getOrder();
		else {
			return null;
		}
	}

	/**
	 * this method get a old order and new order (updated order) and prepare a
	 * requestOrder message by MsgController Class and send the msg request to
	 * handlingMsgFromClientUI method to send to server and waited to save the
	 * Details in zerliClientController.CreateMsg and return the
	 * status(success/failed to update order).
	 * 
	 * @param old the old order
	 * @param New the new order
	 */
	public boolean saveOrder(Order old, Order New) {
		msg = MsgController.createSaveMsg(old, New);
		client.handleMessageFromClientUI(msg);
		if (zerliClientController.CreateMsg.getType().equals("completed")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * this method exit/stop the system when server disconnected
	 */

	public void serverDisconnect() {
		System.out.println("Server disconnected");
		System.exit(0);
	}

	/**
	 * this method our client used to stop/disconnect/terminate.send a msg to
	 * msgController and prepare ExitMsg and send it to server by
	 * handlemsgfromclientUI
	 */
	public void quit() {
		msg = MsgController.createExitMsg();
		client.handleMessageFromClientUI(msg);
		client.quit();
	}
	/*
	 * public int CreateOrder(Order order) throws IOException { msg =
	 * zerliClientController.CreateMsg.createSendMsg(order);
	 * client.handleMessageFromClientUI(msg); if
	 * (zerliClientController.CreateMsg.getType() == "get order request") return
	 * zerliClientController.CreateMsg.getOrderNum(); else { return -1; }
	 * 
	 * }
	 */
}
