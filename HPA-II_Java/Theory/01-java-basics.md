# Java Basics and Program Structure

## Overview
Java is an object-oriented programming language designed to be platform-independent. This module covers the fundamental concepts of Java programming including program structure, data types, variables, and basic input/output operations.

## Key Concepts

### Java Program Structure
Every Java program must have at least one class with a `main` method as the entry point:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Class Declaration
```java
public class ClassName {
    // Class body
}
```

### Main Method
```java
public static void main(String[] args) {
    // Program entry point
}
```

## Data Types

### Primitive Data Types
```java
public class DataTypesExample {
    public static void main(String[] args) {
        // Integer types
        byte byteValue = 127;           // 8-bit signed integer
        short shortValue = 32767;       // 16-bit signed integer
        int intValue = 2147483647;      // 32-bit signed integer
        long longValue = 9223372036854775807L; // 64-bit signed integer
        
        // Floating-point types
        float floatValue = 3.14f;       // 32-bit floating-point
        double doubleValue = 3.14159;   // 64-bit floating-point
        
        // Character type
        char charValue = 'A';           // 16-bit Unicode character
        
        // Boolean type
        boolean boolValue = true;       // true or false
        
        // Display values
        System.out.println("Byte: " + byteValue);
        System.out.println("Short: " + shortValue);
        System.out.println("Int: " + intValue);
        System.out.println("Long: " + longValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Char: " + charValue);
        System.out.println("Boolean: " + boolValue);
    }
}
```

### Reference Data Types
```java
public class ReferenceTypesExample {
    public static void main(String[] args) {
        // String
        String name = "John Doe";
        
        // Arrays
        int[] numbers = {1, 2, 3, 4, 5};
        
        // Objects
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Name: " + name);
        System.out.println("Numbers: " + Arrays.toString(numbers));
    }
}
```

## Variables and Constants

### Variable Declaration and Initialization
```java
public class VariablesExample {
    public static void main(String[] args) {
        // Declaration and initialization
        int age = 25;
        String name = "Alice";
        
        // Declaration first, then assignment
        double height;
        height = 1.75;
        
        // Multiple variables of same type
        int x = 10, y = 20, z = 30;
        
        // Constants (final keyword)
        final double PI = 3.14159;
        final String COMPANY_NAME = "TechCorp";
        
        System.out.println("Age: " + age);
        System.out.println("Name: " + name);
        System.out.println("Height: " + height);
        System.out.println("PI: " + PI);
    }
}
```

### Variable Scope
```java
public class ScopeExample {
    private static int classVariable = 100; // Class-level variable
    
    public static void main(String[] args) {
        int localVariable = 50; // Method-level variable
        
        if (true) {
            int blockVariable = 25; // Block-level variable
            System.out.println("Block variable: " + blockVariable);
            System.out.println("Local variable: " + localVariable);
            System.out.println("Class variable: " + classVariable);
        }
        
        // blockVariable is not accessible here
        System.out.println("Local variable: " + localVariable);
        System.out.println("Class variable: " + classVariable);
    }
}
```

## Input and Output

### Basic Output
```java
public class OutputExample {
    public static void main(String[] args) {
        // System.out.println() - prints with newline
        System.out.println("Hello, World!");
        System.out.println("This is a new line");
        
        // System.out.print() - prints without newline
        System.out.print("This ");
        System.out.print("stays ");
        System.out.print("on the same line");
        System.out.println(); // Add newline
        
        // System.out.printf() - formatted output
        String name = "John";
        int age = 25;
        double height = 1.75;
        
        System.out.printf("Name: %s, Age: %d, Height: %.2f%n", name, age, height);
    }
}
```

### Basic Input with Scanner
```java
import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Reading different data types
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        System.out.print("Enter your height: ");
        double height = scanner.nextDouble();
        
        System.out.print("Enter your grade: ");
        char grade = scanner.next().charAt(0);
        
        // Display input
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
}
```

## Operators

### Arithmetic Operators
```java
public class ArithmeticOperators {
    public static void main(String[] args) {
        int a = 10, b = 3;
        
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));
        
        // Increment and decrement
        int x = 5;
        System.out.println("x = " + x);
        System.out.println("++x = " + (++x)); // Pre-increment
        System.out.println("x++ = " + (x++)); // Post-increment
        System.out.println("x = " + x);
    }
}
```

### Comparison and Logical Operators
```java
public class ComparisonOperators {
    public static void main(String[] args) {
        int a = 10, b = 5;
        
        // Comparison operators
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        
        // Logical operators
        boolean x = true, y = false;
        System.out.println("x && y: " + (x && y)); // AND
        System.out.println("x || y: " + (x || y)); // OR
        System.out.println("!x: " + (!x));         // NOT
    }
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
javac HelloWorld.java
java HelloWorld

# Windows (Command Prompt)
javac HelloWorld.java
java HelloWorld

# Windows (PowerShell)
javac HelloWorld.java
java HelloWorld
```

### Environment Setup
```bash
# Linux - Ubuntu/Debian
sudo apt update
sudo apt install openjdk-11-jdk

# Linux - CentOS/RHEL
sudo yum install java-11-openjdk-devel

# Set environment variables
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin
```

```cmd
# Windows
# Download and install JDK from Oracle or OpenJDK
# Set environment variables:
set JAVA_HOME=C:\Program Files\Java\jdk-11
set PATH=%PATH%;%JAVA_HOME%\bin
```

```powershell
# Windows PowerShell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"
$env:PATH += ";$env:JAVA_HOME\bin"
```

## Common Pitfalls

1. **Case Sensitivity**: Java is case-sensitive (`main` vs `Main`)
2. **File Name**: Public class name must match file name
3. **Semicolons**: All statements must end with semicolons
4. **Variable Initialization**: Local variables must be initialized before use
5. **Scanner Resource**: Always close Scanner to avoid resource leaks

## Best Practices

1. **Naming Conventions**: Use camelCase for variables and methods
2. **Class Names**: Use PascalCase for class names
3. **Constants**: Use UPPER_SNAKE_CASE for constants
4. **Comments**: Use meaningful comments to explain code
5. **Resource Management**: Always close resources like Scanner

## Memory Management

### Garbage Collection
```java
public class MemoryExample {
    public static void main(String[] args) {
        // Objects are automatically garbage collected
        String temp = new String("Temporary");
        temp = null; // Object becomes eligible for garbage collection
        
        // Suggest garbage collection (not guaranteed)
        System.gc();
    }
}
```

## Exercises

1. Create a program that reads user information and displays it
2. Implement a simple calculator with basic arithmetic operations
3. Write a program that converts temperature between Celsius and Fahrenheit
4. Create a program that calculates the area and perimeter of a rectangle
5. Implement a program that determines if a number is even or odd

## Learning Outcomes
- Understanding Java program structure
- Working with primitive and reference data types
- Implementing input/output operations
- Understanding operators and expressions
- Cross-platform Java development
