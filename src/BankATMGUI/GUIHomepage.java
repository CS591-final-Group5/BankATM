
package BankATMGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class GUIHomepage extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			FXMLLoader homepageLoader = new FXMLLoader(getClass().getResource("GUIHomepage.fxml"));
			Parent pane = homepageLoader.load();
			Scene homepageScene = new Scene(pane, 600, 400);
			
			primaryStage.setTitle("BankATM System Homepage!");
			primaryStage.setScene(homepageScene);
			primaryStage.setWidth(600);
			primaryStage.setHeight(400);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button bankManagerLogin;

    @FXML
    private Button customerLogin;

    @FXML
    private Button exit;

    @FXML
    void initialize() {
        assert bankManagerLogin != null : "fx:id=\"bankManagerLogin\" was not injected: check your FXML file 'GUIHomepage.fxml'.";
        assert customerLogin != null : "fx:id=\"customerLogin\" was not injected: check your FXML file 'GUIHomepage.fxml'.";
        assert exit != null : "fx:id=\"exit\" was not injected: check your FXML file 'GUIHomepage.fxml'.";

    }
    
    @FXML
    void clickBankManagerLogin(MouseEvent event) {
    	// GUIUserLogin userLogin = (GUIUserLogin) replaceSceneContent("");
    	// Stage stage = (Stage) 
    }

    @FXML
    void clickCustomerLogin(MouseEvent event) {
    	try {
    		FXMLLoader homepageLoader = new FXMLLoader(GUIUserLogin.class.getResource("GUIUserLogin.fxml"));
			Parent pane = homepageLoader.load();
    		Scene homepageScene = new Scene(pane, 600, 400);
    		Stage newStage = new Stage();
    		newStage.setScene(homepageScene);
    		newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	bankManagerLogin.getScene().getWindow().hide();
    }

    @FXML
    void clickExit(MouseEvent event) {
    	Platform.exit();
    }

}
