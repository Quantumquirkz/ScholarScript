# 📚 C++ Quick Reference Sheet

## 🔧 Basic Syntax

### 📝 Program Structure
```cpp
#include <iostream>
using namespace std;

int main() {
    // Your code here
    return 0;
}
```

### 📊 Data Types
```cpp
// Integer types
int number = 42;           // 4 bytes, -2^31 to 2^31-1
short small = 123;         // 2 bytes
long big = 1234567890L;    // 4+ bytes
long long huge = 1234567890123456789LL; // 8 bytes

// Floating point types
float decimal = 3.14f;     // 4 bytes, 7 digits precision
double precise = 3.14159265359; // 8 bytes, 15 digits precision

// Character types
char letter = 'A';         // 1 byte
wchar_t wide = L'Ω';       // 2+ bytes

// Boolean type
bool flag = true;          // true or false

// String type
string text = "Hello";     // Requires #include <string>
```

### ⚡ Operators

#### Arithmetic Operators
```cpp
int a = 10, b = 3;
int sum = a + b;      // 13
int diff = a - b;     // 7
int product = a * b;  // 30
int quotient = a / b; // 3
int remainder = a % b; // 1
int increment = ++a;  // 11 (pre-increment)
int decrement = --b;  // 2 (pre-decrement)
```

#### Assignment Operators
```cpp
int x = 5;
x += 3;   // x = x + 3
x -= 2;   // x = x - 2
x *= 4;   // x = x * 4
x /= 2;   // x = x / 2
x %= 3;   // x = x % 3
```

#### Comparison Operators
```cpp
bool result;
result = (a == b);  // Equal to
result = (a != b);  // Not equal to
result = (a < b);   // Less than
result = (a > b);   // Greater than
result = (a <= b);  // Less than or equal
result = (a >= b);  // Greater than or equal
```

#### Logical Operators
```cpp
bool p = true, q = false;
bool and_result = p && q;  // Logical AND
bool or_result = p || q;   // Logical OR
bool not_result = !p;      // Logical NOT
```

### 🔄 Control Structures

#### If Statements
```cpp
if (condition) {
    // Code if condition is true
}

if (condition) {
    // Code if condition is true
} else {
    // Code if condition is false
}

if (condition1) {
    // Code for condition1
} else if (condition2) {
    // Code for condition2
} else {
    // Default code
}
```

#### Switch Statement
```cpp
switch (expression) {
    case value1:
        // Code for value1
        break;
    case value2:
        // Code for value2
        break;
    default:
        // Default code
        break;
}
```

#### Loops
```cpp
// For loop
for (int i = 0; i < 10; i++) {
    // Loop body
}

// While loop
while (condition) {
    // Loop body
}

// Do-while loop
do {
    // Loop body
} while (condition);

// Break and continue
for (int i = 0; i < 10; i++) {
    if (i == 5) break;     // Exit loop
    if (i == 3) continue;  // Skip iteration
}
```

### 📊 Arrays

#### One-Dimensional Arrays
```cpp
// Declaration
int numbers[5] = {1, 2, 3, 4, 5};
int scores[10] = {0};  // Initialize all to 0

// Access
int first = numbers[0];
int last = numbers[4];

// Size
int size = sizeof(numbers) / sizeof(numbers[0]);
```

#### Multi-Dimensional Arrays
```cpp
// 2D array
int matrix[3][4] = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

// Access
int element = matrix[1][2];  // 7
```

### 🔧 Functions

#### Function Declaration
```cpp
// Function prototype
returnType functionName(parameterType parameterName);

// Function definition
returnType functionName(parameterType parameterName) {
    // Function body
    return value;
}
```

#### Function Examples
```cpp
// Function with no parameters
void printHello() {
    cout << "Hello, World!" << endl;
}

// Function with parameters
int add(int a, int b) {
    return a + b;
}

// Function with default parameters
void printNumber(int num = 0) {
    cout << "Number: " << num << endl;
}

// Function overloading
int add(int a, int b) {
    return a + b;
}

double add(double a, double b) {
    return a + b;
}
```

### 📝 Input/Output

#### Console I/O
```cpp
#include <iostream>
using namespace std;

// Output
cout << "Hello, World!" << endl;
cout << "Number: " << 42 << endl;

// Input
int number;
cin >> number;

string name;
getline(cin, name);  // Read entire line
```

#### Formatting
```cpp
#include <iomanip>

cout << fixed << setprecision(2);  // Fixed decimal places
cout << setw(10) << "Hello";       // Set field width
cout << left << "Left aligned";    // Left alignment
cout << right << "Right aligned";  // Right alignment
```

### 📚 String Operations

#### String Methods
```cpp
#include <string>
using namespace std;

string str = "Hello World";
int length = str.length();           // 11
string sub = str.substr(0, 5);      // "Hello"
int pos = str.find("World");         // 6
str.replace(6, 5, "C++");           // "Hello C++"
str.erase(5, 1);                    // "HelloWorld"
str.insert(5, " ");                 // "Hello World"
```

#### String Concatenation
```cpp
string str1 = "Hello";
string str2 = "World";
string result = str1 + " " + str2;  // "Hello World"
```

### 🔢 Mathematical Functions

#### Common Math Functions
```cpp
#include <cmath>

double x = 3.14;
double y = 2.0;

double sqrt_val = sqrt(x);      // Square root
double pow_val = pow(x, y);     // Power
double abs_val = abs(-5);       // Absolute value
double floor_val = floor(x);    // Floor
double ceil_val = ceil(x);      // Ceiling
double round_val = round(x);    // Round
double sin_val = sin(x);        // Sine
double cos_val = cos(x);        // Cosine
double tan_val = tan(x);        // Tangent
double log_val = log(x);        // Natural logarithm
double log10_val = log10(x);    // Base-10 logarithm
```

### 🛠️ Common Patterns

#### Array Traversal
```cpp
int arr[] = {1, 2, 3, 4, 5};
int size = sizeof(arr) / sizeof(arr[0]);

// Forward traversal
for (int i = 0; i < size; i++) {
    cout << arr[i] << " ";
}

// Backward traversal
for (int i = size - 1; i >= 0; i--) {
    cout << arr[i] << " ";
}
```

#### Finding Maximum/Minimum
```cpp
int arr[] = {5, 2, 8, 1, 9};
int size = sizeof(arr) / sizeof(arr[0]);

int max = arr[0];
int min = arr[0];

for (int i = 1; i < size; i++) {
    if (arr[i] > max) max = arr[i];
    if (arr[i] < min) min = arr[i];
}
```

#### Array Sum and Average
```cpp
int arr[] = {1, 2, 3, 4, 5};
int size = sizeof(arr) / sizeof(arr[0]);

int sum = 0;
for (int i = 0; i < size; i++) {
    sum += arr[i];
}

double average = static_cast<double>(sum) / size;
```

### 🔍 Common Errors and Solutions

#### Common Compilation Errors
```cpp
// Error: Missing semicolon
int x = 5  // Missing semicolon
int x = 5; // Correct

// Error: Undeclared variable
cout << y; // y not declared
int y = 10; // Declare first

// Error: Array out of bounds
int arr[5];
arr[5] = 10; // Index 5 is out of bounds (0-4 only)

// Error: Division by zero
int result = 10 / 0; // Undefined behavior
if (divisor != 0) {
    result = dividend / divisor;
}
```

#### Best Practices
```cpp
// Always initialize variables
int x = 0;  // Good
int y;      // Bad (uninitialized)

// Use meaningful variable names
int studentCount = 0;  // Good
int sc = 0;           // Bad

// Add comments for complex logic
// Calculate GPA based on weighted average
double gpa = (grade1 * credit1 + grade2 * credit2) / totalCredits;

// Validate input
int number;
cin >> number;
if (cin.fail()) {
    cout << "Invalid input!" << endl;
    cin.clear();
    cin.ignore(1000, '\n');
}
```

---

**Last Updated**: December 2024  
**Version**: 1.0 