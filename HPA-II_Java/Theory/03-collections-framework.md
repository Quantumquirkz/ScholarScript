# Java Collections Framework

## Overview
The Java Collections Framework provides a unified architecture for representing and manipulating collections of objects. It includes interfaces, implementations, and algorithms that make working with groups of objects more efficient and easier to use.

## Key Concepts

### Collections Hierarchy
```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList
│   ├── LinkedList
│   └── Vector
├── Set (Interface)
│   ├── HashSet
│   ├── LinkedHashSet
│   └── TreeSet
└── Queue (Interface)
    ├── PriorityQueue
    └── LinkedList

Map (Interface)
├── HashMap
├── LinkedHashMap
├── TreeMap
└── Hashtable
```

## List Interface and Implementations

### ArrayList
ArrayList is a resizable array implementation of the List interface.

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        // Create ArrayList
        List<String> names = new ArrayList<>();
        
        // Add elements
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add(1, "David"); // Insert at specific index
        
        // Display elements
        System.out.println("Names: " + names);
        System.out.println("Size: " + names.size());
        System.out.println("Element at index 2: " + names.get(2));
        
        // Check if element exists
        if (names.contains("Alice")) {
            System.out.println("Alice is in the list");
        }
        
        // Remove elements
        names.remove("Bob");
        names.remove(0);
        
        System.out.println("After removal: " + names);
        
        // Iterate through list
        System.out.println("Iterating through list:");
        for (String name : names) {
            System.out.println("- " + name);
        }
        
        // Using index-based loop
        System.out.println("Index-based iteration:");
        for (int i = 0; i < names.size(); i++) {
            System.out.println("Index " + i + ": " + names.get(i));
        }
    }
}
```

### LinkedList
LinkedList provides a doubly-linked list implementation with efficient insertion and deletion operations.

```java
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        // Create LinkedList
        List<Integer> numbers = new LinkedList<>();
        
        // Add elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        
        // LinkedList-specific operations
        LinkedList<Integer> linkedList = (LinkedList<Integer>) numbers;
        
        // Add to beginning and end
        linkedList.addFirst(5);
        linkedList.addLast(40);
        
        System.out.println("LinkedList: " + linkedList);
        
        // Remove from beginning and end
        int first = linkedList.removeFirst();
        int last = linkedList.removeLast();
        
        System.out.println("Removed first: " + first);
        System.out.println("Removed last: " + last);
        System.out.println("After removal: " + linkedList);
        
        // Peek operations (without removal)
        System.out.println("First element: " + linkedList.peekFirst());
        System.out.println("Last element: " + linkedList.peekLast());
    }
}
```

## Set Interface and Implementations

### HashSet
HashSet stores unique elements using hash table for fast access.

```java
import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        // Create HashSet
        Set<String> colors = new HashSet<>();
        
        // Add elements
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Red"); // Duplicate - not added
        colors.add("Yellow");
        
        System.out.println("Colors: " + colors);
        System.out.println("Size: " + colors.size());
        
        // Check existence
        if (colors.contains("Red")) {
            System.out.println("Red is in the set");
        }
        
        // Remove element
        colors.remove("Green");
        System.out.println("After removing Green: " + colors);
        
        // Iterate through set
        System.out.println("All colors:");
        for (String color : colors) {
            System.out.println("- " + color);
        }
        
        // Set operations
        Set<String> primaryColors = new HashSet<>();
        primaryColors.add("Red");
        primaryColors.add("Blue");
        primaryColors.add("Yellow");
        
        Set<String> secondaryColors = new HashSet<>();
        secondaryColors.add("Green");
        secondaryColors.add("Purple");
        secondaryColors.add("Orange");
        
        // Union
        Set<String> allColors = new HashSet<>(primaryColors);
        allColors.addAll(secondaryColors);
        System.out.println("All colors (union): " + allColors);
        
        // Intersection
        Set<String> commonColors = new HashSet<>(primaryColors);
        commonColors.retainAll(colors);
        System.out.println("Common colors (intersection): " + commonColors);
    }
}
```

### TreeSet
TreeSet maintains elements in sorted order using a red-black tree.

```java
import java.util.TreeSet;
import java.util.Set;

public class TreeSetExample {
    public static void main(String[] args) {
        // Create TreeSet
        Set<Integer> numbers = new TreeSet<>();
        
        // Add elements (will be automatically sorted)
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(3);
        
        System.out.println("Sorted numbers: " + numbers);
        
        // First and last elements
        TreeSet<Integer> treeSet = (TreeSet<Integer>) numbers;
        System.out.println("First element: " + treeSet.first());
        System.out.println("Last element: " + treeSet.last());
        
        // Head and tail sets
        System.out.println("Elements less than 5: " + treeSet.headSet(5));
        System.out.println("Elements greater than or equal to 5: " + treeSet.tailSet(5));
        
        // Custom comparator for strings
        TreeSet<String> words = new TreeSet<>((s1, s2) -> s2.compareTo(s1)); // Reverse order
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        words.add("date");
        
        System.out.println("Words in reverse order: " + words);
    }
}
```

## Map Interface and Implementations

### HashMap
HashMap provides key-value mapping with fast access using hash table.

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Create HashMap
        Map<String, Integer> ages = new HashMap<>();
        
        // Add key-value pairs
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);
        ages.put("Alice", 26); // Updates existing key
        
        System.out.println("Ages: " + ages);
        
        // Get value by key
        Integer aliceAge = ages.get("Alice");
        System.out.println("Alice's age: " + aliceAge);
        
        // Check if key exists
        if (ages.containsKey("Bob")) {
            System.out.println("Bob is in the map");
        }
        
        // Check if value exists
        if (ages.containsValue(30)) {
            System.out.println("Someone is 30 years old");
        }
        
        // Remove key-value pair
        ages.remove("Charlie");
        System.out.println("After removing Charlie: " + ages);
        
        // Iterate over map
        System.out.println("All entries:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Iterate over keys
        System.out.println("All keys:");
        for (String key : ages.keySet()) {
            System.out.println("- " + key);
        }
        
        // Iterate over values
        System.out.println("All values:");
        for (Integer value : ages.values()) {
            System.out.println("- " + value);
        }
    }
}
```

### TreeMap
TreeMap maintains key-value pairs in sorted order by key.

```java
import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        // Create TreeMap
        Map<String, Integer> scores = new TreeMap<>();
        
        // Add key-value pairs (will be sorted by key)
        scores.put("Charlie", 85);
        scores.put("Alice", 92);
        scores.put("Bob", 78);
        scores.put("David", 96);
        
        System.out.println("Scores (sorted by name): " + scores);
        
        // First and last entries
        TreeMap<String, Integer> treeMap = (TreeMap<String, Integer>) scores;
        System.out.println("First entry: " + treeMap.firstEntry());
        System.out.println("Last entry: " + treeMap.lastEntry());
        
        // Head and tail maps
        System.out.println("Entries before 'C': " + treeMap.headMap("C"));
        System.out.println("Entries from 'C' onwards: " + treeMap.tailMap("C"));
        
        // Custom comparator for reverse order
        Map<Integer, String> reverseScores = new TreeMap<>((a, b) -> b.compareTo(a));
        reverseScores.put(85, "Charlie");
        reverseScores.put(92, "Alice");
        reverseScores.put(78, "Bob");
        reverseScores.put(96, "David");
        
        System.out.println("Scores (sorted by value, descending): " + reverseScores);
    }
}
```

## Queue Interface and Implementations

### PriorityQueue
PriorityQueue orders elements according to their natural ordering or a custom comparator.

```java
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Create PriorityQueue
        Queue<Integer> numbers = new PriorityQueue<>();
        
        // Add elements
        numbers.offer(5);
        numbers.offer(2);
        numbers.offer(8);
        numbers.offer(1);
        numbers.offer(3);
        
        System.out.println("PriorityQueue: " + numbers);
        
        // Process elements by priority (lowest first)
        System.out.println("Processing elements by priority:");
        while (!numbers.isEmpty()) {
            System.out.println("Removed: " + numbers.poll());
        }
        
        // Custom comparator for highest priority first
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(1);
        maxHeap.offer(3);
        
        System.out.println("Max heap: " + maxHeap);
        System.out.println("Peek (highest priority): " + maxHeap.peek());
    }
}
```

## Collections Utility Class

### Common Operations
```java
import java.util.*;

public class CollectionsUtilityExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        
        System.out.println("Original: " + numbers);
        
        // Sort
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        // Reverse
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);
        
        // Shuffle
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);
        
        // Binary search (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 8);
        System.out.println("Index of 8: " + index);
        
        // Fill
        Collections.fill(numbers, 0);
        System.out.println("Filled with 0s: " + numbers);
        
        // Frequency
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "apple");
        int frequency = Collections.frequency(words, "apple");
        System.out.println("Frequency of 'apple': " + frequency);
        
        // Max and min
        List<Integer> testNumbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        System.out.println("Max: " + Collections.max(testNumbers));
        System.out.println("Min: " + Collections.min(testNumbers));
        
        // Swap elements
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        Collections.swap(names, 0, 2);
        System.out.println("After swap: " + names);
    }
}
```

## Iterator and Enhanced For Loop

### Iteration Methods
```java
import java.util.*;

public class IterationExample {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        
        // Enhanced for loop (recommended)
        System.out.println("Enhanced for loop:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        
        // Iterator
        System.out.println("Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("- " + fruit);
        }
        
        // ListIterator (for Lists)
        System.out.println("ListIterator (forward):");
        ListIterator<String> listIterator = fruits.listIterator();
        while (listIterator.hasNext()) {
            String fruit = listIterator.next();
            System.out.println("- " + fruit + " (index: " + listIterator.previousIndex() + ")");
        }
        
        // ListIterator (backward)
        System.out.println("ListIterator (backward):");
        while (listIterator.hasPrevious()) {
            String fruit = listIterator.previous();
            System.out.println("- " + fruit + " (index: " + listIterator.nextIndex() + ")");
        }
        
        // Stream (Java 8+)
        System.out.println("Stream:");
        fruits.stream()
              .filter(fruit -> fruit.startsWith("A"))
              .forEach(fruit -> System.out.println("- " + fruit));
    }
}
```

## Generics in Collections

### Type Safety
```java
import java.util.*;

public class GenericsExample {
    public static void main(String[] args) {
        // Generic collections provide type safety
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");
        // stringList.add(123); // Compilation error
        
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        
        // Generic method
        printList(stringList);
        printList(intList);
        
        // Generic class
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(42);
        
        System.out.println("String box: " + stringBox.get());
        System.out.println("Integer box: " + intBox.get());
    }
    
    // Generic method
    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
    
    // Generic class
    static class Box<T> {
        private T content;
        
        public Box(T content) {
            this.content = content;
        }
        
        public T get() {
            return content;
        }
        
        public void set(T content) {
            this.content = content;
        }
    }
}
```

## Cross-Platform Considerations

### Compilation and Execution
```bash
# Linux/macOS
javac *.java
java ClassName

# Windows (Command Prompt)
javac *.java
java ClassName

# Windows (PowerShell)
javac *.java
java ClassName
```

### Performance Considerations
- **ArrayList**: O(1) access, O(n) insertion/deletion in middle
- **LinkedList**: O(n) access, O(1) insertion/deletion at ends
- **HashSet/HashMap**: O(1) average case for operations
- **TreeSet/TreeMap**: O(log n) for operations, maintains order

## Common Pitfalls

1. **Raw Types**: Always use generics to avoid ClassCastException
2. **Concurrent Modification**: Don't modify collections while iterating
3. **Null Values**: Some collections don't allow null values
4. **Thread Safety**: Most collections are not thread-safe by default
5. **Memory Usage**: Be aware of memory overhead of different implementations

## Best Practices

1. **Use Interfaces**: Declare variables with interface types
2. **Choose Right Implementation**: Select based on usage patterns
3. **Use Generics**: Ensure type safety at compile time
4. **Consider Thread Safety**: Use synchronized collections if needed
5. **Use Utility Methods**: Leverage Collections and Arrays utility classes

## Exercises

1. Create a student management system using ArrayList
2. Implement a word frequency counter using HashMap
3. Build a task scheduler using PriorityQueue
4. Create a phone book using TreeMap
5. Implement a set operations calculator using HashSet

## Learning Outcomes
- Understanding the Java Collections Framework
- Working with List, Set, Map, and Queue interfaces
- Implementing different collection types for specific use cases
- Using generics for type safety
- Cross-platform Java development
