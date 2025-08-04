# 🧪 Lab 1: Introduction to C++

## 📋 Laboratory Objectives
This laboratory session introduces students to the fundamental concepts of C++ programming, including variables, data types, basic input/output operations, and program structure.

## 🎯 Learning Outcomes
By the end of this lab, students will be able to:
- Understand the basic structure of a C++ program
- Declare and initialize variables of different data types
- Use basic input/output operations (cin, cout)
- Apply arithmetic operators and expressions
- Handle basic program compilation and execution
- Write simple programs following C++ conventions

## 📚 Theoretical Background

### 🔧 C++ Program Structure
```cpp
#include <iostream>  // Include necessary libraries
using namespace std; // Use standard namespace

int main() {         // Main function - program entry point
    // Program statements here
    return 0;        // Return statement
}
```

### 📊 Data Types in C++
- **int**: Integer numbers (e.g., 42, -17, 0)
- **double**: Floating-point numbers (e.g., 3.14, -2.5)
- **char**: Single characters (e.g., 'A', '5', '!')
- **bool**: Boolean values (true or false)
- **string**: Text strings (requires #include <string>)

### 🔄 Input/Output Operations
```cpp
// Output to console
cout << "Hello, World!" << endl;

// Input from console
int number;
cin >> number;

// Multiple input/output
cout << "Enter two numbers: ";
cin >> num1 >> num2;
```

### ⚡ Arithmetic Operators
- **+**: Addition
- **-**: Subtraction
- **\***: Multiplication
- **/**: Division
- **%**: Modulus (remainder)
- **++**: Increment
- **--**: Decrement

## 🛠️ Laboratory Exercises

### 📝 Exercise 1: Hello World Program
**Objective**: Create your first C++ program

**Requirements**:
- Display a welcome message
- Ask for the user's name
- Greet the user personally
- Display current date/time information

**Expected Output**:
```
Welcome to C++ Programming!
Please enter your name: John
Hello, John! Welcome to the world of programming.
Current date: [system date]
```

### 📝 Exercise 2: Basic Calculator
**Objective**: Implement a simple calculator

**Requirements**:
- Accept two numbers from the user
- Perform basic arithmetic operations (+, -, *, /)
- Display results with proper formatting
- Handle division by zero

**Expected Output**:
```
Simple Calculator
Enter first number: 10
Enter second number: 5
Results:
10 + 5 = 15
10 - 5 = 5
10 * 5 = 50
10 / 5 = 2
```

### 📝 Exercise 3: Temperature Converter
**Objective**: Convert between Celsius and Fahrenheit

**Requirements**:
- Accept temperature in Celsius
- Convert to Fahrenheit using formula: F = (C * 9/5) + 32
- Display both values with appropriate labels
- Format output to 2 decimal places

**Expected Output**:
```
Temperature Converter
Enter temperature in Celsius: 25
25.00°C = 77.00°F
```

## 🔧 Implementation Guidelines

### 📁 File Structure
```
Lab_1/
├── README.md           # This file
├── hello_world.cpp     # Exercise 1
├── calculator.cpp      # Exercise 2
├── temperature.cpp     # Exercise 3
├── input.txt          # Sample input data
├── expected_output.txt # Expected results
└── Makefile           # Compilation script
```

### 🔧 Compilation Commands
```bash
# Compile individual programs
g++ -o hello_world hello_world.cpp
g++ -o calculator calculator.cpp
g++ -o temperature temperature.cpp

# Compile with warnings
g++ -Wall -Wextra -o program program.cpp

# Compile with debugging information
g++ -g -o program program.cpp
```

### 📝 Code Standards
- Use meaningful variable names
- Include proper comments
- Handle input validation
- Format output clearly
- Follow consistent indentation

## 🧪 Testing and Validation

### ✅ Test Cases
1. **Normal Input**: Test with typical values
2. **Edge Cases**: Test with zero, negative numbers
3. **Invalid Input**: Test with non-numeric input
4. **Boundary Values**: Test with maximum/minimum values

### 🔍 Debugging Tips
- Use cout statements to trace program flow
- Check variable values at different points
- Verify input/output operations
- Test with different data sets

## 📊 Assessment Criteria

### 🎯 Functionality (40%)
- Programs compile without errors
- Correct output for all test cases
- Proper handling of edge cases
- Input validation implemented

### 📝 Code Quality (30%)
- Clean and readable code
- Proper variable naming
- Consistent formatting
- Appropriate comments

### 📚 Documentation (20%)
- Clear program descriptions
- Function documentation
- Input/output specifications
- Algorithm explanations

### 🧪 Testing (10%)
- Comprehensive test cases
- Edge case handling
- Error condition testing
- Performance considerations

## 📚 Additional Resources

### 📖 Reading Materials
- C++ Primer, Chapter 1: Getting Started
- C++ Tutorial: Basic Syntax
- C++ Reference: Data Types

### 🌐 Online Resources
- [C++ Tutorial](https://www.cplusplus.com/doc/tutorial/)
- [C++ Reference](https://en.cppreference.com/)
- [GCC Documentation](https://gcc.gnu.org/onlinedocs/)

### 🛠️ Tools
- [Online C++ Compiler](https://www.onlinegdb.com/online_c++_compiler)
- [C++ Code Formatter](https://format.krzaq.cc/)
- [C++ Linter](https://cppcheck.sourceforge.io/)

## 🚀 Next Steps
After completing this lab, students should:
1. Review the concepts covered
2. Practice with additional exercises
3. Prepare for Lab 2: Control Structures
4. Explore advanced C++ features

## 📞 Support and Questions
- **Instructor Office Hours**: [Schedule]
- **Teaching Assistant**: [Contact Information]
- **Discussion Forum**: [Link to Course Forum]
- **Email Support**: [Course Email]

---

**Lab Duration**: 2-3 hours  
**Difficulty Level**: Beginner  
**Prerequisites**: None  
**Last Updated**: December 2024
