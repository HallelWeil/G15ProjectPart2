package server;

import java.util.ArrayList;

import database.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import msg.Msg;
import msg.MsgController;
import serverGUI.ClientsData;
import serverGUI.ServerGuiController;

/**
 * Boundary for the prototype server, create the server controller, manage
 * status log and client table updates and action for the connect/disconnect
 * 
 * @author hallel
 * @version 01
 * 
 */
public class ServerBoundary {

	private ServerController server;
	private ArrayList<String> status;
	private DBController dbController;
	private ServerGuiController guiController;
	public ObservableList<ClientsData> clientsTable;
	private int clientsCount = 1;

	public ServerBoundary(ServerGuiController guiController) {
		super();
		this.guiController = guiController;
		server = null;
		status = new ArrayList<String>();
		dbController = new DBController();
		clientsTable = FXCollections.observableArrayList();
	}

	/**
	 * connect to the server and the database, set status for success and failure
	 * 
	 * @param ServerPort -> the server port number
	 * @param DBname     -> the database name string
	 * @param DBuser     -> the database username
	 * @param DBpassword -> the database password
	 */
	public boolean connect(int ServerPort, String DBname, String DBuser, String DBpassword) {
		try {// try connecting to db
			dbController.connectToDB(DBname, DBuser, DBpassword);
			setStatus("Connected to database successfully");// on success
		} catch (Exception ex) {
			// System.out.println("ERROR - Could not connect to database!");
			setStatus(ex.getMessage());// on failure
			return false;
		}
		try {// try activate the server
			server = new ServerController(ServerPort, dbController, this);// create the server
			server.listen(); // Start listening for connections
		} catch (Exception ex) {
			setStatus("ERROR - Could not listen for clients!");
			setStatus("Server not active");
			return false;
		}
		setStatus("Server active");
		return true;
	}

	/**
	 * Disconnect from the server and the database
	 */
	public void disconnect() {
		// tell the clients we disconnect
		Msg msg = MsgController.createExitMsg();
		try {
			server.sendToAllClients(msg);
		} catch (Exception e) {
			// do nothing
		}
		// stop the server
		server.stopListening();
		try {
			server.close();
		} catch (Exception ex) {
		}
		dbController.disConnectFromDB();
		server = null;
		setStatus("Server not active");

	}

	/**
	 * update the clients table using the client and the status
	 * 
	 */
	public void updateClientsTable(String ipAdress, String status, String host, String name)// return String
	{
		String tempNumber = "";
		ClientsData tempClientData = new ClientsData(tempNumber, ipAdress, status, host, name);
		if (clientsTable.contains(tempClientData)) {
			tempNumber = clientsTable.get(clientsTable.indexOf(tempClientData)).getNumber();
			clientsTable.remove(tempClientData);
		} else {
			tempNumber += clientsCount;
			clientsCount++;
		}
		tempClientData.setNumber(tempNumber);
		clientsTable.add(tempClientData);
	}

	/**
	 * set the next line in the status log
	 * 
	 * @param s -> the next line in the status log
	 */
	public void setStatus(String s) {
		status.add(s);
		guiController.updateConsole(s);
	}

	/**
	 * return the string for the next line/lines in status log
	 * 
	 * @return string
	 */
	public ArrayList<String> getStatus()// return string, next line in log
	{
		ArrayList<String> s = status;
		status = new ArrayList<String>();// empty the log
		return s;// return the log
	}

	public void quit() {
		Msg msg = MsgController.createExitMsg();
		try {
			server.sendToAllClients(msg);
		} catch (Exception e) {
			// do nothing
		}
		disconnect();
	}
}
