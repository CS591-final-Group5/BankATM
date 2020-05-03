package BankATMGUI;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;

import BankATMDAO.*;


import BankATMCommon.Accounts;
import BankATMCommon.CurrencyCNY;
import BankATMCommon.CurrencyRUB;
import BankATMCommon.CurrencyUSD;
import BankATMCommon.Transactions;

public class GUIDisplayAllTransactions extends GUIInternalWindow {

	private JPanel contentPane;
	private JTable transactionsTable;
	private String currencyType = "USD"; // default
	private JComboBox comboBox_CashType;
	
	/**
	 * Create the frame.
	 */
	public GUIDisplayAllTransactions() {
		this.setTitle("Display all transactions");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(460, 460, 270, 60);
		CloseListener cl = new CloseListener();
		btnClose.addActionListener(cl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 135, 739, 299);
		contentPane.add(scrollPane);
		
		transactionsTable = new JTable();
		transactionsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"USERNAME", "TID", "ACCOUNT_NUMBER", "TYPE", "DATE", "DETAILS", "TARGET", "AMOUNT"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		transactionsTable.getColumnModel().getColumn(1).setPreferredWidth(46);
		transactionsTable.getColumnModel().getColumn(2).setPreferredWidth(111);
		transactionsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		transactionsTable.getColumnModel().getColumn(5).setPreferredWidth(120);
		transactionsTable.getColumnModel().getColumn(6).setPreferredWidth(54);
		transactionsTable.getColumnModel().getColumn(7).setPreferredWidth(68);
		transactionsTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		scrollPane.setViewportView(transactionsTable);
		// center all texts
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i < transactionsTable.getColumnCount(); i ++) {
			transactionsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		JLabel lblDisplayTransactions = new JLabel("<html>\r\nDisplay all transactions.\r\n</html>");
		lblDisplayTransactions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayTransactions.setForeground(Color.RED);
		lblDisplayTransactions.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblDisplayTransactions.setBounds(61, 11, 669, 66);
		contentPane.add(lblDisplayTransactions);
		
		JLabel lblCurrencyType = new JLabel("Currency type: ");
		lblCurrencyType.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrencyType.setBounds(381, 84, 178, 40);
		contentPane.add(lblCurrencyType);
		
		comboBox_CashType = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_CashType.addItemListener(ctl);
		comboBox_CashType.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_CashType.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_CashType.setBackground(SystemColor.controlDkShadow);
		comboBox_CashType.setBounds(554, 87, 160, 30);
		contentPane.add(comboBox_CashType);
		
		TransactionsDAO transactionsDAO = new TransactionsDAO();
		setTable(transactionsDAO.getAllTransactions());
		transactionsDAO.closeConn();
	}
	
	protected void setTable(ArrayList<Transactions> transactions) {
		DefaultTableModel dtm = (DefaultTableModel) transactionsTable.getModel();
		dtm.setRowCount(0);
		for (Transactions t: transactions) {
			Vector v = new Vector();
			v.add(t.getUsername());
			v.add(t.getTid());
			v.add(t.getAccountNumber());
			if (t.getDetails().compareTo(Transactions.TYPE_4) == 0) {
				v.add(null);
			}
			else {
				v.add(t.getType());
			}
			v.add(t.getTime());
			v.add(t.getDetails());
			v.add(t.getTarget());
			if (t.getDetails().compareTo(Transactions.TYPE_5) == 0 ||
					t.getDetails().compareTo(Transactions.TYPE_6) == 0) {
				v.add(null);
			}
			else {
				v.add(convertCur(t.getAmount()));
			}
			dtm.addRow(v);
		}
	}
	
	class CurrencyTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currencyType = jcb.getSelectedItem().toString();
			}
			TransactionsDAO transactionsDAO = new TransactionsDAO();
			setTable(transactionsDAO.getAllTransactions());
			transactionsDAO.closeConn();
		}
	}
	
	protected double convertCur(double x) {
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
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
}
