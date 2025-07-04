package models;

public class Cart {
    private String customerEmail;
    private double subTotal;
    public Cart(String customerEmail){
        this.customerEmail = customerEmail;
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
}
