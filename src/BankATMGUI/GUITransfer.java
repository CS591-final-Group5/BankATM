package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUITransfer extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JButton btnClose;
	private JButton btnTransfer;
	private JLabel lblTransfer;
	private JLabel lblDesc;
	private JLabel lblDisplayedCurrencyType;
	private JLabel lblPasswordOfThe;
	private JLabel lblTransferType;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JComboBox comboBox_CashType;
	private JComboBox comboBox_Type;
	private String currencyType = "USD"; // default
	private String cashType = "USD"; // default
	private JTextField textField;
	private final String accountType = "Securities";
	private JTable leftTable;
	private JTable rightTable;
	private JRadioButton rdbtn1;
	private JRadioButton rdbtn2;
	
	/**
	 * Create the frame.
	 */
	public GUITransfer(String username) {
		super();
		setBounds(10, 10, 1000, 650);
		this.username = username;
		this.setTitle("Transfer");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblTransfer = new JLabel("<html>\r\nTransfer.\r\n</html>");
		lblTransfer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransfer.setForeground(Color.RED);
		lblTransfer.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblTransfer.setBounds(160, 11, 669, 40);
		contentPane.add(lblTransfer);
		
		lblDesc = new JLabel("<html>\r\n- Transfer between a savings account and a securities account<br>\r\n</html>");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setForeground(Color.DARK_GRAY);
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblDesc.setBounds(146, 40, 700, 74);
		contentPane.add(lblDesc);
		
		btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(687, 551, 270, 60);
		contentPane.add(btnClose);
		
		btnTransfer = new JButton("Transfer");
		TransferListener cl = new TransferListener();
		btnTransfer.addActionListener(cl);
		btnTransfer.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnTransfer.setBounds(394, 551, 270, 60);
		contentPane.add(btnTransfer);
		
		JScrollPane scrollPane_left = new JScrollPane();
		scrollPane_left.setBounds(40, 210, 340, 180);
		contentPane.add(scrollPane_left);
		
		JScrollPane scrollPane_right = new JScrollPane();
		scrollPane_right.setBounds(40, 410, 340, 180);
		contentPane.add(scrollPane_right);
		
		leftTable = new JTable();
		leftTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		leftTable.setModel(new DefaultTableModel(
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
		leftTable.getColumnModel().getColumn(0).setPreferredWidth(122);
		leftTable.getColumnModel().getColumn(1).setPreferredWidth(72);
		scrollPane_left.setViewportView(leftTable);
		
		rightTable = new JTable();
		rightTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		rightTable.setModel(new DefaultTableModel(
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
		rightTable.getColumnModel().getColumn(0).setPreferredWidth(122);
		rightTable.getColumnModel().getColumn(1).setPreferredWidth(72);
		scrollPane_right.setViewportView(rightTable);
		
		JLabel lblPassword = new JLabel("Password of the savings account:");
		lblPassword.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblPassword.setBounds(414, 318, 425, 40);
		contentPane.add(lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_1.setBounds(414, 369, 250, 50);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_2.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_2.setBounds(414, 481, 250, 50);
		contentPane.add(passwordField_2);
		
		comboBox_Type = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_Type.addItemListener(ctl);
		comboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_Type.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_Type.setBackground(UIManager.getColor("Button.background"));
		comboBox_Type.setBounds(40, 169, 160, 30);
		contentPane.add(comboBox_Type);
		
		JLabel lblAmount = new JLabel("Transfer amount and currency \r\ntype:");
		lblAmount.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblAmount.setBounds(414, 125, 432, 40);
		contentPane.add(lblAmount);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setBounds(652, 159, 250, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		comboBox_CashType = new JComboBox();
		CashTypeListener cashtl = new CashTypeListener();
		comboBox_CashType.addItemListener(cashtl);
		comboBox_CashType.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_CashType.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_CashType.setBackground(UIManager.getColor("Button.background"));
		comboBox_CashType.setBounds(413, 169, 160, 30);
		contentPane.add(comboBox_CashType);
		
		lblDisplayedCurrencyType = new JLabel("Displayed currency type:");
		lblDisplayedCurrencyType.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblDisplayedCurrencyType.setBounds(40, 125, 324, 40);
		contentPane.add(lblDisplayedCurrencyType);
		
		lblPasswordOfThe = new JLabel("Password of the securities account:");
		lblPasswordOfThe.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblPasswordOfThe.setBounds(414, 430, 425, 40);
		contentPane.add(lblPasswordOfThe);
		
		rdbtn1 = new JRadioButton("from savings to securities");
		rdbtn1.setSelected(true);
		rdbtn1.setFont(new Font("Consolas", Font.PLAIN, 22));
		rdbtn1.setBounds(600, 228, 357, 40);
		contentPane.add(rdbtn1);
		
		rdbtn2 = new JRadioButton("from securites to savings");
		rdbtn2.setFont(new Font("Consolas", Font.PLAIN, 22));
		rdbtn2.setBounds(600, 271, 357, 40);
		contentPane.add(rdbtn2);

		btGroup = new ButtonGroup();
		btGroup.add(rdbtn1);
		btGroup.add(rdbtn2);
		
		lblTransferType = new JLabel("Transfer type:\r\n");
		lblTransferType.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblTransferType.setBounds(414, 250, 180, 40);
		contentPane.add(lblTransferType);
		
		AccountDAO accountDAO = new AccountDAO();
		setTable(accountDAO.getAccounts(username));
		accountDAO.closeConn();
	}
	
	class TransferListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			/*
			 * Choose a savings account and a securities account
			 * Transfer a acceptable amount
			 * Set password correctly
			 */
			int idx_up = leftTable.getSelectedRow();
			if (idx_up == -1) {
				JOptionPane.showMessageDialog(null, "Please select a savings account!");
				return;
			}
			
			int idx_down = rightTable.getSelectedRow();
			if (idx_down == -1) {
				JOptionPane.showMessageDialog(null, "Please select a securities account!");
				return;
			}
			
			DefaultTableModel dtm_up = (DefaultTableModel) leftTable.getModel();
			String type_up = dtm_up.getValueAt(idx_up, 2).toString();
			Double balance_up = Double.valueOf(dtm_up.getValueAt(idx_up, 1).toString());
			String accountNumber_up = dtm_up.getValueAt(idx_up, 0).toString();
			DefaultTableModel dtm_down = (DefaultTableModel) rightTable.getModel();
			String type_down = dtm_down.getValueAt(idx_down, 2).toString();
			Double balance_down = Double.valueOf(dtm_down.getValueAt(idx_down, 1).toString());
			String accountNumber_down = dtm_down.getValueAt(idx_down, 0).toString();
			
			CurrencyUSD currencyUSD_up = new CurrencyUSD(balance_up, currencyType);
			balance_up = currencyUSD_up.getAmount();
			CurrencyUSD currencyUSD_down = new CurrencyUSD(balance_down, currencyType);
			balance_down = currencyUSD_down.getAmount();
			
			// check the format
			String strPassword_up = String.valueOf(passwordField_1.getPassword());
			String strPassword_down = String.valueOf(passwordField_2.getPassword());			
			boolean invalid = false;
			for (int i = 0; i < strPassword_up.length(); i ++) {
				char c = strPassword_up.charAt(i);
				if (!Character.isLetterOrDigit(c)) {
					invalid = true;
					break;
				}
			}
			for (int i = 0; i < strPassword_down.length(); i ++) {
				char c = strPassword_down.charAt(i);
				if (!Character.isLetterOrDigit(c)) {
					invalid = true;
					break;
				}
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid password!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// verify username and password in database
			AccountDAO accountDAO = new AccountDAO();
			if (!accountDAO.authenticate(accountNumber_up, strPassword_up) || 
					!accountDAO.authenticate(accountNumber_down, strPassword_down)) {
				JOptionPane.showMessageDialog(null, "Password doesn't match!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// check the format of the amount
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
				accountDAO.closeConn();
				return;
			}
			
			// check the scope of data
			if (strAmount.length() >= 8) {
				JOptionPane.showMessageDialog(null, "One transaction can't exceed 10000$!",
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			double amount = convertCash(Double.valueOf(strAmount));
			if (amount > 10000) {
				JOptionPane.showMessageDialog(null, "One transaction can't exceed 10000$!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			else if (amount <= 1000) {
				JOptionPane.showMessageDialog(null, "One transaction must exceed 1000$!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			
			if (rdbtn1.isSelected()) {
				// SavingsToSecurities
				if (balance_up < 5000) {
					JOptionPane.showMessageDialog(null, "Please select a savings account"
							+ " which has more than 5000$!");
					accountDAO.closeConn();
					return;
				}
				if (accountDAO.getAllBalanceOfSavings(username) - amount < SavingsAccounts.MINSUM) {
					JOptionPane.showMessageDialog(null, "The sum of balance of all your savings "
							+ "accounts can't be less than " + SavingsAccounts.MINSUM + "$!");
					accountDAO.closeConn();
					return;
				}	
				Transactions transaction = new Transactions(username, "", accountNumber_up, 
						null, null, Transactions.TYPE_4, accountNumber_down, amount);
				if (accountDAO.depositMoney(accountNumber_up, -amount, transaction) && 
						accountDAO.depositMoney(accountNumber_down, amount, null)) {
					JOptionPane.showMessageDialog(null, "Successfully transfered!");
					setTable(accountDAO.getAccounts(username));
					accountDAO.closeConn();
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					accountDAO.closeConn();
				}
			}
			else if (rdbtn2.isSelected()) {
				// SecuritiesToSavings
				if (accountDAO.getBalance(accountNumber_down) < amount) {
					JOptionPane.showMessageDialog(null, "No enough balance");
					accountDAO.closeConn();
					return;
				}
				Transactions transaction = new Transactions(username, "", accountNumber_down, 
						null, null, Transactions.TYPE_4, accountNumber_up, amount);
				if (accountDAO.depositMoney(accountNumber_down, -amount, transaction) && 
						accountDAO.depositMoney(accountNumber_up, amount, null)) {
					JOptionPane.showMessageDialog(null, "Successfully transfered!");
					setTable(accountDAO.getAccounts(username));
					accountDAO.closeConn();
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed!", 
							"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					accountDAO.closeConn();
				}
			}
		}
	}
	
	protected double convertCash(double x) {
		CurrencyUSD USD = null;
		if (cashType.compareTo(CurrencyUSD.abbr) == 0) {
			return x;
		}
		else if (cashType.compareTo(CurrencyCNY.abbr) == 0) {
			USD = new CurrencyUSD(x, CurrencyCNY.abbr);
		}
		else if (cashType.compareTo(CurrencyRUB.abbr) == 0) {
			USD = new CurrencyUSD(x, CurrencyRUB.abbr);
		}
		return USD.getAmount();
	}
	
	protected double convertCur(double x) {
		if (currencyType.compareTo(CurrencyUSD.abbr) == 0) {
			return x;
		}
		else if (currencyType.compareTo(CurrencyCNY.abbr) == 0) {
			CurrencyCNY CNY = new CurrencyCNY(new CurrencyUSD(x));
			return CNY.getAmount();
		}
		else if (currencyType.compareTo(CurrencyRUB.abbr) == 0) {
			CurrencyRUB RUB = new CurrencyRUB(new CurrencyUSD(x));
			return RUB.getAmount();
		}
		return x;
	}
	
	private void setTable(ArrayList<Accounts> accounts) {
		DefaultTableModel dtm_left = (DefaultTableModel) leftTable.getModel();
		dtm_left.setRowCount(0);
		for (Accounts c: accounts) {
			if (c instanceof SavingsAccounts) {
				Vector v = new Vector();
				v.add(c.getAccountNumber());
				v.add(convertCur(c.getBalance()));
				v.add(c.getAbbr());
				dtm_left.addRow(v);
			}
		}
		DefaultTableModel dtm_right = (DefaultTableModel) rightTable.getModel();
		dtm_right.setRowCount(0);
		for (Accounts c: accounts) {
			if (c instanceof SecuritiesAccounts) {
				Vector v = new Vector();
				v.add(c.getAccountNumber());
				v.add(convertCur(c.getBalance()));
				v.add(c.getAbbr());
				dtm_right.addRow(v);
			}
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
