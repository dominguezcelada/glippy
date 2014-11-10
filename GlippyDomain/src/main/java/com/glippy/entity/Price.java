package com.glippy.entity;

/**
 * Created by oscar on 03/11/2014.
 */
public class Price {
    private String supermarket;
    private Float price;


    public Price() {
    }

    public Price(double price) {
        this.supermarket = "mercadona";
        this.price = new Float(price);
    }

    public Price(String supermarket) {
        this.supermarket = supermarket;
        this.price = new Float(0.0);
    }

    public Price(String supermarket, Float price) {
        this.supermarket = supermarket;
        this.price = new Float(price);
    }

    public Price(String supermarket, double price) {
        this.supermarket = supermarket;
        this.price = new Float(price);
    }

    public Price(double price, String supermarket) {
        this.supermarket = supermarket;
        this.price = new Float(price);
    }

    // Setters

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public void setPrice(double price) {
        this.price = new Float(price);
    }

    public void setPrice(Float price) {
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

        if (!price.equals(price1.price)) return false;
        if (!supermarket.equals(price1.supermarket)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = supermarket.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
