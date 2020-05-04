package BankATMGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;

public class GUIDepositOrWithdrawal extends GUIInternalWindow {

	protected String username;
	protected JPanel contentPane;
	protected ButtonGroup btGroup;
	protected JLabel lblCreateANew;
	protected JLabel lblDesc;
	protected JButton btnBack;
	protected JTable accountsTable;
	protected JScrollPane scrollPane;
	protected String currencyType = "USD"; // default
	protected String cashType = "USD"; // default
	protected JTextField textField_Deposit;
	protected JComboBox comboBox_CashType;
	protected JComboBox comboBox_Type;
	protected JLabel lblNewLabel;
	protected JLabel lblAmount;
	protected JPasswordField passwordField;
	protected JLabel lblPasswordOfSpecified;
	
	/**
	 * Create the frame.
	 */
	public GUIDepositOrWithdrawal() {
		super();
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

}
