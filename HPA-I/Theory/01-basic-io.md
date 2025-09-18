# Basic Input/Output in C++

## Overview
Input/Output (I/O) operations are fundamental in C++ programming. This module covers basic console input and output operations, including reading user input and displaying formatted output.

## Key Concepts

### Standard Input/Output Streams
- `std::cin`: Standard input stream (keyboard)
- `std::cout`: Standard output stream (console)
- `std::cerr`: Standard error stream (console)

### Basic Output
```cpp
#include <iostream>

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
```

### Basic Input
```cpp
#include <iostream>
#include <string>

int main() {
    std::string name;
    std::cout << "Enter your name: ";
    std::cin >> name;
    std::cout << "Hello, " << name << "!" << std::endl;
    return 0;
}
```

## Input Methods

### Reading Different Data Types
```cpp
#include <iostream>

int main() {
    int age;
    double height;
    std::string name;
    
    std::cout << "Enter name, age, and height: ";
    std::cin >> name >> age >> height;
    
    std::cout << "Name: " << name << std::endl;
    std::cout << "Age: " << age << std::endl;
    std::cout << "Height: " << height << std::endl;
    
    return 0;
}
```

### Reading Full Lines
```cpp
#include <iostream>
#include <string>

int main() {
    std::string fullName;
    std::cout << "Enter your full name: ";
    std::getline(std::cin, fullName);
    std::cout << "Hello, " << fullName << "!" << std::endl;
    return 0;
}
```

## Output Formatting

### Fixed-Point Notation
```cpp
#include <iostream>
#include <iomanip>

int main() {
    double value = 3.14159;
    
    // Set precision to 2 decimal places
    std::cout << std::fixed << std::setprecision(2);
    std::cout << "Value: " << value << std::endl; // Output: 3.14
    
    return 0;
}
```

### Field Width and Alignment
```cpp
#include <iostream>
#include <iomanip>

int main() {
    int number = 42;
    
    // Right-aligned with width 10
    std::cout << std::setw(10) << number << std::endl;
    
    // Left-aligned with width 10
    std::cout << std::left << std::setw(10) << number << std::endl;
    
    return 0;
}
```

## Cross-Platform Considerations

### Windows
- Use PowerShell or Command Prompt
- Compile with MinGW or Visual Studio
- Path separators: `\` or `/`

### Linux/macOS
- Use terminal/bash
- Compile with g++ or clang++
- Path separators: `/`

### Compilation Examples
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o program main.cpp
./program

# Windows (MinGW)
g++ -std=c++17 -O2 -o program.exe main.cpp
program.exe
```

## Common Pitfalls

1. **Mixing `>>` and `getline()`**: The `>>` operator leaves a newline in the buffer
2. **Buffer issues**: Always clear the input buffer when switching input methods
3. **Precision**: Use `std::fixed` and `std::setprecision()` for decimal formatting

## Best Practices

1. Always include necessary headers (`<iostream>`, `<iomanip>`)
2. Use meaningful variable names
3. Provide clear prompts to users
4. Handle edge cases (empty input, invalid data types)
5. Use appropriate data types for the expected input

## Exercises

1. Create a program that reads a user's name and age, then displays a personalized greeting
2. Implement a calculator that reads two numbers and displays their sum, difference, product, and quotient
3. Write a program that reads a sentence and counts the number of words
