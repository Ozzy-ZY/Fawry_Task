package Data;

import models.*;

import java.time.LocalDate;
import java.util.HashMap;

public class DataBase {
    public static HashMap<String, Customer> customers;
    public static HashMap<String, Cart> carts;
    public static HashMap<String, Product> products;

    static{
        customers = new HashMap<>();
        carts = new HashMap<>();
        products = new HashMap<>();
    }
    private DataBase(){}

    public static void addCustomer(Customer customer){
        if (customers.putIfAbsent(customer.getEmail(), customer) == null) {
            carts.putIfAbsent(customer.getEmail(), new Cart(customer.getEmail()));
        }
    }
    public static void addProduct(Product product){
        products.putIfAbsent(product.getName(),product);
    }
    public static void PrintAllCustomers(){
        for(Customer customer:customers.values()){
            System.out.println(customer);
        }
    }
    public static void PrintAllProducts() {
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
    public static void PrintAllCarts() {
        for (Cart cart : carts.values()) {
            System.out.println(cart);
        }
    }
    public static void PrintAllCartItemsGivenCartId(String customerEmail) {
        Cart cart = carts.get(customerEmail);
        if (cart != null) {
            cart.getItems().forEach(System.out::println);
        }
    }
    public static void seedDatabase(){
        products.put("cheese", new ShippableExpirableProduct(
                "cheese",10, 4, LocalDate.now().plusDays(10), 500));
        products.put("biscuits", new ShippableExpirableProduct(
                "biscuits",5, 8, LocalDate.now().plusDays(30), 100));
        products.put("tv", new ShippableProduct(
                "tv",500, 6, 3000));
        products.put("scratchcard", new BasicProduct("scratchcard", 8,20));
        // adding customers and their carts
        addCustomer(new Customer("Zyad", "01011113221", "zyad@gmail.com", "Giza", 2000));
        addCustomer(new Customer("Mahmoud", "01011113222", "mahmoud@gmail.com", "Cairo", 1000));
    }
}
