package com.glippy.entity;

import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * Created by oscar on 03/11/2014.
 */
public class Price {

    private String supermarket = "Mercadona"; // By default

    private double price = 0.0; // By default

    // Constructors

    public Price() {
    }

    public Price(double price) {
        this.price = price;
    }

    public Price(String supermarket) {
        this.supermarket = supermarket;
    }

    public Price(String supermarket, double price) {
        this.supermarket = supermarket;
        this.price = price;
    }

    public Price(double price, String supermarket) {
        this.supermarket = supermarket;
        this.price = price;
    }

    // Setters

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // Getters

    public String getSupermarket() {
        return supermarket;
    }

    public double getPrice() {
        return price;
    }


    // Equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (Double.compare(price1.price, price) != 0) return false;
        if (supermarket != null ? !supermarket.equals(price1.supermarket) : price1.supermarket != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = supermarket != null ? supermarket.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
