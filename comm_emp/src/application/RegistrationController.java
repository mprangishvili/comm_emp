package application;

//import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Arrays;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class RegistrationController {

	ObservableList<String> countryList = FXCollections.observableArrayList();
	// ObservableList<ObservableList> cityList=
	// FXCollections.observableArrayList();
	// HashMap<Integer, String> tempCityList = new HashMap<Integer, String>();
	@SuppressWarnings("rawtypes")
	HashMap<String, ObservableList> cityList = new HashMap<String, ObservableList>();

	// ObservableList<String> cityListGeorgia =
	// FXCollections.observableArrayList("Tbilisi", "Zugdidi");

	public void back(ActionEvent event) throws IOException {
		Parent login_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene login_page_scene = new Scene(login_page_parent);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(login_page_scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	@FXML
	private Button exitButton;

	@FXML
	private TextField name;
	@FXML
	private TextField lastname;
	@FXML
	private TextField email;
	@FXML
	private TextField password;
	@FXML
	private Button register;

	@FXML
	private Button exitButtonreg;

	@FXML
	private ComboBox<String> regCountry;
	@FXML
	private ComboBox<String> regCity;

	@FXML
	private void registerbutton() {

	}

	@FXML
	private void closeButtonAction() {

		Stage stage = (Stage) exitButtonreg.getScene().getWindow();

		stage.close();
	}

	@FXML
	private void initialize() {
		Connection connection = null;
		java.sql.Statement stmt = null;
		java.sql.Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rsC = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
					"18052010M+m");
			stmt = connection.createStatement();
			stmt2 = connection.createStatement();
//			rsC = stmt2.executeQuery("select * from cities;");

			String country_name = null;
			int country_id;
			String queryForCountries = "select * from countries order by country_name";
			String cityName;
			rs = stmt.executeQuery(queryForCountries);
			while (rs.next()) {

				ObservableList<String> tempCityList = FXCollections.observableArrayList();
				country_name = rs.getString("country_name");
				country_id = rs.getInt("country_id");
   			//System.out.println(country_id);
				String queryForCities = "select city_name from cities where cc_id=" + country_id+" order by city_name asc";
				rsC = stmt2.executeQuery(queryForCities);
				while (rsC.next()) {
					 cityName=rsC.getString("city_name").toString();
					tempCityList.add(cityName);

				}
//				System.out.println(country_name);
				cityList.put(country_name, tempCityList);
				countryList.add(country_name);
			}

			regCountry.setItems(countryList);
		} catch (Exception e) {
			System.out.println("no");
			e.printStackTrace();
		}
		
		regCountry.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            String s = jumpTo(event.getText(), regCountry.getValue(), regCountry.getItems());
	            if (s != null) {
	            	regCountry.setValue(s);
	          
	            	System.out.println(s);
	            	
	            }
	        }
	    });
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void chooseCity() {
		 regCity.setItems(cityList.get(regCountry.getValue().toString()));
		 
	}
	
	static String jumpTo(String keyPressed, String currentlySelected, ObservableList<String> countries) {
	    String key = keyPressed.toUpperCase();
	    if (key.matches("^[A-Z]$")) {
	        boolean letterFound = false;
	        boolean foundCurrent = currentlySelected == null;
	        for (String s : countries) {
	            if (s.toUpperCase().startsWith(key)) {
	                letterFound = true;
	                if (foundCurrent) {
	                    return s;
	                }
	                foundCurrent = s.equals(currentlySelected);
	            }
	        }
	        if (letterFound) {
	            return jumpTo(keyPressed, null, countries);
	        }
	    }
	    return null;
	}
	 
}
