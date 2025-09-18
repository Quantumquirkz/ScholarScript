# Practice 03: Number Classification with Conditionals

## Objective
Learn conditional statements in C++ by creating a program that classifies integers as negative, zero, or positive using if-else statements.

## Problem Statement
Classify an integer as negative, zero, or positive.

### Requirements
- Input: One integer x
- Output: One of: negative | zero | positive
- Constraints: Use if/else chain

### Examples
```
Input: -7
Output: negative

Input: 0
Output: zero

Input: 9
Output: positive
```

## Solution

### Complete Code
```cpp
#include <iostream>

int main() {
    int x;
    
    // Read integer
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    // Classify the number
    if (x < 0) {
        std::cout << "negative" << std::endl;
    } else if (x > 0) {
        std::cout << "positive" << std::endl;
    } else {
        std::cout << "zero" << std::endl;
    }
    
    return 0;
}
```

### Code Explanation
1. **Variable Declaration**: `int x` to store the input integer
2. **Input**: Read integer using `std::cin`
3. **Conditional Logic**: Use if-else chain to classify the number
4. **Output**: Display the classification result

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o classify main.cpp
./classify
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o classify.exe main.cpp
./classify.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o classify.exe main.cpp
classify.exe
```

## Key Concepts

### Conditional Statements
- `if`: Execute code if condition is true
- `else if`: Check additional conditions
- `else`: Execute code if all conditions are false

### Comparison Operators
- `<`: Less than
- `>`: Greater than
- `<=`: Less than or equal to
- `>=`: Greater than or equal to
- `==`: Equal to
- `!=`: Not equal to

### Logical Flow
- Conditions are evaluated in order
- First true condition executes its block
- `else` block executes if no conditions are true

## Common Pitfalls

1. **Missing Braces**: Always use braces for multi-line blocks
2. **Assignment vs Comparison**: Use `==` for comparison, not `=`
3. **Floating-Point Comparison**: Be careful with floating-point equality
4. **Uninitialized Variables**: Always initialize variables before use

## Variations

### Enhanced Version with Input Validation
```cpp
#include <iostream>

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    
    // Check if input is valid
    if (std::cin >> x) {
        if (x < 0) {
            std::cout << "negative" << std::endl;
        } else if (x > 0) {
            std::cout << "positive" << std::endl;
        } else {
            std::cout << "zero" << std::endl;
        }
    } else {
        std::cout << "Error: Invalid input!" << std::endl;
        return 1;
    }
    
    return 0;
}
```

### Version with Additional Classifications
```cpp
#include <iostream>

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    if (x < 0) {
        std::cout << "negative" << std::endl;
        if (x < -100) {
            std::cout << "very negative" << std::endl;
        }
    } else if (x > 0) {
        std::cout << "positive" << std::endl;
        if (x > 100) {
            std::cout << "very positive" << std::endl;
        }
    } else {
        std::cout << "zero" << std::endl;
    }
    
    return 0;
}
```

### Version with Ternary Operator
```cpp
#include <iostream>

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    // Using ternary operator
    std::string classification = (x < 0) ? "negative" : 
                                (x > 0) ? "positive" : "zero";
    
    std::cout << classification << std::endl;
    
    return 0;
}
```

### Version with Switch Statement
```cpp
#include <iostream>

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    // Determine the sign
    int sign = (x > 0) ? 1 : (x < 0) ? -1 : 0;
    
    switch (sign) {
        case 1:
            std::cout << "positive" << std::endl;
            break;
        case -1:
            std::cout << "negative" << std::endl;
            break;
        case 0:
            std::cout << "zero" << std::endl;
            break;
        default:
            std::cout << "unknown" << std::endl;
            break;
    }
    
    return 0;
}
```

## Advanced Features

### Function-Based Approach
```cpp
#include <iostream>
#include <string>

std::string classifyNumber(int x) {
    if (x < 0) {
        return "negative";
    } else if (x > 0) {
        return "positive";
    } else {
        return "zero";
    }
}

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    std::cout << classifyNumber(x) << std::endl;
    
    return 0;
}
```

### Class-Based Approach
```cpp
#include <iostream>
#include <string>

class NumberClassifier {
public:
    static std::string classify(int x) {
        if (x < 0) {
            return "negative";
        } else if (x > 0) {
            return "positive";
        } else {
            return "zero";
        }
    }
    
    static void displayClassification(int x) {
        std::cout << "Number " << x << " is " << classify(x) << std::endl;
    }
};

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    NumberClassifier::displayClassification(x);
    
    return 0;
}
```

## Edge Cases

### Handling Large Numbers
```cpp
#include <iostream>
#include <climits>

int main() {
    int x;
    
    std::cout << "Enter an integer: ";
    std::cin >> x;
    
    // Check for overflow
    if (std::cin.fail()) {
        std::cout << "Error: Number too large or invalid!" << std::endl;
        return 1;
    }
    
    if (x < 0) {
        std::cout << "negative" << std::endl;
    } else if (x > 0) {
        std::cout << "positive" << std::endl;
    } else {
        std::cout << "zero" << std::endl;
    }
    
    return 0;
}
```

## Exercises

1. Create a program that determines if a year is a leap year
2. Implement a grade calculator that determines letter grades from numeric scores
3. Write a program that determines the type of triangle based on side lengths
4. Create a program that checks if a number is even or odd
5. Implement a program that determines the season based on a month number

## Learning Outcomes
- Understanding conditional statements in C++
- Learning comparison operators
- Working with if-else chains
- Handling input validation
- Cross-platform compilation
