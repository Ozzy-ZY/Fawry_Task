package models;

import models.contracts.Expirable;

import java.time.LocalDate;

public class ShippableExpirableProduct extends ShippableProduct implements Expirable {
    private LocalDate expirationDate;

    public ShippableExpirableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }
}
