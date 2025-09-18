# Practice 01: Hello World and Basic Input

## Objective
Learn basic input/output operations in C++ by creating a simple greeting program that reads user input and displays formatted output.

## Problem Statement
Create a program that prints a greeting and reads a user's name from standard input.

### Requirements
- Input: A single word representing the user's name
- Output: A greeting line with the user's name
- Constraints: Name has no spaces

### Example
```
Input: Alice
Output: Hello, Alice!
```

## Solution

### Complete Code
```cpp
#include <iostream>
#include <string>

int main() {
    std::string name;
    
    // Prompt for input
    std::cout << "Enter your name: ";
    
    // Read input
    std::cin >> name;
    
    // Display greeting
    std::cout << "Hello, " << name << "!" << std::endl;
    
    return 0;
}
```

### Code Explanation
1. **Include Headers**: `iostream` for input/output, `string` for string handling
2. **Variable Declaration**: `std::string name` to store the user's name
3. **Input Prompt**: Display a message asking for input
4. **Read Input**: Use `std::cin >> name` to read a single word
5. **Output**: Display the greeting with the user's name

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o hello_world main.cpp
./hello_world
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o hello_world.exe main.cpp
./hello_world.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o hello_world.exe main.cpp
hello_world.exe
```

## Key Concepts

### Input/Output Streams
- `std::cin`: Standard input stream (keyboard)
- `std::cout`: Standard output stream (console)
- `std::endl`: Inserts newline and flushes output buffer

### String Handling
- `std::string`: C++ string class for text manipulation
- `>>` operator: Extracts formatted input (stops at whitespace)

## Common Pitfalls

1. **Missing Headers**: Always include necessary headers
2. **Buffer Issues**: `std::cin >>` leaves newline in buffer
3. **Empty Input**: Program doesn't handle empty input gracefully

## Variations

### Enhanced Version with Input Validation
```cpp
#include <iostream>
#include <string>

int main() {
    std::string name;
    
    std::cout << "Enter your name: ";
    std::cin >> name;
    
    // Check if name is empty
    if (name.empty()) {
        std::cout << "Error: Name cannot be empty!" << std::endl;
        return 1;
    }
    
    std::cout << "Hello, " << name << "!" << std::endl;
    return 0;
}
```

### Version with Full Name Support
```cpp
#include <iostream>
#include <string>

int main() {
    std::string fullName;
    
    std::cout << "Enter your full name: ";
    std::cin.ignore(); // Clear input buffer
    std::getline(std::cin, fullName);
    
    std::cout << "Hello, " << fullName << "!" << std::endl;
    return 0;
}
```

## Exercises

1. Modify the program to ask for both first and last name
2. Add input validation to ensure the name is not empty
3. Create a version that handles full names with spaces
4. Add a loop to ask for multiple names until the user types "quit"

## Learning Outcomes
- Understanding basic I/O operations
- Working with strings in C++
- Cross-platform compilation
- Input validation techniques
