package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class StageLoader {
    public StageLoader(String fxmlFileName, ActionEvent event) throws IOException {
        Parent registration_page_parent = FXMLLoader.load(getClass().getResource("/view/" + fxmlFileName));
        Scene registration_page_scene = new Scene(registration_page_parent);
        registration_page_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(registration_page_scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }
}