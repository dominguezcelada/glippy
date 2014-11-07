package com.glippy.entity;

/**
 * Created by oscar on 03/11/2014.
 */
public class Price {
    private String supermarket;
    private double price;

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Price(String supermarket, Float price) {
        this.supermarket = supermarket;
        this.price = price;
    }

    public Price(String supermarket, double price) {
        this.supermarket = supermarket;
        this.price = price;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
