# 🎯 Project 1: Java Fundamentals

## 📋 Project Overview
This project introduces students to Java programming fundamentals, including basic syntax, object-oriented programming concepts, and the development of simple Java applications. Students will learn to create classes, objects, and basic programs while understanding Java's core features.

## 🎯 Learning Objectives
By the end of this project, students will be able to:
- Understand Java syntax and program structure
- Create and use classes and objects
- Implement basic object-oriented programming concepts
- Work with Java's primitive data types and operators
- Use control structures and loops effectively
- Handle basic input/output operations
- Compile and run Java programs

## 📚 Theoretical Background

### 🔧 Java Program Structure
```java
public class Main {
    public static void main(String[] args) {
        // Program statements here
        System.out.println("Hello, World!");
    }
}
```

### 📊 Java Data Types
- **Primitive Types**: int, double, char, boolean, byte, short, long, float
- **Reference Types**: String, Arrays, Classes, Interfaces
- **Wrapper Classes**: Integer, Double, Character, Boolean

### 🏗️ Object-Oriented Programming Basics
- **Classes**: Blueprints for objects
- **Objects**: Instances of classes
- **Methods**: Functions within classes
- **Attributes**: Data members of classes
- **Constructors**: Special methods for object initialization

### 🔄 Control Structures
- **Conditionals**: if, if-else, switch statements
- **Loops**: for, while, do-while loops
- **Break and Continue**: Loop control statements

## 🛠️ Project Requirements

### 📝 Task 1: Basic Java Program
**Objective**: Create a simple Java program that demonstrates basic syntax

**Requirements**:
- Create a class with a main method
- Use different data types (int, double, String, boolean)
- Perform basic arithmetic operations
- Display results using System.out.println()

**Expected Output**:
```
Java Fundamentals Demo
Integer: 42
Double: 3.14
String: Hello, Java!
Boolean: true
Sum: 45.14
```

### 📝 Task 2: Student Information System
**Objective**: Create a class to represent student information

**Requirements**:
- Create a Student class with attributes (name, ID, age, GPA)
- Implement a constructor to initialize student data
- Create methods to display student information
- Implement a method to calculate letter grade based on GPA

**Expected Output**:
```
Student Information:
Name: John Doe
ID: 12345
Age: 20
GPA: 3.8
Letter Grade: A
```

### 📝 Task 3: Calculator Class
**Objective**: Implement a simple calculator using object-oriented design

**Requirements**:
- Create a Calculator class with methods for basic operations
- Implement addition, subtraction, multiplication, division
- Add input validation for division by zero
- Create a main method to test the calculator

**Expected Output**:
```
Calculator Demo
10 + 5 = 15
10 - 5 = 5
10 * 5 = 50
10 / 5 = 2.0
Division by zero: Error - Cannot divide by zero
```

## 🔧 Implementation Guidelines

### 📁 Project Structure
```
Project1/
├── README.md              # This file
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Main.java          # Main program
│   │       ├── Student.java       # Student class
│   │       └── Calculator.java    # Calculator class
│   └── test/
│       └── java/
│           ├── StudentTest.java   # Student tests
│           └── CalculatorTest.java # Calculator tests
├── pom.xml                        # Maven configuration
├── input.txt                      # Sample input data
└── expected_output.txt            # Expected results
```

### 🔧 Compilation and Execution
```bash
# Compile Java files
javac src/main/java/*.java

# Run main program
java -cp src/main/java Main

# Run with Maven
mvn clean compile
mvn exec:java -Dexec.mainClass="Main"
```

### 📝 Code Standards
- Use meaningful class and method names
- Include proper comments and documentation
- Follow Java naming conventions
- Implement proper error handling
- Use appropriate access modifiers

## 🧪 Testing and Validation

### ✅ Test Cases
1. **Basic Operations**: Test arithmetic operations
2. **Edge Cases**: Test with zero, negative numbers
3. **Invalid Input**: Test with invalid data
4. **Boundary Values**: Test with maximum/minimum values

### 🔍 Testing Guidelines
- Create unit tests for each class
- Test all public methods
- Verify input validation
- Check error handling

## 📊 Assessment Criteria

### 🎯 Functionality (40%)
- Programs compile and run without errors
- Correct output for all test cases
- Proper handling of edge cases
- Input validation implemented

### 📝 Code Quality (30%)
- Clean and readable code
- Proper class and method design
- Consistent formatting and style
- Appropriate comments and documentation

### 🏗️ Object-Oriented Design (20%)
- Proper use of classes and objects
- Encapsulation of data and methods
- Appropriate method signatures
- Constructor implementation

### 🧪 Testing (10%)
- Unit tests for all classes
- Test coverage of public methods
- Edge case testing
- Error condition testing

## 📚 Additional Resources

### 📖 Reading Materials
- Java Tutorial: Getting Started
- Java Language Basics
- Object-Oriented Programming Concepts
- Java Naming Conventions

### 🌐 Online Resources
- [Java Tutorial](https://docs.oracle.com/javase/tutorial/)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)

### 🛠️ Tools
- [Online Java Compiler](https://www.programiz.com/java-programming/online-compiler/)
- [Java Code Formatter](https://codebeautify.org/java-formatter-beautifier)
- [Java Linter](https://checkstyle.sourceforge.io/)

## 🚀 Next Steps
After completing this project, students should:
1. Review object-oriented programming concepts
2. Practice with additional Java exercises
3. Prepare for Project 2: Advanced OOP
4. Explore Java collections and generics

## 📞 Support and Questions
- **Instructor Office Hours**: [Schedule]
- **Teaching Assistant**: [Contact Information]
- **Discussion Forum**: [Link to Course Forum]
- **Email Support**: [Course Email]

---

**Project Duration**: 2-3 weeks  
**Difficulty Level**: Beginner  
**Prerequisites**: APT_I or basic programming knowledge  
**Last Updated**: December 2024 