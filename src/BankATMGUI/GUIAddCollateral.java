package BankATMGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import BankATMDAO.*;

public class GUIAddCollateral extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private JButton btnAdd;
	private JLabel lblUsername;
	private JLabel lblCurrentUser;
	private JLabel lblCurrentUsername;
	private JTextField textField;
	
	/**
	 * Create the frame.
	 */
	public GUIAddCollateral(String username) {
		super();
		this.username = username;
		this.setTitle("Add a collateral");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblHeadline = new JLabel("<html>\r\nPlease add your collateral.\r\n</html>");
		lblHeadline.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadline.setForeground(new Color(255, 0, 0));
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblHeadline.setBounds(60, 11, 669, 66);
		contentPane.add(lblHeadline);
		
		JButton btnBack = new JButton("Back");
		BackListener bl = new BackListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(459, 443, 270, 60);
		contentPane.add(btnBack);
		
		btnAdd = new JButton("Add");
		AddListener al = new AddListener();
		btnAdd.addActionListener(al);
		btnAdd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnAdd.setBounds(81, 443, 270, 60);
		contentPane.add(btnAdd);
		
		lblUsername = new JLabel("Name of collateral: ");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblUsername.setBounds(90, 265, 271, 40);
		contentPane.add(lblUsername);
		
		lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUser.setBounds(90, 130, 206, 40);
		contentPane.add(lblCurrentUser);
		
		lblCurrentUsername = new JLabel(username);
		lblCurrentUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUsername.setBounds(340, 130, 232, 40);
		contentPane.add(lblCurrentUsername);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setBounds(350, 250, 290, 60);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	class AddListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String strCollateral = textField.getText();
			boolean invalid = false;
			for (int i = 0; i < strCollateral.length(); i ++) {
				char c = strCollateral.charAt(i);
				if (!Character.isLetterOrDigit(c)) {
					invalid = true;
					break;
				}
			}
			if (strCollateral.length() == 0) {
				invalid = true;
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid character", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
			}
			else {
				CollateralDAO collateralDAO = new CollateralDAO();
				collateralDAO.addCollateral(username, strCollateral);
				collateralDAO.closeConn();
				JOptionPane.showMessageDialog(null, "Data added successfully", 
						"sds", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			}
			textField.setText("");
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
	
}
	