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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
        accordion.setMinSize(1600, 550);
        //accordion.setMinHeight(150);
//        accordion.setLayoutX(300);
        Connection connect = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        connect = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
        stmt = connect.prepareStatement("select * from massage_appointments where user_id=2 order by date_time DESC limit 7");
        resultSet = stmt.executeQuery();
        String isPaid = null;

        PreparedStatement stmt2 = null;
        ResultSet resultSet2 = null;
        stmt2 = connect.prepareStatement("select * from planner where user_id=2 order by date_time DESC limit 7");
        resultSet2 = stmt2.executeQuery();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");

        while (resultSet.next()) {
            Label paidLabel = new Label("");
            paidLabel.setTranslateX(300);
            if (resultSet.getBoolean("paid")) {
                paidLabel.setText("Paid");
            } else {
                paidLabel.setText("Not Paid");
            }
            Date appDate = (Date) resultSet.getDate("date_time");
            String dateTime = dateFormat.format(appDate);

            HBox hbox = new HBox();

            FlowPane massageFlowPane = new FlowPane();
            Label massageLabel = new Label("Your massage appointment is scheduled for: "+ resultSet.getString("time"));
            massageLabel.setPrefWidth(533);
            massageFlowPane.setMaxWidth(533);
            massageFlowPane.getChildren().add(massageLabel);
            massageFlowPane.getChildren().add(new Label("Your comment: " +resultSet.getString("comment")));
            hbox.getChildren().addAll(massageFlowPane);
            massageFlowPane.getChildren().addAll(paidLabel);

            Line verticalLine = new Line();
            verticalLine.setEndY(230);
            verticalLine.setTranslateY(0);

            hbox.getChildren().addAll((verticalLine));

            TitledPane titledPane = new TitledPane(dateTime.toUpperCase(), hbox);
            titledPane.setId("headerText");



            accordion.getPanes().addAll(titledPane);

            paidLabel.setStyle("-fx-border-color: lightgreen; -fx-background-color: lightgreen; -fx-border-radius: 1px; -fx-padding: 0.3em; -fx-text-fill: white");

        }

        while(resultSet2.next())
        {
            FlowPane plannerPane = new FlowPane();
            Label plannerLabel = new Label(resultSet2.getString("comment"));
            plannerLabel.setMaxWidth(533);
            plannerPane.getChildren().add(plannerLabel);
            System.out.println(resultSet2.getString("comment"));

        }

        connect.close();


        flowPane.getChildren().add(accordion);
    }

}
