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

    private String username;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime lastModifiedDate;

    @TextIndexed
    private String name;

    private List<ShoppingListItem> items = new ArrayList<ShoppingListItem>();

    // Constructors

    public ShoppingList(String name, List<ShoppingListItem> items) {
        this.name = name;
        this.items = items;
    }

    public ShoppingList(String name) {
        this.name = name;
    }

    public ShoppingList(String name, ShoppingListItem shoppingListItem) {
        this.name = name;
        this.items.add(shoppingListItem);
    }

    public ShoppingList(String name, String itemName, double price, String supermarket, int quantity) {
        this.name = name;
        this.items.add(new ShoppingListItem(itemName,price,supermarket,quantity));
    }

    public ShoppingList(String name, String itemName, String supermarket, int quantity, double price) {
        this.name = name;
        this.items.add(new ShoppingListItem(itemName,price,supermarket,quantity));
    }

    public ShoppingList() {

    }

    public ShoppingList(String name, String username) {
        this.name = name;
        this.username = username;
    }


    // Getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Equals & Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingList that = (ShoppingList) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (!name.equals(that.name)) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }



    // AddItem

    public ShoppingList addItem(String itemName) {
        this.items.add(new ShoppingListItem(itemName,0.0,"mercadona",1));
        return this;
    }

    public ShoppingList addItem(String itemName, int quantity) {
        this.items.add(new ShoppingListItem(itemName,0.0,"mercadona",quantity));
        return this;
    }

    public ShoppingList addItem(String itemName, double price) {
        this.items.add(new ShoppingListItem(itemName,price,"mercadona",1));
        return this;
    }

    public ShoppingList addItem(String itemName, double price, String supermarket) {
        this.items.add(new ShoppingListItem(itemName,price,supermarket,1));
        return this;
    }

    public ShoppingList addItem(String itemName, double price, String supermarket, int quantity) {
        this.items.add(new ShoppingListItem(itemName,price,supermarket,quantity));
        return this;
    }
}