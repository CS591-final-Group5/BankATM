package BankATMGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BankATMDAO.*;
import java.awt.Color;

public class GUIChangeDate extends GUIInternalWindow {

	private JPanel contentPane;
	private JButton btnChange;
	private JLabel lblStartDate;
	private JLabel lblCurrentDate;
	private JLabel lblStartDate_DB;
	private JLabel lblCurrentDate_DB;
	
	/**
	 * Create the frame.
	 */
	public GUIChangeDate() {
		super();
		setBounds(100, 100, 500, 500);
		this.setTitle("Change date");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblHeadline = new JLabel("<html>\r\nChange Date.\r\n</html>");
		lblHeadline.setForeground(new Color(255, 0, 0));
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblHeadline.setBounds(165, 11, 170, 66);
		contentPane.add(lblHeadline);
		
		JButton btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(110, 377, 270, 60);
		contentPane.add(btnClose);
		
		lblStartDate = new JLabel("Start date:");
		lblStartDate.setFont(new Font("Consolas", Font.PLAIN, 25));
		lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate.setBounds(23, 109, 210, 50);
		contentPane.add(lblStartDate);
		
		lblCurrentDate = new JLabel("Current date:");
		lblCurrentDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentDate.setFont(new Font("Consolas", Font.PLAIN, 25));
		lblCurrentDate.setBounds(10, 189, 210, 50);
		contentPane.add(lblCurrentDate);
		
		ManagerDAO managerDAO = new ManagerDAO();
		
		lblStartDate_DB = new JLabel(managerDAO.getDate(0).toString());
		lblStartDate_DB.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate_DB.setFont(new Font("Consolas", Font.PLAIN, 25));
		lblStartDate_DB.setBounds(255, 109, 210, 50);
		contentPane.add(lblStartDate_DB);
		
		lblCurrentDate_DB = new JLabel(managerDAO.getDate(1).toString());
		lblCurrentDate_DB.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentDate_DB.setFont(new Font("Consolas", Font.PLAIN, 25));
		lblCurrentDate_DB.setBounds(255, 189, 210, 50);
		contentPane.add(lblCurrentDate_DB);
		
		JButton btnAdd = new JButton("Add one day");
		AddListener al = new AddListener();
		btnAdd.addActionListener(al);
		btnAdd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnAdd.setBounds(110, 287, 270, 60);
		contentPane.add(btnAdd);
		
		managerDAO.closeConn();
	}

	class AddListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			ManagerDAO managerDAO = new ManagerDAO();
			managerDAO.addDate();
			lblCurrentDate_DB.setText(managerDAO.getDate(1).toString());
			managerDAO.closeConn();
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			setVisible(false);
		}
	}
}