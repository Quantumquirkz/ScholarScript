# Text Processing Tool Project

## Project Overview
A comprehensive text processing tool that provides various text manipulation functions including word counting, text analysis, search and replace operations, and text formatting utilities.

## Project Specifications

### Core Features
- Word and character counting
- Text search and replace
- Case conversion (upper, lower, title case)
- Text formatting and alignment
- Text statistics and analysis
- File I/O operations
- Regular expression support
- Text encryption/decryption

### Technical Requirements
- **Language**: C++
- **Standard**: C++17 or higher
- **Dependencies**: Standard library only
- **Platforms**: Linux, Windows, macOS
- **File Support**: Plain text files

## Project Structure
```
text_processor/
├── src/
│   ├── main.cpp
│   ├── text_processor.h
│   ├── text_processor.cpp
│   ├── file_handler.h
│   ├── file_handler.cpp
│   ├── text_analyzer.h
│   ├── text_analyzer.cpp
│   ├── text_formatter.h
│   ├── text_formatter.cpp
│   └── menu.h
├── input/
│   └── sample.txt
├── output/
├── tests/
│   └── test_text_processor.cpp
├── build/
├── CMakeLists.txt
└── README.md
```

## Detailed Specifications

### 1. Text Processor Class
```cpp
class TextProcessor {
private:
    std::string text;
    std::string filename;
    
public:
    // Constructor
    TextProcessor();
    TextProcessor(const std::string& file);
    
    // File operations
    bool loadFromFile(const std::string& filename);
    bool saveToFile(const std::string& filename);
    void setText(const std::string& text);
    std::string getText() const;
    
    // Basic operations
    void append(const std::string& text);
    void prepend(const std::string& text);
    void insert(int position, const std::string& text);
    void remove(int start, int length);
    
    // Search and replace
    int find(const std::string& pattern);
    std::vector<int> findAll(const std::string& pattern);
    int replace(const std::string& oldText, const std::string& newText);
    int replaceAll(const std::string& oldText, const std::string& newText);
    
    // Case conversion
    void toUpperCase();
    void toLowerCase();
    void toTitleCase();
    void toSentenceCase();
    
    // Text manipulation
    void reverse();
    void sortLines();
    void removeEmptyLines();
    void removeDuplicateLines();
    void trimWhitespace();
};
```

### 2. Text Analyzer Class
```cpp
class TextAnalyzer {
private:
    std::string text;
    
public:
    TextAnalyzer(const std::string& text);
    
    // Basic statistics
    int getCharacterCount() const;
    int getWordCount() const;
    int getLineCount() const;
    int getParagraphCount() const;
    
    // Character analysis
    int getVowelCount() const;
    int getConsonantCount() const;
    int getDigitCount() const;
    int getPunctuationCount() const;
    int getSpaceCount() const;
    
    // Word analysis
    std::vector<std::string> getWords() const;
    std::map<std::string, int> getWordFrequency() const;
    std::string getMostFrequentWord() const;
    std::string getLongestWord() const;
    std::string getShortestWord() const;
    
    // Text analysis
    double getAverageWordLength() const;
    double getAverageSentenceLength() const;
    int getSentenceCount() const;
    
    // Report generation
    void generateReport() const;
    void generateWordFrequencyReport() const;
};
```

### 3. Text Formatter Class
```cpp
class TextFormatter {
private:
    std::string text;
    
public:
    TextFormatter(const std::string& text);
    
    // Alignment
    std::string leftAlign(int width) const;
    std::string rightAlign(int width) const;
    std::string centerAlign(int width) const;
    std::string justify(int width) const;
    
    // Wrapping
    std::string wrapText(int lineLength) const;
    std::string wrapWords(int lineLength) const;
    
    // Indentation
    std::string indent(int spaces) const;
    std::string indentLines(int spaces) const;
    std::string unindent() const;
    
    // Formatting
    std::string formatAsList() const;
    std::string formatAsTable(const std::vector<std::string>& headers) const;
    std::string addLineNumbers() const;
    
    // Cleanup
    std::string removeExtraSpaces() const;
    std::string normalizeLineEndings() const;
    std::string removeTrailingSpaces() const;
};
```

### 4. File Handler Class
```cpp
class FileHandler {
public:
    static bool fileExists(const std::string& filename);
    static std::string readFile(const std::string& filename);
    static bool writeFile(const std::string& filename, const std::string& content);
    static bool appendToFile(const std::string& filename, const std::string& content);
    static std::vector<std::string> readLines(const std::string& filename);
    static bool writeLines(const std::string& filename, const std::vector<std::string>& lines);
    static long getFileSize(const std::string& filename);
    static std::string getFileExtension(const std::string& filename);
};
```

## User Interface Design

### Main Menu
```
=== Text Processing Tool ===
1. Load Text from File
2. Enter Text Manually
3. Text Analysis
4. Search and Replace
5. Case Conversion
6. Text Formatting
7. Text Statistics
8. Save Text to File
9. Exit

Enter your choice (1-9):
```

### Text Analysis Menu
```
=== Text Analysis ===
1. Basic Statistics
2. Word Frequency Analysis
3. Character Analysis
4. Generate Full Report
5. Back to Main Menu

Enter your choice (1-5):
```

### Search and Replace Menu
```
=== Search and Replace ===
1. Find Text
2. Find All Occurrences
3. Replace First Occurrence
4. Replace All Occurrences
5. Case-Sensitive Search
6. Back to Main Menu

Enter your choice (1-6):
```

## Implementation Requirements

### Text Processing Functions
- Support for large text files (up to 100MB)
- Efficient string operations
- Memory management for large texts
- Unicode support (basic)

### Search and Replace
- Case-sensitive and case-insensitive search
- Whole word matching
- Regular expression support (basic)
- Highlight search results

### File Operations
- Support for various text encodings
- Error handling for file operations
- Backup file creation
- Progress indication for large files

## Cross-Platform Considerations

### File Path Handling
```cpp
#ifdef _WIN32
    const char PATH_SEPARATOR = '\\';
    const std::string LINE_ENDING = "\r\n";
#else
    const char PATH_SEPARATOR = '/';
    const std::string LINE_ENDING = "\n";
#endif
```

### Compilation
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o text_processor src/*.cpp

# Windows (MinGW)
g++ -std=c++17 -O2 -o text_processor.exe src/*.cpp

# Windows (Visual Studio)
cl /std:c++17 /O2 src/*.cpp /Fe:text_processor.exe
```

### Platform-Specific Features
- Handle different line ending conventions
- Use appropriate path separators
- Ensure consistent text encoding

## Advanced Features

### Text Encryption
```cpp
class TextEncryptor {
public:
    static std::string caesarCipher(const std::string& text, int shift);
    static std::string reverseCipher(const std::string& text);
    static std::string wordReverseCipher(const std::string& text);
    static std::string decrypt(const std::string& encryptedText, int shift);
};
```

### Regular Expression Support
```cpp
class RegexProcessor {
public:
    static std::vector<std::string> findMatches(const std::string& text, 
                                               const std::string& pattern);
    static std::string replaceMatches(const std::string& text, 
                                    const std::string& pattern, 
                                    const std::string& replacement);
    static bool isValidPattern(const std::string& pattern);
};
```

## Testing Requirements

### Unit Tests
- Test all text processing functions
- Test file I/O operations
- Test search and replace functionality
- Test text analysis accuracy
- Test edge cases (empty text, special characters)

### Test Data
- Various text samples (short, long, multilingual)
- Special characters and symbols
- Different line ending formats
- Large text files for performance testing

## Performance Requirements
- Process text files up to 100MB
- Search operations complete in < 2 seconds
- Text analysis complete in < 5 seconds
- Memory usage < 200MB for maximum file size

## Output Formats

### Text Statistics Report
```
=== Text Statistics Report ===
File: sample.txt
Characters: 1,234
Words: 234
Lines: 45
Paragraphs: 12
Sentences: 67

Character Analysis:
- Vowels: 456 (37.0%)
- Consonants: 678 (55.0%)
- Digits: 23 (1.9%)
- Punctuation: 77 (6.2%)
- Spaces: 234 (19.0%)

Word Analysis:
- Average word length: 5.3 characters
- Longest word: "characteristics" (15 characters)
- Shortest word: "a" (1 character)
- Most frequent word: "the" (23 occurrences)
```

### Word Frequency Report
```
=== Word Frequency Report ===
Rank  Word        Count  Percentage
1     the         23     9.8%
2     and         18     7.7%
3     of          15     6.4%
4     to          12     5.1%
5     a           11     4.7%
...
```

## Extension Ideas
- GUI interface
- Batch processing
- Text comparison tool
- Spell checker integration
- Language detection
- Text summarization
- Export to different formats (PDF, HTML)
- Plugin system for custom processors

## Deliverables
1. Complete source code
2. Executable for each platform
3. Sample text files
4. Unit tests
5. User documentation
6. Technical documentation
7. Build scripts

## Success Criteria
- All text processing functions work correctly
- File I/O operations handle errors gracefully
- Search and replace functions are accurate
- Text analysis provides meaningful statistics
- Code compiles and runs on all target platforms
- Unit tests pass with 100% success rate
- Performance requirements are met
