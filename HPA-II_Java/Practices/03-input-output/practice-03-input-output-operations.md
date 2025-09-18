# Practice 03: Input and Output Operations in Java

## Objective
Learn how to handle input and output operations in Java using the Scanner class for input and System.out for output. Understand different formatting options and input validation.

## Problem Statement
Create a program that demonstrates various input and output operations including reading different data types, formatting output, and handling user input.

### Requirements
- Read different types of data from user input
- Display formatted output
- Handle input validation and error cases
- Demonstrate various output formatting options

### Example Interaction
```
Enter your name: John Doe
Enter your age: 25
Enter your height: 1.75
Enter your grade: A
Name: John Doe
Age: 25
Height: 1.75
Grade: A
```

## Solution

### Complete Code
```java
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * InputOutputDemo class demonstrates various input and output operations
 */
public class InputOutputDemo {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Demonstrate different input types
            demonstrateBasicInput(scanner);
            
            // Demonstrate formatted output
            demonstrateFormattedOutput();
            
            // Demonstrate input validation
            demonstrateInputValidation(scanner);
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Demonstrates basic input operations
     */
    public static void demonstrateBasicInput(Scanner scanner) {
        System.out.println("=== Basic Input Demo ===");
        
        // String input
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        // Integer input
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        // Double input
        System.out.print("Enter your height (in meters): ");
        double height = scanner.nextDouble();
        
        // Character input
        System.out.print("Enter your grade: ");
        char grade = scanner.next().charAt(0);
        
        // Boolean input
        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean();
        
        // Display the input
        System.out.println("\n=== Input Results ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Grade: " + grade);
        System.out.println("Is Student: " + isStudent);
        
        // Clear the input buffer
        scanner.nextLine();
    }
    
    /**
     * Demonstrates formatted output
     */
    public static void demonstrateFormattedOutput() {
        System.out.println("\n=== Formatted Output Demo ===");
        
        String name = "John Doe";
        int age = 25;
        double height = 1.75;
        char grade = 'A';
        
        // Basic output
        System.out.println("Basic output:");
        System.out.println("Name: " + name + ", Age: " + age);
        
        // Formatted output using printf
        System.out.println("\nFormatted output using printf:");
        System.out.printf("Name: %s, Age: %d, Height: %.2f, Grade: %c%n", 
                         name, age, height, grade);
        
        // String formatting
        System.out.println("\nString formatting:");
        String formatted = String.format("Student: %s (Age: %d, Height: %.2fm, Grade: %c)", 
                                        name, age, height, grade);
        System.out.println(formatted);
        
        // Number formatting
        System.out.println("\nNumber formatting:");
        double price = 19.99;
        int quantity = 5;
        double total = price * quantity;
        
        System.out.printf("Price: $%.2f%n", price);
        System.out.printf("Quantity: %d%n", quantity);
        System.out.printf("Total: $%.2f%n", total);
    }
    
    /**
     * Demonstrates input validation
     */
    public static void demonstrateInputValidation(Scanner scanner) {
        System.out.println("\n=== Input Validation Demo ===");
        
        // Validate integer input
        int validAge = getValidInteger(scanner, "Enter a valid age (1-120): ", 1, 120);
        System.out.println("Valid age entered: " + validAge);
        
        // Validate double input
        double validHeight = getValidDouble(scanner, "Enter a valid height (0.5-3.0): ", 0.5, 3.0);
        System.out.println("Valid height entered: " + validHeight);
        
        // Validate string input
        String validName = getValidString(scanner, "Enter your name (at least 2 characters): ", 2);
        System.out.println("Valid name entered: " + validName);
    }
    
    /**
     * Gets a valid integer within a specified range
     */
    public static int getValidInteger(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }
    
    /**
     * Gets a valid double within a specified range
     */
    public static double getValidDouble(Scanner scanner, String prompt, double min, double max) {
        double value;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
    
    /**
     * Gets a valid string with minimum length
     */
    public static String getValidString(Scanner scanner, String prompt, int minLength) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.length() >= minLength) {
                return value;
            } else {
                System.out.println("Please enter at least " + minLength + " characters.");
            }
        }
    }
}
```

### Code Explanation
1. **Scanner Class**: Used for reading input from various sources
2. **Input Methods**: `nextLine()`, `nextInt()`, `nextDouble()`, `next()`, `nextBoolean()`
3. **Output Methods**: `println()`, `print()`, `printf()`
4. **Formatting**: String formatting with `%s`, `%d`, `%.2f`, `%c`
5. **Input Validation**: Try-catch blocks and range checking
6. **Resource Management**: Properly closing Scanner

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
javac InputOutputDemo.java
java InputOutputDemo
```

### Windows (Command Prompt)
```cmd
javac InputOutputDemo.java
java InputOutputDemo
```

### Windows (PowerShell)
```powershell
javac InputOutputDemo.java
java InputOutputDemo
```

## Key Concepts

### Scanner Class Methods

#### Reading Different Data Types
```java
import java.util.Scanner;

public class ScannerMethodsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Reading different data types
        System.out.print("Enter a word: ");
        String word = scanner.next();           // Reads until whitespace
        
        System.out.print("Enter a line: ");
        String line = scanner.nextLine();       // Reads entire line including spaces
        
        System.out.print("Enter an integer: ");
        int integer = scanner.nextInt();        // Reads integer
        
        System.out.print("Enter a decimal: ");
        double decimal = scanner.nextDouble();  // Reads double
        
        System.out.print("Enter a boolean: ");
        boolean bool = scanner.nextBoolean();   // Reads boolean
        
        System.out.print("Enter a character: ");
        char character = scanner.next().charAt(0); // Reads first character
        
        // Display results
        System.out.println("Word: " + word);
        System.out.println("Line: " + line);
        System.out.println("Integer: " + integer);
        System.out.println("Decimal: " + decimal);
        System.out.println("Boolean: " + bool);
        System.out.println("Character: " + character);
        
        scanner.close();
    }
}
```

### Output Formatting Options

#### Different Output Methods
```java
public class OutputFormattingDemo {
    public static void main(String[] args) {
        String name = "Alice";
        int age = 25;
        double salary = 50000.75;
        
        // System.out.print() - no newline
        System.out.print("Hello ");
        System.out.print("World");
        System.out.println(); // Add newline
        
        // System.out.println() - with newline
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        
        // System.out.printf() - formatted output
        System.out.printf("Name: %s, Age: %d, Salary: $%.2f%n", name, age, salary);
        
        // String.format() - create formatted string
        String formatted = String.format("Employee: %s (%d years old) earns $%.2f", 
                                        name, age, salary);
        System.out.println(formatted);
        
        // Format specifiers
        System.out.println("\nFormat Specifiers:");
        System.out.printf("String: %s%n", name);
        System.out.printf("Integer: %d%n", age);
        System.out.printf("Float: %.2f%n", salary);
        System.out.printf("Character: %c%n", name.charAt(0));
        System.out.printf("Boolean: %b%n", true);
        System.out.printf("Hex: %x%n", age);
        System.out.printf("Octal: %o%n", age);
    }
}
```

## Common Pitfalls

1. **Scanner Buffer Issues**: Mixing `nextLine()` with other methods
2. **Input Mismatch**: Wrong data type entered by user
3. **Resource Leaks**: Not closing Scanner objects
4. **Input Validation**: Not validating user input
5. **Formatting Errors**: Incorrect format specifiers

## Variations

### Enhanced Input with Menu System
```java
import java.util.Scanner;

public class MenuSystemDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getMenuChoice(scanner);
            
            switch (choice) {
                case 1:
                    calculateArea(scanner);
                    break;
                case 2:
                    calculateVolume(scanner);
                    break;
                case 3:
                    convertTemperature(scanner);
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    public static void displayMenu() {
        System.out.println("\n=== Calculator Menu ===");
        System.out.println("1. Calculate Rectangle Area");
        System.out.println("2. Calculate Box Volume");
        System.out.println("3. Convert Temperature");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    public static int getMenuChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
    
    public static void calculateArea(Scanner scanner) {
        System.out.print("Enter length: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width: ");
        double width = scanner.nextDouble();
        
        double area = length * width;
        System.out.printf("Area: %.2f square units%n", area);
    }
    
    public static void calculateVolume(Scanner scanner) {
        System.out.print("Enter length: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width: ");
        double width = scanner.nextDouble();
        System.out.print("Enter height: ");
        double height = scanner.nextDouble();
        
        double volume = length * width * height;
        System.out.printf("Volume: %.2f cubic units%n", volume);
    }
    
    public static void convertTemperature(Scanner scanner) {
        System.out.print("Enter temperature in Celsius: ");
        double celsius = scanner.nextDouble();
        
        double fahrenheit = (celsius * 9.0 / 5.0) + 32.0;
        System.out.printf("%.2f°C = %.2f°F%n", celsius, fahrenheit);
    }
}
```

### File Input/Output Demo
```java
import java.io.*;
import java.util.Scanner;

public class FileIODemo {
    public static void main(String[] args) {
        // Write to file
        writeToFile();
        
        // Read from file
        readFromFile();
        
        // Read with Scanner
        readWithScanner();
    }
    
    public static void writeToFile() {
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            writer.println("Hello, File!");
            writer.println("This is a test file.");
            writer.printf("Current time: %d%n", System.currentTimeMillis());
            System.out.println("Data written to output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            System.out.println("\nReading from file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    
    public static void readWithScanner() {
        try (Scanner scanner = new Scanner(new File("output.txt"))) {
            System.out.println("\nReading with Scanner:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
```

### Input Validation with Custom Methods
```java
import java.util.Scanner;

public class InputValidationDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Validate email
        String email = getValidEmail(scanner);
        System.out.println("Valid email: " + email);
        
        // Validate phone number
        String phone = getValidPhone(scanner);
        System.out.println("Valid phone: " + phone);
        
        // Validate age range
        int age = getValidAge(scanner);
        System.out.println("Valid age: " + age);
        
        scanner.close();
    }
    
    public static String getValidEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine().trim();
            if (email.contains("@") && email.contains(".")) {
                return email;
            }
            System.out.println("Please enter a valid email address.");
        }
    }
    
    public static String getValidPhone(Scanner scanner) {
        String phone;
        while (true) {
            System.out.print("Enter phone number (10 digits): ");
            phone = scanner.nextLine().replaceAll("[^0-9]", "");
            if (phone.length() == 10) {
                return phone;
            }
            System.out.println("Please enter a valid 10-digit phone number.");
        }
    }
    
    public static int getValidAge(Scanner scanner) {
        int age;
        while (true) {
            try {
                System.out.print("Enter age (18-100): ");
                age = Integer.parseInt(scanner.nextLine());
                if (age >= 18 && age <= 100) {
                    return age;
                }
                System.out.println("Please enter an age between 18 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
```

## Advanced Features

### Console Colors (ANSI Escape Codes)
```java
public class ColoredOutputDemo {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    public static void main(String[] args) {
        System.out.println(RED + "This text is red" + RESET);
        System.out.println(GREEN + "This text is green" + RESET);
        System.out.println(YELLOW + "This text is yellow" + RESET);
        System.out.println(BLUE + "This text is blue" + RESET);
        System.out.println(PURPLE + "This text is purple" + RESET);
        System.out.println(CYAN + "This text is cyan" + RESET);
        
        // Colored formatted output
        String name = "Alice";
        int age = 25;
        System.out.printf("%sName: %s%s, %sAge: %d%s%n", 
                         GREEN, name, RESET, BLUE, age, RESET);
    }
}
```

### Progress Bar Demo
```java
public class ProgressBarDemo {
    public static void main(String[] args) {
        System.out.println("Loading...");
        
        for (int i = 0; i <= 100; i += 10) {
            displayProgressBar(i);
            try {
                Thread.sleep(200); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        System.out.println("\nComplete!");
    }
    
    public static void displayProgressBar(int percentage) {
        int barLength = 20;
        int filledLength = (percentage * barLength) / 100;
        
        System.out.print("\r[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                System.out.print("=");
            } else {
                System.out.print(" ");
            }
        }
        System.out.printf("] %d%%", percentage);
    }
}
```

## Best Practices

1. **Always close Scanner objects** to prevent resource leaks
2. **Validate user input** before using it
3. **Use try-catch blocks** for input operations
4. **Provide clear prompts** to users
5. **Handle edge cases** and error conditions
6. **Use appropriate data types** for input
7. **Format output** for better readability

## Exercises

1. Create a program that reads student information and calculates GPA
2. Write a program that validates user login credentials
3. Create a calculator that reads expressions and evaluates them
4. Write a program that reads a file and counts words/characters
5. Create a program that generates formatted reports from user input

## Learning Outcomes
- Understanding Java input/output operations
- Working with the Scanner class for input
- Learning different output formatting options
- Implementing input validation and error handling
- Cross-platform Java development
- Best practices for I/O operations
