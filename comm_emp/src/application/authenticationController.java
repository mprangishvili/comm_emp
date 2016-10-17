package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class authenticationController {
	@FXML
	private Pane authenticationPane;

	public void authentication(ActionEvent event) throws IOException, InterruptedException {
		Parent registration_page_parent = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		Scene registration_page_scene = new Scene(registration_page_parent);
		registration_page_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(registration_page_scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();
			
	}
	
	
	
	}