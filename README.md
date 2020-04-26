# BankATM

CAS CS591 P1: Object Oriented Software Principles and Design (in Java)

**Group #5:**  
-  Bofeng Liu <bofeng96@bu.edu>
-  Abhinav Bobba <abb12007@bu.edu>
-  Bryant Liriano <bliriano@bu.edu>


**Compile and Run**

cd src

If you are using JDK 11 or higher:  
(JavaFX has been removed, so you need to add all libraries in /lib to Java build path)  

javac --module-path "/home/beven96/eclipse-workspace/BankATM/lib/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml Main.java  

java --module-path "/home/beven96/eclipse-workspace/BankATM/lib/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml Main  

Or you could:  

javac ./BankATMGUI/GUIHomepage.java  
java BankATMGUI.GUIHomepage  

**Class Design:**  

Interface:  
-  ConvertCurrency
-  OpenOrCloseAccount
-  DailyReport
-  TradeStocks
-  RequestLoans
-  

BankATM:  
-  ***

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
    -  GUIUserBankManagerRegister
    -  GUIUserCustomersRegister
-  GUIUserLogin
    -  GUIUserBankManagerLogin
    -  GUIUserCustomersLogin
-  GUIUserProfile
    -  GUIUserBankManagerProfile
    -  GUIUserCustomersProfile
-  GUI****
-  GUIDailyReport
-  GUIStockMarket  

Still not sure if we should seperate panel/frame/events/etc classes.  
But I'll come up with a rough GUI first.  
If there is a need, it should be easy to modify.  

