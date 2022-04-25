package serverGUI;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import server.ServerBoundary;

/**
 * the server gui controller
 *
 * 
 */
public class ServerGuiController {
	/**
	 * the server boundary, use for the server actions and methods
	 */
	private ServerBoundary server;
	private boolean tableCreated;

	public ServerGuiController() {
		this.server = new ServerBoundary(this);
		tableCreated = false;
	}

	public ServerBoundary getBoundary() {
		return server;
	}

	@FXML
	private TableView<ClientsData> connectionsTable;
	@FXML
	private TableColumn<ClientsData, ?> hostCol;

	@FXML
	private TableColumn<ClientsData, ?> ipCol;
	@FXML
	private TableColumn<ClientsData, ?> numberCol;

	@FXML
	private TableColumn<ClientsData, ?> statusCol;

	@FXML
	private Button disconnectButton;

	@FXML
	private Button connectButton;

	@FXML
	private TextField dbNameField;

	@FXML
	private PasswordField dbPassField;

	@FXML
	private TextField dbUserField;

	@FXML
	private TextField ipField;

	@FXML
	private TextField portField;

	@FXML
	private TextArea console;

	/**
	 * Connecting the server + database
	 * 
	 * @param event
	 */
	@FXML
	void connect(ActionEvent event) {
		// local variables
		int ServerPort;
		@SuppressWarnings("unused")
		String ip, DBname, DBuser, DBpassword;
		// get the fields
		try {
			ServerPort = Integer.parseInt(portField.getText());
		} catch (Exception e) {
			updateConsole("Port must be a number");
			return;
		}
		ip = ipField.getText();// not used
		DBname = dbNameField.getText();
		DBuser = dbUserField.getText();
		DBpassword = dbPassField.getText();
		// connect the server
		try {
			if (server.connect(ServerPort, DBname, DBuser, DBpassword)) {
				updateConsole("server connection succeed");
				connectButton.setDisable(true);
				disconnectButton.setDisable(false);
			} else {
				updateConsole("Couldn't connect server");
			}
		} catch (Exception e) {
			// do nothing
		}
		// init the connection table(empty for now)
		if (tableCreated == false) {
			try {
				initConnectionTable();
			} catch (Exception e) {
				// do nothing
			}
		}
	}

	/**
	 * Disconnecting the server + database
	 * 
	 * @param event
	 */
	@FXML
	void disconnect(ActionEvent event) {
		try {
			server.disconnect();
			connectButton.setDisable(false);
			disconnectButton.setDisable(true);
			updateConsole("Connection is closed");
		} catch (Exception e) {
			updateConsole("couldn't close");
			// e.printStackTrace();
		}
		return;
	}

	/**
	 * Init the clients connections table
	 */
	public void initConnectionTable() {

		// connectionsTable.
		ObservableList<ClientsData> data = server.clientsTable;
		// add listener to the table
		data.addListener(new ListChangeListener<ClientsData>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onChanged(Change c) {
				updateConnectionTable();

			}
		});
		numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		ipCol.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		hostCol.setCellValueFactory(new PropertyValueFactory<>("host"));

		// Adding data to the table
		connectionsTable.setItems(data);
		connectionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableCreated = true;

	}

	public synchronized void updateConnectionTable() {
		// refresh the table
		connectionsTable.refresh();
	}

	/**
	 * Update the console, add a line to the to console
	 * 
	 * @param s -> the added line
	 */
	public synchronized void updateConsole(String s) {
		console.appendText(s + "\n");
	}

}
