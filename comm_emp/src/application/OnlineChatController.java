package application;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class OnlineChatController {
    @FXML
    private Pane onlineChatPane;

    @FXML
    private void initialize() {
        Exit exit = new Exit(onlineChatPane);
    }

}
