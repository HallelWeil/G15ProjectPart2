package clientGUI;

import client.ClientBoundary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ClientUI extends Application {

	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 5555;
	public static ClientBoundary clientBoundary;

	@Override
	public void start(Stage stage) {

		ScenesData data = new ScenesData(stage);
		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("OrderMenu.fxml"));
			data.orderMenuRoot = loader1.load();
			data.orderMenucontroller = loader1.getController();
			data.orderScene = new Scene(data.orderMenuRoot);
			data.orderMenucontroller.setScenesData(data);

			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			data.mainMenuRoot = loader2.load();
			data.mainMenuController = loader2.getController();
			data.mainScene = new Scene(data.mainMenuRoot);
			data.clientBoundary = clientBoundary;
			data.mainMenuController.setScenesData(data);

			stage.setTitle("Main Menu");
			stage.setScene(data.mainScene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// get the ip and port from the arguments
		String host = DEFAULT_HOST;
		int port = DEFAULT_PORT;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		clientBoundary = new ClientBoundary(host, port);
		launch();
	}
}