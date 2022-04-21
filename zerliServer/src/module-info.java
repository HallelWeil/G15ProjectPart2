module zerliServer {
	requires java.sql;
	requires ocsf;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;

	opens serverGUI to javafx.graphics, javafx.fxml, javafx.base;
}