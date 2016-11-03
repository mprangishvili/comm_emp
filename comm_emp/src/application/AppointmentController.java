package application;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;

/**
 * Created by mprangishvili on 01.11.16.
 */
public class AppointmentController {

    @FXML
    private FlowPane flowPane;

    public void initialize() throws SQLException, ParseException {
        Accordion accordion = new Accordion();
        accordion.setMinSize(600, 200);
        Connection connect = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        connect = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
//        System.out.println("name " + LoginController._userName);
        stmt = connect.prepareStatement("select * from massage_appointment where user_id=2");
        resultSet = stmt.executeQuery();
        String isPaid = null;


        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");

        while (resultSet.next()) {
            Date appDate = (Date) resultSet.getDate("date_time");
            String dateTime = dateFormat.format(appDate);
//            System.out.println(dateTime);
//            AnchorPane aPane = new AnchorPane();
//
//            TitledPane titledPane = new TitledPane(dateTime, aPane);
//
//           aPane.getChildren().add(new Label(LoginController._userName));
//            aPane.getChildren().add(new Label(resultSet.getString(2)));
//           accordion.getPanes().add(titledPane);

            HBox hbox = new HBox();

            VBox vBox = new VBox(8);
            vBox.setSpacing(10);
            hbox.setSpacing(500);
            TitledPane titledPane = new TitledPane(dateTime, hbox);
//            vBox.getChildren().add(new Label(LoginController._userName));
            vBox.getChildren().addAll(new Label(resultSet.getString("time")));
            vBox.getChildren().add(new Label("Your comment: " + resultSet.getString("comment")));
            Label paidLabel = new Label("");
            if (resultSet.getBoolean("paid")) {
                paidLabel.setText("Paid");
            } else {
                paidLabel.setText("Not Paid");
            }
            hbox.getChildren().addAll(vBox, paidLabel);
            accordion.getPanes().addAll(titledPane);
            titledPane.setStyle("-fx-text-fill: white");
            paidLabel.setStyle("-fx-border-color: lightgreen; -fx-background-color: lightgreen; -fx-border-radius: 1px; -fx-padding: 0.3em; -fx-text-fill: white");


        }

        connect.close();


        flowPane.getChildren().add(accordion);
    }

}