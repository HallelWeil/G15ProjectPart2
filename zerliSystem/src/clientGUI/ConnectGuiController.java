package clientGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * gui controller for the Connect window, manage connection to server attempts
 *
 */
public class ConnectGuiController {
	/**
	 * the different scenes data
	 */
	private ScenesData data;
	// private String host;
	private int port;

	public void setHost(String host) {
		// this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setScenesData(ScenesData data) {
		this.data = data;
	}

	@FXML
	private Button connectBtn;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField ipAddress;

	/**
	 * try to connect to the server
	 * 
	 * @param event
	 */
	@FXML
	void tryToConnect(ActionEvent event) {
		if (data.clientBoundary.connect(ipAddress.getText(), port)) {
			errorLabel.setText("");
			data.stage.setTitle("Main Menu");
			data.stage.setScene(data.mainScene);
			data.stage.show();
		} else {
			errorLabel.setText("Can't connect to server");
		}
	}

}