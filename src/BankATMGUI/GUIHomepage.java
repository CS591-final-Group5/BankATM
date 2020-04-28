package BankATMGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUIHomepage extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUIHomepage() {
		setTitle("BankATM System Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to BankATM System!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(100, 41, 582, 60);
		contentPane.add(lblNewLabel);
		
		JButton btnBMLogin = new JButton("Bank Manager Login");
		btnBMLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					GUIBankManagerLogin frame = new GUIBankManagerLogin();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnBMLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBMLogin.setBounds(100, 180, 270, 60);
		contentPane.add(btnBMLogin);
		
		JButton btnCtLogin = new JButton("Customers Login");
		btnCtLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					GUICustomersLogin frame = new GUICustomersLogin();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCtLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnCtLogin.setBounds(430, 180, 270, 60);
		contentPane.add(btnCtLogin);
		
		JButton btnCreateAccount = new JButton("Create customer account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					GUIUserRegister frame = new GUIUserRegister();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCreateAccount.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnCreateAccount.setBounds(265, 335, 270, 60);
		contentPane.add(btnCreateAccount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnExit.setBounds(265, 442, 270, 60);
		contentPane.add(btnExit);
	}

}
