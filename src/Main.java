import Data.DataBase;
import models.*;
import services.CartService;
import services.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("E-COMMERCE SYSTEM DEMO");
        System.out.println("=====================\n");
        DataBase.seedDatabase();

        System.out.println("Test 1: Successful Checkout");
        System.out.println("---------------------------");
        testSuccessfulCheckout();

        System.out.println("\nTest 2: Insufficient Balance");
        System.out.println("----------------------------");
        testInsufficientBalance();

        System.out.println("\nTest 3: Out of Stock");
        System.out.println("-------------------");
        testOutOfStock();

        System.out.println("\nTest 4: Expired Product");
        System.out.println("----------------------");
        testExpiredProduct();

        System.out.println("\nTest 5: Empty Cart");
        System.out.println("-----------------");
        testEmptyCart();

        System.out.println("\nTest 6: Cart Operations");
        System.out.println("----------------------");
        testCartOperations();
        System.out.println("\nDemo completed.");
    }

    private static void testSuccessfulCheckout() {
        String customerEmail = "zyad@gmail.com";
        CartService.AddToCart(customerEmail, "cheese", 2);
        CartService.AddToCart(customerEmail, "tv", 1);
        CartService.AddToCart(customerEmail, "scratchcard", 1);
        CheckoutService.Checkout(customerEmail);
    }

    private static void testInsufficientBalance() {
        String customerEmail = "ahmed@gmail.com";
        CartService.AddToCart(customerEmail, "tv", 1);
        CheckoutService.Checkout(customerEmail);
        CartService.ClearCart(customerEmail);
    }

    private static void testOutOfStock() {
        String customerEmail = "mahmoud@gmail.com";

        CartService.AddToCart(customerEmail, "tv", 2);
        DataBase.products.get("tv").setQuantity(1);
        CheckoutService.Checkout(customerEmail);
        DataBase.products.get("tv").setQuantity(6);
        CartService.ClearCart(customerEmail);
    }

    private static void testExpiredProduct() {
        String customerEmail = "zyad@gmail.com";
        CartService.AddToCart(customerEmail, "expiredMilk", 2);
        CheckoutService.Checkout(customerEmail);
        CartService.ClearCart(customerEmail);
    }

    private static void testEmptyCart() {
        String customerEmail = "mahmoud@gmail.com";
        CheckoutService.Checkout(customerEmail);
    }
    private static void testCartOperations() {
        String customerEmail = "zyad@gmail.com";
        CartService.AddToCart(customerEmail, "cheese", 3);
        CartService.AddToCart(customerEmail, "scratchcard", 2);
        CartService.AddToCart(customerEmail, "cheese", 1);
        CartService.RemoveFromCart(customerEmail, "cheese");
        CartService.RemoveItemFromCart(customerEmail, "scratchcard", 2);
        CartService.ClearCart(customerEmail);
    }
}