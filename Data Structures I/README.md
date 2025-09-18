# Data Structures I

## Overview
This repository contains comprehensive learning materials for Data Structures I, focusing on fundamental data structures and algorithms. All content is designed to be cross-platform compatible with Linux, Windows, and macOS, with examples implemented in C++.

## Repository Structure

```
Data Structures I/
├── Theory/           # Theoretical concepts and explanations
├── Projects/         # Project specifications and requirements
├── Practices/        # Hands-on exercises and solutions
└── README.md        # This file
```

## Theory
Comprehensive theoretical explanations with examples covering:

- **01-introduction-to-data-structures.md**: Introduction, concepts, and algorithmic efficiency (Big O)
- **02-arrays.md**: One-dimensional and multi-dimensional arrays, matrices
- **03-linked-lists.md**: Singly, doubly, and circular linked lists
- **04-stacks.md**: Stack implementation and applications
- **05-queues.md**: Queue types and implementations
- **06-recursion.md**: Recursive concepts and classic examples

## Projects
Detailed project specifications for practical application:

- **01-matrix-calculator.md**: Matrix operations and linear algebra
- **02-expression-evaluator.md**: Expression parsing using stacks
- **03-data-structure-benchmark.md**: Performance analysis and comparison

## Practices
Hands-on exercises with complete solutions:

- **01-arrays/**: Array operations and algorithms
- **02-linked-lists/**: Linked list implementations and algorithms
- **03-stacks/**: Stack-based applications
- **04-queues/**: Queue implementations and usage
- **05-recursion/**: Recursive algorithms and problem solving

## Topics Covered

### Core Data Structures
- **Arrays**: Static and dynamic arrays, multi-dimensional arrays
- **Linked Lists**: Singly, doubly, and circular linked lists
- **Stacks**: LIFO operations, applications, and implementations
- **Queues**: FIFO operations, priority queues, and circular queues
- **Recursion**: Recursive thinking, classic problems, and optimization

### Key Concepts
- **Algorithmic Complexity**: Big O notation, time and space complexity
- **Memory Management**: Dynamic allocation, smart pointers, RAII
- **Performance Analysis**: Benchmarking, profiling, and optimization
- **Problem Solving**: Algorithm design patterns and strategies

## Cross-Platform Compatibility

All code examples and projects are designed to work on:

- **Linux**: Using GCC or Clang
- **Windows**: Using MinGW, MSYS2, or Visual Studio
- **macOS**: Using Xcode or Homebrew GCC

### Compilation Examples

#### Linux/macOS
```bash
g++ -std=c++17 -O2 -o program main.cpp
./program
```

#### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o program.exe main.cpp
program.exe
```

#### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o program.exe main.cpp
./program.exe
```

## Prerequisites

- C++17 or higher compiler (GCC, Clang, MSVC)
- Basic understanding of programming concepts
- Familiarity with object-oriented programming
- Command line/terminal access

## Learning Path

1. **Start with Theory**: Read through the theoretical concepts in the Theory directory
2. **Practice with Exercises**: Work through the practices in the Practices directory
3. **Apply with Projects**: Implement the projects in the Projects directory
4. **Cross-Platform Testing**: Test your code on different platforms

## C++ Environment Setup

### Linux (Ubuntu/Debian)
```bash
# Install build tools
sudo apt update
sudo apt install build-essential gdb

# Verify installation
g++ --version
gdb --version
```

### Linux (CentOS/RHEL/Fedora)
```bash
# Install build tools
sudo yum groupinstall "Development Tools"
# or for newer versions
sudo dnf groupinstall "Development Tools"

# Verify installation
g++ --version
```

### Windows
1. Install MinGW-w64 or MSYS2:
   - [MinGW-w64](https://www.mingw-w64.org/)
   - [MSYS2](https://www.msys2.org/)
2. Add to PATH environment variable
3. Verify installation in Command Prompt:
   ```cmd
   g++ --version
   ```

### macOS
```bash
# Install Xcode Command Line Tools
xcode-select --install

# Or install via Homebrew
brew install gcc

# Verify installation
g++ --version
```

## Features

- **Comprehensive Coverage**: From basic arrays to advanced algorithms
- **Cross-Platform**: Works on Linux, Windows, and macOS
- **Practical Examples**: Real-world applicable code examples
- **Performance Focus**: Understanding algorithmic efficiency
- **Modern C++**: Using C++17 features and best practices
- **Complete Solutions**: All practices include complete working solutions

## Getting Started

1. **Setup C++ Environment**: Install a C++17 compiler on your system
2. **Choose a Topic**: Start with Theory directory to understand concepts
3. **Practice**: Work through exercises in the Practices directory
4. **Build Projects**: Implement projects from the Projects directory
5. **Test Cross-Platform**: Ensure your code works on different platforms

## IDE Recommendations

### Visual Studio Code
- Free and lightweight
- Excellent C++ extension pack
- Great for learning and development
- Cross-platform compatibility

### CLion
- Professional C++ IDE
- Excellent debugging and profiling tools
- Built-in CMake support
- Cross-platform support

### Code::Blocks
- Free and open-source
- Good for beginners
- Built-in compiler support
- Cross-platform compatibility

### Dev-C++
- Simple and lightweight
- Good for Windows users
- Easy setup and configuration
- Built-in MinGW compiler

## Build Tools (Optional)

### CMake
```cmake
# Example CMakeLists.txt
cmake_minimum_required(VERSION 3.10)
project(DataStructures)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_executable(program main.cpp)

# Compiler flags
if(MSVC)
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /W4")
else()
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -Wpedantic")
endif()
```

### Makefile
```makefile
CXX = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -Wpedantic -O2
TARGET = program
SOURCES = main.cpp

# Platform detection
ifeq ($(OS),Windows_NT)
    TARGET := $(TARGET).exe
    RM = del /Q
else
    RM = rm -f
endif

$(TARGET): $(SOURCES)
	$(CXX) $(CXXFLAGS) -o $(TARGET) $(SOURCES)

clean:
	$(RM) $(TARGET)

.PHONY: clean
```

## Testing

### Simple Testing Framework
```cpp
#include <iostream>
#include <cassert>

#define ASSERT_EQ(expected, actual) \
    do { \
        if ((expected) != (actual)) { \
            std::cerr << "Assertion failed: " << #expected << " != " << #actual \
                      << " (" << (expected) << " != " << (actual) << ")" << std::endl; \
            exit(1); \
        } \
    } while(0)

void testArrayOperations() {
    DynamicArray<int> array;
    
    // Test insertion
    array.insert(10);
    array.insert(20);
    ASSERT_EQ(2, array.size());
    
    // Test search
    ASSERT_EQ(0, array.search(10));
    ASSERT_EQ(1, array.search(20));
    ASSERT_EQ(-1, array.search(30));
    
    std::cout << "All tests passed!" << std::endl;
}

int main() {
    testArrayOperations();
    return 0;
}
```

## Common Issues and Solutions

### Compilation Errors
```bash
# Error: 'std::make_unique' was not declared
# Solution: Ensure C++14 or higher standard
g++ -std=c++17 main.cpp

# Error: 'g++' is not recognized
# Solution: Add compiler to PATH or install build tools
```

### Runtime Errors
```cpp
// Error: Segmentation fault
// Solution: Check for null pointer dereferences
if (ptr != nullptr) {
    // Safe to use ptr
}

// Error: Memory leaks
// Solution: Use smart pointers
std::unique_ptr<int> ptr = std::make_unique<int>(42);
```

## Performance Considerations

### Compiler Optimizations
```bash
# Debug build (with debugging symbols)
g++ -std=c++17 -g -O0 -o debug_program main.cpp

# Release build (optimized)
g++ -std=c++17 -O2 -DNDEBUG -o release_program main.cpp

# Maximum optimization
g++ -std=c++17 -O3 -march=native -DNDEBUG -o fast_program main.cpp
```

### Profiling
```bash
# Using gprof (Linux/macOS)
g++ -std=c++17 -pg -O2 -o program main.cpp
./program
gprof program gmon.out > profile.txt

# Using Valgrind (Linux)
valgrind --tool=callgrind ./program
```

## Best Practices

1. **Use Modern C++**: Prefer C++17 features over older alternatives
2. **Memory Management**: Use smart pointers and RAII principles
3. **Error Handling**: Use exceptions and proper error checking
4. **Code Style**: Follow consistent naming and formatting conventions
5. **Documentation**: Comment complex algorithms and data structures
6. **Testing**: Write tests for your implementations
7. **Performance**: Profile and optimize critical code paths

## Extension Ideas

- **Advanced Data Structures**: Trees, graphs, hash tables
- **Algorithm Design**: Dynamic programming, greedy algorithms
- **Parallel Programming**: Multi-threading and concurrency
- **GPU Computing**: CUDA or OpenCL implementations
- **Web Interface**: Emscripten for browser-based demos
- **Mobile Development**: Cross-platform mobile applications

## Resources

### Books
- "Data Structures and Algorithms in C++" by Mark Allen Weiss
- "Introduction to Algorithms" by Cormen, Leiserson, Rivest, and Stein
- "Effective C++" by Scott Meyers
- "Modern C++ Design" by Andrei Alexandrescu

### Online Resources
- [C++ Reference](https://en.cppreference.com/)
- [C++ Core Guidelines](https://isocpp.github.io/CppCoreGuidelines/)
- [LeetCode](https://leetcode.com/) - Practice problems
- [HackerRank](https://www.hackerrank.com/) - Algorithm challenges

### Communities
- [Stack Overflow](https://stackoverflow.com/questions/tagged/c%2b%2b)
- [Reddit r/cpp](https://www.reddit.com/r/cpp/)
- [C++ Forums](https://cplusplus.com/forum/)

## Contributing

This repository is designed for educational purposes. Feel free to:
- Add more examples and exercises
- Improve existing code and documentation
- Add cross-platform compatibility notes
- Suggest new topics or data structures
- Report issues and bugs

## License

This educational content is provided for learning purposes. Please respect the educational nature of this repository.

## Contact

For questions or suggestions regarding this educational content, please refer to the course materials or instructor.

---

**Happy Learning with Data Structures!** 🚀📊
