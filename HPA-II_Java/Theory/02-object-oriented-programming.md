# Object-Oriented Programming in Java

## Overview
Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects" which contain data (attributes) and code (methods). Java is inherently object-oriented, and this module covers the four main OOP principles: encapsulation, inheritance, polymorphism, and abstraction.

## Key Concepts

### Classes and Objects
A class is a blueprint for creating objects, while an object is an instance of a class.

```java
// Class definition
public class Car {
    // Attributes (fields)
    private String brand;
    private String model;
    private int year;
    private double price;
    
    // Constructor
    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }
    
    // Methods
    public void start() {
        System.out.println("The " + brand + " " + model + " is starting...");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: $" + price);
    }
    
    // Getter methods
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    
    // Setter methods
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setPrice(double price) { this.price = price; }
}

// Main class to demonstrate usage
public class CarDemo {
    public static void main(String[] args) {
        // Creating objects
        Car car1 = new Car("Toyota", "Camry", 2023, 25000.0);
        Car car2 = new Car("Honda", "Civic", 2022, 22000.0);
        
        // Using objects
        car1.displayInfo();
        car1.start();
        
        car2.displayInfo();
        car2.start();
    }
}
```

## Encapsulation

Encapsulation is the bundling of data and methods that operate on that data within a single unit (class), and restricting direct access to some of the object's components.

```java
public class BankAccount {
    // Private fields (data hiding)
    private String accountNumber;
    private double balance;
    private String ownerName;
    
    // Constructor
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }
    
    // Public methods to access private data (controlled access)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount!");
            return false;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    // Private helper method
    private boolean isValidAmount(double amount) {
        return amount > 0;
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456", "John Doe", 1000.0);
        
        System.out.println("Account Owner: " + account.getOwnerName());
        System.out.println("Initial Balance: $" + account.getBalance());
        
        account.deposit(500.0);
        account.withdraw(200.0);
        account.withdraw(1500.0); // Should fail
        
        // Direct access to balance is not allowed
        // account.balance = 10000; // Compilation error
    }
}
```

## Inheritance

Inheritance allows a class to inherit properties and methods from another class. It promotes code reusability and establishes an "is-a" relationship.

```java
// Parent class (superclass)
public class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    public void start() {
        System.out.println("The vehicle is starting...");
    }
    
    public void stop() {
        System.out.println("The vehicle is stopping...");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year);
    }
}

// Child class (subclass)
public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    
    public Car(String brand, String model, int year, int numberOfDoors, String fuelType) {
        super(brand, model, year); // Call parent constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
    }
    
    // Method overriding
    @Override
    public void start() {
        System.out.println("The car engine is starting...");
    }
    
    public void openTrunk() {
        System.out.println("Opening the trunk...");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Doors: " + numberOfDoors + ", Fuel: " + fuelType);
    }
}

public class Motorcycle extends Vehicle {
    private boolean hasWindshield;
    
    public Motorcycle(String brand, String model, int year, boolean hasWindshield) {
        super(brand, model, year);
        this.hasWindshield = hasWindshield;
    }
    
    @Override
    public void start() {
        System.out.println("The motorcycle engine is starting...");
    }
    
    public void wheelie() {
        System.out.println("Doing a wheelie!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Has Windshield: " + hasWindshield);
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Camry", 2023, 4, "Gasoline");
        Motorcycle motorcycle = new Motorcycle("Harley", "Sportster", 2022, true);
        
        car.displayInfo();
        car.start();
        car.openTrunk();
        
        System.out.println();
        
        motorcycle.displayInfo();
        motorcycle.start();
        motorcycle.wheelie();
    }
}
```

## Polymorphism

Polymorphism allows objects of different types to be treated as objects of a common super type. It enables one interface to be used for a general class of actions.

```java
// Abstract base class
public abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    // Abstract method (must be implemented by subclasses)
    public abstract void makeSound();
    
    // Concrete method
    public void eat() {
        System.out.println(name + " is eating...");
    }
    
    public String getName() {
        return name;
    }
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof!");
    }
    
    public void fetch() {
        System.out.println(name + " is fetching the ball!");
    }
}

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }
    
    public void climb() {
        System.out.println(name + " is climbing the tree!");
    }
}

public class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Tweet!");
    }
    
    public void fly() {
        System.out.println(name + " is flying!");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        // Polymorphism: treating different objects as Animal type
        Animal[] animals = {
            new Dog("Buddy"),
            new Cat("Whiskers"),
            new Bird("Tweety"),
            new Dog("Max")
        };
        
        // Polymorphic method calls
        for (Animal animal : animals) {
            animal.makeSound(); // Different implementation for each type
            animal.eat();       // Same implementation for all
            System.out.println();
        }
        
        // Type checking and casting
        Animal animal = new Dog("Rex");
        animal.makeSound();
        
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal; // Downcasting
            dog.fetch();
        }
    }
}
```

## Abstraction

Abstraction hides complex implementation details and shows only the essential features of an object.

```java
// Interface (complete abstraction)
public interface Drawable {
    void draw();
    double getArea();
}

// Abstract class (partial abstraction)
public abstract class Shape implements Drawable {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract method (must be implemented)
    public abstract double calculateArea();
    
    // Concrete method
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
    
    @Override
    public double getArea() {
        return calculateArea();
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius);
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    public double getRadius() {
        return radius;
    }
}

public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle " + width + "x" + height);
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle("Red", 5.0),
            new Rectangle("Blue", 4.0, 6.0),
            new Circle("Green", 3.0)
        };
        
        for (Shape shape : shapes) {
            shape.draw();
            System.out.println("Area: " + shape.getArea());
            System.out.println();
        }
        
        // Interface usage
        Drawable drawable = new Circle("Yellow", 2.0);
        drawable.draw();
        System.out.println("Area: " + drawable.getArea());
    }
}
```

## Constructors

### Constructor Types
```java
public class Student {
    private String name;
    private int age;
    private String major;
    
    // Default constructor
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.major = "Undeclared";
    }
    
    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.major = "Undeclared";
    }
    
    // Full parameterized constructor
    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    // Copy constructor
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
        this.major = other.major;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", major='" + major + "'}";
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student("Alice", 20);
        Student student3 = new Student("Bob", 22, "Computer Science");
        Student student4 = new Student(student3); // Copy constructor
        
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
        System.out.println(student4);
    }
}
```

## Method Overloading and Overriding

### Method Overloading
```java
public class Calculator {
    // Method overloading: same method name, different parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
}

public class OverloadingDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        System.out.println("Int addition: " + calc.add(5, 3));
        System.out.println("Double addition: " + calc.add(5.5, 3.2));
        System.out.println("Three numbers: " + calc.add(1, 2, 3));
        System.out.println("String concatenation: " + calc.add("Hello", "World"));
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

### Package Structure
```java
// File: com/example/vehicles/Car.java
package com.example.vehicles;

public class Car {
    // Class implementation
}

// File: com/example/vehicles/VehicleDemo.java
package com.example.vehicles;

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car();
    }
}
```

Compilation:
```bash
# Create package structure
mkdir -p com/example/vehicles

# Compile
javac com/example/vehicles/*.java

# Run
java com.example.vehicles.VehicleDemo
```

## Common Pitfalls

1. **Access Modifiers**: Understand public, private, protected, and package-private
2. **Constructor Chaining**: Use `super()` and `this()` correctly
3. **Method Overriding**: Use `@Override` annotation and maintain signature
4. **Object References**: Understand reference vs value semantics
5. **Memory Management**: Be aware of object lifecycle and garbage collection

## Best Practices

1. **Encapsulation**: Make fields private and provide public getters/setters
2. **Inheritance**: Use inheritance for "is-a" relationships
3. **Composition**: Prefer composition over inheritance when appropriate
4. **Interfaces**: Use interfaces for multiple inheritance and contracts
5. **Naming**: Follow Java naming conventions consistently

## Exercises

1. Create a `Person` class with encapsulation and inheritance
2. Implement a `Shape` hierarchy with polymorphism
3. Design a `Library` system with books and users
4. Create an `Employee` management system with different employee types
5. Build a `Vehicle` rental system with various vehicle types

## Learning Outcomes
- Understanding OOP principles in Java
- Implementing encapsulation, inheritance, polymorphism, and abstraction
- Working with classes, objects, and methods
- Understanding constructor types and method overloading/overriding
- Cross-platform Java development
