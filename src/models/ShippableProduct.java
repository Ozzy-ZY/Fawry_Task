package models;

import models.contracts.Shippable;

public class ShippableProduct extends Product implements Shippable {
    protected double weight;
    public ShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }
}
