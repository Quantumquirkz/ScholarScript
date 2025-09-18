# Banking System Project

## Project Overview
A comprehensive banking system application that manages bank accounts, transactions, and customer information. The system demonstrates object-oriented programming principles, exception handling, and file I/O operations in Java.

## Project Specifications

### Core Features
- Bank account management (create, update, delete accounts)
- Transaction processing (deposit, withdrawal, transfer)
- Customer management system
- Account balance inquiry and transaction history
- Data persistence using file I/O
- Exception handling for invalid operations
- User authentication and authorization

### Technical Requirements
- **Language**: Java 11 or higher
- **Dependencies**: Standard Java libraries only
- **Platforms**: Linux, Windows, macOS
- **Data Storage**: Text files (CSV format)
- **Build Tool**: Maven or Gradle (optional)

## Project Structure
```
banking-system/
├── src/main/java/
│   ├── banking/
│   │   ├── model/
│   │   │   ├── Account.java
│   │   │   ├── Customer.java
│   │   │   ├── Transaction.java
│   │   │   └── Bank.java
│   │   ├── service/
│   │   │   ├── AccountService.java
│   │   │   ├── TransactionService.java
│   │   │   └── CustomerService.java
│   │   ├── exception/
│   │   │   ├── InsufficientFundsException.java
│   │   │   ├── InvalidAccountException.java
│   │   │   └── BankingException.java
│   │   ├── util/
│   │   │   ├── FileManager.java
│   │   │   └── Validator.java
│   │   └── BankingApplication.java
├── src/main/resources/
│   ├── accounts.csv
│   ├── customers.csv
│   └── transactions.csv
├── pom.xml
└── README.md
```

## Detailed Specifications

### 1. Model Classes

#### Account Class
```java
package banking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String customerId;
    private AccountType accountType;
    private double balance;
    private LocalDateTime createdDate;
    private List<Transaction> transactions;
    
    public enum AccountType {
        CHECKING, SAVINGS, BUSINESS
    }
    
    // Constructors, getters, setters, and methods
    public Account(String accountNumber, String customerId, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = 0.0;
        this.createdDate = LocalDateTime.now();
        this.transactions = new ArrayList<>();
    }
    
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        this.balance += amount;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        this.balance -= amount;
    }
    
    // Additional methods for transaction history, balance inquiry, etc.
}
```

#### Customer Class
```java
package banking.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private List<String> accountNumbers;
    
    // Constructors, getters, setters, and methods
    public Customer(String customerId, String firstName, String lastName, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountNumbers = new ArrayList<>();
    }
    
    public void addAccount(String accountNumber) {
        if (!accountNumbers.contains(accountNumber)) {
            accountNumbers.add(accountNumber);
        }
    }
    
    public void removeAccount(String accountNumber) {
        accountNumbers.remove(accountNumber);
    }
    
    // Additional methods for customer management
}
```

#### Transaction Class
```java
package banking.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime timestamp;
    private String description;
    private double balanceAfter;
    
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }
    
    // Constructors, getters, setters
    public Transaction(String accountNumber, TransactionType transactionType, 
                      double amount, String description, double balanceAfter) {
        this.transactionId = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.balanceAfter = balanceAfter;
    }
    
    // Additional methods for transaction formatting and validation
}
```

### 2. Service Classes

#### AccountService Class
```java
package banking.service;

import banking.model.Account;
import banking.model.Customer;
import banking.exception.InvalidAccountException;
import banking.exception.InsufficientFundsException;
import banking.util.FileManager;
import java.util.List;
import java.util.Optional;

public class AccountService {
    private FileManager fileManager;
    private List<Account> accounts;
    
    public AccountService() {
        this.fileManager = new FileManager();
        this.accounts = loadAccounts();
    }
    
    public Account createAccount(String customerId, Account.AccountType accountType) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, customerId, accountType);
        accounts.add(account);
        saveAccounts();
        return account;
    }
    
    public Account getAccount(String accountNumber) throws InvalidAccountException {
        Optional<Account> account = accounts.stream()
            .filter(a -> a.getAccountNumber().equals(accountNumber))
            .findFirst();
        
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new InvalidAccountException("Account not found: " + accountNumber);
        }
    }
    
    public void deleteAccount(String accountNumber) throws InvalidAccountException {
        Account account = getAccount(accountNumber);
        if (account.getBalance() > 0) {
            throw new InvalidAccountException("Cannot delete account with remaining balance");
        }
        accounts.remove(account);
        saveAccounts();
    }
    
    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis();
    }
    
    private List<Account> loadAccounts() {
        // Implementation to load accounts from file
        return fileManager.loadAccounts();
    }
    
    private void saveAccounts() {
        // Implementation to save accounts to file
        fileManager.saveAccounts(accounts);
    }
}
```

#### TransactionService Class
```java
package banking.service;

import banking.model.Account;
import banking.model.Transaction;
import banking.model.Transaction.TransactionType;
import banking.exception.InsufficientFundsException;
import banking.exception.InvalidAccountException;
import banking.util.FileManager;
import java.util.List;

public class TransactionService {
    private AccountService accountService;
    private FileManager fileManager;
    private List<Transaction> transactions;
    
    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
        this.fileManager = new FileManager();
        this.transactions = loadTransactions();
    }
    
    public Transaction deposit(String accountNumber, double amount, String description) 
            throws InvalidAccountException {
        Account account = accountService.getAccount(accountNumber);
        account.deposit(amount);
        
        Transaction transaction = new Transaction(accountNumber, TransactionType.DEPOSIT, 
                                                amount, description, account.getBalance());
        transactions.add(transaction);
        
        saveTransactions();
        return transaction;
    }
    
    public Transaction withdraw(String accountNumber, double amount, String description) 
            throws InvalidAccountException, InsufficientFundsException {
        Account account = accountService.getAccount(accountNumber);
        account.withdraw(amount);
        
        Transaction transaction = new Transaction(accountNumber, TransactionType.WITHDRAWAL, 
                                                amount, description, account.getBalance());
        transactions.add(transaction);
        
        saveTransactions();
        return transaction;
    }
    
    public void transfer(String fromAccountNumber, String toAccountNumber, 
                        double amount, String description) 
            throws InvalidAccountException, InsufficientFundsException {
        Account fromAccount = accountService.getAccount(fromAccountNumber);
        Account toAccount = accountService.getAccount(toAccountNumber);
        
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        
        Transaction withdrawTransaction = new Transaction(fromAccountNumber, 
            TransactionType.TRANSFER_OUT, amount, description, fromAccount.getBalance());
        Transaction depositTransaction = new Transaction(toAccountNumber, 
            TransactionType.TRANSFER_IN, amount, description, toAccount.getBalance());
        
        transactions.add(withdrawTransaction);
        transactions.add(depositTransaction);
        
        saveTransactions();
    }
    
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactions.stream()
            .filter(t -> t.getAccountNumber().equals(accountNumber))
            .collect(Collectors.toList());
    }
    
    private List<Transaction> loadTransactions() {
        return fileManager.loadTransactions();
    }
    
    private void saveTransactions() {
        fileManager.saveTransactions(transactions);
    }
}
```

### 3. Exception Classes

#### Custom Exceptions
```java
package banking.exception;

public class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
    
    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class InsufficientFundsException extends BankingException {
    private double requestedAmount;
    private double availableAmount;
    
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, double requestedAmount, double availableAmount) {
        super(message);
        this.requestedAmount = requestedAmount;
        this.availableAmount = availableAmount;
    }
    
    // Getters for requestedAmount and availableAmount
}

public class InvalidAccountException extends BankingException {
    private String accountNumber;
    
    public InvalidAccountException(String message) {
        super(message);
    }
    
    public InvalidAccountException(String message, String accountNumber) {
        super(message);
        this.accountNumber = accountNumber;
    }
    
    // Getter for accountNumber
}

public class InvalidAmountException extends BankingException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
```

## User Interface Design

### Console Menu System
```java
public class BankingApplication {
    private Scanner scanner;
    private AccountService accountService;
    private TransactionService transactionService;
    private CustomerService customerService;
    
    public static void main(String[] args) {
        BankingApplication app = new BankingApplication();
        app.run();
    }
    
    public void run() {
        scanner = new Scanner(System.in);
        initializeServices();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    createCustomer();
                    break;
                case 8:
                    viewAccountDetails();
                    break;
                case 9:
                    running = false;
                    System.out.println("Thank you for using the Banking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n=== Banking System ===");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. Transaction History");
        System.out.println("7. Create Customer");
        System.out.println("8. Account Details");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
}
```

## Data Persistence

### File Manager Implementation
```java
package banking.util;

import banking.model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {
    private static final String ACCOUNTS_FILE = "src/main/resources/accounts.csv";
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.csv";
    private static final String TRANSACTIONS_FILE = "src/main/resources/transactions.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    Account account = new Account(data[0], data[1], 
                        Account.AccountType.valueOf(data[2]));
                    account.setBalance(Double.parseDouble(data[3]));
                    account.setCreatedDate(LocalDateTime.parse(data[4], DATE_FORMATTER));
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
        }
        
        return accounts;
    }
    
    public void saveAccounts(List<Account> accounts) {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(ACCOUNTS_FILE)))) {
            for (Account account : accounts) {
                writer.println(String.format("%s,%s,%s,%.2f,%s",
                    account.getAccountNumber(),
                    account.getCustomerId(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getCreatedDate().format(DATE_FORMATTER)
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }
    
    // Similar methods for customers and transactions
}
```

## Cross-Platform Considerations

### Build Configuration (Maven)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.banking</groupId>
    <artifactId>banking-system</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Compilation Commands
```bash
# Linux/macOS
mvn compile
mvn exec:java -Dexec.mainClass="banking.BankingApplication"

# Windows (Command Prompt)
mvn compile
mvn exec:java -Dexec.mainClass="banking.BankingApplication"

# Windows (PowerShell)
mvn compile
mvn exec:java -Dexec.mainClass="banking.BankingApplication"
```

## Testing Requirements

### Unit Tests
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Account account;
    
    @BeforeEach
    void setUp() {
        account = new Account("ACC123", "CUST001", Account.AccountType.CHECKING);
    }
    
    @Test
    void testDeposit() throws InvalidAmountException {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }
    
    @Test
    void testWithdraw() throws InsufficientFundsException, InvalidAmountException {
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }
    
    @Test
    void testInsufficientFundsException() {
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(100.0);
        });
    }
}
```

## Performance Requirements
- Support for up to 10,000 accounts
- Transaction processing in < 1 second
- File I/O operations complete in < 2 seconds
- Memory usage < 100MB for maximum dataset

## Security Considerations
- Input validation for all user inputs
- Secure file handling and path validation
- Transaction logging for audit trails
- Error handling without exposing sensitive information

## Extension Ideas
- Database integration (MySQL, PostgreSQL)
- GUI interface (JavaFX, Swing)
- Web-based interface (Spring Boot)
- REST API for external integrations
- Encryption for sensitive data
- Multi-currency support
- Interest calculation for savings accounts

## Deliverables
1. Complete source code with proper package structure
2. Maven/Gradle build configuration
3. Unit tests with JUnit
4. Documentation (README, JavaDoc)
5. Sample data files
6. Build and run scripts for all platforms

## Success Criteria
- All banking operations work correctly
- Exception handling prevents system crashes
- Data persists between application sessions
- Code follows Java best practices and conventions
- Unit tests achieve 90% code coverage
- Application compiles and runs on all target platforms
