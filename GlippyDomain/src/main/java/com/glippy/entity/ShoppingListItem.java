package com.glippy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListItem {

    @TextIndexed
    private Item item;

    private int quantity;

    // Constructors

    public ShoppingListItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName, double price, String supermarket, int quantity) {
        this.item = new Item(itemName,price,supermarket);
        this.quantity = quantity;
    }

    public ShoppingListItem(String itemName) {
        this.item = new Item(itemName);
    }

    public ShoppingListItem(String itemName, int quantity) {
        this.item = new Item(itemName);
        this.quantity = quantity;
    }

    public ShoppingListItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }


    // Getters

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }


    //Setters

    public void setItem(Item item) {
        this.item = item;
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

        if (quantity != that.quantity) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }


    // Other

    public ShoppingListItem addPrice(String supermarket, double price) {
        this.item.addPrice(supermarket, price);
        return this;
    }

}
