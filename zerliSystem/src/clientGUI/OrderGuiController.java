package clientGUI;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import client.ClientBoundary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import order.Order;

public class OrderGuiController {
	private ScenesData data;
	private Order order;
	private LocalDate localOrderDate;
	private LocalDate localArrivalDate;

	public void setScenesData(ScenesData c) {
		data = c;
	}

	@FXML
	private TextField arrivalDateDay;

	@FXML
	private TextField arrivalDateMonth;

	@FXML
	private TextField arrivalDateYear;

	@FXML
	private Button backBtn;

	@FXML
	private TextField colorChoice;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField greetingCard;

	@FXML
	private TextField orderData;

	@FXML
	private TextField orderDateDay;

	@FXML
	private TextField orderDateMonth;

	@FXML
	private TextField orderDateYear;

	@FXML
	private TextField orderNumber;

	@FXML
	private TextField orderPrice;

	@FXML
	private TextField orderShop;

	@FXML
	private Button saveBtn;

	@FXML
	void goBack(ActionEvent event) {
		errorLabel.setText("");
		data.stage.setTitle("Main Menu");
		data.stage.setScene(data.mainScene);
		data.stage.show();
	}

	@FXML
	void saveOrder(ActionEvent event) {
		Order newOrder = new Order();
		// existing fields
		newOrder.setOrderID(order.getOrderID());
		newOrder.setOrderDate(order.getDate());
		newOrder.setPrice(order.getPrice());
		newOrder.setGreetingCard(order.getGreetingCard());
		newOrder.setShop(order.getShop());
		// new fields
		newOrder.setColor(colorChoice.getText());
		// getting the date
		int day, month, year;
		try {
			year = Integer.parseInt(arrivalDateYear.getText());
			day = Integer.parseInt(arrivalDateDay.getText());
			month = Integer.parseInt(arrivalDateMonth.getText());
		} catch (Exception e) {
			errorLabel.setText("date must be numbers");
			return;
		}
		try {
			localArrivalDate = LocalDate.of(year, month, day);
		} catch (Exception e) {
			errorLabel.setText("thats not a date");
			return;
		}
		newOrder.setDate(localToDate(localArrivalDate));
		// save the order
		if (data.clientBoundary.saveOrder(order, newOrder)) {
			errorLabel.setText("Order saved successfully");
		} else {
			errorLabel.setText("Error while saving massage");
		}
	}

	public void setOrder() {
		if (data.order == null) {
			order = new Order();
		} else {
			order = data.order;
		}
		localOrderDate = dateToLocal(order.getOrderDate());
		localArrivalDate = dateToLocal(order.getDate());
		orderNumber.setText(order.getOrderID() + "");

		orderDateDay.setText(localOrderDate.getDayOfMonth() + "");
		orderDateMonth.setText(localOrderDate.getMonthValue() + "");
		orderDateYear.setText(localOrderDate.getYear() + "");

		orderPrice.setText(order.getPrice() + "");
		orderShop.setText(order.getShop());
		orderData.setText("");
		greetingCard.setText(order.getGreetingCard());

		arrivalDateDay.setText(localArrivalDate.getDayOfMonth() + "");
		arrivalDateMonth.setText(localArrivalDate.getMonthValue() + "");
		arrivalDateYear.setText(localArrivalDate.getYear() + "");

		colorChoice.setText(order.getColor());
		setEditable();
	}

	private Date localToDate(LocalDate localDate) {
		return Date.valueOf(localDate);
	}

	private LocalDate dateToLocal(Date date) {
		return date.toLocalDate();
	}

	private void setEditable() {
		arrivalDateDay.setEditable(true);
		arrivalDateMonth.setEditable(true);
		arrivalDateYear.setEditable(true);
		colorChoice.setEditable(true);
		greetingCard.setEditable(false);
		orderData.setEditable(false);
		orderDateDay.setEditable(false);
		orderDateMonth.setEditable(false);
		orderDateYear.setEditable(false);
		orderNumber.setEditable(false);
		orderPrice.setEditable(false);
		orderShop.setEditable(false);
	}
}
