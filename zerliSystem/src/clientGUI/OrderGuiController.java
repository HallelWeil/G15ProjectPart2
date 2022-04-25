package clientGUI;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import order.Order;

/**
 * The order menu gui controller, manage the order gui, can go back, save and
 * change the color and the arrival date
 * 
 * @author halel
 *
 */
public class OrderGuiController {
	private ScenesData data;
	private Order order;
	// the local dates
	private LocalDateTime localOrderDate;
	private LocalDateTime localArrivalDate;
	// private variables for the date components
	private int day, month, year, hours, minutes;

	public void setScenesData(ScenesData c) {
		data = c;
		initTextfiledListeners();
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
	private TextField arrivalTimeHour;

	@FXML
	private TextField arrivalTimeMin;

	@FXML
	private TextField orderTimeHour;

	@FXML
	private TextField orderTimeMin;

	/**
	 * go back to the main menu
	 * 
	 * @param event
	 */
	@FXML
	void goBack(ActionEvent event) {
		errorLabel.setText("");
		data.stage.setTitle("Main Menu");
		data.stage.setScene(data.mainScene);
		data.stage.show();
	}

	/**
	 * save the order if all the changes fields are correct *
	 * 
	 * @param event
	 */
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
		try {
			year = Integer.parseInt(arrivalDateYear.getText());
			day = Integer.parseInt(arrivalDateDay.getText());
			month = Integer.parseInt(arrivalDateMonth.getText());
			hours = Integer.parseInt(arrivalTimeHour.getText());
			minutes = Integer.parseInt(arrivalTimeMin.getText());
			localArrivalDate = LocalDateTime.of(year, month, day, hours, minutes);
		} catch (Exception e) {
			errorLabel.setText("thats not a date ");
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

	/**
	 * Init the order text fields
	 */
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
		orderTimeHour.setText(localOrderDate.getHour() + "");
		orderTimeMin.setText(localOrderDate.getMinute() + "");

		orderPrice.setText(order.getPrice() + "");
		orderShop.setText(order.getShop());
		orderData.setText("");
		greetingCard.setText(order.getGreetingCard());

		year = localArrivalDate.getYear();
		month = localArrivalDate.getMonthValue();
		day = localArrivalDate.getDayOfMonth();
		hours = localArrivalDate.getHour();
		minutes = localArrivalDate.getMinute();
		arrivalDateDay.setText(day + "");
		arrivalDateMonth.setText(month + "");
		arrivalDateYear.setText(year + "");
		arrivalTimeHour.setText(hours + "");
		arrivalTimeMin.setText(minutes + "");

		colorChoice.setText(order.getColor());

		orderData.setText(order.getOrderData());
		setEditable();
	}

	/**
	 * change local time to timestamp
	 * 
	 * @param localDate
	 * @return
	 */
	private Timestamp localToDate(LocalDateTime localDate) {
		String timeString = localDate.toLocalDate().toString() + " " + localDate.toLocalTime().toString();
		if (localDate.toLocalTime().getSecond() == 0)
			timeString += ":00";
		return java.sql.Timestamp.valueOf(timeString);
	}

	/**
	 * change timestamp to local time
	 * 
	 * @param date
	 * @return
	 */
	private LocalDateTime dateToLocal(Timestamp date) {
		LocalDateTime tempDateTime = date.toLocalDateTime();
		return tempDateTime;
	}

	/**
	 * set the fields to editable if can be changed and disable fields that cant be
	 * changed
	 */
	private void setEditable() {
		arrivalDateDay.setEditable(true);
		arrivalDateMonth.setEditable(true);
		arrivalDateYear.setEditable(true);
		arrivalTimeHour.setEditable(true);
		arrivalTimeMin.setEditable(true);
		colorChoice.setEditable(true);

		greetingCard.setEditable(false);
		orderData.setEditable(false);
		orderDateDay.setEditable(false);
		orderDateMonth.setEditable(false);
		orderDateYear.setEditable(false);
		orderTimeHour.setEditable(false);
		orderTimeMin.setEditable(false);
		orderNumber.setEditable(false);
		orderPrice.setEditable(false);
		orderShop.setEditable(false);
		// disable fields
		greetingCard.setDisable(true);
		orderData.setDisable(true);
		orderDateDay.setDisable(true);
		orderDateMonth.setDisable(true);
		orderDateYear.setDisable(true);
		orderTimeHour.setDisable(true);
		orderTimeMin.setDisable(true);
		orderNumber.setDisable(true);
		orderPrice.setDisable(true);
		orderShop.setDisable(true);
	}

	/**
	 * init listeners for the text fields
	 */
	private void initTextfiledListeners() {
		arrivalDateDay.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					int temp = Integer.parseInt(arrivalDateDay.getText());
					LocalDateTime.of(year, month, temp, hours, minutes);
					arrivalDateDay.setStyle("");
				} catch (Exception e) {
					arrivalDateDay.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");

				}
			}
		});
		// month
		arrivalDateMonth.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					int temp = Integer.parseInt(arrivalDateMonth.getText());
					LocalDateTime.of(year, temp, day, hours, minutes);
					arrivalDateMonth.setStyle("");
				} catch (Exception e) {
					arrivalDateMonth.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");

				}
			}
		});
		// year
		arrivalDateYear.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					int temp = Integer.parseInt(arrivalDateYear.getText());
					LocalDateTime.of(temp, month, day, hours, minutes);
					arrivalDateYear.setStyle("");
				} catch (Exception e) {
					arrivalDateYear.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");

				}
			}
		});
		// hour
		arrivalTimeHour.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					int temp = Integer.parseInt(arrivalTimeHour.getText());
					LocalDateTime.of(year, month, day, temp, minutes);
					arrivalTimeHour.setStyle("");
				} catch (Exception e) {
					arrivalTimeHour.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");

				}
			}
		});
		// minutes
		arrivalTimeMin.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					int temp = Integer.parseInt(arrivalTimeMin.getText());
					LocalDateTime.of(year, month, day, hours, temp);
					arrivalTimeMin.setStyle("");
				} catch (Exception e) {
					arrivalTimeMin.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");

				}
			}
		});
	}
}
