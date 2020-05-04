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
import BankATMCommon.*;

public class GUIManagerCheckUp extends JInternalFrame {

	private JPanel contentPane;
	private JTable transactionsTable;
	private String currencyType = "USD"; // default
	private JComboBox comboBox_Date;
	private JComboBox comboBox_CashType;
	private String specifiedDate;
	private String specifiedUsername = "";
	private JTable userTable;
	private JButton btnCheckUp;
	private JButton btnClose;
	
	/**
	 * Create the frame.
	 */
	public GUIManagerCheckUp() {
		this.setTitle("Check up on a customer");
		setBounds(10, 10, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(661, 535, 270, 60);
		CloseListener cl = new CloseListener();
		btnClose.addActionListener(cl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		getContentPane().add(btnClose);
		
		btnCheckUp = new JButton("Check Up");
		btnCheckUp.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnCheckUp.setBounds(109, 535, 270, 60);
		CheckUpListener cul = new CheckUpListener();
		btnCheckUp.addActionListener(cul);
		contentPane.add(btnCheckUp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 180, 705, 328);
		contentPane.add(scrollPane);
		
		transactionsTable = new JTable();
		transactionsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TID", "ACCOUNT_NUMBER", "TYPE", "DATE", "DETAILS", "TARGET", "AMOUNT"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		transactionsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		transactionsTable.getColumnModel().getColumn(1).setPreferredWidth(105);
		transactionsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		transactionsTable.getColumnModel().getColumn(4).setPreferredWidth(120);
		transactionsTable.getColumnModel().getColumn(5).setPreferredWidth(45);
		transactionsTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		scrollPane.setViewportView(transactionsTable);
		// center all texts
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i < transactionsTable.getColumnCount(); i ++) {
			transactionsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		JLabel lblDisplayTransactions = new JLabel("<html>\r\nCheck up on a customer.\r\n</html>");
		lblDisplayTransactions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayTransactions.setForeground(Color.RED);
		lblDisplayTransactions.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblDisplayTransactions.setBounds(170, 11, 669, 66);
		contentPane.add(lblDisplayTransactions);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Consolas", Font.BOLD, 20));
		lblDate.setBounds(335, 129, 110, 40);
		contentPane.add(lblDate);
		
		comboBox_Date = new JComboBox();
		DateListener dl = new DateListener();
		comboBox_Date.addItemListener(dl);
		comboBox_Date.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_Date.setBackground(SystemColor.controlDkShadow);
		comboBox_Date.setBounds(414, 132, 160, 30);
		setComboBox(comboBox_Date);
		contentPane.add(comboBox_Date);
		
		JLabel lblCurrencyType = new JLabel("Currency type: ");
		lblCurrencyType.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrencyType.setBounds(627, 129, 178, 40);
		contentPane.add(lblCurrencyType);
		
		comboBox_CashType = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_CashType.addItemListener(ctl);
		comboBox_CashType.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_CashType.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_CashType.setBackground(SystemColor.controlDkShadow);
		comboBox_CashType.setBounds(800, 132, 160, 30);
		contentPane.add(comboBox_CashType);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 180, 186, 328);
		contentPane.add(scrollPane_1);
		
		userTable = new JTable();
		userTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"USERNAME"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		userTable.getColumnModel().getColumn(0).setPreferredWidth(146);
		scrollPane_1.setViewportView(userTable);
		
		JLabel lblSelectA = new JLabel("<html>\r\n- Select a user and a specific date, then click \"Check Up\".\r\n</html>");
		lblSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectA.setForeground(Color.DARK_GRAY);
		lblSelectA.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblSelectA.setBounds(139, 64, 700, 60);
		contentPane.add(lblSelectA);
		
		ManagerDAO managerDAO = new ManagerDAO();
		setUserTable(managerDAO.getAllUser());
		specifiedDate = managerDAO.getDate(0).toString();
		TransactionsDAO transactionsDAO = new TransactionsDAO();
		setTable(transactionsDAO.getTransactions(specifiedUsername));
		transactionsDAO.closeConn();
		managerDAO.closeConn();
	}
	
	class CheckUpListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			int idx = userTable.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(null, "Please select a user");
				return;
			}
			DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
			specifiedUsername = dtm.getValueAt(idx, 0).toString();
			TransactionsDAO transactionsDAO = new TransactionsDAO();
			setTable(transactionsDAO.getTransactions(specifiedUsername));
			transactionsDAO.closeConn();
		}
	}
	
	class DateListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				specifiedDate = jcb.getSelectedItem().toString();
			}
		}
	}
	
	private void setComboBox(JComboBox jcb) {
		/*
		 * add all dates
		 */
		jcb.removeAllItems();
		ManagerDAO managerDAO = new ManagerDAO();
		ArrayList<Date> dates = managerDAO.getAllDates();
		System.out.println(dates.size()); // why 1
		managerDAO.closeConn();
		for (Date d: dates) {
			jcb.addItem(d);
		}
	}
	
	private void setUserTable(ArrayList<String> users) {
		DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
		dtm.setRowCount(0);
		for (String s: users) {
			Vector v = new Vector();
			v.add(s);
			dtm.addRow(v);
		}
	}
	
	protected void setTable(ArrayList<Transactions> transactions) {
		DefaultTableModel dtm = (DefaultTableModel) transactionsTable.getModel();
		dtm.setRowCount(0);
		for (Transactions t: transactions) {
			if (t.getTime().toString().compareTo(specifiedDate) != 0) {
				continue;
			}
			Vector v = new Vector();
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
			setTable(transactionsDAO.getTransactions(specifiedUsername));
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
