# Practice 06: Simple Classes and Encapsulation

## Objective
Learn object-oriented programming in C++ by creating a simple Rectangle class with constructor, methods, and encapsulation principles.

## Problem Statement
Create a class Rectangle with constructor and area method.

### Requirements
- Input: None; instantiate with sample values
- Output: Area as a floating-point value
- Constraints: Provide const-correct area() method

### Example
```
Input: (3,4)
Output: 12
```

## Solution

### Complete Code
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Constructor
    Rectangle(double w, double h) : width(w), height(h) {}
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Setter methods
    void setWidth(double w) { width = w; }
    void setHeight(double h) { height = h; }
    
    // Area calculation
    double area() const {
        return width * height;
    }
    
    // Perimeter calculation
    double perimeter() const {
        return 2 * (width + height);
    }
    
    // Display method
    void display() const {
        std::cout << "Rectangle: width=" << width 
                  << ", height=" << height 
                  << ", area=" << area() << std::endl;
    }
};

int main() {
    // Create rectangle with sample values
    Rectangle rect(3.0, 4.0);
    
    // Display rectangle information
    rect.display();
    
    // Calculate and display area
    std::cout << "Area: " << rect.area() << std::endl;
    
    return 0;
}
```

### Code Explanation
1. **Class Definition**: `class Rectangle` with private and public sections
2. **Private Members**: `width` and `height` for encapsulation
3. **Constructor**: Initialize width and height with member initializer list
4. **Getter Methods**: Access private members safely
5. **Setter Methods**: Modify private members with validation
6. **Const Methods**: `area()` and `perimeter()` don't modify object state
7. **Display Method**: Show rectangle information

## Cross-Platform Compilation

### Linux/macOS
```bash
g++ -std=c++17 -O2 -o rectangle main.cpp
./rectangle
```

### Windows (PowerShell)
```powershell
g++ -std=c++17 -O2 -o rectangle.exe main.cpp
./rectangle.exe
```

### Windows (Command Prompt)
```cmd
g++ -std=c++17 -O2 -o rectangle.exe main.cpp
rectangle.exe
```

## Key Concepts

### Encapsulation
- **Private Members**: Data members that cannot be accessed directly
- **Public Methods**: Interface for accessing and modifying data
- **Data Hiding**: Protect internal implementation from external access

### Constructor
- **Member Initializer List**: `: width(w), height(h)` initializes members
- **Parameterized Constructor**: Takes parameters to initialize object
- **Default Constructor**: Can be provided for default initialization

### Const Correctness
- **Const Methods**: Methods that don't modify object state
- **Const Objects**: Objects that cannot be modified
- **Const Parameters**: Parameters that cannot be modified

## Common Pitfalls

1. **Missing Const**: Methods that don't modify state should be const
2. **Direct Access**: Don't access private members directly from outside class
3. **Uninitialized Members**: Always initialize all member variables
4. **Missing Semicolon**: Class definition must end with semicolon

## Variations

### Enhanced Version with Input Validation
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Constructor with validation
    Rectangle(double w, double h) {
        setWidth(w);
        setHeight(h);
    }
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Setter methods with validation
    void setWidth(double w) {
        if (w > 0) {
            width = w;
        } else {
            std::cout << "Error: Width must be positive!" << std::endl;
            width = 1.0; // Default value
        }
    }
    
    void setHeight(double h) {
        if (h > 0) {
            height = h;
        } else {
            std::cout << "Error: Height must be positive!" << std::endl;
            height = 1.0; // Default value
        }
    }
    
    // Area calculation
    double area() const {
        return width * height;
    }
    
    // Perimeter calculation
    double perimeter() const {
        return 2 * (width + height);
    }
    
    // Display method
    void display() const {
        std::cout << "Rectangle: width=" << width 
                  << ", height=" << height 
                  << ", area=" << area() 
                  << ", perimeter=" << perimeter() << std::endl;
    }
};

int main() {
    // Test with valid values
    Rectangle rect1(3.0, 4.0);
    rect1.display();
    
    // Test with invalid values
    Rectangle rect2(-2.0, 5.0);
    rect2.display();
    
    return 0;
}
```

### Version with Multiple Constructors
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Default constructor
    Rectangle() : width(1.0), height(1.0) {}
    
    // Parameterized constructor
    Rectangle(double w, double h) : width(w), height(h) {}
    
    // Copy constructor
    Rectangle(const Rectangle& other) : width(other.width), height(other.height) {}
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Setter methods
    void setWidth(double w) { width = w; }
    void setHeight(double h) { height = h; }
    
    // Area calculation
    double area() const {
        return width * height;
    }
    
    // Perimeter calculation
    double perimeter() const {
        return 2 * (width + height);
    }
    
    // Display method
    void display() const {
        std::cout << "Rectangle: width=" << width 
                  << ", height=" << height 
                  << ", area=" << area() << std::endl;
    }
};

int main() {
    // Test different constructors
    Rectangle rect1;           // Default constructor
    Rectangle rect2(3.0, 4.0); // Parameterized constructor
    Rectangle rect3(rect2);    // Copy constructor
    
    rect1.display();
    rect2.display();
    rect3.display();
    
    return 0;
}
```

### Version with Static Methods
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Constructor
    Rectangle(double w, double h) : width(w), height(h) {}
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Setter methods
    void setWidth(double w) { width = w; }
    void setHeight(double h) { height = h; }
    
    // Instance methods
    double area() const {
        return width * height;
    }
    
    double perimeter() const {
        return 2 * (width + height);
    }
    
    // Static methods
    static double calculateArea(double w, double h) {
        return w * h;
    }
    
    static double calculatePerimeter(double w, double h) {
        return 2 * (w + h);
    }
    
    static Rectangle createSquare(double side) {
        return Rectangle(side, side);
    }
    
    // Display method
    void display() const {
        std::cout << "Rectangle: width=" << width 
                  << ", height=" << height 
                  << ", area=" << area() << std::endl;
    }
};

int main() {
    // Test instance methods
    Rectangle rect(3.0, 4.0);
    rect.display();
    
    // Test static methods
    double area = Rectangle::calculateArea(5.0, 6.0);
    std::cout << "Area calculated statically: " << area << std::endl;
    
    // Create square using static method
    Rectangle square = Rectangle::createSquare(5.0);
    square.display();
    
    return 0;
}
```

## Advanced Features

### Operator Overloading
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Constructor
    Rectangle(double w, double h) : width(w), height(h) {}
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Area calculation
    double area() const {
        return width * height;
    }
    
    // Operator overloading
    bool operator==(const Rectangle& other) const {
        return (width == other.width) && (height == other.height);
    }
    
    bool operator>(const Rectangle& other) const {
        return area() > other.area();
    }
    
    Rectangle operator+(const Rectangle& other) const {
        return Rectangle(width + other.width, height + other.height);
    }
    
    // Display method
    void display() const {
        std::cout << "Rectangle: width=" << width 
                  << ", height=" << height 
                  << ", area=" << area() << std::endl;
    }
};

int main() {
    Rectangle rect1(3.0, 4.0);
    Rectangle rect2(2.0, 5.0);
    
    rect1.display();
    rect2.display();
    
    // Test operators
    if (rect1 == rect2) {
        std::cout << "Rectangles are equal" << std::endl;
    } else {
        std::cout << "Rectangles are not equal" << std::endl;
    }
    
    if (rect1 > rect2) {
        std::cout << "Rectangle 1 is larger" << std::endl;
    } else {
        std::cout << "Rectangle 2 is larger" << std::endl;
    }
    
    Rectangle rect3 = rect1 + rect2;
    std::cout << "Sum of rectangles: ";
    rect3.display();
    
    return 0;
}
```

### Friend Functions
```cpp
#include <iostream>

class Rectangle {
private:
    double width;
    double height;
    
public:
    // Constructor
    Rectangle(double w, double h) : width(w), height(h) {}
    
    // Getter methods
    double getWidth() const { return width; }
    double getHeight() const { return height; }
    
    // Area calculation
    double area() const {
        return width * height;
    }
    
    // Friend function
    friend double calculateDiagonal(const Rectangle& rect);
    
    // Display method
    void display() const {
        std::cout << "Rectangle: width=" << width 
                  << ", height=" << height 
                  << ", area=" << area() << std::endl;
    }
};

// Friend function implementation
double calculateDiagonal(const Rectangle& rect) {
    return std::sqrt(rect.width * rect.width + rect.height * rect.height);
}

int main() {
    Rectangle rect(3.0, 4.0);
    rect.display();
    
    double diagonal = calculateDiagonal(rect);
    std::cout << "Diagonal: " << diagonal << std::endl;
    
    return 0;
}
```

## Exercises

1. Create a Circle class with radius and area calculation
2. Implement a Book class with title, author, and page count
3. Write a BankAccount class with balance and transaction methods
4. Create a Student class with name, age, and grade management
5. Implement a Time class with hours, minutes, and seconds

## Learning Outcomes
- Understanding object-oriented programming concepts
- Learning encapsulation and data hiding
- Working with constructors and member functions
- Understanding const correctness
- Cross-platform compilation
