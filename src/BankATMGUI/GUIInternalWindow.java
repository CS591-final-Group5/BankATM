package BankATMGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUIInternalWindow extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public GUIInternalWindow() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); Commented for iconifiable
		setBounds(100, 50, 800, 600);
	}

	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
	
}
