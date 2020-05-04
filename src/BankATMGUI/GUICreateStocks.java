package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUICreateStocks extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private ButtonGroup btGroup;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JButton btnClose;
	private JButton btnCreate;
	private JTable stocksTable;
	private JScrollPane scrollPane;
	private String currencyType = "USD"; // default
	private JTextField textField_name;
	private JTextField textField_amount;
	private JTextField textField_price;
	
	/**
	 * Create the frame.
	 */
	public GUICreateStocks() {
		super();
		this.setTitle("Create stocks");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblCreateANew = new JLabel("<html>\r\nCreate new stocks.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
		btGroup = new ButtonGroup();
		
		lblDesc = new JLabel("<html>\r\n<br>\r\n</html>");
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
		
		btnCreate = new JButton("Create");
		CreateListener cl = new CreateListener();
		btnCreate.addActionListener(cl);
		btnCreate.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnCreate.setBounds(70, 460, 270, 60);
		contentPane.add(btnCreate);
		
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
		
		textField_name = new JTextField();
		textField_name.setHorizontalAlignment(SwingConstants.CENTER);
		textField_name.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField_name.setBounds(460, 192, 200, 40);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_amount = new JTextField();
		textField_amount.setHorizontalAlignment(SwingConstants.CENTER);
		textField_amount.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField_amount.setColumns(10);
		textField_amount.setBounds(460, 294, 200, 40);
		contentPane.add(textField_amount);
		
		textField_price = new JTextField();
		textField_price.setHorizontalAlignment(SwingConstants.CENTER);
		textField_price.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField_price.setColumns(10);
		textField_price.setBounds(460, 395, 200, 40);
		contentPane.add(textField_price);
		
		JLabel lblName = new JLabel("Stock name");
		lblName.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(460, 141, 200, 40);
		contentPane.add(lblName);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmount.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblAmount.setBounds(460, 243, 200, 40);
		contentPane.add(lblAmount);
		
		JLabel lblPrice = new JLabel("Price($)");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblPrice.setBounds(460, 345, 200, 40);
		contentPane.add(lblPrice);
		
		StockDAO stockDAO = new StockDAO();
		setTable(stockDAO.getAllStocks());
		stockDAO.closeConn();
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
	
	private boolean checkInt(String str) {
		try {
			int temp = Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;	
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
			String strStockName = textField_name.getText();
			String strAmount = textField_amount.getText();
			String strPrice = textField_price.getText();
			
			StockDAO stockDAO = new StockDAO();
			
			if (stockDAO.findStock(strStockName)) {
				JOptionPane.showMessageDialog(null, "Duplicated stock name!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				stockDAO.closeConn();
				return;
			}
			
			if (!checkInt(strAmount) || !checkDouble(strPrice)) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				stockDAO.closeConn();
				return;
			}
			
			int amount = Integer.valueOf(strAmount);
			double price = Double.valueOf(strPrice);
			if (amount < 0 || price < 0) {
				JOptionPane.showMessageDialog(null, "Invalid input!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
				stockDAO.closeConn();
				return;
			}
			
			Stocks stock = new Stocks(strStockName, amount, price);
			stockDAO.createStock(stock);
			setTable(stockDAO.getAllStocks());
			stockDAO.closeConn();
			
			textField_price.setText("");
			textField_amount.setText("");
			textField_name.setText("");
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
	
	private void setTable(ArrayList<Stocks> stocks) {
		DefaultTableModel dtm = (DefaultTableModel) stocksTable.getModel();
		dtm.setRowCount(0);
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
