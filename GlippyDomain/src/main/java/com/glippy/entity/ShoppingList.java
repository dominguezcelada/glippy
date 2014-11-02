package com.glippy.entity;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.ArrayList;
import java.util.List;

@Document
public class ShoppingList {
    @Id
    private String id;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime lastModifiedDate;

    @TextIndexed
    private String name;

    @TextScore
    private Float score;

    private List<ShoppingListItem> items = new ArrayList<ShoppingListItem>();

    public ShoppingList() {}

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

    public ShoppingList addItem(ShoppingListItem item) {
        this.items.add(item);
        return this;
    }

    public ShoppingList addItem(String itemName, int quantity) {
        this.items.add(new ShoppingListItem(itemName, quantity));
        return this;
    }

    public ShoppingList addItem(String itemName) {
        this.items.add(new ShoppingListItem(itemName));
        return this;
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