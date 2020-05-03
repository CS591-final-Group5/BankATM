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

Interface:  
-  ConvertCurrency
-  OpenOrCloseAccount
-  DailyReport
-  TradeStocks
-  RequestLoans
-  

BankATMSystem:  
-  ***

Transactions  

Users:  
-  UserBankManager
-  UserCustomers  

Accounts  
-  AccountsChecking
-  AccountsSavings  

Currencies  
-  CurrencyUSD
-  CurrencyCNY
-  CurrencyRUB  

Loans  

GUIBankATM  
-  GUIHomepage
-  GUIUserRegister
-  GUIUserLogin
    -  GUIUserBankManagerLogin
    -  GUIUserCustomersLogin
-  GUIInternalWindow
    -  GUIChangeBankSystemPassword
    -  GUIChangeCardsPassword
    -  GUIAddCollateral
    -  GUIDeleteCollateral
    -  GUICards (unfinished)
        -  GUIOpenAccounts
        -  GUICloseAccounts
        -  GUI
    -  GUIDepositOrWithdrawal
        -  GUIDeposit
        -  GUIWithdrawal
        -  GUILoans
    -  GUIDailyReport
    -  GUIStockMarket  


### Tips
1. The bank manager's account can only be used in BankManagerLogin page. You can create a customer account that has the same username as the name manager's account.
2. Username and password can only contain digits or letters.
3. To avoiding losing money, please withdrawal all your balance/sell all your stocks before close a account.
4. If you have a securities account that could do stock business, the sum of balance of all your savings accounts should be no less than 2500$.
5. Transfer can only happen between savings account and securities account. And you can't violate tip_4. Meanwhile, only savings account that has no less than 5000 can transfer money to a securities account.
6. About charging fee:
   -  open/close an account (1$)
   -  checking transaction
      -   When you click on opntion "Transactions/Display transactions", the system charges you 1$.
   -  withdrawal (1$)
7. About interest
   -  charge 1$/day for accounts that once requested a loan
      -   No matter how much money and how many times you requested, the system only charges you 1$ per day.
   -  the system award 1$/day on savings accounts that has more than 5000$