package com.glippy.entity;

/**
 * Created by oscar on 03/11/2014.
 */
public class Price {
    private String supermarket;
    private Float price;

    public Price(String supermarket, Float price) {
        this.supermarket = supermarket;
        this.price = price;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
