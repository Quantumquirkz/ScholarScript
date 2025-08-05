# 🧪 Practice Problems - APT_I

## 📋 Overview
This document contains additional practice problems to help students reinforce their understanding of C++ programming concepts. These problems range from basic to advanced difficulty levels.

## 🎯 Problem Categories

### 🔢 Basic Problems (Beginner)

#### Problem 1: Simple Calculator
**Objective**: Create a basic calculator that performs arithmetic operations.

**Requirements**:
- Accept two numbers and an operation (+, -, *, /)
- Perform the calculation and display result
- Handle division by zero
- Allow multiple calculations

**Sample Input/Output**:
```
Simple Calculator
Enter first number: 10
Enter operation (+,-,*,/): +
Enter second number: 5
Result: 10 + 5 = 15
```

#### Problem 2: Number Guessing Game
**Objective**: Implement a simple number guessing game.

**Requirements**:
- Generate a random number between 1-100
- Accept user guesses
- Provide hints (higher/lower)
- Count attempts
- Allow multiple games

#### Problem 3: Temperature Converter
**Objective**: Convert between different temperature scales.

**Requirements**:
- Support Celsius, Fahrenheit, and Kelvin
- Allow bidirectional conversion
- Display conversion formulas
- Handle invalid inputs

#### Problem 4: Simple Interest Calculator
**Objective**: Calculate simple interest and total amount.

**Requirements**:
- Accept principal, rate, and time
- Calculate simple interest
- Display breakdown of calculations
- Format output properly

### 🔄 Intermediate Problems

#### Problem 5: Prime Number Generator
**Objective**: Generate prime numbers within a range.

**Requirements**:
- Accept range from user
- Generate all prime numbers in range
- Count total primes found
- Display in formatted output

**Sample Input/Output**:
```
Prime Number Generator
Enter start of range: 1
Enter end of range: 50

Prime numbers between 1 and 50:
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47
Total primes found: 15
```

#### Problem 6: Fibonacci Sequence
**Objective**: Generate Fibonacci sequence.

**Requirements**:
- Accept number of terms
- Generate Fibonacci sequence
- Display sequence and sum
- Handle large numbers appropriately

#### Problem 7: Array Statistics
**Objective**: Calculate various statistics for an array.

**Requirements**:
- Accept array size and elements
- Calculate mean, median, mode
- Find min, max, range
- Display results in table format

#### Problem 8: String Manipulator
**Objective**: Perform various string operations.

**Requirements**:
- Accept a string from user
- Count characters, words, vowels
- Reverse the string
- Convert case (upper/lower)
- Check if palindrome

### 🚀 Advanced Problems

#### Problem 9: Matrix Operations
**Objective**: Perform operations on matrices.

**Requirements**:
- Accept two matrices
- Implement addition, subtraction, multiplication
- Calculate determinant (2x2, 3x3)
- Display matrices in formatted output

#### Problem 10: Sorting Algorithm Visualizer
**Objective**: Visualize sorting algorithms step by step.

**Requirements**:
- Accept array from user
- Implement bubble sort, selection sort
- Display each step of sorting process
- Count comparisons and swaps

#### Problem 11: Simple Database System
**Objective**: Create a basic database-like system.

**Requirements**:
- Store records (name, age, city)
- Add, delete, search records
- Sort records by different fields
- Save/load from file

#### Problem 12: Game of Life
**Objective**: Implement Conway's Game of Life.

**Requirements**:
- Create 2D grid
- Implement game rules
- Display generations
- Allow user interaction

## 📊 Problem Templates

### 🔧 Basic Template
```cpp
/*
 * Problem Name
 * Description of what the program does
 */

#include <iostream>
using namespace std;

int main() {
    // Variable declarations
    
    // Input section
    
    // Processing section
    
    // Output section
    
    return 0;
}
```

### 🔧 Function-Based Template
```cpp
/*
 * Problem Name
 * Description of what the program does
 */

#include <iostream>
using namespace std;

// Function prototypes
void function1();
int function2(int parameter);

int main() {
    // Main program logic
    return 0;
}

// Function implementations
void function1() {
    // Function body
}

int function2(int parameter) {
    // Function body
    return result;
}
```

## 🎯 Problem-Solving Strategies

### 📝 Understanding the Problem
1. Read the problem statement carefully
2. Identify inputs and outputs
3. Understand the requirements
4. Plan your approach

### 🔧 Design Phase
1. Break down the problem into smaller parts
2. Identify required data structures
3. Plan the algorithm
4. Consider edge cases

### 💻 Implementation Phase
1. Start with basic structure
2. Implement core functionality
3. Add error handling
4. Test with various inputs

### 🧪 Testing Phase
1. Test with normal inputs
2. Test with edge cases
3. Test with invalid inputs
4. Verify output format

## 📚 Additional Resources

### 🛠️ Useful Libraries
```cpp
#include <iostream>   // Input/output
#include <string>     // String operations
#include <cmath>      // Mathematical functions
#include <iomanip>    // Output formatting
#include <cstdlib>    // Random numbers
#include <ctime>      // Time functions
#include <fstream>    // File operations
```

### 🔍 Debugging Tips
1. Use cout statements to trace execution
2. Check variable values at different points
3. Verify loop conditions and termination
4. Test with simple cases first

### 📝 Common Patterns
1. **Input validation**: Always validate user input
2. **Error handling**: Handle potential errors gracefully
3. **Modular design**: Break code into functions
4. **Documentation**: Comment your code clearly

## 🎯 Difficulty Levels

### 🌱 Beginner (1-3 hours)
- Basic input/output
- Simple calculations
- Basic control structures
- Single functions

### 🌿 Intermediate (3-6 hours)
- Multiple functions
- Arrays and loops
- String manipulation
- File operations

### 🌳 Advanced (6+ hours)
- Complex algorithms
- Multi-dimensional arrays
- Advanced data structures
- System integration

## 📊 Assessment Criteria

### 🎯 Code Quality (30%)
- Clean and readable code
- Proper variable naming
- Consistent formatting
- Appropriate comments

### 🔧 Functionality (40%)
- Correct output for all test cases
- Proper error handling
- Input validation
- Edge case handling

### 📝 Problem Solving (20%)
- Logical approach
- Efficient algorithms
- Creative solutions
- Optimization

### 🧪 Testing (10%)
- Comprehensive testing
- Various input scenarios
- Error condition testing
- Performance considerations

---

**Last Updated**: December 2024  
**Total Problems**: 12  
**Difficulty Levels**: 3  
**Estimated Time**: 20-40 hours 