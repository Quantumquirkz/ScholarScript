# Arrays

## Overview
Arrays are one of the most fundamental data structures in computer science. They provide a way to store multiple elements of the same type in contiguous memory locations, allowing for efficient access and manipulation.

## Key Concepts

### What are Arrays?
An array is a collection of elements of the same data type stored in contiguous memory locations. Each element can be accessed directly using an index.

### Properties of Arrays
1. **Fixed Size**: Size is determined at creation and cannot be changed
2. **Homogeneous**: All elements must be of the same data type
3. **Contiguous Memory**: Elements are stored in adjacent memory locations
4. **Random Access**: Any element can be accessed in O(1) time using its index

## One-Dimensional Arrays

### Declaration and Initialization
```cpp
#include <iostream>
#include <array>

int main() {
    // Method 1: Declare and initialize
    int numbers[5] = {1, 2, 3, 4, 5};
    
    // Method 2: Declare first, then initialize
    int scores[3];
    scores[0] = 95;
    scores[1] = 87;
    scores[2] = 92;
    
    // Method 3: Using std::array (C++11)
    std::array<int, 5> modernArray = {10, 20, 30, 40, 50};
    
    // Method 4: Partial initialization
    int partialArray[5] = {1, 2}; // Rest are initialized to 0
    
    // Display arrays
    std::cout << "Traditional array: ";
    for (int i = 0; i < 5; i++) {
        std::cout << numbers[i] << " ";
    }
    std::cout << std::endl;
    
    std::cout << "Modern array: ";
    for (int num : modernArray) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

### Basic Operations
```cpp
#include <iostream>
#include <algorithm>

class ArrayOperations {
private:
    int* arr;
    int size;
    int capacity;
    
public:
    ArrayOperations(int cap) : capacity(cap), size(0) {
        arr = new int[capacity];
    }
    
    ~ArrayOperations() {
        delete[] arr;
    }
    
    // Insert element at end
    void insert(int element) {
        if (size < capacity) {
            arr[size] = element;
            size++;
        } else {
            std::cout << "Array is full!" << std::endl;
        }
    }
    
    // Insert element at specific index
    void insertAt(int index, int element) {
        if (index >= 0 && index <= size && size < capacity) {
            // Shift elements to the right
            for (int i = size; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = element;
            size++;
        } else {
            std::cout << "Invalid index or array is full!" << std::endl;
        }
    }
    
    // Delete element at specific index
    void deleteAt(int index) {
        if (index >= 0 && index < size) {
            // Shift elements to the left
            for (int i = index; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        } else {
            std::cout << "Invalid index!" << std::endl;
        }
    }
    
    // Search for element
    int search(int element) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1; // Not found
    }
    
    // Update element at index
    bool update(int index, int element) {
        if (index >= 0 && index < size) {
            arr[index] = element;
            return true;
        }
        return false;
    }
    
    // Display array
    void display() {
        std::cout << "Array: ";
        for (int i = 0; i < size; i++) {
            std::cout << arr[i] << " ";
        }
        std::cout << std::endl;
    }
    
    // Get array size
    int getSize() const { return size; }
    
    // Get array capacity
    int getCapacity() const { return capacity; }
};

int main() {
    ArrayOperations array(10);
    
    // Insert elements
    array.insert(10);
    array.insert(20);
    array.insert(30);
    array.display();
    
    // Insert at specific position
    array.insertAt(1, 15);
    array.display();
    
    // Search for element
    int index = array.search(20);
    std::cout << "Element 20 found at index: " << index << std::endl;
    
    // Update element
    array.update(2, 25);
    array.display();
    
    // Delete element
    array.deleteAt(1);
    array.display();
    
    return 0;
}
```

### Dynamic Arrays (Vectors)
```cpp
#include <iostream>
#include <vector>

class DynamicArrayDemo {
public:
    static void demonstrateVectorOperations() {
        // Create vector
        std::vector<int> vec;
        
        // Add elements
        vec.push_back(1);
        vec.push_back(2);
        vec.push_back(3);
        vec.push_back(4);
        vec.push_back(5);
        
        std::cout << "Vector size: " << vec.size() << std::endl;
        std::cout << "Vector capacity: " << vec.capacity() << std::endl;
        
        // Access elements
        std::cout << "First element: " << vec.front() << std::endl;
        std::cout << "Last element: " << vec.back() << std::endl;
        std::cout << "Element at index 2: " << vec[2] << std::endl;
        
        // Insert element
        vec.insert(vec.begin() + 2, 10);
        std::cout << "After inserting 10 at index 2: ";
        for (int num : vec) {
            std::cout << num << " ";
        }
        std::cout << std::endl;
        
        // Remove element
        vec.erase(vec.begin() + 2);
        std::cout << "After removing element at index 2: ";
        for (int num : vec) {
            std::cout << num << " ";
        }
        std::cout << std::endl;
        
        // Resize vector
        vec.resize(3);
        std::cout << "After resizing to 3: ";
        for (int num : vec) {
            std::cout << num << " ";
        }
        std::cout << std::endl;
    }
};
```

## Multi-Dimensional Arrays

### Two-Dimensional Arrays
```cpp
#include <iostream>
#include <vector>

class TwoDArrayDemo {
public:
    static void demonstrateStatic2DArray() {
        // Static 2D array
        int matrix[3][4] = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        std::cout << "Static 2D Array:" << std::endl;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                std::cout << matrix[i][j] << " ";
            }
            std::cout << std::endl;
        }
    }
    
    static void demonstrateDynamic2DArray() {
        // Dynamic 2D array using vector
        std::vector<std::vector<int>> dynamicMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        std::cout << "\nDynamic 2D Array:" << std::endl;
        for (const auto& row : dynamicMatrix) {
            for (int element : row) {
                std::cout << element << " ";
            }
            std::cout << std::endl;
        }
        
        // Add new row
        dynamicMatrix.push_back({10, 11, 12});
        
        // Add new column
        for (auto& row : dynamicMatrix) {
            row.push_back(0);
        }
        
        std::cout << "\nAfter adding row and column:" << std::endl;
        for (const auto& row : dynamicMatrix) {
            for (int element : row) {
                std::cout << element << " ";
            }
            std::cout << std::endl;
        }
    }
    
    static void demonstrateMatrixOperations() {
        std::vector<std::vector<int>> matrix1 = {{1, 2}, {3, 4}};
        std::vector<std::vector<int>> matrix2 = {{5, 6}, {7, 8}};
        std::vector<std::vector<int>> result(2, std::vector<int>(2, 0));
        
        // Matrix addition
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        
        std::cout << "\nMatrix Addition Result:" << std::endl;
        for (const auto& row : result) {
            for (int element : row) {
                std::cout << element << " ";
            }
            std::cout << std::endl;
        }
        
        // Matrix multiplication
        std::vector<std::vector<int>> multResult(2, std::vector<int>(2, 0));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    multResult[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        
        std::cout << "\nMatrix Multiplication Result:" << std::endl;
        for (const auto& row : multResult) {
            for (int element : row) {
                std::cout << element << " ";
            }
            std::cout << std::endl;
        }
    }
};
```

### Three-Dimensional Arrays
```cpp
#include <iostream>
#include <vector>

class ThreeDArrayDemo {
public:
    static void demonstrate3DArray() {
        // 3D array: 2x3x4
        std::vector<std::vector<std::vector<int>>> cube(2, 
            std::vector<std::vector<int>>(3, 
                std::vector<int>(4, 0)));
        
        // Initialize with values
        int value = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    cube[i][j][k] = value++;
                }
            }
        }
        
        std::cout << "3D Array:" << std::endl;
        for (int i = 0; i < 2; i++) {
            std::cout << "Layer " << i << ":" << std::endl;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    std::cout << cube[i][j][k] << " ";
                }
                std::cout << std::endl;
            }
            std::cout << std::endl;
        }
    }
};
```

## Matrix Operations

### Advanced Matrix Operations
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

class MatrixOperations {
public:
    // Transpose matrix
    static std::vector<std::vector<int>> transpose(const std::vector<std::vector<int>>& matrix) {
        int rows = matrix.size();
        int cols = matrix[0].size();
        
        std::vector<std::vector<int>> result(cols, std::vector<int>(rows));
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        
        return result;
    }
    
    // Find maximum element
    static int findMax(const std::vector<std::vector<int>>& matrix) {
        int maxElement = matrix[0][0];
        
        for (const auto& row : matrix) {
            for (int element : row) {
                maxElement = std::max(maxElement, element);
            }
        }
        
        return maxElement;
    }
    
    // Find minimum element
    static int findMin(const std::vector<std::vector<int>>& matrix) {
        int minElement = matrix[0][0];
        
        for (const auto& row : matrix) {
            for (int element : row) {
                minElement = std::min(minElement, element);
            }
        }
        
        return minElement;
    }
    
    // Calculate sum of all elements
    static int calculateSum(const std::vector<std::vector<int>>& matrix) {
        int sum = 0;
        
        for (const auto& row : matrix) {
            for (int element : row) {
                sum += element;
            }
        }
        
        return sum;
    }
    
    // Check if matrix is square
    static bool isSquare(const std::vector<std::vector<int>>& matrix) {
        int rows = matrix.size();
        if (rows == 0) return false;
        
        int cols = matrix[0].size();
        return rows == cols;
    }
    
    // Rotate matrix 90 degrees clockwise
    static std::vector<std::vector<int>> rotate90Clockwise(const std::vector<std::vector<int>>& matrix) {
        int n = matrix.size();
        std::vector<std::vector<int>> result(n, std::vector<int>(n));
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j];
            }
        }
        
        return result;
    }
    
    // Display matrix
    static void displayMatrix(const std::vector<std::vector<int>>& matrix) {
        for (const auto& row : matrix) {
            for (int element : row) {
                std::cout << element << " ";
            }
            std::cout << std::endl;
        }
    }
};

int main() {
    std::vector<std::vector<int>> matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    
    std::cout << "Original Matrix:" << std::endl;
    MatrixOperations::displayMatrix(matrix);
    
    std::cout << "\nMax element: " << MatrixOperations::findMax(matrix) << std::endl;
    std::cout << "Min element: " << MatrixOperations::findMin(matrix) << std::endl;
    std::cout << "Sum of elements: " << MatrixOperations::calculateSum(matrix) << std::endl;
    std::cout << "Is square matrix: " << (MatrixOperations::isSquare(matrix) ? "Yes" : "No") << std::endl;
    
    std::cout << "\nTransposed Matrix:" << std::endl;
    auto transposed = MatrixOperations::transpose(matrix);
    MatrixOperations::displayMatrix(transposed);
    
    std::cout << "\nRotated Matrix (90° clockwise):" << std::endl;
    auto rotated = MatrixOperations::rotate90Clockwise(matrix);
    MatrixOperations::displayMatrix(rotated);
    
    return 0;
}
```

## Array Algorithms

### Common Array Algorithms
```cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>

class ArrayAlgorithms {
public:
    // Linear search
    static int linearSearch(const std::vector<int>& arr, int target) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    // Binary search (requires sorted array)
    static int binarySearch(const std::vector<int>& arr, int target) {
        int left = 0, right = arr.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // Selection sort
    static void selectionSort(std::vector<int>& arr) {
        int n = arr.size();
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                std::swap(arr[i], arr[minIndex]);
            }
        }
    }
    
    // Bubble sort
    static void bubbleSort(std::vector<int>& arr) {
        int n = arr.size();
        
        for (int i = 0; i < n - 1; i++) {
            bool swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    std::swap(arr[j], arr[j + 1]);
                    swapped = true;
                }
            }
            
            if (!swapped) break; // Array is sorted
        }
    }
    
    // Find maximum subarray sum (Kadane's algorithm)
    static int maxSubarraySum(const std::vector<int>& arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.size(); i++) {
            maxEndingHere = std::max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = std::max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    // Find duplicate elements
    static std::vector<int> findDuplicates(const std::vector<int>& arr) {
        std::vector<int> duplicates;
        std::vector<bool> visited(arr.size(), false);
        
        for (int i = 0; i < arr.size(); i++) {
            if (!visited[i]) {
                bool foundDuplicate = false;
                
                for (int j = i + 1; j < arr.size(); j++) {
                    if (arr[i] == arr[j] && !visited[j]) {
                        visited[j] = true;
                        foundDuplicate = true;
                    }
                }
                
                if (foundDuplicate) {
                    duplicates.push_back(arr[i]);
                }
                
                visited[i] = true;
            }
        }
        
        return duplicates;
    }
    
    // Reverse array
    static void reverseArray(std::vector<int>& arr) {
        int left = 0, right = arr.size() - 1;
        
        while (left < right) {
            std::swap(arr[left], arr[right]);
            left++;
            right--;
        }
    }
    
    // Rotate array to the right by k positions
    static void rotateArray(std::vector<int>& arr, int k) {
        int n = arr.size();
        k = k % n; // Handle k > n
        
        reverseArray(arr);
        
        // Reverse first k elements
        for (int i = 0; i < k / 2; i++) {
            std::swap(arr[i], arr[k - 1 - i]);
        }
        
        // Reverse remaining elements
        for (int i = k; i < (n + k) / 2; i++) {
            std::swap(arr[i], arr[n - 1 - (i - k)]);
        }
    }
};

int main() {
    std::vector<int> numbers = {64, 34, 25, 12, 22, 11, 90, 25};
    
    std::cout << "Original array: ";
    for (int num : numbers) std::cout << num << " ";
    std::cout << std::endl;
    
    // Search operations
    int index = ArrayAlgorithms::linearSearch(numbers, 25);
    std::cout << "Linear search for 25: " << index << std::endl;
    
    // Sort array for binary search
    std::vector<int> sortedNumbers = numbers;
    ArrayAlgorithms::selectionSort(sortedNumbers);
    std::cout << "Sorted array: ";
    for (int num : sortedNumbers) std::cout << num << " ";
    std::cout << std::endl;
    
    int binaryIndex = ArrayAlgorithms::binarySearch(sortedNumbers, 25);
    std::cout << "Binary search for 25: " << binaryIndex << std::endl;
    
    // Find duplicates
    auto duplicates = ArrayAlgorithms::findDuplicates(numbers);
    std::cout << "Duplicates: ";
    for (int dup : duplicates) std::cout << dup << " ";
    std::cout << std::endl;
    
    // Max subarray sum
    std::vector<int> testArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int maxSum = ArrayAlgorithms::maxSubarraySum(testArray);
    std::cout << "Maximum subarray sum: " << maxSum << std::endl;
    
    // Rotate array
    std::vector<int> rotateArray = {1, 2, 3, 4, 5};
    ArrayAlgorithms::rotateArray(rotateArray, 2);
    std::cout << "Rotated array: ";
    for (int num : rotateArray) std::cout << num << " ";
    std::cout << std::endl;
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o arrays main.cpp
./arrays

# Windows (PowerShell)
g++ -std=c++17 -O2 -o arrays.exe main.cpp
./arrays.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o arrays.exe main.cpp
arrays.exe
```

### Memory Management
```cpp
#include <iostream>
#include <memory>

class MemoryManagementDemo {
public:
    static void demonstrateSmartPointers() {
        // Using unique_ptr for dynamic arrays
        auto dynamicArray = std::make_unique<int[]>(10);
        
        // Initialize array
        for (int i = 0; i < 10; i++) {
            dynamicArray[i] = i * i;
        }
        
        std::cout << "Dynamic array: ";
        for (int i = 0; i < 10; i++) {
            std::cout << dynamicArray[i] << " ";
        }
        std::cout << std::endl;
        
        // Memory is automatically freed when unique_ptr goes out of scope
    }
};
```

## Best Practices

1. **Use std::vector for Dynamic Arrays**: Prefer vector over raw arrays
2. **Use std::array for Fixed-Size Arrays**: Use std::array for compile-time known sizes
3. **Bounds Checking**: Always validate array indices
4. **Memory Management**: Use smart pointers for dynamic memory
5. **Algorithm Selection**: Choose appropriate algorithms based on data characteristics

## Common Pitfalls

1. **Array Bounds**: Accessing elements outside array bounds
2. **Memory Leaks**: Not freeing dynamically allocated arrays
3. **Shallow vs Deep Copy**: Understanding when copying arrays
4. **Size Mismatches**: Using wrong array sizes in operations
5. **Uninitialized Arrays**: Using arrays before initialization

## Exercises

1. Implement a dynamic array class with automatic resizing
2. Create a matrix class with common operations (addition, multiplication, transpose)
3. Implement various sorting algorithms and compare their performance
4. Build a sparse matrix representation for memory efficiency
5. Create a 2D array-based game (like tic-tac-toe or minesweeper)

## Learning Outcomes
- Understanding array fundamentals and memory layout
- Learning one-dimensional and multi-dimensional array operations
- Implementing common array algorithms and data structures
- Understanding time and space complexity of array operations
- Cross-platform C++ development for array-based programs
