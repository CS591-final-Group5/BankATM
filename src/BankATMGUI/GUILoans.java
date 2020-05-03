package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUILoans extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JButton btnBack;
	private JTable accountsTable;
	private JScrollPane scrollPane;
	private String currencyType = "USD"; // default
	private String cashType = "USD"; // default
	private JTextField textField_Deposit;
	private JComboBox comboBox_CashType;
	private JComboBox comboBox_Type;
	private JLabel lblNewLabel;
	private JLabel lblAmount;
	private JPasswordField passwordField;
	private JLabel lblPasswordOfSpecified;
    private JButton btnRequest;
    private JTable loansTable;
	
	/**
	 * Create the frame.
	 */
	public GUILoans(String username) {
		super();
		this.username = username;
		this.setTitle("Loans");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblCreateANew = new JLabel("<html>\r\nRequest a Loan.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
		btGroup = new ButtonGroup();
		
		lblDesc = new JLabel("<html>\r\n- Choose your account and request a loan.\r\n</html>");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setForeground(Color.DARK_GRAY);
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblDesc.setBounds(50, 65, 700, 60);
		contentPane.add(lblDesc);
		
		btnBack = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(460, 460, 270, 60);
		contentPane.add(btnBack);
		
		btnRequest = new JButton("Request");
		RequestListener dl = new RequestListener();
		btnRequest.addActionListener(dl);
		btnRequest.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnRequest.setBounds(80, 460, 270, 60);
		contentPane.add(btnRequest);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 170, 340, 125);
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
		
		comboBox_Type = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_Type.addItemListener(ctl);
		comboBox_Type.setBackground(UIManager.getColor("Button.darkShadow"));
		comboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_Type.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_Type.setBounds(70, 136, 160, 30);
		contentPane.add(comboBox_Type);
		
		textField_Deposit = new JTextField();
		textField_Deposit.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Deposit.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField_Deposit.setBounds(460, 360, 250, 50);
		contentPane.add(textField_Deposit);
		textField_Deposit.setColumns(10);
		
		comboBox_CashType = new JComboBox();
		CashTypeListener cashtl = new CashTypeListener();
		comboBox_CashType.addItemListener(cashtl);
		comboBox_CashType.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_CashType.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_CashType.setBackground(SystemColor.controlDkShadow);
		comboBox_CashType.setBounds(601, 265, 160, 30);
		contentPane.add(comboBox_CashType);
		
		lblNewLabel = new JLabel("Loan type:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel.setBounds(437, 255, 131, 50);
		contentPane.add(lblNewLabel);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblAmount.setBounds(437, 299, 131, 50);
		contentPane.add(lblAmount);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField.setBounds(460, 195, 250, 50);
		contentPane.add(passwordField);
		
		lblPasswordOfSpecified = new JLabel("Password of specified account:");
		lblPasswordOfSpecified.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblPasswordOfSpecified.setBounds(437, 136, 347, 50);
		contentPane.add(lblPasswordOfSpecified);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(71, 310, 340, 125);
		contentPane.add(scrollPane_1);
		
		loansTable = new JTable();
		loansTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ACCOUNT_NUMBER", "AMOUNT_OF_LOAN"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		loansTable.getColumnModel().getColumn(0).setPreferredWidth(132);
		loansTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		loansTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		scrollPane_1.setViewportView(loansTable);
		
		AccountDAO accountDAO = new AccountDAO();
		setTable(accountDAO.getAccounts(username));
		setTable_L(accountDAO.getLoans(username));
		accountDAO.closeConn();
	}
	
	class CurrencyTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currencyType = jcb.getSelectedItem().toString();
			}
			AccountDAO accountDAO = new AccountDAO();
			setTable(accountDAO.getAccounts(username));
			setTable_L(accountDAO.getLoans(username));
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
			setTable_L(accountDAO.getLoans(username));
			accountDAO.closeConn();
		}
	}
	
	class RequestListener implements ActionListener {
		/*
		 * Request a loan Pipeline:
		 * -  Select account and verify password
		 * -  Use resonable amount and check collateral 
		 * -  Request loan
		 */
		public void actionPerformed( ActionEvent e ) {
			int idx = accountsTable.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(null, "Please select the account which you use to"
						+ "request a loan");
				return;
			}
			DefaultTableModel dtm = (DefaultTableModel) accountsTable.getModel();
			String accountNumber = dtm.getValueAt(idx, 0).toString();
			String accountType = dtm.getValueAt(idx, 2).toString();
			AccountDAO accountDAO = new AccountDAO();
			String strPassword = String.valueOf(passwordField.getPassword());
			if (!accountDAO.authenticate(accountNumber, strPassword)) {
				JOptionPane.showMessageDialog(null, "Wrong password!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			else {
				String strAmount = textField_Deposit.getText();
				boolean invalid = false;
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
				else {
					if (strAmount.length() >= 8) {
						JOptionPane.showMessageDialog(null, "One transaction can't exceed 10000$!", 
								"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
						accountDAO.closeConn();
						return;
					}
					double amount = convertCash(Double.valueOf(strAmount));
					if (amount >= 10000) {
						JOptionPane.showMessageDialog(null, "One transaction can't exceed 10000$!", 
								"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
						accountDAO.closeConn();
						return;
					}
					CollateralDAO collateralDAO = new CollateralDAO();
					if (!collateralDAO.hasCollaterals(username)) {
						JOptionPane.showMessageDialog(null, "You don't have collateral!", 
								"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
						return;
					}
					collateralDAO.closeConn();
					Transactions transaction = new Transactions(username, "", accountNumber, 
							accountType, null, Transactions.TYPE_3, "", amount);
					if (accountDAO.depositMoney(accountNumber, amount, transaction)) {
						JOptionPane.showMessageDialog(null, "Requested successfully!");
						setTable(accountDAO.getAccounts(username));
						setTable_L(accountDAO.getLoans(username));
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed!", 
								"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
					}
					accountDAO.closeConn();
				}
			}
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
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
	
	protected void setTable(ArrayList<Accounts> accounts) {
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
	
	protected void setTable_L(ArrayList<Loans> loans) {
		DefaultTableModel dtm = (DefaultTableModel) loansTable.getModel();
		dtm.setRowCount(0);
		for (Loans l: loans) {
			Vector v = new Vector();
			v.add(l.getAccountNumber());
			v.add(convertCur(l.getAmount()));
			dtm.addRow(v);
		}
		System.out.println("ddd");
	}
}
