package order;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final int GREETING_CARD_MAX_SIZE = 200;
	private int orderID;
	private double price;
	private String greetingCard;
	private String color;
	private String shop;
	private Date date;
	private Date orderDate;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		if (orderID >= 0)
			this.orderID = orderID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price > 0)
			this.price = price;
	}

	public String getGreetingCard() {
		return greetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		if (greetingCard != null)
			if (greetingCard.length() <= GREETING_CARD_MAX_SIZE)
				this.greetingCard = greetingCard;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if (color != null)
			this.color = color;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		if (shop != null)
			this.shop = shop;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if (date != null)
			this.date = date;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		if (orderDate != null)
			this.orderDate = orderDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
