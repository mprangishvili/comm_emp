package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.ConnectionClass;
import view.SlideCheckBox;

public class LoginController {
    @FXML
    private Pane loginPane;
    @FXML
    private Pane rememberMe_CheckBox_Pane;
    @FXML
    private Pane authorisationPanelPane;
    @FXML
    private ImageView loader;

    @FXML
    public void registration(ActionEvent event) throws IOException, InterruptedException {
        new StageLoader("Registration.fxml", event);
    }

    @FXML
    public void forgotPasswordAppear(ActionEvent event) throws IOException {
        new StageLoader("ForgotPassword.fxml", event);
    }

    @FXML
    private void initialize() {
        SlideCheckBox loginCheckBox = new SlideCheckBox();
        rememberMe_CheckBox_Pane.getChildren().addAll(loginCheckBox);
        loginCheckBox.setScaleX(0.5);
        loginCheckBox.setScaleY(0.5);
    }

    @FXML
    private Label warning;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField passwordfield;

    public void authentication(ActionEvent event) throws IOException, InterruptedException {
        new Thread(() -> {
            loader.setVisible(true);
            authorisationPanelPane.setVisible(false);
        }).start();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        loader.setVisible(false);
                        authorisationPanelPane.setVisible(true);
                    }
                }, 1000);

        String user = emailfield.getText();
        String pass = passwordfield.getText();

        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = ConnectionClass.getConnection().prepareStatement("select * from validation('" + emailfield.getText() + "','" + passwordfield.getText() + "')");
            rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("validation_result")) {
                    new StageLoader("MainPage.fxml", event);
                } else {
                    warning.setText("Check your Username and Password !");
                }

            } else {
                warning.setText("Check your Username and Password !");
                passwordfield.setText("");
            }
        } catch (Exception e) {


            System.out.println(e);
            e.printStackTrace();
        }

    }


    public void KeyEvent() {
        passwordfield.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {

                }
            }
        });
    }

}