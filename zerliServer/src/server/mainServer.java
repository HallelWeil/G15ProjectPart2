package server;

import java.util.Scanner;

//temp class for testing
public class mainServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerBoundary sb = new ServerBoundary();
		char c = ' ';
		Scanner scanner = new Scanner(System.in);
		sb.connect(5555, "0", "0", "0");
		while (c != 'q') {
			if (sb.updateLog) {
				System.out.println("Status:");
				System.out.println(sb.getStatus());
			}
			if (sb.updateTable) {
				System.out.println("client table:");
				System.out.println("number|ip		|status");
				System.out.println(sb.getClientsTable());
			}
			System.out.println("q to quit");
			System.out.println("c to connect");
			System.out.println("d to disconnect");
			System.out.println("a to sendall");
			String str = scanner.nextLine();
			c = str.charAt(0);
			switch (c) {
			case 'c':
				sb.connect(5555, "0", "0", "0");
				break;
			case 'd':
				sb.disconnect();
				break;
			case 'a':
				sb.server.sendToAllClients("server is active");
				break;
			default:
				break;
			}

		}
	}

}
