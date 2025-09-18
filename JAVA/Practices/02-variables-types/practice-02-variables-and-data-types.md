# Practice 02: Variables and Data Types

## Objective
Learn about Java variables, primitive data types, reference types, and understand variable scope and initialization in Java.

## Problem Statement
Create a comprehensive program that demonstrates all Java data types, variable declarations, type conversions, and operations with different data types.

### Requirements
- Declare variables of all primitive data types
- Demonstrate string operations and methods
- Show type conversion (implicit and explicit)
- Create arrays of different types
- Handle user input with different data types

### Expected Output
```
=== Java Data Types Demo ===
Integer: 42
Long: 9223372036854775807
Float: 3.14159
Double: 2.718281828459045
Character: A
Boolean: true
String: Hello, Java!

=== Type Conversions ===
Implicit conversion: 42.0
Explicit conversion: 42

=== Array Operations ===
Numbers: [1, 2, 3, 4, 5]
```

## Solution

### Complete Code
```java
import java.util.Scanner;
import java.util.Arrays;

public class VariablesAndDataTypes {
    public static void main(String[] args) {
        System.out.println("=== Java Data Types Demo ===\n");
        
        // Demonstrate primitive data types
        demonstratePrimitiveTypes();
        
        // Demonstrate reference types
        demonstrateReferenceTypes();
        
        // Demonstrate type conversions
        demonstrateTypeConversions();
        
        // Demonstrate arrays
        demonstrateArrays();
        
        // Demonstrate user input
        demonstrateUserInput();
        
        // Demonstrate constants
        demonstrateConstants();
        
        // Demonstrate variable scope
        demonstrateVariableScope();
    }
    
    public static void demonstratePrimitiveTypes() {
        System.out.println("=== Primitive Data Types ===");
        
        // Integer types
        byte byteValue = 127;                    // 8-bit, -128 to 127
        short shortValue = 32767;                // 16-bit, -32,768 to 32,767
        int intValue = 2147483647;               // 32-bit, -2^31 to 2^31-1
        long longValue = 9223372036854775807L;   // 64-bit, -2^63 to 2^63-1
        
        System.out.println("Byte: " + byteValue);
        System.out.println("Short: " + shortValue);
        System.out.println("Int: " + intValue);
        System.out.println("Long: " + longValue);
        
        // Floating-point types
        float floatValue = 3.14159f;             // 32-bit, single precision
        double doubleValue = 2.718281828459045;  // 64-bit, double precision
        
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        
        // Character type
        char charValue = 'A';                    // 16-bit Unicode character
        char unicodeChar = '\u0041';             // Unicode for 'A'
        
        System.out.println("Char: " + charValue);
        System.out.println("Unicode char: " + unicodeChar);
        
        // Boolean type
        boolean booleanValue = true;             // true or false
        
        System.out.println("Boolean: " + booleanValue);
        
        // Demonstrate overflow
        System.out.println("\n=== Overflow Demonstration ===");
        byte maxByte = 127;
        System.out.println("Max byte: " + maxByte);
        maxByte++;
        System.out.println("Max byte + 1 (overflow): " + maxByte);
        
        System.out.println();
    }
    
    public static void demonstrateReferenceTypes() {
        System.out.println("=== Reference Data Types ===");
        
        // String type
        String str1 = "Hello, Java!";           // String literal
        String str2 = new String("Hello, World!"); // String object
        
        System.out.println("String literal: " + str1);
        System.out.println("String object: " + str2);
        
        // String operations
        System.out.println("String length: " + str1.length());
        System.out.println("Uppercase: " + str1.toUpperCase());
        System.out.println("Lowercase: " + str1.toLowerCase());
        System.out.println("Contains 'Java': " + str1.contains("Java"));
        System.out.println("Substring: " + str1.substring(0, 5));
        
        // String concatenation
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        System.out.println("Full name: " + fullName);
        
        // String formatting
        String formattedString = String.format("Name: %s, Age: %d", "Alice", 25);
        System.out.println("Formatted string: " + formattedString);
        
        // StringBuilder for efficient string building
        StringBuilder sb = new StringBuilder();
        sb.append("Building ");
        sb.append("strings ");
        sb.append("efficiently!");
        System.out.println("StringBuilder: " + sb.toString());
        
        System.out.println();
    }
    
    public static void demonstrateTypeConversions() {
        System.out.println("=== Type Conversions ===");
        
        // Implicit conversions (widening)
        int intVal = 42;
        long longVal = intVal;           // int to long
        float floatVal = intVal;         // int to float
        double doubleVal = floatVal;     // float to double
        
        System.out.println("Implicit int to long: " + longVal);
        System.out.println("Implicit int to float: " + floatVal);
        System.out.println("Implicit float to double: " + doubleVal);
        
        // Explicit conversions (narrowing)
        double d = 3.14159;
        int i = (int) d;                 // double to int
        float f = (float) d;             // double to float
        long l = (long) d;               // double to long
        
        System.out.println("Explicit double to int: " + i);
        System.out.println("Explicit double to float: " + f);
        System.out.println("Explicit double to long: " + l);
        
        // String to number conversions
        String numberStr = "123";
        int numberFromString = Integer.parseInt(numberStr);
        double doubleFromString = Double.parseDouble("123.45");
        
        System.out.println("String to int: " + numberFromString);
        System.out.println("String to double: " + doubleFromString);
        
        // Number to string conversions
        int num = 456;
        String numAsString = String.valueOf(num);
        String numAsString2 = Integer.toString(num);
        
        System.out.println("Int to string (valueOf): " + numAsString);
        System.out.println("Int to string (toString): " + numAsString2);
        
        // Character operations
        char ch = 'A';
        int asciiValue = (int) ch;
        char nextChar = (char) (ch + 1);
        
        System.out.println("Char 'A' ASCII value: " + asciiValue);
        System.out.println("Next char after 'A': " + nextChar);
        
        System.out.println();
    }
    
    public static void demonstrateArrays() {
        System.out.println("=== Arrays ===");
        
        // Array declaration and initialization
        int[] numbers = {1, 2, 3, 4, 5};
        String[] names = new String[3];
        names[0] = "Alice";
        names[1] = "Bob";
        names[2] = "Charlie";
        
        // Display arrays
        System.out.println("Numbers array: " + Arrays.toString(numbers));
        System.out.println("Names array: " + Arrays.toString(names));
        
        // Array operations
        System.out.println("Array length: " + numbers.length);
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[numbers.length - 1]);
        
        // Enhanced for loop (for-each)
        System.out.print("Numbers using for-each: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Multi-dimensional arrays
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        
        // Array methods
        int[] unsorted = {5, 2, 8, 1, 9};
        System.out.println("Unsorted: " + Arrays.toString(unsorted));
        
        Arrays.sort(unsorted);
        System.out.println("Sorted: " + Arrays.toString(unsorted));
        
        int index = Arrays.binarySearch(unsorted, 8);
        System.out.println("Index of 8: " + index);
        
        System.out.println();
    }
    
    public static void demonstrateUserInput() {
        System.out.println("=== User Input ===");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        System.out.print("Enter your height (in meters): ");
        double height = scanner.nextDouble();
        
        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean();
        
        System.out.println("\n--- User Information ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + " meters");
        System.out.println("Student: " + isStudent);
        
        // Calculate BMI (assuming weight is 70kg for demo)
        double weight = 70.0;
        double bmi = weight / (height * height);
        System.out.println("BMI: " + String.format("%.2f", bmi));
        
        scanner.close();
        
        System.out.println();
    }
    
    public static void demonstrateConstants() {
        System.out.println("=== Constants ===");
        
        // Final variables (constants)
        final int MAX_SIZE = 100;
        final double PI = 3.14159;
        final String COMPANY_NAME = "Java Corp";
        
        System.out.println("Max size: " + MAX_SIZE);
        System.out.println("PI: " + PI);
        System.out.println("Company: " + COMPANY_NAME);
        
        // Constants in calculations
        double radius = 5.0;
        double area = PI * radius * radius;
        double circumference = 2 * PI * radius;
        
        System.out.println("Circle with radius " + radius + ":");
        System.out.println("Area: " + String.format("%.2f", area));
        System.out.println("Circumference: " + String.format("%.2f", circumference));
        
        System.out.println();
    }
    
    public static void demonstrateVariableScope() {
        System.out.println("=== Variable Scope ===");
        
        // Local variables
        int localVar = 10;
        System.out.println("Local variable: " + localVar);
        
        // Block scope
        {
            int blockVar = 20;
            System.out.println("Block variable: " + blockVar);
            System.out.println("Local variable in block: " + localVar);
        }
        // blockVar is not accessible here
        
        // Demonstrate scope with methods
        int result = calculateSum(5, 10);
        System.out.println("Sum from method: " + result);
        
        // Demonstrate parameter scope
        String message = "Hello";
        modifyString(message);
        System.out.println("Original message: " + message); // Still "Hello"
        
        System.out.println();
    }
    
    // Helper method to demonstrate method scope
    public static int calculateSum(int a, int b) {
        int sum = a + b;  // Local variable in method
        return sum;
    }
    
    // Helper method to demonstrate parameter passing
    public static void modifyString(String str) {
        str = str + " World";  // This doesn't modify the original string
        System.out.println("Modified string in method: " + str);
    }
}
```

### Code Explanation
1. **Primitive Types**: Demonstrates all 8 primitive data types with their ranges and usage
2. **Reference Types**: Shows String operations, StringBuilder, and object creation
3. **Type Conversions**: Implicit (widening) and explicit (narrowing) conversions
4. **Arrays**: One-dimensional and multi-dimensional arrays with operations
5. **User Input**: Reading different data types from user input
6. **Constants**: Using final keyword for constants
7. **Variable Scope**: Local, block, and method scope demonstration

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
# Compile
javac VariablesAndDataTypes.java

# Run
java VariablesAndDataTypes
```

### Windows
```cmd
# Compile
javac VariablesAndDataTypes.java

# Run
java VariablesAndDataTypes
```

## Advanced Examples

### Type Wrapper Classes
```java
public class WrapperClassesDemo {
    public static void main(String[] args) {
        // Primitive to wrapper (autoboxing)
        Integer intWrapper = 42;
        Double doubleWrapper = 3.14;
        Boolean boolWrapper = true;
        Character charWrapper = 'A';
        
        // Wrapper to primitive (unboxing)
        int primitiveInt = intWrapper;
        double primitiveDouble = doubleWrapper;
        boolean primitiveBool = boolWrapper;
        char primitiveChar = charWrapper;
        
        System.out.println("Wrapper Integer: " + intWrapper);
        System.out.println("Primitive int: " + primitiveInt);
        
        // Wrapper class methods
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Integer.parseInt(\"123\"): " + Integer.parseInt("123"));
        System.out.println("Integer.toBinaryString(10): " + Integer.toBinaryString(10));
        System.out.println("Integer.toHexString(255): " + Integer.toHexString(255));
        
        // Character class methods
        System.out.println("Character.isLetter('A'): " + Character.isLetter('A'));
        System.out.println("Character.isDigit('5'): " + Character.isDigit('5'));
        System.out.println("Character.isUpperCase('A'): " + Character.isUpperCase('A'));
        System.out.println("Character.toLowerCase('A'): " + Character.toLowerCase('A'));
    }
}
```

### String Operations Advanced
```java
public class StringOperationsDemo {
    public static void main(String[] args) {
        String text = "  Hello, Java Programming!  ";
        
        // String manipulation
        System.out.println("Original: '" + text + "'");
        System.out.println("Trimmed: '" + text.trim() + "'");
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        
        // String comparison
        String str1 = "Hello";
        String str2 = "hello";
        String str3 = new String("Hello");
        
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1.equalsIgnoreCase(str2): " + str1.equalsIgnoreCase(str2));
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str1.equals(str3): " + str1.equals(str3));
        
        // String searching
        String sentence = "Java is a powerful programming language";
        System.out.println("Contains 'Java': " + sentence.contains("Java"));
        System.out.println("Starts with 'Java': " + sentence.startsWith("Java"));
        System.out.println("Ends with 'language': " + sentence.endsWith("language"));
        System.out.println("Index of 'powerful': " + sentence.indexOf("powerful"));
        
        // String splitting
        String[] words = sentence.split(" ");
        System.out.println("Words: " + java.util.Arrays.toString(words));
        
        // String replacement
        String replaced = sentence.replace("Java", "Python");
        System.out.println("Replaced: " + replaced);
    }
}
```

## Common Pitfalls and Solutions

### Issue 1: Division with Integers
```java
// Problem: Integer division truncates
int a = 5;
int b = 2;
double result = a / b;  // Result is 2.0, not 2.5

// Solution: Cast to double
double result2 = (double) a / b;  // Result is 2.5
```

### Issue 2: String Concatenation with Numbers
```java
// Problem: Operator precedence
int x = 5;
int y = 3;
String result = "Sum: " + x + y;  // Result is "Sum: 53"

// Solution: Use parentheses
String result2 = "Sum: " + (x + y);  // Result is "Sum: 8"
```

### Issue 3: Array Index Out of Bounds
```java
// Problem: Accessing invalid index
int[] arr = {1, 2, 3};
int value = arr[5];  // ArrayIndexOutOfBoundsException

// Solution: Check bounds
if (index >= 0 && index < arr.length) {
    int value = arr[index];
}
```

## Best Practices

### Variable Naming
```java
// Good: Clear and descriptive names
String firstName = "John";
int studentAge = 20;
double gpaScore = 3.8;
boolean isEnrolled = true;

// Bad: Unclear names
String s = "John";
int a = 20;
double d = 3.8;
boolean b = true;
```

### Constant Declaration
```java
// Good: Use final for constants
public static final int MAX_STUDENTS = 100;
public static final double TAX_RATE = 0.08;
public static final String COMPANY_NAME = "Java Corp";

// Good: Use UPPER_CASE for constants
private static final int DEFAULT_TIMEOUT = 5000;
```

### Type Safety
```java
// Good: Explicit type declarations
int count = 0;
String name = "John";
double price = 19.99;

// Avoid: var keyword (Java 10+) unless type is obvious
var list = new ArrayList<String>();  // OK - type is obvious
var result = calculateComplexValue(); // Avoid - type not obvious
```

## Exercises

### Exercise 1: Temperature Converter
Create a program that converts between Celsius and Fahrenheit temperatures.

### Exercise 2: Simple Calculator
Create a calculator that performs basic arithmetic operations on different data types.

### Exercise 3: String Analyzer
Create a program that analyzes a string and reports:
- Number of characters
- Number of words
- Number of vowels
- Most frequent character

### Exercise 4: Array Statistics
Create a program that calculates statistics for an array of numbers:
- Sum, average, minimum, maximum
- Standard deviation

### Exercise 5: Type Conversion Utility
Create a utility class with methods to safely convert between different data types.

## Learning Outcomes
- Understanding Java primitive and reference data types
- Learning variable declaration and initialization
- Understanding type conversions and casting
- Learning array operations and manipulation
- Understanding variable scope and lifetime
- Learning string operations and methods
- Understanding user input handling
- Learning best practices for variable naming and type safety
