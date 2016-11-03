package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class MainController {
    @FXML
    private Button onlineChat_button;
    @FXML
    private Pane mainPagePane;

    @FXML
    private void callOnlineChat(ActionEvent event) throws IOException {
        new StageLoader("OnlineChat.fxml", event);
    }

    @FXML
    private void initialize() {
//        Exit exit = new Exit(mainPagePane);
    }
}