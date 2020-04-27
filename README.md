# BankATM

CAS CS591 P1: Object Oriented Software Principles and Design (in Java)

### Group #5:
-  Bofeng Liu <bofeng96@bu.edu>
-  Abhinav Bobba <abb12007@bu.edu>
-  Bryant Liriano <bliriano@bu.edu>


### Compile and Run

cd src

If you are using JDK 11 or higher:  

-  javac --module-path "../lib/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml Main.java  
-  java --module-path "../lib/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml -cp .:"../lib/mysql-connector-java-8.0.19.jar"  Main  

### Databases
**mysql 5.7**  
We link Java with MySQL using JDBC  
A connector should be installed. (https://dev.mysql.com/downloads/connector/j/)  

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
    -  GUIUserBankManagerLogin.fxml
    -  GUIUserCustomersLogin.fxml
-  GUIUserProfile
    -  GUIUserBankManagerProfile
    -  GUIUserCustomersProfile
-  GUI****
-  GUIDailyReport
-  GUIStockMarket  

