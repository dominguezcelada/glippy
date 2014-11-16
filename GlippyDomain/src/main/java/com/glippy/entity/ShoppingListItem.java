package com.glippy.entity;

import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListItem {

    private int quantity = 1; // By default
    private Item item;


    // Constructors

    public ShoppingListItem() {
    }

    public ShoppingListItem(String itemName) {
        this.item = new Item(itemName);
    }

    public ShoppingListItem(String itemName, int quantity) {
        this.item = new Item(itemName);
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, double price) {
        this.item = new Item(itemName);
    }

    public ShoppingListItem(String itemName, double price, String supermarket) {
        this.item = new Item(itemName, price, supermarket);
    }

    public ShoppingListItem(String itemName, double price, String supermarket, int quantity) {
        this.item = new Item(itemName, price, supermarket);
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, int quantity, String supermarket, double price) {
        this.item = new Item(itemName, price, supermarket);
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, String supermarket, double price, int quantity) {
        this.item = new Item(itemName, price, supermarket);
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, String description, String supermarket, double price, int quantity) {
        this.item = new Item(itemName, price, supermarket);
        this.quantity = quantity;
    }


    // Getters

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    //Setters

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //Equals & Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingListItem that = (ShoppingListItem) o;

        if (quantity != that.quantity) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }


    // Other

}
