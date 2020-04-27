
import javafx.application.Application;
import javafx.stage.Stage;

import BankATMGUI.*;

import BankATMDAO.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Database database = new Database();
		database.test();
		
		//Application.launch(BankATMGUI.GUIHomepage.class, args);
	}

	/*
	 * Unfinished: throw exception
	 */
	
}
