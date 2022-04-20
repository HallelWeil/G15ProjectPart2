package serverGUI;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import server.ServerBoundary;
/**
 * the server gui controller
 * 
 *
 */
public class ServerGuiController {
	private ServerBoundary server;

	public ServerGuiController() {
		this.server = new ServerBoundary();
	}

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

	@FXML
	void connect(ActionEvent event) throws Exception, SQLException {
		int ServerPort;
		String ip,DBname,DBuser,DBpassword;
		//get the fields
		try {
			ServerPort = Integer.parseInt(portField.getText());
		}catch (Exception e) {
			 console.appendText("Port must be a number" + "\n");
			 return;
		}
		DBname = dbNameField.getText();
		DBuser = dbUserField.getText();
		DBpassword = dbPassField.getText();
		//connect the server
		 try {
			 if(server.connect(ServerPort, DBname, DBuser, DBpassword))
			 {
				 console.appendText("SQL connection succeed" + "\n");
					connectButton.setDisable(true);
					disconnectButton.setDisable(false);
			 }
			 else {
				 console.appendText("Couldn't connect DB" + "\n");
			}
		 }catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	void disconnect(ActionEvent event) throws Exception {
		try {
			server.disconnect();
			connectButton.setDisable(false);
			disconnectButton.setDisable(true);
			console.appendText("Connection is closed");
		} catch (Exception e) {
			console.appendText("couldn't close");
			// e.printStackTrace();
		}
		return;
	}
}
