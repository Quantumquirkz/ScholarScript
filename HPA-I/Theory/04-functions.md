# Functions in C++

## Overview
Functions are reusable blocks of code that perform specific tasks. This module covers function declaration, definition, parameters, return values, and different ways of passing arguments.

## Basic Function Structure

### Function Declaration and Definition
```cpp
#include <iostream>

// Function declaration (prototype)
int add(int a, int b);

// Function definition
int add(int a, int b) {
    return a + b;
}

int main() {
    int result = add(5, 3);
    std::cout << "Sum: " << result << std::endl;
    return 0;
}
```

### Inline Functions
```cpp
#include <iostream>

inline int multiply(int a, int b) {
    return a * b;
}

int main() {
    int result = multiply(4, 6);
    std::cout << "Product: " << result << std::endl;
    return 0;
}
```

## Parameter Passing

### Pass by Value
```cpp
#include <iostream>

void swapByValue(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    std::cout << "Inside function: a=" << a << ", b=" << b << std::endl;
}

int main() {
    int x = 5, y = 10;
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    swapByValue(x, y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    return 0;
}
```

### Pass by Reference
```cpp
#include <iostream>

void swapByReference(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    swapByReference(x, y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    return 0;
}
```

### Pass by Pointer
```cpp
#include <iostream>

void swapByPointer(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    int x = 5, y = 10;
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    swapByPointer(&x, &y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    return 0;
}
```

## Function Overloading

### Same Function Name, Different Parameters
```cpp
#include <iostream>

// Overloaded functions
int add(int a, int b) {
    return a + b;
}

double add(double a, double b) {
    return a + b;
}

int add(int a, int b, int c) {
    return a + b + c;
}

int main() {
    std::cout << "add(5, 3) = " << add(5, 3) << std::endl;
    std::cout << "add(5.5, 3.2) = " << add(5.5, 3.2) << std::endl;
    std::cout << "add(1, 2, 3) = " << add(1, 2, 3) << std::endl;
    return 0;
}
```

## Default Parameters

### Functions with Default Arguments
```cpp
#include <iostream>

void greet(const std::string& name, int times = 1) {
    for (int i = 0; i < times; i++) {
        std::cout << "Hello, " << name << "!" << std::endl;
    }
}

int main() {
    greet("Alice");           // Uses default times = 1
    greet("Bob", 3);          // Uses times = 3
    return 0;
}
```

## Return Types

### Different Return Types
```cpp
#include <iostream>
#include <string>

// Return int
int getMax(int a, int b) {
    return (a > b) ? a : b;
}

// Return double
double getAverage(int a, int b) {
    return (a + b) / 2.0;
}

// Return string
std::string getGrade(int score) {
    if (score >= 90) return "A";
    if (score >= 80) return "B";
    if (score >= 70) return "C";
    if (score >= 60) return "D";
    return "F";
}

// Return bool
bool isEven(int number) {
    return (number % 2 == 0);
}

int main() {
    std::cout << "Max: " << getMax(10, 20) << std::endl;
    std::cout << "Average: " << getAverage(10, 20) << std::endl;
    std::cout << "Grade: " << getGrade(85) << std::endl;
    std::cout << "Is 7 even? " << (isEven(7) ? "Yes" : "No") << std::endl;
    return 0;
}
```

## Const Correctness

### Const Parameters and Return Values
```cpp
#include <iostream>
#include <string>

// Const reference parameter (read-only)
void printInfo(const std::string& name, const int& age) {
    std::cout << "Name: " << name << ", Age: " << age << std::endl;
    // name = "New Name";  // Error: cannot modify const reference
}

// Const member function (if this were a class method)
class Person {
private:
    std::string name;
    int age;
public:
    Person(const std::string& n, int a) : name(n), age(a) {}
    
    // Const member function - cannot modify member variables
    std::string getName() const {
        return name;
    }
    
    int getAge() const {
        return age;
    }
};

int main() {
    printInfo("Alice", 25);
    
    Person person("Bob", 30);
    std::cout << "Person: " << person.getName() 
              << ", Age: " << person.getAge() << std::endl;
    return 0;
}
```

## Recursive Functions

### Basic Recursion
```cpp
#include <iostream>

// Factorial function
long long factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

// Fibonacci function
int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

int main() {
    int n = 5;
    std::cout << "Factorial of " << n << " = " << factorial(n) << std::endl;
    std::cout << "Fibonacci(" << n << ") = " << fibonacci(n) << std::endl;
    return 0;
}
```

## Function Templates

### Generic Functions
```cpp
#include <iostream>

template<typename T>
T getMax(T a, T b) {
    return (a > b) ? a : b;
}

template<typename T>
void printArray(T arr[], int size) {
    for (int i = 0; i < size; i++) {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
}

int main() {
    std::cout << "Max of 5 and 3: " << getMax(5, 3) << std::endl;
    std::cout << "Max of 5.5 and 3.2: " << getMax(5.5, 3.2) << std::endl;
    
    int intArray[] = {1, 2, 3, 4, 5};
    double doubleArray[] = {1.1, 2.2, 3.3, 4.4, 5.5};
    
    printArray(intArray, 5);
    printArray(doubleArray, 5);
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o functions main.cpp
./functions

# Windows (PowerShell)
g++ -std=c++17 -O2 -o functions.exe main.cpp
./functions.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o functions.exe main.cpp
functions.exe
```

### Platform-Specific Considerations
- Function calling conventions may vary between platforms
- Stack size limits may affect recursive functions
- Template instantiation behavior is consistent across platforms

## Common Pitfalls

1. **Missing return statement**: Functions with non-void return type must return a value
2. **Reference to local variable**: Don't return references to local variables
3. **Uninitialized parameters**: Always initialize parameters before use
4. **Infinite recursion**: Ensure recursive functions have proper base cases

## Best Practices

1. Use meaningful function names that describe what the function does
2. Keep functions small and focused on a single task
3. Use const correctness whenever possible
4. Prefer pass by reference for large objects to avoid copying
5. Use default parameters to reduce function overloads
6. Document function parameters and return values
7. Avoid global variables; pass data through parameters instead

## Performance Considerations

### Inline Functions
- Use `inline` for small, frequently called functions
- Compiler may ignore `inline` suggestion if function is too complex
- Inline functions should be defined in header files

### Pass by Reference vs Value
- Use pass by reference for large objects to avoid copying
- Use pass by value for small, primitive types
- Use const reference when you don't need to modify the parameter

## Exercises

1. Create a function that calculates the area of a circle
2. Implement a function that finds the greatest common divisor (GCD) of two numbers
3. Write a recursive function to calculate the sum of digits in a number
4. Create a template function that sorts an array of any type
5. Implement a function that checks if a string is a palindrome
