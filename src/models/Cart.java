package models;

import java.util.ArrayList;

public class Cart {
    private String customerEmail;
    private double subTotal;
    public ArrayList<CartItem> cartItems;
    public Cart(String customerEmail){
        this.customerEmail = customerEmail;
        cartItems = new ArrayList<>();
        subTotal = 0.0d;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    public double getSubTotal() {
        return subTotal;
    }
    public ArrayList<CartItem> getItems() {
        return cartItems;
    }
}
