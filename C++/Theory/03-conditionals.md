# Conditional Statements in C++

## Overview
Conditional statements allow programs to make decisions based on different conditions. This module covers if-else statements, switch statements, and logical operators for controlling program flow.

## Basic If Statement

### Simple Condition
```cpp
#include <iostream>

int main() {
    int number;
    std::cout << "Enter a number: ";
    std::cin >> number;
    
    if (number > 0) {
        std::cout << "The number is positive." << std::endl;
    }
    
    return 0;
}
```

### If-Else Statement
```cpp
#include <iostream>

int main() {
    int number;
    std::cout << "Enter a number: ";
    std::cin >> number;
    
    if (number > 0) {
        std::cout << "positive" << std::endl;
    } else if (number < 0) {
        std::cout << "negative" << std::endl;
    } else {
        std::cout << "zero" << std::endl;
    }
    
    return 0;
}
```

## Logical Operators

### Comparison Operators
- `==` : Equal to
- `!=` : Not equal to
- `<` : Less than
- `>` : Greater than
- `<=` : Less than or equal to
- `>=` : Greater than or equal to

### Logical Operators
- `&&` : Logical AND
- `||` : Logical OR
- `!` : Logical NOT

### Example Usage
```cpp
#include <iostream>

int main() {
    int age;
    std::cout << "Enter your age: ";
    std::cin >> age;
    
    if (age >= 18 && age <= 65) {
        std::cout << "You are of working age." << std::endl;
    } else if (age < 18) {
        std::cout << "You are a minor." << std::endl;
    } else {
        std::cout << "You are a senior." << std::endl;
    }
    
    return 0;
}
```

## Nested If Statements

### Complex Conditions
```cpp
#include <iostream>

int main() {
    int score;
    std::cout << "Enter your test score (0-100): ";
    std::cin >> score;
    
    if (score >= 90) {
        std::cout << "Grade: A" << std::endl;
    } else if (score >= 80) {
        std::cout << "Grade: B" << std::endl;
    } else if (score >= 70) {
        std::cout << "Grade: C" << std::endl;
    } else if (score >= 60) {
        std::cout << "Grade: D" << std::endl;
    } else {
        std::cout << "Grade: F" << std::endl;
    }
    
    return 0;
}
```

## Switch Statements

### Basic Switch
```cpp
#include <iostream>

int main() {
    char grade;
    std::cout << "Enter your grade (A, B, C, D, F): ";
    std::cin >> grade;
    
    switch (grade) {
        case 'A':
        case 'a':
            std::cout << "Excellent!" << std::endl;
            break;
        case 'B':
        case 'b':
            std::cout << "Good!" << std::endl;
            break;
        case 'C':
        case 'c':
            std::cout << "Average." << std::endl;
            break;
        case 'D':
        case 'd':
            std::cout << "Below average." << std::endl;
            break;
        case 'F':
        case 'f':
            std::cout << "Failed." << std::endl;
            break;
        default:
            std::cout << "Invalid grade." << std::endl;
            break;
    }
    
    return 0;
}
```

### Switch with Integer Values
```cpp
#include <iostream>

int main() {
    int day;
    std::cout << "Enter day number (1-7): ";
    std::cin >> day;
    
    switch (day) {
        case 1:
            std::cout << "Monday" << std::endl;
            break;
        case 2:
            std::cout << "Tuesday" << std::endl;
            break;
        case 3:
            std::cout << "Wednesday" << std::endl;
            break;
        case 4:
            std::cout << "Thursday" << std::endl;
            break;
        case 5:
            std::cout << "Friday" << std::endl;
            break;
        case 6:
            std::cout << "Saturday" << std::endl;
            break;
        case 7:
            std::cout << "Sunday" << std::endl;
            break;
        default:
            std::cout << "Invalid day number." << std::endl;
            break;
    }
    
    return 0;
}
```

## Ternary Operator

### Conditional Expression
```cpp
#include <iostream>

int main() {
    int a, b;
    std::cout << "Enter two numbers: ";
    std::cin >> a >> b;
    
    int max = (a > b) ? a : b;
    std::cout << "Maximum: " << max << std::endl;
    
    // More complex example
    std::cout << "The numbers are " 
              << ((a == b) ? "equal" : "not equal") 
              << std::endl;
    
    return 0;
}
```

## Edge Cases and Error Handling

### Input Validation
```cpp
#include <iostream>
#include <limits>

int main() {
    int number;
    std::cout << "Enter a number: ";
    
    if (std::cin >> number) {
        if (number > 0) {
            std::cout << "positive" << std::endl;
        } else if (number < 0) {
            std::cout << "negative" << std::endl;
        } else {
            std::cout << "zero" << std::endl;
        }
    } else {
        std::cout << "Invalid input!" << std::endl;
        std::cin.clear();
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }
    
    return 0;
}
```

### Range Checking
```cpp
#include <iostream>

int main() {
    int score;
    std::cout << "Enter test score (0-100): ";
    std::cin >> score;
    
    if (score < 0 || score > 100) {
        std::cout << "Invalid score! Must be between 0 and 100." << std::endl;
    } else if (score >= 90) {
        std::cout << "Grade: A" << std::endl;
    } else if (score >= 80) {
        std::cout << "Grade: B" << std::endl;
    } else if (score >= 70) {
        std::cout << "Grade: C" << std::endl;
    } else if (score >= 60) {
        std::cout << "Grade: D" << std::endl;
    } else {
        std::cout << "Grade: F" << std::endl;
    }
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o conditionals main.cpp
./conditionals

# Windows (PowerShell)
g++ -std=c++17 -O2 -o conditionals.exe main.cpp
./conditionals.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o conditionals.exe main.cpp
conditionals.exe
```

### Character Input Handling
- Different platforms may handle character input differently
- Use `std::cin.ignore()` to clear input buffer
- Consider using `std::getline()` for more robust input handling

## Common Pitfalls

1. **Missing break statements**: In switch statements, missing `break` causes fall-through
2. **Assignment vs comparison**: Using `=` instead of `==` in conditions
3. **Floating-point comparison**: Use epsilon for floating-point equality checks
4. **Uninitialized variables**: Always initialize variables before use

## Best Practices

1. Use meaningful variable names
2. Always include `break` statements in switch cases (unless intentional fall-through)
3. Handle all possible cases in switch statements with `default`
4. Use parentheses to clarify complex logical expressions
5. Validate input before processing
6. Use consistent indentation and formatting

## Performance Considerations

### Short-Circuit Evaluation
```cpp
#include <iostream>

int main() {
    int x = 0;
    
    // Short-circuit: second condition won't be evaluated
    if (x != 0 && (10 / x) > 5) {
        std::cout << "This won't execute" << std::endl;
    }
    
    return 0;
}
```

## Exercises

1. Create a program that determines if a year is a leap year
2. Implement a simple calculator with basic operations
3. Write a program that determines the type of triangle based on side lengths
4. Create a grade calculator that determines letter grades from numeric scores
5. Implement a program that checks if a number is prime
