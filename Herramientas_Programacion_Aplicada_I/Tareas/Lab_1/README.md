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
- Display both values with proper formatting
- Handle negative temperatures

**Expected Output**:
```
Temperature Converter
Enter temperature in Celsius: 25
25°C = 77°F
```

### 📝 Exercise 4: Student Grade Calculator
**Objective**: Calculate and display student grades

**Requirements**:
- Accept student name and three test scores
- Calculate average score
- Determine letter grade (A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: <60)
- Display comprehensive grade report

**Expected Output**:
```
Student Grade Calculator
Enter student name: Maria
Enter test 1 score: 85
Enter test 2 score: 92
Enter test 3 score: 78

Grade Report for Maria:
Test 1: 85
Test 2: 92
Test 3: 78
Average: 85.0
Letter Grade: B
```

### 📝 Exercise 5: Area and Perimeter Calculator
**Objective**: Calculate geometric properties

**Requirements**:
- Accept dimensions for rectangle, circle, and triangle
- Calculate area and perimeter/circumference
- Display results in a formatted table
- Use appropriate mathematical constants

**Expected Output**:
```
Geometric Calculator
Enter rectangle length: 10
Enter rectangle width: 5
Enter circle radius: 7
Enter triangle base: 8
Enter triangle height: 6

Results:
Rectangle: Area = 50, Perimeter = 30
Circle: Area = 153.94, Circumference = 43.98
Triangle: Area = 24, Perimeter = [calculated]
```

## 🧪 Advanced Exercises

### 📝 Exercise 6: Currency Converter
**Objective**: Convert between different currencies

**Requirements**:
- Support USD, EUR, GBP, JPY conversions
- Use current exchange rates (hardcoded for lab)
- Accept amount and source/target currencies
- Display conversion with proper formatting

### 📝 Exercise 7: Time Calculator
**Objective**: Perform time calculations

**Requirements**:
- Add/subtract hours, minutes, seconds
- Convert between time formats (12/24 hour)
- Calculate time differences
- Handle day boundaries

### 📝 Exercise 8: Mathematical Functions
**Objective**: Implement basic mathematical functions

**Requirements**:
- Calculate square, cube, square root
- Implement power function
- Calculate factorial
- Handle edge cases and errors

## 📊 Assessment Criteria

### 🎯 Code Quality (30%)
- Proper variable naming and declarations
- Clean and readable code structure
- Appropriate use of comments
- Consistent formatting and indentation

### 🔧 Functionality (40%)
- Programs compile without errors
- Correct output for all test cases
- Proper input validation
- Error handling where appropriate

### 📝 Documentation (20%)
- Clear program descriptions
- Function documentation
- Input/output specifications
- Algorithm explanations

### 🧪 Testing (10%)
- Test with various input values
- Edge case testing
- Error condition testing
- Performance considerations

## 🛠️ Development Guidelines

### 🔧 Compilation Commands
```bash
# Basic compilation
g++ -o program program.cpp

# With warnings enabled
g++ -Wall -Wextra -o program program.cpp

# With debugging information
g++ -g -o program program.cpp

# With optimization
g++ -O2 -o program program.cpp
```

### 📝 Code Standards
- Use meaningful variable names
- Include header comments
- Add inline comments for complex logic
- Follow consistent indentation (4 spaces)
- Use proper spacing around operators

### 🧪 Testing Strategy
- Test with normal input values
- Test with edge cases (0, negative numbers, large values)
- Test with invalid input
- Verify output formatting

## 📚 Additional Resources

### 📖 Reference Materials
- [C++ Tutorial](https://www.cplusplus.com/doc/tutorial/)
- [C++ Reference](https://en.cppreference.com/)
- [GCC Documentation](https://gcc.gnu.org/onlinedocs/)

### 🛠️ Development Tools
- **IDE**: Visual Studio Code, CLion, Dev-C++
- **Compiler**: GCC/G++ (GNU Compiler Collection)
- **Debugger**: GDB (GNU Debugger)
- **Version Control**: Git

### 📝 Submission Guidelines
- Submit source code files (.cpp)
- Include README with program descriptions
- Provide test cases and expected outputs
- Document any assumptions or limitations

## 🎯 Learning Objectives Checklist

- [ ] Understand C++ program structure
- [ ] Declare and use different data types
- [ ] Implement basic input/output operations
- [ ] Use arithmetic operators correctly
- [ ] Handle program compilation and execution
- [ ] Write clean, documented code
- [ ] Test programs with various inputs
- [ ] Debug common programming errors

## 📞 Support and Help

### 👨‍🏫 Office Hours
- **Instructor**: Available during scheduled office hours
- **Teaching Assistants**: Lab support and tutoring
- **Online Forums**: Course discussion and Q&A

### 📧 Communication
- **Email**: For individual questions and concerns
- **Discussion Board**: For general questions and collaboration
- **GitHub Issues**: For technical problems and bug reports

---

**Lab Duration**: 3 hours  
**Difficulty Level**: Beginner  
**Prerequisites**: None  
**Last Updated**: December 2024
