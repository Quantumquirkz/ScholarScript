# Practice 02: Linked List Implementation

## Objective
Implement and understand different types of linked lists (singly, doubly, circular) and their operations. Learn about memory management, pointer manipulation, and the trade-offs between arrays and linked lists.

## Problem Statement
Create a comprehensive linked list system that demonstrates various linked list types and their operations.

### Requirements
- Implement singly linked list with all basic operations
- Implement doubly linked list with bidirectional traversal
- Implement circular linked list with wraparound behavior
- Compare performance with arrays
- Handle memory management properly
- Demonstrate advanced linked list algorithms

### Example Output
```
Linked List Operations Demo
==========================
Singly Linked List: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
Doubly Linked List: 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> NULL
Circular Linked List: 1 -> 2 -> 3 -> 4 -> 5 -> (back to 1)
```

## Solution

### Complete Code
```cpp
#include <iostream>
#include <memory>
#include <chrono>
#include <vector>
#include <algorithm>

// Node structure for singly linked list
template<typename T>
struct SinglyNode {
    T data;
    std::unique_ptr<SinglyNode<T>> next;
    
    SinglyNode(const T& value) : data(value), next(nullptr) {}
};

// Node structure for doubly linked list
template<typename T>
struct DoublyNode {
    T data;
    std::unique_ptr<DoublyNode<T>> next;
    DoublyNode<T>* prev;
    
    DoublyNode(const T& value) : data(value), next(nullptr), prev(nullptr) {}
};

template<typename T>
class SinglyLinkedList {
private:
    std::unique_ptr<SinglyNode<T>> head;
    size_t size;
    
public:
    SinglyLinkedList() : head(nullptr), size(0) {}
    
    ~SinglyLinkedList() {
        clear();
    }
    
    // Insert at the beginning
    void insertAtBeginning(const T& value) {
        auto newNode = std::make_unique<SinglyNode<T>>(value);
        newNode->next = std::move(head);
        head = std::move(newNode);
        size++;
    }
    
    // Insert at the end
    void insertAtEnd(const T& value) {
        auto newNode = std::make_unique<SinglyNode<T>>(value);
        
        if (!head) {
            head = std::move(newNode);
        } else {
            SinglyNode<T>* current = head.get();
            while (current->next) {
                current = current->next.get();
            }
            current->next = std::move(newNode);
        }
        size++;
    }
    
    // Insert at specific position
    void insertAtPosition(size_t position, const T& value) {
        if (position > size) {
            throw std::out_of_range("Position out of range");
        }
        
        if (position == 0) {
            insertAtBeginning(value);
            return;
        }
        
        if (position == size) {
            insertAtEnd(value);
            return;
        }
        
        auto newNode = std::make_unique<SinglyNode<T>>(value);
        SinglyNode<T>* current = head.get();
        
        for (size_t i = 0; i < position - 1; i++) {
            current = current->next.get();
        }
        
        newNode->next = std::move(current->next);
        current->next = std::move(newNode);
        size++;
    }
    
    // Remove from beginning
    bool removeFromBeginning() {
        if (!head) {
            return false;
        }
        
        head = std::move(head->next);
        size--;
        return true;
    }
    
    // Remove from end
    bool removeFromEnd() {
        if (!head) {
            return false;
        }
        
        if (!head->next) {
            head.reset();
        } else {
            SinglyNode<T>* current = head.get();
            while (current->next->next) {
                current = current->next.get();
            }
            current->next.reset();
        }
        size--;
        return true;
    }
    
    // Remove by value
    bool removeByValue(const T& value) {
        if (!head) {
            return false;
        }
        
        if (head->data == value) {
            head = std::move(head->next);
            size--;
            return true;
        }
        
        SinglyNode<T>* current = head.get();
        while (current->next) {
            if (current->next->data == value) {
                current->next = std::move(current->next->next);
                size--;
                return true;
            }
            current = current->next.get();
        }
        
        return false;
    }
    
    // Search for value
    int search(const T& value) const {
        SinglyNode<T>* current = head.get();
        int index = 0;
        
        while (current) {
            if (current->data == value) {
                return index;
            }
            current = current->next.get();
            index++;
        }
        
        return -1;
    }
    
    // Get element at index
    T get(size_t index) const {
        if (index >= size) {
            throw std::out_of_range("Index out of range");
        }
        
        SinglyNode<T>* current = head.get();
        for (size_t i = 0; i < index; i++) {
            current = current->next.get();
        }
        
        return current->data;
    }
    
    // Update element at index
    void set(size_t index, const T& value) {
        if (index >= size) {
            throw std::out_of_range("Index out of range");
        }
        
        SinglyNode<T>* current = head.get();
        for (size_t i = 0; i < index; i++) {
            current = current->next.get();
        }
        
        current->data = value;
    }
    
    // Reverse the list
    void reverse() {
        if (!head || !head->next) {
            return;
        }
        
        SinglyNode<T>* prev = nullptr;
        SinglyNode<T>* current = head.get();
        SinglyNode<T>* next = nullptr;
        
        while (current) {
            next = current->next.get();
            current->next = std::unique_ptr<SinglyNode<T>>(prev);
            prev = current;
            current = next;
        }
        
        head = std::unique_ptr<SinglyNode<T>>(prev);
    }
    
    // Sort the list (merge sort)
    void sort() {
        head = mergeSort(std::move(head));
    }
    
private:
    std::unique_ptr<SinglyNode<T>> mergeSort(std::unique_ptr<SinglyNode<T>> head) {
        if (!head || !head->next) {
            return head;
        }
        
        // Find middle
        SinglyNode<T>* slow = head.get();
        SinglyNode<T>* fast = head->next.get();
        
        while (fast && fast->next) {
            slow = slow->next.get();
            fast = fast->next->next.get();
        }
        
        // Split the list
        auto mid = std::move(slow->next);
        slow->next = nullptr;
        
        // Recursively sort both halves
        auto left = mergeSort(std::move(head));
        auto right = mergeSort(std::move(mid));
        
        // Merge the sorted halves
        return merge(std::move(left), std::move(right));
    }
    
    std::unique_ptr<SinglyNode<T>> merge(std::unique_ptr<SinglyNode<T>> left, 
                                        std::unique_ptr<SinglyNode<T>> right) {
        if (!left) return right;
        if (!right) return left;
        
        if (left->data <= right->data) {
            left->next = merge(std::move(left->next), std::move(right));
            return left;
        } else {
            right->next = merge(std::move(left), std::move(right->next));
            return right;
        }
    }
    
public:
    // Display the list
    void display() const {
        if (!head) {
            std::cout << "Empty list" << std::endl;
            return;
        }
        
        SinglyNode<T>* current = head.get();
        while (current) {
            std::cout << current->data;
            if (current->next) {
                std::cout << " -> ";
            }
            current = current->next.get();
        }
        std::cout << " -> NULL" << std::endl;
    }
    
    // Utility methods
    bool isEmpty() const { return size == 0; }
    size_t getSize() const { return size; }
    
    void clear() {
        while (head) {
            head = std::move(head->next);
        }
        size = 0;
    }
    
    // Fill with random values
    void fillRandom(size_t count, T minVal, T maxVal) {
        clear();
        
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<T> dist(minVal, maxVal);
        
        for (size_t i = 0; i < count; i++) {
            insertAtEnd(dist(gen));
        }
    }
};

template<typename T>
class DoublyLinkedList {
private:
    std::unique_ptr<DoublyNode<T>> head;
    DoublyNode<T>* tail;
    size_t size;
    
public:
    DoublyLinkedList() : head(nullptr), tail(nullptr), size(0) {}
    
    ~DoublyLinkedList() {
        clear();
    }
    
    // Insert at the beginning
    void insertAtBeginning(const T& value) {
        auto newNode = std::make_unique<DoublyNode<T>>(value);
        
        if (!head) {
            tail = newNode.get();
        } else {
            head->prev = newNode.get();
            newNode->next = std::move(head);
        }
        
        head = std::move(newNode);
        size++;
    }
    
    // Insert at the end
    void insertAtEnd(const T& value) {
        auto newNode = std::make_unique<DoublyNode<T>>(value);
        
        if (!tail) {
            head = std::move(newNode);
            tail = head.get();
        } else {
            tail->next = std::move(newNode);
            tail->next->prev = tail;
            tail = tail->next.get();
        }
        size++;
    }
    
    // Remove from beginning
    bool removeFromBeginning() {
        if (!head) {
            return false;
        }
        
        if (head == tail) {
            head.reset();
            tail = nullptr;
        } else {
            head = std::move(head->next);
            head->prev = nullptr;
        }
        size--;
        return true;
    }
    
    // Remove from end
    bool removeFromEnd() {
        if (!tail) {
            return false;
        }
        
        if (head.get() == tail) {
            head.reset();
            tail = nullptr;
        } else {
            tail = tail->prev;
            tail->next.reset();
        }
        size--;
        return true;
    }
    
    // Display forward
    void displayForward() const {
        if (!head) {
            std::cout << "Empty list" << std::endl;
            return;
        }
        
        DoublyNode<T>* current = head.get();
        while (current) {
            std::cout << current->data;
            if (current->next) {
                std::cout << " <-> ";
            }
            current = current->next.get();
        }
        std::cout << " <-> NULL" << std::endl;
    }
    
    // Display backward
    void displayBackward() const {
        if (!tail) {
            std::cout << "Empty list" << std::endl;
            return;
        }
        
        DoublyNode<T>* current = tail;
        while (current) {
            std::cout << current->data;
            if (current->prev) {
                std::cout << " <-> ";
            }
            current = current->prev;
        }
        std::cout << " <-> NULL" << std::endl;
    }
    
    // Utility methods
    bool isEmpty() const { return size == 0; }
    size_t getSize() const { return size; }
    
    void clear() {
        while (head) {
            head = std::move(head->next);
        }
        tail = nullptr;
        size = 0;
    }
    
    void fillRandom(size_t count, T minVal, T maxVal) {
        clear();
        
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<T> dist(minVal, maxVal);
        
        for (size_t i = 0; i < count; i++) {
            insertAtEnd(dist(gen));
        }
    }
};

template<typename T>
class CircularLinkedList {
private:
    SinglyNode<T>* head;
    size_t size;
    
public:
    CircularLinkedList() : head(nullptr), size(0) {}
    
    ~CircularLinkedList() {
        clear();
    }
    
    // Insert at the beginning
    void insertAtBeginning(const T& value) {
        auto newNode = std::make_unique<SinglyNode<T>>(value);
        
        if (!head) {
            head = newNode.get();
            head->next = std::unique_ptr<SinglyNode<T>>(head);
        } else {
            newNode->next = std::unique_ptr<SinglyNode<T>>(head);
            
            // Find the last node and update its next pointer
            SinglyNode<T>* current = head;
            while (current->next.get() != head) {
                current = current->next.get();
            }
            
            current->next = std::move(newNode);
            head = newNode.get();
        }
        size++;
    }
    
    // Insert at the end
    void insertAtEnd(const T& value) {
        auto newNode = std::make_unique<SinglyNode<T>>(value);
        
        if (!head) {
            head = newNode.get();
            head->next = std::unique_ptr<SinglyNode<T>>(head);
        } else {
            newNode->next = std::unique_ptr<SinglyNode<T>>(head);
            
            // Find the last node
            SinglyNode<T>* current = head;
            while (current->next.get() != head) {
                current = current->next.get();
            }
            
            current->next = std::move(newNode);
        }
        size++;
    }
    
    // Display the list
    void display() const {
        if (!head) {
            std::cout << "Empty list" << std::endl;
            return;
        }
        
        SinglyNode<T>* current = head;
        std::cout << current->data << " -> ";
        current = current->next.get();
        
        while (current != head) {
            std::cout << current->data << " -> ";
            current = current->next.get();
        }
        
        std::cout << "(back to " << head->data << ")" << std::endl;
    }
    
    // Josephus problem
    T josephus(int k) {
        if (!head || k <= 0) {
            throw std::invalid_argument("Invalid parameters");
        }
        
        SinglyNode<T>* current = head;
        
        // Find the last node
        while (current->next.get() != head) {
            current = current->next.get();
        }
        
        // Eliminate every k-th person
        while (size > 1) {
            for (int i = 1; i < k; i++) {
                current = current->next.get();
            }
            
            SinglyNode<T>* toDelete = current->next.get();
            current->next = std::move(current->next->next);
            
            if (toDelete == head) {
                head = current->next.get();
            }
            
            size--;
        }
        
        return head->data;
    }
    
    // Utility methods
    bool isEmpty() const { return size == 0; }
    size_t getSize() const { return size; }
    
    void clear() {
        while (size > 0) {
            if (head) {
                if (head->next.get() == head) {
                    head = nullptr;
                    size = 0;
                } else {
                    SinglyNode<T>* current = head;
                    while (current->next.get() != head) {
                        current = current->next.get();
                    }
                    current->next = std::move(head->next);
                    head = current->next.get();
                    size--;
                }
            }
        }
    }
    
    void fillRandom(size_t count, T minVal, T maxVal) {
        clear();
        
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_int_distribution<T> dist(minVal, maxVal);
        
        for (size_t i = 0; i < count; i++) {
            insertAtEnd(dist(gen));
        }
    }
};

class LinkedListDemo {
public:
    static void demonstrateSinglyLinkedList() {
        std::cout << "\n=== Singly Linked List Demo ===" << std::endl;
        
        SinglyLinkedList<int> list;
        
        // Insert elements
        std::cout << "Inserting elements..." << std::endl;
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        list.insertAtBeginning(0);
        list.insertAtPosition(2, 1.5);
        
        std::cout << "List: ";
        list.display();
        std::cout << "Size: " << list.getSize() << std::endl;
        
        // Search
        int index = list.search(2);
        std::cout << "Search for 2: " << (index != -1 ? "Found at " + std::to_string(index) : "Not found") << std::endl;
        
        // Remove elements
        std::cout << "Removing element 1.5..." << std::endl;
        list.removeByValue(1);
        std::cout << "List after removal: ";
        list.display();
        
        // Sort
        std::cout << "Sorting list..." << std::endl;
        list.sort();
        std::cout << "Sorted list: ";
        list.display();
        
        // Reverse
        std::cout << "Reversing list..." << std::endl;
        list.reverse();
        std::cout << "Reversed list: ";
        list.display();
    }
    
    static void demonstrateDoublyLinkedList() {
        std::cout << "\n=== Doubly Linked List Demo ===" << std::endl;
        
        DoublyLinkedList<int> list;
        
        // Insert elements
        std::cout << "Inserting elements..." << std::endl;
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        list.insertAtBeginning(0);
        
        std::cout << "Forward: ";
        list.displayForward();
        std::cout << "Backward: ";
        list.displayBackward();
        
        // Remove from beginning
        std::cout << "Removing from beginning..." << std::endl;
        list.removeFromBeginning();
        std::cout << "Forward: ";
        list.displayForward();
        
        // Remove from end
        std::cout << "Removing from end..." << std::endl;
        list.removeFromEnd();
        std::cout << "Forward: ";
        list.displayForward();
    }
    
    static void demonstrateCircularLinkedList() {
        std::cout << "\n=== Circular Linked List Demo ===" << std::endl;
        
        CircularLinkedList<int> list;
        
        // Insert elements
        std::cout << "Inserting elements..." << std::endl;
        for (int i = 1; i <= 5; i++) {
            list.insertAtEnd(i);
        }
        
        std::cout << "Circular list: ";
        list.display();
        
        // Josephus problem
        std::cout << "\nJosephus problem (k=3):" << std::endl;
        CircularLinkedList<int> josephusList;
        for (int i = 1; i <= 7; i++) {
            josephusList.insertAtEnd(i);
        }
        
        std::cout << "Initial list: ";
        josephusList.display();
        
        int survivor = josephusList.josephus(3);
        std::cout << "Survivor: " << survivor << std::endl;
    }
    
    static void compareWithArray() {
        std::cout << "\n=== Performance Comparison ===" << std::endl;
        
        const size_t SIZE = 10000;
        
        // Test array operations
        std::vector<int> array;
        array.reserve(SIZE);
        
        auto start = std::chrono::high_resolution_clock::now();
        for (size_t i = 0; i < SIZE; i++) {
            array.push_back(i);
        }
        auto end = std::chrono::high_resolution_clock::now();
        auto arrayInsertTime = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
        
        // Test linked list operations
        SinglyLinkedList<int> list;
        
        start = std::chrono::high_resolution_clock::now();
        for (size_t i = 0; i < SIZE; i++) {
            list.insertAtEnd(i);
        }
        end = std::chrono::high_resolution_clock::now();
        auto listInsertTime = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
        
        std::cout << "Insertion time for " << SIZE << " elements:" << std::endl;
        std::cout << "Array: " << arrayInsertTime.count() << " μs" << std::endl;
        std::cout << "Linked List: " << listInsertTime.count() << " μs" << std::endl;
        
        // Test access time
        start = std::chrono::high_resolution_clock::now();
        int arrayValue = array[SIZE / 2];
        end = std::chrono::high_resolution_clock::now();
        auto arrayAccessTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start);
        
        start = std::chrono::high_resolution_clock::now();
        int listValue = list.get(SIZE / 2);
        end = std::chrono::high_resolution_clock::now();
        auto listAccessTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start);
        
        std::cout << "\nAccess time for middle element:" << std::endl;
        std::cout << "Array: " << arrayAccessTime.count() << " ns" << std::endl;
        std::cout << "Linked List: " << listAccessTime.count() << " ns" << std::endl;
        
        std::cout << "\nArray access is " << (listAccessTime.count() / arrayAccessTime.count()) 
                  << "x faster than linked list access" << std::endl;
    }
};

int main() {
    std::cout << "Linked List Operations Demo" << std::endl;
    std::cout << "==========================" << std::endl;
    
    try {
        LinkedListDemo::demonstrateSinglyLinkedList();
        LinkedListDemo::demonstrateDoublyLinkedList();
        LinkedListDemo::demonstrateCircularLinkedList();
        LinkedListDemo::compareWithArray();
        
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
        return 1;
    }
    
    std::cout << "\nDemo completed successfully!" << std::endl;
    return 0;
}
```

### Code Explanation
1. **SinglyLinkedList**: Basic linked list with forward traversal only
2. **DoublyLinkedList**: Linked list with bidirectional traversal
3. **CircularLinkedList**: Linked list where last node points to first
4. **Smart Pointers**: Using std::unique_ptr for automatic memory management
5. **Advanced Algorithms**: Merge sort, Josephus problem, list reversal
6. **Performance Comparison**: Comparing linked lists with arrays

## Cross-Platform Compilation and Execution

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o linked_list_demo main.cpp
./linked_list_demo
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o linked_list_demo.exe main.cpp
linked_list_demo.exe
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o linked_list_demo.exe main.cpp
./linked_list_demo.exe
```

## Key Concepts

### Memory Management with Smart Pointers
```cpp
class SmartPointerDemo {
public:
    static void demonstrateMemorySafety() {
        std::cout << "\n=== Smart Pointer Memory Management ===" << std::endl;
        
        // Using unique_ptr for automatic memory management
        auto list = std::make_unique<SinglyLinkedList<int>>();
        
        // Fill with data
        list->fillRandom(1000, 1, 100);
        
        std::cout << "List size: " << list->getSize() << std::endl;
        
        // Memory is automatically freed when list goes out of scope
        // No need for explicit delete calls
        
        // Demonstrate exception safety
        try {
            list->insertAtPosition(2000, 999); // This will throw
        } catch (const std::exception& e) {
            std::cout << "Exception caught: " << e.what() << std::endl;
            std::cout << "Memory is still properly managed" << std::endl;
        }
        
        std::cout << "List still valid after exception: " << list->getSize() << " elements" << std::endl;
    }
};
```

### Advanced Linked List Algorithms
```cpp
class AdvancedAlgorithms {
public:
    // Detect cycle in linked list (Floyd's algorithm)
    template<typename T>
    static bool hasCycle(SinglyNode<T>* head) {
        if (!head || !head->next) {
            return false;
        }
        
        SinglyNode<T>* slow = head;
        SinglyNode<T>* fast = head;
        
        while (fast && fast->next) {
            slow = slow->next.get();
            fast = fast->next->next.get();
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    
    // Find the middle of linked list
    template<typename T>
    static SinglyNode<T>* findMiddle(SinglyNode<T>* head) {
        if (!head) {
            return nullptr;
        }
        
        SinglyNode<T>* slow = head;
        SinglyNode<T>* fast = head;
        
        while (fast && fast->next) {
            slow = slow->next.get();
            fast = fast->next->next.get();
        }
        
        return slow;
    }
    
    // Merge two sorted linked lists
    template<typename T>
    static std::unique_ptr<SinglyNode<T>> mergeSortedLists(
        std::unique_ptr<SinglyNode<T>> list1,
        std::unique_ptr<SinglyNode<T>> list2) {
        
        if (!list1) return list2;
        if (!list2) return list1;
        
        if (list1->data <= list2->data) {
            list1->next = mergeSortedLists(std::move(list1->next), std::move(list2));
            return list1;
        } else {
            list2->next = mergeSortedLists(std::move(list1), std::move(list2->next));
            return list2;
        }
    }
    
    // Remove nth node from end
    template<typename T>
    static std::unique_ptr<SinglyNode<T>> removeNthFromEnd(
        std::unique_ptr<SinglyNode<T>> head, int n) {
        
        auto dummy = std::make_unique<SinglyNode<T>>(T{});
        dummy->next = std::move(head);
        
        SinglyNode<T>* first = dummy.get();
        SinglyNode<T>* second = dummy.get();
        
        // Move first n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first->next.get();
        }
        
        // Move both pointers until first reaches end
        while (first) {
            first = first->next.get();
            second = second->next.get();
        }
        
        // Remove the nth node
        second->next = std::move(second->next->next);
        
        return std::move(dummy->next);
    }
};
```

## Best Practices

1. **Use Smart Pointers**: Prefer std::unique_ptr for automatic memory management
2. **Handle Edge Cases**: Always check for empty lists and null pointers
3. **Exception Safety**: Use RAII principles for resource management
4. **Algorithm Selection**: Choose appropriate algorithms based on requirements
5. **Memory Efficiency**: Consider memory overhead of linked lists vs arrays

## Common Pitfalls

1. **Memory Leaks**: Not properly managing dynamic memory
2. **Dangling Pointers**: Accessing deleted nodes
3. **Infinite Loops**: Not handling circular references properly
4. **Off-by-One Errors**: Incorrect boundary conditions
5. **Lost References**: Not updating head/tail pointers correctly

## Exercises

1. Implement a polynomial class using linked lists
2. Create a music playlist manager with linked lists
3. Implement LRU cache using doubly linked list and hash map
4. Build a text editor with undo functionality using linked lists
5. Create a snake game using circular linked list

## Learning Outcomes
- Understanding linked list fundamentals and memory layout
- Learning different types of linked lists and their applications
- Implementing common linked list operations and algorithms
- Understanding trade-offs between arrays and linked lists
- Cross-platform C++ development for linked list programs
