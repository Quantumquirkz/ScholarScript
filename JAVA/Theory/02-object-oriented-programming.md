# Object-Oriented Programming in Java

## Overview
Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects", which contain data (attributes) and code (methods). Java is a pure object-oriented language that supports all four pillars of OOP: Encapsulation, Inheritance, Polymorphism, and Abstraction.

## Key Concepts

### What is Object-Oriented Programming?
OOP is a programming paradigm that:
- Organizes software design around data (objects) rather than functions and logic
- Emphasizes reusability, maintainability, and scalability
- Provides a clear structure for programs
- Makes it easier to debug, modify, and extend code

### The Four Pillars of OOP

#### 1. Encapsulation
Encapsulation is the bundling of data and methods that operate on that data into a single unit (class) and restricting direct access to some of the object's components.

```java
public class BankAccount {
    // Private fields (data hiding)
    private String accountNumber;
    private double balance;
    private String accountHolder;
    
    // Public constructor
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    
    // Public methods (controlled access)
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ", New balance: $" + balance);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount");
            return false;
        }
    }
    
    public String getAccountInfo() {
        return "Account: " + accountNumber + ", Holder: " + accountHolder + 
               ", Balance: $" + balance;
    }
    
    // Private helper method
    private boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 1000000; // Maximum transaction limit
    }
}

// Usage example
public class EncapsulationDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", "John Doe", 1000.0);
        
        System.out.println(account.getAccountInfo());
        
        account.deposit(500.0);
        account.withdraw(200.0);
        
        // account.balance = -1000; // This would cause a compilation error
        System.out.println("Current balance: $" + account.getBalance());
    }
}
```

#### 2. Inheritance
Inheritance allows a class to inherit properties and methods from another class, promoting code reusability and establishing an "is-a" relationship.

```java
// Base class (Parent/Super class)
public class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected double speed;
    
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.speed = 0;
    }
    
    public void start() {
        System.out.println("Starting the " + brand + " " + model);
    }
    
    public void stop() {
        speed = 0;
        System.out.println("Stopping the " + brand + " " + model);
    }
    
    public void accelerate(double increment) {
        speed += increment;
        System.out.println("Speed increased to: " + speed + " mph");
    }
    
    public void brake(double decrement) {
        speed = Math.max(0, speed - decrement);
        System.out.println("Speed decreased to: " + speed + " mph");
    }
    
    public String getInfo() {
        return year + " " + brand + " " + model + " (Speed: " + speed + " mph)";
    }
}

// Derived class (Child/Sub class)
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
        System.out.println("Turning the key to start the " + brand + " " + model);
        super.start(); // Call parent method
    }
    
    // Additional methods specific to Car
    public void openTrunk() {
        System.out.println("Opening trunk of the " + brand + " " + model);
    }
    
    public void refuel() {
        System.out.println("Refueling with " + fuelType);
    }
    
    @Override
    public String getInfo() {
        return super.getInfo() + " (" + numberOfDoors + " doors, " + fuelType + ")";
    }
}

// Another derived class
public class Motorcycle extends Vehicle {
    private boolean hasWindshield;
    private int engineCC;
    
    public Motorcycle(String brand, String model, int year, boolean hasWindshield, int engineCC) {
        super(brand, model, year);
        this.hasWindshield = hasWindshield;
        this.engineCC = engineCC;
    }
    
    @Override
    public void start() {
        System.out.println("Kicking the starter of the " + brand + " " + model);
        super.start();
    }
    
    public void wheelie() {
        System.out.println("Performing a wheelie on the " + brand + " " + model);
    }
    
    @Override
    public String getInfo() {
        return super.getInfo() + " (" + engineCC + "cc, Windshield: " + hasWindshield + ")";
    }
}

// Demo class
public class InheritanceDemo {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Camry", 2023, 4, "Gasoline");
        Motorcycle motorcycle = new Motorcycle("Honda", "CBR600", 2023, true, 600);
        
        System.out.println("=== Car Operations ===");
        System.out.println(car.getInfo());
        car.start();
        car.accelerate(30);
        car.openTrunk();
        car.refuel();
        
        System.out.println("\n=== Motorcycle Operations ===");
        System.out.println(motorcycle.getInfo());
        motorcycle.start();
        motorcycle.accelerate(50);
        motorcycle.wheelie();
    }
}
```

#### 3. Polymorphism
Polymorphism allows objects of different types to be treated as objects of a common base type, enabling "one interface, multiple implementations".

```java
// Abstract base class
abstract class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Abstract method (must be implemented by subclasses)
    public abstract void makeSound();
    
    // Concrete method (can be inherited as-is or overridden)
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    // Method that can be overridden (virtual method)
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Concrete implementations
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
    
    public void fetch() {
        System.out.println(name + " is fetching the ball");
    }
}

class Cat extends Animal {
    private boolean isIndoor;
    
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow! Meow!");
    }
    
    public void purr() {
        System.out.println(name + " is purring");
    }
}

class Bird extends Animal {
    private String species;
    private boolean canFly;
    
    public Bird(String name, int age, String species, boolean canFly) {
        super(name, age);
        this.species = species;
        this.canFly = canFly;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " chirps: Tweet! Tweet!");
    }
    
    public void fly() {
        if (canFly) {
            System.out.println(name + " is flying high");
        } else {
            System.out.println(name + " cannot fly");
        }
    }
}

// Polymorphism demonstration
public class PolymorphismDemo {
    public static void main(String[] args) {
        // Polymorphic array - treating different objects as Animal type
        Animal[] animals = {
            new Dog("Buddy", 3, "Golden Retriever"),
            new Cat("Whiskers", 2, true),
            new Bird("Tweety", 1, "Canary", true),
            new Dog("Max", 5, "German Shepherd")
        };
        
        System.out.println("=== Polymorphism Demo ===");
        
        // Same method call, different behaviors
        for (Animal animal : animals) {
            System.out.println("\n--- " + animal.getClass().getSimpleName() + " ---");
            animal.displayInfo();
            animal.makeSound(); // Polymorphic method call
            animal.eat();
            animal.sleep();
        }
        
        // Method overloading example
        System.out.println("\n=== Method Overloading ===");
        Calculator calc = new Calculator();
        
        System.out.println("Add integers: " + calc.add(5, 3));
        System.out.println("Add doubles: " + calc.add(5.5, 3.2));
        System.out.println("Add three integers: " + calc.add(5, 3, 2));
        System.out.println("Add strings: " + calc.add("Hello", " World"));
    }
}

// Method overloading example
class Calculator {
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
```

#### 4. Abstraction
Abstraction hides complex implementation details and shows only the essential features of an object.

```java
// Abstract class example
abstract class Shape {
    protected String color;
    protected boolean filled;
    
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
    
    // Abstract methods (must be implemented by subclasses)
    public abstract double getArea();
    public abstract double getPerimeter();
    
    // Concrete methods
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public boolean isFilled() {
        return filled;
    }
    
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    @Override
    public String toString() {
        return "Shape[color=" + color + ", filled=" + filled + "]";
    }
}

// Interface example
interface Drawable {
    void draw();
    void erase();
}

interface Movable {
    void move(int dx, int dy);
}

// Concrete implementations
class Circle extends Shape implements Drawable, Movable {
    private double radius;
    private int x, y;
    
    public Circle(double radius, String color, boolean filled, int x, int y) {
        super(color, filled);
        this.radius = radius;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius + " at (" + x + ", " + y + ")");
    }
    
    @Override
    public void erase() {
        System.out.println("Erasing the circle");
    }
    
    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        System.out.println("Circle moved to (" + x + ", " + y + ")");
    }
    
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
}

class Rectangle extends Shape implements Drawable, Movable {
    private double width, height;
    private int x, y;
    
    public Rectangle(double width, double height, String color, boolean filled, int x, int y) {
        super(color, filled);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public double getArea() {
        return width * height;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle " + width + "x" + height + " at (" + x + ", " + y + ")");
    }
    
    @Override
    public void erase() {
        System.out.println("Erasing the rectangle");
    }
    
    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        System.out.println("Rectangle moved to (" + x + ", " + y + ")");
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
}

// Abstraction demo
public class AbstractionDemo {
    public static void main(String[] args) {
        // Cannot instantiate abstract class
        // Shape shape = new Shape("red", true); // Compilation error
        
        Circle circle = new Circle(5.0, "blue", true, 10, 20);
        Rectangle rectangle = new Rectangle(8.0, 6.0, "red", true, 30, 40);
        
        System.out.println("=== Circle ===");
        System.out.println("Area: " + circle.getArea());
        System.out.println("Perimeter: " + circle.getPerimeter());
        circle.draw();
        circle.move(5, 10);
        circle.erase();
        
        System.out.println("\n=== Rectangle ===");
        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Perimeter: " + rectangle.getPerimeter());
        rectangle.draw();
        rectangle.move(-5, 15);
        rectangle.erase();
        
        // Polymorphism with abstract class
        System.out.println("\n=== Polymorphism with Abstract Class ===");
        Shape[] shapes = {circle, rectangle};
        
        for (Shape shape : shapes) {
            System.out.println("Shape: " + shape.getClass().getSimpleName());
            System.out.println("Area: " + shape.getArea());
            System.out.println("Perimeter: " + shape.getPerimeter());
            System.out.println();
        }
        
        // Polymorphism with interfaces
        System.out.println("=== Polymorphism with Interfaces ===");
        Drawable[] drawables = {circle, rectangle};
        
        for (Drawable drawable : drawables) {
            drawable.draw();
            drawable.erase();
        }
    }
}
```

## Advanced OOP Concepts

### Static Members
```java
public class Student {
    // Instance variables
    private String name;
    private int studentId;
    
    // Static variables (class variables)
    private static int totalStudents = 0;
    private static String schoolName = "Java University";
    
    // Constructor
    public Student(String name) {
        this.name = name;
        this.studentId = ++totalStudents; // Increment and assign
    }
    
    // Instance methods
    public String getName() {
        return name;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    // Static methods
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    public static String getSchoolName() {
        return schoolName;
    }
    
    public static void setSchoolName(String newName) {
        schoolName = newName;
    }
    
    // Static block (executed when class is first loaded)
    static {
        System.out.println("Student class loaded. Welcome to " + schoolName + "!");
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', id=" + studentId + "}";
    }
}

// Static demo
public class StaticDemo {
    public static void main(String[] args) {
        System.out.println("School: " + Student.getSchoolName());
        System.out.println("Total students: " + Student.getTotalStudents());
        
        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");
        Student student3 = new Student("Charlie");
        
        System.out.println("\nStudents created:");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
        
        System.out.println("\nTotal students: " + Student.getTotalStudents());
        
        // Change school name
        Student.setSchoolName("Advanced Java University");
        System.out.println("New school name: " + Student.getSchoolName());
    }
}
```

### Final Keyword
```java
// Final class (cannot be extended)
final class ImmutablePoint {
    private final int x;
    private final int y;
    
    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Final methods (cannot be overridden)
    public final int getX() {
        return x;
    }
    
    public final int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}

class Parent {
    // Final method
    public final void cannotOverride() {
        System.out.println("This method cannot be overridden");
    }
    
    // Regular method
    public void canOverride() {
        System.out.println("This method can be overridden");
    }
}

class Child extends Parent {
    // This would cause compilation error:
    // public void cannotOverride() { }
    
    @Override
    public void canOverride() {
        System.out.println("This method has been overridden");
    }
}

// Final demo
public class FinalDemo {
    // Final variable (constant)
    private static final double PI = 3.14159;
    private final String constantValue;
    
    public FinalDemo(String value) {
        this.constantValue = value; // Can be assigned only once
        // this.constantValue = "new value"; // Would cause compilation error
    }
    
    public static void main(String[] args) {
        System.out.println("PI = " + PI);
        
        FinalDemo demo = new FinalDemo("Initial Value");
        
        // ImmutablePoint example
        ImmutablePoint point = new ImmutablePoint(10, 20);
        System.out.println("Point: " + point);
        
        // Final method example
        Parent parent = new Parent();
        Child child = new Child();
        
        parent.canOverride();
        parent.cannotOverride();
        
        child.canOverride();
        child.cannotOverride();
    }
}
```

### Access Modifiers
```java
package com.example.access;

// Public class (accessible from anywhere)
public class AccessModifiersDemo {
    // Public - accessible from anywhere
    public String publicField = "Public";
    
    // Protected - accessible within package and subclasses
    protected String protectedField = "Protected";
    
    // Package-private (default) - accessible within same package
    String packagePrivateField = "Package Private";
    
    // Private - accessible only within this class
    private String privateField = "Private";
    
    // Public method
    public void publicMethod() {
        System.out.println("This is a public method");
        privateMethod(); // Can access private method from within class
    }
    
    // Protected method
    protected void protectedMethod() {
        System.out.println("This is a protected method");
    }
    
    // Package-private method
    void packagePrivateMethod() {
        System.out.println("This is a package-private method");
    }
    
    // Private method
    private void privateMethod() {
        System.out.println("This is a private method");
    }
}

// Subclass in the same package
class Subclass extends AccessModifiersDemo {
    public void testAccess() {
        // Can access public, protected, and package-private
        System.out.println(publicField);
        System.out.println(protectedField);
        System.out.println(packagePrivateField);
        // System.out.println(privateField); // Compilation error
        
        publicMethod();
        protectedMethod();
        packagePrivateMethod();
        // privateMethod(); // Compilation error
    }
}

// Class in different package
package com.example.other;

import com.example.access.AccessModifiersDemo;

class OtherPackageClass {
    public void testAccess() {
        AccessModifiersDemo demo = new AccessModifiersDemo();
        
        // Can only access public members
        System.out.println(demo.publicField);
        demo.publicMethod();
        
        // Cannot access protected, package-private, or private
        // System.out.println(demo.protectedField); // Compilation error
        // demo.protectedMethod(); // Compilation error
    }
}
```

## Design Patterns

### Singleton Pattern
```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;
    
    // Private constructor
    private DatabaseConnection() {
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
    }
    
    // Public static method to get instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Connecting to: " + connectionString);
    }
    
    public void disconnect() {
        System.out.println("Disconnected from database");
    }
}

// Singleton demo
public class SingletonDemo {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        System.out.println("Same instance? " + (db1 == db2));
        
        db1.connect();
        db2.disconnect();
    }
}
```

### Factory Pattern
```java
// Product interface
interface Shape {
    void draw();
}

// Concrete products
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle");
    }
}

// Factory class
class ShapeFactory {
    public Shape createShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        
        switch (shapeType.toLowerCase()) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            case "triangle":
                return new Triangle();
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
}

// Factory demo
public class FactoryDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        
        Shape circle = factory.createShape("circle");
        Shape rectangle = factory.createShape("rectangle");
        Shape triangle = factory.createShape("triangle");
        
        circle.draw();
        rectangle.draw();
        triangle.draw();
    }
}
```

## Best Practices

### Class Design
```java
// Good class design example
public class BankAccount {
    // Use meaningful names
    private final String accountNumber;
    private final String accountHolder;
    private double balance;
    private final LocalDate createdAt;
    
    // Constructor validation
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder cannot be null or empty");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.createdAt = LocalDate.now();
    }
    
    // Immutable fields - only getters, no setters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    // Business logic methods
    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        logTransaction("DEPOSIT", amount);
    }
    
    public boolean withdraw(double amount) {
        validateAmount(amount);
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        logTransaction("WITHDRAWAL", amount);
        return true;
    }
    
    // Private helper method
    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    
    private void logTransaction(String type, double amount) {
        System.out.println(type + ": $" + amount + " on " + LocalDate.now());
    }
    
    @Override
    public String toString() {
        return String.format("Account[%s, %s, $%.2f, created: %s]", 
                           accountNumber, accountHolder, balance, createdAt);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount that = (BankAccount) obj;
        return Objects.equals(accountNumber, that.accountNumber);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
```

## Learning Outcomes
- Understanding the four pillars of OOP: Encapsulation, Inheritance, Polymorphism, and Abstraction
- Learning how to design and implement classes with proper encapsulation
- Understanding inheritance hierarchies and method overriding
- Learning polymorphism and method overloading
- Understanding abstract classes and interfaces
- Learning advanced OOP concepts like static members, final keyword, and access modifiers
- Understanding common design patterns in OOP
- Best practices for object-oriented design in Java
