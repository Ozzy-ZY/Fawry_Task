import models.Cart;
import models.CartItem;
import models.Customer;
import models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataBase {
    private static HashMap<String, Customer> customers;
    private static HashMap<String, Cart> carts;
    private static HashMap<String, Product> products;
    private static ArrayList<CartItem> cartItems;

    static{
        customers = new HashMap<>();
        carts = new HashMap<>();
        products = new HashMap<>();
        cartItems = new ArrayList<>();
    }
    private DataBase(){}

    public void addCustomer(Customer customer){
        if (customers.putIfAbsent(customer.getEmail(), customer) == null) {
            carts.putIfAbsent(customer.getEmail(), new Cart(customer.getEmail()));
        }
    }
    public void addProduct(Product product){
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
        cartItems.stream()
                .filter(x -> x.getCustomerEmail().equals(customerEmail))
                .forEach(System.out::println);
    }
}
