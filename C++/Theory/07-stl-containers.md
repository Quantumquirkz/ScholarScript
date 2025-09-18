# STL Containers and Algorithms in C++

## Overview
The Standard Template Library (STL) provides powerful container classes and algorithms for C++. This module covers vectors, lists, maps, sets, and common STL algorithms.

## Vector Container

### Basic Vector Operations
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    // Create and initialize vector
    std::vector<int> numbers = {5, 2, 8, 1, 9};
    
    // Add elements
    numbers.push_back(3);
    numbers.insert(numbers.begin() + 2, 7);
    
    // Access elements
    std::cout << "First element: " << numbers[0] << std::endl;
    std::cout << "Last element: " << numbers.back() << std::endl;
    
    // Iterate through vector
    std::cout << "Vector elements: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Size and capacity
    std::cout << "Size: " << numbers.size() << std::endl;
    std::cout << "Capacity: " << numbers.capacity() << std::endl;
    
    return 0;
}
```

### Vector Sorting and Searching
```cpp
#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    std::vector<int> numbers = {5, 2, 8, 1, 9, 3, 7};
    
    // Sort vector
    std::sort(numbers.begin(), numbers.end());
    
    std::cout << "Sorted vector: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Search for element
    int target = 7;
    auto it = std::find(numbers.begin(), numbers.end(), target);
    if (it != numbers.end()) {
        std::cout << "Found " << target << " at position: " 
                  << (it - numbers.begin()) << std::endl;
    } else {
        std::cout << target << " not found" << std::endl;
    }
    
    // Binary search (requires sorted container)
    bool found = std::binary_search(numbers.begin(), numbers.end(), target);
    std::cout << "Binary search result: " << (found ? "Found" : "Not found") << std::endl;
    
    return 0;
}
```

## List Container

### Basic List Operations
```cpp
#include <iostream>
#include <list>
#include <algorithm>

int main() {
    // Create list
    std::list<int> numbers = {5, 2, 8, 1, 9};
    
    // Add elements
    numbers.push_front(0);
    numbers.push_back(10);
    
    // Insert element
    auto it = std::find(numbers.begin(), numbers.end(), 8);
    if (it != numbers.end()) {
        numbers.insert(it, 6);
    }
    
    // Remove elements
    numbers.remove(2);  // Remove all occurrences of 2
    numbers.remove_if([](int n) { return n % 2 == 0; });  // Remove even numbers
    
    // Display list
    std::cout << "List elements: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Sort list
    numbers.sort();
    std::cout << "Sorted list: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

## Map Container

### Basic Map Operations
```cpp
#include <iostream>
#include <map>
#include <string>

int main() {
    // Create map
    std::map<std::string, int> ages;
    
    // Insert elements
    ages["Alice"] = 25;
    ages["Bob"] = 30;
    ages["Charlie"] = 35;
    
    // Access elements
    std::cout << "Alice's age: " << ages["Alice"] << std::endl;
    
    // Check if key exists
    if (ages.find("David") != ages.end()) {
        std::cout << "David's age: " << ages["David"] << std::endl;
    } else {
        std::cout << "David not found" << std::endl;
    }
    
    // Iterate through map
    std::cout << "All ages:" << std::endl;
    for (const auto& pair : ages) {
        std::cout << pair.first << ": " << pair.second << std::endl;
    }
    
    // Update value
    ages["Alice"] = 26;
    std::cout << "Updated Alice's age: " << ages["Alice"] << std::endl;
    
    return 0;
}
```

### Map with Custom Comparator
```cpp
#include <iostream>
#include <map>
#include <string>

// Custom comparator for case-insensitive string comparison
struct CaseInsensitiveCompare {
    bool operator()(const std::string& a, const std::string& b) const {
        return std::lexicographical_compare(
            a.begin(), a.end(),
            b.begin(), b.end(),
            [](char a, char b) { return std::tolower(a) < std::tolower(b); }
        );
    }
};

int main() {
    std::map<std::string, int, CaseInsensitiveCompare> ages;
    
    ages["alice"] = 25;
    ages["Bob"] = 30;
    ages["CHARLIE"] = 35;
    
    // All these will find the same entry
    std::cout << "Alice: " << ages["alice"] << std::endl;
    std::cout << "Bob: " << ages["Bob"] << std::endl;
    std::cout << "Charlie: " << ages["CHARLIE"] << std::endl;
    
    return 0;
}
```

## Set Container

### Basic Set Operations
```cpp
#include <iostream>
#include <set>
#include <algorithm>

int main() {
    // Create set
    std::set<int> numbers = {5, 2, 8, 1, 9, 2, 5};  // Duplicates automatically removed
    
    // Insert elements
    numbers.insert(3);
    numbers.insert(7);
    
    // Check if element exists
    if (numbers.find(5) != numbers.end()) {
        std::cout << "5 is in the set" << std::endl;
    }
    
    // Display set (automatically sorted)
    std::cout << "Set elements: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Remove elements
    numbers.erase(5);
    numbers.erase(numbers.begin());  // Remove first element
    
    std::cout << "After removal: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

## STL Algorithms

### Common Algorithms
```cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>

int main() {
    std::vector<int> numbers = {5, 2, 8, 1, 9, 3, 7, 4, 6};
    
    // Sort
    std::sort(numbers.begin(), numbers.end());
    std::cout << "Sorted: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Reverse
    std::reverse(numbers.begin(), numbers.end());
    std::cout << "Reversed: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    // Find min and max
    auto minIt = std::min_element(numbers.begin(), numbers.end());
    auto maxIt = std::max_element(numbers.begin(), numbers.end());
    std::cout << "Min: " << *minIt << ", Max: " << *maxIt << std::endl;
    
    // Count elements
    int count = std::count(numbers.begin(), numbers.end(), 5);
    std::cout << "Count of 5: " << count << std::endl;
    
    // Sum of elements
    int sum = std::accumulate(numbers.begin(), numbers.end(), 0);
    std::cout << "Sum: " << sum << std::endl;
    
    // Remove duplicates (requires sorted container)
    std::sort(numbers.begin(), numbers.end());
    auto last = std::unique(numbers.begin(), numbers.end());
    numbers.erase(last, numbers.end());
    
    std::cout << "Unique elements: ";
    for (int num : numbers) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

### Custom Sorting and Filtering
```cpp
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

struct Person {
    std::string name;
    int age;
    
    Person(const std::string& n, int a) : name(n), age(a) {}
};

int main() {
    std::vector<Person> people = {
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 20),
        Person("David", 35)
    };
    
    // Sort by age
    std::sort(people.begin(), people.end(), 
              [](const Person& a, const Person& b) {
                  return a.age < b.age;
              });
    
    std::cout << "Sorted by age:" << std::endl;
    for (const auto& person : people) {
        std::cout << person.name << ": " << person.age << std::endl;
    }
    
    // Filter people older than 25
    std::vector<Person> adults;
    std::copy_if(people.begin(), people.end(), std::back_inserter(adults),
                 [](const Person& p) { return p.age > 25; });
    
    std::cout << "\nAdults (age > 25):" << std::endl;
    for (const auto& person : adults) {
        std::cout << person.name << ": " << person.age << std::endl;
    }
    
    return 0;
}
```

## Iterator Types

### Different Iterator Types
```cpp
#include <iostream>
#include <vector>
#include <list>
#include <set>

int main() {
    std::vector<int> vec = {1, 2, 3, 4, 5};
    std::list<int> lst = {1, 2, 3, 4, 5};
    std::set<int> st = {1, 2, 3, 4, 5};
    
    // Random access iterator (vector)
    std::cout << "Vector (random access): ";
    for (auto it = vec.begin(); it != vec.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
    
    // Bidirectional iterator (list)
    std::cout << "List (bidirectional): ";
    for (auto it = lst.begin(); it != lst.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
    
    // Const iterator
    std::cout << "Set (const iterator): ";
    for (auto it = st.cbegin(); it != st.cend(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
    
    // Reverse iterator
    std::cout << "Vector (reverse): ";
    for (auto it = vec.rbegin(); it != vec.rend(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o stl main.cpp
./stl

# Windows (PowerShell)
g++ -std=c++17 -O2 -o stl.exe main.cpp
./stl.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o stl.exe main.cpp
stl.exe
```

### Platform-Specific Considerations
- STL implementation may vary between compilers
- Memory allocation strategies may differ
- Performance characteristics may vary between platforms

## Common Pitfalls

1. **Iterator invalidation**: Don't use iterators after modifying the container
2. **Undefined behavior**: Don't dereference end() iterator
3. **Memory leaks**: Use smart pointers when storing pointers in containers
4. **Performance**: Choose the right container for your use case

## Best Practices

1. Use appropriate container types for your needs
2. Prefer algorithms over manual loops when possible
3. Use const iterators when you don't need to modify elements
4. Use range-based for loops for simple iteration
5. Consider using `auto` keyword for iterator types
6. Use `emplace` methods for better performance when inserting objects

## Performance Considerations

### Container Selection
- **Vector**: Fast random access, good for sequential access
- **List**: Fast insertion/deletion, no random access
- **Map**: O(log n) lookup, sorted by key
- **Set**: O(log n) lookup, unique elements, sorted
- **Unordered_map**: O(1) average lookup, not sorted
- **Unordered_set**: O(1) average lookup, unique elements, not sorted

### Memory Efficiency
```cpp
#include <iostream>
#include <vector>

int main() {
    std::vector<int> numbers;
    
    // Reserve space to avoid reallocations
    numbers.reserve(1000);
    
    for (int i = 0; i < 1000; i++) {
        numbers.push_back(i);
    }
    
    std::cout << "Size: " << numbers.size() << std::endl;
    std::cout << "Capacity: " << numbers.capacity() << std::endl;
    
    return 0;
}
```

## Exercises

1. Create a program that reads words from input and stores them in a set to remove duplicates
2. Implement a simple phone book using a map to store names and phone numbers
3. Write a program that finds the most frequent word in a text using a map
4. Create a program that sorts a list of students by their grades using custom sorting
5. Implement a simple inventory system using a map to track product quantities
