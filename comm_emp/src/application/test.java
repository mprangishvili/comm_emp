package application;


import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test extends Application {


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    @Override
    public void start(Stage primaryStage) throws SQLException, IOException {
        new StageLoader("Massage.fxml", primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

