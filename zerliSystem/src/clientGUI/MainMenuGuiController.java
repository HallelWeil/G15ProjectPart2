package clientGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import order.Order;

/**
 * the controller for the main menu window
 *
 */
public class MainMenuGuiController {
	private ScenesData data;

	@FXML
	private Label errorTextlabel;

	@FXML
	private Button getOrderBtn;

	@FXML
	private TextField orderNumber;

	/**
	 * get the order by number, move to the order window on success, do nothing if
	 * failed
	 * 
	 * @param event
	 */
	@FXML
	void getOrder(ActionEvent event) {
		int orderId;
		// read the order number, must be a number
		try {
			orderId = Integer.parseInt(orderNumber.getText());
			orderNumber.setStyle("");
		} catch (Exception e) {
			orderNumber.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			errorTextlabel.setText("Error, order number must be a number");
			return;
		}
		Order order;
		// get the order from the server
		order = data.clientBoundary.RequestOrder(orderId);
		// check if the order exist
		if (order == null) {
			orderNumber.setStyle("-fx-text-box-border: #B200ff; -fx-focus-color: #B200ff;");
			errorTextlabel.setText("Error, can't find the order");
		} else {
			orderNumber.setStyle("");
			data.order = order;
			// update the order text fields
			data.orderMenucontroller.setOrder();
			// load the order window
			loadOrder();
		}

	}

	/**
	 * Load the order window
	 */
	private void loadOrder() {
		data.stage.setTitle("Order window");
		data.stage.setScene(data.orderScene);
		data.stage.show();
	}

	public void setScenesData(ScenesData c) {
		data = c;
	}
}
