package application;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistrationController {
    ObservableList<String> countryList = FXCollections.observableArrayList();

    //String usertemp=null,lastnametemp=null,emailtemp=null,reemailtemp=null,addresstemp=null;
    public void back(ActionEvent event) throws IOException {

        new StageLoader("Login.fxml", event);
//        usertemp=regFName.getText();
//        lastnametemp=regLName.getText();
//        emailtemp=regEMail.getText();
//        reemailtemp=regREMail.getText();
//        addresstemp=regAddress.getText();
    }

    // private boolean _textField = false;
// private boolean _comboBox = false;
// private boolean _date = false;
// private boolean _radioButton = false;
// private boolean _checkBox = false;
    @FXML
    private ComboBox<String> regCountry;
    @FXML
    private ComboBox<String> regCity;
    @FXML
    private TextField regFName;
    @FXML
    private TextField regLName;
    @FXML
    private TextField regEMail;
    @FXML
    private TextField regREMail;
    @FXML
    private PasswordField regPass;
    @FXML
    private PasswordField regCPass;
    @FXML
    private TextField regAddress;
    @FXML
    private DatePicker regDate;
    @FXML
    private RadioButton regRBtnMr;
    @FXML
    private RadioButton regRBtnMrs;
    @FXML
    private Pane regOrgPane;
    @FXML
    private CheckBox regFleet;
    @FXML
    private CheckBox regLease;
    @FXML
    private CheckBox regMobile;
    @FXML
    private CheckBox regCars;
    @FXML
    private CheckBox regObject;
    @FXML
    private CheckBox regGps;
    @FXML
    private CheckBox regFair;
    @FXML
    private CheckBox regTicket;
    @FXML
    private Button regReg;
    @FXML
    private Label mistakeReEmail;
    @FXML
    private Label mistakeMainEmail;
    @FXML
    private Label mistakeDate;
    @FXML
    private Label mistakeMainPass;
    @FXML
    private Label mistakeRePass;
    @FXML
    private Label mistakecountrySelect;
    @FXML
    private Label mistakeCitySelect;

    @FXML
    private Label errorfirstName;
    @FXML
    private Label errorlastName;
    @FXML
    private Label warnlabel;
    @FXML
    private Label mistakeAddress;
    boolean RegisterButton = false;
    boolean usernamefield;
    boolean lastnamefield;
    boolean emailfield;
    boolean reemailfield;
    boolean passwordf;
    boolean repasswordf;
    boolean countryselectfield;
    boolean cityselectfield;
    boolean addressfield;
    boolean datefield;
    private ArrayList<TextInputControl> textInputControlList = new ArrayList<TextInputControl>();
    private ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    Connection connect = null;
    Image image = new Image(getClass().getResourceAsStream("/image/emailError.png"));
    Image imageDateValid = new Image(getClass().getResourceAsStream("/image/q.png"));

    @FXML
    private void initialize() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connect = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
            stmt = connect.prepareStatement("select * from addcountries()");
            rs = stmt.executeQuery();
            while (rs.next()) {
                countryList.add(rs.getString("country_name"));
                regCountry.setItems(countryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AutocompleteClass.getComboBoxValue(regCountry);
        AutocompleteClass.getComboBoxValue(regCity);

        AutocompleteClass.autoCompleteComboBoxPlus(regCountry, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
        errorfirstName.setGraphic(new ImageView(image));
        errorlastName.setGraphic(new ImageView(image));
        mistakeMainEmail.setGraphic(new ImageView(image));
        mistakeReEmail.setGraphic(new ImageView(image));
        mistakeMainPass.setGraphic(new ImageView(image));
        mistakecountrySelect.setGraphic(new ImageView(image));
        mistakeCitySelect.setGraphic(new ImageView(image));
        mistakeAddress.setGraphic(new ImageView(image));
        mistakeDate.setGraphic(new ImageView(imageDateValid));
        errorfirstName.setGraphic(new ImageView(image));
        errorfirstName.setGraphic(new ImageView(image));
        mistakeRePass.setGraphic(new ImageView(image));
        regCountry.setValue("Choose country*");
        regCountry.setItems(countryList);
        regCity.setValue("Choose city*");
        textInputControlList
                .addAll(Arrays.asList(regFName, regLName, regEMail, regREMail, regPass, regCPass, regAddress));
        checkBoxList
                .addAll(Arrays.asList(regFleet, regLease, regMobile, regCars, regObject, regGps, regFair, regTicket));
    }

    @FXML
    private void usernameCheck() {

        String userName = regFName.getText().toLowerCase().toString();

        String usercheck = "^[a-z]{3,15}$";
        java.util.regex.Pattern k = java.util.regex.Pattern.compile(usercheck);
        java.util.regex.Matcher n = k.matcher(userName);
        if (n.matches()) {
            errorfirstName.setVisible(false);
            usernamefield = true;
            lastcheck();
            warnlabel.setText("");
        } else {
            warnlabel.setText("Name should contain only Letters A-Z");
            errorfirstName.setVisible(true);
            errorfirstName.setGraphic(new ImageView(image));
            usernamefield = false;
            lastcheck();
        }
    }

    @FXML
    private void lastnameCheck() {
        String lastname = regLName.getText().toLowerCase().toString();
        warnlabel.setText("");

        String usercheck = "^[a-z]{3,15}$";
        java.util.regex.Pattern k = java.util.regex.Pattern.compile(usercheck);
        java.util.regex.Matcher n = k.matcher(lastname);
        if (n.matches()) {
            errorlastName.setVisible(false);
            lastnamefield = true;
            lastcheck();
        } else {
            warnlabel.setText("Last Name should contain only Letters A-Z");
            errorlastName.setVisible(true);
            errorlastName.setGraphic(new ImageView(image));
            lastnamefield = false;
            lastcheck();
        }
    }

    @FXML
    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @FXML
    private void fieldChecker() {
        boolean k;
        ReEmailCheck();
        k = isValidEmailAddress(regEMail.getText().toString());
        System.out.println(k);
        if (k) {
            warnlabel.setText("");
            mistakeMainEmail.setVisible(false);
            emailfield = true;
            lastcheck();

        } else {
            warnlabel.setText("Please Enter your Email Correctly ");
            mistakeMainEmail.setVisible(true);
            mistakeMainEmail.setGraphic(new ImageView(image));
            emailfield = false;
            lastcheck();
        }
    }

    @FXML
    private void ReEmailCheck() {
        if (regEMail.getText().equals(regREMail.getText())) {
            warnlabel.setText("");
            mistakeReEmail.setVisible(false);
            reemailfield = true;
            lastcheck();
        } else {
            mistakeReEmail.setVisible(true);
            mistakeReEmail.setGraphic(new ImageView(image));
            warnlabel.setText("Email Fields do not match!");
            reemailfield = false;
            lastcheck();
        }
    }

    @FXML
    private void PassCheck() {
        PassReCheck();
        boolean p = PassValidator(regPass.getText().toString());
        System.out.println(p);
        if (!p) {
            warnlabel.setText("Password should be at least 8 Symbols including [,#$%] 1-9 A-z  ");
            mistakeMainPass.setVisible(true);
            mistakeMainPass.setGraphic(new ImageView(image));
            passwordf = false;
            lastcheck();
        } else {
            passwordf = true;
            mistakeMainPass.setVisible(false);
            lastcheck();
        }
    }

    @FXML
    private boolean PassValidator(String password) {
        String rule = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(rule);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }

    @FXML
    private void PassReCheck() {
        if (regPass.getText().toString().equals(regCPass.getText().toString())) {
            warnlabel.setText("");
            mistakeRePass.setVisible(false);
            repasswordf = true;
            lastcheck();
        } else {

            warnlabel.setText("Passwords do not match!");
            mistakeRePass.setVisible(true);
            mistakeRePass.setGraphic(new ImageView(image));
            repasswordf = false;
            lastcheck();
        }
    }

    @FXML
    private void countryCheck() {

        if (regCountry.getValue() == "Choose country*" || regCountry.getValue() == "") {
            warnlabel.setText("Please select country!");
            mistakecountrySelect.setVisible(true);
            mistakecountrySelect.setGraphic(new ImageView(image));
            countryselectfield = false;
            lastcheck();
        } else {
            warnlabel.setText("");
            mistakecountrySelect.setVisible(false);
            countryselectfield = true;
            lastcheck();
        }
    }

    ObservableList<String> cityList = FXCollections.observableArrayList();

    @FXML

    private void chooseCity() throws SQLException {

        PreparedStatement stmtC = null;
        PreparedStatement stmtCa = null;
        ResultSet rsC = null;
        ResultSet rsCa = null;
        stmtC = connect.prepareStatement("select country_id from countries where country_name= '" + regCountry.getValue().toString() + "'");
        rsC = stmtC.executeQuery();
        while (rsC.next()) {
            stmtCa = connect.prepareStatement("select * from addcities(" + rsC.getInt("country_id") + ")");
            rsCa = stmtCa.executeQuery();
            while (rsCa.next()) {
                cityList.addAll(rsCa.getString("city_name"));
            }
        }

        regCity.setEditable(true);
        regCity.setPrefHeight(regCity.getMinHeight());
        regCity.setItems(cityList);
        AutocompleteClass.autoCompleteComboBoxPlus(regCity, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()) ||
                itemToCompare.toString().equals(typedText));
        System.out.println(regCity.getValue());
        if (regCity.getValue() == null) {
            warnlabel.setText("Please select city!");
            mistakeCitySelect.setVisible(true);
            mistakeCitySelect.setGraphic(new ImageView(image));
            cityselectfield = false;
            lastcheck();
        } else {
            warnlabel.setText("");
            mistakeCitySelect.setVisible(false);
            cityselectfield = true;
            lastcheck();
        }
    }

    @FXML
    private void addressCheck() {
        if (regAddress.getText().matches("[a-zA-Z0-9\\-#\\.\\(\\)\\/%&\\s]{15,40}")) {
            warnlabel.setText("");
            mistakeAddress.setVisible(false);
            addressfield = true;
            lastcheck();

        } else {

            warnlabel.setText("please enter your address correctly!");
            mistakeAddress.setVisible(true);
            mistakeAddress.setGraphic(new ImageView(image));
            addressfield = false;
            lastcheck();
        }
    }

    @FXML
    private void date() {

        if (regDate.getValue() == null)

        {
            warnlabel.setText("please select your birth Date!");
            mistakeDate.setVisible(true);
            mistakeDate.setGraphic(new ImageView(imageDateValid));
            datefield = false;
            lastcheck();
        } else {
            warnlabel.setText("");
            mistakeDate.setVisible(false);
            datefield = true;
            lastcheck();
        }
    }

    @FXML
    private void lastcheck() {

        if ((usernamefield && lastnamefield && emailfield && reemailfield && passwordf && repasswordf && countryselectfield && addressfield && datefield && cityselectfield) && (regFleet.isSelected()
                || regLease.isSelected() || regMobile.isSelected() || regCars.isSelected() || regTicket.isSelected()) && (regRBtnMr.isSelected() || regRBtnMrs.isSelected())) {
            regReg.setDisable(false); ////should be false
            RegisterButton = true;
        } else
            regReg.setDisable(true); ///should be true
    }

    @FXML
    private void register(ActionEvent event) throws IOException, InterruptedException {
        Connection connection = null;
        String workAppend = "";
        String gender = "";
        System.out.println(regFName.getText());
        System.out.println(regLName.getText());
        System.out.println(regEMail.getText());
        System.out.println(regREMail.getText());
        System.out.println(regPass.getText());
        System.out.println(regCPass.getText());
        System.out.println(regCountry.getValue().toString());
        System.out.println(regCity.getValue().toString());
//        if(workAppend=="")
//            workAppend+=",";
        if (regFair.isSelected())
            workAppend += " regFair";
        if (regFleet.isSelected())
            workAppend += " Fleet";
        if (regLease.isSelected())
            workAppend += " Lease";
        if (regTicket.isSelected())
            workAppend += " Ticket";
        if (regGps.isSelected())
            workAppend += " Gps";
        if (regObject.isSelected())
            workAppend += " Object";
        if (regCars.isSelected())
            workAppend += " Cars";
        if (regMobile.isSelected())
            workAppend += " mobile";
        if (regRBtnMr.isSelected())
            gender += "male";
        else if (regRBtnMrs.isSelected())
            gender += "female";
        System.out.println(gender);
        System.out.println(workAppend);

        try {
            PreparedStatement stmt = null;

            connection = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
            stmt = connection.prepareStatement("insert into  users_new values('" + regFName.getText() + "','" + regLName.getText() + "','" +
                    regEMail.getText() + "','" + regPass.getText() + "','" + regCountry.getValue().toString() + "','" +
                    regCity.getValue().toString() + "','" + regAddress.getText() +
                    "','" + regDate.getValue().toString() + "','" + gender + "','" + workAppend + "')");
            stmt.executeUpdate();
            System.out.println("data added to the table");


            new StageLoader("Login.fxml", event);


        } catch (Exception e) {
            System.out.println("check your syntax ");
            e.printStackTrace();

        }
    }
}
