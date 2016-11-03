package application;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class OnlineChatController {
    @FXML
    private Pane onlineChatPane;
    @FXML
    private Pane transitionPane;
    @FXML
    private Button transitionBtn;

    @FXML
    private void initialize() {
//        Exit exit = new Exit(onlineChatPane);
    }

    @FXML
    private void transition() {
//        transitionPane.setPrefHeight(0.0d);

//        Timeline timeline = new Timeline();
//        timeline.getKeyFrames().addAll(
//                new KeyFrame(Duration.millis(600.0d), new KeyValue(transitionPane.backgroundProperty(), new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))))
//        );
//        timeline.play();
    }

    @FXML
    private void makeit(double a) {
        System.out.println(a);
        transitionPane.setStyle("-fx-background-color: white");
    }
}
