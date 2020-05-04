package BankATMGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BankATMDAO.*;

public class GUICheckProfit extends GUIInternalWindow {

	private JPanel contentPane;
	private JButton btnChange;
	private JLabel lblAmount;
	
	/**
	 * Create the frame.
	 */
	public GUICheckProfit() {
		super();
		setBounds(100, 100, 400, 400);
		this.setTitle("Change date");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblHeadline = new JLabel("<html>\r\nThe money you have earned.\r\n</html>");
		lblHeadline.setForeground(new Color(255, 0, 0));
		lblHeadline.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblHeadline.setBounds(41, 11, 363, 66);
		contentPane.add(lblHeadline);
		
		JButton btnClose = new JButton("Close");
		CloseListener bl = new CloseListener();
		btnClose.addActionListener(bl);
		btnClose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		btnClose.setBounds(55, 265, 270, 60);
		contentPane.add(btnClose);

		ManagerDAO managerDAO = new ManagerDAO();
		
		lblAmount = new JLabel(String.valueOf(managerDAO.getProfit()) + "$");
		lblAmount.setFont(new Font("Consolas", Font.PLAIN, 25));
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setBounds(55, 112, 270, 109);
		contentPane.add(lblAmount);

		managerDAO.closeConn();
	}
	
}