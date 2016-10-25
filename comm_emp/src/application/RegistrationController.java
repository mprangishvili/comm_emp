package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistrationController {
    ObservableList<String> countryList = FXCollections.observableArrayList("Germany", "Georgia");
    ObservableList<String> cityListGermany = FXCollections.observableArrayList("Chemnitz", "Berlin");
    ObservableList<String> cityListGeorgia = FXCollections.observableArrayList("Tbilisi", "Zugdidi");

    public void back(ActionEvent event) throws IOException {
        StageLoader sl = new StageLoader("Login.fxml", event);
    }

    @FXML
    private Pane registrationPane;
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
    private Label mistakeMainPass;
    @FXML
    private Label mistakeRePass;
    @FXML
    private Label mistakecountrySelect;
    @FXML
    private Label mistakeCitySelect;


    private ArrayList<TextInputControl> textInputControlList = new ArrayList<TextInputControl>();
    private ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    Image image = new Image(getClass().getResourceAsStream("/image/emailError.png"));

    @FXML
    private void initialize() {
        regCountry.setValue("Choose country*");
        regCountry.setItems(countryList);
        regCity.setValue("Choose city*");
        textInputControlList
                .addAll(Arrays.asList(regFName, regLName, regEMail, regREMail, regPass, regCPass, regAddress));
        checkBoxList
                .addAll(Arrays.asList(regFleet, regLease, regMobile, regCars, regObject, regGps, regFair, regTicket));
//        Exit exit = new Exit(registrationPane);
    }


    @FXML
    public boolean isValidEmailAddress(String email) {


        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
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
        if (!k) {
            mistakeMainEmail.setVisible(true);
            mistakeMainEmail.setGraphic(new ImageView(image));
            regReg.setDisable(true);
        } else {
            //JOptionPane.showMessageDialog(null, "alert!");
            mistakeMainEmail.setVisible(false);
        }


//    if (eventType.equals("ComboBox")) {
//       if (regCountry.getValue() == "Germany") {
//          regCity.setItems(cityListGermany);
//       } else if (regCountry.getValue() == "Georgia") {
//          regCity.setItems(cityListGeorgia);
//       } else {
//       }
//    }
//
        if (!regFName.getText().isEmpty() && !regLName.getText().isEmpty() && !regEMail.getText().isEmpty()
                && (!regREMail.getText().isEmpty() && !regPass.getText().isEmpty()) && !regCPass.getText().isEmpty()
                && !regAddress.getText().isEmpty() && regDate.getValue() != null
                && (regRBtnMr.isSelected() || regRBtnMrs.isSelected())
                && (regFleet.isSelected() || regLease.isSelected() || regMobile.isSelected() || regCars.isSelected()
                || regObject.isSelected() || regGps.isSelected() || regFair.isSelected()
                || regTicket.isSelected())
                && !regCountry.getValue().equals("Choose country*") && !regCity.getValue().equals("Choose city*")) {
            //regEMail.getStyleClass().remove("emptyFieldError");
            regReg.setDisable(false);
//       System.out.println(k);
        } else regReg.setDisable(true);
        // if (eventType.equals("TextField")) {
        // if (((TextInputControl) event.getSource()).getText().isEmpty()) {
        // TextInputControl currentTextField = (TextInputControl)
        // event.getSource();
        // currentTextField.getStyleClass().add("emptyFieldError");
        // } else {
        // TextInputControl currentTextField = (TextInputControl)
        // event.getSource();
        // currentTextField.getStyleClass().remove("emptyFieldError");
        // }
        // }
        // && _comboBox && _date && _radioButton && _checkBox
        // for (TextInputControl tFieldFromArrayList : textInputControlList) {
        // if (!tFieldFromArrayList.getText().isEmpty()) {
        // _textField = true;
        // } else {
        // _textField = false;
        // break;
        // }
        // }
        // for (CheckBox checkBox : checkBoxList) {
        // if (checkBox.isDisabled()) {
        // _checkBox = false;
        // break;
        // } else {
        // _checkBox = true;
        // }
        // }
        //
        // if (_textField && _checkBox) {
        // regReg.setDisable(false);
        // } else {
        // regReg.setDisable(true);
        // }
        // else if (eventType.equals("ComboBox")) {
        // if (((ComboBox) event.getSource()).getValue().equals("Choose
        // country*")
        // || ((ComboBox) event.getSource()).getValue().equals("Choose city*"))
        // {
        // ComboBox currentComboBox = (ComboBox) event.getSource();
        // currentComboBox.getStyleClass().add("emptyFieldError");
        // if (regCountry.getValue() == "Germany") {
        // regCity.setItems(cityListGermany);
        // } else if (regCountry.getValue() == "Georgia") {
        // regCity.setItems(cityListGeorgia);
        // } else {
        // }
        // } else {
        // ComboBox currentComboBox = (ComboBox) event.getSource();
        // currentComboBox.getStyleClass().remove("emptyFieldError");
        // }
        // }
        // if (eventType.equals("DatePicker")) {
        //
        // if (((DatePicker) event.getSource()).getValue() == null) {
        // DatePicker currentDatePicker = (DatePicker) event.getSource();
        // currentDatePicker.getStyleClass().add("emptyFieldError");
        // } else {
        // DatePicker currentDatePicker = (DatePicker) event.getSource();
        // currentDatePicker.getStyleClass().remove("emptyFieldError");
        // }
        // }
        //
        // }else if (eventType.equals("TextField")) {
        // if (((TextInputControl) event.getSource()).getText().isEmpty()) {
        // TextInputControl currentTextField = (TextInputControl)
        // event.getSource();
        // currentTextField.getStyleClass().add("emptyFieldError");
        // } else {
        // TextInputControl currentTextField = (TextInputControl)
        // event.getSource();
        // currentTextField.getStyleClass().remove("emptyFieldError");
        // }
        // }else
        // int counter = 0;
        // for (TextInputControl tFieldFromArrayList : textInputControlList) {
        // if (!tFieldFromArrayList.getText().isEmpty()) {
        // _textField = true;
        // } else {
        // counter++;
        // }
        // }
        // if (counter > 0) {
        // _textField = false;
        // }
        // for (CheckBox checkBox : checkBoxList) {
        // if (checkBox.isDisabled()) {
        // counter++;
        // }
        // }
        // if (regCountry.getValue() != "Choose country*" && regCity.getValue()
        // != "Choose city*"
        // && (!regRBtnMr.isDisabled() || !regRBtnMrs.isDisabled()) &&
        // !regDate.getValue().equals("dd/mm/yy*")) {
        // counter++;
        // }
        // if (_textField) {
        // regReg.setDisable(false);
        // } else {
        // regReg.setDisable(true);
        // }
    }


    @FXML
    private void ReEmailCheck() {

        //   TextInputControl emailfield = (TextInputControl) emailEvent.getSource();
        //System.out.println(emailfield.getId());
        //  if(emailfield.getId().equals("regREMail"))

        if (regEMail.getText().equals(regREMail.getText())) {

            mistakeReEmail.setVisible(false);

        } else {
            mistakeReEmail.setVisible(true);
            mistakeReEmail.setGraphic(new ImageView(image));
            regReg.setDisable(true);
        }

    }


    @FXML
    private void PassCheck() {
        boolean p = PassValidator(regPass.getText().toString());
        System.out.println(p);


    }


    @FXML
    private boolean PassValidator(String password) {

        password = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(password);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }

    @FXML
    private void PassReCheck() {
        System.out.println("ffff");
    }


}
