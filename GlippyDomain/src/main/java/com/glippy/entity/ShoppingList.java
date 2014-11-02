package com.glippy.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 01/11/2014.
 */

@Document()
public class ShoppingList {
    @Id
    private String id;

    @TextIndexed
    private String name;

    private List<ShoppingListItem> items = new ArrayList<ShoppingListItem>();



    public ShoppingList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItem> items) {
        this.items = items;
    }

    public ShoppingList addItem(String itemName, int quantity) {
        this.items.add(new ShoppingListItem(itemName, quantity));
        return this;
    }

    public ShoppingList addItem(String itemName) {
        this.items.add(new ShoppingListItem(itemName));
        return this;
    }

    public ShoppingList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingList that = (ShoppingList) o;

        if (!items.equals(that.items)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + items.hashCode();
        return result;
    }
}
