package application;

import java.awt.*;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StageLoader sl = new StageLoader("Login.fxml", primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
