package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUIOpenAccounts extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JLabel lblAccountType;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JRadioButton rdbtnChecking;
	private JRadioButton rdbtnSavings;
	private JButton btnBack;
	private JButton btnCreate;
	private JTable accountsTable;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JComboBox comboBox;
	private String currencyType = "USD"; // default
	
	/**
	 * Create the frame.
	 */
	public GUIOpenAccounts(String username) {
		super();
		this.username = username;
		this.setTitle("Open accounts");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblAccountType = new JLabel("Account type");
		lblAccountType.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblAccountType.setBounds(440, 133, 168, 40);
		contentPane.add(lblAccountType);
		
		lblCreateANew = new JLabel("<html>\r\nOpen a new account.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
		rdbtnChecking = new JRadioButton("Checking account");
		rdbtnChecking.setSelected(true);
		rdbtnChecking.setFont(new Font("Consolas", Font.PLAIN, 22));
		rdbtnChecking.setBounds(479, 180, 250, 40);
		contentPane.add(rdbtnChecking);
		
		rdbtnSavings = new JRadioButton("Savings account");
		rdbtnSavings.setFont(new Font("Consolas", Font.PLAIN, 22));
		rdbtnSavings.setBounds(479, 223, 250, 40);
		contentPane.add(rdbtnSavings);
		
		btGroup = new ButtonGroup();
		btGroup.add(rdbtnChecking);
		btGroup.add(rdbtnSavings);
		
		lblDesc = new JLabel("<html>\r\n- The system will generate a unique card ID for the new account<br>\r\n</html>");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setForeground(Color.DARK_GRAY);
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblDesc.setBounds(50, 65, 700, 60);
		contentPane.add(lblDesc);
		
		btnBack = new JButton("Back");
		BackListener bl = new BackListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(460, 460, 270, 60);
		contentPane.add(btnBack);
		
		btnCreate = new JButton("Create");
		CreateListener cl = new CreateListener();
		btnCreate.addActionListener(cl);
		btnCreate.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnCreate.setBounds(80, 460, 270, 60);
		contentPane.add(btnCreate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 170, 340, 254);
		contentPane.add(scrollPane);
		
		accountsTable = new JTable();
		accountsTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		accountsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ACCOUNT_NUMBER", "BALANCE", "TYPE"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		accountsTable.getColumnModel().getColumn(0).setPreferredWidth(122);
		accountsTable.getColumnModel().getColumn(1).setPreferredWidth(72);
		scrollPane.setViewportView(accountsTable);
		
		JLabel lblAccountType_1 = new JLabel("Password:");
		lblAccountType_1.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblAccountType_1.setBounds(440, 263, 168, 40);
		contentPane.add(lblAccountType_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setBounds(460, 314, 250, 50);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_2.setBounds(460, 375, 250, 50);
		contentPane.add(passwordField_2);
		
		comboBox = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox.addItemListener(ctl);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox.setBackground(SystemColor.controlDkShadow);
		comboBox.setBounds(70, 136, 160, 30);
		contentPane.add(comboBox);
		
		AccountDAO accountDAO = new AccountDAO();
		setTable(accountDAO.getAccounts(username));
		accountDAO.closeConn();
	}
	
	class CreateListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String strPassword_1 = String.valueOf(passwordField_1.getPassword());
			String strPassword_2 = String.valueOf(passwordField_2.getPassword());			
			boolean invalid = false;
			if (strPassword_1.length() == 0 || strPassword_2.length() == 0
					|| strPassword_1.length() != strPassword_2.length()) {
				invalid = true;
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid character!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (int i = 0; i < strPassword_1.length(); i ++) {
				char c1 = strPassword_1.charAt(i);
				char c2 = strPassword_2.charAt(i);
				if (!Character.isLetterOrDigit(c1) || !Character.isLetterOrDigit(c2)
						|| c1 != c2) {
					invalid = true;
					break;
				}
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid character!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String type = "";
			if (rdbtnChecking.isSelected()) {
				type = "Checking";
			}
			else if (rdbtnSavings.isSelected()) {
				type = "Savings";
			}
			
			AccountDAO accountDAO = new AccountDAO();
			String accountNumber = accountDAO.openNewAccount(username, type, strPassword_1);
			setTable(accountDAO.getAccounts(username));
			passwordField_1.setText("");
			passwordField_2.setText("");
			accountDAO.closeConn();
			JOptionPane.showMessageDialog(null, "Account created successfully!\n"
					+ "The account number is " + accountNumber);
			// fee
			ManagerDAO managerDAO = new ManagerDAO();
			managerDAO.chargeFee(1);
			managerDAO.closeConn();
		}
	}
	
	private double convertCur(double x) {
		CurrencyUSD USD = new CurrencyUSD(x);
		if (currencyType.compareTo(CurrencyUSD.abbr) == 0) {
			return x;
		}
		else if (currencyType.compareTo(CurrencyCNY.abbr) == 0) {
			CurrencyCNY CNY = new CurrencyCNY(USD);
			return CNY.getAmount();
		}
		else if (currencyType.compareTo(CurrencyRUB.abbr) == 0) {
			CurrencyRUB RUB = new CurrencyRUB(USD);
			return RUB.getAmount();
		}
		return x;
	}
	
	private void setTable(ArrayList<Accounts> accounts) {
		DefaultTableModel dtm = (DefaultTableModel) accountsTable.getModel();
		dtm.setRowCount(0);
		for (Accounts c: accounts) {
			Vector v = new Vector();
			v.add(c.getAccountNumber());
			v.add(convertCur(c.getBalance()));
			v.add(c.getAbbr());
			dtm.addRow(v);
		}
	}
	
	class CurrencyTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currencyType = jcb.getSelectedItem().toString();
			}
			AccountDAO accountDAO = new AccountDAO();
			setTable(accountDAO.getAccounts(username));
			accountDAO.closeConn();
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
}
