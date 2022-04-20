package serverGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ServerUI extends Application {

	@Override
	public void start(Stage stage) {
		AnchorPane root;
		ServerGuiController controller;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/serverGUI/ServerGUI.fxml"));
			root = loader.load();
			controller = loader.getController();
			stage.setTitle("Server");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch();
	}
}