package BankATMGUI;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BankATMDAO.*;
import BankATMCommon.*;

public class GUIDeleteCollateral extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private JButton btnDelete;
	private JButton btnClose;
	private JTable collateralTable;
	
	/**
	 * Create the frame.
	 */
	public GUIDeleteCollateral(String username) {
		super();
		this.username = username;
		this.setTitle("Delete collaterals");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblHeadline = new JLabel("<html>\r\nCollateral list\r\n</html>");
		lblHeadline.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadline.setForeground(new Color(255, 0, 0));
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblHeadline.setBounds(60, 11, 669, 66);
		contentPane.add(lblHeadline);
		
		btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(459, 443, 270, 60);
		contentPane.add(btnClose);
		
		btnDelete = new JButton("Delete selected row");
		DeleteListener dl = new DeleteListener();
		btnDelete.addActionListener(dl);
		btnDelete.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnDelete.setBounds(459, 205, 270, 60);
		contentPane.add(btnDelete);
		
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
		
		JLabel lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUser.setBounds(106, 85, 206, 40);
		contentPane.add(lblCurrentUser);
		
		JLabel lblCurrentUsername = new JLabel("<dynamic>");
		lblCurrentUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUsername.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCurrentUsername.setBounds(356, 85, 232, 40);
		contentPane.add(lblCurrentUsername);
		
		CollateralDAO collateralDAO = new CollateralDAO();
		setTable(collateralDAO.getCollaterals(username));
		collateralDAO.closeConn();
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
	
	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int idx = collateralTable.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(null, "Please select the collateral that you want to delete");
				return;
			}
			DefaultTableModel dtm = (DefaultTableModel) collateralTable.getModel();
			String cid = dtm.getValueAt(idx, 0).toString();
			CollateralDAO collateralDAO = new CollateralDAO();
			if (collateralDAO.deleteCollateral(cid)) {
				JOptionPane.showMessageDialog(null, "Successfully deleted!");
				// Refresh table
				setTable(collateralDAO.getCollaterals(username));
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed to delete this collateral!");
			}
			collateralDAO.closeConn();
		}
	}
	
}
	