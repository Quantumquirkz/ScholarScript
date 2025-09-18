# Calculator Project

## Project Overview
A comprehensive calculator application that performs basic arithmetic operations, handles user input validation, and provides a clean command-line interface.

## Project Specifications

### Core Features
- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Input validation and error handling
- Support for both integer and floating-point calculations
- Interactive menu system
- Cross-platform compatibility

### Technical Requirements
- **Language**: C++
- **Standard**: C++17 or higher
- **Dependencies**: Standard library only
- **Platforms**: Linux, Windows, macOS

## Project Structure
```
calculator/
├── src/
│   ├── main.cpp
│   ├── calculator.h
│   ├── calculator.cpp
│   ├── input_handler.h
│   └── input_handler.cpp
├── tests/
│   └── test_calculator.cpp
├── build/
├── CMakeLists.txt
└── README.md
```

## Detailed Specifications

### 1. Calculator Class
```cpp
class Calculator {
public:
    // Basic operations
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);
    
    // Advanced operations
    double power(double base, double exponent);
    double squareRoot(double number);
    double percentage(double value, double percent);
    
    // Utility functions
    bool isValidNumber(const std::string& input);
    void displayMenu();
    void run();
};
```

### 2. Input Handler Class
```cpp
class InputHandler {
public:
    double getNumber(const std::string& prompt);
    int getMenuChoice();
    bool getYesNo(const std::string& prompt);
    void clearInputBuffer();
    void pause();
};
```

### 3. Error Handling
- Division by zero protection
- Invalid input validation
- Overflow/underflow detection
- Graceful error recovery

### 4. User Interface
- Clear menu display
- Input prompts
- Result formatting
- Error messages
- Exit confirmation

## Implementation Details

### Menu System
```
=== Calculator ===
1. Addition
2. Subtraction
3. Multiplication
4. Division
5. Power
6. Square Root
7. Percentage
8. Exit

Enter your choice (1-8): 
```

### Input Validation
- Check for valid numeric input
- Handle empty input gracefully
- Provide clear error messages
- Allow retry on invalid input

### Output Formatting
- Use `std::fixed` and `std::setprecision(2)` for consistent decimal display
- Format results with appropriate units
- Display calculations in a readable format

## Cross-Platform Considerations

### Compilation
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o calculator src/*.cpp

# Windows (MinGW)
g++ -std=c++17 -O2 -o calculator.exe src/*.cpp

# Windows (Visual Studio)
cl /std:c++17 /O2 src/*.cpp /Fe:calculator.exe
```

### Platform-Specific Features
- Use `std::cin.ignore()` for input buffer clearing
- Handle different line ending conventions
- Ensure consistent decimal formatting across platforms

## Testing Requirements

### Unit Tests
- Test all arithmetic operations
- Test input validation
- Test error handling
- Test edge cases (zero, negative numbers, very large numbers)

### Test Cases
```cpp
// Test cases for addition
assert(calculator.add(2, 3) == 5);
assert(calculator.add(-2, 3) == 1);
assert(calculator.add(0, 0) == 0);

// Test cases for division
assert(calculator.divide(10, 2) == 5);
assert(calculator.divide(10, 0) throws std::invalid_argument);
```

## Performance Requirements
- Response time < 100ms for basic operations
- Memory usage < 1MB
- Support for calculations with up to 15 decimal places

## Documentation Requirements
- Comprehensive code comments
- User manual
- API documentation
- Build instructions for all platforms

## Extension Ideas
- History of calculations
- Memory functions (M+, M-, MR, MC)
- Scientific calculator functions
- GUI interface
- Configuration file support

## Deliverables
1. Complete source code
2. Executable for each platform
3. Unit tests
4. Documentation
5. Build scripts
6. User manual

## Success Criteria
- All basic operations work correctly
- Input validation prevents crashes
- Code compiles and runs on all target platforms
- Unit tests pass with 100% success rate
- User interface is intuitive and responsive
