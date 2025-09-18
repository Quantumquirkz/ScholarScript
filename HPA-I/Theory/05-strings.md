# String Manipulation in C++

## Overview
Strings are sequences of characters used to store and manipulate text data. This module covers C++ string operations, character manipulation, and common string algorithms.

## String Types

### C-Style Strings vs C++ Strings
```cpp
#include <iostream>
#include <string>
#include <cstring>

int main() {
    // C-style string
    char cString[] = "Hello";
    char* cStringPtr = "World";
    
    // C++ string
    std::string cppString = "C++ String";
    
    std::cout << "C-string: " << cString << std::endl;
    std::cout << "C++ string: " << cppString << std::endl;
    
    return 0;
}
```

## Basic String Operations

### String Input and Output
```cpp
#include <iostream>
#include <string>

int main() {
    std::string name;
    
    // Reading a single word
    std::cout << "Enter your first name: ";
    std::cin >> name;
    std::cout << "Hello, " << name << "!" << std::endl;
    
    // Reading a full line
    std::cin.ignore(); // Clear the input buffer
    std::cout << "Enter your full name: ";
    std::getline(std::cin, name);
    std::cout << "Full name: " << name << std::endl;
    
    return 0;
}
```

### String Concatenation
```cpp
#include <iostream>
#include <string>

int main() {
    std::string firstName = "John";
    std::string lastName = "Doe";
    
    // Method 1: Using + operator
    std::string fullName1 = firstName + " " + lastName;
    
    // Method 2: Using append()
    std::string fullName2 = firstName;
    fullName2.append(" ").append(lastName);
    
    // Method 3: Using += operator
    std::string fullName3 = firstName;
    fullName3 += " " + lastName;
    
    std::cout << "Full name: " << fullName1 << std::endl;
    
    return 0;
}
```

## String Properties and Methods

### Length and Size
```cpp
#include <iostream>
#include <string>

int main() {
    std::string text = "Hello, World!";
    
    std::cout << "String: " << text << std::endl;
    std::cout << "Length: " << text.length() << std::endl;
    std::cout << "Size: " << text.size() << std::endl;
    std::cout << "Empty: " << (text.empty() ? "Yes" : "No") << std::endl;
    
    return 0;
}
```

### Character Access
```cpp
#include <iostream>
#include <string>

int main() {
    std::string text = "Hello";
    
    // Access individual characters
    std::cout << "First character: " << text[0] << std::endl;
    std::cout << "Last character: " << text[text.length() - 1] << std::endl;
    
    // Using at() method (bounds checking)
    try {
        std::cout << "Character at index 2: " << text.at(2) << std::endl;
        std::cout << "Character at index 10: " << text.at(10) << std::endl;
    } catch (const std::out_of_range& e) {
        std::cout << "Index out of range!" << std::endl;
    }
    
    return 0;
}
```

## String Searching and Manipulation

### Finding Substrings
```cpp
#include <iostream>
#include <string>

int main() {
    std::string text = "The quick brown fox jumps over the lazy dog";
    std::string search = "fox";
    
    // Find first occurrence
    size_t pos = text.find(search);
    if (pos != std::string::npos) {
        std::cout << "Found '" << search << "' at position: " << pos << std::endl;
    } else {
        std::cout << "Substring not found!" << std::endl;
    }
    
    // Find last occurrence
    pos = text.rfind("the");
    if (pos != std::string::npos) {
        std::cout << "Last 'the' at position: " << pos << std::endl;
    }
    
    return 0;
}
```

### String Replacement
```cpp
#include <iostream>
#include <string>

int main() {
    std::string text = "Hello World!";
    
    // Replace substring
    text.replace(6, 5, "C++");  // Replace "World" with "C++"
    std::cout << "After replacement: " << text << std::endl;
    
    // Replace all occurrences
    std::string sentence = "I love programming. Programming is fun.";
    size_t pos = 0;
    while ((pos = sentence.find("Programming", pos)) != std::string::npos) {
        sentence.replace(pos, 11, "Coding");
        pos += 6; // Move past the replacement
    }
    std::cout << "After replacing all: " << sentence << std::endl;
    
    return 0;
}
```

## Character Manipulation

### Character Classification
```cpp
#include <iostream>
#include <string>
#include <cctype>

int main() {
    std::string text = "Hello123!";
    
    for (char c : text) {
        std::cout << "Character: " << c;
        std::cout << " - isalpha: " << (std::isalpha(c) ? "Yes" : "No");
        std::cout << " - isdigit: " << (std::isdigit(c) ? "Yes" : "No");
        std::cout << " - isalnum: " << (std::isalnum(c) ? "Yes" : "No");
        std::cout << " - isspace: " << (std::isspace(c) ? "Yes" : "No");
        std::cout << std::endl;
    }
    
    return 0;
}
```

### Case Conversion
```cpp
#include <iostream>
#include <string>
#include <cctype>
#include <algorithm>

int main() {
    std::string text = "Hello World!";
    
    // Convert to uppercase
    std::string upper = text;
    std::transform(upper.begin(), upper.end(), upper.begin(), ::toupper);
    std::cout << "Uppercase: " << upper << std::endl;
    
    // Convert to lowercase
    std::string lower = text;
    std::transform(lower.begin(), lower.end(), lower.begin(), ::tolower);
    std::cout << "Lowercase: " << lower << std::endl;
    
    return 0;
}
```

## String Algorithms

### Vowel Counting
```cpp
#include <iostream>
#include <string>
#include <cctype>

int countVowels(const std::string& str) {
    int count = 0;
    for (char c : str) {
        char lower = std::tolower(static_cast<unsigned char>(c));
        if (lower == 'a' || lower == 'e' || lower == 'i' || 
            lower == 'o' || lower == 'u') {
            count++;
        }
    }
    return count;
}

int main() {
    std::string word;
    std::cout << "Enter a word: ";
    std::cin >> word;
    
    int vowelCount = countVowels(word);
    std::cout << "Number of vowels: " << vowelCount << std::endl;
    
    return 0;
}
```

### Palindrome Check
```cpp
#include <iostream>
#include <string>
#include <algorithm>
#include <cctype>

bool isPalindrome(const std::string& str) {
    std::string cleaned;
    
    // Remove non-alphanumeric characters and convert to lowercase
    for (char c : str) {
        if (std::isalnum(c)) {
            cleaned += std::tolower(static_cast<unsigned char>(c));
        }
    }
    
    // Check if palindrome
    int left = 0;
    int right = cleaned.length() - 1;
    
    while (left < right) {
        if (cleaned[left] != cleaned[right]) {
            return false;
        }
        left++;
        right--;
    }
    
    return true;
}

int main() {
    std::string text;
    std::cout << "Enter a string: ";
    std::getline(std::cin, text);
    
    if (isPalindrome(text)) {
        std::cout << "The string is a palindrome!" << std::endl;
    } else {
        std::cout << "The string is not a palindrome." << std::endl;
    }
    
    return 0;
}
```

## String Tokenization

### Splitting Strings
```cpp
#include <iostream>
#include <string>
#include <vector>
#include <sstream>

std::vector<std::string> splitString(const std::string& str, char delimiter) {
    std::vector<std::string> tokens;
    std::stringstream ss(str);
    std::string token;
    
    while (std::getline(ss, token, delimiter)) {
        tokens.push_back(token);
    }
    
    return tokens;
}

int main() {
    std::string text = "apple,banana,cherry,date";
    std::vector<std::string> fruits = splitString(text, ',');
    
    std::cout << "Fruits:" << std::endl;
    for (const std::string& fruit : fruits) {
        std::cout << "- " << fruit << std::endl;
    }
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o strings main.cpp
./strings

# Windows (PowerShell)
g++ -std=c++17 -O2 -o strings.exe main.cpp
./strings.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o strings.exe main.cpp
strings.exe
```

### Character Encoding
- Different platforms may handle character encoding differently
- Use `unsigned char` when calling `std::tolower()` to avoid undefined behavior
- Consider using `std::wstring` for wide character support if needed

## Common Pitfalls

1. **Buffer overflow**: C-style strings can cause buffer overflows if not handled carefully
2. **Character casting**: Always cast to `unsigned char` when using character functions
3. **String comparison**: Use `==` for C++ strings, `strcmp()` for C-style strings
4. **Memory management**: C++ strings handle memory automatically, C-style strings don't

## Best Practices

1. Prefer `std::string` over C-style strings for better safety and functionality
2. Use `const std::string&` for function parameters to avoid unnecessary copying
3. Use `std::getline()` for reading full lines of text
4. Always check bounds when accessing string characters
5. Use meaningful variable names for string operations
6. Handle empty strings and edge cases appropriately

## Performance Considerations

### String Concatenation
- Use `+=` operator for single concatenations
- Use `std::ostringstream` for multiple concatenations
- Reserve space with `reserve()` if you know the final size

### Memory Efficiency
```cpp
#include <iostream>
#include <string>

int main() {
    std::string result;
    result.reserve(1000); // Reserve space to avoid reallocations
    
    for (int i = 0; i < 100; i++) {
        result += "word" + std::to_string(i) + " ";
    }
    
    std::cout << result << std::endl;
    return 0;
}
```

## Exercises

1. Create a program that counts the number of words in a sentence
2. Implement a function that reverses a string
3. Write a program that removes all vowels from a string
4. Create a function that finds the longest word in a sentence
5. Implement a simple text encryption/decryption program using character shifting
