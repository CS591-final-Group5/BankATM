package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUIModifyStocksPrice extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JButton btnClose;
	private JButton btnChange;
	private JTable stocksTable;
	private JScrollPane scrollPane;
	private String currencyType = "USD"; // default
	private String cashType = "USD"; // default
	private JTextField textField_price;
	
	/**
	 * Create the frame.
	 */
	public GUIModifyStocksPrice() {
		super();
		this.setTitle("Create stocks");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblCreateANew = new JLabel("<html>\r\nChange price of stocks.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
		btGroup = new ButtonGroup();
		
		lblDesc = new JLabel("<html>\r\nSelect a stock, set its new price<br>\r\n</html>");
		lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesc.setForeground(Color.DARK_GRAY);
		lblDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblDesc.setBounds(50, 65, 700, 60);
		contentPane.add(lblDesc);
		
		btnClose = new JButton("Close");
		CloseListener cll = new CloseListener();
		btnClose.addActionListener(cll);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(460, 460, 270, 60);
		contentPane.add(btnClose);
		
		btnChange = new JButton("Change");
		CreateListener cl = new CreateListener();
		btnChange.addActionListener(cl);
		btnChange.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnChange.setBounds(70, 460, 270, 60);
		contentPane.add(btnChange);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 170, 340, 265);
		contentPane.add(scrollPane);
		
		stocksTable = new JTable();
		stocksTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		stocksTable.setModel(new DefaultTableModel(
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
		stocksTable.getColumnModel().getColumn(0).setPreferredWidth(124);
		stocksTable.getColumnModel().getColumn(1).setPreferredWidth(73);
		scrollPane.setViewportView(stocksTable);
		
		JComboBox comboBox = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox.addItemListener(ctl);
		comboBox.setBackground(UIManager.getColor("Button.darkShadow"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox.setBounds(70, 136, 160, 30);
		contentPane.add(comboBox);
		
		textField_price = new JTextField();
		textField_price.setHorizontalAlignment(SwingConstants.CENTER);
		textField_price.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField_price.setColumns(10);
		textField_price.setBounds(460, 280, 200, 40);
		contentPane.add(textField_price);
		
		JLabel lblPrice = new JLabel("Set new price");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblPrice.setBounds(460, 189, 228, 40);
		contentPane.add(lblPrice);
		
		JComboBox comboBox_price = new JComboBox();
		CashTypeListener cashtl = new CashTypeListener();
		comboBox_price.addItemListener(cashtl);
		comboBox_price.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_price.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_price.setBackground(SystemColor.controlDkShadow);
		comboBox_price.setBounds(460, 240, 160, 30);
		contentPane.add(comboBox_price);
		
		StockDAO stockDAO = new StockDAO();
		setTable(stockDAO.getAllStocks());
		stockDAO.closeConn();
	}
	
	class CashTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				cashType = jcb.getSelectedItem().toString();
			}
		}
	}
	
	class CurrencyTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currencyType = jcb.getSelectedItem().toString();
			}
			StockDAO stockDAO = new StockDAO();
			setTable(stockDAO.getAllStocks());
			stockDAO.closeConn();
		}
	}
	
	private boolean checkDouble(String str) {
		try {
			double temp = Double.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;	
		}
	}
	
	class CreateListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			int idx = stocksTable.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(null, "Please select a stock!");
				return;
			}
			DefaultTableModel dtm = (DefaultTableModel) stocksTable.getModel();
			String stockName = dtm.getValueAt(idx, 0).toString();
			String strPrice = textField_price.getText();
			
			StockDAO stockDAO = new StockDAO();
			
			if (!checkDouble(strPrice)) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				stockDAO.closeConn();
				return;
			}
			
			double price = Double.valueOf(strPrice);
			if (price < 0) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				stockDAO.closeConn();
				return;
			}
			
			stockDAO.updatePrice(stockName, convertCash(price));
			setTable(stockDAO.getAllStocks());
			stockDAO.closeConn();
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
	
	private void setTable(ArrayList<Stocks> stocks) {
		DefaultTableModel dtm = (DefaultTableModel) stocksTable.getModel();
		dtm.setRowCount(0);
		System.out.println(stocks.size());
		for (Stocks s: stocks) {
			Vector v = new Vector();
			v.add(s.getStockName());
			v.add(s.getAmount());
			v.add(convertCur(s.getPrice()));
			dtm.addRow(v);
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
}
