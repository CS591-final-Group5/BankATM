# BankATM

CAS CS591 P1: Object Oriented Software Principles and Design (in Java)

### Group #5:
-  Bofeng Liu <bofeng96@bu.edu>
-  Abhinav Bobba <abb12007@bu.edu>
-  Bryant Liriano <bliriano@bu.edu>


### Compile and Run
	cd src
	If you are using JDK 11 or higher:  
	-  javac Main.java  
	-  java -cp .:"../lib/mysql-connector-java-8.0.19.jar"  Main  

### Databases
	**mysql 5.7**  
	We interact Java with MySQL using JDBC  
	The connector is attached in /lib. (https://dev.mysql.com/downloads/connector/j/)  

### Class Design

-  Main

package: GUIBankATMCommon
-  Accounts
    -  CheckingAccounts
    -  SavingsAccounts
    -  SecuritiesAccounts
-  Collaterals
-  ConvertCurrency: Interface
-  Currencies
    -  CurrencyCNY
    -  CurrencyRUB
    -  CurrencyUSD
-  Loans
-  StockDeal
-  Stocks
-  Transactions
-  Users
    -  UserBankManager
    -  UserCustomers

package: GUIBankATMDAO
-  Database: base class
    -  AccountDAO
    -  CollateralDAO
    -  ManagerDAO
    -  StockDAO
    -  TransactionsDAO

package: GUIBankATMGUI  
-  GUIHomepage
-  GUIUserRegister
-  GUIUserLogin
    -  GUIUserBankManagerLogin
    -  GUIUserCustomersLogin
-  GUIInternalWindow: base class
    -  GUICustomerInterface
    -  GUIManagerInterface
    -  GUIManagerCheckUp
    -  GUIChangeBankATMPassword
    -  GUIChangeCardsPassword
    -  GUIChangeManagerPassword
    -  GUIChangeDate: 
    -  GUIGetCurrentDate: 
    -  GUICheckProfit: manager
    -  GUIAddCollateral
    -  GUIDeleteCollateral
    -  GUICards (unfinished)
        -  GUIOpenAccounts
        -  GUICloseAccounts
        -  GUIOpenSecuritiesAccounts
    -  GUIDepositOrWithdrawal
        -  GUIDeposit
        -  GUIWithdrawal
        -  GUILoans
    -  GUIDailyReport
    -  GUIDisplayAllLoans
    -  GUIDisplayTransactions
    -  GUIDisplayAllTransactions
    -  GUIRequestLoans
    -  GUITransfer
    -  GUIWithdrawal
    -  GUIStockMarket  
    -  GUICreateStocks
    -  GUIModifyStocksPrice


### Tips
1. The bank manager's account can only be used in BankManagerLogin page. You can create a customer account that has the same username as the name manager's account.
2. Username and password can only contain digits or letters.
3. To avoiding losing money, please withdrawal all your balance/sell all your stocks before close a account.
4. If you have a securities account that could do stock business, the sum of balance of all your savings accounts should be no less than 2500$.
5. Transfer can only happen between savings account and securities account. And you can't violate tip_4. Meanwhile, only savings account that has no less than 5000 can transfer money to a securities account.
6. About charging fee and pay interest:
   -  open/close an account (charge 1$)
      - ex: If you open a new account, the default balance will be -1$
   -  checking transaction
      -   When you click on opntion "Transactions/Display transactions", the system charges you 1$. The system will select a account randomly and decrease its balance. If you haven't done any transaction, the system won't charge any fee.
   -  withdrawal (charge 1$)
      -   When you withdrawal 100$, you will only get 99$.
   -  charge 1$/day for accounts that once requested a loan
      -   No matter how much money and how many times you requested, the system only charges you 1$ per day.
   -  pay interrst: the system award 1$/day on savings accounts that has more than 5000$