module zerliServer {
	requires java.sql;
	requires ocsf;
	requires javafx.fxml;
	requires javafx.controls;
	opens serverGUI to javafx.graphics,javafx.fxml;
}