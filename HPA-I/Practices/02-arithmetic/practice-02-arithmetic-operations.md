# Practice 02: Arithmetic Operations and Formatting

## Objective
Learn arithmetic operations and output formatting in C++ by creating a program that performs basic mathematical calculations and displays results with proper formatting.

## Problem Statement
Read two real numbers and print their sum and product with two decimal places.

### Requirements
- Input: Two floating-point numbers a and b
- Output: Sum and product on one line with 2 decimal places
- Constraints: Use `std::fixed` and `std::setprecision(2)`

### Example
```
Input: 2 3
Output: sum=5.00 prod=6.00
```

## Solution

### Complete Code
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double a, b;
    
    // Read two numbers
    std::cout << "Enter two numbers: ";
    std::cin >> a >> b;
    
    // Calculate sum and product
    double sum = a + b;
    double product = a * b;
    
    // Set precision to 2 decimal places
    std::cout << std::fixed << std::setprecision(2);
    
    // Display results
    std::cout << "sum=" << sum << " prod=" << product << std::endl;
    
    return 0;
}
```

### Code Explanation
1. **Include Headers**: `iostream` for I/O, `iomanip` for formatting
2. **Variable Declaration**: `double a, b` for floating-point numbers
3. **Input**: Read two numbers using `std::cin`
4. **Calculations**: Compute sum and product
5. **Formatting**: Set fixed-point notation with 2 decimal places
6. **Output**: Display formatted results

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o arithmetic main.cpp
./arithmetic
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o arithmetic.exe main.cpp
./arithmetic.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o arithmetic.exe main.cpp
arithmetic.exe
```

## Key Concepts

### Arithmetic Operators
- `+`: Addition
- `-`: Subtraction
- `*`: Multiplication
- `/`: Division
- `%`: Modulus (remainder)

### Output Formatting
- `std::fixed`: Use fixed-point notation
- `std::setprecision(2)`: Set decimal places to 2
- `std::setw()`: Set field width
- `std::left`/`std::right`: Set alignment

### Data Types
- `double`: Double-precision floating-point
- `float`: Single-precision floating-point
- `int`: Integer type

## Common Pitfalls

1. **Integer Division**: `7 / 3` equals `2`, not `2.333...`
2. **Division by Zero**: Always check for zero before division
3. **Precision Issues**: Use appropriate data types for calculations
4. **Missing Headers**: Include `<iomanip>` for formatting functions

## Variations

### Enhanced Version with All Operations
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double a, b;
    
    std::cout << "Enter two numbers: ";
    std::cin >> a >> b;
    
    // Calculate all operations
    double sum = a + b;
    double difference = a - b;
    double product = a * b;
    double quotient = (b != 0) ? a / b : 0;
    
    // Set precision
    std::cout << std::fixed << std::setprecision(2);
    
    // Display results
    std::cout << "Sum: " << sum << std::endl;
    std::cout << "Difference: " << difference << std::endl;
    std::cout << "Product: " << product << std::endl;
    
    if (b != 0) {
        std::cout << "Quotient: " << quotient << std::endl;
    } else {
        std::cout << "Quotient: Division by zero!" << std::endl;
    }
    
    return 0;
}
```

### Version with Input Validation
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double a, b;
    
    std::cout << "Enter two numbers: ";
    
    // Check if input is valid
    if (std::cin >> a >> b) {
        double sum = a + b;
        double product = a * b;
        
        std::cout << std::fixed << std::setprecision(2);
        std::cout << "sum=" << sum << " prod=" << product << std::endl;
    } else {
        std::cout << "Error: Invalid input!" << std::endl;
        return 1;
    }
    
    return 0;
}
```

### Version with Scientific Notation
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double a, b;
    
    std::cout << "Enter two numbers: ";
    std::cin >> a >> b;
    
    double sum = a + b;
    double product = a * b;
    
    // Display in different formats
    std::cout << "Fixed notation:" << std::endl;
    std::cout << std::fixed << std::setprecision(2);
    std::cout << "sum=" << sum << " prod=" << product << std::endl;
    
    std::cout << "Scientific notation:" << std::endl;
    std::cout << std::scientific << std::setprecision(2);
    std::cout << "sum=" << sum << " prod=" << product << std::endl;
    
    return 0;
}
```

## Advanced Features

### Calculator Class
```cpp
#include <iostream>
#include <iomanip>

class Calculator {
public:
    static double add(double a, double b) { return a + b; }
    static double subtract(double a, double b) { return a - b; }
    static double multiply(double a, double b) { return a * b; }
    static double divide(double a, double b) { 
        return (b != 0) ? a / b : 0; 
    }
    
    static void displayResult(const std::string& operation, double result) {
        std::cout << std::fixed << std::setprecision(2);
        std::cout << operation << ": " << result << std::endl;
    }
};

int main() {
    double a, b;
    std::cout << "Enter two numbers: ";
    std::cin >> a >> b;
    
    Calculator::displayResult("Sum", Calculator::add(a, b));
    Calculator::displayResult("Product", Calculator::multiply(a, b));
    
    return 0;
}
```

## Exercises

1. Create a program that calculates the area and perimeter of a rectangle
2. Implement a temperature converter between Celsius and Fahrenheit
3. Create a compound interest calculator
4. Write a program that calculates the distance between two points
5. Implement a program that finds the roots of a quadratic equation

## Learning Outcomes
- Understanding arithmetic operations in C++
- Learning output formatting techniques
- Working with floating-point numbers
- Handling input validation
- Cross-platform compilation
