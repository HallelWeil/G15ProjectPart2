package order;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Order entity, implements Serializable and can be used in messages
 * 
 * @author hallel
 *
 */
public class Order implements Serializable {

	/**
	 * the order version, must be the same in both client and server
	 */
	private static final long serialVersionUID = 5L;
	/**
	 * max size for the greeting card field
	 */
	public final int GREETING_CARD_MAX_SIZE = 200;
	/**
	 * The unique order id number
	 */
	private int orderID;
	private double price;
	private String greetingCard;
	private String color;
	private String shop;
	private Timestamp date;
	private Timestamp orderDate;
	private String dOrder;

	/**
	 * Set the order data string, up to 45 characters
	 * 
	 * @param OrderData
	 */
	public void setOrderData(String OrderData) {
		dOrder = OrderData;
	}

	/**
	 * Get the order data string
	 * 
	 * @return the order data string
	 */
	public String getOrderData() {
		return dOrder;
	}

	/**
	 * Get the order id number
	 * 
	 * @return the order id number
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Set the order id, must be positive number
	 * 
	 * @param orderID
	 */
	public void setOrderID(int orderID) {
		if (orderID >= 0)
			this.orderID = orderID;
	}

	/**
	 * Get the order price
	 * 
	 * @return the order price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the order price, must be positive number
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		if (price > 0)
			this.price = price;
	}

	/**
	 * Get the Greeting Card
	 * 
	 * @return the Greeting Card
	 */
	public String getGreetingCard() {
		return greetingCard;
	}

	/**
	 * Set the greeting card string ,must be <= from GREETING_CARD_MAX_SIZE
	 * 
	 * @param greetingCard
	 */
	public void setGreetingCard(String greetingCard) {
		if (greetingCard != null)
			if (greetingCard.length() <= GREETING_CARD_MAX_SIZE)
				this.greetingCard = greetingCard;
	}

	/**
	 * Get the color string
	 * 
	 * @return the color string
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Set the color string, up to 45 characters
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		if (color != null)
			this.color = color;
	}

	/**
	 * Get the shop string
	 * 
	 * @return the shop string
	 */
	public String getShop() {
		return shop;
	}

	/**
	 * Set the shop string, up to 45 characters
	 * 
	 */
	public void setShop(String shop) {
		if (shop != null)
			this.shop = shop;
	}

	/**
	 * Get the order arrival date
	 * 
	 * @return the order arrival date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Set the order arrival date
	 * 
	 * @param date
	 */
	public void setDate(Timestamp date) {
		if (date != null)
			this.date = date;
	}

	/**
	 * Get the order creation date
	 * 
	 * @return the order creation date
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * Set the order creation date
	 * 
	 * @param orderDate
	 */
	public void setOrderDate(Timestamp orderDate) {
		if (orderDate != null)
			this.orderDate = orderDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Get the order as string ain the format:
	 * [id,price,color,shop,arrivalDate,orderDate,orderData,greetingCard]
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		s.append(orderID + ", " + price + ", " + color + ", " + shop + ", ");
		s.append(date + ", " + orderDate + "," + dOrder + "," + greetingCard + "]");
		return s.toString();
	}
}
