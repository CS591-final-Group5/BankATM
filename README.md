# BankATM

CAS CS591 P1: Object Oriented Software Principles and Design (in Java)

### Group #5:
	Bofeng Liu <bofeng96@bu.edu> BU ID: U47945506
	Abhinav Bobba <abb12007@bu.edu> BU ID: U65749603
	Bryant Liriano <bliriano@bu.edu> BU ID: U24224015


### Compile and Run
	cd src
	If you are using JDK 11 or higher:  
	-  javac Main.java  
	-  java -cp .:"../lib/mysql-connector-java-8.0.19.jar"  Main  

### Databases
	**mysql 5.7**  
	We interact Java with MySQL using JDBC  
	The connector is attached in /lib. (https://dev.mysql.com/downloads/connector/j/)  
	mysql root password: 123456

### Class Design

-  Main

package: GUIBankATMCommon
-  Accounts:
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

### Class design details:
BankATMCommon Classes
- Accounts - This class is the superclass for Checking, Savings and Security Accounts. Creates the basic information shared by the sub-class as well as setters and getters associated with the Accounts. 
- Checking Accounts - This is a sub-class of Accounts, it has the minimum restriction applied to it.
- Collateral - Creates the Collateral object that contains the information of what a user might use for finincial purposes
- Convert Currency- It is a interface that holds the conversion rate from USD to both RUB and CNY.
- Currencies - Super-class to the other currency classes. Contains the basic information such as amount as well as the convert function that it gains from the interface ConvertCurrency
- Currency CNY - Sub-class of Currencies specified to take in the properties of CNY. Automatically converts from USD.
- Currency RUB - Sub-class of Currencies specified to take in the properties of RUB. Automatically converts from USD.
- Currency USD - Sub-class of Currencies Specified to take in the properites of USD. This is the main currency class utilized by BankAtm. Has the ability to be converted from CNY and RUB.
- Loans - Creates the Loan object that holds the amount of loan and the account number associated with it. This also hass getters and setters for the parameters. 
- SavingsAccount - Sub-class of the Account class. This has a minimum restriction of $2500 to create.
- SecuritiesAccount - Sub-Class of the Account Class. Maintains the same basic properties of Account.
- StockDeal -Creates a stock deal object that has the value of a stock and its current bidding price along with the basic information pertaining to it. 
- Stocks - Creates a Stock object that includesd the name,amount and price of a given Stock. It includes setter and getter for the information pertaining to it.
- Transactions - Creates an instance of when a transaction has been made including the type. Information includes username, id ,account number, time and additional detais that are relevent to the action.
- UserBankManager - A Sub-class of Users, it creates the instance of the manager as well as the basic information associated with it.
- UserCustomers - A Sub-class of Users, creates an profile for the user. It focuses also on maintaning an arrayList of Accounts that are associated with a specific User. 
- Users - The Super- Class of the BankManagers and Users. This creates an instance of any user with basic information such as username, password and Email. 

BankATMDAO Classes
- AccountDAO - Maintains the information pertaining to the Users. This includes retreiving information, applying fees, transactions and creating accounts. 
- CollateralDAO - Creates and Maintains information pertaining to Collaterals, This includes adding, removing or retrieving information on collaterals.
- Database - Creates the Database that is utilized by the program. Using MySQL it creates tables for users,accounts,transactions, collaterals, managers,loans,stocks, and stockdeals. 
- ManagerDAO - Creates a way to store information that a manager would use. This includes charging fees, privacy protection and getting information that is associated with the bank.
- StockDAO - Maintains the information that is associated with Stocks. This includes when a stock is created, when it is updated and when it is bought in a deal.
- TransactionDAO - Maintains the infrmation about transactions that have ocurred. 

BankATMGUI Classes
- GUIAddCollateral - Creates a user visible way for the customer to add a collateral to their account.
- GUIBankManagerLogin - Creates a user-visible frame for bank manager to input their information to access their account
- GUIChangeBankATMPassword - Displays to the user a way to change their password in their accounts.
- GUIChangeCardsPassword -  Displays to the user how to change their passwords on their card. 
- GUIChangeDate - Creates a user-visible way to update dates for users.
- GUIChangeManagerPassword -  Creates a display that allows manager's to change their password
- GUICheckProfit - Creates a visible way to check profits that have been generated by the bank.
- GUICloseAccounts - Creates a visible way to close accounts on various user accounts.
- GUICreateStocks - Creates a visible way to create stock and input its information
- GUICustomerInterface - Creates a visible menu that the Customer can use to navigate throughout the bank software.
- GUICustomerLogin - Creates a visible way for the customer to login and access their account.
- GUIDeleteCollateral - Creates a visible way to Delete Collaterals from an account
- GUIDeposit - Creates a visible way to Deposit money into accounts. 
- GUIDepositOrWithdrawal - Creates a visible way to convert currency in order to deposit or withdraw money
- GUIDisplayAllLoans - Creates a visible way to view all loans that are currnently available
- GUIDisplayAllTransactions - Creates a visible way to view all transactions that have occurred.
- GUIDisplayTransactions - Creates a visible way to view certain transactions.
- GUIGetCurrentDate - Creates a visible way for the user to retrieve the current date of certain things.
- GUIHomePage - Creates a homepage that the user returns to after an action is complete allowing them to choose what actions they wish to do. 
- GUIInternalWindow - Creates the frame that is used by other classes. Also contains the actions Listener for the user's accessibility. 
- GUIManagerCheckup - Creates a visible way for a manager to check up on customers and see the information that is relevant ot the customer.
- GUIManagerInterface - Creates a visible way for a manager to update their security information as well as display information that occurs in the bank such as loans,transactions and profits.
- GUIModifyStocksPrice - Creates a visible way to modify Stock prices.
- GUIOpenAcoounts - Creates a visible way to open accounts for the user.
- GUIOpenSecuritiesAccounts - Creates a visible way to open Security accounts for the user.
- GUIRequestLoans - Creates a visible way for users to request loans from the bank depending if they are eligible.
- GUIStockMarket - Creates a visible way to view the Stock market.
- GUITransfer - Creates a visible way for users to transfer money between accounts. This includes money that is in different currencies as well as the security measures required for the transactions to occur.
- GUIUserRegister - Creates a visible way for a user to create a customer account. This includes displays for things like name, email and password.
- GUIWithdrawal -Creates a visible way for Users to withdraw money from their accounts.


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