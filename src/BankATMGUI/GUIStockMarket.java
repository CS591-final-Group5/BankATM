package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUIStockMarket extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private JButton btnClose;
	private JButton btnBuy;
	private JLabel lblStock;
	private JLabel lblDesc;
	private JLabel lblDisplayedCurrencyType;
	private JLabel lblPasswordOfThe;
	private JLabel lblSelectASecurities;
	private JPasswordField passwordField;
	private JComboBox comboBox_Type;
	private String currencyType = "USD"; // default
	private String cashType = "USD"; // default
	private JTable leftTable;
	private JTable rightTable;
	private JTable accountTable;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JLabel lblRealizedProfit_1;
	private JLabel lblUnrealizedProfit_1;
	
	/**
	 * Create the frame.
	 */
	public GUIStockMarket(String username) {
		super();
		setBounds(10, 10, 1000, 650);
		this.username = username;
		this.setTitle("Stock market");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblStock = new JLabel("<html>\r\nBuy or sell stock.\r\n</html>");
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setForeground(Color.RED);
		lblStock.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblStock.setBounds(160, 11, 669, 40);
		contentPane.add(lblStock);
		
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
		btnClose.setBounds(864, 369, 120, 50);
		contentPane.add(btnClose);
		
		btnBuy = new JButton("Buy");
		BuyListener cl = new BuyListener();
		btnBuy.addActionListener(cl);
		btnBuy.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBuy.setBounds(864, 242, 120, 50);
		contentPane.add(btnBuy);
		
		JScrollPane scrollPane_left = new JScrollPane();
		scrollPane_left.setBounds(40, 431, 340, 180);
		contentPane.add(scrollPane_left);
		
		JScrollPane scrollPane_right = new JScrollPane();
		scrollPane_right.setBounds(440, 431, 521, 180);
		contentPane.add(scrollPane_right);
		
		leftTable = new JTable();
		leftTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		leftTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"STOCK_NAME", "AMOUNT", "PRICE"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		leftTable.getColumnModel().getColumn(0).setPreferredWidth(124);
		leftTable.getColumnModel().getColumn(1).setPreferredWidth(73);
		scrollPane_left.setViewportView(leftTable);
		
		rightTable = new JTable();
		rightTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		rightTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SID", "STOCK_NAME", "AMOUNT", "BID_PRICE", "CURRENT_PRICE"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		rightTable.getColumnModel().getColumn(1).setPreferredWidth(98);
		rightTable.getColumnModel().getColumn(2).setPreferredWidth(78);
		rightTable.getColumnModel().getColumn(3).setPreferredWidth(88);
		rightTable.getColumnModel().getColumn(4).setPreferredWidth(88);
		scrollPane_right.setViewportView(rightTable);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Consolas", Font.PLAIN, 20));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(440, 164, 250, 40);
		contentPane.add(passwordField);
		
		comboBox_Type = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_Type.addItemListener(ctl);
		comboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_Type.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_Type.setBackground(UIManager.getColor("Button.background"));
		comboBox_Type.setBounds(40, 400, 160, 30);
		contentPane.add(comboBox_Type);
		
		lblDisplayedCurrencyType = new JLabel("Displayed currency type:");
		lblDisplayedCurrencyType.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblDisplayedCurrencyType.setBounds(40, 359, 324, 40);
		contentPane.add(lblDisplayedCurrencyType);
		
		lblPasswordOfThe = new JLabel("Password of the securities account:");
		lblPasswordOfThe.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblPasswordOfThe.setBounds(440, 125, 425, 40);
		contentPane.add(lblPasswordOfThe);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 175, 340, 180);
		contentPane.add(scrollPane);
		
		accountTable = new JTable();
		accountTable.setModel(new DefaultTableModel(
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
		accountTable.getColumnModel().getColumn(0).setPreferredWidth(122);
		accountTable.getColumnModel().getColumn(1).setPreferredWidth(72);
		scrollPane.setViewportView(accountTable);
		
		lblSelectASecurities = new JLabel("Select a securities account:");
		lblSelectASecurities.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblSelectASecurities.setBounds(40, 125, 358, 40);
		contentPane.add(lblSelectASecurities);
		
		JLabel lblHowManyStocks = new JLabel("How many stocks you wanna buy?\r\n");
		lblHowManyStocks.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblHowManyStocks.setBounds(440, 215, 425, 40);
		contentPane.add(lblHowManyStocks);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setBounds(440, 252, 250, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSell = new JButton("Sell");
		SellListener sl = new SellListener();
		btnSell.addActionListener(sl);
		btnSell.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnSell.setBounds(864, 308, 120, 50);
		contentPane.add(btnSell);
		
		JLabel lblRealizedProfit = new JLabel("Realized Profit: ");
		lblRealizedProfit.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblRealizedProfit.setBounds(440, 315, 229, 40);
		contentPane.add(lblRealizedProfit);
		
		JLabel lblUnrealizedProfit = new JLabel("Unrealized Profit: ");
		lblUnrealizedProfit.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblUnrealizedProfit.setBounds(440, 380, 229, 40);
		contentPane.add(lblUnrealizedProfit);
		
		StockDAO stockDAO = new StockDAO();
		
		lblRealizedProfit_1 = new JLabel(String.valueOf(stockDAO.getRealizedProfit(username)));
		lblRealizedProfit_1.setForeground(Color.RED);
		lblRealizedProfit_1.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblRealizedProfit_1.setBounds(699, 315, 130, 40);
		contentPane.add(lblRealizedProfit_1);
		
		lblUnrealizedProfit_1 = new JLabel(String.valueOf(stockDAO.getUnrealizedProfit(username)));
		lblUnrealizedProfit_1.setForeground(Color.RED);
		lblUnrealizedProfit_1.setFont(new Font("Consolas", Font.ITALIC, 22));
		lblUnrealizedProfit_1.setBounds(699, 380, 130, 40);
		contentPane.add(lblUnrealizedProfit_1);

		setTable(stockDAO.getSecuritiesAccounts(username), 
				stockDAO.getAllStocks(), 
				stockDAO.getStockDeal(username));
		stockDAO.closeConn();
	}
	
	class SellListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			int idx_right = rightTable.getSelectedRow();
			if (idx_right == -1) {
				JOptionPane.showMessageDialog(null, "Please select the stock you have bought!");
				return;
			}
			
			String strPassword = String.valueOf(passwordField.getPassword());
			if (strPassword.length() == 0) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DefaultTableModel dtm_right = (DefaultTableModel) rightTable.getModel();
			String sid = dtm_right.getValueAt(idx_right, 0).toString();
			System.out.println(sid);
			
			StockDAO stockDAO = new StockDAO();
			StockDeal stockDeal = stockDAO.getStockDealWithSID(sid);
			
			boolean invalid = false;
			for (int i = 0; i < strPassword.length(); i ++) {
				char c = strPassword.charAt(i);
				if (!Character.isLetterOrDigit(c)) {
					invalid = true;
					break;
				}
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid character!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			AccountDAO accountDAO = new AccountDAO();
			if (!accountDAO.authenticate(stockDeal.getAccountNumber(), strPassword)) {
				JOptionPane.showMessageDialog(null, "Wrong password!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			accountDAO.closeConn();
			
			stockDAO.sellStock(stockDeal);
			setTable(stockDAO.getSecuritiesAccounts(username), 
					stockDAO.getAllStocks(), 
					stockDAO.getStockDeal(username));
			passwordField.setText("");
			textField.setText("");
			lblRealizedProfit_1.setText(String.valueOf(stockDAO.getRealizedProfit(username)));
			lblUnrealizedProfit_1.setText(String.valueOf(stockDAO.getUnrealizedProfit(username)));
			JOptionPane.showMessageDialog(null, "Sold successfully!");
			stockDAO.closeConn();
		}
	}
	
	class BuyListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			/*
			 * Choose a securities account
			 * Enter password correctly
			 * Enter the amount
			 * Buy/Sell stocks
			 */
			int idx_up = accountTable.getSelectedRow();
			if (idx_up == -1) {
				JOptionPane.showMessageDialog(null, "Please select a securities account!");
				return;
			}
			int idx_left = leftTable.getSelectedRow();
			if (idx_left == -1) {
				JOptionPane.showMessageDialog(null, "Please select a stock!");
				return;
			}
			
			DefaultTableModel dtm_up = (DefaultTableModel) accountTable.getModel();
			String accountNumber = dtm_up.getValueAt(idx_up, 0).toString();
			double balance = Double.valueOf(dtm_up.getValueAt(idx_up, 1).toString());
			
			String strPassword = String.valueOf(passwordField.getPassword());
			DefaultTableModel dtm_left = (DefaultTableModel) leftTable.getModel();
			String stockName = dtm_left.getValueAt(idx_left, 0).toString();
			int amount = Integer.valueOf(dtm_left.getValueAt(idx_left, 1).toString());
			double price = Double.valueOf(dtm_left.getValueAt(idx_left, 2).toString());
			
			boolean invalid = false;
			for (int i = 0; i < strPassword.length(); i ++) {
				char c = strPassword.charAt(i);
				if (!Character.isLetterOrDigit(c)) {
					invalid = true;
					break;
				}
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid character!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
			}
			
			AccountDAO accountDAO = new AccountDAO();
			
			if (!accountDAO.authenticate(accountNumber, strPassword)) {
				JOptionPane.showMessageDialog(null, "Wrong password!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				accountDAO.closeConn();
				return;
			}
			accountDAO.closeConn();
			
			String strAmount = textField.getText();
			if (!checkInt(strAmount)) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int stockAmount = Integer.valueOf(strAmount);
			
			if (amount < stockAmount) {
				JOptionPane.showMessageDialog(null, "No enough stocks!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (balance < 1.0 * stockAmount * price) {
				JOptionPane.showMessageDialog(null, "No enough balance!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			StockDAO stockDAO = new StockDAO();
			stockDAO.buyStock(new StockDeal(stockDAO.generateSid(), accountNumber, stockName, 
					stockAmount, price, price, "Hold"));
			setTable(stockDAO.getSecuritiesAccounts(username), 
					stockDAO.getAllStocks(), 
					stockDAO.getStockDeal(username));
			passwordField.setText("");
			textField.setText("");
			lblRealizedProfit_1.setText(String.valueOf(stockDAO.getRealizedProfit(username)));
			lblUnrealizedProfit_1.setText(String.valueOf(stockDAO.getUnrealizedProfit(username)));
			stockDAO.closeConn();
		}
	}
	
	private boolean checkInt(String str) {
		try {
			double temp = Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;	
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
	
	private void setTable(ArrayList<Accounts> accounts, 
			ArrayList<Stocks> stocks, 
			ArrayList<StockDeal> stockDeal) {
		DefaultTableModel dtm_up = (DefaultTableModel) accountTable.getModel();
		dtm_up.setRowCount(0);
		for (Accounts c: accounts) {
			Vector v = new Vector();
			v.add(c.getAccountNumber());
			v.add(convertCur(c.getBalance()));
			v.add(c.getAbbr());
			dtm_up.addRow(v);
		}
		
		DefaultTableModel dtm_left = (DefaultTableModel) leftTable.getModel();
		dtm_left.setRowCount(0);
		for (Stocks s: stocks) {
			Vector v = new Vector();
			v.add(s.getStockName());
			v.add(s.getAmount());
			v.add(convertCur(s.getPrice()));
			dtm_left.addRow(v);
		}
		
		DefaultTableModel dtm_right = (DefaultTableModel) rightTable.getModel();
		dtm_right.setRowCount(0);
		for (StockDeal s: stockDeal) {
			if (s.getType().compareTo("Sold") == 0) {
				continue;
			}
			Vector v = new Vector();
			v.add(s.getSid());
			v.add(s.getStockName());
			v.add(s.getAmount());
			v.add(convertCur(s.getBidPrice()));
			v.add(convertCur(s.getCurrentPrice()));
			dtm_right.addRow(v);
		}
	}
	
	class CurrencyTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currencyType = jcb.getSelectedItem().toString();
			}
			StockDAO stockDAO = new StockDAO();
			setTable(stockDAO.getSecuritiesAccounts(username), 
					stockDAO.getAllStocks(), 
					stockDAO.getStockDeal(username));
			stockDAO.closeConn();
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
}
