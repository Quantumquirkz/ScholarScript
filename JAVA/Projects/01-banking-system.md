# Banking System Project

## Project Overview
A comprehensive banking system application that demonstrates object-oriented programming principles, exception handling, and file I/O operations in Java. The system manages multiple bank accounts, transactions, and provides a secure banking environment.

## Project Specifications

### Core Features
- Account management (create, view, update, delete accounts)
- Transaction processing (deposit, withdraw, transfer)
- Interest calculation and compound interest
- Account statements and transaction history
- Data persistence using file I/O
- Input validation and exception handling
- Menu-driven user interface
- Multi-platform compatibility

### Technical Requirements
- **Language**: Java 11 or higher
- **Dependencies**: Standard Java libraries only
- **Platforms**: Linux, Windows, macOS
- **Build Tool**: Maven or Gradle (optional)
- **Data Storage**: File-based (CSV/JSON format)

## Project Structure
```
banking-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── banking/
│   │   │   │       ├── BankingSystem.java
│   │   │   │       ├── model/
│   │   │   │       │   ├── Account.java
│   │   │   │       │   ├── Transaction.java
│   │   │   │       │   ├── Customer.java
│   │   │   │       │   └── Bank.java
│   │   │   │       ├── service/
│   │   │   │       │   ├── AccountService.java
│   │   │   │       │   ├── TransactionService.java
│   │   │   │       │   └── DataService.java
│   │   │   │       ├── exception/
│   │   │   │       │   ├── InsufficientFundsException.java
│   │   │   │       │   ├── InvalidAccountException.java
│   │   │   │       │   └── BankingException.java
│   │   │   │       └── util/
│   │   │   │           ├── InputValidator.java
│   │   │   │           ├── DateUtil.java
│   │   │   │           └── FileManager.java
│   │   │   └── Main.java
│   │   └── resources/
│   │       └── accounts.txt
├── data/
│   ├── accounts.csv
│   └── transactions.csv
├── docs/
│   └── README.md
├── pom.xml (if using Maven)
└── build.gradle (if using Gradle)
```

## Detailed Specifications

### 1. Core Model Classes

#### Account.java
```java
package com.banking.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private AccountType accountType;
    private LocalDate createdDate;
    private boolean isActive;
    private List<Transaction> transactions;
    
    public enum AccountType {
        SAVINGS, CHECKING, BUSINESS
    }
    
    public Account(String accountNumber, String accountHolderName, 
                   AccountType accountType, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.createdDate = LocalDate.now();
        this.isActive = true;
        this.transactions = new ArrayList<>();
    }
    
    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public AccountType getAccountType() { return accountType; }
    public LocalDate getCreatedDate() { return createdDate; }
    public boolean isActive() { return isActive; }
    public List<Transaction> getTransactions() { return transactions; }
    
    public void setBalance(double balance) { this.balance = balance; }
    public void setActive(boolean active) { this.isActive = active; }
    
    // Business methods
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available: $" + balance);
        }
        balance -= amount;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    public double calculateInterest(double annualRate, int years) {
        return balance * Math.pow(1 + annualRate / 100, years) - balance;
    }
    
    public String getAccountSummary() {
        return String.format("Account: %s | Holder: %s | Type: %s | Balance: $%.2f | Status: %s",
                           accountNumber, accountHolderName, accountType, 
                           balance, isActive ? "Active" : "Inactive");
    }
    
    @Override
    public String toString() {
        return getAccountSummary();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return accountNumber.equals(account.accountNumber);
    }
    
    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}
```

#### Transaction.java
```java
package com.banking.model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String fromAccount;
    private String toAccount;
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;
    private String description;
    private TransactionStatus status;
    
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER, INTEREST
    }
    
    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
    
    public Transaction(String transactionId, String fromAccount, String toAccount,
                      TransactionType type, double amount, String description) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.status = TransactionStatus.PENDING;
    }
    
    // Getters and setters
    public String getTransactionId() { return transactionId; }
    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
    public TransactionStatus getStatus() { return status; }
    
    public void setStatus(TransactionStatus status) { this.status = status; }
    
    public String getTransactionSummary() {
        return String.format("ID: %s | Type: %s | Amount: $%.2f | Date: %s | Status: %s",
                           transactionId, type, amount, timestamp, status);
    }
    
    @Override
    public String toString() {
        return getTransactionSummary();
    }
}
```

#### Customer.java
```java
package com.banking.model;

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
    
    public Customer(String customerId, String firstName, String lastName, 
                   String email, String phoneNumber, LocalDate dateOfBirth, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.accountNumbers = new ArrayList<>();
    }
    
    // Getters and setters
    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getAddress() { return address; }
    public List<String> getAccountNumbers() { return accountNumbers; }
    
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    
    // Business methods
    public void addAccount(String accountNumber) {
        if (!accountNumbers.contains(accountNumber)) {
            accountNumbers.add(accountNumber);
        }
    }
    
    public void removeAccount(String accountNumber) {
        accountNumbers.remove(accountNumber);
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
    
    public String getCustomerSummary() {
        return String.format("Customer ID: %s | Name: %s | Email: %s | Accounts: %d",
                           customerId, getFullName(), email, accountNumbers.size());
    }
    
    @Override
    public String toString() {
        return getCustomerSummary();
    }
}
```

### 2. Service Classes

#### AccountService.java
```java
package com.banking.service;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAccountException;
import java.util.*;

public class AccountService {
    private Map<String, Account> accounts;
    private TransactionService transactionService;
    
    public AccountService() {
        this.accounts = new HashMap<>();
        this.transactionService = new TransactionService();
    }
    
    public void createAccount(String accountNumber, String accountHolderName, 
                            Account.AccountType accountType, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account number already exists");
        }
        
        Account account = new Account(accountNumber, accountHolderName, accountType, initialBalance);
        accounts.put(accountNumber, account);
        
        // Record initial deposit transaction
        if (initialBalance > 0) {
            Transaction transaction = new Transaction(
                generateTransactionId(), null, accountNumber,
                Transaction.TransactionType.DEPOSIT, initialBalance,
                "Initial deposit"
            );
            transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
            account.addTransaction(transaction);
        }
    }
    
    public Account getAccount(String accountNumber) throws InvalidAccountException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found: " + accountNumber);
        }
        return account;
    }
    
    public void deposit(String accountNumber, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNumber);
        
        if (!account.isActive()) {
            throw new InvalidAccountException("Account is inactive: " + accountNumber);
        }
        
        account.deposit(amount);
        
        // Record transaction
        Transaction transaction = new Transaction(
            generateTransactionId(), null, accountNumber,
            Transaction.TransactionType.DEPOSIT, amount,
            "Cash deposit"
        );
        transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
        account.addTransaction(transaction);
    }
    
    public void withdraw(String accountNumber, double amount) 
            throws InvalidAccountException, InsufficientFundsException {
        Account account = getAccount(accountNumber);
        
        if (!account.isActive()) {
            throw new InvalidAccountException("Account is inactive: " + accountNumber);
        }
        
        account.withdraw(amount);
        
        // Record transaction
        Transaction transaction = new Transaction(
            generateTransactionId(), accountNumber, null,
            Transaction.TransactionType.WITHDRAWAL, amount,
            "Cash withdrawal"
        );
        transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
        account.addTransaction(transaction);
    }
    
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws InvalidAccountException, InsufficientFundsException {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        
        if (!fromAccount.isActive()) {
            throw new InvalidAccountException("Source account is inactive: " + fromAccountNumber);
        }
        
        if (!toAccount.isActive()) {
            throw new InvalidAccountException("Destination account is inactive: " + toAccountNumber);
        }
        
        // Withdraw from source account
        fromAccount.withdraw(amount);
        
        // Deposit to destination account
        toAccount.deposit(amount);
        
        // Record transaction
        Transaction transaction = new Transaction(
            generateTransactionId(), fromAccountNumber, toAccountNumber,
            Transaction.TransactionType.TRANSFER, amount,
            "Transfer to account " + toAccountNumber
        );
        transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
        
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
    }
    
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
    
    public List<Account> getAccountsByType(Account.AccountType accountType) {
        return accounts.values().stream()
                .filter(account -> account.getAccountType() == accountType)
                .collect(java.util.stream.Collectors.toList());
    }
    
    public void closeAccount(String accountNumber) throws InvalidAccountException {
        Account account = getAccount(accountNumber);
        
        if (account.getBalance() > 0) {
            throw new IllegalStateException("Cannot close account with positive balance");
        }
        
        account.setActive(false);
    }
    
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
}
```

#### TransactionService.java
```java
package com.banking.service;

import com.banking.model.Transaction;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionService {
    private List<Transaction> allTransactions;
    
    public TransactionService() {
        this.allTransactions = new ArrayList<>();
    }
    
    public void recordTransaction(Transaction transaction) {
        allTransactions.add(transaction);
    }
    
    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return allTransactions.stream()
                .filter(t -> accountNumber.equals(t.getFromAccount()) || 
                           accountNumber.equals(t.getToAccount()))
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .collect(Collectors.toList());
    }
    
    public List<Transaction> getTransactionsByType(Transaction.TransactionType type) {
        return allTransactions.stream()
                .filter(t -> t.getType() == type)
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .collect(Collectors.toList());
    }
    
    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return allTransactions.stream()
                .filter(t -> t.getTimestamp().isAfter(startDate) && t.getTimestamp().isBefore(endDate))
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .collect(Collectors.toList());
    }
    
    public double getTotalTransactionAmount(String accountNumber, Transaction.TransactionType type) {
        return allTransactions.stream()
                .filter(t -> (accountNumber.equals(t.getFromAccount()) || accountNumber.equals(t.getToAccount())) &&
                           t.getType() == type)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    
    public List<Transaction> getRecentTransactions(int limit) {
        return allTransactions.stream()
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    public void generateAccountStatement(String accountNumber, int days) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        List<Transaction> transactions = getTransactionsByAccount(accountNumber).stream()
                .filter(t -> t.getTimestamp().isAfter(startDate))
                .collect(Collectors.toList());
        
        System.out.println("\n=== Account Statement ===");
        System.out.println("Account: " + accountNumber);
        System.out.println("Period: Last " + days + " days");
        System.out.println("Generated: " + LocalDateTime.now());
        System.out.println("==========================================");
        
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getTransactionSummary());
        }
        
        System.out.println("==========================================");
        System.out.println("Total transactions: " + transactions.size());
    }
}
```

### 3. Exception Classes

#### BankingException.java
```java
package com.banking.exception;

public class BankingException extends Exception {
    private String errorCode;
    
    public BankingException(String message) {
        super(message);
    }
    
    public BankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BankingException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
```

#### InsufficientFundsException.java
```java
package com.banking.exception;

public class InsufficientFundsException extends BankingException {
    private double requestedAmount;
    private double availableBalance;
    
    public InsufficientFundsException(String message) {
        super(message, "INSUFFICIENT_FUNDS");
    }
    
    public InsufficientFundsException(String message, double requestedAmount, double availableBalance) {
        super(message, "INSUFFICIENT_FUNDS");
        this.requestedAmount = requestedAmount;
        this.availableBalance = availableBalance;
    }
    
    public double getRequestedAmount() {
        return requestedAmount;
    }
    
    public double getAvailableBalance() {
        return availableBalance;
    }
}
```

#### InvalidAccountException.java
```java
package com.banking.exception;

public class InvalidAccountException extends BankingException {
    private String accountNumber;
    
    public InvalidAccountException(String message) {
        super(message, "INVALID_ACCOUNT");
    }
    
    public InvalidAccountException(String message, String accountNumber) {
        super(message, "INVALID_ACCOUNT");
        this.accountNumber = accountNumber;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}
```

### 4. Utility Classes

#### InputValidator.java
```java
package com.banking.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class InputValidator {
    
    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && 
               accountNumber.matches("^[A-Z0-9]{8,12}$") && 
               !accountNumber.trim().isEmpty();
    }
    
    public static boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 1000000; // Maximum transaction limit
    }
    
    public static boolean isValidName(String name) {
        return name != null && 
               name.trim().length() >= 2 && 
               name.matches("^[a-zA-Z\\s]+$");
    }
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\+?[1-9]\\d{1,14}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return phoneNumber != null && pattern.matcher(phoneNumber).matches();
    }
    
    public static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isValidAccountType(String accountType) {
        return accountType != null && 
               (accountType.equalsIgnoreCase("SAVINGS") ||
                accountType.equalsIgnoreCase("CHECKING") ||
                accountType.equalsIgnoreCase("BUSINESS"));
    }
    
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return input.trim();
    }
}
```

#### FileManager.java
```java
package com.banking.util;

import com.banking.model.Account;
import com.banking.model.Transaction;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String ACCOUNTS_FILE = "data/accounts.csv";
    private static final String TRANSACTIONS_FILE = "data/transactions.csv";
    
    public static void saveAccounts(List<Account> accounts) throws IOException {
        createDataDirectory();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_FILE))) {
            writer.println("AccountNumber,AccountHolderName,AccountType,Balance,CreatedDate,IsActive");
            
            for (Account account : accounts) {
                writer.printf("%s,%s,%s,%.2f,%s,%s%n",
                    account.getAccountNumber(),
                    account.getAccountHolderName(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getCreatedDate(),
                    account.isActive()
                );
            }
        }
    }
    
    public static List<Account> loadAccounts() throws IOException {
        List<Account> accounts = new ArrayList<>();
        Path path = Paths.get(ACCOUNTS_FILE);
        
        if (!Files.exists(path)) {
            return accounts;
        }
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    Account account = new Account(
                        fields[0],
                        fields[1],
                        Account.AccountType.valueOf(fields[2]),
                        Double.parseDouble(fields[3])
                    );
                    
                    // Set additional properties if available
                    if (fields.length > 5) {
                        account.setActive(Boolean.parseBoolean(fields[5]));
                    }
                    
                    accounts.add(account);
                }
            }
        }
        
        return accounts;
    }
    
    public static void saveTransactions(List<Transaction> transactions) throws IOException {
        createDataDirectory();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            // Append mode - don't overwrite existing transactions
            
            for (Transaction transaction : transactions) {
                writer.printf("%s,%s,%s,%s,%.2f,%s,%s%n",
                    transaction.getTransactionId(),
                    transaction.getFromAccount() != null ? transaction.getFromAccount() : "",
                    transaction.getToAccount() != null ? transaction.getToAccount() : "",
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getTimestamp(),
                    transaction.getStatus()
                );
            }
        }
    }
    
    public static List<Transaction> loadTransactions() throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        Path path = Paths.get(TRANSACTIONS_FILE);
        
        if (!Files.exists(path)) {
            return transactions;
        }
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 7) {
                    Transaction transaction = new Transaction(
                        fields[0],
                        fields[1].isEmpty() ? null : fields[1],
                        fields[2].isEmpty() ? null : fields[2],
                        Transaction.TransactionType.valueOf(fields[3]),
                        Double.parseDouble(fields[4]),
                        "Loaded from file"
                    );
                    
                    transaction.setStatus(Transaction.TransactionStatus.valueOf(fields[6]));
                    transactions.add(transaction);
                }
            }
        }
        
        return transactions;
    }
    
    private static void createDataDirectory() throws IOException {
        Path dataDir = Paths.get("data");
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
    }
}
```

### 5. Main Banking System Class

#### BankingSystem.java
```java
package com.banking;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;
import com.banking.exception.*;
import com.banking.util.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class BankingSystem {
    private AccountService accountService;
    private TransactionService transactionService;
    private Scanner scanner;
    private boolean isRunning;
    
    public BankingSystem() {
        this.accountService = new AccountService();
        this.transactionService = new TransactionService();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        
        // Load existing data
        loadData();
    }
    
    public void start() {
        System.out.println("=== Welcome to Java Banking System ===");
        System.out.println("Version 1.0 - Cross-Platform Banking Solution");
        
        while (isRunning) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            handleMainMenuChoice(choice);
        }
        
        // Save data before exiting
        saveData();
        System.out.println("Thank you for using Java Banking System!");
    }
    
    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View Account Details");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Transfer Money");
        System.out.println("6. View Account Statement");
        System.out.println("7. View All Accounts");
        System.out.println("8. Calculate Interest");
        System.out.println("9. Close Account");
        System.out.println("10. Exit");
        System.out.println("==================");
    }
    
    private void handleMainMenuChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewAccountDetails();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    transferMoney();
                    break;
                case 6:
                    viewAccountStatement();
                    break;
                case 7:
                    viewAllAccounts();
                    break;
                case 8:
                    calculateInterest();
                    break;
                case 9:
                    closeAccount();
                    break;
                case 10:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        if (choice != 10) {
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private void createAccount() {
        System.out.println("\n=== Create New Account ===");
        
        String accountNumber = getStringInput("Enter account number (8-12 alphanumeric): ");
        if (!InputValidator.isValidAccountNumber(accountNumber)) {
            System.out.println("Invalid account number format.");
            return;
        }
        
        String accountHolderName = getStringInput("Enter account holder name: ");
        if (!InputValidator.isValidName(accountHolderName)) {
            System.out.println("Invalid name format.");
            return;
        }
        
        System.out.println("Account types: SAVINGS, CHECKING, BUSINESS");
        String accountTypeStr = getStringInput("Enter account type: ");
        if (!InputValidator.isValidAccountType(accountTypeStr)) {
            System.out.println("Invalid account type.");
            return;
        }
        
        Account.AccountType accountType = Account.AccountType.valueOf(accountTypeStr.toUpperCase());
        double initialBalance = getDoubleInput("Enter initial balance: ");
        
        if (!InputValidator.isValidAmount(initialBalance)) {
            System.out.println("Invalid initial balance amount.");
            return;
        }
        
        accountService.createAccount(accountNumber, accountHolderName, accountType, initialBalance);
        System.out.println("Account created successfully!");
    }
    
    private void viewAccountDetails() {
        System.out.println("\n=== View Account Details ===");
        String accountNumber = getStringInput("Enter account number: ");
        
        try {
            Account account = accountService.getAccount(accountNumber);
            System.out.println("\n" + account.getAccountSummary());
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void depositMoney() {
        System.out.println("\n=== Deposit Money ===");
        String accountNumber = getStringInput("Enter account number: ");
        double amount = getDoubleInput("Enter amount to deposit: ");
        
        if (!InputValidator.isValidAmount(amount)) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        
        try {
            accountService.deposit(accountNumber, amount);
            System.out.println("Deposit successful!");
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void withdrawMoney() {
        System.out.println("\n=== Withdraw Money ===");
        String accountNumber = getStringInput("Enter account number: ");
        double amount = getDoubleInput("Enter amount to withdraw: ");
        
        if (!InputValidator.isValidAmount(amount)) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        
        try {
            accountService.withdraw(accountNumber, amount);
            System.out.println("Withdrawal successful!");
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void transferMoney() {
        System.out.println("\n=== Transfer Money ===");
        String fromAccount = getStringInput("Enter source account number: ");
        String toAccount = getStringInput("Enter destination account number: ");
        double amount = getDoubleInput("Enter amount to transfer: ");
        
        if (!InputValidator.isValidAmount(amount)) {
            System.out.println("Invalid transfer amount.");
            return;
        }
        
        try {
            accountService.transfer(fromAccount, toAccount, amount);
            System.out.println("Transfer successful!");
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void viewAccountStatement() {
        System.out.println("\n=== Account Statement ===");
        String accountNumber = getStringInput("Enter account number: ");
        int days = getIntInput("Enter number of days for statement: ");
        
        try {
            Account account = accountService.getAccount(accountNumber);
            transactionService.generateAccountStatement(accountNumber, days);
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void viewAllAccounts() {
        System.out.println("\n=== All Accounts ===");
        List<Account> accounts = accountService.getAllAccounts();
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println(account.getAccountSummary());
            }
        }
    }
    
    private void calculateInterest() {
        System.out.println("\n=== Calculate Interest ===");
        String accountNumber = getStringInput("Enter account number: ");
        
        try {
            Account account = accountService.getAccount(accountNumber);
            double annualRate = getDoubleInput("Enter annual interest rate (%): ");
            int years = getIntInput("Enter number of years: ");
            
            double interest = account.calculateInterest(annualRate, years);
            System.out.printf("Interest for %d years at %.2f%%: $%.2f%n", 
                            years, annualRate, interest);
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void closeAccount() {
        System.out.println("\n=== Close Account ===");
        String accountNumber = getStringInput("Enter account number: ");
        
        try {
            accountService.closeAccount(accountNumber);
            System.out.println("Account closed successfully!");
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void loadData() {
        try {
            List<Account> accounts = FileManager.loadAccounts();
            // Load accounts into service
            for (Account account : accounts) {
                // Implementation would load accounts into AccountService
            }
            
            List<Transaction> transactions = FileManager.loadTransactions();
            // Load transactions into service
            for (Transaction transaction : transactions) {
                transactionService.recordTransaction(transaction);
            }
            
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing data found. Starting with empty system.");
        }
    }
    
    private void saveData() {
        try {
            FileManager.saveAccounts(accountService.getAllAccounts());
            // Save transactions
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
    
    // Helper methods for input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
```

### 6. Main Class

#### Main.java
```java
package com.banking;

public class Main {
    public static void main(String[] args) {
        try {
            BankingSystem bankingSystem = new BankingSystem();
            bankingSystem.start();
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

## Cross-Platform Build and Run

### Linux/macOS
```bash
# Compile all Java files
javac -d bin -cp src src/**/*.java

# Create JAR file
jar cfe BankingSystem.jar com.banking.Main -C bin .

# Run the application
java -jar BankingSystem.jar
```

### Windows (Command Prompt)
```cmd
# Compile all Java files
javac -d bin -cp src src\**\*.java

# Create JAR file
jar cfe BankingSystem.jar com.banking.Main -C bin .

# Run the application
java -jar BankingSystem.jar
```

### Windows (PowerShell)
```powershell
# Compile all Java files
javac -d bin -cp src src\**\*.java

# Create JAR file
jar cfe BankingSystem.jar com.banking.Main -C bin .

# Run the application
java -jar BankingSystem.jar
```

## Maven Configuration (Optional)

### pom.xml
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
            
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.banking.Main</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Testing Strategy

### Unit Tests Example
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BankingSystemTest {
    private AccountService accountService;
    
    @BeforeEach
    public void setUp() {
        accountService = new AccountService();
    }
    
    @Test
    public void testCreateAccount() {
        accountService.createAccount("ACC001", "John Doe", 
                                   Account.AccountType.SAVINGS, 1000.0);
        
        Account account = accountService.getAccount("ACC001");
        assertNotNull(account);
        assertEquals("John Doe", account.getAccountHolderName());
        assertEquals(1000.0, account.getBalance());
    }
    
    @Test
    public void testDeposit() throws InvalidAccountException {
        accountService.createAccount("ACC002", "Jane Smith", 
                                   Account.AccountType.CHECKING, 500.0);
        
        accountService.deposit("ACC002", 200.0);
        
        Account account = accountService.getAccount("ACC002");
        assertEquals(700.0, account.getBalance());
    }
    
    @Test
    public void testWithdrawInsufficientFunds() throws InvalidAccountException {
        accountService.createAccount("ACC003", "Bob Johnson", 
                                   Account.AccountType.SAVINGS, 100.0);
        
        assertThrows(InsufficientFundsException.class, () -> {
            accountService.withdraw("ACC003", 200.0);
        });
    }
    
    @Test
    public void testTransfer() throws InvalidAccountException, InsufficientFundsException {
        accountService.createAccount("ACC004", "Alice Brown", 
                                   Account.AccountType.CHECKING, 1000.0);
        accountService.createAccount("ACC005", "Charlie Wilson", 
                                   Account.AccountType.SAVINGS, 500.0);
        
        accountService.transfer("ACC004", "ACC005", 300.0);
        
        Account fromAccount = accountService.getAccount("ACC004");
        Account toAccount = accountService.getAccount("ACC005");
        
        assertEquals(700.0, fromAccount.getBalance());
        assertEquals(800.0, toAccount.getBalance());
    }
}
```

## Performance Requirements
- Support up to 10,000 accounts
- Transaction processing in < 100ms
- File I/O operations in < 1 second
- Memory usage < 100MB for maximum load
- Concurrent access support (future enhancement)

## Extension Ideas
- Database integration (MySQL, PostgreSQL)
- REST API using Spring Boot
- Web interface using JavaFX or Swing
- Mobile application using Java
- Multi-currency support
- Loan and credit card management
- Real-time notifications
- Advanced reporting and analytics
- Integration with external payment systems

## Deliverables
1. Complete source code with proper package structure
2. Maven/Gradle build configuration
3. Comprehensive unit tests
4. Documentation (README, API docs)
5. Sample data files for testing
6. Build and run scripts for all platforms
7. User manual with screenshots

## Success Criteria
- All banking operations work correctly
- Exception handling prevents crashes
- File I/O operations work reliably
- Code compiles and runs on all target platforms
- Unit tests achieve 90% code coverage
- Performance meets specified requirements
- User interface is intuitive and responsive
- Data persistence works across application restarts
