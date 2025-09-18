# Inventory Management System Project

## Project Overview
A comprehensive inventory management system that demonstrates Java collections, generics, and advanced programming concepts. The system manages product inventory, suppliers, orders, and generates various reports.

## Project Specifications

### Core Features
- Product catalog management
- Inventory tracking and stock control
- Supplier management
- Order processing and fulfillment
- Low stock alerts and notifications
- Sales reporting and analytics
- Data persistence using file I/O
- Search and filtering capabilities

### Technical Requirements
- **Language**: Java 11 or higher
- **Dependencies**: Standard Java libraries only
- **Platforms**: Linux, Windows, macOS
- **Data Storage**: File-based (CSV/JSON format)

## Project Structure
```
inventory-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── inventory/
│   │   │   │       ├── InventoryManagementSystem.java
│   │   │   │       ├── model/
│   │   │   │       │   ├── Product.java
│   │   │   │       │   ├── Supplier.java
│   │   │   │       │   ├── Order.java
│   │   │   │       │   └── InventoryItem.java
│   │   │   │       ├── service/
│   │   │   │       │   ├── InventoryService.java
│   │   │   │       │   ├── OrderService.java
│   │   │   │       │   └── ReportService.java
│   │   │   │       ├── exception/
│   │   │   │       │   ├── InsufficientStockException.java
│   │   │   │       │   └── ProductNotFoundException.java
│   │   │   │       └── util/
│   │   │   │           ├── DataManager.java
│   │   │   │           └── StockAlert.java
│   │   │   └── Main.java
├── data/
│   ├── products.csv
│   ├── suppliers.csv
│   └── orders.csv
└── reports/
    └── inventory/
```

## Detailed Implementation

### Core Model Classes

#### Product.java
```java
package com.inventory.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private String productId;
    private String productName;
    private String description;
    private String category;
    private BigDecimal unitPrice;
    private String supplierId;
    private LocalDate dateAdded;
    private boolean isActive;
    
    public Product(String productId, String productName, String category, 
                   BigDecimal unitPrice, String supplierId) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
        this.dateAdded = LocalDate.now();
        this.isActive = true;
    }
    
    // Getters and setters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public String getSupplierId() { return supplierId; }
    public LocalDate getDateAdded() { return dateAdded; }
    public boolean isActive() { return isActive; }
    
    public void setDescription(String description) { this.description = description; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public void setActive(boolean active) { this.isActive = active; }
    
    public String getProductSummary() {
        return String.format("ID: %s | Name: %s | Category: %s | Price: $%.2f | Supplier: %s",
                           productId, productName, category, unitPrice, supplierId);
    }
}
```

#### InventoryItem.java
```java
package com.inventory.model;

import java.time.LocalDateTime;

public class InventoryItem {
    private String productId;
    private int quantityInStock;
    private int minimumStockLevel;
    private int maximumStockLevel;
    private LocalDateTime lastUpdated;
    
    public InventoryItem(String productId, int initialQuantity, int minimumStock, int maximumStock) {
        this.productId = productId;
        this.quantityInStock = initialQuantity;
        this.minimumStockLevel = minimumStock;
        this.maximumStockLevel = maximumStock;
        this.lastUpdated = LocalDateTime.now();
    }
    
    // Getters and setters
    public String getProductId() { return productId; }
    public int getQuantityInStock() { return quantityInStock; }
    public int getMinimumStockLevel() { return minimumStockLevel; }
    public int getMaximumStockLevel() { return maximumStockLevel; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    
    public void setMinimumStockLevel(int minimumStockLevel) { 
        this.minimumStockLevel = minimumStockLevel; 
    }
    public void setMaximumStockLevel(int maximumStockLevel) { 
        this.maximumStockLevel = maximumStockLevel; 
    }
    
    // Business methods
    public void addStock(int quantity) {
        if (quantity > 0) {
            quantityInStock += quantity;
            lastUpdated = LocalDateTime.now();
        }
    }
    
    public void removeStock(int quantity) throws InsufficientStockException {
        if (quantity > quantityInStock) {
            throw new InsufficientStockException(
                "Insufficient stock. Available: " + quantityInStock + ", Requested: " + quantity);
        }
        quantityInStock -= quantity;
        lastUpdated = LocalDateTime.now();
    }
    
    public boolean isLowStock() {
        return quantityInStock <= minimumStockLevel;
    }
    
    public boolean isOverstocked() {
        return quantityInStock > maximumStockLevel;
    }
    
    public String getStockStatus() {
        if (isLowStock()) return "LOW";
        if (isOverstocked()) return "OVERSTOCKED";
        return "NORMAL";
    }
    
    public String getInventorySummary() {
        return String.format("Product: %s | Stock: %d | Min: %d | Max: %d | Status: %s",
                           productId, quantityInStock, minimumStockLevel, 
                           maximumStockLevel, getStockStatus());
    }
}
```

### Service Classes

#### InventoryService.java
```java
package com.inventory.service;

import com.inventory.model.*;
import com.inventory.exception.*;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {
    private Map<String, Product> products;
    private Map<String, InventoryItem> inventory;
    private Map<String, Supplier> suppliers;
    
    public InventoryService() {
        this.products = new HashMap<>();
        this.inventory = new HashMap<>();
        this.suppliers = new HashMap<>();
    }
    
    public void addProduct(Product product, int initialQuantity, int minStock, int maxStock) {
        products.put(product.getProductId(), product);
        inventory.put(product.getProductId(), 
                     new InventoryItem(product.getProductId(), initialQuantity, minStock, maxStock));
    }
    
    public Product getProduct(String productId) throws ProductNotFoundException {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found: " + productId);
        }
        return product;
    }
    
    public InventoryItem getInventoryItem(String productId) throws ProductNotFoundException {
        InventoryItem item = inventory.get(productId);
        if (item == null) {
            throw new ProductNotFoundException("Inventory item not found: " + productId);
        }
        return item;
    }
    
    public void updateStock(String productId, int quantity) throws ProductNotFoundException {
        InventoryItem item = getInventoryItem(productId);
        if (quantity > 0) {
            item.addStock(quantity);
        } else if (quantity < 0) {
            item.removeStock(-quantity);
        }
    }
    
    public List<InventoryItem> getLowStockItems() {
        return inventory.values().stream()
                .filter(InventoryItem::isLowStock)
                .collect(Collectors.toList());
    }
    
    public List<Product> searchProductsByName(String name) {
        return products.values().stream()
                .filter(product -> product.getProductName().toLowerCase()
                                 .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Product> getProductsByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    public void generateInventoryReport() {
        System.out.println("\n=== Inventory Report ===");
        System.out.println("Generated: " + java.time.LocalDateTime.now());
        System.out.println("==========================================");
        
        for (InventoryItem item : inventory.values()) {
            Product product = products.get(item.getProductId());
            if (product != null) {
                System.out.printf("%-15s | %-20s | %-10s | %-6d | %-8s%n",
                                product.getProductId(),
                                product.getProductName(),
                                product.getCategory(),
                                item.getQuantityInStock(),
                                item.getStockStatus());
            }
        }
        
        System.out.println("==========================================");
        
        long lowStockCount = inventory.values().stream()
                .filter(InventoryItem::isLowStock)
                .count();
        
        System.out.println("Total Products: " + inventory.size());
        System.out.println("Low Stock Items: " + lowStockCount);
    }
    
    public void generateLowStockAlert() {
        List<InventoryItem> lowStockItems = getLowStockItems();
        
        if (!lowStockItems.isEmpty()) {
            System.out.println("\n⚠️  LOW STOCK ALERT ⚠️");
            System.out.println("The following items are running low on stock:");
            
            for (InventoryItem item : lowStockItems) {
                Product product = products.get(item.getProductId());
                if (product != null) {
                    System.out.printf("- %s (%s): %d units (Min: %d)%n",
                                    product.getProductName(),
                                    product.getProductId(),
                                    item.getQuantityInStock(),
                                    item.getMinimumStockLevel());
                }
            }
        }
    }
}
```

## Cross-Platform Build and Run

### Linux/macOS
```bash
# Compile
javac -d bin -cp src src/**/*.java

# Run
java -cp bin com.inventory.Main
```

### Windows
```cmd
# Compile
javac -d bin -cp src src\**\*.java

# Run
java -cp bin com.inventory.Main
```

## Key Features Demonstrated
- Java Collections Framework (HashMap, ArrayList, Streams)
- Generic types and type safety
- Exception handling with custom exceptions
- File I/O operations for data persistence
- Business logic implementation
- Search and filtering capabilities
- Report generation
- Stock management algorithms

## Extension Ideas
- Database integration
- REST API implementation
- Web interface
- Barcode scanning integration
- Multi-location inventory tracking
- Automated reorder suggestions
- Sales forecasting
- Integration with accounting systems

## Learning Outcomes
- Understanding Java collections and generics
- Learning inventory management concepts
- Implementing business logic in service classes
- Creating comprehensive management systems
- Cross-platform Java development
- File I/O and data persistence
- Exception handling and validation
