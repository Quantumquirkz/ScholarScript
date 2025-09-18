# Practice 01: Hello World

## Objective
Learn the basic structure of a Java program, understand compilation and execution, and become familiar with the Java development environment.

## Problem Statement
Create a simple "Hello World" program that demonstrates the basic Java program structure and learn how to compile and run Java programs on different platforms.

### Requirements
- Create a simple HelloWorld class
- Understand the main method structure
- Learn compilation and execution commands
- Practice cross-platform compatibility

### Expected Output
```
Hello, World!
Welcome to Java Programming!
```

## Solution

### Complete Code
```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        // Print a simple greeting message
        System.out.println("Hello, World!");
        
        // Print a welcome message
        System.out.println("Welcome to Java Programming!");
        
        // Demonstrate different print methods
        System.out.print("This is ");
        System.out.print("all on ");
        System.out.println("one line!");
        
        // Print with formatting
        System.out.printf("Today's date: %s%n", java.time.LocalDate.now());
        
        // Print command line arguments if provided
        if (args.length > 0) {
            System.out.println("Command line arguments:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Argument " + i + ": " + args[i]);
            }
        }
        
        // Print system information
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("User: " + System.getProperty("user.name"));
    }
}
```

### Code Explanation
1. **Class Declaration**: `public class HelloWorld` - declares a public class named HelloWorld
2. **Main Method**: `public static void main(String[] args)` - the entry point of the program
3. **System.out.println()**: Used to print text to the console with a newline
4. **System.out.print()**: Prints text without adding a newline
5. **System.out.printf()**: Formatted printing similar to C's printf
6. **Command Line Arguments**: Access to arguments passed when running the program
7. **System Properties**: Access to system information

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
# Compile the Java source file
javac HelloWorld.java

# Run the compiled program
java HelloWorld

# Run with command line arguments
java HelloWorld arg1 arg2 arg3

# Verify Java installation
java -version
javac -version
```

### Windows (Command Prompt)
```cmd
# Compile the Java source file
javac HelloWorld.java

# Run the compiled program
java HelloWorld

# Run with command line arguments
java HelloWorld arg1 arg2 arg3

# Verify Java installation
java -version
javac -version
```

### Windows (PowerShell)
```powershell
# Compile the Java source file
javac HelloWorld.java

# Run the compiled program
java HelloWorld

# Run with command line arguments
java HelloWorld arg1 arg2 arg3

# Verify Java installation
java -version
javac -version
```

## Advanced Examples

### Enhanced HelloWorld with User Input
```java
import java.util.Scanner;

public class HelloWorldAdvanced {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        System.out.println("Hello, " + name + "!");
        System.out.println("You are " + age + " years old.");
        
        // Determine age group
        if (age < 18) {
            System.out.println("You are a minor.");
        } else if (age < 65) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a senior citizen.");
        }
        
        scanner.close();
    }
}
```

### HelloWorld with Methods
```java
public class HelloWorldMethods {
    public static void main(String[] args) {
        printGreeting();
        printPersonalizedGreeting("Alice");
        printPersonalizedGreeting("Bob", 25);
        
        // Demonstrate method return values
        String message = createGreeting("Charlie");
        System.out.println(message);
        
        // Demonstrate variable arguments
        printMultipleGreetings("David", "Eve", "Frank");
    }
    
    // Method with no parameters and no return value
    public static void printGreeting() {
        System.out.println("Hello, World!");
    }
    
    // Method with one parameter
    public static void printPersonalizedGreeting(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
    // Method with multiple parameters
    public static void printPersonalizedGreeting(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }
    
    // Method with return value
    public static String createGreeting(String name) {
        return "Welcome, " + name + "!";
    }
    
    // Method with variable arguments (varargs)
    public static void printMultipleGreetings(String... names) {
        System.out.println("Greetings to:");
        for (String name : names) {
            System.out.println("- " + name);
        }
    }
}
```

### HelloWorld with Date and Time
```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloWorldDateTime {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("Current Date and Time Information:");
        
        // Current date
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);
        
        // Current time
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now);
        
        // Current date and time
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Date and time: " + dateTime);
        
        // Formatted date and time
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("Formatted date: " + today.format(dateFormatter));
        System.out.println("Formatted time: " + now.format(timeFormatter));
        System.out.println("Formatted date-time: " + dateTime.format(dateTimeFormatter));
        
        // Day of week
        System.out.println("Day of week: " + today.getDayOfWeek());
        
        // Day of year
        System.out.println("Day of year: " + today.getDayOfYear());
    }
}
```

## Build Scripts

### Linux/macOS Build Script
```bash
#!/bin/bash
# build.sh

echo "Building HelloWorld Java program..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile Java source files
javac -d bin *.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Run the main class
    echo "Running HelloWorld..."
    java -cp bin HelloWorld
    
    echo "Execution completed!"
else
    echo "Compilation failed!"
    exit 1
fi
```

### Windows Build Script
```batch
@echo off
REM build.bat

echo Building HelloWorld Java program...

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

REM Compile Java source files
javac -d bin *.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    
    REM Run the main class
    echo Running HelloWorld...
    java -cp bin HelloWorld
    
    echo Execution completed!
) else (
    echo Compilation failed!
    exit /b 1
)
```

## Common Issues and Solutions

### Issue 1: "javac is not recognized"
**Solution:**
- Ensure Java Development Kit (JDK) is installed
- Add JDK bin directory to PATH environment variable
- Restart command prompt/terminal after adding to PATH

### Issue 2: "Error: Could not find or load main class"
**Solution:**
- Ensure the class name matches the filename (case-sensitive)
- Check that the .class file exists in the current directory
- Use the correct classpath: `java -cp . HelloWorld`

### Issue 3: "Public class must be defined in its own file"
**Solution:**
- Each public class must be in a file named exactly like the class
- File `HelloWorld.java` must contain only the `HelloWorld` class

## Best Practices

### Code Style
```java
// Good: Clear and descriptive class name
public class HelloWorld {
    
    // Good: Proper indentation and spacing
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

// Good: Meaningful variable names
public class GreetingProgram {
    public static void main(String[] args) {
        String greetingMessage = "Hello, World!";
        String userName = "Java Developer";
        
        System.out.println(greetingMessage);
        System.out.println("Welcome, " + userName + "!");
    }
}
```

### Documentation
```java
/**
 * A simple Hello World program that demonstrates
 * basic Java programming concepts.
 * 
 * @author Your Name
 * @version 1.0
 * @since 2024-01-01
 */
public class HelloWorld {
    
    /**
     * The main method is the entry point of the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## Exercises

### Exercise 1: Personal Greeting
Create a program that asks for the user's name and age, then prints a personalized greeting.

### Exercise 2: Calculator
Create a simple calculator that performs basic arithmetic operations (addition, subtraction, multiplication, division).

### Exercise 3: Temperature Converter
Create a program that converts between Celsius and Fahrenheit temperatures.

### Exercise 4: Number Guessing Game
Create a simple number guessing game where the program generates a random number and the user tries to guess it.

### Exercise 5: Simple Menu
Create a program with a menu that allows users to choose different operations.

## Learning Outcomes
- Understanding Java program structure and syntax
- Learning how to compile and run Java programs
- Understanding the main method and its parameters
- Learning basic input/output operations
- Understanding cross-platform compatibility
- Learning Java development environment setup
- Understanding command line arguments
- Learning basic debugging techniques
