package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Pane loginPane;
    @FXML
    private Pane rememberMe_CheckBox_Pane;

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
        SlideCheckBox loginCheckBox=new SlideCheckBox();
        rememberMe_CheckBox_Pane.getChildren().addAll(loginCheckBox);

        loginCheckBox.setScaleX(0.5);
        loginCheckBox.setScaleY(0.5);
    }

    @FXML
    private Button exitB;
    @FXML
    private Label warning;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField passwordfield;

    @FXML
    private void closeButtonAction() {

        Stage stage = (Stage) exitB.getScene().getWindow();

        stage.close();
    }

    public void authentication(ActionEvent event) throws IOException, InterruptedException {
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
                Boolean passcheck = rs.getBoolean("validation");
                System.out.println(passcheck);

                if (passcheck) {
                    System.out.println("equals");
                    StageLoader sl = new StageLoader("MainPage.fxml", event);

                } else {
                    System.out.println("no");
                }

            } else
            warning.setText("Check your Username and Password !");
            passwordfield.setText("");

            System.out.println("Check your Username and Password");

        } catch (Exception e) {


            System.out.println("check!!!!");
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