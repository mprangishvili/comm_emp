package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Exit {

    public Exit(Pane pane) {
        Button exitBtn = new Button();
        exitBtn.textProperty().setValue("X");
        exitBtn.getStyleClass();
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.close();
            }
        });
        exitBtn.setLayoutX(780);
        exitBtn.setStyle("-fx-background-color: #0a2268;-fx-text-fill: white;-fx-cursor: hand");
        pane.getChildren().addAll(exitBtn);
    }
}
