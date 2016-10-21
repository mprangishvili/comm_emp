package application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
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
    private Label resetPasswordInstruction;

    @FXML
    private ImageView userIcon;

    @FXML
    private Button resetPasswordBtn;

    @FXML
    private Button submitCodeBtn;

    protected String tempocode;

    @FXML
    private void loadLogin(ActionEvent event) throws IOException, InterruptedException {

        StageLoader sl = new StageLoader("Login.fxml", event);

    }

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

    @FXML
    private void sendOnSubmit() {
        String sender_mail = "verifyourpwd@gmail.com";
        String sender_password = "18052010M+m";
        String recipient = resetEmail.getText();
        String subject = "Password recovery";
        String reseTCode = randomAlphaNumeric(5);


        String body = "Your password is ... " + reseTCode;


        sendPassword(sender_mail, sender_password, recipient, subject, body);
    }

    private void sendPassword(String sender, String pass, String reciever, String subject, String body) {


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
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

        resetCodeFunc();
    }

    public void resetCodeFunc() {
        resetPasswordInstruction.textProperty().setValue("The code was sent to your email. \n Please enter it in the field below");
        resetEmail.setText("");
        resetPasswordBtn.textProperty().setValue("Resend");
        userIcon.visibleProperty().setValue(false);
        submitCodeBtn.setVisible(true);


    }

    @FXML
    private void newPasswordPrompt(ActionEvent event) throws IOException, InterruptedException {
        if (resetEmail.getText().equals(tempocode)) {
            StageLoader sdddl = new StageLoader("NewPasswordPane.fxml", event);

        }
    }


}
