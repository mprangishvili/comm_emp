package application;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Created by mprangishvili on 20.10.16.
 */
public class ForgotPasswordController {
    @FXML
    private Pane forgotPasswordPane;

    @FXML
    private void initialize() {
        Exit exit = new Exit(forgotPasswordPane);
    }
}
