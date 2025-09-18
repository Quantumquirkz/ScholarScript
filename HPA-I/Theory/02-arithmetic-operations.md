# Arithmetic Operations in C++

## Overview
Arithmetic operations are fundamental mathematical operations that can be performed on numeric data types in C++. This module covers basic arithmetic operations, operator precedence, and formatting output.

## Basic Arithmetic Operators

### Primary Operators
- `+` : Addition
- `-` : Subtraction  
- `*` : Multiplication
- `/` : Division
- `%` : Modulus (remainder)

### Example Usage
```cpp
#include <iostream>

int main() {
    int a = 10, b = 3;
    
    std::cout << "a = " << a << ", b = " << b << std::endl;
    std::cout << "Addition: " << (a + b) << std::endl;
    std::cout << "Subtraction: " << (a - b) << std::endl;
    std::cout << "Multiplication: " << (a * b) << std::endl;
    std::cout << "Division: " << (a / b) << std::endl;
    std::cout << "Modulus: " << (a % b) << std::endl;
    
    return 0;
}
```

## Data Types and Precision

### Integer Division vs Floating-Point Division
```cpp
#include <iostream>

int main() {
    int a = 7, b = 3;
    double x = 7.0, y = 3.0;
    
    std::cout << "Integer division: " << (a / b) << std::endl;        // 2
    std::cout << "Floating-point division: " << (x / y) << std::endl; // 2.33333
    
    return 0;
}
```

### Type Casting
```cpp
#include <iostream>

int main() {
    int a = 7, b = 3;
    
    // Explicit casting to double
    double result = static_cast<double>(a) / b;
    std::cout << "Casted division: " << result << std::endl; // 2.33333
    
    return 0;
}
```

## Output Formatting for Arithmetic

### Fixed-Point Notation
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double sum = 5.0;
    double product = 6.0;
    
    // Set precision to 2 decimal places
    std::cout << std::fixed << std::setprecision(2);
    std::cout << "sum=" << sum << " prod=" << product << std::endl;
    
    return 0;
}
```

### Scientific Notation
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double largeNumber = 1234567.89;
    
    std::cout << std::scientific << std::setprecision(2);
    std::cout << "Scientific: " << largeNumber << std::endl;
    
    return 0;
}
```

## Operator Precedence

### Order of Operations
1. Parentheses `()`
2. Unary operators (`+`, `-`, `!`)
3. Multiplicative (`*`, `/`, `%`)
4. Additive (`+`, `-`)
5. Relational (`<`, `>`, `<=`, `>=`)
6. Equality (`==`, `!=`)
7. Logical AND (`&&`)
8. Logical OR (`||`)

### Example
```cpp
#include <iostream>

int main() {
    int result = 2 + 3 * 4;        // 14 (not 20)
    int result2 = (2 + 3) * 4;     // 20
    
    std::cout << "2 + 3 * 4 = " << result << std::endl;
    std::cout << "(2 + 3) * 4 = " << result2 << std::endl;
    
    return 0;
}
```

## Compound Assignment Operators

### Shortcut Operators
```cpp
#include <iostream>

int main() {
    int x = 10;
    
    x += 5;    // x = x + 5
    x -= 3;    // x = x - 3
    x *= 2;    // x = x * 2
    x /= 4;    // x = x / 4
    x %= 3;    // x = x % 3
    
    std::cout << "Final value: " << x << std::endl;
    
    return 0;
}
```

## Increment and Decrement Operators

### Pre-increment vs Post-increment
```cpp
#include <iostream>

int main() {
    int x = 5;
    
    std::cout << "Original x: " << x << std::endl;
    std::cout << "Pre-increment: " << (++x) << std::endl;  // 6
    std::cout << "x after pre-increment: " << x << std::endl; // 6
    
    x = 5; // Reset
    std::cout << "Post-increment: " << (x++) << std::endl; // 5
    std::cout << "x after post-increment: " << x << std::endl; // 6
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o arithmetic main.cpp
./arithmetic

# Windows (PowerShell)
g++ -std=c++17 -O2 -o arithmetic.exe main.cpp
./arithmetic.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o arithmetic.exe main.cpp
arithmetic.exe
```

### Floating-Point Precision
- Different platforms may have slight variations in floating-point precision
- Use `std::fixed` and `std::setprecision()` for consistent output
- Consider using `long double` for higher precision when needed

## Common Pitfalls

1. **Integer Division**: `7 / 3` equals `2`, not `2.333...`
2. **Division by Zero**: Always check for zero before division
3. **Overflow**: Large numbers may cause integer overflow
4. **Precision Loss**: Be careful with floating-point arithmetic

## Error Handling

### Division by Zero Check
```cpp
#include <iostream>

int main() {
    double a, b;
    std::cout << "Enter two numbers: ";
    std::cin >> a >> b;
    
    if (b != 0) {
        std::cout << "Division: " << (a / b) << std::endl;
    } else {
        std::cout << "Error: Division by zero!" << std::endl;
    }
    
    return 0;
}
```

## Best Practices

1. Use parentheses to clarify complex expressions
2. Be explicit about type conversions
3. Handle edge cases (division by zero, overflow)
4. Use appropriate data types for the expected range of values
5. Format output consistently using `std::fixed` and `std::setprecision()`

## Exercises

1. Create a calculator that performs basic arithmetic operations
2. Implement a program that calculates the area and perimeter of a rectangle
3. Write a program that converts temperature between Celsius and Fahrenheit
4. Create a compound interest calculator
