package services;

import Data.DataBase;
import models.Cart;
import models.Product;
import models.contracts.Expirable;
import models.contracts.Shippable;
import services.helpers.ShippableQuantityPair;

import java.util.ArrayList;

public class CheckoutService {
    public static void Checkout(String customerEmail){
        var customer = DataBase.customers.get(customerEmail);
        if(customer == null){
            System.out.println("Customer does not exist");
            return;
        }
        var cart = DataBase.carts.get(customerEmail);
        if(cart == null || cart.getItems().isEmpty()){
            System.out.println("cart does not exist or empty ");
            return;
        }
        if(!validateCartItems(cart)){
            System.out.println("cart items are not valid");
            return;
        }
        double subtotal = calculateSubtotal(cart);
        var shippableItemsWithQuantities = getShippableItems(cart);
        double shippingFee = ShippingService.ProcessShipping(
                shippableItemsWithQuantities.getShippableProducts(), shippableItemsWithQuantities.getQuantities());
        var totalAmount = subtotal + shippingFee;
        if(customer.getBalance() < totalAmount){
            System.out.println("Insufficient balance");
            return;
        }
        customer.setBalance(customer.getBalance() - totalAmount);
        var products = new ArrayList<Product>();
        for(var cartItem: cart.getItems()){
            var product = DataBase.products.get(cartItem.getProductName());
            if(product != null){
                products.add(product); // to print it out later
                product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            }
        }
        System.out.println("** Checkout receipt **");
        for(int i = 0; i < cart.cartItems.size(); i++){
            var item = cart.cartItems.get(i);
            System.out.println(item.getQuantity()+"x "+ item.getProductName() + "\t" + products.get(i).getPrice()* item.getQuantity());
        }
        System.out.println("---------------------");
        System.out.println("Subtotal\t"+ subtotal);
        System.out.println("Shipping\t "+ shippingFee);
        System.out.println("Amount\t"+ totalAmount);
        System.out.println("---------------------");
        System.out.println("New Balance for customer\t"+ customer.getBalance());
        CartService.ClearCart(customerEmail);
    }
    private static ShippableQuantityPair getShippableItems(Cart cart){
        ArrayList<Shippable> shippableItems = new ArrayList<>();
        ArrayList<Integer> quantites = new ArrayList<>();

        for(var cartItem: cart.getItems()){
            var product = DataBase.products.get(cartItem.getProductName());
            if(product instanceof Shippable shippableProduct){
                shippableItems.add(shippableProduct);
                quantites.add(cartItem.getQuantity());
            }
        }
        return new ShippableQuantityPair(shippableItems, quantites);
    }
    private static double calculateSubtotal(Cart cart){
        double subtotal = 0.0d;
        for(var cartItem: cart.getItems()){
            var product = DataBase.products.get(cartItem.getProductName());
            if(product!= null){
                subtotal += product.getPrice() * cartItem.getQuantity();
            }
        }
        return subtotal;
    }
    private static boolean validateCartItems(Cart cart){
        for(var cartItem: cart.getItems()){
            var product = DataBase.products.get(cartItem.getProductName());
            if(product == null){
                System.out.println("Product does not exist");
                return false;
            }
            if(product.getQuantity() < cartItem.getQuantity()){
                System.out.println("stock quantity is not enough");
                return false;
            }
            if (product instanceof Expirable expirableProduct) {
                if (expirableProduct.isExpired()) {
                    System.out.println("Product is expired");
                    return false;
                }
            }
        }
        return true;
    }
}
