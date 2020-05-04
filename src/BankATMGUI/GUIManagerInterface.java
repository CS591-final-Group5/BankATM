package BankATMGUI;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import java.awt.event.*;

public class GUIManagerInterface extends JFrame {

	private String username;
	private JPanel contentPane;
	private final JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Create the frame.
	 */
	public GUIManagerInterface(String username) {
		this.username = username;
		setTitle("Manager Interface");
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
		
		JMenu mnProfit = new JMenu("Profit");
		mnProfit.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Profit.png")));
		menuBar.add(mnProfit);
		
		JMenuItem mntmCheckProfit = new JMenuItem("How many money you earn?");
		CheckProfitListener cptl = new CheckProfitListener();
		mntmCheckProfit.addActionListener(cptl);
		mntmCheckProfit.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/Earn.png")));
		mnProfit.add(mntmCheckProfit);
		
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
		
		JMenuItem mntmDisplayLoans = new JMenuItem("Display loans");
		DisplayLoansListener dll = new DisplayLoansListener();
		mntmDisplayLoans.addActionListener(dll);
		mntmDisplayLoans.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/DisplayLoans.png")));
		mnTransactions.add(mntmDisplayLoans);
		mntmCheckUp.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/CheckUp.png")));
		mnTransactions.add(mntmCheckUp);
		
		JMenu mnStockMarket = new JMenu("StockMarket");
		mnStockMarket.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/StockMarket.png")));
		menuBar.add(mnStockMarket);
		
		JMenuItem mntmCreateStocks = new JMenuItem("Create stocks");
		mntmCreateStocks.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/CreateStocks.png")));
		CreateStocksListener css = new CreateStocksListener();
		mntmCreateStocks.addActionListener(css);
		mnStockMarket.add(mntmCreateStocks);
		
		JMenuItem mntmModifyPrice = new JMenuItem("Modify Price");
		ModifyStocksListener msl = new ModifyStocksListener();
		mntmModifyPrice.addActionListener(msl);
		mntmModifyPrice.setIcon(new ImageIcon(GUIManagerInterface.class.getResource("/Icons/ModifyPrice.png")));
		mnStockMarket.add(mntmModifyPrice);
		
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
	
	class ModifyStocksListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIModifyStocksPrice frame = new GUIModifyStocksPrice();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class CreateStocksListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUICreateStocks frame = new GUICreateStocks();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class DisplayLoansListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIDisplayAllLoans frame = new GUIDisplayAllLoans();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class CheckProfitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUICheckProfit frame = new GUICheckProfit();
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
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
