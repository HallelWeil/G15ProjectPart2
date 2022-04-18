package server;

import java.util.ArrayList;

import ocsf.server.ConnectionToClient;

/**
 * Boundary for the prototype server, create the server controller, manage
 * status log and client table updates and action for the connect/disconnect
 * 
 * @author hallel
 * @version 01
 * 
 */
public class ServerBoundary {

	public boolean updateTable;
	public boolean updateLog;
	public ServerController server;
	private StringBuilder status;
	private DBController dbController;

	public ServerBoundary() {
		super();
		this.updateTable = false;
		this.updateLog = false;
		server = null;
		status = new StringBuilder();
		dbController = null;
	}

	/**
	 * connect to the server and the database, set status for success and failure
	 * 
	 * @param ServerPort -> the server port number
	 * @param DBname     -> the database name string
	 * @param DBuser     -> the database username
	 * @param DBpassword -> the database password
	 */
	public void connect(int ServerPort, String DBname, String DBuser, String DBpassword) {
		try {// try connecting to db
			dbController = new DBController(DBname, DBuser, DBpassword);
			setStatus("Connected to database successfully");// on success
		} catch (Exception ex) {
			System.out.println("ERROR - Could not connect to database!");
			setStatus("ERROR - Could not connect to database!");// on failure
			return;
		}
		server = new ServerController(ServerPort, dbController, this);// create the server
		try {// try activate the server
			server.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
			setStatus("Server not active");
			return;
		}
		setStatus("Server active");
	}

	/**
	 * Disconnect from the server and the database
	 */
	public void disconnect() {
		server.stopListening();
		try {
			server.close();
		} catch (Exception ex) {
		}
		dbController = null;
		server = null;
		setStatus("Server not active");
	}

	/**
	 * get the active clients matrix in the following format: number|clientIp|status
	 * 
	 * @return matrix of strings for the table
	 */
	public String[][] getClientsTable()// return String
	{
		updateTable = false;
		ArrayList<String> clients = server.getActiveClients();
		String[][] stringMatrix = new String[clients.size()][3];
		for (int i = 0; i < clients.size(); i++) {
			stringMatrix[i][0] = "" + (i + 1);
			stringMatrix[i][1] = clients.get(i);
			stringMatrix[i][2] = "Active";
		}
		return stringMatrix;
	}

	/**
	 * return the string for the next line/lines in status log
	 * 
	 * @return string
	 */
	public String getStatus()// return string, next line in log
	{
		updateLog = false;
		String s = status.toString();
		status = new StringBuilder();// empty the log
		return s;// return the log
	}

	/**
	 * set the next line in the status log
	 * 
	 * @param s -> the next line in the status log
	 */
	public void setStatus(String s) {
		status.append("\n\n" + s);
		updateLog = true;
	}
}
