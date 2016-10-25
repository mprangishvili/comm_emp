package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

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
        StageLoader sl = new StageLoader("Registration.fxml", event);
    }

    @FXML
    public void forgotPasswordAppear(ActionEvent event) throws IOException {
        StageLoader sl = new StageLoader("ForgotPassword.fxml", event);
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

        Connection connection = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            connection = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
            stmt = connection.prepareStatement("select validation('" + emailfield.getText() + "','" + passwordfield.getText() + "')");
            rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("validation")) {
                    StageLoader sl = new StageLoader("MainPage.fxml", event);
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