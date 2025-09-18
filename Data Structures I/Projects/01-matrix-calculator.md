# Matrix Calculator Project

## Project Overview
A comprehensive matrix calculator application that performs various matrix operations including addition, subtraction, multiplication, transpose, determinant calculation, and matrix inversion. The project demonstrates advanced array manipulation and mathematical algorithms in C++.

## Project Specifications

### Core Features
- Matrix creation and input validation
- Basic operations: addition, subtraction, multiplication
- Advanced operations: transpose, determinant, inverse
- Matrix display and formatting
- Error handling for invalid operations
- File I/O for saving and loading matrices
- Command-line interface with menu system

### Technical Requirements
- **Language**: C++17 or higher
- **Dependencies**: Standard C++ libraries only
- **Platforms**: Linux, Windows, macOS
- **Build Tool**: CMake or Makefile
- **Memory Management**: Smart pointers for dynamic memory

## Project Structure
```
matrix-calculator/
├── src/
│   ├── Matrix.h
│   ├── Matrix.cpp
│   ├── MatrixCalculator.h
│   ├── MatrixCalculator.cpp
│   ├── FileHandler.h
│   ├── FileHandler.cpp
│   ├── main.cpp
│   └── utils/
│       ├── InputValidator.h
│       └── InputValidator.cpp
├── tests/
│   ├── test_matrix.cpp
│   └── test_operations.cpp
├── CMakeLists.txt
├── Makefile
└── README.md
```

## Detailed Specifications

### 1. Matrix Class

#### Matrix.h
```cpp
#ifndef MATRIX_H
#define MATRIX_H

#include <vector>
#include <memory>
#include <string>
#include <iostream>

class Matrix {
private:
    std::vector<std::vector<double>> data;
    size_t rows;
    size_t cols;
    
    // Helper methods
    bool isValidIndex(size_t row, size_t col) const;
    void validateDimensions(const Matrix& other, const std::string& operation) const;
    
public:
    // Constructors
    Matrix();
    Matrix(size_t rows, size_t cols);
    Matrix(size_t rows, size_t cols, double value);
    Matrix(const std::vector<std::vector<double>>& data);
    Matrix(const Matrix& other);
    Matrix(Matrix&& other) noexcept;
    
    // Destructor
    ~Matrix() = default;
    
    // Assignment operators
    Matrix& operator=(const Matrix& other);
    Matrix& operator=(Matrix&& other) noexcept;
    
    // Access operators
    double& operator()(size_t row, size_t col);
    const double& operator()(size_t row, size_t col) const;
    std::vector<double>& operator[](size_t row);
    const std::vector<double>& operator[](size_t row) const;
    
    // Arithmetic operators
    Matrix operator+(const Matrix& other) const;
    Matrix operator-(const Matrix& other) const;
    Matrix operator*(const Matrix& other) const;
    Matrix operator*(double scalar) const;
    Matrix operator/(double scalar) const;
    
    // Compound assignment operators
    Matrix& operator+=(const Matrix& other);
    Matrix& operator-=(const Matrix& other);
    Matrix& operator*=(const Matrix& other);
    Matrix& operator*=(double scalar);
    Matrix& operator/=(double scalar);
    
    // Comparison operators
    bool operator==(const Matrix& other) const;
    bool operator!=(const Matrix& other) const;
    
    // Matrix operations
    Matrix transpose() const;
    double determinant() const;
    Matrix inverse() const;
    Matrix adjoint() const;
    Matrix cofactor() const;
    double trace() const;
    double norm() const;
    
    // Utility methods
    void fill(double value);
    void fillRandom(double min = 0.0, double max = 1.0);
    void identity();
    void swapRows(size_t row1, size_t row2);
    void swapColumns(size_t col1, size_t col2);
    void multiplyRow(size_t row, double factor);
    void addRow(size_t row1, size_t row2, double factor = 1.0);
    
    // Getters
    size_t getRows() const { return rows; }
    size_t getCols() const { return cols; }
    bool isSquare() const { return rows == cols; }
    bool isEmpty() const { return rows == 0 || cols == 0; }
    
    // Display methods
    void display() const;
    void displayFormatted(int precision = 2) const;
    std::string toString() const;
    std::string toStringFormatted(int precision = 2) const;
    
    // Input methods
    void readFromInput();
    void readFromFile(const std::string& filename);
    void writeToFile(const std::string& filename) const;
};

// Non-member operators
Matrix operator*(double scalar, const Matrix& matrix);
std::ostream& operator<<(std::ostream& os, const Matrix& matrix);
std::istream& operator>>(std::istream& is, Matrix& matrix);

#endif // MATRIX_H
```

#### Matrix.cpp (Key Methods)
```cpp
#include "Matrix.h"
#include <algorithm>
#include <iomanip>
#include <stdexcept>
#include <random>
#include <fstream>
#include <sstream>

Matrix::Matrix(size_t rows, size_t cols) 
    : rows(rows), cols(cols) {
    if (rows == 0 || cols == 0) {
        throw std::invalid_argument("Matrix dimensions must be positive");
    }
    data.resize(rows, std::vector<double>(cols, 0.0));
}

Matrix::Matrix(const std::vector<std::vector<double>>& inputData) {
    if (inputData.empty() || inputData[0].empty()) {
        throw std::invalid_argument("Matrix data cannot be empty");
    }
    
    rows = inputData.size();
    cols = inputData[0].size();
    data = inputData;
    
    // Validate all rows have same number of columns
    for (const auto& row : data) {
        if (row.size() != cols) {
            throw std::invalid_argument("All rows must have the same number of columns");
        }
    }
}

Matrix Matrix::operator+(const Matrix& other) const {
    validateDimensions(other, "addition");
    
    Matrix result(rows, cols);
    for (size_t i = 0; i < rows; ++i) {
        for (size_t j = 0; j < cols; ++j) {
            result.data[i][j] = data[i][j] + other.data[i][j];
        }
    }
    return result;
}

Matrix Matrix::operator*(const Matrix& other) const {
    if (cols != other.rows) {
        throw std::invalid_argument("Matrix dimensions incompatible for multiplication");
    }
    
    Matrix result(rows, other.cols);
    for (size_t i = 0; i < rows; ++i) {
        for (size_t j = 0; j < other.cols; ++j) {
            for (size_t k = 0; k < cols; ++k) {
                result.data[i][j] += data[i][k] * other.data[k][j];
            }
        }
    }
    return result;
}

Matrix Matrix::transpose() const {
    Matrix result(cols, rows);
    for (size_t i = 0; i < rows; ++i) {
        for (size_t j = 0; j < cols; ++j) {
            result.data[j][i] = data[i][j];
        }
    }
    return result;
}

double Matrix::determinant() const {
    if (!isSquare()) {
        throw std::invalid_argument("Determinant can only be calculated for square matrices");
    }
    
    if (rows == 1) {
        return data[0][0];
    }
    
    if (rows == 2) {
        return data[0][0] * data[1][1] - data[0][1] * data[1][0];
    }
    
    // For larger matrices, use LU decomposition
    Matrix temp = *this;
    double det = 1.0;
    
    for (size_t i = 0; i < rows; ++i) {
        // Find pivot
        size_t maxRow = i;
        for (size_t k = i + 1; k < rows; ++k) {
            if (std::abs(temp.data[k][i]) > std::abs(temp.data[maxRow][i])) {
                maxRow = k;
            }
        }
        
        if (maxRow != i) {
            temp.swapRows(i, maxRow);
            det *= -1;
        }
        
        if (std::abs(temp.data[i][i]) < 1e-10) {
            return 0.0; // Singular matrix
        }
        
        det *= temp.data[i][i];
        
        // Eliminate column
        for (size_t k = i + 1; k < rows; ++k) {
            double factor = temp.data[k][i] / temp.data[i][i];
            temp.addRow(k, i, -factor);
        }
    }
    
    return det;
}

Matrix Matrix::inverse() const {
    if (!isSquare()) {
        throw std::invalid_argument("Inverse can only be calculated for square matrices");
    }
    
    double det = determinant();
    if (std::abs(det) < 1e-10) {
        throw std::invalid_argument("Matrix is singular (determinant is zero)");
    }
    
    // Create augmented matrix [A|I]
    Matrix augmented(rows, 2 * cols);
    
    // Copy original matrix
    for (size_t i = 0; i < rows; ++i) {
        for (size_t j = 0; j < cols; ++j) {
            augmented.data[i][j] = data[i][j];
        }
    }
    
    // Add identity matrix
    for (size_t i = 0; i < rows; ++i) {
        augmented.data[i][cols + i] = 1.0;
    }
    
    // Gaussian elimination
    for (size_t i = 0; i < rows; ++i) {
        // Find pivot
        size_t maxRow = i;
        for (size_t k = i + 1; k < rows; ++k) {
            if (std::abs(augmented.data[k][i]) > std::abs(augmented.data[maxRow][i])) {
                maxRow = k;
            }
        }
        
        if (maxRow != i) {
            augmented.swapRows(i, maxRow);
        }
        
        // Make diagonal element 1
        double pivot = augmented.data[i][i];
        augmented.multiplyRow(i, 1.0 / pivot);
        
        // Eliminate column
        for (size_t k = 0; k < rows; ++k) {
            if (k != i) {
                double factor = augmented.data[k][i];
                augmented.addRow(k, i, -factor);
            }
        }
    }
    
    // Extract inverse matrix
    Matrix result(rows, cols);
    for (size_t i = 0; i < rows; ++i) {
        for (size_t j = 0; j < cols; ++j) {
            result.data[i][j] = augmented.data[i][cols + j];
        }
    }
    
    return result;
}

void Matrix::displayFormatted(int precision) const {
    std::cout << std::fixed << std::setprecision(precision);
    for (size_t i = 0; i < rows; ++i) {
        std::cout << "[";
        for (size_t j = 0; j < cols; ++j) {
            std::cout << std::setw(precision + 4) << data[i][j];
            if (j < cols - 1) std::cout << " ";
        }
        std::cout << "]" << std::endl;
    }
}
```

### 2. Matrix Calculator Class

#### MatrixCalculator.h
```cpp
#ifndef MATRIX_CALCULATOR_H
#define MATRIX_CALCULATOR_H

#include "Matrix.h"
#include <vector>
#include <memory>

class MatrixCalculator {
private:
    std::vector<std::unique_ptr<Matrix>> matrices;
    
    // Helper methods
    int getMatrixIndex(const std::string& name) const;
    void displayMenu() const;
    void createMatrix();
    void loadMatrix();
    void saveMatrix();
    void displayMatrix();
    void performAddition();
    void performSubtraction();
    void performMultiplication();
    void performTranspose();
    void calculateDeterminant();
    void calculateInverse();
    void performScalarOperation();
    void compareMatrices();
    
public:
    MatrixCalculator();
    ~MatrixCalculator() = default;
    
    void run();
    void addMatrix(std::unique_ptr<Matrix> matrix);
    void removeMatrix(int index);
    void clearAllMatrices();
    size_t getMatrixCount() const;
    const Matrix* getMatrix(int index) const;
};

#endif // MATRIX_CALCULATOR_H
```

### 3. File Handler Class

#### FileHandler.h
```cpp
#ifndef FILE_HANDLER_H
#define FILE_HANDLER_H

#include "Matrix.h"
#include <string>
#include <vector>

class FileHandler {
public:
    static bool saveMatrix(const Matrix& matrix, const std::string& filename);
    static Matrix loadMatrix(const std::string& filename);
    static bool saveMultipleMatrices(const std::vector<Matrix>& matrices, 
                                   const std::string& filename);
    static std::vector<Matrix> loadMultipleMatrices(const std::string& filename);
    static bool fileExists(const std::string& filename);
    static std::vector<std::string> getMatrixFiles(const std::string& directory);
};

#endif // FILE_HANDLER_H
```

## User Interface Design

### Main Application
```cpp
#include "MatrixCalculator.h"
#include "InputValidator.h"
#include <iostream>
#include <iomanip>

int main() {
    std::cout << "===========================================" << std::endl;
    std::cout << "        MATRIX CALCULATOR v2.0            " << std::endl;
    std::cout << "===========================================" << std::endl;
    std::cout << std::endl;
    
    try {
        MatrixCalculator calculator;
        calculator.run();
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
        return 1;
    }
    
    std::cout << "Thank you for using Matrix Calculator!" << std::endl;
    return 0;
}
```

### Menu System
```cpp
void MatrixCalculator::displayMenu() const {
    std::cout << "\n=== MATRIX CALCULATOR MENU ===" << std::endl;
    std::cout << "1.  Create new matrix" << std::endl;
    std::cout << "2.  Load matrix from file" << std::endl;
    std::cout << "3.  Save matrix to file" << std::endl;
    std::cout << "4.  Display matrix" << std::endl;
    std::cout << "5.  Matrix addition" << std::endl;
    std::cout << "6.  Matrix subtraction" << std::endl;
    std::cout << "7.  Matrix multiplication" << std::endl;
    std::cout << "8.  Matrix transpose" << std::endl;
    std::cout << "9.  Calculate determinant" << std::endl;
    std::cout << "10. Calculate inverse" << std::endl;
    std::cout << "11. Scalar operations" << std::endl;
    std::cout << "12. Compare matrices" << std::endl;
    std::cout << "13. List all matrices" << std::endl;
    std::cout << "14. Clear all matrices" << std::endl;
    std::cout << "15. Help" << std::endl;
    std::cout << "0.  Exit" << std::endl;
    std::cout << "===============================" << std::endl;
    std::cout << "Enter your choice: ";
}
```

## Advanced Features

### Matrix Operations with Error Handling
```cpp
void MatrixCalculator::performMultiplication() {
    if (matrices.size() < 2) {
        std::cout << "You need at least 2 matrices for multiplication!" << std::endl;
        return;
    }
    
    try {
        std::cout << "\nAvailable matrices:" << std::endl;
        for (size_t i = 0; i < matrices.size(); ++i) {
            std::cout << i << ": " << matrices[i]->getRows() << "x" 
                      << matrices[i]->getCols() << std::endl;
        }
        
        size_t index1, index2;
        std::cout << "Enter index of first matrix: ";
        std::cin >> index1;
        std::cout << "Enter index of second matrix: ";
        std::cin >> index2;
        
        if (index1 >= matrices.size() || index2 >= matrices.size()) {
            std::cout << "Invalid matrix index!" << std::endl;
            return;
        }
        
        Matrix result = (*matrices[index1]) * (*matrices[index2]);
        
        std::cout << "\nMultiplication result:" << std::endl;
        result.displayFormatted(3);
        
        std::cout << "\nSave result as new matrix? (y/n): ";
        char save;
        std::cin >> save;
        if (save == 'y' || save == 'Y') {
            matrices.push_back(std::make_unique<Matrix>(std::move(result)));
            std::cout << "Result saved as matrix " << matrices.size() - 1 << std::endl;
        }
        
    } catch (const std::exception& e) {
        std::cout << "Error: " << e.what() << std::endl;
    }
}
```

### Input Validation
```cpp
class InputValidator {
public:
    static bool isValidMatrixDimensions(size_t rows, size_t cols) {
        return rows > 0 && rows <= 100 && cols > 0 && cols <= 100;
    }
    
    static bool isValidMatrixIndex(int index, size_t size) {
        return index >= 0 && index < static_cast<int>(size);
    }
    
    static bool isValidFilename(const std::string& filename) {
        if (filename.empty() || filename.length() > 255) {
            return false;
        }
        
        // Check for invalid characters
        std::string invalidChars = "<>:\"|?*";
        for (char c : invalidChars) {
            if (filename.find(c) != std::string::npos) {
                return false;
            }
        }
        
        return true;
    }
    
    static double getValidDouble(const std::string& prompt) {
        double value;
        while (true) {
            std::cout << prompt;
            if (std::cin >> value) {
                return value;
            } else {
                std::cout << "Invalid input! Please enter a valid number." << std::endl;
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            }
        }
    }
};
```

## Cross-Platform Considerations

### CMakeLists.txt
```cmake
cmake_minimum_required(VERSION 3.10)
project(MatrixCalculator)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Add compiler flags
if(MSVC)
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /W4")
else()
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -Wpedantic")
endif()

# Source files
set(SOURCES
    src/Matrix.cpp
    src/MatrixCalculator.cpp
    src/FileHandler.cpp
    src/utils/InputValidator.cpp
    src/main.cpp
)

# Header files
set(HEADERS
    src/Matrix.h
    src/MatrixCalculator.h
    src/FileHandler.h
    src/utils/InputValidator.h
)

# Create executable
add_executable(${PROJECT_NAME} ${SOURCES} ${HEADERS})

# Include directories
target_include_directories(${PROJECT_NAME} PRIVATE src)

# Testing
enable_testing()
add_subdirectory(tests)
```

### Makefile
```makefile
CXX = g++
CXXFLAGS = -std=c++17 -Wall -Wextra -Wpedantic -O2
TARGET = matrix_calculator
SRCDIR = src
OBJDIR = obj
SOURCES = $(wildcard $(SRCDIR)/*.cpp) $(wildcard $(SRCDIR)/utils/*.cpp)
OBJECTS = $(SOURCES:$(SRCDIR)/%.cpp=$(OBJDIR)/%.o)

# Platform detection
ifeq ($(OS),Windows_NT)
    TARGET := $(TARGET).exe
    RM = del /Q
else
    RM = rm -f
endif

$(TARGET): $(OBJECTS)
	$(CXX) $(OBJECTS) -o $(TARGET)

$(OBJDIR)/%.o: $(SRCDIR)/%.cpp
	@mkdir -p $(dir $@)
	$(CXX) $(CXXFLAGS) -c $< -o $@

clean:
	$(RM) $(OBJECTS) $(TARGET)

.PHONY: clean
```

## Testing Strategy

### Unit Tests
```cpp
#include <gtest/gtest.h>
#include "Matrix.h"

class MatrixTest : public ::testing::Test {
protected:
    void SetUp() override {
        matrix1 = Matrix(2, 2);
        matrix1(0, 0) = 1; matrix1(0, 1) = 2;
        matrix1(1, 0) = 3; matrix1(1, 1) = 4;
        
        matrix2 = Matrix(2, 2);
        matrix2(0, 0) = 5; matrix2(0, 1) = 6;
        matrix2(1, 0) = 7; matrix2(1, 1) = 8;
    }
    
    Matrix matrix1, matrix2;
};

TEST_F(MatrixTest, Addition) {
    Matrix result = matrix1 + matrix2;
    EXPECT_EQ(result(0, 0), 6);
    EXPECT_EQ(result(0, 1), 8);
    EXPECT_EQ(result(1, 0), 10);
    EXPECT_EQ(result(1, 1), 12);
}

TEST_F(MatrixTest, Multiplication) {
    Matrix result = matrix1 * matrix2;
    EXPECT_EQ(result(0, 0), 19);
    EXPECT_EQ(result(0, 1), 22);
    EXPECT_EQ(result(1, 0), 43);
    EXPECT_EQ(result(1, 1), 50);
}

TEST_F(MatrixTest, Determinant) {
    EXPECT_EQ(matrix1.determinant(), -2);
}

TEST_F(MatrixTest, Transpose) {
    Matrix transposed = matrix1.transpose();
    EXPECT_EQ(transposed(0, 0), 1);
    EXPECT_EQ(transposed(0, 1), 3);
    EXPECT_EQ(transposed(1, 0), 2);
    EXPECT_EQ(transposed(1, 1), 4);
}
```

## Performance Requirements
- Support matrices up to 100x100
- Operations complete in < 1 second for 50x50 matrices
- Memory usage < 100MB for maximum matrix size
- Determinant calculation in O(n³) time complexity
- Matrix inversion in O(n³) time complexity

## Extension Ideas
- Sparse matrix support for memory efficiency
- GPU acceleration using CUDA or OpenCL
- Parallel processing for large matrices
- Matrix decomposition (LU, QR, SVD)
- Eigenvalue and eigenvector calculation
- Matrix visualization tools
- Web interface using Emscripten
- Mobile application using C++

## Deliverables
1. Complete source code with proper class structure
2. CMake and Makefile build configurations
3. Comprehensive unit tests
4. Documentation (README, code comments)
5. Sample matrix files for testing
6. Build and run scripts for all platforms
7. User manual with examples

## Success Criteria
- All matrix operations work correctly
- Error handling prevents crashes
- File I/O operations work reliably
- Code compiles and runs on all target platforms
- Unit tests achieve 90% code coverage
- Performance meets specified requirements
- User interface is intuitive and responsive
