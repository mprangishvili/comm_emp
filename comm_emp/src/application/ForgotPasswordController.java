package application;


import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPasswordController {
    @FXML
    private TextField resetEmail;

    @FXML
    private Label warningLabel;


    @FXML
    private Label resetPasswordInstruction;

    @FXML
    private ImageView userIcon;

    @FXML
    private Button resetPasswordBtn;

    @FXML
    private Button submitCodeBtn;

    protected String tempocode;

    @FXML
    private Pane forgotPasswordPane;

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView loadImage;

    @FXML
    private Pane newPasswordPane;

    @FXML
    private void loadLogin(ActionEvent event) throws IOException, InterruptedException {

        StageLoader sl = new StageLoader("Login.fxml", event);

    }

    @FXML
    private void initialize() {
        //forgotPasswordPane.setVisible(true);
        Exit exitBtn = new Exit(forgotPasswordPane);
    }


    static int count;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        System.out.println(builder.toString());
        tempocode = builder.toString();
        return tempocode;
    }

    Image image = new Image(getClass().getResourceAsStream("/image/load.gif"));

    @FXML
    private void sendOnSubmit() throws SQLException {

        if (!resetEmail.getText().equals("")) {
            String sender_mail = "verifyourpwd@gmail.com";
            String sender_password = "18052010M+m";
            String recipient = resetEmail.getText();
            String subject = "Password recovery";
            String reseTCode = randomAlphaNumeric(5);
            String body = "Your password is ... " + reseTCode;
            sendPassword(sender_mail, sender_password, recipient, subject, body);

        } else {
            warningLabel.setVisible(true);
            warningLabel.setText("This email doesn't exist in our system!");
        }

    }

    private Thread threadTwo;
    private Thread threadOne;

    private void sendPassword(String sender, String pass, String reciever, String subject, String body) throws SQLException {
        count = 0;
        try {
            Connection connect = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            connect = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
            stmt = connect.prepareStatement("select * from users where email = ?");
            stmt.setString(1, resetEmail.getText());

            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("email").equals(resetEmail.getText())) {

                    threadTwo = new Thread() {
                        public void run() {
                            mainPane.setVisible(false);
                            loadImage.setVisible(true);
                        }
                    };

                    threadOne = new Thread() {
                        public void run() {
                            warningLabel.setVisible(false);
                            System.out.println("HI");
                            Properties props = System.getProperties();
                            String host = "smtp.gmail.com";
                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", host);
                            props.put("mail.smtp.user", reciever);
                            props.put("mail.smtp.password", pass);
                            props.put("mail.smtp.port", "587");
                            props.put("mail.smtp.auth", "true");

                            Session session = Session.getDefaultInstance(props);
                            MimeMessage message = new MimeMessage(session);

                            try {
                                System.out.println("ok");
                                message.setFrom(new InternetAddress(sender));
                                message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
                                message.setSubject(subject);
                                message.setText(body);
                                Transport transport = session.getTransport("smtp");
                                transport.connect(host, sender, pass);
                                transport.sendMessage(message, message.getAllRecipients());
                                transport.close();
                                System.out.println("asdasd");
                                resetCodeFunc();
                            } catch (AddressException ae) {
                                ae.printStackTrace();
                            } catch (MessagingException me) {
                                me.printStackTrace();
                            }
                        }
                    };


                    threadOne.start();
                    threadTwo.start();


                } else {
                    warningLabel.setVisible(true);
                    warningLabel.setText("This email doesn't exist in our system!");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void resetCodeFunc() {
        mainPane.setVisible(true);
        loadImage.setVisible(false);
        Platform.runLater(new Runnable() {
            public void run() {
                resetPasswordInstruction.textProperty().setValue("The code was sent to your email. \n Please enter it in the field below");
                resetEmail.setText("");
                resetPasswordBtn.textProperty().setValue("Resend");
                userIcon.visibleProperty().setValue(false);
                submitCodeBtn.setVisible(true);
            }
        });
    }

    @FXML
    private void newPasswordPrompt(ActionEvent event) throws IOException, InterruptedException {

        if (resetEmail.getText().equals(tempocode)) {
            warningLabel.setVisible(false);
            StageLoader sdddl = new StageLoader("NewPasswordPane.fxml", event);
        } else {
            ++count;
            warningLabel.setVisible(true);
            warningLabel.setText("The code is not correct!");
//            if (count == 3) {
//                System.out.println("Locked");
//                Thread.sleep(5000);
//            }
        }
    }


}
