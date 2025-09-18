# Exception Handling in Java

## Overview
Exception handling is a mechanism in Java that provides a way to handle runtime errors gracefully. It allows programs to continue execution even when unexpected situations occur, making applications more robust and user-friendly.

## Key Concepts

### What are Exceptions?
Exceptions are events that occur during the execution of a program that disrupt the normal flow of instructions. In Java, exceptions are objects that represent errors or exceptional conditions.

### Exception Hierarchy
```
Throwable
├── Error (unchecked)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception (checked/unchecked)
    ├── IOException (checked)
    ├── SQLException (checked)
    ├── RuntimeException (unchecked)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── IllegalArgumentException
    │   └── ArithmeticException
    └── Custom Exceptions
```

### Checked vs Unchecked Exceptions
- **Checked Exceptions**: Must be handled at compile time (IOException, SQLException)
- **Unchecked Exceptions**: Runtime exceptions (RuntimeException and its subclasses)

## Basic Exception Handling

### try-catch Block
```java
public class BasicExceptionHandling {
    public static void main(String[] args) {
        // Basic try-catch example
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Multiple catch blocks
        try {
            String str = null;
            int length = str.length(); // NullPointerException
            int[] array = new int[5];
            int value = array[10]; // ArrayIndexOutOfBoundsException
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General exception: " + e.getMessage());
        }
        
        // Finally block
        try {
            System.out.println("Inside try block");
            throw new RuntimeException("Something went wrong");
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block always executes");
        }
    }
    
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }
}
```

### try-with-resources
```java
import java.io.*;
import java.util.Scanner;

public class TryWithResources {
    public static void main(String[] args) {
        // Traditional resource management
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("example.txt");
            // Read from file
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
        
        // try-with-resources (Java 7+)
        try (FileInputStream fis2 = new FileInputStream("example.txt");
             Scanner scanner = new Scanner(fis2)) {
            
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        // Resources are automatically closed
        
        // Custom resource example
        try (DatabaseConnection db = new DatabaseConnection()) {
            db.connect();
            db.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

// Custom resource implementing AutoCloseable
class DatabaseConnection implements AutoCloseable {
    public void connect() throws SQLException {
        System.out.println("Connecting to database...");
        // Simulate connection
    }
    
    public void executeQuery(String query) throws SQLException {
        System.out.println("Executing query: " + query);
        // Simulate query execution
    }
    
    @Override
    public void close() throws SQLException {
        System.out.println("Closing database connection...");
        // Clean up resources
    }
}
```

## Custom Exceptions

### Creating Custom Exception Classes
```java
// Custom checked exception
public class InsufficientFundsException extends Exception {
    private double amount;
    private double balance;
    
    public InsufficientFundsException(double amount, double balance) {
        super("Insufficient funds. Required: $" + amount + ", Available: $" + balance);
        this.amount = amount;
        this.balance = balance;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public double getBalance() {
        return balance;
    }
}

// Custom unchecked exception
public class InvalidAgeException extends RuntimeException {
    private int age;
    
    public InvalidAgeException(int age) {
        super("Invalid age: " + age + ". Age must be between 0 and 150.");
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
}

// Using custom exceptions
public class BankAccount {
    private double balance;
    private String accountHolder;
    
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + ", New balance: $" + balance);
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
    }
    
    public double getBalance() {
        return balance;
    }
}

// Exception chaining
public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class DataService {
    public void saveData(String data) throws DatabaseException {
        try {
            // Simulate database operation
            if (data == null) {
                throw new IllegalArgumentException("Data cannot be null");
            }
            // Database save operation
        } catch (IllegalArgumentException e) {
            throw new DatabaseException("Failed to save data", e);
        }
    }
}
```

### Custom Exception Demo
```java
public class CustomExceptionDemo {
    public static void main(String[] args) {
        // Testing custom checked exception
        BankAccount account = new BankAccount("John Doe", 1000.0);
        
        try {
            account.withdraw(500.0);
            account.withdraw(800.0); // This will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Required amount: $" + e.getAmount());
            System.out.println("Available balance: $" + e.getBalance());
        }
        
        // Testing custom unchecked exception
        try {
            validateAge(25);
            validateAge(-5); // This will throw InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid age provided: " + e.getAge());
        }
        
        // Testing exception chaining
        DataService service = new DataService();
        try {
            service.saveData(null);
        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
            System.out.println("Root cause: " + e.getCause().getMessage());
        }
    }
    
    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException(age);
        }
        System.out.println("Valid age: " + age);
    }
}
```

## Advanced Exception Handling

### Exception Propagation
```java
public class ExceptionPropagation {
    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            System.out.println("Caught in main: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void method1() throws Exception {
        System.out.println("In method1");
        method2();
    }
    
    public static void method2() throws Exception {
        System.out.println("In method2");
        method3();
    }
    
    public static void method3() throws Exception {
        System.out.println("In method3");
        throw new Exception("Exception thrown in method3");
    }
}
```

### Re-throwing Exceptions
```java
public class RethrowingExceptions {
    public static void main(String[] args) {
        try {
            processFile("nonexistent.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
    }
    
    public static void processFile(String filename) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            // Process file
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println("Logging: File not found - " + filename);
            throw e; // Re-throw the exception
        } catch (IOException e) {
            System.err.println("Logging: IO error occurred");
            throw new IOException("Failed to process file: " + filename, e);
        }
    }
}
```

### Multi-catch (Java 7+)
```java
public class MultiCatch {
    public static void main(String[] args) {
        try {
            String input = args[0];
            int number = Integer.parseInt(input);
            int result = 100 / number;
            System.out.println("Result: " + result);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | ArithmeticException e) {
            System.out.println("Caught exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }
    }
}
```

## Best Practices

### Exception Handling Best Practices
```java
public class ExceptionBestPractices {
    
    // 1. Use specific exceptions
    public void goodSpecificException() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("file.txt");
        // Process file
    }
    
    // Avoid generic exceptions
    public void badGenericException() throws Exception {
        // Too generic - caller doesn't know what to expect
    }
    
    // 2. Don't catch exceptions you can't handle
    public void readFile(String filename) throws IOException {
        // Let the caller decide how to handle the exception
        FileInputStream fis = new FileInputStream(filename);
        // Process file
    }
    
    // 3. Use try-with-resources for cleanup
    public void goodResourceManagement() throws IOException {
        try (FileInputStream fis = new FileInputStream("file.txt")) {
            // Process file
        } // Automatically closed
    }
    
    // 4. Log exceptions appropriately
    public void goodLogging() {
        try {
            riskyOperation();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error in riskyOperation: " + e.getMessage());
            e.printStackTrace();
            
            // Re-throw if necessary
            throw new RuntimeException("Failed to perform operation", e);
        }
    }
    
    // 5. Validate input parameters
    public void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        // Process input
    }
    
    // 6. Use meaningful exception messages
    public void meaningfulMessages(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative: " + age);
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age cannot exceed 150: " + age);
        }
        // Process age
    }
    
    private void riskyOperation() throws Exception {
        // Simulate risky operation
        throw new Exception("Something went wrong");
    }
}
```

### Exception Handling Patterns
```java
import java.util.logging.Logger;
import java.util.logging.Level;

public class ExceptionHandlingPatterns {
    private static final Logger logger = Logger.getLogger(ExceptionHandlingPatterns.class.getName());
    
    // Pattern 1: Retry mechanism
    public void retryOperation(int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                performOperation();
                break; // Success, exit loop
            } catch (Exception e) {
                attempts++;
                if (attempts >= maxRetries) {
                    logger.log(Level.SEVERE, "Operation failed after " + maxRetries + " attempts", e);
                    throw new RuntimeException("Operation failed", e);
                }
                logger.log(Level.WARNING, "Attempt " + attempts + " failed, retrying...", e);
                try {
                    Thread.sleep(1000); // Wait before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Operation interrupted", ie);
                }
            }
        }
    }
    
    // Pattern 2: Fallback mechanism
    public String getData(String primarySource, String fallbackSource) {
        try {
            return readFromSource(primarySource);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Primary source failed, using fallback", e);
            try {
                return readFromSource(fallbackSource);
            } catch (IOException fallbackException) {
                logger.log(Level.SEVERE, "Both primary and fallback sources failed", fallbackException);
                throw new RuntimeException("Unable to retrieve data from any source", fallbackException);
            }
        }
    }
    
    // Pattern 3: Validation with custom exceptions
    public void validateUser(User user) throws ValidationException {
        ValidationException validationException = new ValidationException();
        
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            validationException.addError("Name is required");
        }
        
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            validationException.addError("Valid email is required");
        }
        
        if (user.getAge() < 0 || user.getAge() > 150) {
            validationException.addError("Age must be between 0 and 150");
        }
        
        if (validationException.hasErrors()) {
            throw validationException;
        }
    }
    
    private void performOperation() throws Exception {
        // Simulate operation that might fail
        if (Math.random() < 0.7) {
            throw new Exception("Simulated failure");
        }
        System.out.println("Operation successful");
    }
    
    private String readFromSource(String source) throws IOException {
        // Simulate reading from source
        if (source.equals("primary")) {
            throw new IOException("Primary source unavailable");
        }
        return "Data from " + source;
    }
}

// Custom validation exception
class ValidationException extends Exception {
    private java.util.List<String> errors = new java.util.ArrayList<>();
    
    public void addError(String error) {
        errors.add(error);
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    @Override
    public String getMessage() {
        return "Validation failed: " + String.join(", ", errors);
    }
    
    public java.util.List<String> getErrors() {
        return new java.util.ArrayList<>(errors);
    }
}

class User {
    private String name;
    private String email;
    private int age;
    
    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}
```

## Testing Exceptions

### JUnit Exception Testing
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTesting {
    private BankAccount account;
    
    @BeforeEach
    public void setUp() {
        account = new BankAccount("Test User", 1000.0);
    }
    
    @Test
    public void testInsufficientFundsException() {
        // Test that exception is thrown
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(1500.0);
        });
        
        // Test exception message
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(1500.0);
        });
        
        assertEquals("Insufficient funds. Required: $1500.0, Available: $1000.0", 
                     exception.getMessage());
        assertEquals(1500.0, exception.getAmount());
        assertEquals(1000.0, exception.getBalance());
    }
    
    @Test
    public void testValidWithdrawal() {
        // Test that no exception is thrown
        assertDoesNotThrow(() -> {
            account.withdraw(500.0);
        });
        
        assertEquals(500.0, account.getBalance());
    }
    
    @Test
    public void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100.0);
        });
    }
}
```

## Performance Considerations

### Exception Performance
```java
public class ExceptionPerformance {
    
    // Inefficient: Using exceptions for control flow
    public int badExceptionUsage(String[] array, String target) {
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(target)) {
                    return i;
                }
            }
            throw new RuntimeException("Not found");
        } catch (RuntimeException e) {
            return -1;
        }
    }
    
    // Efficient: Normal control flow
    public int goodControlFlow(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    // Exception creation is expensive
    public void demonstrateExceptionCost() {
        long startTime = System.nanoTime();
        
        // Creating many exceptions
        for (int i = 0; i < 10000; i++) {
            try {
                throw new RuntimeException("Test exception " + i);
            } catch (RuntimeException e) {
                // Catch and ignore
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Exception creation time: " + (endTime - startTime) + " ns");
    }
}
```

## Learning Outcomes
- Understanding the Java exception hierarchy and types
- Learning proper exception handling with try-catch-finally blocks
- Understanding try-with-resources for automatic resource management
- Learning to create and use custom exceptions
- Understanding exception propagation and re-throwing
- Learning exception handling best practices
- Understanding performance implications of exceptions
- Learning to test exception scenarios effectively
