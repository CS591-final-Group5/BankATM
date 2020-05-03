package BankATMGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BankATMDAO.*;

public class GUIUserRegister extends JFrame {

	private JPanel contentPane;
	private JButton btnRegister;
	private JTextField textEmail;
	private JTextField textUsername;
	private JTextField textFullname;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JLabel lblFullname;

	/**
	 * Create the frame.
	 */
	public GUIUserRegister() {
		setTitle("Create a customer account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeadline = new JLabel("<html>\r\nPlease enter your username and password!\r\n</html>");
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblHeadline.setBounds(194, 20, 420, 100);
		contentPane.add(lblHeadline);
		
		textUsername = new JTextField("");
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setFont(new Font("Consolas", Font.PLAIN, 20));
		textUsername.setToolTipText("");
		textUsername.setBounds(240, 120, 290, 60);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password\r\n");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField.setBounds(240, 210, 290, 60);
		contentPane.add(passwordField);
		
		JButton btnBack = new JButton("Back");
		BackListener bl = new BackListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(460, 460, 270, 60);
		contentPane.add(btnBack);
		
		btnRegister = new JButton("Register");
		RegisterListener rgtl = new RegisterListener();
		btnRegister.addActionListener(rgtl);
		btnRegister.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnRegister.setBounds(80, 460, 270, 60);
		contentPane.add(btnRegister);
		
		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setToolTipText("");
		textEmail.setFont(new Font("Consolas", Font.PLAIN, 20));
		textEmail.setColumns(10);
		textEmail.setBounds(240, 300, 290, 60);
		contentPane.add(textEmail);
		
		lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblUsername.setBounds(80, 130, 110, 40);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 20));
		lblPassword.setBounds(80, 225, 110, 40);
		contentPane.add(lblPassword);
		
		lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Consolas", Font.BOLD, 20));
		lblEmail.setBounds(80, 310, 110, 40);
		contentPane.add(lblEmail);
		
		lblFullname = new JLabel("Fullname:");
		lblFullname.setFont(new Font("Consolas", Font.BOLD, 20));
		lblFullname.setBounds(80, 395, 110, 40);
		contentPane.add(lblFullname);
		
		textFullname = new JTextField();
		textFullname.setToolTipText("");
		textFullname.setHorizontalAlignment(SwingConstants.CENTER);
		textFullname.setFont(new Font("Consolas", Font.PLAIN, 20));
		textFullname.setColumns(10);
		textFullname.setBounds(240, 385, 290, 60);
		contentPane.add(textFullname);
	}

	class RegisterListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String strUsername = textUsername.getText();
			String strPassword = String.valueOf(passwordField.getPassword());
			String strEmail = textEmail.getText();
			String strFullname = textFullname.getText();
			/*
			 * Check if there is invalid character
			 * If the format is valid, create a user account, then go to homepage
			 */
			if (strUsername.length() == 0 || strPassword.length() == 0 
					|| strEmail.length() == 0 || strFullname.length() == 0) {
				JOptionPane.showMessageDialog(null, "Lack of enough info!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
			}
			else {
				boolean invalid = false;
				for (int i = 0; i < strUsername.length(); i ++) {
					char c = strUsername.charAt(i);
					if (!Character.isLetterOrDigit(c)) {
						invalid = true;
						break;
					}
				}
				for (int i = 0; i < strPassword.length(); i ++) {
					char c = strPassword.charAt(i);
					if (!Character.isLetterOrDigit(c)) {
						invalid = true;
						break;
					}
				}
				for (int i = 0; i < strEmail.length(); i ++) {
					char c = strEmail.charAt(i);
					if (!Character.isLetterOrDigit(c) && c != '@' && c != '.') {
						invalid = true;
						break;
					}
				}
				for (int i = 0; i < strFullname.length(); i ++) {
					char c = strFullname.charAt(i);
					if (!Character.isLetterOrDigit(c) && c != ' ') {
						invalid = true;
						break;
					}
				}
				if (invalid) {
					JOptionPane.showMessageDialog(null, "Invalid character!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				}
				else {
					AccountDAO accountDAO = new AccountDAO();
					if (accountDAO.findUser(strUsername)) {
						JOptionPane.showMessageDialog(null, "This username has already been claimed!", 
								"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					}
					else {
						accountDAO.createUser(strUsername, strPassword, strEmail, strFullname);
						setVisible(false);
						GUIHomepage frame = new GUIHomepage();
						frame.setVisible(true);
					}
					accountDAO.closeConn();
				}
			}
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			try {
				setVisible(false);
				GUIHomepage frame = new GUIHomepage();
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
