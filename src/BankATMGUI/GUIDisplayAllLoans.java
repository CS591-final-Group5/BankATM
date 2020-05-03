package BankATMGUI;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.*;
import BankATMDAO.*;

public class GUIDisplayAllLoans extends GUIInternalWindow {

	private JPanel contentPane;
	private JLabel lblCreateANew;
	private JLabel lblDesc;
	private JButton btnBack;
	private String currencyType = "USD"; // default
	private JComboBox comboBox_Type;
    private JTable loansTable;
	
	/**
	 * Create the frame.
	 */
	public GUIDisplayAllLoans() {
		super();
		this.setTitle("Display loans");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		lblCreateANew = new JLabel("<html>\r\nDisplay all loans.\r\n</html>");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setForeground(Color.RED);
		lblCreateANew.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblCreateANew.setBounds(60, 11, 669, 66);
		contentPane.add(lblCreateANew);
		
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
		
		comboBox_Type = new JComboBox();
		CurrencyTypeListener ctl = new CurrencyTypeListener();
		comboBox_Type.addItemListener(ctl);
		comboBox_Type.setBackground(UIManager.getColor("Button.darkShadow"));
		comboBox_Type.setModel(new DefaultComboBoxModel(new String[] {"USD", "CNY", "RUB"}));
		comboBox_Type.setFont(new Font("Consolas", Font.PLAIN, 20));
		comboBox_Type.setBounds(71, 174, 160, 30);
		contentPane.add(comboBox_Type);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(71, 215, 644, 220);
		contentPane.add(scrollPane_1);
		
		loansTable = new JTable();
		loansTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"USERNAME", "ACCOUNT_NUMBER", "AMOUNT_OF_LOAN"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		loansTable.getColumnModel().getColumn(0).setPreferredWidth(170);
		loansTable.getColumnModel().getColumn(1).setPreferredWidth(177);
		loansTable.getColumnModel().getColumn(2).setPreferredWidth(163);
		loansTable.setFont(new Font("Consolas", Font.PLAIN, 14));
		scrollPane_1.setViewportView(loansTable);
		
		AccountDAO accountDAO = new AccountDAO();
		setTable(accountDAO.getAllLoans());
		accountDAO.closeConn();
	}
	
	class CurrencyTypeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JComboBox jcb = (JComboBox)e.getSource();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				currencyType = jcb.getSelectedItem().toString();
			}
			AccountDAO accountDAO = new AccountDAO();
			setTable(accountDAO.getAllLoans());
			accountDAO.closeConn();
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
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
	
	protected void setTable(ArrayList<Loans> loans) {
		DefaultTableModel dtm = (DefaultTableModel) loansTable.getModel();
		dtm.setRowCount(0);
		for (Loans l: loans) {
			Vector v = new Vector();
			AccountDAO accountDAO = new AccountDAO();
			v.add(accountDAO.getUsername(l.getAccountNumber()));
			accountDAO.closeConn();
			v.add(l.getAccountNumber());
			v.add(convertCur(l.getAmount()));
			dtm.addRow(v);
		}
		System.out.println("ddd");
	}
}
