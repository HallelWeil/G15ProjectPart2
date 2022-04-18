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
			client = new zerliClientController(host, port);
			System.out.println("connected to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error cant connect to server");
			e.printStackTrace();
		}
	}

	public Order RequestOrder(int Order) {
		msg = zerliClientController.CreateMsg.createGetOrderMsg(Order);
		System.out.println("msg created " + msg);
		client.handleMessageFromClientUI((Object) msg);
		System.out.println("msg sent");
		if (zerliClientController.CreateMsg.getType().equals("Send order"))
			return zerliClientController.CreateMsg.getOrder();
		else {
			return null;
		}
	}

	public void saveOrder(Order old, Order New) {
		msg = zerliClientController.CreateMsg.createSaveMsg(old, New);
		client.handleMessageFromClientUI(msg);

	}

	public int CreateOrder(Order order) throws IOException {
		msg = zerliClientController.CreateMsg.createSendMsg(order);
		client.handleMessageFromClientUI(msg);
		if (zerliClientController.CreateMsg.getType() == "get order request")
			return zerliClientController.CreateMsg.getOrderNum();
		else {
			return -1;
		}

	}

}
