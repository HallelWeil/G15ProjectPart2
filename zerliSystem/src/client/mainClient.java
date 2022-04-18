package client;

import java.sql.Date;
import java.util.Scanner;

import order.Order;

public class mainClient {

	public static void main(String args[]) {
		ClientBoundary clientboundary = new ClientBoundary("localhost", 5555);
		Order order = createOrder(1212);
		Order order1 = createOrder(1235);
		Scanner s = new Scanner(System.in);
		char ch = ' ';
		while (ch != 'q') {
			System.out.println("q to quit");
			System.out.println("r to request");
			System.out.println("s to save");
			String constr = s.nextLine();
			ch = constr.charAt(0);
			switch (ch) {
			case 'r':
				order = clientboundary.RequestOrder(15);
				System.out.println("the order is:" + order);
				break;
			case 's':
				clientboundary.saveOrder(order, order1);
				System.out.println("order saved");
				break;
			}
		}
	}

	static public Order createOrder(int orderNum) {
		Order order = new Order();
		order.setColor("blue");
		order.setDate(new Date(12122012));
		order.setGreetingCard("i greet you" + orderNum);
		order.setOrderDate(new Date(10012012));
		order.setOrderID(orderNum);
		order.setPrice(100);
		order.setShop("ort braude zerli branch " + orderNum);
		return order;
	}
}
