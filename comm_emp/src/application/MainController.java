package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button onlineChat_button;

    @FXML
    private void callOnlineChat(ActionEvent event) throws IOException {
        StageLoader sl = new StageLoader("OnlineChat.fxml", event);
    }
}