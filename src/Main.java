
import BankATMGUI.*;
import BankATMDAO.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Database database = new Database();
		
		try {
			GUIHomepage frame = new GUIHomepage();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Unfinished: throw exception
	 */
	
}
