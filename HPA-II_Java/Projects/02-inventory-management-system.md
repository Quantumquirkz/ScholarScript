# Inventory Management System Project

## Project Overview
A comprehensive inventory management system that tracks products, manages stock levels, processes orders, and generates reports. The system demonstrates advanced Java concepts including collections, generics, file I/O, and design patterns.

## Project Specifications

### Core Features
- Product catalog management (add, update, delete products)
- Inventory tracking with real-time stock levels
- Order processing and fulfillment
- Supplier management
- Low stock alerts and notifications
- Sales reporting and analytics
- Data import/export functionality
- Search and filtering capabilities

### Technical Requirements
- **Language**: Java 11 or higher
- **Dependencies**: Standard Java libraries only
- **Platforms**: Linux, Windows, macOS
- **Data Storage**: JSON or XML files
- **Build Tool**: Maven or Gradle
- **Testing Framework**: JUnit 5

## Project Structure
```
inventory-management/
├── src/main/java/
│   ├── inventory/
│   │   ├── model/
│   │   │   ├── Product.java
│   │   │   ├── Category.java
│   │   │   ├── Supplier.java
│   │   │   ├── Order.java
│   │   │   ├── OrderItem.java
│   │   │   └── Inventory.java
│   │   ├── service/
│   │   │   ├── ProductService.java
│   │   │   ├── InventoryService.java
│   │   │   ├── OrderService.java
│   │   │   └── ReportService.java
│   │   ├── dao/
│   │   │   ├── ProductDAO.java
│   │   │   ├── OrderDAO.java
│   │   │   └── DataAccessObject.java
│   │   ├── exception/
│   │   │   ├── InventoryException.java
│   │   │   ├── ProductNotFoundException.java
│   │   │   └── InsufficientStockException.java
│   │   ├── util/
│   │   │   ├── FileManager.java
│   │   │   ├── JsonUtils.java
│   │   │   └── DateUtils.java
│   │   └── InventoryApplication.java
├── src/main/resources/
│   ├── products.json
│   ├── orders.json
│   ├── suppliers.json
│   └── config.properties
├── src/test/java/
│   └── inventory/
├── pom.xml
└── README.md
```

## Detailed Specifications

### 1. Model Classes

#### Product Class
```java
package inventory.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private String productId;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    private int stockQuantity;
    private int minimumStockLevel;
    private String supplierId;
    private LocalDate dateAdded;
    private LocalDate lastUpdated;
    
    public Product(String productId, String name, Category category, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = 0;
        this.minimumStockLevel = 10;
        this.dateAdded = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }
    
    public boolean isLowStock() {
        return stockQuantity <= minimumStockLevel;
    }
    
    public boolean isOutOfStock() {
        return stockQuantity <= 0;
    }
    
    public void updateStock(int quantity) {
        if (this.stockQuantity + quantity < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stockQuantity += quantity;
        this.lastUpdated = LocalDate.now();
    }
    
    public BigDecimal getTotalValue() {
        return price.multiply(BigDecimal.valueOf(stockQuantity));
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
    
    // Getters and setters
}
```

#### Category Class
```java
package inventory.model;

import java.util.Objects;

public class Category {
    private String categoryId;
    private String name;
    private String description;
    
    public Category(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    // Getters and setters
}
```

#### Supplier Class
```java
package inventory.model;

import java.util.Objects;

public class Supplier {
    private String supplierId;
    private String name;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean isActive;
    
    public Supplier(String supplierId, String name, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.email = email;
        this.isActive = true;
    }
    
    public void deactivate() {
        this.isActive = false;
    }
    
    public void activate() {
        this.isActive = true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierId, supplier.supplierId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(supplierId);
    }
    
    // Getters and setters
}
```

#### Order Class
```java
package inventory.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private String customerName;
    private String customerEmail;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    
    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }
    
    public Order(String customerName, String customerEmail) {
        this.orderId = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.items = new ArrayList<>();
        this.totalAmount = BigDecimal.ZERO;
    }
    
    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        calculateTotalAmount();
    }
    
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
        calculateTotalAmount();
    }
    
    public void updateItemQuantity(Product product, int newQuantity) {
        items.stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(newQuantity);
                calculateTotalAmount();
            });
    }
    
    private void calculateTotalAmount() {
        totalAmount = items.stream()
            .map(OrderItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void process() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be processed");
        }
        status = OrderStatus.PROCESSING;
    }
    
    public void ship() {
        if (status != OrderStatus.PROCESSING) {
            throw new IllegalStateException("Only processing orders can be shipped");
        }
        status = OrderStatus.SHIPPED;
    }
    
    public void deliver() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Only shipped orders can be delivered");
        }
        status = OrderStatus.DELIVERED;
    }
    
    public void cancel() {
        if (status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Delivered orders cannot be cancelled");
        }
        status = OrderStatus.CANCELLED;
    }
    
    // Getters and setters
}
```

### 2. Service Classes

#### InventoryService Class
```java
package inventory.service;

import inventory.model.*;
import inventory.exception.*;
import inventory.dao.ProductDAO;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {
    private ProductDAO productDAO;
    private Map<String, Product> products;
    private List<InventoryAlert> alerts;
    
    public InventoryService(ProductDAO productDAO) {
        this.productDAO = productDAO;
        this.products = new HashMap<>();
        this.alerts = new ArrayList<>();
        loadProducts();
    }
    
    public Product addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            throw new ProductAlreadyExistsException("Product already exists: " + product.getProductId());
        }
        
        products.put(product.getProductId(), product);
        productDAO.save(product);
        checkLowStockAlert(product);
        return product;
    }
    
    public Product updateProduct(String productId, Product updatedProduct) {
        Product existingProduct = getProduct(productId);
        
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setMinimumStockLevel(updatedProduct.getMinimumStockLevel());
        existingProduct.setSupplierId(updatedProduct.getSupplierId());
        
        productDAO.save(existingProduct);
        checkLowStockAlert(existingProduct);
        return existingProduct;
    }
    
    public void removeProduct(String productId) {
        Product product = getProduct(productId);
        if (product.getStockQuantity() > 0) {
            throw new ProductHasStockException("Cannot remove product with remaining stock");
        }
        
        products.remove(productId);
        productDAO.delete(productId);
    }
    
    public Product getProduct(String productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found: " + productId);
        }
        return product;
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public List<Product> searchProducts(String searchTerm) {
        String lowerSearchTerm = searchTerm.toLowerCase();
        return products.values().stream()
            .filter(product -> 
                product.getName().toLowerCase().contains(lowerSearchTerm) ||
                product.getDescription().toLowerCase().contains(lowerSearchTerm))
            .collect(Collectors.toList());
    }
    
    public List<Product> getProductsByCategory(Category category) {
        return products.values().stream()
            .filter(product -> product.getCategory().equals(category))
            .collect(Collectors.toList());
    }
    
    public List<Product> getLowStockProducts() {
        return products.values().stream()
            .filter(Product::isLowStock)
            .collect(Collectors.toList());
    }
    
    public List<Product> getOutOfStockProducts() {
        return products.values().stream()
            .filter(Product::isOutOfStock)
            .collect(Collectors.toList());
    }
    
    public void updateStock(String productId, int quantity) {
        Product product = getProduct(productId);
        product.updateStock(quantity);
        productDAO.save(product);
        checkLowStockAlert(product);
    }
    
    public void reduceStock(String productId, int quantity) throws InsufficientStockException {
        Product product = getProduct(productId);
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(
                String.format("Insufficient stock. Available: %d, Requested: %d", 
                    product.getStockQuantity(), quantity));
        }
        
        product.updateStock(-quantity);
        productDAO.save(product);
        checkLowStockAlert(product);
    }
    
    public void increaseStock(String productId, int quantity) {
        Product product = getProduct(productId);
        product.updateStock(quantity);
        productDAO.save(product);
        checkLowStockAlert(product);
    }
    
    private void checkLowStockAlert(Product product) {
        if (product.isLowStock()) {
            InventoryAlert alert = new InventoryAlert(
                product.getProductId(),
                product.getName(),
                product.getStockQuantity(),
                product.getMinimumStockLevel()
            );
            
            if (!alerts.contains(alert)) {
                alerts.add(alert);
                System.out.println("LOW STOCK ALERT: " + product.getName() + 
                    " (Stock: " + product.getStockQuantity() + 
                    ", Minimum: " + product.getMinimumStockLevel() + ")");
            }
        }
    }
    
    private void loadProducts() {
        products = productDAO.loadAll();
    }
    
    public List<InventoryAlert> getAlerts() {
        return new ArrayList<>(alerts);
    }
    
    public void clearAlerts() {
        alerts.clear();
    }
}
```

#### OrderService Class
```java
package inventory.service;

import inventory.model.*;
import inventory.exception.*;
import inventory.dao.OrderDAO;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private InventoryService inventoryService;
    private OrderDAO orderDAO;
    private List<Order> orders;
    
    public OrderService(InventoryService inventoryService, OrderDAO orderDAO) {
        this.inventoryService = inventoryService;
        this.orderDAO = orderDAO;
        this.orders = new ArrayList<>();
        loadOrders();
    }
    
    public Order createOrder(String customerName, String customerEmail) {
        Order order = new Order(customerName, customerEmail);
        orders.add(order);
        orderDAO.save(order);
        return order;
    }
    
    public void addItemToOrder(String orderId, String productId, int quantity) 
            throws ProductNotFoundException, InsufficientStockException {
        Order order = getOrder(orderId);
        Product product = inventoryService.getProduct(productId);
        
        // Check stock availability
        inventoryService.reduceStock(productId, quantity);
        
        order.addItem(product, quantity);
        orderDAO.save(order);
    }
    
    public void removeItemFromOrder(String orderId, String productId) {
        Order order = getOrder(orderId);
        Product product = inventoryService.getProduct(productId);
        
        // Find the item to get quantity
        Optional<OrderItem> itemToRemove = order.getItems().stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();
        
        if (itemToRemove.isPresent()) {
            int quantity = itemToRemove.get().getQuantity();
            order.removeItem(product);
            // Restore stock
            inventoryService.increaseStock(productId, quantity);
            orderDAO.save(order);
        }
    }
    
    public void processOrder(String orderId) {
        Order order = getOrder(orderId);
        order.process();
        orderDAO.save(order);
    }
    
    public void shipOrder(String orderId) {
        Order order = getOrder(orderId);
        order.ship();
        orderDAO.save(order);
    }
    
    public void deliverOrder(String orderId) {
        Order order = getOrder(orderId);
        order.deliver();
        orderDAO.save(order);
    }
    
    public void cancelOrder(String orderId) {
        Order order = getOrder(orderId);
        
        // Restore stock for all items
        for (OrderItem item : order.getItems()) {
            inventoryService.increaseStock(item.getProduct().getProductId(), item.getQuantity());
        }
        
        order.cancel();
        orderDAO.save(order);
    }
    
    public Order getOrder(String orderId) {
        return orders.stream()
            .filter(order -> order.getOrderId().equals(orderId))
            .findFirst()
            .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
    }
    
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
    
    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orders.stream()
            .filter(order -> order.getStatus() == status)
            .collect(Collectors.toList());
    }
    
    public List<Order> getOrdersByCustomer(String customerEmail) {
        return orders.stream()
            .filter(order -> order.getCustomerEmail().equals(customerEmail))
            .collect(Collectors.toList());
    }
    
    private void loadOrders() {
        orders = orderDAO.loadAll();
    }
}
```

### 3. Data Access Layer

#### Generic DAO Interface
```java
package inventory.dao;

import java.util.List;

public interface DataAccessObject<T, ID> {
    T save(T entity);
    T findById(ID id);
    List<T> loadAll();
    void delete(ID id);
    boolean exists(ID id);
}
```

#### ProductDAO Implementation
```java
package inventory.dao;

import inventory.model.Product;
import inventory.util.JsonUtils;
import java.util.*;
import java.util.stream.Collectors;

public class ProductDAO implements DataAccessObject<Product, String> {
    private static final String PRODUCTS_FILE = "src/main/resources/products.json";
    private JsonUtils jsonUtils;
    
    public ProductDAO() {
        this.jsonUtils = new JsonUtils();
    }
    
    @Override
    public Product save(Product product) {
        Map<String, Product> products = loadAllAsMap();
        products.put(product.getProductId(), product);
        jsonUtils.saveToFile(products, PRODUCTS_FILE);
        return product;
    }
    
    @Override
    public Product findById(String productId) {
        Map<String, Product> products = loadAllAsMap();
        return products.get(productId);
    }
    
    @Override
    public List<Product> loadAll() {
        return new ArrayList<>(loadAllAsMap().values());
    }
    
    @Override
    public void delete(String productId) {
        Map<String, Product> products = loadAllAsMap();
        products.remove(productId);
        jsonUtils.saveToFile(products, PRODUCTS_FILE);
    }
    
    @Override
    public boolean exists(String productId) {
        Map<String, Product> products = loadAllAsMap();
        return products.containsKey(productId);
    }
    
    private Map<String, Product> loadAllAsMap() {
        return jsonUtils.loadFromFile(PRODUCTS_FILE, new TypeReference<Map<String, Product>>() {});
    }
}
```

## User Interface

### Console Application
```java
package inventory;

import inventory.model.*;
import inventory.service.*;
import inventory.dao.*;
import java.util.Scanner;

public class InventoryApplication {
    private Scanner scanner;
    private InventoryService inventoryService;
    private OrderService orderService;
    private ReportService reportService;
    
    public static void main(String[] args) {
        InventoryApplication app = new InventoryApplication();
        app.run();
    }
    
    public void run() {
        scanner = new Scanner(System.in);
        initializeServices();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageInventory();
                    break;
                case 3:
                    manageOrders();
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    viewAlerts();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Inventory Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n=== Inventory Management System ===");
        System.out.println("1. Product Management");
        System.out.println("2. Inventory Management");
        System.out.println("3. Order Management");
        System.out.println("4. Reports");
        System.out.println("5. Alerts");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void manageProducts() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. View All Products");
            System.out.println("5. Search Products");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    searchProducts();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Additional menu methods...
}
```

## Cross-Platform Considerations

### Maven Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.inventory</groupId>
    <artifactId>inventory-management</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.8.2</junit.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.3</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M7</version>
            </plugin>
        </plugins>
    </build>
</project>
```

## Testing Strategy

### Unit Tests
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {
    private InventoryService inventoryService;
    private ProductDAO productDAO;
    
    @BeforeEach
    void setUp() {
        productDAO = new MockProductDAO();
        inventoryService = new InventoryService(productDAO);
    }
    
    @Test
    void testAddProduct() {
        Product product = new Product("P001", "Test Product", 
            new Category("C001", "Electronics"), new BigDecimal("99.99"));
        
        Product addedProduct = inventoryService.addProduct(product);
        assertEquals(product, addedProduct);
        assertTrue(inventoryService.getAllProducts().contains(product));
    }
    
    @Test
    void testInsufficientStockException() {
        Product product = new Product("P001", "Test Product", 
            new Category("C001", "Electronics"), new BigDecimal("99.99"));
        inventoryService.addProduct(product);
        
        assertThrows(InsufficientStockException.class, () -> {
            inventoryService.reduceStock("P001", 10);
        });
    }
}
```

## Performance Requirements
- Support for up to 50,000 products
- Order processing in < 2 seconds
- Search operations complete in < 1 second
- File I/O operations complete in < 3 seconds
- Memory usage < 200MB for maximum dataset

## Extension Ideas
- Database integration (MySQL, PostgreSQL)
- REST API with Spring Boot
- Web interface with Angular/React
- Mobile application
- Barcode scanning integration
- Multi-location inventory tracking
- Supplier integration and automated ordering
- Advanced analytics and forecasting

## Deliverables
1. Complete source code with proper package structure
2. Maven configuration with dependencies
3. Comprehensive unit tests
4. Documentation (README, JavaDoc)
5. Sample data files
6. Build and deployment scripts
7. User manual

## Success Criteria
- All inventory operations work correctly
- Order processing handles stock validation
- Data persists between application sessions
- Search and filtering functions work efficiently
- Exception handling prevents system crashes
- Code achieves 90% test coverage
- Application compiles and runs on all target platforms
