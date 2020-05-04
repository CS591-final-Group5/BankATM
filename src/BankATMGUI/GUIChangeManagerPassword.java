package BankATMGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BankATMDAO.*;

public class GUIChangeManagerPassword extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private JPasswordField passwordField_2;
	private JButton btnChange;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField passwordField_1;
	private JLabel lblCurrentUser;
	private JLabel lblCurrentUsername;
	
	/**
	 * Create the frame.
	 */
	public GUIChangeManagerPassword(String username) {
		super();
		this.username = username;
		this.setTitle("Change password");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblHeadline = new JLabel("<html>\r\nPlease enter your new password twice.\r\n</html>");
		lblHeadline.setForeground(new Color(255, 0, 0));
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblHeadline.setBounds(175, 11, 487, 66);
		contentPane.add(lblHeadline);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_2.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_2.setBounds(300, 322, 290, 60);
		contentPane.add(passwordField_2);
		
		JButton btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(459, 443, 270, 60);
		contentPane.add(btnClose);
		
		btnChange = new JButton("Change");
		ChangeListener sil = new ChangeListener();
		btnChange.addActionListener(sil);
		btnChange.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnChange.setBounds(81, 443, 270, 60);
		contentPane.add(btnChange);
		
		lblUsername = new JLabel("New password: ");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblUsername.setBounds(80, 206, 160, 40);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Confirm password: ");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 20));
		lblPassword.setBounds(80, 332, 210, 40);
		contentPane.add(lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_1.setBounds(300, 200, 290, 60);
		contentPane.add(passwordField_1);
		
		lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUser.setBounds(81, 119, 160, 40);
		contentPane.add(lblCurrentUser);
		
		lblCurrentUsername = new JLabel(username);
		lblCurrentUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUsername.setBounds(306, 119, 232, 40);
		contentPane.add(lblCurrentUsername);
	}

	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
	
	class ChangeListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			try {
				String strPassword_1 = String.valueOf(passwordField_1.getPassword());
				String strPassword_2 = String.valueOf(passwordField_2.getPassword());
				if (strPassword_1.compareTo(strPassword_2) == 0 &&
						strPassword_1.length() != 0) {
					boolean invalid = false;
					for (int i = 0; i < strPassword_1.length(); i ++) {
						char c = strPassword_1.charAt(i);
						if (!Character.isLetterOrDigit(c)) {
							invalid = true;
							break;
						}
					}
					if (invalid) {
						JOptionPane.showMessageDialog(null, "Invalid character", 
								"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					}
					else {
						ManagerDAO managerDAO = new ManagerDAO();
						managerDAO.changePassword(username, strPassword_1);
						JOptionPane.showMessageDialog(null, "Password changed!", 
								"", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						managerDAO.closeConn();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "The passwords don't match", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}