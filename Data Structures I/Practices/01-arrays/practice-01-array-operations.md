# Practice 01: Array Operations

## Objective
Learn and implement fundamental array operations including insertion, deletion, searching, and sorting algorithms. Understand the time and space complexity of different array operations.

## Problem Statement
Create a comprehensive array management system that demonstrates various array operations and algorithms.

### Requirements
- Implement dynamic array with basic operations
- Perform searching algorithms (linear and binary)
- Implement sorting algorithms (bubble, selection, insertion)
- Handle array resizing and memory management
- Calculate and display performance metrics

### Example Output
```
Array Operations Demo
====================
Original array: [64, 34, 25, 12, 22, 11, 90]
After bubble sort: [11, 12, 22, 25, 34, 64, 90]
Binary search for 25: Found at index 3
Linear search for 90: Found at index 6
```

## Solution

### Complete Code
```cpp
#include <iostream>
#include <vector>
#include <chrono>
#include <random>
#include <algorithm>
#include <iomanip>

class DynamicArray {
private:
    std::vector<int> data;
    size_t capacity;
    size_t currentSize;
    
    void resize() {
        capacity *= 2;
        data.reserve(capacity);
    }
    
public:
    DynamicArray(size_t initialCapacity = 10) 
        : capacity(initialCapacity), currentSize(0) {
        data.reserve(capacity);
    }
    
    // Basic operations
    void insert(int value) {
        if (currentSize >= capacity) {
            resize();
        }
        data.push_back(value);
        currentSize++;
    }
    
    void insertAt(int index, int value) {
        if (index > currentSize) {
            throw std::out_of_range("Index out of range");
        }
        
        if (currentSize >= capacity) {
            resize();
        }
        
        data.insert(data.begin() + index, value);
        currentSize++;
    }
    
    bool remove(int value) {
        auto it = std::find(data.begin(), data.end(), value);
        if (it != data.end()) {
            data.erase(it);
            currentSize--;
            return true;
        }
        return false;
    }
    
    bool removeAt(int index) {
        if (index >= currentSize) {
            return false;
        }
        
        data.erase(data.begin() + index);
        currentSize--;
        return true;
    }
    
    int linearSearch(int value) const {
        for (size_t i = 0; i < currentSize; i++) {
            if (data[i] == value) {
                return static_cast<int>(i);
            }
        }
        return -1;
    }
    
    int binarySearch(int value) const {
        // Array must be sorted for binary search
        int left = 0;
        int right = static_cast<int>(currentSize) - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (data[mid] == value) {
                return mid;
            } else if (data[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // Sorting algorithms
    void bubbleSort() {
        for (size_t i = 0; i < currentSize - 1; i++) {
            bool swapped = false;
            for (size_t j = 0; j < currentSize - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    std::swap(data[j], data[j + 1]);
                    swapped = true;
                }
            }
            if (!swapped) break; // Array is sorted
        }
    }
    
    void selectionSort() {
        for (size_t i = 0; i < currentSize - 1; i++) {
            size_t minIndex = i;
            for (size_t j = i + 1; j < currentSize; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                std::swap(data[i], data[minIndex]);
            }
        }
    }
    
    void insertionSort() {
        for (size_t i = 1; i < currentSize; i++) {
            int key = data[i];
            size_t j = i;
            
            while (j > 0 && data[j - 1] > key) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = key;
        }
    }
    
    // Utility methods
    void display() const {
        std::cout << "[";
        for (size_t i = 0; i < currentSize; i++) {
            std::cout << data[i];
            if (i < currentSize - 1) std::cout << ", ";
        }
        std::cout << "]" << std::endl;
    }
    
    void fillRandom(size_t count, int minVal = 1, int maxVal = 100) {
        data.clear();
        currentSize = 0;
        
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<int> dist(minVal, maxVal);
        
        for (size_t i = 0; i < count; i++) {
            insert(dist(gen));
        }
    }
    
    void clear() {
        data.clear();
        currentSize = 0;
    }
    
    size_t size() const { return currentSize; }
    size_t getCapacity() const { return capacity; }
    
    int get(int index) const {
        if (index >= currentSize) {
            throw std::out_of_range("Index out of range");
        }
        return data[index];
    }
    
    void set(int index, int value) {
        if (index >= currentSize) {
            throw std::out_of_range("Index out of range");
        }
        data[index] = value;
    }
};

class PerformanceAnalyzer {
public:
    template<typename Func>
    static double measureTime(Func func, const std::string& operationName) {
        auto start = std::chrono::high_resolution_clock::now();
        func();
        auto end = std::chrono::high_resolution_clock::now();
        
        auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
        double timeMs = duration.count() / 1000.0;
        
        std::cout << operationName << " took: " << std::fixed << std::setprecision(3) 
                  << timeMs << " ms" << std::endl;
        
        return timeMs;
    }
    
    static void compareSortingAlgorithms(DynamicArray& array) {
        std::cout << "\n=== Sorting Algorithm Comparison ===" << std::endl;
        
        // Create copies for each algorithm
        DynamicArray bubbleArray = array;
        DynamicArray selectionArray = array;
        DynamicArray insertionArray = array;
        
        std::cout << "Original array: ";
        array.display();
        
        // Measure bubble sort
        double bubbleTime = measureTime([&bubbleArray]() {
            bubbleArray.bubbleSort();
        }, "Bubble Sort");
        
        // Measure selection sort
        double selectionTime = measureTime([&selectionArray]() {
            selectionArray.selectionSort();
        }, "Selection Sort");
        
        // Measure insertion sort
        double insertionTime = measureTime([&insertionArray]() {
            insertionArray.insertionSort();
        }, "Insertion Sort");
        
        // Display results
        std::cout << "\nSorted arrays:" << std::endl;
        std::cout << "Bubble:    ";
        bubbleArray.display();
        std::cout << "Selection: ";
        selectionArray.display();
        std::cout << "Insertion: ";
        insertionArray.display();
        
        // Performance summary
        std::cout << "\nPerformance Summary:" << std::endl;
        std::cout << "Bubble Sort:    " << std::fixed << std::setprecision(3) << bubbleTime << " ms" << std::endl;
        std::cout << "Selection Sort: " << std::fixed << std::setprecision(3) << selectionTime << " ms" << std::endl;
        std::cout << "Insertion Sort: " << std::fixed << std::setprecision(3) << insertionTime << " ms" << std::endl;
    }
    
    static void compareSearchAlgorithms(DynamicArray& array, int searchValue) {
        std::cout << "\n=== Search Algorithm Comparison ===" << std::endl;
        std::cout << "Searching for: " << searchValue << std::endl;
        
        // Linear search
        double linearTime = measureTime([&array, searchValue]() {
            array.linearSearch(searchValue);
        }, "Linear Search");
        
        int linearResult = array.linearSearch(searchValue);
        std::cout << "Linear search result: " 
                  << (linearResult != -1 ? "Found at index " + std::to_string(linearResult) : "Not found") 
                  << std::endl;
        
        // Sort array for binary search
        array.bubbleSort();
        std::cout << "Sorted array for binary search: ";
        array.display();
        
        // Binary search
        double binaryTime = measureTime([&array, searchValue]() {
            array.binarySearch(searchValue);
        }, "Binary Search");
        
        int binaryResult = array.binarySearch(searchValue);
        std::cout << "Binary search result: " 
                  << (binaryResult != -1 ? "Found at index " + std::to_string(binaryResult) : "Not found") 
                  << std::endl;
        
        // Performance comparison
        std::cout << "\nPerformance Summary:" << std::endl;
        std::cout << "Linear Search: " << std::fixed << std::setprecision(3) << linearTime << " ms" << std::endl;
        std::cout << "Binary Search: " << std::fixed << std::setprecision(3) << binaryTime << " ms" << std::endl;
        
        if (binaryTime > 0) {
            double speedup = linearTime / binaryTime;
            std::cout << "Binary search is " << std::fixed << std::setprecision(1) << speedup << "x faster" << std::endl;
        }
    }
};

class ArrayOperationsDemo {
public:
    static void demonstrateBasicOperations() {
        std::cout << "=== Basic Array Operations ===" << std::endl;
        
        DynamicArray array;
        
        // Insert elements
        std::cout << "Inserting elements..." << std::endl;
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insertAt(1, 15);
        
        std::cout << "Array after insertions: ";
        array.display();
        std::cout << "Size: " << array.size() << ", Capacity: " << array.getCapacity() << std::endl;
        
        // Search operations
        std::cout << "\nSearch operations:" << std::endl;
        int index = array.linearSearch(20);
        std::cout << "Linear search for 20: " << (index != -1 ? "Found at " + std::to_string(index) : "Not found") << std::endl;
        
        // Update element
        std::cout << "\nUpdating element at index 2..." << std::endl;
        array.set(2, 25);
        std::cout << "Array after update: ";
        array.display();
        
        // Remove element
        std::cout << "\nRemoving element 15..." << std::endl;
        bool removed = array.remove(15);
        std::cout << "Element removed: " << (removed ? "Yes" : "No") << std::endl;
        std::cout << "Array after removal: ";
        array.display();
    }
    
    static void demonstrateSortingAlgorithms() {
        std::cout << "\n=== Sorting Algorithms Demo ===" << std::endl;
        
        DynamicArray array;
        array.fillRandom(20, 1, 100);
        
        PerformanceAnalyzer::compareSortingAlgorithms(array);
    }
    
    static void demonstrateSearchAlgorithms() {
        std::cout << "\n=== Search Algorithms Demo ===" << std::endl;
        
        DynamicArray array;
        array.fillRandom(1000, 1, 1000);
        
        // Search for a random value
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<int> dist(1, 1000);
        int searchValue = dist(gen);
        
        PerformanceAnalyzer::compareSearchAlgorithms(array, searchValue);
    }
    
    static void demonstrateArrayResizing() {
        std::cout << "\n=== Array Resizing Demo ===" << std::endl;
        
        DynamicArray array(5); // Start with small capacity
        
        std::cout << "Initial capacity: " << array.getCapacity() << std::endl;
        
        // Insert more elements than initial capacity
        for (int i = 1; i <= 20; i++) {
            array.insert(i);
            if (i % 5 == 0) {
                std::cout << "After inserting " << i << " elements - Size: " << array.size() 
                          << ", Capacity: " << array.getCapacity() << std::endl;
            }
        }
        
        std::cout << "Final array: ";
        array.display();
    }
};

int main() {
    std::cout << "Array Operations Demo" << std::endl;
    std::cout << "====================" << std::endl;
    
    try {
        // Demonstrate basic operations
        ArrayOperationsDemo::demonstrateBasicOperations();
        
        // Demonstrate sorting algorithms
        ArrayOperationsDemo::demonstrateSortingAlgorithms();
        
        // Demonstrate search algorithms
        ArrayOperationsDemo::demonstrateSearchAlgorithms();
        
        // Demonstrate array resizing
        ArrayOperationsDemo::demonstrateArrayResizing();
        
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
        return 1;
    }
    
    std::cout << "\nDemo completed successfully!" << std::endl;
    return 0;
}
```

### Code Explanation
1. **DynamicArray Class**: Implements a dynamic array with automatic resizing
2. **Basic Operations**: Insert, delete, search, and update operations
3. **Sorting Algorithms**: Bubble sort, selection sort, and insertion sort
4. **Search Algorithms**: Linear search and binary search
5. **Performance Analysis**: Time measurement and comparison of algorithms
6. **Memory Management**: Automatic resizing when capacity is exceeded

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o array_operations main.cpp
./array_operations
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o array_operations.exe main.cpp
array_operations.exe
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o array_operations.exe main.cpp
./array_operations.exe
```

## Key Concepts

### Time Complexity Analysis
```cpp
class ComplexityAnalysis {
public:
    static void demonstrateTimeComplexity() {
        std::cout << "\n=== Time Complexity Analysis ===" << std::endl;
        
        std::vector<size_t> sizes = {100, 1000, 10000, 100000};
        
        for (size_t size : sizes) {
            DynamicArray array;
            array.fillRandom(size);
            
            std::cout << "\nArray size: " << size << std::endl;
            
            // O(1) operation - Access by index
            auto start = std::chrono::high_resolution_clock::now();
            int value = array.get(size / 2);
            auto end = std::chrono::high_resolution_clock::now();
            auto duration = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start);
            std::cout << "Access by index (O(1)): " << duration.count() << " ns" << std::endl;
            
            // O(n) operation - Linear search
            start = std::chrono::high_resolution_clock::now();
            int index = array.linearSearch(size / 2);
            end = std::chrono::high_resolution_clock::now();
            duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
            std::cout << "Linear search (O(n)): " << duration.count() << " μs" << std::endl;
            
            // O(n²) operation - Bubble sort
            DynamicArray sortArray = array;
            start = std::chrono::high_resolution_clock::now();
            sortArray.bubbleSort();
            end = std::chrono::high_resolution_clock::now();
            duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
            std::cout << "Bubble sort (O(n²)): " << duration.count() << " ms" << std::endl;
            
            // O(log n) operation - Binary search (on sorted array)
            start = std::chrono::high_resolution_clock::now();
            int binaryIndex = sortArray.binarySearch(size / 2);
            end = std::chrono::high_resolution_clock::now();
            duration = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start);
            std::cout << "Binary search (O(log n)): " << duration.count() << " ns" << std::endl;
        }
    }
};
```

### Memory Management
```cpp
class MemoryManagementDemo {
public:
    static void demonstrateMemoryUsage() {
        std::cout << "\n=== Memory Management Demo ===" << std::endl;
        
        DynamicArray array(10);
        
        std::cout << "Initial capacity: " << array.getCapacity() << std::endl;
        std::cout << "Initial size: " << array.size() << std::endl;
        
        // Track memory usage as we add elements
        for (int i = 1; i <= 25; i++) {
            array.insert(i);
            
            if (i % 5 == 0) {
                std::cout << "Elements: " << array.size() 
                          << ", Capacity: " << array.getCapacity() 
                          << ", Memory usage: " << (array.getCapacity() * sizeof(int)) << " bytes" << std::endl;
            }
        }
        
        // Demonstrate shrinking (if implemented)
        std::cout << "\nRemoving elements to test memory efficiency..." << std::endl;
        for (int i = 20; i >= 1; i--) {
            array.remove(i);
        }
        
        std::cout << "After removal - Elements: " << array.size() 
                  << ", Capacity: " << array.getCapacity() << std::endl;
    }
};
```

## Advanced Features

### Array Utilities
```cpp
class ArrayUtilities {
public:
    // Find maximum and minimum elements
    static std::pair<int, int> findMinMax(const DynamicArray& array) {
        if (array.size() == 0) {
            throw std::runtime_error("Array is empty");
        }
        
        int minVal = array.get(0);
        int maxVal = array.get(0);
        
        for (size_t i = 1; i < array.size(); i++) {
            int current = array.get(i);
            if (current < minVal) minVal = current;
            if (current > maxVal) maxVal = current;
        }
        
        return {minVal, maxVal};
    }
    
    // Calculate array statistics
    static struct ArrayStats {
        double mean;
        double median;
        int mode;
        double standardDeviation;
    } calculateStatistics(const DynamicArray& array) {
        if (array.size() == 0) {
            throw std::runtime_error("Array is empty");
        }
        
        ArrayStats stats;
        
        // Calculate mean
        double sum = 0;
        for (size_t i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        stats.mean = sum / array.size();
        
        // Calculate median (array should be sorted)
        DynamicArray sortedArray = array;
        sortedArray.bubbleSort();
        
        if (sortedArray.size() % 2 == 0) {
            stats.median = (sortedArray.get(sortedArray.size() / 2 - 1) + 
                           sortedArray.get(sortedArray.size() / 2)) / 2.0;
        } else {
            stats.median = sortedArray.get(sortedArray.size() / 2);
        }
        
        // Calculate standard deviation
        double variance = 0;
        for (size_t i = 0; i < array.size(); i++) {
            double diff = array.get(i) - stats.mean;
            variance += diff * diff;
        }
        stats.standardDeviation = std::sqrt(variance / array.size());
        
        // Find mode (most frequent element)
        std::unordered_map<int, int> frequency;
        for (size_t i = 0; i < array.size(); i++) {
            frequency[array.get(i)]++;
        }
        
        int maxFreq = 0;
        for (const auto& pair : frequency) {
            if (pair.second > maxFreq) {
                maxFreq = pair.second;
                stats.mode = pair.first;
            }
        }
        
        return stats;
    }
    
    // Reverse array
    static void reverse(DynamicArray& array) {
        size_t start = 0;
        size_t end = array.size() - 1;
        
        while (start < end) {
            int temp = array.get(start);
            array.set(start, array.get(end));
            array.set(end, temp);
            start++;
            end--;
        }
    }
    
    // Remove duplicates
    static DynamicArray removeDuplicates(const DynamicArray& array) {
        DynamicArray result;
        std::unordered_set<int> seen;
        
        for (size_t i = 0; i < array.size(); i++) {
            int value = array.get(i);
            if (seen.find(value) == seen.end()) {
                result.insert(value);
                seen.insert(value);
            }
        }
        
        return result;
    }
};
```

## Best Practices

1. **Use std::vector**: Prefer std::vector over raw arrays for dynamic arrays
2. **Reserve Memory**: Use reserve() to avoid frequent reallocations
3. **Bounds Checking**: Always validate array indices before access
4. **Algorithm Selection**: Choose appropriate algorithms based on data size and requirements
5. **Memory Management**: Be aware of memory usage and fragmentation

## Common Pitfalls

1. **Index Out of Bounds**: Accessing elements beyond array size
2. **Memory Leaks**: Not properly managing dynamic memory
3. **Inefficient Resizing**: Resizing too frequently or by too small amounts
4. **Wrong Algorithm**: Using O(n²) algorithm when O(n log n) is available
5. **Uninitialized Arrays**: Using arrays before proper initialization

## Exercises

1. Implement a sparse array that only stores non-zero elements
2. Create a circular array with fixed size and wrapping behavior
3. Implement a 2D dynamic array class
4. Build a priority queue using arrays
5. Create an array-based hash table implementation

## Learning Outcomes
- Understanding array fundamentals and memory layout
- Learning different sorting and searching algorithms
- Understanding time and space complexity analysis
- Implementing dynamic array resizing
- Cross-platform C++ development for array-based programs
