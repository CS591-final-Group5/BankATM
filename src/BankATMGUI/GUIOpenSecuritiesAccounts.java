package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUIOpenSecuritiesAccounts extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JButton btnClose;
	private JButton btnCreate;
	private JTable accountsTable;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JComboBox comboBox_CashType;
	private JComboBox comboBox_Type;
	private String currencyType = "USD"; // default
	private String cashType = "USD"; // default
	private JTextField textField;
	private final String accountType = "Securities";
	
	/**
	 * Create the frame.
	 */
	public GUIOpenSecuritiesAccounts(String username) {
		super();
		setBounds(100, 10, 800, 650);
		this.username = username;
		this.setTitle("Open securities accounts");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblCreateANew = new JLabel("<html>\r\nOpen a new account.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
		btGroup = new ButtonGroup();
		
		lblDesc = new JLabel("<html>\r\n- The system will generate a unique card ID for the new account<br>\r\n- Select a savings account and transfer money to the new account<br>\r\n</html>");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setForeground(Color.DARK_GRAY);
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblDesc.setBounds(50, 65, 700, 60);
		contentPane.add(lblDesc);
		
		btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(460, 551, 270, 60);
		contentPane.add(btnClose);
		
		btnCreate = new JButton("Create");
		CreateListener cl = new CreateListener();
		btnCreate.addActionListener(cl);
		btnCreate.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnCreate.setBounds(80, 551, 270, 60);
		contentPane.add(btnCreate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 184, 340, 346);
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
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblPassword.setBounds(440, 368, 168, 40);
		contentPane.add(lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setBounds(460, 419, 250, 50);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_2.setBounds(460, 480, 250, 50);
		contentPane.add(passwordField_2);
		
		comboBox_Type = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_Type.addItemListener(ctl);
		comboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_Type.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_Type.setBackground(SystemColor.controlDkShadow);
		comboBox_Type.setBounds(70, 150, 160, 30);
		contentPane.add(comboBox_Type);
		
		JLabel lblAmount = new JLabel("Transfer amount and type:");
		lblAmount.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblAmount.setBounds(440, 207, 324, 40);
		contentPane.add(lblAmount);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setBounds(460, 307, 250, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		comboBox_CashType = new JComboBox();
		CashTypeListener cashtl = new CashTypeListener();
		comboBox_CashType.addItemListener(cashtl);
		comboBox_CashType.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_CashType.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_CashType.setBackground(SystemColor.controlDkShadow);
		comboBox_CashType.setBounds(460, 262, 160, 30);
		contentPane.add(comboBox_CashType);
		
		AccountDAO accountDAO = new AccountDAO();
		setTable(accountDAO.getAccounts(username));
		accountDAO.closeConn();
	}
	
	class CreateListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			/*
			 * Choose a savings account
			 * Transfer a acceptable amount
			 * Set password correctly
			 */
			int idx = accountsTable.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(null, "Please select a savings account!");
				return;
			}
			DefaultTableModel dtm = (DefaultTableModel) accountsTable.getModel();
			String type = dtm.getValueAt(idx, 2).toString();
			Double balance = Double.valueOf(dtm.getValueAt(idx, 1).toString());
			String accountNumber = dtm.getValueAt(idx, 0).toString();
			if (type.compareTo("Savings") != 0) {
				JOptionPane.showMessageDialog(null, "Please select a savings account");
				return;
			}
			CurrencyUSD currencyUSD = new CurrencyUSD(balance, cashType);
			balance = currencyUSD.getAmount();
			if (balance < 5000) {
				JOptionPane.showMessageDialog(null, "Please select a savings account"
						+ " which has more than 5000$!");
				return;
			}
			
			String strPassword_1 = String.valueOf(passwordField_1.getPassword());
			String strPassword_2 = String.valueOf(passwordField_2.getPassword());			
			boolean invalid = false;
			if (strPassword_1.length() == 0 || strPassword_2.length() == 0
					|| strPassword_1.length() != strPassword_2.length()) {
				invalid = true;
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
				JOptionPane.showMessageDialog(null, "Invalid password!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String strAmount = textField.getText();
			for (int i = 0; i < strAmount.length(); i ++) {
				char c = strAmount.charAt(i);
				if (!Character.isLetterOrDigit(c)) {
					invalid = true;
					break;
				}
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				if (strAmount.length() >= 8) {
					JOptionPane.showMessageDialog(null, "One transaction can't exceed 10000$!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					return;
				}
				double amount = convertCash(Double.valueOf(strAmount));
				if (amount > 10000) {
					JOptionPane.showMessageDialog(null, "One transaction can't exceed 10000$!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (amount <= 1000) {
					JOptionPane.showMessageDialog(null, "One transaction must exceed 1000$!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					return;
				}
				AccountDAO accountDAO = new AccountDAO();
				String newID = accountDAO.openNewAccount(username, accountType, strPassword_1);
				if (accountDAO.depositMoney(accountNumber, -amount) && 
						accountDAO.depositMoney(newID, amount)) {
					JOptionPane.showMessageDialog(null, "Successfully transfered!");
					setTable(accountDAO.getAccounts(username));
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				}
				accountDAO.closeConn();
			}
		}
	}
		
	protected double convertCash(double x) {
		CurrencyUSD USD = new CurrencyUSD(x);
		if (cashType.compareTo(CurrencyUSD.abbr) == 0) {
			return x;
		}
		else if (cashType.compareTo(CurrencyCNY.abbr) == 0) {
			CurrencyCNY CNY = new CurrencyCNY(USD);
			return CNY.getAmount();
		}
		else if (cashType.compareTo(CurrencyRUB.abbr) == 0) {
			CurrencyRUB RUB = new CurrencyRUB(USD);
			return RUB.getAmount();
		}
		return x;
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
	
	class CashTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				cashType = jcb.getSelectedItem().toString();
			}
			AccountDAO accountDAO = new AccountDAO();
			setTable(accountDAO.getAccounts(username));
			accountDAO.closeConn();
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
}
