package application;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class StageLoader {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    public StageLoader(String fxmlFileName, ActionEvent event) throws IOException {
        BorderPane registration_page_parent = (BorderPane) FXMLLoader.load(getClass().getResource("/view/" + fxmlFileName));
        Scene registration_page_scene = new Scene(registration_page_parent, width, height);
        registration_page_scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(registration_page_scene);
        primaryStage.show();
        FadeTransition ft = new FadeTransition(Duration.millis(700.0d), registration_page_parent);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.play();
    }

    public StageLoader(String fxmlFileName, Stage primaryStage) throws IOException {
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/" + fxmlFileName));
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}