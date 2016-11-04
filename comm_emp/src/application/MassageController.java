package application;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aphatsatsia on 03.11.16.
 */
public class MassageController {

    @FXML
    private Pane datePickerSkinPane;
    @FXML
    private Pane datePickerBackgroundPanePane;
    @FXML
    private Pane datePickerInnerPane;
    @FXML
    private Button exitButton;

    Connection connect = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;
    String dateTime;
    List<String> recordedTimes = new ArrayList<String>();
    HashMap<String, List<String>> myMap = new HashMap<String, List<String>>();
    Boolean clicked = false;
    ImageView tempAddButton = new ImageView();
    Image image = new Image("image/add_green_button.png");

    public void initialize() throws SQLException {
        datePickerBackgroundPanePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                datePickerBackgroundPanePane.setVisible(false);
                datePickerInnerPane.setVisible(false);
            }
        });
        connect = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
        stmt = connect.prepareStatement("select * from massage_appointment ORDER BY date_time,time");
        resultSet = stmt.executeQuery();
        resultSet.next();
        dateTime = resultSet.getString("date_time");
        recordedTimes.add(resultSet.getString("time"));
        while (resultSet.next()) {
            if (resultSet.getString("date_time").equals(dateTime)) {
                recordedTimes.add(resultSet.getString("time"));
            } else {
                myMap.put(dateTime, recordedTimes);
                recordedTimes = new ArrayList<String>();
                recordedTimes.add(resultSet.getString("time"));
                dateTime = resultSet.getString("date_time");
            }
        }
        myMap.put(dateTime, recordedTimes);

        DatePicker date_picker = new DatePicker();
        date_picker.setShowWeekNumbers(false);

        date_picker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {

                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            FlowPane fPane = new FlowPane();

                            Label lbl = new Label(getText());
                            lbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    System.out.println("asdasd");
                                }
                            });
                            FlowPane subFPane = new FlowPane();
                            subFPane.setPrefWidth(180);
                            lbl.setPrefWidth(190);
                            fPane.getChildren().add(lbl);
                            ArrayList<String> tempTimes = (ArrayList<String>) myMap.get(item.toString());

                            ImageView add_button = new ImageView();
                            add_button.setImage(image);
                            add_button.setStyle("-fx-cursor: hand !important;");
                            add_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    datePickerBackgroundPanePane.setVisible(true);
                                    datePickerInnerPane.setVisible(true);
                                }
                            });
                            add_button.setVisible(false);
                            fPane.getChildren().add(add_button);
                            fPane.getChildren().add(subFPane);
                            if (tempTimes != null) {
                                for (String tempTimesItem : tempTimes) {
                                }
                            }
                            setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    clicked = true;
                                    add_button.setVisible(true);
                                    tempAddButton = add_button;
                                }
                            });
                            setOnMouseEntered(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    add_button.setVisible(true);
                                }
                            });
                            setOnMouseExited(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    add_button.setVisible(false);
                                    if (clicked) {
                                        tempAddButton.setVisible(true);
                                    }
                                }
                            });

                            fPane.setMinHeight(100);
                            fPane.setPrefWidth(230);
                            setGraphic(fPane);
                            setPrefWidth(230);
                            setPrefHeight(100);
                        }
                    }

                };
            }
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(date_picker);
        Node popupContent = datePickerSkin.getPopupContent();
        datePickerSkinPane.getChildren().add(popupContent);
    }

    public void performExit() {
        datePickerBackgroundPanePane.setVisible(false);
        datePickerInnerPane.setVisible(false);
    }
}
