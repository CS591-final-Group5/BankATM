package BankATMGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMCommon.Collaterals;

import BankATMDAO.*;

public class GUIAddCollateral extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private JButton btnAdd;
	private JLabel lblDesc;
	private JLabel lblCurrentUser;
	private JLabel lblCurrentUsername;
	private JTextField textField;
	private JTable collateralTable;
	
	/**
	 * Create the frame.
	 */
	public GUIAddCollateral(String username) {
		super();
		this.username = username;
		this.setTitle("Add a collateral");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblHeadline = new JLabel("<html>\r\nPlease add your collateral.\r\n</html>");
		lblHeadline.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadline.setForeground(new Color(255, 0, 0));
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblHeadline.setBounds(60, 11, 669, 66);
		contentPane.add(lblHeadline);
		
		JButton btnBack = new JButton("Back");
		BackListener bl = new BackListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(459, 443, 270, 60);
		contentPane.add(btnBack);
		
		btnAdd = new JButton("Add");
		AddListener al = new AddListener();
		btnAdd.addActionListener(al);
		btnAdd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnAdd.setBounds(459, 212, 270, 60);
		contentPane.add(btnAdd);
		
		lblDesc = new JLabel("Description of a new collateral: ");
		lblDesc.setFont(new Font("Consolas", Font.BOLD, 20));
		lblDesc.setBounds(90, 390, 444, 40);
		contentPane.add(lblDesc);
		
		lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUser.setBounds(90, 90, 206, 40);
		contentPane.add(lblCurrentUser);
		
		lblCurrentUsername = new JLabel(username);
		lblCurrentUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUsername.setBounds(340, 90, 232, 40);
		contentPane.add(lblCurrentUsername);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Consolas", Font.PLAIN, 20));
		textField.setBounds(88, 448, 290, 60);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 160, 300, 220);
		contentPane.add(scrollPane);
		
		collateralTable = new JTable();
		collateralTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CID", "Description"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(collateralTable);
		
		CollateralDAO collateralDAO = new CollateralDAO();
		setTable(collateralDAO.getCollaterals(username));
		collateralDAO.closeConn();
	}
	
	class AddListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String strCollateral = textField.getText();
			boolean invalid = false;
			for (int i = 0; i < strCollateral.length(); i ++) {
				char c = strCollateral.charAt(i);
				if (!Character.isLetterOrDigit(c) && c != ' ') {
					invalid = true;
					break;
				}
			}
			if (strCollateral.length() == 0) {
				invalid = true;
			}
			if (invalid) {
				JOptionPane.showMessageDialog(null, "Invalid character!", 
						"ERROR OCCURS", JOptionPane.ERROR_MESSAGE);
			}
			else {
				CollateralDAO collateralDAO = new CollateralDAO();
				collateralDAO.addCollateral(username, strCollateral);
				setTable(collateralDAO.getCollaterals(username));
				collateralDAO.closeConn();
				JOptionPane.showMessageDialog(null, "Data added successfully!");
				// setVisible(false);
			}
			textField.setText("");
		}
	}
	
	private void setTable(ArrayList<Collaterals> collaterals) {
		DefaultTableModel dtm = (DefaultTableModel) collateralTable.getModel();
		dtm.setRowCount(0);
		for (Collaterals c: collaterals) {
			Vector v = new Vector();
			v.add(c.getCid());
			v.add(c.getDescription());
			dtm.addRow(v);
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
	
}
	