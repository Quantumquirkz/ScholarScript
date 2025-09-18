# Exception Handling in Java

## Overview
Exception handling in Java provides a robust mechanism to handle runtime errors and exceptional conditions that may occur during program execution. Java's exception handling is built on the concepts of try-catch blocks, throw statements, and the exception hierarchy.

## Key Concepts

### Exception Hierarchy
```
Throwable
├── Error (unchecked)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception
    ├── RuntimeException (unchecked)
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   ├── IndexOutOfBoundsException
    │   └── ArithmeticException
    └── Checked Exceptions
        ├── IOException
        ├── SQLException
        ├── ClassNotFoundException
        └── ParseException
```

### Types of Exceptions

#### Checked Exceptions
Must be handled at compile time using try-catch or declared in method signature.

```java
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckedExceptionExample {
    public static void main(String[] args) {
        // Method 1: Handle with try-catch
        try {
            readFile("nonexistent.txt");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        // Method 2: Declare in method signature
        try {
            parseDate("invalid-date");
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
    }
    
    // IOException must be handled
    public static void readFile(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(reader);
        
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        
        bufferedReader.close();
    }
    
    // ParseException must be handled
    public static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }
}
```

#### Unchecked Exceptions
Runtime exceptions that don't need to be explicitly handled.

```java
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        // NullPointerException
        try {
            String str = null;
            int length = str.length(); // Throws NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        }
        
        // ArithmeticException
        try {
            int result = 10 / 0; // Throws ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception: " + e.getMessage());
        }
        
        // ArrayIndexOutOfBoundsException
        try {
            int[] array = {1, 2, 3};
            int value = array[5]; // Throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index exception: " + e.getMessage());
        }
        
        // IllegalArgumentException
        try {
            validateAge(-5); // Throws IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        }
    }
    
    public static void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative: " + age);
        }
        System.out.println("Valid age: " + age);
    }
}
```

## Try-Catch-Finally Blocks

### Basic Exception Handling
```java
import java.util.Scanner;

public class TryCatchFinallyExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter a number: ");
            int number = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter divisor: ");
            int divisor = Integer.parseInt(scanner.nextLine());
            
            double result = divide(number, divisor);
            System.out.println("Result: " + result);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number");
            System.out.println("Details: " + e.getMessage());
            
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed");
            System.out.println("Details: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            
        } finally {
            System.out.println("Finally block executed");
            scanner.close();
        }
    }
    
    public static double divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) dividend / divisor;
    }
}
```

### Multiple Catch Blocks
```java
import java.io.*;

public class MultipleCatchExample {
    public static void main(String[] args) {
        try {
            processFile("input.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Security error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        }
    }
    
    public static void processFile(String filename) throws IOException {
        File file = new File(filename);
        
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + filename);
        }
        
        if (!file.canRead()) {
            throw new SecurityException("Cannot read file: " + filename);
        }
        
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        
        bufferedReader.close();
    }
}
```

## Custom Exceptions

### Creating Custom Exception Classes
```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Required: $" + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}

// Custom unchecked exception
class InvalidAccountException extends RuntimeException {
    private String accountNumber;
    
    public InvalidAccountException(String accountNumber) {
        super("Invalid account number: " + accountNumber);
        this.accountNumber = accountNumber;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}

// Bank account class using custom exceptions
class BankAccount {
    private String accountNumber;
    private double balance;
    
    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new InvalidAccountException(accountNumber);
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        
        balance -= amount;
        System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}

public class CustomExceptionExample {
    public static void main(String[] args) {
        try {
            // Test valid account
            BankAccount account = new BankAccount("123456789", 1000.0);
            
            account.deposit(500.0);
            account.withdraw(200.0);
            account.withdraw(1500.0); // This will throw InsufficientFundsException
            
        } catch (InsufficientFundsException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
            System.out.println("Shortfall: $" + e.getAmount());
            
        } catch (InvalidAccountException e) {
            System.out.println("Invalid account: " + e.getMessage());
            
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument: " + e.getMessage());
        }
        
        // Test invalid account
        try {
            BankAccount invalidAccount = new BankAccount("", 100.0);
        } catch (InvalidAccountException e) {
            System.out.println("Invalid account caught: " + e.getMessage());
        }
    }
}
```

## Try-With-Resources

### Automatic Resource Management
```java
import java.io.*;
import java.util.Scanner;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // Try-with-resources automatically closes resources
        try (FileWriter writer = new FileWriter("output.txt");
             PrintWriter printWriter = new PrintWriter(writer)) {
            
            printWriter.println("Hello, World!");
            printWriter.println("This is a test file.");
            printWriter.printf("Current time: %d%n", System.currentTimeMillis());
            
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
        
        // Reading file with try-with-resources
        try (FileReader reader = new FileReader("output.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            
            String line;
            System.out.println("File contents:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        // Multiple resources
        try (Scanner scanner = new Scanner(System.in);
             FileWriter writer = new FileWriter("user_input.txt")) {
            
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            writer.write("User name: " + name + "\n");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Custom resource class implementing AutoCloseable
class DatabaseConnection implements AutoCloseable {
    private String connectionString;
    
    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        System.out.println("Connected to: " + connectionString);
    }
    
    public void executeQuery(String query) {
        System.out.println("Executing: " + query);
    }
    
    @Override
    public void close() {
        System.out.println("Closing connection to: " + connectionString);
    }
}

class CustomResourceExample {
    public static void main(String[] args) {
        try (DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306/test")) {
            db.executeQuery("SELECT * FROM users");
            db.executeQuery("INSERT INTO logs VALUES ('test')");
            
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
        // Database connection is automatically closed
    }
}
```

## Exception Propagation

### Method Call Stack and Exception Propagation
```java
public class ExceptionPropagationExample {
    public static void main(String[] args) {
        try {
            methodA();
        } catch (Exception e) {
            System.out.println("Exception caught in main: " + e.getMessage());
            e.printStackTrace(); // Print stack trace
        }
    }
    
    public static void methodA() throws Exception {
        System.out.println("Method A called");
        methodB();
    }
    
    public static void methodB() throws Exception {
        System.out.println("Method B called");
        methodC();
    }
    
    public static void methodC() throws Exception {
        System.out.println("Method C called");
        throw new Exception("Exception thrown in method C");
    }
}

// Exception handling at different levels
class ExceptionHandlingLevels {
    public static void main(String[] args) {
        try {
            processData();
        } catch (DataProcessingException e) {
            System.out.println("Data processing failed: " + e.getMessage());
        }
    }
    
    public static void processData() throws DataProcessingException {
        try {
            validateData();
            transformData();
            saveData();
        } catch (ValidationException e) {
            throw new DataProcessingException("Validation failed", e);
        } catch (TransformationException e) {
            throw new DataProcessingException("Transformation failed", e);
        } catch (SaveException e) {
            throw new DataProcessingException("Save failed", e);
        }
    }
    
    public static void validateData() throws ValidationException {
        throw new ValidationException("Invalid data format");
    }
    
    public static void transformData() throws TransformationException {
        throw new TransformationException("Transformation error");
    }
    
    public static void saveData() throws SaveException {
        throw new SaveException("Database connection failed");
    }
}

// Custom exception classes for the example
class DataProcessingException extends Exception {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}

class TransformationException extends Exception {
    public TransformationException(String message) {
        super(message);
    }
}

class SaveException extends Exception {
    public SaveException(String message) {
        super(message);
    }
}
```

## Best Practices

### Exception Handling Guidelines
```java
import java.util.logging.Logger;
import java.util.logging.Level;

public class ExceptionBestPractices {
    private static final Logger logger = Logger.getLogger(ExceptionBestPractices.class.getName());
    
    public static void main(String[] args) {
        // Good practice: Specific exception handling
        try {
            performOperation();
        } catch (SpecificException e) {
            // Handle specific exception appropriately
            logger.log(Level.WARNING, "Specific error occurred", e);
            handleSpecificError(e);
        } catch (AnotherSpecificException e) {
            // Handle another specific exception
            logger.log(Level.SEVERE, "Another specific error occurred", e);
            handleAnotherError(e);
        }
        
        // Good practice: Don't catch and ignore
        try {
            riskyOperation();
        } catch (Exception e) {
            // Log the exception instead of ignoring it
            logger.log(Level.WARNING, "Risky operation failed", e);
            // Handle appropriately or rethrow
        }
        
        // Good practice: Use finally for cleanup
        FileReader reader = null;
        try {
            reader = new FileReader("file.txt");
            // Process file
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File operation failed", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Failed to close file", e);
                }
            }
        }
    }
    
    public static void performOperation() throws SpecificException, AnotherSpecificException {
        // Operation implementation
    }
    
    public static void riskyOperation() throws Exception {
        // Risky operation implementation
    }
    
    private static void handleSpecificError(SpecificException e) {
        // Handle specific error
    }
    
    private static void handleAnotherError(AnotherSpecificException e) {
        // Handle another error
    }
}

class SpecificException extends Exception {
    public SpecificException(String message) {
        super(message);
    }
}

class AnotherSpecificException extends Exception {
    public AnotherSpecificException(String message) {
        super(message);
    }
}
```

## Cross-Platform Considerations

### Compilation and Execution
```bash
# Linux/macOS
javac *.java
java ClassName

# Windows (Command Prompt)
javac *.java
java ClassName

# Windows (PowerShell)
javac *.java
java ClassName
```

### Platform-Specific Considerations
- File path handling may differ between platforms
- Line ending conventions vary (Windows: \r\n, Unix/Linux: \n)
- Character encoding may need special attention
- System-specific error messages

## Common Pitfalls

1. **Catching and Ignoring**: Never catch exceptions without handling them
2. **Overly Broad Catch**: Avoid catching Exception unless necessary
3. **Resource Leaks**: Always close resources in finally blocks or use try-with-resources
4. **Exception Swallowing**: Don't catch exceptions just to prevent them from propagating
5. **Poor Error Messages**: Provide meaningful error messages for debugging

## Best Practices Summary

1. **Use Specific Exceptions**: Catch specific exceptions rather than general ones
2. **Handle Exceptions Appropriately**: Log, recover, or rethrow as appropriate
3. **Use Try-With-Resources**: For automatic resource management
4. **Provide Meaningful Messages**: Include context in exception messages
5. **Document Exceptions**: Use @throws in JavaDoc for checked exceptions
6. **Don't Suppress Exceptions**: Always handle exceptions meaningfully
7. **Use Logging**: Log exceptions for debugging and monitoring

## Exercises

1. Create a file reader with proper exception handling
2. Implement a calculator with input validation and exception handling
3. Build a database connection manager with custom exceptions
4. Create a network client with timeout and connection exceptions
5. Implement a configuration loader with validation exceptions

## Learning Outcomes
- Understanding Java exception hierarchy
- Implementing proper exception handling with try-catch-finally
- Creating custom exception classes
- Using try-with-resources for resource management
- Understanding exception propagation and best practices
- Cross-platform Java development
