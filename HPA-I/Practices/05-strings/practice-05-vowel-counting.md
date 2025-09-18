# Practice 05: String Manipulation and Vowel Counting

## Objective
Learn string manipulation in C++ by creating a program that counts vowels in a word, handling case-insensitive input and proper character processing.

## Problem Statement
Count vowels in a word (case-insensitive).

### Requirements
- Input: One word s
- Output: Number of vowels in s
- Constraints: Consider a, e, i, o, u only

### Example
```
Input: Programming
Output: 3
```

## Solution

### Complete Code
```cpp
#include <iostream>
#include <string>
#include <cctype>

int countVowels(const std::string& word) {
    int count = 0;
    
    for (char c : word) {
        // Convert to lowercase and cast to unsigned char
        char lower = std::tolower(static_cast<unsigned char>(c));
        
        // Check if character is a vowel
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

### Code Explanation
1. **Include Headers**: `iostream` for I/O, `string` for strings, `cctype` for character functions
2. **Function Definition**: `countVowels` takes a const reference to string
3. **Character Processing**: Convert each character to lowercase using `std::tolower`
4. **Vowel Check**: Compare with vowel characters
5. **Return Count**: Return total number of vowels found

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o vowel_count main.cpp
./vowel_count
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o vowel_count.exe main.cpp
./vowel_count.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o vowel_count.exe main.cpp
vowel_count.exe
```

## Key Concepts

### String Processing
- `std::string`: C++ string class for text manipulation
- Range-based for loop: `for (char c : word)`
- Character iteration through string

### Character Functions
- `std::tolower()`: Convert character to lowercase
- `static_cast<unsigned char>()`: Safe casting for character functions
- Character comparison with single quotes

### Case-Insensitive Processing
- Convert all characters to lowercase before comparison
- Handle both uppercase and lowercase input
- Use `unsigned char` casting to avoid undefined behavior

## Common Pitfalls

1. **Signed Char Issue**: `std::tolower()` with signed char is undefined behavior
2. **Missing Cast**: Always cast to `unsigned char` before using character functions
3. **Case Sensitivity**: Remember to convert to lowercase for case-insensitive comparison
4. **Empty String**: Handle empty input gracefully

## Variations

### Enhanced Version with Input Validation
```cpp
#include <iostream>
#include <string>
#include <cctype>

int countVowels(const std::string& word) {
    int count = 0;
    
    for (char c : word) {
        char lower = std::tolower(static_cast<unsigned char>(c));
        
        if (lower == 'a' || lower == 'e' || lower == 'i' || 
            lower == 'o' || lower == 'u') {
            count++;
        }
    }
    
    return count;
}

bool isValidWord(const std::string& word) {
    if (word.empty()) {
        return false;
    }
    
    for (char c : word) {
        if (!std::isalpha(static_cast<unsigned char>(c))) {
            return false;
        }
    }
    
    return true;
}

int main() {
    std::string word;
    
    std::cout << "Enter a word: ";
    std::cin >> word;
    
    if (isValidWord(word)) {
        int vowelCount = countVowels(word);
        std::cout << "Number of vowels: " << vowelCount << std::endl;
    } else {
        std::cout << "Error: Invalid input! Please enter a valid word." << std::endl;
        return 1;
    }
    
    return 0;
}
```

### Version with Detailed Analysis
```cpp
#include <iostream>
#include <string>
#include <cctype>

struct VowelAnalysis {
    int totalVowels;
    int aCount, eCount, iCount, oCount, uCount;
    
    VowelAnalysis() : totalVowels(0), aCount(0), eCount(0), iCount(0), oCount(0), uCount(0) {}
};

VowelAnalysis analyzeVowels(const std::string& word) {
    VowelAnalysis analysis;
    
    for (char c : word) {
        char lower = std::tolower(static_cast<unsigned char>(c));
        
        switch (lower) {
            case 'a':
                analysis.aCount++;
                analysis.totalVowels++;
                break;
            case 'e':
                analysis.eCount++;
                analysis.totalVowels++;
                break;
            case 'i':
                analysis.iCount++;
                analysis.totalVowels++;
                break;
            case 'o':
                analysis.oCount++;
                analysis.totalVowels++;
                break;
            case 'u':
                analysis.uCount++;
                analysis.totalVowels++;
                break;
        }
    }
    
    return analysis;
}

int main() {
    std::string word;
    
    std::cout << "Enter a word: ";
    std::cin >> word;
    
    VowelAnalysis analysis = analyzeVowels(word);
    
    std::cout << "Vowel Analysis for '" << word << "':" << std::endl;
    std::cout << "Total vowels: " << analysis.totalVowels << std::endl;
    std::cout << "A: " << analysis.aCount << std::endl;
    std::cout << "E: " << analysis.eCount << std::endl;
    std::cout << "I: " << analysis.iCount << std::endl;
    std::cout << "O: " << analysis.oCount << std::endl;
    std::cout << "U: " << analysis.uCount << std::endl;
    
    return 0;
}
```

### Version with Multiple Words
```cpp
#include <iostream>
#include <string>
#include <cctype>
#include <vector>

int countVowels(const std::string& word) {
    int count = 0;
    
    for (char c : word) {
        char lower = std::tolower(static_cast<unsigned char>(c));
        
        if (lower == 'a' || lower == 'e' || lower == 'i' || 
            lower == 'o' || lower == 'u') {
            count++;
        }
    }
    
    return count;
}

int main() {
    std::string input;
    
    std::cout << "Enter words (separated by spaces): ";
    std::cin.ignore(); // Clear input buffer
    std::getline(std::cin, input);
    
    std::vector<std::string> words;
    std::string word;
    
    // Split input into words
    for (char c : input) {
        if (c == ' ') {
            if (!word.empty()) {
                words.push_back(word);
                word.clear();
            }
        } else {
            word += c;
        }
    }
    
    if (!word.empty()) {
        words.push_back(word);
    }
    
    // Count vowels in each word
    for (const std::string& w : words) {
        int vowelCount = countVowels(w);
        std::cout << "Word: '" << w << "' - Vowels: " << vowelCount << std::endl;
    }
    
    return 0;
}
```

## Advanced Features

### Class-Based Approach
```cpp
#include <iostream>
#include <string>
#include <cctype>

class VowelCounter {
private:
    std::string word;
    
public:
    VowelCounter(const std::string& w) : word(w) {}
    
    int countVowels() const {
        int count = 0;
        
        for (char c : word) {
            char lower = std::tolower(static_cast<unsigned char>(c));
            
            if (lower == 'a' || lower == 'e' || lower == 'i' || 
                lower == 'o' || lower == 'u') {
                count++;
            }
        }
        
        return count;
    }
    
    int countConsonants() const {
        int count = 0;
        
        for (char c : word) {
            char lower = std::tolower(static_cast<unsigned char>(c));
            
            if (std::isalpha(static_cast<unsigned char>(c)) && 
                lower != 'a' && lower != 'e' && lower != 'i' && 
                lower != 'o' && lower != 'u') {
                count++;
            }
        }
        
        return count;
    }
    
    void displayAnalysis() const {
        std::cout << "Analysis for '" << word << "':" << std::endl;
        std::cout << "Vowels: " << countVowels() << std::endl;
        std::cout << "Consonants: " << countConsonants() << std::endl;
        std::cout << "Total letters: " << word.length() << std::endl;
    }
};

int main() {
    std::string word;
    
    std::cout << "Enter a word: ";
    std::cin >> word;
    
    VowelCounter counter(word);
    counter.displayAnalysis();
    
    return 0;
}
```

### Template Function for Different String Types
```cpp
#include <iostream>
#include <string>
#include <cctype>

template<typename StringType>
int countVowels(const StringType& word) {
    int count = 0;
    
    for (char c : word) {
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

## Exercises

1. Create a program that counts consonants in a word
2. Implement a function that finds the most frequent vowel in a word
3. Write a program that counts vowels in a sentence
4. Create a function that removes all vowels from a string
5. Implement a program that checks if a word has more vowels than consonants

## Learning Outcomes
- Understanding string manipulation in C++
- Learning character processing functions
- Working with case-insensitive string operations
- Understanding the importance of proper character casting
- Cross-platform compilation
