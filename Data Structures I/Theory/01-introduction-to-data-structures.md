# Introduction to Data Structures

## Overview
Data structures are specialized formats for organizing, processing, retrieving and storing data. They are fundamental building blocks of computer science and programming, enabling efficient algorithms and data management.

## Key Concepts

### What are Data Structures?
Data structures are ways of organizing data in a computer so that it can be used efficiently. They provide a systematic way to access and update data, making algorithms more efficient and programs more maintainable.

### Importance of Data Structures
1. **Efficiency**: Proper data structures can dramatically improve algorithm performance
2. **Organization**: Help organize data in logical and meaningful ways
3. **Reusability**: Provide standardized ways to handle common data operations
4. **Scalability**: Enable programs to handle large amounts of data efficiently

### Types of Data Structures

#### Linear Data Structures
- **Arrays**: Contiguous memory locations storing elements
- **Linked Lists**: Elements connected via pointers
- **Stacks**: Last In, First Out (LIFO) structure
- **Queues**: First In, First Out (FIFO) structure

#### Non-Linear Data Structures
- **Trees**: Hierarchical data organization
- **Graphs**: Network of connected nodes
- **Hash Tables**: Key-value pairs with fast lookup

## Algorithmic Efficiency (Big O Notation)

### Time Complexity
Big O notation describes how the runtime of an algorithm grows relative to the input size.

```cpp
#include <iostream>
#include <vector>
#include <chrono>

// O(1) - Constant Time
int getFirstElement(const std::vector<int>& arr) {
    if (!arr.empty()) {
        return arr[0];
    }
    return -1;
}

// O(n) - Linear Time
int findElement(const std::vector<int>& arr, int target) {
    for (int i = 0; i < arr.size(); i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}

// O(n²) - Quadratic Time
void bubbleSort(std::vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                std::swap(arr[j], arr[j + 1]);
            }
        }
    }
}

// O(log n) - Logarithmic Time
int binarySearch(const std::vector<int>& arr, int target) {
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

int main() {
    std::vector<int> numbers = {64, 34, 25, 12, 22, 11, 90};
    
    // Demonstrate different time complexities
    std::cout << "First element (O(1)): " << getFirstElement(numbers) << std::endl;
    
    int index = findElement(numbers, 25);
    std::cout << "Element 25 found at index (O(n)): " << index << std::endl;
    
    bubbleSort(numbers);
    std::cout << "Sorted array (O(n²)): ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    int found = binarySearch(numbers, 25);
    std::cout << "Binary search result (O(log n)): " << found << std::endl;
    
    return 0;
}
```

### Space Complexity
Space complexity measures the amount of memory space required by an algorithm.

```cpp
#include <iostream>
#include <vector>

// O(1) Space - Constant Space
int sumArray(const std::vector<int>& arr) {
    int sum = 0;
    for (int num : arr) {
        sum += num;
    }
    return sum;
}

// O(n) Space - Linear Space
std::vector<int> copyArray(const std::vector<int>& arr) {
    std::vector<int> copy(arr);
    return copy;
}

// O(n²) Space - Quadratic Space
std::vector<std::vector<int>> createMatrix(int n) {
    return std::vector<std::vector<int>>(n, std::vector<int>(n, 0));
}

int main() {
    std::vector<int> numbers = {1, 2, 3, 4, 5};
    
    std::cout << "Sum (O(1) space): " << sumArray(numbers) << std::endl;
    
    std::vector<int> copy = copyArray(numbers);
    std::cout << "Copy created (O(n) space)" << std::endl;
    
    auto matrix = createMatrix(3);
    std::cout << "Matrix created (O(n²) space)" << std::endl;
    
    return 0;
}
```

### Common Big O Complexities

| Complexity | Name | Example |
|------------|------|---------|
| O(1) | Constant | Array access by index |
| O(log n) | Logarithmic | Binary search |
| O(n) | Linear | Linear search |
| O(n log n) | Linearithmic | Merge sort, heap sort |
| O(n²) | Quadratic | Bubble sort, selection sort |
| O(2ⁿ) | Exponential | Recursive Fibonacci |
| O(n!) | Factorial | Permutations |

## Choosing the Right Data Structure

### Factors to Consider

1. **Access Patterns**: How will data be accessed?
2. **Insertion/Deletion Frequency**: How often will data be modified?
3. **Memory Constraints**: How much memory is available?
4. **Search Requirements**: How often will you need to find elements?

### Decision Matrix

```cpp
#include <iostream>
#include <vector>
#include <list>
#include <stack>
#include <queue>
#include <unordered_map>
#include <set>

class DataStructureComparison {
public:
    static void demonstrateAccess() {
        std::cout << "=== Access Patterns ===" << std::endl;
        
        // Array - O(1) random access
        std::vector<int> arr = {1, 2, 3, 4, 5};
        std::cout << "Array access arr[2]: " << arr[2] << " (O(1))" << std::endl;
        
        // Linked List - O(n) sequential access
        std::list<int> linkedList = {1, 2, 3, 4, 5};
        auto it = linkedList.begin();
        std::advance(it, 2); // Move to 3rd element
        std::cout << "Linked list access 3rd element: " << *it << " (O(n))" << std::endl;
        
        // Hash Map - O(1) average access
        std::unordered_map<std::string, int> hashMap;
        hashMap["apple"] = 5;
        hashMap["banana"] = 3;
        std::cout << "Hash map access hashMap[\"apple\"]: " << hashMap["apple"] << " (O(1))" << std::endl;
    }
    
    static void demonstrateInsertion() {
        std::cout << "\n=== Insertion Operations ===" << std::endl;
        
        // Array - O(n) insertion in middle
        std::vector<int> arr = {1, 2, 4, 5};
        arr.insert(arr.begin() + 2, 3); // Insert 3 at index 2
        std::cout << "Array after insertion: ";
        for (int num : arr) std::cout << num << " ";
        std::cout << " (O(n))" << std::endl;
        
        // Linked List - O(1) insertion if position known
        std::list<int> linkedList = {1, 2, 4, 5};
        auto it = linkedList.begin();
        std::advance(it, 2);
        linkedList.insert(it, 3);
        std::cout << "Linked list after insertion: ";
        for (int num : linkedList) std::cout << num << " ";
        std::cout << " (O(1) if position known)" << std::endl;
    }
    
    static void demonstrateSearch() {
        std::cout << "\n=== Search Operations ===" << std::endl;
        
        // Unsorted array - O(n) linear search
        std::vector<int> unsorted = {5, 2, 8, 1, 9};
        auto it = std::find(unsorted.begin(), unsorted.end(), 8);
        if (it != unsorted.end()) {
            std::cout << "Found 8 in unsorted array at index: " 
                      << std::distance(unsorted.begin(), it) << " (O(n))" << std::endl;
        }
        
        // Sorted array - O(log n) binary search
        std::vector<int> sorted = {1, 2, 5, 8, 9};
        bool found = std::binary_search(sorted.begin(), sorted.end(), 8);
        std::cout << "Found 8 in sorted array: " << (found ? "Yes" : "No") << " (O(log n))" << std::endl;
        
        // Set - O(log n) search
        std::set<int> set = {1, 2, 5, 8, 9};
        bool setFound = set.find(8) != set.end();
        std::cout << "Found 8 in set: " << (setFound ? "Yes" : "No") << " (O(log n))" << std::endl;
    }
};

int main() {
    DataStructureComparison::demonstrateAccess();
    DataStructureComparison::demonstrateInsertion();
    DataStructureComparison::demonstrateSearch();
    
    return 0;
}
```

## Performance Analysis

### Measuring Performance
```cpp
#include <iostream>
#include <vector>
#include <chrono>
#include <random>
#include <algorithm>

class PerformanceAnalyzer {
public:
    static void analyzeLinearSearch() {
        std::vector<int> sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            std::vector<int> data(size);
            std::iota(data.begin(), data.end(), 1);
            
            auto start = std::chrono::high_resolution_clock::now();
            
            // Linear search for last element
            auto it = std::find(data.begin(), data.end(), size);
            
            auto end = std::chrono::high_resolution_clock::now();
            auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
            
            std::cout << "Linear search in array of size " << size 
                      << ": " << duration.count() << " microseconds" << std::endl;
        }
    }
    
    static void analyzeBinarySearch() {
        std::vector<int> sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            std::vector<int> data(size);
            std::iota(data.begin(), data.end(), 1);
            
            auto start = std::chrono::high_resolution_clock::now();
            
            // Binary search for last element
            bool found = std::binary_search(data.begin(), data.end(), size);
            
            auto end = std::chrono::high_resolution_clock::now();
            auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
            
            std::cout << "Binary search in array of size " << size 
                      << ": " << duration.count() << " microseconds" << std::endl;
        }
    }
};

int main() {
    std::cout << "=== Performance Analysis ===" << std::endl;
    std::cout << "Linear Search Performance:" << std::endl;
    PerformanceAnalyzer::analyzeLinearSearch();
    
    std::cout << "\nBinary Search Performance:" << std::endl;
    PerformanceAnalyzer::analyzeBinarySearch();
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o data_structures main.cpp
./data_structures

# Windows (PowerShell)
g++ -std=c++17 -O2 -o data_structures.exe main.cpp
./data_structures.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o data_structures.exe main.cpp
data_structures.exe
```

### Memory Management
```cpp
#include <iostream>
#include <memory>

class MemoryManagementDemo {
public:
    static void demonstrateSmartPointers() {
        // Unique pointer - exclusive ownership
        auto uniquePtr = std::make_unique<std::vector<int>>(10, 5);
        std::cout << "Unique pointer size: " << uniquePtr->size() << std::endl;
        
        // Shared pointer - shared ownership
        auto sharedPtr = std::make_shared<std::vector<int>>(10, 3);
        auto sharedPtr2 = sharedPtr;
        std::cout << "Shared pointer use count: " << sharedPtr.use_count() << std::endl;
        
        // Weak pointer - non-owning reference
        std::weak_ptr<std::vector<int>> weakPtr = sharedPtr;
        if (auto locked = weakPtr.lock()) {
            std::cout << "Weak pointer locked, size: " << locked->size() << std::endl;
        }
    }
};
```

## Best Practices

1. **Choose Appropriate Data Structures**: Select based on your specific use case
2. **Consider Time vs Space Trade-offs**: Balance performance with memory usage
3. **Profile Your Code**: Measure actual performance rather than theoretical
4. **Use Standard Library**: Leverage optimized implementations when possible
5. **Plan for Scale**: Consider how your data structure will perform with larger datasets

## Common Pitfalls

1. **Ignoring Big O Notation**: Not considering algorithmic complexity
2. **Premature Optimization**: Optimizing without measuring actual bottlenecks
3. **Memory Leaks**: Not properly managing dynamic memory
4. **Cache Inefficiency**: Not considering memory locality
5. **Inappropriate Data Structure Choice**: Using wrong structure for the task

## Exercises

1. Implement a function to measure the time complexity of different sorting algorithms
2. Compare the performance of vector vs list for frequent insertions
3. Analyze the space complexity of recursive vs iterative implementations
4. Create a benchmark to test different data structures for your specific use case
5. Implement a custom data structure and measure its performance against standard library equivalents

## Learning Outcomes
- Understanding the importance of data structures in computer science
- Learning Big O notation for analyzing algorithmic efficiency
- Knowing how to choose appropriate data structures for different scenarios
- Understanding time and space complexity trade-offs
- Cross-platform C++ development for data structures
