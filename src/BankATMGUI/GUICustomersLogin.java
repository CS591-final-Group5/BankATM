package BankATMGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import BankATMDAO.*;
import BankATMCommon.*;


public class GUICustomersLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JButton btnSignIn;
	private JLabel lblUsername;
	private JLabel lblPassword;

	/**
	 * Create the frame.
	 */
	public GUICustomersLogin() {
		setTitle("Login as customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeadline = new JLabel("<html>\r\nPlease enter your username and password!\r\n</html>");
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblHeadline.setBounds(194, 28, 420, 100);
		contentPane.add(lblHeadline);
		
		textUsername = new JTextField();
		textUsername.setText("123123");
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setFont(new Font("Consolas", Font.PLAIN, 20));
		textUsername.setToolTipText("");
		textUsername.setBounds(240, 196, 290, 60);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField.setBounds(240, 322, 290, 60);
		contentPane.add(passwordField);
		
		JButton btnBack = new JButton("Back");
		BackListener bl = new BackListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(459, 443, 270, 60);
		contentPane.add(btnBack);
		
		btnSignIn = new JButton("Sign in");
		SignInListener sil = new SignInListener();
		btnSignIn.addActionListener(sil);
		btnSignIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnSignIn.setBounds(81, 443, 270, 60);
		contentPane.add(btnSignIn);
		
		lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblUsername.setBounds(80, 206, 110, 40);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 20));
		lblPassword.setBounds(80, 332, 110, 40);
		contentPane.add(lblPassword);
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
	
	class SignInListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			try {
				String strUsername = textUsername.getText();
				String strPassword = String.valueOf(passwordField.getPassword());				
				if (Database.userLogin(strUsername, strPassword)) {
					setVisible(false);
					GUICustomersInterface frame = new GUICustomersInterface(strUsername);
					frame.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Login fail: wrong username or password", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
