package com.glippy.entity;

import org.springframework.data.mongodb.core.index.TextIndexed;


public class ShoppingListItem {

    @TextIndexed
    private String name;

    @TextIndexed
    private String description;

    private Float price;

    private String supermarket;

    private int quantity;

    // Constructors

    public ShoppingListItem() {
    }

    public ShoppingListItem(String itemName) {
        this.name = itemName;
        this.price =  new Float(0.0);
        this.supermarket = "mercadona";
        this.quantity = 1;
    }

    public ShoppingListItem(String itemName, int quantity) {
        this.name = itemName;
        this.price = new Float(0.0);
        this.supermarket = "mercadona";
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, double price) {
        this.name = itemName;
        this.price = new Float(price);
        this.quantity = 1;
        this.supermarket = "mercadona";
    }

    public ShoppingListItem(String itemName, double price, String supermarket) {
        this.name = itemName;
        this.price = new Float(price);
        this.supermarket = supermarket;
        this.quantity = 1;
    }

    public ShoppingListItem(String itemName, double price, String supermarket, int quantity) {
        this.name = itemName;
        this.price = new Float(price);
        this.supermarket = supermarket;
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, String supermarket, double price, int quantity) {
        this.name = itemName;
        this.supermarket = supermarket;
        this.price = new Float(price);
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, int quantity, String supermarket, double price) {
        this.name = itemName;
        this.quantity = quantity;
        this.supermarket = supermarket;
        this.price = new Float(price);
    }

    public ShoppingListItem(String itemName, String description, String supermarket, double price, int quantity) {
        this.name = itemName;
        this.description = description;
        this.supermarket = supermarket;
        this.price = new Float(price);
        this.quantity = quantity;
    }


    // Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public int getQuantity() {
        return quantity;
    }


    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = new Float(price);
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //Equals & Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingListItem that = (ShoppingListItem) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (quantity != that.quantity) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!name.equals(that.name)) return false;
        if (supermarket != null ? !supermarket.equals(that.supermarket) : that.supermarket != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (supermarket != null ? supermarket.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }


    // Other

}
