package clientGUI;

import client.ClientBoundary;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import order.Order;

public class ScenesData {
	
	public Stage stage;
	public AnchorPane mainMenuRoot, orderMenuRoot;
	public MainMenuGuiController mainMenuController;
	public OrderGuiController orderMenucontroller;
	public Scene mainScene, orderScene;
	public ClientBoundary clientBoundary;
	public Order order;
	
	public ScenesData(Stage stage) {
		this.stage=stage;
	}
}
