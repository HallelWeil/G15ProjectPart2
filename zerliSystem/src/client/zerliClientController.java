package client;

import java.io.IOException;
import msg.Msg;
import msg.MsgController;
import ocsf.client.AbstractClient;

public class zerliClientController extends AbstractClient {
	public static boolean awaitResponse = false;
	public static MsgController CreateMsg;

	public zerliClientController(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
		CreateMsg = new MsgController();
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Msg CastMsg = new Msg();
		if (msg instanceof Msg) {
			CreateMsg.resetParser();
			CastMsg = (Msg) msg;
			CreateMsg.msgParser(CastMsg);
			awaitResponse = false;
		}

	}

	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	public void display(String message) {
		System.out.println("> " + message);
	}
}
