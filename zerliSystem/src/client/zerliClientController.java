package client;

import java.io.IOException;
import msg.Msg;
import msg.MsgController;
import ocsf.client.AbstractClient;

/**
 * This class overrides some of the methods defined in the abstract
 superclass in order to give more functionality to the client 
 in addition this class like a connector between GUI and Server 
 it handle data from GUI and received data from server
 * @author Ronen
 *
 */
public class zerliClientController extends AbstractClient {
	
	 //Instance variables **********************************************
	
		/**
		 * the Static variable CreateMsg , used to save the Analyzed Data from received Msg
		 to used in the ClientBoundary methods (connected variable between HandleMsgFromServer and ClientBoundary methods)
		 */
	public static boolean awaitResponse = false;
	public static MsgController CreateMsg;
	ClientBoundary clientBoundary;
	
	//Constructors ****************************************************
	
		/**
		 * Constructs an instance of zerliClientController
		 * @param host The host to connect to.
		 * @param port The port to connect on.
		 * @param clientBoundary client boundary 
		 */
	
	public zerliClientController(String host, int port, ClientBoundary clientBoundary) throws IOException {
		super(host, port); // Call the superclass constructor
		CreateMsg = new MsgController();
		openConnection();
		this.clientBoundary = clientBoundary;
	}
	
	//Instance methods ************************************************
	
		/** This method override handleMessageFromServer method and
		 *  handles all data that comes in from the server
		 then send it to MsgController Class to msgParser to Parse the data and saved in createMsg.
		 * @param msg  the received msg from the server 
		 */
	
	@Override
	protected void handleMessageFromServer(Object msg) {
		Msg CastMsg = new Msg();
		if (msg instanceof Msg) {
			CreateMsg.resetParser();
			CastMsg = (Msg) msg;
			CreateMsg.msgParser(CastMsg);
			awaitResponse = false;
			if (CreateMsg.getType().equals("exit")) {
				clientBoundary.serverDisconnect();
			}
		}

	}

	/** This method handles all data coming from the ClientBoundary Methods that coming from GUI
	 * and send to server and wait until handlingMsgFromServer change awaitresponse status to wakeup
	 * 
	 * @param message the received msg from the GUI 
	 */
	
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

	/**
	 * This method terminates the client.
	 */
	
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method displays a message onto the screen.
	 * 
	 * @param message msg to display
	 */
	
	public void display(String message) {
		System.out.println("> " + message);
	}
}
