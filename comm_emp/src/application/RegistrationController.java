package application;

import java.io.IOException;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistrationController {

	ObservableList<String> countryList = FXCollections.observableArrayList("Germany", "Georgia");
	ObservableList<String> cityListGermany = FXCollections.observableArrayList("Chemnitz", "Berlin");
	ObservableList<String> cityListGeorgia = FXCollections.observableArrayList("Tbilisi", "Zugdidi");

	public void back(ActionEvent event) throws IOException {
		Parent login_page_parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene login_page_scene = new Scene(login_page_parent);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(login_page_scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();

	}

//	private boolean _textField = false;
//	private boolean _comboBox = false;
//	private boolean _date = false;
//	private boolean _radioButton = false;
//	private boolean _checkBox = false;
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

	private ArrayList<TextInputControl> textInputControlList = new ArrayList<TextInputControl>();
	private ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();

	@FXML
	private void initialize() {
		regCountry.setValue("Choose country*");
		regCountry.setItems(countryList);
		regCity.setValue("Choose city*");
		textInputControlList
				.addAll(Arrays.asList(regFName, regLName, regEMail, regREMail, regPass, regCPass, regAddress));
		checkBoxList
				.addAll(Arrays.asList(regFleet, regLease, regMobile, regCars, regObject, regGps, regFair, regTicket));
	}

	
	
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
	
	
	@FXML
	private void fieldChecker() {
		
		boolean k;
		k=isValidEmailAddress(regEMail.getText().toString());
		System.out.println(k);
	
		
		
	if(k&&(regEMail.getText().equals(regREMail.getText()))){
			

			regEMail.getStyleClass().remove("emptyFieldError");
			
	}
		
	else {
		
		
		//JOptionPane.showMessageDialog(null, "alert!");
	
		regReg.setDisable(true);
		regEMail.getStyleClass().add("emptyFieldError");
		
		
		
	}
		
		if (!regFName.getText().isEmpty() && !regLName.getText().isEmpty() && !regEMail.getText().isEmpty()
				&& (!regREMail.getText().isEmpty() && !regPass.getText().isEmpty()) && !regCPass.getText().isEmpty()
				&& !regAddress.getText().isEmpty() && regDate.getValue() != null
				&& (regRBtnMr.isSelected() || regRBtnMrs.isSelected())
				&& (regFleet.isSelected() || regLease.isSelected() || regMobile.isSelected() || regCars.isSelected()
						|| regObject.isSelected() || regGps.isSelected() || regFair.isSelected()
						|| regTicket.isSelected())
				&& !regCountry.getValue().equals("Choose country*") && !regCity.getValue().equals("Choose city*"))

		{
			
			//regEMail.getStyleClass().remove("emptyFieldError");
			regReg.setDisable(false);
			
			
//			System.out.println(k);
			}
		
		else  regReg.setDisable(true);
		
		
				
	
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

}
