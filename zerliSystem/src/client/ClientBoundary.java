package client;

import java.io.IOException;

import msg.Msg;
import msg.MsgController;
import order.Order;

public class ClientBoundary {

	// MsgController CreateMsg;
	private Msg msg;
	private zerliClientController client;

	public ClientBoundary(String host, int port) {
		msg = new Msg();
		try {
			client = new zerliClientController(host, port,this);
			System.out.println("connected to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error cant connect to server");
			e.printStackTrace();
		}
	}

	public Order RequestOrder(int Order) {
		msg = MsgController.createGetOrderMsg(Order);
		client.handleMessageFromClientUI((Object) msg);
		if (zerliClientController.CreateMsg.getType().equals("Send order"))
			return zerliClientController.CreateMsg.getOrder();
		else {
			return null;
		}
	}

	public boolean saveOrder(Order old, Order New) {
		msg = MsgController.createSaveMsg(old, New);
		client.handleMessageFromClientUI(msg);
		if (zerliClientController.CreateMsg.getType().equals("completed")) {
			return true;
		} else {
			return false;
		}

	}

	public void serverDisconnect() {
	     System.out.println("Server disconnected");
			System.exit(0);
	}
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
