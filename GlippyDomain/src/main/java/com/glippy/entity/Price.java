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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (Double.compare(price1.price, price) != 0) return false;
        if (!supermarket.equals(price1.supermarket)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = supermarket.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
