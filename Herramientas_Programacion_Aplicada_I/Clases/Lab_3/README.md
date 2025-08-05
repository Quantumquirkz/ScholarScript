# 🧪 Lab 3: Functions and Arrays

## 📋 Laboratory Objectives
This laboratory session introduces students to functions and arrays in C++. Students will learn to create modular programs using functions and work with one-dimensional and multi-dimensional arrays for data storage and manipulation.

## 🎯 Learning Outcomes
By the end of this lab, students will be able to:
- Define and call functions with different parameter types
- Understand function scope and variable lifetime
- Work with one-dimensional and multi-dimensional arrays
- Pass arrays to functions and return arrays
- Implement common array operations (searching, sorting, filtering)
- Create modular and reusable code structures
- Handle string manipulation using character arrays

## 📚 Theoretical Background

### 🔧 Function Fundamentals

#### Function Declaration and Definition
```cpp
// Function declaration (prototype)
returnType functionName(parameterType parameterName);

// Function definition
returnType functionName(parameterType parameterName) {
    // Function body
    return value;
}
```

#### Function Types
```cpp
// Function with no parameters and no return value
void printMessage() {
    cout << "Hello, World!" << endl;
}

// Function with parameters and return value
int add(int a, int b) {
    return a + b;
}

// Function with default parameters
void printNumber(int num = 0) {
    cout << "Number: " << num << endl;
}
```

#### Function Overloading
```cpp
// Multiple functions with same name but different parameters
int add(int a, int b) {
    return a + b;
}

double add(double a, double b) {
    return a + b;
}
```

### 📊 Array Fundamentals

#### One-Dimensional Arrays
```cpp
// Array declaration
int numbers[5] = {1, 2, 3, 4, 5};

// Array initialization
int scores[10] = {0};  // Initialize all to 0

// Array access
int firstElement = numbers[0];
int lastElement = numbers[4];
```

#### Multi-Dimensional Arrays
```cpp
// 2D array declaration
int matrix[3][4] = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

// 3D array
int cube[2][3][4];
```

#### Arrays and Functions
```cpp
// Passing array to function
void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

// Returning array (using pointer)
int* createArray(int size) {
    int* arr = new int[size];
    return arr;
}
```

## 🛠️ Laboratory Exercises

### 📝 Exercise 1: Function Library
**Objective**: Create a library of mathematical functions

**Requirements**:
- Implement functions for basic operations (add, subtract, multiply, divide)
- Create functions for power, factorial, and absolute value
- Implement functions for finding maximum and minimum of two numbers
- Test all functions with various inputs

**Expected Output**:
```
Function Library Test
Addition: 5 + 3 = 8
Multiplication: 4 * 7 = 28
Power: 2^5 = 32
Factorial: 5! = 120
Maximum: max(10, 15) = 15
```

### 📝 Exercise 2: Array Statistics Calculator
**Objective**: Calculate various statistics for an array of numbers

**Requirements**:
- Accept array size and elements from user
- Calculate mean, median, mode, and standard deviation
- Find minimum, maximum, and range
- Display results in formatted output

**Expected Output**:
```
Array Statistics Calculator
Enter array size: 5
Enter elements: 10 20 15 25 30

Statistics:
Mean: 20.0
Median: 20.0
Mode: No mode
Standard Deviation: 7.07
Range: 20 (10 to 30)
```

### 📝 Exercise 3: Array Search and Sort
**Objective**: Implement searching and sorting algorithms

**Requirements**:
- Implement linear and binary search algorithms
- Implement bubble sort and selection sort
- Allow user to choose search/sort method
- Display step-by-step process

**Expected Output**:
```
Array Search and Sort
Original array: 64 34 25 12 22 11 90
After bubble sort: 11 12 22 25 34 64 90
Linear search for 25: Found at index 3
Binary search for 25: Found at index 3
```

### 📝 Exercise 4: Matrix Operations
**Objective**: Perform operations on 2D arrays (matrices)

**Requirements**:
- Accept two matrices from user
- Implement matrix addition, subtraction, and multiplication
- Calculate matrix transpose
- Display matrices in formatted output

**Expected Output**:
```
Matrix Operations
Matrix A:
1 2 3
4 5 6

Matrix B:
7 8 9
10 11 12

Matrix A + B:
8 10 12
14 16 18
```

### 📝 Exercise 5: String Manipulation Functions
**Objective**: Create functions for string operations

**Requirements**:
- Implement string length, copy, and concatenation
- Create functions to reverse string and check palindrome
- Implement case conversion (upper/lower)
- Count vowels, consonants, and words

**Expected Output**:
```
String Manipulation
Original string: Hello World
Length: 11
Reversed: dlroW olleH
Uppercase: HELLO WORLD
Vowels: 3, Consonants: 7
```

### 📝 Exercise 6: Menu-Driven Array Manager
**Objective**: Create a comprehensive array management system

**Requirements**:
- Display menu with array operations
- Implement insert, delete, update, and display functions
- Add search and sort functionality
- Handle array bounds and validation

**Expected Output**:
```
Array Manager
1. Insert element
2. Delete element
3. Update element
4. Display array
5. Search element
6. Sort array
7. Exit

Enter choice: 1
Enter position: 2
Enter value: 50
Element inserted successfully!
```

## 🧪 Advanced Exercises

### 📝 Exercise 7: Recursive Functions
**Objective**: Implement recursive functions

**Requirements**:
- Create recursive factorial and Fibonacci functions
- Implement recursive binary search
- Create recursive array reversal
- Compare recursive vs iterative approaches

### 📝 Exercise 8: Dynamic Arrays
**Objective**: Work with dynamic memory allocation

**Requirements**:
- Create functions to allocate and deallocate arrays
- Implement dynamic array resizing
- Create functions to merge and split arrays
- Handle memory management properly

### 📝 Exercise 9: Function Pointers
**Objective**: Use function pointers for callbacks

**Requirements**:
- Create function pointer types
- Implement callback functions
- Use function pointers for sorting with different criteria
- Create a function dispatcher

## 📊 Assessment Criteria

### 🎯 Code Quality (30%)
- Proper function design and implementation
- Clean and readable code structure
- Appropriate use of arrays and functions
- Consistent formatting and indentation

### 🔧 Functionality (40%)
- Programs compile without errors
- Correct output for all test cases
- Proper array bounds checking
- Error handling where appropriate

### 📝 Modularity (20%)
- Effective use of functions
- Proper parameter passing
- Good code organization
- Reusable function design

### 🧪 Testing (10%)
- Test with various input values
- Edge case testing
- Array boundary testing
- Function parameter testing

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
- Use meaningful function and variable names
- Include function documentation
- Add inline comments for complex logic
- Follow consistent indentation (4 spaces)
- Use proper spacing around operators

### 🧪 Testing Strategy
- Test functions with various input values
- Test array operations with different sizes
- Test edge cases (empty arrays, single elements)
- Verify function return values

## 📚 Additional Resources

### 📖 Reference Materials
- [C++ Functions](https://www.cplusplus.com/doc/tutorial/functions/)
- [C++ Arrays](https://www.cplusplus.com/doc/tutorial/arrays/)
- [C++ Function Overloading](https://en.cppreference.com/w/cpp/language/overload_resolution)

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

- [ ] Define and call functions with different parameters
- [ ] Understand function scope and variable lifetime
- [ ] Work with one-dimensional arrays
- [ ] Work with multi-dimensional arrays
- [ ] Pass arrays to functions
- [ ] Implement common array operations
- [ ] Create modular and reusable code
- [ ] Handle string manipulation
- [ ] Test functions and arrays thoroughly

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
**Difficulty Level**: Intermediate  
**Prerequisites**: Lab 1 and Lab 2 completion  
**Last Updated**: December 2024
