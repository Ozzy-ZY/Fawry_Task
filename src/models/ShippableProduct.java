package models;

import models.contracts.Shippable;

public class ShippableProduct extends Product implements Shippable {
    protected double weight;
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return super.toString() + " Weight: " + weight +"g ";
    }
}
