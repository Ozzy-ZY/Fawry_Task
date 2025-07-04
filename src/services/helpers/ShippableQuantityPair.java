package services.helpers;

import models.contracts.Shippable;

import java.util.ArrayList;

public class ShippableQuantityPair {
    private ArrayList<Shippable> shippableProducts;
    private ArrayList<Integer> quantities;
    public ShippableQuantityPair(ArrayList<Shippable> shippableProducts, ArrayList<Integer> quantities){
        this.setShippableProducts(shippableProducts);
        this.setQuantities(quantities);
    }

    public ArrayList<Shippable> getShippableProducts() {
        return shippableProducts;
    }

    public void setShippableProducts(ArrayList<Shippable> shippableProducts) {
        this.shippableProducts = shippableProducts;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }
}
