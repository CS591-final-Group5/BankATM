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

public class GUICustomersInterface extends JFrame {

	private String username;
	private JPanel contentPane;
	private final JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Create the frame.
	 */
	public GUICustomersInterface(String username) {
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
		mnSetting.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Setting.png")));
		menuBar.add(mnSetting);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		ChangePasswordListener cpl = new ChangePasswordListener();
		mntmChangePassword.addActionListener(cpl);
		mntmChangePassword.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/ChangePassword.png")));
		mnSetting.add(mntmChangePassword);
		
		JMenuItem mntmLogout = new JMenuItem("Logout (Return to homepage)");
		LogoutListener rl = new LogoutListener();
		mntmLogout.addActionListener(rl);
		mntmLogout.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Return.png")));
		mnSetting.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		ExitListener el = new ExitListener();
		mntmExit.addActionListener(el);
		mntmExit.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Exit.png")));
		mnSetting.add(mntmExit);
		
		JMenu mnCollaterals = new JMenu("Collaterals");
		mnCollaterals.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Collaterals.png")));
		menuBar.add(mnCollaterals);
		
		JMenuItem mntmAdd = new JMenuItem("Add a collateral");
		mntmAdd.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Add.png")));
		AddListener al = new AddListener();
		mntmAdd.addActionListener(al);
		mnCollaterals.add(mntmAdd);
		
		JMenuItem mntmDelete = new JMenuItem("Delete collaterals");
		DeleteListener dl = new DeleteListener();
		mntmDelete.addActionListener(dl);
		mntmDelete.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Delete.png")));
		mnCollaterals.add(mntmDelete);
		
		JMenu mnCards = new JMenu("Cards");
		mnCards.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Cards.png")));
		menuBar.add(mnCards);
		
		JMenuItem mntmOpenAccounts = new JMenuItem("Open a account");
		OpenAccountsListener oal = new OpenAccountsListener();
		mntmOpenAccounts.addActionListener(oal);
		mntmOpenAccounts.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/SavingsAccount.png")));
		mnCards.add(mntmOpenAccounts);
		
		JMenuItem mntmCloseAccounts = new JMenuItem("Close accounts");
		CloseAccountsListener cal = new CloseAccountsListener();
		mntmCloseAccounts.addActionListener(cal);
		mntmCloseAccounts.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/CheckingAccount .png")));
		mnCards.add(mntmCloseAccounts);
		
		JMenu mnStockMarket = new JMenu("StockMarket");
		mnStockMarket.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/StockMarket.png")));
		menuBar.add(mnStockMarket);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open stock market");
		mnStockMarket.add(mntmNewMenuItem);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("About us");
		AboutUsListener aul = new AboutUsListener();
		mntmHelp.addActionListener(aul);
		mnHelp.add(mntmHelp);
		desktopPane.setBounds(0, 56, 1012, 666);
		contentPane.add(desktopPane);
		
		setLocationRelativeTo(null);
	}
	
	class OpenAccountsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIOpenAccounts frame = new GUIOpenAccounts(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class CloseAccountsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUICloseAccounts frame = new GUICloseAccounts(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				// close all sub-windows inside current window 
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIAddCollateral frame = new GUIAddCollateral(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIDeleteCollateral frame = new GUIDeleteCollateral(username);
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
				GUIChangeBankATMPassword frame = new GUIChangeBankATMPassword(username);
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
