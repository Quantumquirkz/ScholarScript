# Linked Lists

## Overview
Linked lists are linear data structures where elements are stored in nodes, and each node contains a data field and a reference (link) to the next node in the sequence. Unlike arrays, linked lists don't store elements in contiguous memory locations.

## Key Concepts

### What are Linked Lists?
A linked list is a collection of nodes where each node contains:
- **Data**: The actual value stored
- **Next**: A pointer/reference to the next node in the sequence

### Properties of Linked Lists
1. **Dynamic Size**: Can grow and shrink during runtime
2. **Non-contiguous Memory**: Nodes can be stored anywhere in memory
3. **Sequential Access**: Must traverse from head to access elements
4. **Memory Efficient**: Only uses memory for actual data stored

### Types of Linked Lists
- **Singly Linked List**: Each node points to the next node
- **Doubly Linked List**: Each node points to both next and previous nodes
- **Circular Linked List**: Last node points back to the first node

## Singly Linked Lists

### Node Structure
```cpp
#include <iostream>

template<typename T>
struct ListNode {
    T data;
    ListNode<T>* next;
    
    ListNode(const T& value) : data(value), next(nullptr) {}
};

template<typename T>
class SinglyLinkedList {
private:
    ListNode<T>* head;
    int size;
    
public:
    SinglyLinkedList() : head(nullptr), size(0) {}
    
    ~SinglyLinkedList() {
        clear();
    }
    
    // Insert at the beginning
    void insertAtBeginning(const T& data) {
        ListNode<T>* newNode = new ListNode<T>(data);
        newNode->next = head;
        head = newNode;
        size++;
    }
    
    // Insert at the end
    void insertAtEnd(const T& data) {
        ListNode<T>* newNode = new ListNode<T>(data);
        
        if (head == nullptr) {
            head = newNode;
        } else {
            ListNode<T>* current = head;
            while (current->next != nullptr) {
                current = current->next;
            }
            current->next = newNode;
        }
        size++;
    }
    
    // Insert at specific position
    void insertAtPosition(int position, const T& data) {
        if (position < 0 || position > size) {
            std::cout << "Invalid position!" << std::endl;
            return;
        }
        
        if (position == 0) {
            insertAtBeginning(data);
            return;
        }
        
        ListNode<T>* newNode = new ListNode<T>(data);
        ListNode<T>* current = head;
        
        for (int i = 0; i < position - 1; i++) {
            current = current->next;
        }
        
        newNode->next = current->next;
        current->next = newNode;
        size++;
    }
    
    // Delete from beginning
    void deleteFromBeginning() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        ListNode<T>* temp = head;
        head = head->next;
        delete temp;
        size--;
    }
    
    // Delete from end
    void deleteFromEnd() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        if (head->next == nullptr) {
            delete head;
            head = nullptr;
            size = 0;
            return;
        }
        
        ListNode<T>* current = head;
        while (current->next->next != nullptr) {
            current = current->next;
        }
        
        delete current->next;
        current->next = nullptr;
        size--;
    }
    
    // Delete by value
    bool deleteByValue(const T& value) {
        if (head == nullptr) {
            return false;
        }
        
        if (head->data == value) {
            deleteFromBeginning();
            return true;
        }
        
        ListNode<T>* current = head;
        while (current->next != nullptr && current->next->data != value) {
            current = current->next;
        }
        
        if (current->next != nullptr) {
            ListNode<T>* temp = current->next;
            current->next = current->next->next;
            delete temp;
            size--;
            return true;
        }
        
        return false;
    }
    
    // Search for element
    int search(const T& value) {
        ListNode<T>* current = head;
        int index = 0;
        
        while (current != nullptr) {
            if (current->data == value) {
                return index;
            }
            current = current->next;
            index++;
        }
        
        return -1; // Not found
    }
    
    // Get element at index
    T get(int index) {
        if (index < 0 || index >= size) {
            throw std::out_of_range("Index out of range");
        }
        
        ListNode<T>* current = head;
        for (int i = 0; i < index; i++) {
            current = current->next;
        }
        
        return current->data;
    }
    
    // Update element at index
    void set(int index, const T& value) {
        if (index < 0 || index >= size) {
            std::cout << "Index out of range!" << std::endl;
            return;
        }
        
        ListNode<T>* current = head;
        for (int i = 0; i < index; i++) {
            current = current->next;
        }
        
        current->data = value;
    }
    
    // Display list
    void display() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        ListNode<T>* current = head;
        std::cout << "List: ";
        while (current != nullptr) {
            std::cout << current->data << " -> ";
            current = current->next;
        }
        std::cout << "NULL" << std::endl;
    }
    
    // Get size
    int getSize() const {
        return size;
    }
    
    // Check if empty
    bool isEmpty() const {
        return size == 0;
    }
    
    // Clear list
    void clear() {
        while (head != nullptr) {
            ListNode<T>* temp = head;
            head = head->next;
            delete temp;
        }
        size = 0;
    }
    
    // Reverse list
    void reverse() {
        ListNode<T>* prev = nullptr;
        ListNode<T>* current = head;
        ListNode<T>* next = nullptr;
        
        while (current != nullptr) {
            next = current->next;
            current->next = prev;
            prev = current;
            current = next;
        }
        
        head = prev;
    }
};
```

### Basic Operations Demo
```cpp
int main() {
    SinglyLinkedList<int> list;
    
    // Insert elements
    list.insertAtEnd(10);
    list.insertAtEnd(20);
    list.insertAtEnd(30);
    list.display();
    
    // Insert at beginning
    list.insertAtBeginning(5);
    list.display();
    
    // Insert at position
    list.insertAtPosition(2, 15);
    list.display();
    
    // Search for element
    int index = list.search(20);
    std::cout << "Element 20 found at index: " << index << std::endl;
    
    // Update element
    list.set(1, 25);
    list.display();
    
    // Delete operations
    list.deleteFromBeginning();
    list.display();
    
    list.deleteFromEnd();
    list.display();
    
    list.deleteByValue(15);
    list.display();
    
    // Reverse list
    list.reverse();
    std::cout << "Reversed list: ";
    list.display();
    
    std::cout << "List size: " << list.getSize() << std::endl;
    
    return 0;
}
```

## Doubly Linked Lists

### Doubly Linked List Implementation
```cpp
#include <iostream>

template<typename T>
struct DoublyListNode {
    T data;
    DoublyListNode<T>* next;
    DoublyListNode<T>* prev;
    
    DoublyListNode(const T& value) : data(value), next(nullptr), prev(nullptr) {}
};

template<typename T>
class DoublyLinkedList {
private:
    DoublyListNode<T>* head;
    DoublyListNode<T>* tail;
    int size;
    
public:
    DoublyLinkedList() : head(nullptr), tail(nullptr), size(0) {}
    
    ~DoublyLinkedList() {
        clear();
    }
    
    // Insert at the beginning
    void insertAtBeginning(const T& data) {
        DoublyListNode<T>* newNode = new DoublyListNode<T>(data);
        
        if (head == nullptr) {
            head = tail = newNode;
        } else {
            newNode->next = head;
            head->prev = newNode;
            head = newNode;
        }
        size++;
    }
    
    // Insert at the end
    void insertAtEnd(const T& data) {
        DoublyListNode<T>* newNode = new DoublyListNode<T>(data);
        
        if (tail == nullptr) {
            head = tail = newNode;
        } else {
            tail->next = newNode;
            newNode->prev = tail;
            tail = newNode;
        }
        size++;
    }
    
    // Insert at specific position
    void insertAtPosition(int position, const T& data) {
        if (position < 0 || position > size) {
            std::cout << "Invalid position!" << std::endl;
            return;
        }
        
        if (position == 0) {
            insertAtBeginning(data);
            return;
        }
        
        if (position == size) {
            insertAtEnd(data);
            return;
        }
        
        DoublyListNode<T>* newNode = new DoublyListNode<T>(data);
        DoublyListNode<T>* current = head;
        
        for (int i = 0; i < position; i++) {
            current = current->next;
        }
        
        newNode->next = current;
        newNode->prev = current->prev;
        current->prev->next = newNode;
        current->prev = newNode;
        size++;
    }
    
    // Delete from beginning
    void deleteFromBeginning() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        DoublyListNode<T>* temp = head;
        head = head->next;
        
        if (head != nullptr) {
            head->prev = nullptr;
        } else {
            tail = nullptr;
        }
        
        delete temp;
        size--;
    }
    
    // Delete from end
    void deleteFromEnd() {
        if (tail == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        DoublyListNode<T>* temp = tail;
        tail = tail->prev;
        
        if (tail != nullptr) {
            tail->next = nullptr;
        } else {
            head = nullptr;
        }
        
        delete temp;
        size--;
    }
    
    // Delete by value
    bool deleteByValue(const T& value) {
        DoublyListNode<T>* current = head;
        
        while (current != nullptr) {
            if (current->data == value) {
                if (current == head) {
                    deleteFromBeginning();
                } else if (current == tail) {
                    deleteFromEnd();
                } else {
                    current->prev->next = current->next;
                    current->next->prev = current->prev;
                    delete current;
                    size--;
                }
                return true;
            }
            current = current->next;
        }
        
        return false;
    }
    
    // Display forward
    void displayForward() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        DoublyListNode<T>* current = head;
        std::cout << "Forward: ";
        while (current != nullptr) {
            std::cout << current->data << " <-> ";
            current = current->next;
        }
        std::cout << "NULL" << std::endl;
    }
    
    // Display backward
    void displayBackward() {
        if (tail == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        DoublyListNode<T>* current = tail;
        std::cout << "Backward: ";
        while (current != nullptr) {
            std::cout << current->data << " <-> ";
            current = current->prev;
        }
        std::cout << "NULL" << std::endl;
    }
    
    // Get size
    int getSize() const {
        return size;
    }
    
    // Check if empty
    bool isEmpty() const {
        return size == 0;
    }
    
    // Clear list
    void clear() {
        while (head != nullptr) {
            DoublyListNode<T>* temp = head;
            head = head->next;
            delete temp;
        }
        tail = nullptr;
        size = 0;
    }
};
```

## Circular Linked Lists

### Circular Linked List Implementation
```cpp
#include <iostream>

template<typename T>
class CircularLinkedList {
private:
    ListNode<T>* head;
    int size;
    
public:
    CircularLinkedList() : head(nullptr), size(0) {}
    
    ~CircularLinkedList() {
        clear();
    }
    
    // Insert at the beginning
    void insertAtBeginning(const T& data) {
        ListNode<T>* newNode = new ListNode<T>(data);
        
        if (head == nullptr) {
            head = newNode;
            head->next = head; // Point to itself
        } else {
            newNode->next = head;
            
            // Find the last node and update its next pointer
            ListNode<T>* current = head;
            while (current->next != head) {
                current = current->next;
            }
            
            current->next = newNode;
            head = newNode;
        }
        size++;
    }
    
    // Insert at the end
    void insertAtEnd(const T& data) {
        ListNode<T>* newNode = new ListNode<T>(data);
        
        if (head == nullptr) {
            head = newNode;
            head->next = head;
        } else {
            newNode->next = head;
            
            // Find the last node
            ListNode<T>* current = head;
            while (current->next != head) {
                current = current->next;
            }
            
            current->next = newNode;
        }
        size++;
    }
    
    // Delete from beginning
    void deleteFromBeginning() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        if (head->next == head) {
            // Only one node
            delete head;
            head = nullptr;
        } else {
            // Find the last node
            ListNode<T>* last = head;
            while (last->next != head) {
                last = last->next;
            }
            
            ListNode<T>* temp = head;
            head = head->next;
            last->next = head;
            delete temp;
        }
        size--;
    }
    
    // Delete by value
    bool deleteByValue(const T& value) {
        if (head == nullptr) {
            return false;
        }
        
        ListNode<T>* current = head;
        ListNode<T>* prev = nullptr;
        
        // Find the node to delete
        do {
            if (current->data == value) {
                if (current == head && current->next == head) {
                    // Only one node
                    delete head;
                    head = nullptr;
                } else if (current == head) {
                    // Deleting head node
                    ListNode<T>* last = head;
                    while (last->next != head) {
                        last = last->next;
                    }
                    
                    head = head->next;
                    last->next = head;
                    delete current;
                } else {
                    // Deleting middle or end node
                    prev->next = current->next;
                    delete current;
                }
                size--;
                return true;
            }
            prev = current;
            current = current->next;
        } while (current != head);
        
        return false;
    }
    
    // Display list
    void display() {
        if (head == nullptr) {
            std::cout << "List is empty!" << std::endl;
            return;
        }
        
        ListNode<T>* current = head;
        std::cout << "Circular List: ";
        
        do {
            std::cout << current->data << " -> ";
            current = current->next;
        } while (current != head);
        
        std::cout << "(back to " << head->data << ")" << std::endl;
    }
    
    // Get size
    int getSize() const {
        return size;
    }
    
    // Check if empty
    bool isEmpty() const {
        return size == 0;
    }
    
    // Clear list
    void clear() {
        while (head != nullptr) {
            deleteFromBeginning();
        }
    }
    
    // Josephus problem (circular elimination)
    T josephus(int k) {
        if (head == nullptr || k <= 0) {
            throw std::invalid_argument("Invalid parameters");
        }
        
        ListNode<T>* current = head;
        
        // Find the last node
        while (current->next != head) {
            current = current->next;
        }
        
        // Eliminate every k-th person
        while (size > 1) {
            for (int i = 1; i < k; i++) {
                current = current->next;
            }
            
            ListNode<T>* toDelete = current->next;
            current->next = toDelete->next;
            
            if (toDelete == head) {
                head = current->next;
            }
            
            delete toDelete;
            size--;
        }
        
        return head->data;
    }
};
```

## Advanced Linked List Operations

### Linked List Algorithms
```cpp
#include <iostream>
#include <unordered_set>

class LinkedListAlgorithms {
public:
    // Detect cycle in linked list (Floyd's cycle detection)
    template<typename T>
    static bool hasCycle(ListNode<T>* head) {
        if (head == nullptr || head->next == nullptr) {
            return false;
        }
        
        ListNode<T>* slow = head;
        ListNode<T>* fast = head;
        
        while (fast != nullptr && fast->next != nullptr) {
            slow = slow->next;
            fast = fast->next->next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    
    // Find the middle of linked list
    template<typename T>
    static ListNode<T>* findMiddle(ListNode<T>* head) {
        if (head == nullptr) {
            return nullptr;
        }
        
        ListNode<T>* slow = head;
        ListNode<T>* fast = head;
        
        while (fast != nullptr && fast->next != nullptr) {
            slow = slow->next;
            fast = fast->next->next;
        }
        
        return slow;
    }
    
    // Merge two sorted linked lists
    template<typename T>
    static ListNode<T>* mergeSortedLists(ListNode<T>* list1, ListNode<T>* list2) {
        ListNode<T>* dummy = new ListNode<T>(T{});
        ListNode<T>* current = dummy;
        
        while (list1 != nullptr && list2 != nullptr) {
            if (list1->data <= list2->data) {
                current->next = list1;
                list1 = list1->next;
            } else {
                current->next = list2;
                list2 = list2->next;
            }
            current = current->next;
        }
        
        // Append remaining elements
        if (list1 != nullptr) {
            current->next = list1;
        } else {
            current->next = list2;
        }
        
        ListNode<T>* result = dummy->next;
        delete dummy;
        return result;
    }
    
    // Remove duplicates from sorted list
    template<typename T>
    static void removeDuplicates(ListNode<T>* head) {
        if (head == nullptr) {
            return;
        }
        
        ListNode<T>* current = head;
        
        while (current != nullptr && current->next != nullptr) {
            if (current->data == current->next->data) {
                ListNode<T>* temp = current->next;
                current->next = current->next->next;
                delete temp;
            } else {
                current = current->next;
            }
        }
    }
    
    // Check if linked list is palindrome
    template<typename T>
    static bool isPalindrome(ListNode<T>* head) {
        if (head == nullptr || head->next == nullptr) {
            return true;
        }
        
        // Find middle
        ListNode<T>* middle = findMiddle(head);
        
        // Reverse second half
        ListNode<T>* secondHalf = reverseList(middle->next);
        middle->next = nullptr;
        
        // Compare both halves
        ListNode<T>* current1 = head;
        ListNode<T>* current2 = secondHalf;
        bool isPal = true;
        
        while (current2 != nullptr) {
            if (current1->data != current2->data) {
                isPal = false;
                break;
            }
            current1 = current1->next;
            current2 = current2->next;
        }
        
        // Restore original list
        middle->next = reverseList(secondHalf);
        
        return isPal;
    }
    
    // Reverse linked list recursively
    template<typename T>
    static ListNode<T>* reverseList(ListNode<T>* head) {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }
        
        ListNode<T>* newHead = reverseList(head->next);
        head->next->next = head;
        head->next = nullptr;
        
        return newHead;
    }
    
    // Remove nth node from end
    template<typename T>
    static ListNode<T>* removeNthFromEnd(ListNode<T>* head, int n) {
        ListNode<T>* dummy = new ListNode<T>(T{});
        dummy->next = head;
        
        ListNode<T>* first = dummy;
        ListNode<T>* second = dummy;
        
        // Move first n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first->next;
        }
        
        // Move both pointers until first reaches end
        while (first != nullptr) {
            first = first->next;
            second = second->next;
        }
        
        // Remove the nth node
        ListNode<T>* toDelete = second->next;
        second->next = second->next->next;
        delete toDelete;
        
        ListNode<T>* result = dummy->next;
        delete dummy;
        return result;
    }
};

// Demo of advanced operations
int main() {
    SinglyLinkedList<int> list;
    
    // Create a list
    list.insertAtEnd(1);
    list.insertAtEnd(2);
    list.insertAtEnd(3);
    list.insertAtEnd(2);
    list.insertAtEnd(1);
    
    std::cout << "Original list: ";
    list.display();
    
    // Find middle
    ListNode<int>* middle = LinkedListAlgorithms::findMiddle(list.head);
    if (middle) {
        std::cout << "Middle element: " << middle->data << std::endl;
    }
    
    // Check if palindrome
    bool isPal = LinkedListAlgorithms::isPalindrome(list.head);
    std::cout << "Is palindrome: " << (isPal ? "Yes" : "No") << std::endl;
    
    // Remove duplicates (assuming sorted list)
    SinglyLinkedList<int> sortedList;
    sortedList.insertAtEnd(1);
    sortedList.insertAtEnd(1);
    sortedList.insertAtEnd(2);
    sortedList.insertAtEnd(2);
    sortedList.insertAtEnd(3);
    
    std::cout << "Before removing duplicates: ";
    sortedList.display();
    
    LinkedListAlgorithms::removeDuplicates(sortedList.head);
    std::cout << "After removing duplicates: ";
    sortedList.display();
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o linked_lists main.cpp
./linked_lists

# Windows (PowerShell)
g++ -std=c++17 -O2 -o linked_lists.exe main.cpp
./linked_lists.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o linked_lists.exe main.cpp
linked_lists.exe
```

### Memory Management Best Practices
```cpp
#include <memory>

template<typename T>
class SmartLinkedList {
private:
    struct Node {
        T data;
        std::unique_ptr<Node> next;
        
        Node(const T& value) : data(value), next(nullptr) {}
    };
    
    std::unique_ptr<Node> head;
    int size;
    
public:
    SmartLinkedList() : head(nullptr), size(0) {}
    
    // Destructor not needed - smart pointers handle cleanup automatically
    
    void insertAtBeginning(const T& data) {
        auto newNode = std::make_unique<Node>(data);
        newNode->next = std::move(head);
        head = std::move(newNode);
        size++;
    }
    
    // Other methods...
};
```

## Performance Analysis

### Time Complexity Comparison
| Operation | Array | Singly Linked List | Doubly Linked List |
|-----------|-------|-------------------|-------------------|
| Access | O(1) | O(n) | O(n) |
| Search | O(n) | O(n) | O(n) |
| Insertion (beginning) | O(n) | O(1) | O(1) |
| Insertion (end) | O(1) | O(n) | O(1) |
| Insertion (middle) | O(n) | O(n) | O(n) |
| Deletion (beginning) | O(n) | O(1) | O(1) |
| Deletion (end) | O(1) | O(n) | O(1) |
| Deletion (middle) | O(n) | O(n) | O(n) |

## Best Practices

1. **Use Smart Pointers**: Prefer smart pointers for automatic memory management
2. **Handle Edge Cases**: Always check for empty lists and null pointers
3. **Memory Leaks**: Ensure proper cleanup in destructors
4. **Iterator Pattern**: Implement iterators for clean traversal
5. **Template Usage**: Use templates for type flexibility

## Common Pitfalls

1. **Memory Leaks**: Not deleting nodes properly
2. **Dangling Pointers**: Accessing deleted nodes
3. **Infinite Loops**: Not handling circular references
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
