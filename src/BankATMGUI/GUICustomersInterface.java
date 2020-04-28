package BankATMGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUICustomersInterface extends JFrame {

	private String username;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUICustomersInterface(String username) {
		this.username = username;
		setTitle("Customer Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1012, 45);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Setting");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		ChangePasswordListener cpl = new ChangePasswordListener();
		mntmChangePassword.addActionListener(cpl);
		mntmChangePassword.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/ChangePassword.png")));
		mnNewMenu.add(mntmChangePassword);
		
		JMenuItem mntmLogout = new JMenuItem("Logout (Return to homepage)");
		LogoutListener rl = new LogoutListener();
		mntmLogout.addActionListener(rl);
		mntmLogout.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Return.png")));
		mnNewMenu.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		ExitListener el = new ExitListener();
		mntmExit.addActionListener(el);
		mntmExit.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Exit.png")));
		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Help");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About us");
		mnNewMenu_3.add(mntmNewMenuItem_2);
	}
	
	class ChangePasswordListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				setVisible(false);
				GUIChangeBankATMPassword frame = new GUIChangeBankATMPassword(username);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class LogoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				setVisible(false);
				GUIHomepage frame = new GUIHomepage();
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
}
