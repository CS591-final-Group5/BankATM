package BankATMGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
		
		JMenuItem OpenSecuritiesAccount = new JMenuItem("Open securities account");
		OpenSecuritiesAccountsListener osal = new OpenSecuritiesAccountsListener();
		OpenSecuritiesAccount.addActionListener(osal);
		OpenSecuritiesAccount.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/SecuritiesAccount.png")));
		mnCards.add(OpenSecuritiesAccount);
		
		JMenuItem mntmChangeCardsPassword = new JMenuItem("Change password of a card");
		mntmChangeCardsPassword.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/ChangeCardsPassword.png")));
		mnCards.add(mntmChangeCardsPassword);
		TransferListener tfl = new TransferListener();
		
		JMenu mnTransactions = new JMenu("Transactions");
		mnTransactions.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Transactions.png")));
		menuBar.add(mnTransactions);
		
		JMenuItem mntmDeposit = new JMenuItem("Deposit");
		DepositListener dpl = new DepositListener();
		mntmDeposit.addActionListener(dpl);
		
		JMenuItem mntmTransfer = new JMenuItem("Transfer");
		mnTransactions.add(mntmTransfer);
		mntmTransfer.addActionListener(tfl);
		mntmTransfer.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Transfer.png")));
		mntmDeposit.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Deposit.png")));
		mnTransactions.add(mntmDeposit);
		
		JMenuItem mntmWithdrawal = new JMenuItem("Withdrawal");
		WithdrawalListener wdl = new WithdrawalListener();
		mntmWithdrawal.addActionListener(wdl);
		mntmWithdrawal.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Withdrawal.png")));
		mnTransactions.add(mntmWithdrawal);
		
		JMenuItem mntmLoan = new JMenuItem("Loan");
		RequestLoanListener rll = new RequestLoanListener();
		mntmLoan.addActionListener(rll);
		mntmLoan.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Loans.png")));
		mnTransactions.add(mntmLoan);
		
		JMenuItem mntmDisplayTransactions = new JMenuItem("Display Transactions");
		DisplayTransactionListener dtl = new DisplayTransactionListener();
		mntmDisplayTransactions.addActionListener(dtl);
		mntmDisplayTransactions.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/DisplayTransactions.png")));
		mnTransactions.add(mntmDisplayTransactions);
		
		JMenu mnStockMarket = new JMenu("StockMarket");
		mnStockMarket.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/StockMarket.png")));
		menuBar.add(mnStockMarket);
		
		JMenuItem mntmOpenStockMarket = new JMenuItem("Open stock market");
		OpenStockMarketListener osml = new OpenStockMarketListener();
		mntmOpenStockMarket.addActionListener(osml);
		mntmOpenStockMarket.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/OpenStockMarket.png")));
		mnStockMarket.add(mntmOpenStockMarket);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/Help.png")));
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("About us");
		mntmHelp.setIcon(new ImageIcon(GUICustomersInterface.class.getResource("/Icons/AboutMe.png")));
		AboutUsListener aul = new AboutUsListener();
		mntmHelp.addActionListener(aul);
		mnHelp.add(mntmHelp);
		desktopPane.setBounds(0, 56, 1012, 666);
		contentPane.add(desktopPane);
		
		setLocationRelativeTo(null);
	}
	
	class OpenStockMarketListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIStockMarket frame = new GUIStockMarket(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class RequestLoanListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUILoans frame = new GUILoans(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class TransferListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUITransfer frame = new GUITransfer(username);
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
				GUIDisplayTransactions frame = new GUIDisplayTransactions(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class OpenSecuritiesAccountsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIOpenSecuritiesAccounts frame = new GUIOpenSecuritiesAccounts(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
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
	
	class DepositListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIDeposit frame = new GUIDeposit(username);
				frame.setVisible(true);
				desktopPane.add(frame);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class WithdrawalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				JInternalFrame[] allJFrames = desktopPane.getAllFrames();
				for (JInternalFrame jf: allJFrames) {
					jf.setVisible(false);
				}
				GUIWithdrawal frame = new GUIWithdrawal(username);
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
