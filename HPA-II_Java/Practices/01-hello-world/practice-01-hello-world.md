# Practice 01: Hello World and Basic Java Program Structure

## Objective
Learn the basic structure of a Java program by creating your first "Hello World" application and understanding the fundamental components of Java programming.

## Problem Statement
Create a Java program that prints "Hello World" and demonstrates basic program structure.

### Requirements
- Create a public class with a main method
- Print "Hello World" to the console
- Demonstrate basic Java program structure
- Understand compilation and execution process

### Example Output
```
Hello World from Java!
```

## Solution

### Complete Code
```java
/**
 * HelloWorld class demonstrates the basic structure of a Java program
 * This is typically the first program written when learning Java
 */
public class HelloWorld {
    
    /**
     * Main method - the entry point of any Java application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Print "Hello World" to the console
        System.out.println("Hello World from Java!");
    }
}
```

### Code Explanation
1. **Class Declaration**: `public class HelloWorld` - defines a public class named HelloWorld
2. **Main Method**: `public static void main(String[] args)` - the entry point of the program
3. **Output Statement**: `System.out.println()` - prints text to the console
4. **Comments**: `//` for single-line comments, `/* */` for multi-line comments
5. **Documentation**: `/** */` for JavaDoc comments

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
# Compile the Java file
javac HelloWorld.java

# Run the compiled program
java HelloWorld
```

### Windows (Command Prompt)
```cmd
# Compile the Java file
javac HelloWorld.java

# Run the compiled program
java HelloWorld
```

### Windows (PowerShell)
```powershell
# Compile the Java file
javac HelloWorld.java

# Run the compiled program
java HelloWorld
```

## Key Concepts

### Java Program Structure
- **Class**: Every Java program must have at least one class
- **Main Method**: Required entry point for executable programs
- **Access Modifiers**: `public` makes the class accessible from anywhere
- **Static Method**: `main` must be static to be called without creating an object

### Java Naming Conventions
- **Class Names**: Use PascalCase (e.g., `HelloWorld`, `StudentManager`)
- **Method Names**: Use camelCase (e.g., `calculateGrade`, `printMessage`)
- **Variable Names**: Use camelCase (e.g., `studentName`, `totalAmount`)
- **Constants**: Use UPPER_SNAKE_CASE (e.g., `MAX_SIZE`, `DEFAULT_VALUE`)

### Compilation Process
1. **Source Code**: `.java` files contain human-readable code
2. **Compilation**: `javac` compiles source code to bytecode
3. **Bytecode**: `.class` files contain platform-independent bytecode
4. **Execution**: `java` runs the bytecode on the Java Virtual Machine (JVM)

## Common Pitfalls

1. **File Name Mismatch**: Public class name must match the file name exactly
2. **Case Sensitivity**: Java is case-sensitive (`main` ≠ `Main`)
3. **Missing Main Method**: Every executable Java program needs a main method
4. **Incorrect Syntax**: Missing semicolons, braces, or parentheses cause compilation errors

## Variations

### Enhanced Hello World with Variables
```java
public class HelloWorld {
    public static void main(String[] args) {
        // Declare and initialize variables
        String greeting = "Hello";
        String language = "Java";
        
        // Print using variables
        System.out.println(greeting + " World from " + language + "!");
    }
}
```

### Hello World with User Input
```java
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        // Create Scanner object for input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        // Print personalized greeting
        System.out.println("Hello " + name + "! Welcome to Java programming!");
        
        // Close scanner
        scanner.close();
    }
}
```

### Hello World with Command Line Arguments
```java
public class HelloWorld {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("Hello " + args[0] + " from Java!");
        } else {
            System.out.println("Hello World from Java!");
            System.out.println("Usage: java HelloWorld <name>");
        }
    }
}
```

### Hello World with Multiple Methods
```java
public class HelloWorld {
    public static void main(String[] args) {
        printGreeting();
        printLanguageInfo();
    }
    
    /**
     * Prints a basic greeting message
     */
    public static void printGreeting() {
        System.out.println("Hello World from Java!");
    }
    
    /**
     * Prints information about the Java language
     */
    public static void printLanguageInfo() {
        System.out.println("Java is a powerful, object-oriented programming language.");
        System.out.println("It's platform-independent and widely used in enterprise applications.");
    }
}
```

## Advanced Features

### Using System Properties
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World from Java!");
        
        // Display system information
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("User Name: " + System.getProperty("user.name"));
    }
}
```

### Hello World with Date and Time
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World from Java!");
        
        // Display current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time: " + now.format(formatter));
    }
}
```

### Hello World with Error Handling
```java
public class HelloWorld {
    public static void main(String[] args) {
        try {
            System.out.println("Hello World from Java!");
            
            // Simulate some processing
            Thread.sleep(1000); // Sleep for 1 second
            
            System.out.println("Program completed successfully!");
            
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

## Environment Setup

### Verify Java Installation
```bash
# Check Java version
java -version

# Check Java compiler version
javac -version

# Check JAVA_HOME environment variable
echo $JAVA_HOME  # Linux/macOS
echo %JAVA_HOME% # Windows
```

### Setting Up Environment Variables

#### Linux/macOS
```bash
# Add to ~/.bashrc or ~/.zshrc
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin
```

#### Windows
```cmd
# Set environment variables
set JAVA_HOME=C:\Program Files\Java\jdk-11
set PATH=%PATH%;%JAVA_HOME%\bin
```

#### Windows PowerShell
```powershell
# Set environment variables
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"
$env:PATH += ";$env:JAVA_HOME\bin"
```

## Troubleshooting

### Common Compilation Errors
```java
// Error: class HelloWorld is public, should be declared in a file named HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

### Common Runtime Errors
```bash
# Error: Could not find or load main class HelloWorld
# Solution: Make sure you're in the correct directory and the .class file exists

# Error: 'javac' is not recognized as an internal or external command
# Solution: Add Java bin directory to your PATH environment variable
```

## Best Practices

1. **Always use meaningful class names** that describe the program's purpose
2. **Include proper documentation** using JavaDoc comments
3. **Follow naming conventions** consistently throughout your code
4. **Use proper indentation** to make code readable
5. **Handle exceptions** appropriately when dealing with I/O operations
6. **Close resources** like Scanner objects to prevent memory leaks

## Exercises

1. Create a program that prints your name and the current date
2. Write a program that takes a command line argument and prints a personalized greeting
3. Create a program that displays system information (Java version, OS, etc.)
4. Write a program that prints multiple lines of text using different methods
5. Create a program that demonstrates different types of comments (single-line, multi-line, JavaDoc)

## Learning Outcomes
- Understanding the basic structure of a Java program
- Learning how to compile and execute Java programs
- Understanding Java naming conventions and best practices
- Working with the main method and command line arguments
- Cross-platform Java development setup
- Basic error handling and troubleshooting
