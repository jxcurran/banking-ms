# Banking Application
This is a simple banking application to:
* Send money between two existing accounts
* Request account balance and list of transactions 

# How to run

### In an IDE
* In your preferred IDE, one can execute the test and run the main class BankingMsApplication. 
  This will start a Spring Boot application on localhost:8080 and create a /data directory for the DB

### As a standalone JAR
* Taken from target/banking-ms-0.0.1-SNAPSHOT.jar
* java -jar banking-ms-0.0.1-SNAPSHOT.jar
* This will start a Spring Boot application on localhost:8080 and create a /data directory with a H2 db with some initial account data. 
* This can be viewed inside the H2 console but simpler would be to call the endpoints to view all data. 
* The initial data is created by /resources/data.sql

#### Endpoint lists
* /accounts - GET  - lists all accounts
* /transactions - GET lists all transactions
* /account-create - POST accepts RAW JSON - creates account
* /createTransaction - POST accepts RAW JSON - creates transaction with account details
* /account-transactions - POST accepts RAW JSON - lists accounts and their transactions
* Configuration for postman endpoints can be found in /banking-ms/postman. These can be imported as a postman collection and ran individually.