package BankATMGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUIBankManagerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
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
		
		JLabel lblHeadline = new JLabel("<html>\r\nHi, Christine<br>\r\nYour username is BMcpk<br>\r\nYour password is CS591<br>\r\n</html>");
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblHeadline.setBounds(270, 28, 240, 120);
		contentPane.add(lblHeadline);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setToolTipText("");
		textField.setBounds(240, 196, 290, 60);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField.setBounds(240, 322, 290, 60);
		contentPane.add(passwordField);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					GUIHomepage frame = new GUIHomepage();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(459, 443, 270, 60);
		contentPane.add(btnBack);
		
		btnSignIn = new JButton("Sign in");
		btnSignIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnSignIn.setBounds(81, 443, 270, 60);
		contentPane.add(btnSignIn);
		
		lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblUsername.setBounds(100, 206, 110, 40);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 20));
		lblPassword.setBounds(100, 332, 110, 40);
		contentPane.add(lblPassword);
	}
}
