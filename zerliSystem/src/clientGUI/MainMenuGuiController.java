package clientGUI;

import client.ClientBoundary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import order.Order;

public class MainMenuGuiController {
	private ScenesData data;

	@FXML
	private Label errorTextlabel;

	@FXML
	private Button getOrderBtn;

	@FXML
	private TextField orderNumber;

	@FXML
	void getOrder(ActionEvent event) {
		int orderId;
		try {
			orderId = Integer.parseInt(orderNumber.getText());
		} catch (Exception e) {
			errorTextlabel.setText("error, order number must be a number");
			return;
		}
		Order order;
		order = data.clientBoundary.RequestOrder(orderId);
		if (order == null) {
			errorTextlabel.setText("error, can't find the order");
		}
		else {
			data.order=order;
			data.orderMenucontroller.setOrder();
			loadOrder();
		}

	}

	private void loadOrder() {
		data.stage.setTitle("Order window");
		data.stage.setScene(data.orderScene);
		data.stage.show();
	}

	public void setScenesData(ScenesData c) {
		data = c;
	}
}
