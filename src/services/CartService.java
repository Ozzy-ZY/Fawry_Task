package services;
import Data.DataBase;
import models.Cart;
import models.CartItem;

public class CartService {
    public static boolean AddToCart(String customerEmail, String productName, int quantity){
        var prod = DataBase.products.get(productName);
        if(prod == null){
            System.out.println("Product does not exist"); // I could instead return a custom exception
            // but I chose to keep it simple
            // or maybe I could Create a Result Pattern and return custom Result Objects
            return false;
        }
        if(prod.getQuantity() < quantity){
            System.out.println("stock quantity is not enough");
            return false;
        }
        var cart = DataBase.carts.get(customerEmail);
        if(cart == null){
            System.out.println("Customer does not exist");
            return false;
        }
        CartItem item = cart.getItems()
                .stream()
                .filter(x -> x.getProductName().equals(productName)).findFirst().orElse(null);
        if(item != null){
            if(item.getQuantity() + quantity > prod.getQuantity()){
                System.out.println("stock quantity is not enough");
                return false;
            }
            item.setQuantity(item.getQuantity() + quantity);
        }

        else{
        cart.cartItems.add(new CartItem(productName, customerEmail, quantity));
        }
        cart.setSubTotal(cart.getSubTotal() + prod.getPrice() * quantity);
        System.out.println("added "+ productName + " to cart");
        return true;
    }
    public static boolean RemoveItemFromCart(String customerEmail, String productName, int quantity){
        var cart = DataBase.carts.get(customerEmail);
        if(cart == null){
            System.out.println("Customer does not exist");
            return false;
        }
        CartItem item = cart.getItems()
                .stream()
                .filter(x -> x.getProductName().equals(productName)).findFirst().orElse(null);
        if(item == null){
            System.out.println("Item does not exist");
            return true;// as it does not exist in the cart in the first place
        }
        cart.cartItems.remove(item);
        DataBase.products.get(productName).setQuantity(DataBase.products.get(productName).getQuantity() + item.getQuantity());
        double newSubTotal = cart.getSubTotal() - item.getQuantity() * DataBase.products.get(productName).getPrice();
        cart.setSubTotal(newSubTotal);
        return true;
    }
    public static boolean RemoveFromCart(String customerEmail, String productName){
        var cart = DataBase.carts.get(customerEmail);
        if(cart == null){
            System.out.println("Customer does not exist");
            return false;
        }
        CartItem item = cart.getItems()
                .stream()
                .filter(x -> x.getProductName().equals(productName)).findFirst().orElse(null);
        if(item == null){
            System.out.println("Item does not exist");
            return true; // as it does not exist in the cart in the first place
        }
        if(item.getQuantity() == 1){
            cart.getItems().remove(item);
        }
        else{
            item.setQuantity(item.getQuantity() - 1);
        }
        DataBase.products.get(productName).setQuantity(DataBase.products.get(productName).getQuantity() + 1);
        double newSubTotal = cart.getSubTotal() - item.getQuantity() * DataBase.products.get(productName).getPrice();
        cart.setSubTotal(newSubTotal);
        return true;
    }
    public static Cart GetCart(String customerEmail){
        return DataBase.carts.get(customerEmail);
    }
    public static void ClearCart(String customerEmail){
        var cart = DataBase.carts.get(customerEmail);
        cart.getItems().clear();
        cart.setSubTotal(0.0d);
    }
}
