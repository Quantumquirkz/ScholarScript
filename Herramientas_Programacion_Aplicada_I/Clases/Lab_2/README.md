# 🧪 Lab 2: Control Structures

## 📋 Laboratory Objectives
This laboratory session focuses on control structures in C++, including conditional statements (if, if-else, switch) and loops (for, while, do-while). Students will learn to control program flow and implement decision-making logic.

## 🎯 Learning Outcomes
By the end of this lab, students will be able to:
- Implement conditional statements using if, if-else, and switch
- Use logical operators (&&, ||, !) for complex conditions
- Implement different types of loops (for, while, do-while)
- Control loop execution with break and continue statements
- Design nested control structures
- Handle input validation using control structures

## 📚 Theoretical Background

### 🔀 Conditional Statements

#### If Statement
```cpp
if (condition) {
    // Code to execute if condition is true
}
```

#### If-Else Statement
```cpp
if (condition) {
    // Code if condition is true
} else {
    // Code if condition is false
}
```

#### If-Else If-Else Chain
```cpp
if (condition1) {
    // Code for condition1
} else if (condition2) {
    // Code for condition2
} else {
    // Default code
}
```

#### Switch Statement
```cpp
switch (expression) {
    case value1:
        // Code for value1
        break;
    case value2:
        // Code for value2
        break;
    default:
        // Default code
        break;
}
```

### 🔄 Loop Structures

#### For Loop
```cpp
for (initialization; condition; increment) {
    // Loop body
}
```

#### While Loop
```cpp
while (condition) {
    // Loop body
}
```

#### Do-While Loop
```cpp
do {
    // Loop body
} while (condition);
```

### ⚡ Logical Operators
- **&&**: Logical AND
- **||**: Logical OR
- **!**: Logical NOT
- **==**: Equal to
- **!=**: Not equal to
- **<, >, <=, >=**: Comparison operators

## 🛠️ Laboratory Exercises

### 📝 Exercise 1: Number Classification
**Objective**: Classify numbers based on various criteria

**Requirements**:
- Accept a number from the user
- Determine if it's positive, negative, or zero
- Check if it's even or odd
- Determine if it's a single digit, double digit, or larger
- Display comprehensive classification

**Expected Output**:
```
Number Classification
Enter a number: 42
Classification:
- Sign: Positive
- Parity: Even
- Digits: Double digit
- Range: 10-99
```

### 📝 Exercise 2: Grade Point Calculator
**Objective**: Calculate grade points using switch statement

**Requirements**:
- Accept letter grade (A, B, C, D, F)
- Convert to grade points (A=4.0, B=3.0, C=2.0, D=1.0, F=0.0)
- Handle both uppercase and lowercase letters
- Provide feedback for invalid grades

**Expected Output**:
```
Grade Point Calculator
Enter letter grade: B
Grade Points: 3.0
Grade Level: Good
```

### 📝 Exercise 3: Multiplication Table Generator
**Objective**: Generate multiplication tables using loops

**Requirements**:
- Accept a number from the user
- Generate multiplication table from 1 to 10
- Format output in a neat table
- Allow user to generate multiple tables

**Expected Output**:
```
Multiplication Table Generator
Enter a number: 7

Multiplication Table for 7:
7 x 1 = 7
7 x 2 = 14
7 x 3 = 21
...
7 x 10 = 70
```

### 📝 Exercise 4: Prime Number Checker
**Objective**: Check if a number is prime using loops

**Requirements**:
- Accept a number from the user
- Check if it's prime using a loop
- Display all factors if not prime
- Handle edge cases (1, 2, negative numbers)

**Expected Output**:
```
Prime Number Checker
Enter a number: 17
17 is a prime number.

Enter a number: 24
24 is not a prime number.
Factors: 1, 2, 3, 4, 6, 8, 12, 24
```

### 📝 Exercise 5: Pattern Generator
**Objective**: Generate various patterns using nested loops

**Requirements**:
- Generate triangle patterns using asterisks
- Create different pattern types (right triangle, inverted triangle)
- Allow user to choose pattern type and size
- Use nested for loops

**Expected Output**:
```
Pattern Generator
Choose pattern type (1-3):
1. Right Triangle
2. Inverted Triangle
3. Diamond

Enter size: 5

Right Triangle:
*
**
***
****
*****
```

### 📝 Exercise 6: Menu-Driven Calculator
**Objective**: Create a calculator with menu-driven interface

**Requirements**:
- Display menu with arithmetic operations
- Accept user choice and numbers
- Perform selected operation
- Allow multiple calculations
- Handle division by zero

**Expected Output**:
```
Menu-Driven Calculator
1. Addition
2. Subtraction
3. Multiplication
4. Division
5. Exit

Enter your choice: 1
Enter first number: 10
Enter second number: 5
Result: 10 + 5 = 15
```

## 🧪 Advanced Exercises

### 📝 Exercise 7: Number Guessing Game
**Objective**: Implement a number guessing game

**Requirements**:
- Generate random number between 1-100
- Accept user guesses
- Provide hints (higher/lower)
- Count number of attempts
- Allow multiple games

### 📝 Exercise 8: Fibonacci Sequence Generator
**Objective**: Generate Fibonacci sequence

**Requirements**:
- Accept number of terms from user
- Generate Fibonacci sequence
- Display sequence in formatted output
- Handle large numbers appropriately

### 📝 Exercise 9: Password Validator
**Objective**: Validate password strength

**Requirements**:
- Check password length (minimum 8 characters)
- Verify presence of uppercase, lowercase, digits
- Check for special characters
- Provide detailed feedback on weaknesses

## 📊 Assessment Criteria

### 🎯 Code Quality (30%)
- Proper use of control structures
- Clean and readable code structure
- Appropriate variable naming
- Consistent formatting and indentation

### 🔧 Functionality (40%)
- Programs compile without errors
- Correct output for all test cases
- Proper input validation
- Error handling where appropriate

### 📝 Logic and Design (20%)
- Efficient algorithm design
- Proper use of control flow
- Logical problem-solving approach
- Appropriate use of loops and conditions

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
- [C++ Control Structures](https://www.cplusplus.com/doc/tutorial/control/)
- [C++ Loops](https://www.cplusplus.com/doc/tutorial/control/)
- [C++ Switch Statement](https://en.cppreference.com/w/cpp/language/switch)

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

- [ ] Implement if, if-else, and switch statements
- [ ] Use logical operators correctly
- [ ] Implement for, while, and do-while loops
- [ ] Control loop execution with break/continue
- [ ] Design nested control structures
- [ ] Handle input validation
- [ ] Write clean, documented code
- [ ] Test programs with various inputs

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
**Difficulty Level**: Beginner-Intermediate  
**Prerequisites**: Lab 1 completion  
**Last Updated**: December 2024
