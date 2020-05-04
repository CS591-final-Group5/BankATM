package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUIChangeCardsPassword extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JButton btnClose;
	private JButton btnChange;
	private JTable accountsTable;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JComboBox comboBox;
	private String currencyType = "USD"; // default
	private JPasswordField passwordField_old;
	
	/**
	 * Create the frame.
	 */
	public GUIChangeCardsPassword(String username) {
		super();
		this.username = username;
		this.setTitle("Change password of one card");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblCreateANew = new JLabel("<html>\r\nChange password.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
		lblDesc = new JLabel("<html>\r\n- Select one account, and change its password<br>\r\n</html>");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setForeground(Color.DARK_GRAY);
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblDesc.setBounds(50, 65, 700, 60);
		contentPane.add(lblDesc);
		
		btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(460, 460, 270, 60);
		contentPane.add(btnClose);
		
		btnChange = new JButton("Change");
		ChangeListener cl = new ChangeListener();
		btnChange.addActionListener(cl);
		btnChange.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnChange.setBounds(80, 460, 270, 60);
		contentPane.add(btnChange);
		
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
		
		JLabel lblAccountType_1 = new JLabel("Enter new password 2 times:");
		lblAccountType_1.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblAccountType_1.setBounds(431, 263, 353, 40);
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
		
		JLabel lblAccountType_1_1 = new JLabel("Old password\"");
		lblAccountType_1_1.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblAccountType_1_1.setBounds(431, 158, 353, 40);
		contentPane.add(lblAccountType_1_1);
		
		passwordField_old = new JPasswordField();
		passwordField_old.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_old.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField_old.setBounds(460, 202, 250, 50);
		contentPane.add(passwordField_old);
		
		AccountDAO accountDAO = new AccountDAO();
		setTable(accountDAO.getAccounts(username));
		accountDAO.closeConn();
	}
	
	class ChangeListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			int idx = accountsTable.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(null, "Please select an account!");
				return;
			}
			
			String strPassword_old = String.valueOf(passwordField_old.getPassword());
			DefaultTableModel dtm = (DefaultTableModel) accountsTable.getModel();
			String strAccountNumber = dtm.getValueAt(idx, 0).toString();
			
			if (strPassword_old.length() == 0) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			boolean invalid = false;
			for (int i = 0; i < strPassword_old.length(); i ++) {
				char c = strPassword_old.charAt(i);
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
			
			AccountDAO accountDAO = new AccountDAO();
			if (!accountDAO.authenticate(strAccountNumber, strPassword_old)) {
				JOptionPane.showMessageDialog(null, "Wrong old password!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			
			String strPassword_1 = String.valueOf(passwordField_1.getPassword());
			String strPassword_2 = String.valueOf(passwordField_2.getPassword());			
			if (strPassword_1.length() == 0 || strPassword_2.length() == 0
					|| strPassword_1.length() != strPassword_2.length()) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
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
				accountDAO.closeConn();
				return;
			}
			
			accountDAO.changePasswordOfCard(strAccountNumber, strPassword_1);
			setTable(accountDAO.getAccounts(username));
			passwordField_1.setText("");
			passwordField_2.setText("");
			accountDAO.closeConn();
			JOptionPane.showMessageDialog(null, "Password changed successfully!\n");
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
}
