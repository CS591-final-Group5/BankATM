package BankATMGUI;

import BankATMDAO.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUIBankManagerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JButton btnSignIn;
	private JLabel lblUsername;
	private JLabel lblPassword;

	/**
	 * Create the frame.
	 */
	public GUIBankManagerLogin() {
		setTitle("Login as bank manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeadline = new JLabel("<html>\r\nHi, Christine<br>\r\nYour username is BMcpk<br>\r\nYour default password is CS591<br>\r\n</html>");
		lblHeadline.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblHeadline.setBounds(153, 27, 509, 120);
		contentPane.add(lblHeadline);
		
		textUsername = new JTextField("BMcpk");
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setFont(new Font("Consolas", Font.PLAIN, 20));
		textUsername.setToolTipText("");
		textUsername.setBounds(300, 196, 290, 60);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField.setBounds(300, 322, 290, 60);
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
		lblUsername.setBounds(110, 206, 160, 40);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 20));
		lblPassword.setBounds(110, 332, 160, 40);
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
				ManagerDAO managerDAO = new ManagerDAO();
				if (managerDAO.authenticate(strUsername, strPassword)) {
					setVisible(false);
					GUIManagerInterface frame = new GUIManagerInterface(strUsername);
					frame.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong username or wrong password!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				}
				managerDAO.closeConn();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
