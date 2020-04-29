package BankATMGUI;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

import BankATMDAO.*;
import BankATMCommon.*;

public class GUIDeleteCollateral extends GUIInternalWindow {

	private String username;
	private JPanel contentPane;
	private JButton btnAdd;
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
		
		JButton btnBack = new JButton("Back");
		BackListener bl = new BackListener();
		btnBack.addActionListener(bl);
		btnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnBack.setBounds(459, 443, 270, 60);
		contentPane.add(btnBack);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnAdd.setBounds(81, 443, 270, 60);
		contentPane.add(btnAdd);
		
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
		
		JButton btnRefresh = new JButton("Display/Refresh list");
		RefreshListener rl = new RefreshListener();
		btnRefresh.addActionListener(rl);
		btnRefresh.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnRefresh.setBounds(459, 170, 270, 60);
		contentPane.add(btnRefresh);
	}
	
	private void setTable(ArrayList<Collaterals> collaterals) {
		DefaultTableModel dfm = (DefaultTableModel) collateralTable.getModel();
		dfm.setRowCount(0);
		for (Collaterals c: collaterals) {
			Vector v = new Vector();
			v.add(c.getCid());
			v.add(c.getDescription());
			dfm.addRow(v);
		}
	}
	
	class RefreshListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CollateralDAO collateralDAO = new CollateralDAO();
			setTable(collateralDAO.getCollaterals(username));
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	
}
	