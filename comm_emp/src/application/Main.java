package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(Main.class.getResource("/view/fonts/SourceSansPro-Black.ttf").toExternalForm(), 10);
//        new StageLoader("Login.fxml", primaryStage);
        new StageLoader("MassageAppointment.fxml", primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
