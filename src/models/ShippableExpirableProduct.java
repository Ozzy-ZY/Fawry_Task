package models;

import models.contracts.Expirable;

import java.time.LocalDate;

public class ShippableExpirableProduct extends ShippableProduct implements Expirable {
    protected LocalDate expirationDate;

    public ShippableExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }
}
