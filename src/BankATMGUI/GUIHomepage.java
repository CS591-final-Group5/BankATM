
package BankATMGUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class GUIHomepage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader homepageLoader = new FXMLLoader(GUIHomepage.class.getResource("GUIHomepage.fxml"));
		homepageLoader.setController(this);
		Parent pane = homepageLoader.load();
		Scene homepageScene = new Scene(pane, 600, 400);
		
		primaryStage.setTitle("BankATM System Homepage!");
		primaryStage.setScene(homepageScene);
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);
		primaryStage.show();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void BankManagerLogin(MouseEvent event) {
    }

    @FXML
    void CustomerLogin(MouseEvent event) {
    }

    @FXML
    void Exit(MouseEvent event) {
    }

    @FXML
    void initialize() {
    	

    }

}
