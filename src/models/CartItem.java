package models;

public class CartItem {
    private String customerEmail; // partial key
    private String productName; // partial key and they both act as a composite key
    private int quantity;

    public CartItem(String productName, String customerEmail, int quantity ){
        this.productName = productName;
        this.customerEmail = customerEmail;
        this.quantity = quantity;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
