package clientGUI;

import client.ClientBoundary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * class ClientUI extends Application, the main for the client. init and start
 * the client gui
 *
 */
public class ClientUI extends Application {

	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 5555;
	public static ClientBoundary clientBoundary;
	public static String host;
	public static int port;

	/**
	 * Start the gui, create the different scenes and load the controllers
	 */
	@Override
	public void start(Stage stage) {

		ScenesData data = new ScenesData(stage);
		try {
			// load and init the main menu
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("OrderMenu.fxml"));
			data.orderMenuRoot = loader1.load();
			data.orderMenucontroller = loader1.getController();
			data.orderScene = new Scene(data.orderMenuRoot);
			data.orderMenucontroller.setScenesData(data);
			// load and init the order menu
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			data.mainMenuRoot = loader2.load();
			data.mainMenuController = loader2.getController();
			data.mainScene = new Scene(data.mainMenuRoot);
			data.clientBoundary = clientBoundary;
			data.mainMenuController.setScenesData(data);
			// load and init the connect to server window
			FXMLLoader loader3 = new FXMLLoader(getClass().getResource("ConnectToServerScreen.fxml"));
			data.connectRoot = loader3.load();
			data.connectGuiController = loader3.getController();
			data.connectScene = new Scene(data.connectRoot);
			data.connectGuiController.setScenesData(data);
			data.connectGuiController.setHost(host);
			data.connectGuiController.setPort(port);
			// try to connect to server
			if (clientBoundary.connect(host, port)) {
				// if connected go to the main menu
				stage.setTitle("Main Menu");
				stage.setScene(data.mainScene);
				stage.show();
			} else {
				// if cant connect go to the connect window
				stage.setTitle("Connect to server");
				stage.setScene(data.connectScene);
				stage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Stop the clients, called on exit the window, close the connection and exit
	 * the program
	 */
	@Override
	public void stop() {
		try {
			clientBoundary.quit();
		} catch (Exception e) {
			// do nothing
		}
		System.out.println("End");
		System.exit(1);
	}

	// should have arguments "host" "server ip address"
	public static void main(String[] args) {
		// get the ip and port from the arguments
		host = DEFAULT_HOST;
		port = DEFAULT_PORT;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		clientBoundary = new ClientBoundary();

		// clientBoundary.seth
		launch();
	}
}