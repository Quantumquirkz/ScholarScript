# Practice 07: STL Algorithms - Sorting and Removing Duplicates

## Objective
Learn STL algorithms in C++ by implementing a program that sorts a vector and removes duplicates using `std::sort` and `std::unique`.

## Problem Statement
Sort a vector and remove duplicates.

### Requirements
- Input: A sequence of integers (hardcoded or read)
- Output: Sorted unique sequence
- Constraints: Use `std::sort` and `std::unique`

### Example
```
Input: 3 1 2 2 1
Output: 1 2 3
```

## Solution

### Complete Code
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    // Create vector with sample data
    std::vector<int> numbers = {3, 1, 2, 2, 1};
    
    // Display original vector
    std::cout << "Original vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Sort the vector
    std::sort(numbers.begin(), numbers.end());
    
    // Display sorted vector
    std::cout << "Sorted vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Remove duplicates
    auto last = std::unique(numbers.begin(), numbers.end());
    numbers.erase(last, numbers.end());
    
    // Display unique sorted vector
    std::cout << "Unique sorted vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

### Code Explanation
1. **Include Headers**: `iostream` for I/O, `vector` for container, `algorithm` for STL functions
2. **Vector Creation**: Initialize with sample data
3. **Sorting**: Use `std::sort` to sort the vector
4. **Remove Duplicates**: Use `std::unique` to move duplicates to end, then `erase` to remove them
5. **Display Results**: Show original, sorted, and unique sorted vectors

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o sort_unique main.cpp
./sort_unique
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o sort_unique.exe main.cpp
./sort_unique.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o sort_unique.exe main.cpp
sort_unique.exe
```

## Key Concepts

### STL Algorithms
- **std::sort**: Sorts elements in ascending order
- **std::unique**: Removes consecutive duplicate elements
- **std::erase**: Removes elements from container
- **Iterators**: `begin()` and `end()` for container access

### Vector Operations
- **Range-based for loop**: `for (int num : numbers)`
- **Iterator operations**: `begin()`, `end()`, `erase()`
- **Container modification**: Adding, removing, and modifying elements

### Algorithm Complexity
- **std::sort**: O(n log n) average case
- **std::unique**: O(n) linear time
- **Overall**: O(n log n) due to sorting

## Common Pitfalls

1. **Missing Headers**: Always include `<algorithm>` for STL functions
2. **Iterator Invalidation**: Be careful with iterators after container modification
3. **Unique Behavior**: `std::unique` only removes consecutive duplicates
4. **Sorting First**: Must sort before using `std::unique` for all duplicates

## Variations

### Version with User Input
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    std::vector<int> numbers;
    int n, value;
    
    std::cout << "Enter number of elements: ";
    std::cin >> n;
    
    std::cout << "Enter " << n << " integers: ";
    for (int i = 0; i < n; i++) {
        std::cin >> value;
        numbers.push_back(value);
    }
    
    // Display original vector
    std::cout << "Original vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Sort the vector
    std::sort(numbers.begin(), numbers.end());
    
    // Remove duplicates
    auto last = std::unique(numbers.begin(), numbers.end());
    numbers.erase(last, numbers.end());
    
    // Display result
    std::cout << "Unique sorted vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

### Version with Function
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

std::vector<int> sortAndRemoveDuplicates(std::vector<int> numbers) {
    // Sort the vector
    std::sort(numbers.begin(), numbers.end());
    
    // Remove duplicates
    auto last = std::unique(numbers.begin(), numbers.end());
    numbers.erase(last, numbers.end());
    
    return numbers;
}

int main() {
    std::vector<int> numbers = {3, 1, 2, 2, 1, 5, 3, 4, 5};
    
    std::cout << "Original vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    std::vector<int> result = sortAndRemoveDuplicates(numbers);
    
    std::cout << "Unique sorted vector: ";
    for (int num : result) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

### Version with Different Data Types
```cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

template<typename T>
void sortAndRemoveDuplicates(std::vector<T>& vec) {
    std::sort(vec.begin(), vec.end());
    auto last = std::unique(vec.begin(), vec.end());
    vec.erase(last, vec.end());
}

int main() {
    // Test with integers
    std::vector<int> intNumbers = {3, 1, 2, 2, 1, 5, 3, 4, 5};
    std::cout << "Original integers: ";
    for (int num : intNumbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    sortAndRemoveDuplicates(intNumbers);
    std::cout << "Unique sorted integers: ";
    for (int num : intNumbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Test with strings
    std::vector<std::string> words = {"apple", "banana", "apple", "cherry", "banana", "date"};
    std::cout << "Original words: ";
    for (const std::string& word : words) {
        std::cout << word << " ";
    }
    std::cout << std::endl;
    
    sortAndRemoveDuplicates(words);
    std::cout << "Unique sorted words: ";
    for (const std::string& word : words) {
        std::cout << word << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

## Advanced Features

### Class-Based Approach
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

class VectorProcessor {
private:
    std::vector<int> data;
    
public:
    VectorProcessor(const std::vector<int>& input) : data(input) {}
    
    void sort() {
        std::sort(data.begin(), data.end());
    }
    
    void removeDuplicates() {
        auto last = std::unique(data.begin(), data.end());
        data.erase(last, data.end());
    }
    
    void sortAndRemoveDuplicates() {
        sort();
        removeDuplicates();
    }
    
    void display() const {
        for (int num : data) {
            std::cout << num << " ";
        }
        std::cout << std::endl;
    }
    
    std::vector<int> getData() const {
        return data;
    }
    
    size_t size() const {
        return data.size();
    }
};

int main() {
    std::vector<int> numbers = {3, 1, 2, 2, 1, 5, 3, 4, 5};
    
    VectorProcessor processor(numbers);
    
    std::cout << "Original vector: ";
    processor.display();
    
    processor.sortAndRemoveDuplicates();
    
    std::cout << "Unique sorted vector: ";
    processor.display();
    
    std::cout << "Size: " << processor.size() << std::endl;
    
    return 0;
}
```

### Performance Analysis
```cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <chrono>
#include <random>

class PerformanceAnalyzer {
public:
    static std::vector<int> generateRandomData(size_t size) {
        std::vector<int> data;
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<> dis(1, 100);
        
        for (size_t i = 0; i < size; i++) {
            data.push_back(dis(gen));
        }
        
        return data;
    }
    
    static double measureSortTime(std::vector<int>& data) {
        auto start = std::chrono::high_resolution_clock::now();
        std::sort(data.begin(), data.end());
        auto end = std::chrono::high_resolution_clock::now();
        
        auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
        return duration.count() / 1000.0; // Convert to milliseconds
    }
    
    static double measureUniqueTime(std::vector<int>& data) {
        auto start = std::chrono::high_resolution_clock::now();
        auto last = std::unique(data.begin(), data.end());
        data.erase(last, data.end());
        auto end = std::chrono::high_resolution_clock::now();
        
        auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
        return duration.count() / 1000.0; // Convert to milliseconds
    }
};

int main() {
    std::vector<int> data = PerformanceAnalyzer::generateRandomData(10000);
    
    std::cout << "Performance Analysis:" << std::endl;
    std::cout << "Data size: " << data.size() << std::endl;
    
    // Measure sort time
    double sortTime = PerformanceAnalyzer::measureSortTime(data);
    std::cout << "Sort time: " << sortTime << " ms" << std::endl;
    
    // Measure unique time
    double uniqueTime = PerformanceAnalyzer::measureUniqueTime(data);
    std::cout << "Unique time: " << uniqueTime << " ms" << std::endl;
    
    std::cout << "Final size: " << data.size() << std::endl;
    
    return 0;
}
```

## Exercises

1. Create a program that sorts strings and removes duplicates
2. Implement a function that finds the most frequent element in a vector
3. Write a program that merges two sorted vectors without duplicates
4. Create a function that sorts a vector in descending order
5. Implement a program that finds the intersection of two vectors

## Learning Outcomes
- Understanding STL algorithms
- Working with vectors and iterators
- Learning sorting and unique operations
- Understanding algorithm complexity
- Cross-platform compilation
