package BankATMGUI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.Accounts;
import BankATMCommon.CheckingAccounts;
import BankATMCommon.CurrencyCNY;
import BankATMCommon.CurrencyRUB;
import BankATMCommon.CurrencyUSD;
import BankATMCommon.SavingsAccounts;

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
	
	protected void setTable(ArrayList<Accounts> accounts) {
		DefaultTableModel dtm = (DefaultTableModel) accountsTable.getModel();
		dtm.setRowCount(0);
		for (Accounts c: accounts) {
			Vector v = new Vector();
			v.add(c.getAccountNumber());
			v.add(convertCur(c.getBalance()));
			if (c instanceof CheckingAccounts) {
				v.add("Checking");
			}
			else if (c instanceof SavingsAccounts) {
				v.add("Savings");
			}
			dtm.addRow(v);
		}
	}

}
