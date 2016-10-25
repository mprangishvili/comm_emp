package application;

import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class test extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/OnlineChat.fxml"));
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

