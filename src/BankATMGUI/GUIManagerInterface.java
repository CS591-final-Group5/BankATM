package BankATMGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class GUIManagerInterface extends JFrame {

	private String username;
	private JPanel contentPane;
	private final JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Create the frame.
	 */
	public GUIManagerInterface(String username) {
		this.username = username;
		setTitle("Customer Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1012, 45);
		contentPane.add(menuBar);
		
		JMenu mnSetting = new JMenu("Setting");
		mnSetting.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Setting.png")));
		menuBar.add(mnSetting);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		ChangePasswordListener cpl = new ChangePasswordListener();
		mntmChangePassword.addActionListener(cpl);
		mntmChangePassword.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/ChangePassword.png")));
		mnSetting.add(mntmChangePassword);
		
		JMenuItem mntmLogout = new JMenuItem("Logout (Return to homepage)");
		LogoutListener rl = new LogoutListener();
		mntmLogout.addActionListener(rl);
		mntmLogout.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Return.png")));
		mnSetting.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		ExitListener el = new ExitListener();
		mntmExit.addActionListener(el);
		mntmExit.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Exit.png")));
		mnSetting.add(mntmExit);
		
		JMenu mnDate = new JMenu("Date");
		mnDate.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Date.png")));
		menuBar.add(mnDate);
		
		JMenuItem mntmChangeDate = new JMenuItem("Change date");
		ChangeDateListener cdl = new ChangeDateListener();
		mntmChangeDate.addActionListener(cdl);
		mntmChangeDate.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/ModifyDate.png")));
		mnDate.add(mntmChangeDate);
		
		JMenu mnTransactions = new JMenu("Transactions");
		mnTransactions.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Transactions.png")));
		menuBar.add(mnTransactions);
		
		JMenuItem mntmDisplayTransactions = new JMenuItem("Display all transactions");
		DisplayTransactionListener dtl = new DisplayTransactionListener();
		mntmDisplayTransactions.addActionListener(dtl);
		mntmDisplayTransactions.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/DisplayTransactions.png")));
		mnTransactions.add(mntmDisplayTransactions);
		
		JMenuItem mntmCheckUp = new JMenuItem("Check up");
		CheckUpListener cul = new CheckUpListener();
		mntmCheckUp.addActionListener(cul);
		mntmCheckUp.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/CheckUp.png")));
		mnTransactions.add(mntmCheckUp);
		
		JMenu mnStockMarket = new JMenu("StockMarket");
		mnStockMarket.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/StockMarket.png")));
		menuBar.add(mnStockMarket);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open stock market");
		mntmNewMenuItem.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/OpenStockMarket.png")));
		mnStockMarket.add(mntmNewMenuItem);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Help.png")));
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("About us");
		mntmHelp.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/AboutMe.png")));
		AboutUsListener aul = new AboutUsListener();
		mntmHelp.addActionListener(aul);
		mnHelp.add(mntmHelp);
		desktopPane.setBounds(0, 56, 1012, 666);
		contentPane.add(desktopPane);
		
		setLocationRelativeTo(null);
	}
	
	class CheckUpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIManagerCheckUp frame = new GUIManagerCheckUp();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ChangeDateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIChangeDate frame = new GUIChangeDate();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class DisplayTransactionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIDisplayAllTransactions frame = new GUIDisplayAllTransactions();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ChangePasswordListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIChangeManagerPassword frame = new GUIChangeManagerPassword(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class LogoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				setVisible(false);
				GUIHomepage frame = new GUIHomepage();
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	class AboutUsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Bofeng Liu,    bofeng96@bu.edu", 
					"About us", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
