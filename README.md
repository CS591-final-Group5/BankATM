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
-  GUIChangePassword
    -  GUIChangeBankSystemPassword
    -  GUIChangeCardsPassword
-  GUI****
-  GUIDailyReport
-  GUIStockMarket  


### Tips
The bank manager's account can only be used in BankManagerLogin page. You can create a customer account that has the same username as the name manager's account.

