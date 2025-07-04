package models;

import models.contracts.Expirable;

import java.time.LocalDate;

public class ShippableExpirableProduct extends ShippableProduct implements Expirable {
    protected LocalDate expirationDate;

    public ShippableExpirableProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, weight);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return super.toString() + " ExpirationDate: " + expirationDate;
    }
}
