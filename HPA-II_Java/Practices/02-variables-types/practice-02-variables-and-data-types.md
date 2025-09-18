# Practice 02: Variables and Data Types in Java

## Objective
Learn about Java variables, data types, and how to declare, initialize, and use them in programs. Understand the differences between primitive and reference data types.

## Problem Statement
Create a program that demonstrates various Java data types, variable declarations, and basic operations.

### Requirements
- Declare variables of different primitive data types
- Initialize variables with appropriate values
- Perform basic operations with variables
- Display variable values and their properties

### Example Output
```
Integer: 42
Double: 3.14159
Character: A
Boolean: true
String: Hello Java!
```

## Solution

### Complete Code
```java
/**
 * VariablesAndTypes class demonstrates Java data types and variable usage
 */
public class VariablesAndTypes {
    
    public static void main(String[] args) {
        // Integer types
        byte byteValue = 127;           // 8-bit signed integer (-128 to 127)
        short shortValue = 32000;       // 16-bit signed integer (-32,768 to 32,767)
        int intValue = 2147483647;      // 32-bit signed integer (-2^31 to 2^31-1)
        long longValue = 9223372036854775807L; // 64-bit signed integer
        
        // Floating-point types
        float floatValue = 3.14f;       // 32-bit floating-point
        double doubleValue = 3.141592653589793; // 64-bit floating-point
        
        // Character type
        char charValue = 'A';           // 16-bit Unicode character
        
        // Boolean type
        boolean boolValue = true;       // true or false
        
        // Reference type (String)
        String stringValue = "Hello Java!";
        
        // Display all values
        System.out.println("=== Primitive Data Types ===");
        System.out.println("Byte: " + byteValue);
        System.out.println("Short: " + shortValue);
        System.out.println("Int: " + intValue);
        System.out.println("Long: " + longValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Char: " + charValue);
        System.out.println("Boolean: " + boolValue);
        System.out.println("String: " + stringValue);
        
        // Demonstrate operations
        demonstrateOperations();
    }
    
    /**
     * Demonstrates various operations with different data types
     */
    public static void demonstrateOperations() {
        System.out.println("\n=== Operations ===");
        
        // Arithmetic operations
        int a = 10, b = 3;
        System.out.println("Arithmetic Operations:");
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));
        
        // Type conversion
        System.out.println("\nType Conversion:");
        double result = (double) a / b; // Explicit casting
        System.out.println("Division with casting: " + result);
        
        // String operations
        System.out.println("\nString Operations:");
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        System.out.println("Full name: " + fullName);
        System.out.println("Name length: " + fullName.length());
    }
}
```

### Code Explanation
1. **Variable Declaration**: `type variableName = value;`
2. **Primitive Types**: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`
3. **Reference Types**: `String` (and other objects)
4. **Type Conversion**: Implicit and explicit casting
5. **Operations**: Arithmetic, string concatenation, and other operations

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
javac VariablesAndTypes.java
java VariablesAndTypes
```

### Windows (Command Prompt)
```cmd
javac VariablesAndTypes.java
java VariablesAndTypes
```

### Windows (PowerShell)
```powershell
javac VariablesAndTypes.java
java VariablesAndTypes
```

## Key Concepts

### Primitive Data Types

#### Integer Types
```java
public class IntegerTypesDemo {
    public static void main(String[] args) {
        // Different integer types with their ranges
        byte smallest = -128;           // 8-bit: -128 to 127
        short small = -32768;           // 16-bit: -32,768 to 32,767
        int medium = -2147483648;       // 32-bit: -2^31 to 2^31-1
        long large = -9223372036854775808L; // 64-bit: -2^63 to 2^63-1
        
        System.out.println("Byte range: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Short range: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Int range: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Long range: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
    }
}
```

#### Floating-Point Types
```java
public class FloatingPointDemo {
    public static void main(String[] args) {
        float floatValue = 3.14159f;    // 32-bit floating-point
        double doubleValue = 3.141592653589793; // 64-bit floating-point
        
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Float precision: " + Float.MAX_VALUE);
        System.out.println("Double precision: " + Double.MAX_VALUE);
        
        // Scientific notation
        double scientific = 1.23e4;     // 1.23 × 10^4 = 12300
        System.out.println("Scientific notation: " + scientific);
    }
}
```

#### Character and Boolean Types
```java
public class CharBooleanDemo {
    public static void main(String[] args) {
        // Character type
        char letter = 'A';
        char unicode = '\u0041';        // Unicode for 'A'
        char number = '5';
        
        System.out.println("Character: " + letter);
        System.out.println("Unicode: " + unicode);
        System.out.println("Number as char: " + number);
        System.out.println("ASCII value: " + (int) letter);
        
        // Boolean type
        boolean isTrue = true;
        boolean isFalse = false;
        boolean result = 5 > 3;
        
        System.out.println("Boolean true: " + isTrue);
        System.out.println("Boolean false: " + isFalse);
        System.out.println("Comparison result: " + result);
    }
}
```

### Variable Declaration and Initialization

#### Different Ways to Declare Variables
```java
public class VariableDeclarationDemo {
    public static void main(String[] args) {
        // Method 1: Declaration and initialization on separate lines
        int number;
        number = 42;
        
        // Method 2: Declaration and initialization on same line
        String name = "Alice";
        
        // Method 3: Multiple variables of same type
        int x = 10, y = 20, z = 30;
        
        // Method 4: Constants (final keyword)
        final double PI = 3.14159;
        final String COMPANY_NAME = "TechCorp";
        
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
        System.out.println("Coordinates: (" + x + ", " + y + ", " + z + ")");
        System.out.println("PI: " + PI);
        System.out.println("Company: " + COMPANY_NAME);
        
        // PI = 3.14; // This would cause a compilation error
    }
}
```

### Type Conversion and Casting

#### Implicit and Explicit Casting
```java
public class TypeConversionDemo {
    public static void main(String[] args) {
        // Implicit casting (widening conversion)
        byte b = 100;
        short s = b;        // byte to short
        int i = s;          // short to int
        long l = i;         // int to long
        float f = l;        // long to float
        double d = f;       // float to double
        
        System.out.println("Implicit casting: byte(" + b + ") -> double(" + d + ")");
        
        // Explicit casting (narrowing conversion)
        double doubleValue = 3.14159;
        float floatValue = (float) doubleValue;    // double to float
        long longValue = (long) floatValue;        // float to long
        int intValue = (int) longValue;            // long to int
        short shortValue = (short) intValue;       // int to short
        byte byteValue = (byte) shortValue;        // short to byte
        
        System.out.println("Explicit casting: double(" + doubleValue + ") -> byte(" + byteValue + ")");
        
        // String conversion
        int number = 42;
        String numberString = String.valueOf(number);
        String concatenated = "The number is " + number; // Automatic string conversion
        
        System.out.println("String conversion: " + numberString);
        System.out.println("Concatenation: " + concatenated);
    }
}
```

## Common Pitfalls

1. **Uninitialized Variables**: Local variables must be initialized before use
2. **Type Mismatch**: Cannot assign incompatible types without casting
3. **Overflow**: Integer operations can overflow beyond type limits
4. **Precision Loss**: Floating-point arithmetic may have precision issues
5. **Case Sensitivity**: Variable names are case-sensitive

## Variations

### Enhanced Variables Demo with Input
```java
import java.util.Scanner;

public class VariablesWithInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Variable Input Demo ===");
        
        // Get integer input
        System.out.print("Enter an integer: ");
        int intValue = scanner.nextInt();
        
        // Get double input
        System.out.print("Enter a decimal number: ");
        double doubleValue = scanner.nextDouble();
        
        // Get string input
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        
        // Get character input
        System.out.print("Enter a character: ");
        char charValue = scanner.next().charAt(0);
        
        // Display results
        System.out.println("\n=== Results ===");
        System.out.println("Integer: " + intValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Name: " + name);
        System.out.println("Character: " + charValue);
        
        scanner.close();
    }
}
```

### Variables with Mathematical Operations
```java
public class MathematicalOperations {
    public static void main(String[] args) {
        // Declare and initialize variables
        double radius = 5.0;
        final double PI = 3.14159;
        
        // Calculate circle properties
        double circumference = 2 * PI * radius;
        double area = PI * radius * radius;
        
        // Display results
        System.out.println("Circle with radius " + radius + ":");
        System.out.println("Circumference: " + circumference);
        System.out.println("Area: " + area);
        
        // Temperature conversion
        double celsius = 25.0;
        double fahrenheit = (celsius * 9.0 / 5.0) + 32.0;
        
        System.out.println("\nTemperature Conversion:");
        System.out.println("Celsius: " + celsius + "°C");
        System.out.println("Fahrenheit: " + fahrenheit + "°F");
    }
}
```

### Variables with String Operations
```java
public class StringOperations {
    public static void main(String[] args) {
        // String variables
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        
        // String concatenation
        String fullName = firstName + " " + lastName;
        
        // String methods
        int nameLength = fullName.length();
        String upperCase = fullName.toUpperCase();
        String lowerCase = fullName.toLowerCase();
        boolean containsJohn = fullName.contains("John");
        
        // Display results
        System.out.println("Full name: " + fullName);
        System.out.println("Name length: " + nameLength);
        System.out.println("Uppercase: " + upperCase);
        System.out.println("Lowercase: " + lowerCase);
        System.out.println("Contains 'John': " + containsJohn);
        
        // String formatting
        String formatted = String.format("Name: %s, Email: %s", fullName, email);
        System.out.println("Formatted: " + formatted);
    }
}
```

## Advanced Features

### Wrapper Classes
```java
public class WrapperClassesDemo {
    public static void main(String[] args) {
        // Primitive types
        int primitiveInt = 42;
        double primitiveDouble = 3.14;
        boolean primitiveBoolean = true;
        
        // Wrapper classes
        Integer wrapperInt = Integer.valueOf(42);
        Double wrapperDouble = Double.valueOf(3.14);
        Boolean wrapperBoolean = Boolean.valueOf(true);
        
        // Autoboxing and unboxing
        Integer autoBoxed = 42;         // Autoboxing
        int autoUnboxed = wrapperInt;   // Unboxing
        
        // Wrapper class methods
        System.out.println("Integer max value: " + Integer.MAX_VALUE);
        System.out.println("Double min value: " + Double.MIN_VALUE);
        System.out.println("Parse string to int: " + Integer.parseInt("123"));
        System.out.println("Convert to string: " + wrapperInt.toString());
        
        // Null handling
        Integer nullableInt = null;
        // int nullPrimitive = nullableInt; // This would throw NullPointerException
    }
}
```

### Variable Scope
```java
public class VariableScopeDemo {
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

## Best Practices

1. **Use meaningful variable names** that describe their purpose
2. **Initialize variables** when declaring them
3. **Use final for constants** that won't change
4. **Choose appropriate data types** for your needs
5. **Be aware of type conversion** and potential data loss
6. **Use wrapper classes** when you need object features

## Exercises

1. Create a program that calculates the area and perimeter of a rectangle
2. Write a program that converts temperature between Celsius and Fahrenheit
3. Create a program that calculates compound interest
4. Write a program that demonstrates different ways of string concatenation
5. Create a program that shows the difference between primitive and wrapper types

## Learning Outcomes
- Understanding Java primitive and reference data types
- Learning variable declaration and initialization
- Understanding type conversion and casting
- Working with constants and variable scope
- Cross-platform Java development
- Best practices for variable naming and usage
