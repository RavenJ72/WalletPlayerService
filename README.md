# This project
Ylabs Internship Selection Task  
This is the first assignment as part of the Ylabs internship selection process.  
It involves creating a simple console application that uses collections for data storage.  
The application follows an onion architecture.  

### Project Overview
Type: Console Application  
Architecture: Onion Architecture  
Dependencies: None (No DI containers used)  
Data Storage: In-memory repositories  

### Functionality
Authorization: Allows users to log in to their accounts.  
Registration: Enables users to create new accounts.  
Check Balance: Displays the current account balance for a player.  
Debit (Withdraw Funds): Allows players to withdraw money from their accounts.  
Credit (Deposit Funds): Permits players to deposit money into their accounts.  
Transaction History: Provides an option to view the history of fund deposits and withdrawals for a player.  
User Interface: The application's user interface is a console.  

### Requirements
Layer Separation: Each application layer is organized into separate packages.  
Input and Output Packages: Contains packages 'in' (for handling input data) and 'out' (for handling output data).  
Unit Testing: Unit tests are implemented, and Mockito is used for mocking behavior in other layers. AssertJ is utilized for checking.  
Self-Documented Code: The code is well-documented with clear method names.  
Javadoc: Javadoc is provided for documentation.  
Build and Run: The project's README.MD includes instructions on how to build and run the project.  
Build Tools: Build tools are used for the project.  
This assignment aims to assess your ability to design and build a simple console application with a clean architecture and well-documented code.  
It involves creating different layers, handling input and output, and providing user-friendly functionality for managing player accounts.  

### Note to Reviewers
It is a simple but functional console application. 
When the system is launched, an 'admin' account is automatically created.  
Login: ADMIN  
Password: ADMIN  


## Building and running

App is build using maven. Simple type in root folder
```
mvn install
```

It will download npm to get angular and other web dependencies. Standard maven clean won't clean frontend dependencies so if you wont to clean them as well simply run clean with profile 'node_clean' like this
```
mvn clean -Pnode_clean
```
To run console app also just run generated jar, like
```
 java -jar in/target/in-1.0-SNAPSHOT-MAIN-jar-with-dependencies.jar
```
