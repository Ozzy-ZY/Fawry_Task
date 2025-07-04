# Fawry Rise Journey Task
#### Contender: Zyad Mohamed Mahmoud Alsaeed
## High level Architecture:
* **Data Layer:** created an In-memory Database using static hashMaps to simulate a database
* **Model Layer:** where the models are
* **Service Layer:** contains the business logic for the Operations like Cart, Checkout and shipping
* **Contracts:** Interfaces defining behavior for expirable and shippable products
## Some Design Choices and why
### 1. Product Hierarchy with Interface Implementation

I chose to implement a product hierarchy using abstract classes and interfaces:

```java
Product (abstract)
-- BasicProduct
-- ExpirableProduct (implements Expirable)
-- ShippableProduct (implements Shippable)
-- ShippableExpirableProduct (extends ShippableProduct, implements Expirable)
```

**Why?**: This design allows for flexible product types while maintaining the Open/Closed Principle,  
New product types can be added without modifying existing code  

### 2. Service-Oriented Architecture

Each major operation is encapsulated in its own service class:
- **CartService**: Handles cart operations
- **CheckoutService**: Manages checkout process
- **ShippingService**: Calculates shipping fees

**Why?**: This promotes single responsibility and makes the system more maintainable
### 4. Interface-Based Design for Shipping and Expiry

The `Shippable` interface defines the contract for items that can be shipped, requiring only `getName()` and `getWeight()` methods  
and also validating expiry with Type Safety.

**Why?**: This follows the Interface Segregation Principle, allowing the shipping service to work with any object implementing these minimal requirements

## Alternative Approaches Considered

### 1. Composition Over Inheritance

**What I Considered**: Using composition to create products with behavior objects:
```java
public class Product {
    private String name;
    private double price;
    private int quantity;
    private ExpirationBehavior expirationBehavior;
    private ShippingBehavior shippingBehavior;
}
```
**Why I Didn't Choose It**:
- Would require more complex Implementation
- The current inheritance structure is more straightforward for this use case
- The behavior combinations are limited and well-defined
### 2. Adapter Pattern for Shipping Service

**What I Considered**: Creating an adapter to convert products to shipping items:
```java
public class ProductShippingAdapter implements Shippable {
    private Product product;
    // Convert product to shippable format
}
```
**Why I Didn't Choose It**:
- The current approach is simpler and more direct
- Products that implement `Shippable` already provide the required interface
- Adding an adapter layer would introduce unnecessary complexity for this scope
## Design Pattern Opportunities for Future Refactoring

### 1. Factory Pattern
Implement a ProductFactory to create different product types:
```java
public class ProductFactory;
```
## HashMap Performance Optimization

The system uses HashMaps strategically to optimize performance:

### 1. Product Lookup
```java
public static HashMap<String, Product> products;
```
- **Benefit**: O(1) average-case lookup time for product retrieval
- **Use Case**: Quick product validation and price calculation during cart operations

### 2. Customer Management
```java
public static HashMap<String, Customer> customers;
```
- **Benefit**: O(1) customer lookup using email as key
- **Use Case**: Fast customer validation during checkout

### 3. Cart Association
```java
public static HashMap<String, Cart> carts;
```
- **Benefit**: Direct cart access per customer without iteration
- **Use Case**: Efficient cart operations without searching through collections

### Performance Impact
- **Add to Cart**: O(1) for product lookup + O(n) for existing item check (where n = cart items)
- **Checkout**: O(1) for customer/cart lookup + O(m) for cart validation (where m = cart items)
- **Product Management**: O(1) for all basic operations
## Finally
**I** want to Thank **Fawry** for this challenge I enjoyed it very Much :) 

