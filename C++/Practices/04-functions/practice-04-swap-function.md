# Practice 04: Functions and References

## Objective
Learn function definition, parameter passing, and references in C++ by implementing a swap function that exchanges two values using references.

## Problem Statement
Implement swap by reference and demonstrate it.

### Requirements
- Input: None required; you may hardcode sample values
- Output: Values after swapping
- Constraints: No pointers; use references

### Example
```
Input: (2,5)
Output: 5 2
```

## Solution

### Complete Code
```cpp
#include <iostream>

// Function declaration
void swap_ref(int& a, int& b);

int main() {
    int x = 2, y = 5;
    
    // Display values before swap
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    
    // Call swap function
    swap_ref(x, y);
    
    // Display values after swap
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    
    return 0;
}

// Function definition
void swap_ref(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}
```

### Code Explanation
1. **Function Declaration**: `void swap_ref(int& a, int& b)` - parameters are references
2. **Main Function**: Initialize variables and call swap function
3. **Function Definition**: Implement swap logic using a temporary variable
4. **Reference Parameters**: `int&` means the parameters are references to the original variables

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o swap main.cpp
./swap
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o swap.exe main.cpp
./swap.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o swap.exe main.cpp
swap.exe
```

## Key Concepts

### References
- `int& a`: Reference to an integer
- References must be initialized when declared
- References cannot be reassigned to refer to different objects
- References provide an alias for the original variable

### Function Parameters
- **Pass by Value**: `void func(int a)` - creates a copy
- **Pass by Reference**: `void func(int& a)` - works with original variable
- **Pass by Pointer**: `void func(int* a)` - works with memory address

### Function Declaration vs Definition
- **Declaration**: Tells compiler about function signature
- **Definition**: Provides actual implementation
- Declaration can be in header file, definition in source file

## Common Pitfalls

1. **Missing Reference Operator**: Use `&` to declare references
2. **Uninitialized References**: References must be initialized
3. **Reassigning References**: References cannot be reassigned
4. **Same Variable Passed Twice**: `swap_ref(x, x)` causes undefined behavior

## Variations

### Version with Multiple Data Types
```cpp
#include <iostream>

// Template function for any type
template<typename T>
void swap_ref(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

int main() {
    // Test with integers
    int x = 2, y = 5;
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    swap_ref(x, y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    
    // Test with doubles
    double a = 3.14, b = 2.71;
    std::cout << "Before swap: a=" << a << ", b=" << b << std::endl;
    swap_ref(a, b);
    std::cout << "After swap: a=" << a << ", b=" << b << std::endl;
    
    // Test with strings
    std::string s1 = "Hello", s2 = "World";
    std::cout << "Before swap: s1=" << s1 << ", s2=" << s2 << std::endl;
    swap_ref(s1, s2);
    std::cout << "After swap: s1=" << s1 << ", s2=" << s2 << std::endl;
    
    return 0;
}
```

### Version with Input from User
```cpp
#include <iostream>

void swap_ref(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x, y;
    
    std::cout << "Enter two integers: ";
    std::cin >> x >> y;
    
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    swap_ref(x, y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    
    return 0;
}
```

### Version with Error Handling
```cpp
#include <iostream>

bool swap_ref(int& a, int& b) {
    // Check if same variable passed twice
    if (&a == &b) {
        std::cout << "Error: Cannot swap same variable!" << std::endl;
        return false;
    }
    
    int temp = a;
    a = b;
    b = temp;
    return true;
}

int main() {
    int x = 2, y = 5;
    
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    
    if (swap_ref(x, y)) {
        std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    }
    
    // Test error case
    std::cout << "Testing error case:" << std::endl;
    swap_ref(x, x);
    
    return 0;
}
```

## Advanced Features

### Class-Based Approach
```cpp
#include <iostream>

class Swapper {
public:
    static void swap(int& a, int& b) {
        int temp = a;
        a = b;
        b = temp;
    }
    
    static void swap(double& a, double& b) {
        double temp = a;
        a = b;
        b = temp;
    }
    
    static void displayValues(const std::string& label, int a, int b) {
        std::cout << label << ": a=" << a << ", b=" << b << std::endl;
    }
};

int main() {
    int x = 2, y = 5;
    
    Swapper::displayValues("Before swap", x, y);
    Swapper::swap(x, y);
    Swapper::displayValues("After swap", x, y);
    
    return 0;
}
```

### Multiple Swap Functions
```cpp
#include <iostream>

// Swap two integers
void swap_int(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

// Swap two doubles
void swap_double(double& a, double& b) {
    double temp = a;
    a = b;
    b = temp;
}

// Swap two characters
void swap_char(char& a, char& b) {
    char temp = a;
    a = b;
    b = temp;
}

int main() {
    // Test integer swap
    int x = 2, y = 5;
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    swap_int(x, y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    
    // Test double swap
    double a = 3.14, b = 2.71;
    std::cout << "Before swap: a=" << a << ", b=" << b << std::endl;
    swap_double(a, b);
    std::cout << "After swap: a=" << a << ", b=" << b << std::endl;
    
    // Test character swap
    char c1 = 'A', c2 = 'B';
    std::cout << "Before swap: c1=" << c1 << ", c2=" << c2 << std::endl;
    swap_char(c1, c2);
    std::cout << "After swap: c1=" << c1 << ", c2=" << c2 << std::endl;
    
    return 0;
}
```

## Comparison with Other Approaches

### Pass by Value (Doesn't Work)
```cpp
void swap_value(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    // Changes only local copies, not original variables
}
```

### Pass by Pointer
```cpp
void swap_pointer(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Usage: swap_pointer(&x, &y);
```

### Using std::swap
```cpp
#include <iostream>
#include <algorithm>

int main() {
    int x = 2, y = 5;
    
    std::cout << "Before swap: x=" << x << ", y=" << y << std::endl;
    std::swap(x, y);
    std::cout << "After swap: x=" << x << ", y=" << y << std::endl;
    
    return 0;
}
```

## Exercises

1. Create a function that swaps three variables in a circular manner
2. Implement a function that swaps two strings
3. Write a function that swaps two elements in an array
4. Create a function that swaps two objects of a custom class
5. Implement a function that swaps two vectors

## Learning Outcomes
- Understanding function declaration and definition
- Learning about references in C++
- Understanding pass by reference vs pass by value
- Working with function parameters
- Cross-platform compilation
