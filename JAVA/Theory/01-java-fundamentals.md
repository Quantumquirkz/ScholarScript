# Java Fundamentals

## Overview
Java is a high-level, object-oriented programming language designed to be platform-independent through the "Write Once, Run Anywhere" (WORA) principle. This section covers the fundamental concepts of Java programming.

## Key Concepts

### What is Java?
Java is a general-purpose programming language that is:
- **Object-oriented**: Everything in Java is an object
- **Platform-independent**: Compiled to bytecode that runs on the Java Virtual Machine (JVM)
- **Secure**: Built-in security features and sandboxing
- **Multithreaded**: Native support for concurrent programming
- **Interpreted**: Bytecode is interpreted by the JVM

### Java Architecture
```
Source Code (.java) → Compiler (javac) → Bytecode (.class) → JVM → Machine Code
```

## Java Development Environment

### JDK vs JRE vs JVM
- **JDK (Java Development Kit)**: Complete development environment including compiler, debugger, and tools
- **JRE (Java Runtime Environment)**: Runtime environment needed to run Java applications
- **JVM (Java Virtual Machine)**: Virtual machine that executes Java bytecode

### Installation and Setup

#### Linux (Ubuntu/Debian)
```bash
# Update package list
sudo apt update

# Install OpenJDK (recommended)
sudo apt install openjdk-17-jdk

# Verify installation
java -version
javac -version

# Set JAVA_HOME environment variable
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc
```

#### Linux (CentOS/RHEL/Fedora)
```bash
# Install OpenJDK
sudo yum install java-17-openjdk-devel
# or for newer versions
sudo dnf install java-17-openjdk-devel

# Verify installation
java -version
javac -version

# Set JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk' >> ~/.bashrc
source ~/.bashrc
```

#### Windows
1. Download Oracle JDK or OpenJDK from:
   - [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
   - [OpenJDK](https://openjdk.java.net/)
2. Run the installer and follow the setup wizard
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Environment Variables → New System Variable
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-17` (or your JDK path)
4. Add to PATH: `%JAVA_HOME%\bin`

#### macOS
```bash
# Using Homebrew (recommended)
brew install openjdk@17

# Link the installed JDK
sudo ln -sfn /usr/local/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk

# Verify installation
java -version
javac -version

# Set JAVA_HOME
echo 'export JAVA_HOME=/usr/local/opt/openjdk@17' >> ~/.zshrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc
```

## Basic Java Program Structure

### Hello World Program
```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Compilation and Execution
```bash
# Compile
javac HelloWorld.java

# Run
java HelloWorld

# Output: Hello, World!
```

### Program Structure Explanation
```java
// Class declaration
public class ClassName {
    // Fields (variables)
    private int fieldName;
    
    // Constructor
    public ClassName() {
        // Constructor body
    }
    
    // Methods
    public void methodName() {
        // Method body
    }
    
    // Main method (entry point)
    public static void main(String[] args) {
        // Program execution starts here
    }
}
```

## Variables and Data Types

### Primitive Data Types
```java
public class DataTypes {
    public static void main(String[] args) {
        // Integer types
        byte byteValue = 127;           // 8-bit, -128 to 127
        short shortValue = 32767;       // 16-bit, -32,768 to 32,767
        int intValue = 2147483647;      // 32-bit, -2^31 to 2^31-1
        long longValue = 9223372036854775807L; // 64-bit, -2^63 to 2^63-1
        
        // Floating-point types
        float floatValue = 3.14f;       // 32-bit, single precision
        double doubleValue = 3.14159265359; // 64-bit, double precision
        
        // Character type
        char charValue = 'A';           // 16-bit Unicode character
        
        // Boolean type
        boolean booleanValue = true;    // true or false
        
        // Display values
        System.out.println("Byte: " + byteValue);
        System.out.println("Short: " + shortValue);
        System.out.println("Int: " + intValue);
        System.out.println("Long: " + longValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Char: " + charValue);
        System.out.println("Boolean: " + booleanValue);
    }
}
```

### Reference Data Types
```java
public class ReferenceTypes {
    public static void main(String[] args) {
        // String (immutable)
        String name = "John Doe";
        String greeting = new String("Hello");
        
        // Arrays
        int[] numbers = {1, 2, 3, 4, 5};
        String[] names = new String[3];
        names[0] = "Alice";
        names[1] = "Bob";
        names[2] = "Charlie";
        
        // Objects
        Person person = new Person("Jane", 25);
        
        System.out.println("Name: " + name);
        System.out.println("Numbers: " + java.util.Arrays.toString(numbers));
        System.out.println("Person: " + person.getName() + ", Age: " + person.getAge());
    }
}

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
}
```

### Variable Scope and Lifetime
```java
public class VariableScope {
    // Instance variable
    private int instanceVar = 10;
    
    // Static variable
    private static int staticVar = 20;
    
    public void method() {
        // Local variable
        int localVar = 30;
        
        // Block scope
        {
            int blockVar = 40;
            System.out.println("Block variable: " + blockVar);
        }
        // blockVar is not accessible here
        
        System.out.println("Local variable: " + localVar);
        System.out.println("Instance variable: " + instanceVar);
        System.out.println("Static variable: " + staticVar);
    }
    
    public static void main(String[] args) {
        VariableScope obj = new VariableScope();
        obj.method();
    }
}
```

## Operators

### Arithmetic Operators
```java
public class ArithmeticOperators {
    public static void main(String[] args) {
        int a = 10, b = 3;
        
        // Basic arithmetic
        System.out.println("Addition: " + (a + b));        // 13
        System.out.println("Subtraction: " + (a - b));     // 7
        System.out.println("Multiplication: " + (a * b));  // 30
        System.out.println("Division: " + (a / b));        // 3
        System.out.println("Modulus: " + (a % b));         // 1
        
        // Increment and decrement
        int x = 5;
        System.out.println("Original x: " + x);            // 5
        System.out.println("Pre-increment: " + (++x));     // 6
        System.out.println("Post-increment: " + (x++));    // 6, then x becomes 7
        System.out.println("Final x: " + x);               // 7
        
        // Compound assignment
        int y = 10;
        y += 5;  // y = y + 5
        System.out.println("y += 5: " + y);                // 15
        
        y *= 2;  // y = y * 2
        System.out.println("y *= 2: " + y);                // 30
    }
}
```

### Comparison and Logical Operators
```java
public class ComparisonLogicalOperators {
    public static void main(String[] args) {
        int a = 10, b = 20, c = 10;
        
        // Comparison operators
        System.out.println("a == c: " + (a == c));         // true
        System.out.println("a != b: " + (a != b));         // true
        System.out.println("a < b: " + (a < b));           // true
        System.out.println("a > b: " + (a > b));           // false
        System.out.println("a <= c: " + (a <= c));         // true
        System.out.println("a >= b: " + (a >= b));         // false
        
        // Logical operators
        boolean x = true, y = false;
        System.out.println("x && y: " + (x && y));         // false
        System.out.println("x || y: " + (x || y));         // true
        System.out.println("!x: " + (!x));                 // false
        
        // Short-circuit evaluation
        System.out.println("false && (5/0 == 0): " + (false && (5/0 == 0))); // false (no error)
    }
}
```

### Bitwise Operators
```java
public class BitwiseOperators {
    public static void main(String[] args) {
        int a = 12;  // 1100 in binary
        int b = 10;  // 1010 in binary
        
        System.out.println("a: " + Integer.toBinaryString(a));  // 1100
        System.out.println("b: " + Integer.toBinaryString(b));  // 1010
        
        // Bitwise AND
        System.out.println("a & b: " + (a & b));               // 8 (1000)
        
        // Bitwise OR
        System.out.println("a | b: " + (a | b));               // 14 (1110)
        
        // Bitwise XOR
        System.out.println("a ^ b: " + (a ^ b));               // 6 (0110)
        
        // Bitwise NOT
        System.out.println("~a: " + (~a));                     // -13
        
        // Left shift
        System.out.println("a << 2: " + (a << 2));             // 48 (110000)
        
        // Right shift
        System.out.println("a >> 2: " + (a >> 2));             // 3 (11)
        
        // Unsigned right shift
        System.out.println("a >>> 2: " + (a >>> 2));           // 3
    }
}
```

## Control Structures

### Conditional Statements
```java
public class ConditionalStatements {
    public static void main(String[] args) {
        int score = 85;
        
        // if-else statement
        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else if (score >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
        
        // Ternary operator
        String result = (score >= 60) ? "Pass" : "Fail";
        System.out.println("Result: " + result);
        
        // Switch statement
        char grade = 'B';
        switch (grade) {
            case 'A':
                System.out.println("Excellent!");
                break;
            case 'B':
                System.out.println("Good job!");
                break;
            case 'C':
                System.out.println("Satisfactory");
                break;
            case 'D':
                System.out.println("Needs improvement");
                break;
            case 'F':
                System.out.println("Failed");
                break;
            default:
                System.out.println("Invalid grade");
        }
        
        // Switch expression (Java 14+)
        String message = switch (grade) {
            case 'A' -> "Excellent!";
            case 'B' -> "Good job!";
            case 'C' -> "Satisfactory";
            case 'D' -> "Needs improvement";
            case 'F' -> "Failed";
            default -> "Invalid grade";
        };
        System.out.println("Message: " + message);
    }
}
```

### Loop Structures
```java
public class LoopStructures {
    public static void main(String[] args) {
        // for loop
        System.out.println("For loop:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }
        
        // Enhanced for loop (for-each)
        System.out.println("\nEnhanced for loop:");
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println("Number: " + num);
        }
        
        // while loop
        System.out.println("\nWhile loop:");
        int count = 1;
        while (count <= 3) {
            System.out.println("Count: " + count);
            count++;
        }
        
        // do-while loop
        System.out.println("\nDo-while loop:");
        int value = 1;
        do {
            System.out.println("Value: " + value);
            value++;
        } while (value <= 3);
        
        // Nested loops
        System.out.println("\nNested loops (multiplication table):");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }
        
        // Loop control statements
        System.out.println("\nLoop control:");
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                continue; // Skip iteration
            }
            if (i == 7) {
                break; // Exit loop
            }
            System.out.println("i: " + i);
        }
    }
}
```

## Arrays

### Array Declaration and Initialization
```java
public class ArrayBasics {
    public static void main(String[] args) {
        // Array declaration and initialization
        int[] numbers1 = {1, 2, 3, 4, 5};
        int[] numbers2 = new int[5];
        int[] numbers3 = new int[]{10, 20, 30, 40, 50};
        
        // Initialize array elements
        for (int i = 0; i < numbers2.length; i++) {
            numbers2[i] = (i + 1) * 10;
        }
        
        // Display arrays
        System.out.println("numbers1: " + java.util.Arrays.toString(numbers1));
        System.out.println("numbers2: " + java.util.Arrays.toString(numbers2));
        System.out.println("numbers3: " + java.util.Arrays.toString(numbers3));
        
        // Array properties
        System.out.println("Array length: " + numbers1.length);
        System.out.println("First element: " + numbers1[0]);
        System.out.println("Last element: " + numbers1[numbers1.length - 1]);
        
        // Multi-dimensional arrays
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("\nMatrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Jagged arrays
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1, 2};
        jagged[1] = new int[]{3, 4, 5};
        jagged[2] = new int[]{6};
        
        System.out.println("\nJagged array:");
        for (int i = 0; i < jagged.length; i++) {
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.print(jagged[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```

### Array Operations
```java
public class ArrayOperations {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1, 9, 3};
        
        // Find maximum and minimum
        int max = numbers[0];
        int min = numbers[0];
        for (int num : numbers) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
        
        // Calculate sum and average
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = (double) sum / numbers.length;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        
        // Linear search
        int target = 8;
        int index = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                index = i;
                break;
            }
        }
        System.out.println("Search for " + target + ": " + 
                          (index != -1 ? "Found at index " + index : "Not found"));
        
        // Reverse array
        System.out.println("Original: " + java.util.Arrays.toString(numbers));
        for (int i = 0; i < numbers.length / 2; i++) {
            int temp = numbers[i];
            numbers[i] = numbers[numbers.length - 1 - i];
            numbers[numbers.length - 1 - i] = temp;
        }
        System.out.println("Reversed: " + java.util.Arrays.toString(numbers));
        
        // Sort array (using built-in method)
        java.util.Arrays.sort(numbers);
        System.out.println("Sorted: " + java.util.Arrays.toString(numbers));
        
        // Binary search (requires sorted array)
        int searchTarget = 5;
        int binaryIndex = java.util.Arrays.binarySearch(numbers, searchTarget);
        System.out.println("Binary search for " + searchTarget + ": " + 
                          (binaryIndex >= 0 ? "Found at index " + binaryIndex : "Not found"));
    }
}
```

## Cross-Platform Development

### Build Scripts

#### Linux/macOS Build Script
```bash
#!/bin/bash
# build.sh

echo "Building Java project..."

# Compile all Java files
javac -d bin -cp src src/**/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Run the main class
    java -cp bin MainClass
    
    echo "Execution completed!"
else
    echo "Compilation failed!"
    exit 1
fi
```

#### Windows Build Script
```batch
@echo off
REM build.bat

echo Building Java project...

REM Compile all Java files
javac -d bin -cp src src\**\*.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    
    REM Run the main class
    java -cp bin MainClass
    
    echo Execution completed!
) else (
    echo Compilation failed!
    exit /b 1
)
```

### Project Structure
```
MyJavaProject/
├── src/
│   ├── com/
│   │   └── company/
│   │       └── project/
│   │           ├── Main.java
│   │           ├── models/
│   │           │   └── Person.java
│   │           └── utils/
│   │               └── Calculator.java
├── bin/
├── lib/
├── docs/
├── build.sh (Linux/macOS)
├── build.bat (Windows)
└── README.md
```

## Best Practices

### Code Style
```java
// Use meaningful names
public class BankAccount {
    private String accountNumber;
    private double balance;
    
    // Use camelCase for variables and methods
    public void depositMoney(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }
    
    // Use PascalCase for classes
    public double getBalance() {
        return balance;
    }
}
```

### Documentation
```java
/**
 * Represents a bank account with basic operations
 * 
 * @author John Doe
 * @version 1.0
 * @since 2024-01-01
 */
public class BankAccount {
    private String accountNumber;
    private double balance;
    
    /**
     * Creates a new bank account
     * 
     * @param accountNumber the unique account number
     * @param initialBalance the initial balance
     */
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    /**
     * Deposits money into the account
     * 
     * @param amount the amount to deposit
     * @return true if deposit successful, false otherwise
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}
```

## Common Pitfalls and Solutions

### Memory Management
```java
public class MemoryManagement {
    public static void main(String[] args) {
        // Good: Use StringBuilder for string concatenation in loops
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("Item ").append(i).append("\n");
        }
        String result = sb.toString();
        
        // Bad: String concatenation in loops (creates many temporary objects)
        // String result = "";
        // for (int i = 0; i < 1000; i++) {
        //     result += "Item " + i + "\n";
        // }
    }
}
```

### Null Pointer Prevention
```java
public class NullSafety {
    public static void main(String[] args) {
        String name = null;
        
        // Safe null check
        if (name != null && name.length() > 0) {
            System.out.println("Name: " + name);
        } else {
            System.out.println("Name is null or empty");
        }
        
        // Using Optional (Java 8+)
        java.util.Optional<String> optionalName = java.util.Optional.ofNullable(name);
        optionalName.ifPresent(n -> System.out.println("Name: " + n));
    }
}
```

## Learning Outcomes
- Understanding Java fundamentals and architecture
- Learning basic programming constructs and syntax
- Understanding data types, operators, and control structures
- Learning array operations and manipulation
- Cross-platform Java development setup
- Best practices for Java programming
