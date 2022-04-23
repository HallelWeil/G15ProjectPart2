package clientGUI;

import client.ClientBoundary;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import order.Order;

/**
 * simple class to save the different scenes data
 * 
 * @author halel
 *
 */
public class ScenesData {
	/**
	 * the main window
	 */
	public Stage stage;
	/**
	 * the roots for the scenes
	 */
	public AnchorPane mainMenuRoot, orderMenuRoot, connectRoot;
	/**
	 * the Main menu controller
	 */
	public MainMenuGuiController mainMenuController;
	/**
	 * the order menu controller
	 */
	public OrderGuiController orderMenucontroller;
	/**
	 * the scenes
	 */
	public Scene mainScene, orderScene, connectScene;
	/**
	 * the client boundary
	 */
	public ClientBoundary clientBoundary;
	public Order order;
	/**
	 * the connect controller
	 */
	public ConnectGuiController connectGuiController;

	public ScenesData(Stage stage) {
		this.stage = stage;
	}
}
