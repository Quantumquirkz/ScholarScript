# Classes and Objects in C++

## Overview
Classes are user-defined data types that encapsulate data and functions together. This module covers class definition, object creation, constructors, destructors, and encapsulation principles.

## Basic Class Definition

### Simple Class
```cpp
#include <iostream>
#include <string>

class Person {
private:
    std::string name;
    int age;
    
public:
    // Constructor
    Person(const std::string& n, int a) : name(n), age(a) {}
    
    // Getter methods
    std::string getName() const { return name; }
    int getAge() const { return age; }
    
    // Setter methods
    void setName(const std::string& n) { name = n; }
    void setAge(int a) { age = a; }
    
    // Other methods
    void display() const {
        std::cout << "Name: " << name << ", Age: " << age << std::endl;
    }
};

int main() {
    Person person("Alice", 25);
    person.display();
    
    person.setAge(26);
    std::cout << "Updated age: " << person.getAge() << std::endl;
    
    return 0;
}
```

## Constructors and Destructors

### Different Types of Constructors
```cpp
#include <iostream>
#include <string>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Default constructor
    Rectangle() : width(0), height(0) {
        std::cout << "Default constructor called" << std::endl;
    }
    
    // Parameterized constructor
    Rectangle(double w, double h) : width(w), height(h) {
        std::cout << "Parameterized constructor called" << std::endl;
    }
    
    // Copy constructor
    Rectangle(const Rectangle& other) : width(other.width), height(other.height) {
        std::cout << "Copy constructor called" << std::endl;
    }
    
    // Destructor
    ~Rectangle() {
        std::cout << "Destructor called" << std::endl;
    }
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Setter methods
    void setWidth(double w) { width = w; }
    void setHeight(double h) { height = h; }
    
    // Calculate area
    double area() const {
        return width * height;
    }
    
    // Calculate perimeter
    double perimeter() const {
        return 2 * (width + height);
    }
};

int main() {
    Rectangle rect1;           // Default constructor
    Rectangle rect2(5.0, 3.0); // Parameterized constructor
    Rectangle rect3(rect2);    // Copy constructor
    
    std::cout << "Area of rect2: " << rect2.area() << std::endl;
    std::cout << "Perimeter of rect2: " << rect2.perimeter() << std::endl;
    
    return 0;
}
```

## Encapsulation

### Private, Protected, and Public Members
```cpp
#include <iostream>
#include <string>

class BankAccount {
private:
    std::string accountNumber;
    double balance;
    
protected:
    std::string bankName;
    
public:
    // Constructor
    BankAccount(const std::string& accNum, double initialBalance, const std::string& bank)
        : accountNumber(accNum), balance(initialBalance), bankName(bank) {}
    
    // Public methods
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            std::cout << "Deposited $" << amount << std::endl;
        } else {
            std::cout << "Invalid deposit amount!" << std::endl;
        }
    }
    
    bool withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            std::cout << "Withdrew $" << amount << std::endl;
            return true;
        } else {
            std::cout << "Insufficient funds or invalid amount!" << std::endl;
            return false;
        }
    }
    
    double getBalance() const {
        return balance;
    }
    
    std::string getAccountNumber() const {
        return accountNumber;
    }
    
    void displayInfo() const {
        std::cout << "Account: " << accountNumber 
                  << ", Bank: " << bankName 
                  << ", Balance: $" << balance << std::endl;
    }
};

int main() {
    BankAccount account("123456789", 1000.0, "MyBank");
    
    account.displayInfo();
    account.deposit(500.0);
    account.withdraw(200.0);
    account.displayInfo();
    
    return 0;
}
```

## Static Members

### Static Variables and Methods
```cpp
#include <iostream>
#include <string>

class Student {
private:
    std::string name;
    int studentId;
    static int totalStudents;  // Static member variable
    
public:
    // Constructor
    Student(const std::string& n) : name(n) {
        studentId = ++totalStudents;
    }
    
    // Static method
    static int getTotalStudents() {
        return totalStudents;
    }
    
    // Instance method
    void displayInfo() const {
        std::cout << "Student ID: " << studentId 
                  << ", Name: " << name << std::endl;
    }
};

// Initialize static member
int Student::totalStudents = 0;

int main() {
    std::cout << "Total students: " << Student::getTotalStudents() << std::endl;
    
    Student s1("Alice");
    Student s2("Bob");
    Student s3("Charlie");
    
    std::cout << "Total students: " << Student::getTotalStudents() << std::endl;
    
    s1.displayInfo();
    s2.displayInfo();
    s3.displayInfo();
    
    return 0;
}
```

## Operator Overloading

### Basic Operator Overloading
```cpp
#include <iostream>

class Complex {
private:
    double real;
    double imag;
    
public:
    // Constructor
    Complex(double r = 0, double i = 0) : real(r), imag(i) {}
    
    // Overload + operator
    Complex operator+(const Complex& other) const {
        return Complex(real + other.real, imag + other.imag);
    }
    
    // Overload - operator
    Complex operator-(const Complex& other) const {
        return Complex(real - other.real, imag - other.imag);
    }
    
    // Overload == operator
    bool operator==(const Complex& other) const {
        return (real == other.real) && (imag == other.imag);
    }
    
    // Overload << operator for output
    friend std::ostream& operator<<(std::ostream& os, const Complex& c) {
        os << c.real << " + " << c.imag << "i";
        return os;
    }
    
    // Getter methods
    double getReal() const { return real; }
    double getImag() const { return imag; }
};

int main() {
    Complex c1(3, 4);
    Complex c2(1, 2);
    
    Complex sum = c1 + c2;
    Complex diff = c1 - c2;
    
    std::cout << "c1: " << c1 << std::endl;
    std::cout << "c2: " << c2 << std::endl;
    std::cout << "Sum: " << sum << std::endl;
    std::cout << "Difference: " << diff << std::endl;
    
    if (c1 == c2) {
        std::cout << "c1 and c2 are equal" << std::endl;
    } else {
        std::cout << "c1 and c2 are not equal" << std::endl;
    }
    
    return 0;
}
```

## Friend Functions

### Friend Functions and Classes
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    Rectangle(double w, double h) : width(w), height(h) {}
    
    // Friend function
    friend double calculateArea(const Rectangle& rect);
    
    // Friend class
    friend class RectangleHelper;
    
    void display() const {
        std::cout << "Width: " << width << ", Height: " << height << std::endl;
    }
};

// Friend function
double calculateArea(const Rectangle& rect) {
    return rect.width * rect.height;  // Can access private members
}

// Friend class
class RectangleHelper {
public:
    static void printDetails(const Rectangle& rect) {
        std::cout << "Rectangle details - Width: " << rect.width 
                  << ", Height: " << rect.height 
                  << ", Area: " << calculateArea(rect) << std::endl;
    }
};

int main() {
    Rectangle rect(5.0, 3.0);
    
    rect.display();
    std::cout << "Area: " << calculateArea(rect) << std::endl;
    RectangleHelper::printDetails(rect);
    
    return 0;
}
```

## Cross-Platform Considerations

### Compilation Commands
```bash
# Linux/macOS
g++ -std=c++17 -O2 -o classes main.cpp
./classes

# Windows (PowerShell)
g++ -std=c++17 -O2 -o classes.exe main.cpp
./classes.exe

# Windows (Command Prompt)
g++ -std=c++17 -O2 -o classes.exe main.cpp
classes.exe
```

### Platform-Specific Considerations
- Object layout may vary between platforms
- Virtual function table (vtable) implementation may differ
- Memory alignment requirements may vary

## Common Pitfalls

1. **Missing const correctness**: Use `const` for methods that don't modify the object
2. **Shallow vs deep copy**: Be careful with copy constructors and assignment operators
3. **Memory leaks**: Always pair `new` with `delete` and `new[]` with `delete[]`
4. **Initialization order**: Member variables are initialized in declaration order, not initialization list order

## Best Practices

1. Use meaningful class and method names
2. Follow the principle of encapsulation (private data, public interface)
3. Use const correctness throughout
4. Initialize all member variables in constructors
5. Use initializer lists for member initialization
6. Implement the rule of three/five when needed
7. Use `explicit` keyword for single-parameter constructors to prevent implicit conversions

## Memory Management

### RAII (Resource Acquisition Is Initialization)
```cpp
#include <iostream>
#include <memory>

class Resource {
private:
    int* data;
    size_t size;
    
public:
    // Constructor
    Resource(size_t s) : size(s) {
        data = new int[size];
        std::cout << "Resource allocated" << std::endl;
    }
    
    // Destructor
    ~Resource() {
        delete[] data;
        std::cout << "Resource deallocated" << std::endl;
    }
    
    // Copy constructor (deep copy)
    Resource(const Resource& other) : size(other.size) {
        data = new int[size];
        for (size_t i = 0; i < size; i++) {
            data[i] = other.data[i];
        }
    }
    
    // Assignment operator
    Resource& operator=(const Resource& other) {
        if (this != &other) {
            delete[] data;
            size = other.size;
            data = new int[size];
            for (size_t i = 0; i < size; i++) {
                data[i] = other.data[i];
            }
        }
        return *this;
    }
    
    void setValue(size_t index, int value) {
        if (index < size) {
            data[index] = value;
        }
    }
    
    int getValue(size_t index) const {
        if (index < size) {
            return data[index];
        }
        return 0;
    }
};

int main() {
    {
        Resource res(5);
        res.setValue(0, 10);
        res.setValue(1, 20);
        
        std::cout << "Value at index 0: " << res.getValue(0) << std::endl;
    } // Resource automatically deallocated here
    
    return 0;
}
```

## Exercises

1. Create a `Circle` class with radius, area, and circumference calculations
2. Implement a `Book` class with title, author, and page count
3. Create a `Stack` class using arrays with push, pop, and peek operations
4. Implement a `Time` class with hours, minutes, and seconds
5. Create a `Matrix` class with basic matrix operations (addition, multiplication)
