package com.glippy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Item {
    @Id
    private String id;

    @TextIndexed
    private String name;

    @TextIndexed
    private String description;

    private String category;

    private String imageURL;

    private List<Price> prices = new ArrayList<Price>();



    // Constructors

    public Item(String itemName) {
        this.name = itemName;
        this.prices.add(new Price());
    }

    public Item(String itemName, Price price) {
        this.name = itemName;
        this.prices.add(price);
    }

    public Item(String itemName, ArrayList<Price> prices) {
        this.name = itemName;
        this.prices = prices;
    }

    public Item(String itemName, double price, String supermarket) {
        this.name = itemName;
        this.prices.add(new Price(supermarket, price));
    }

    public Item(String itemName, String supermarket, double price) {
        this.name = itemName;
        this.prices.add(new Price (supermarket,price));
    }

    public Item(String itemName, String description, List<Price> prices) {
        this.name = itemName;
        this.description = description;
        this.prices = prices;
    }

    public Item(String itemName, String description, List<Price> prices, String category, String imageURL) {
        this.name = itemName;
        this.description = description;
        this.prices = prices;
        this.category = category;
        this.imageURL = imageURL;
    }

    public Item() {

    }

    public Item(String itemName, double price) {
        this.name = itemName;
        this.prices.add(new Price(price));
    }

    public Item(String itemName, String description, double price, String supermarket) {
        this.name = itemName;
        this.description = description;
        this.prices.add(new Price(price,supermarket));
    }

    public Item(String itemName, String description) {
        this.name = itemName;
        this.description = description;
    }

    public Item(String id, String name, String description, List<Price> prices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prices = prices;
    }

    public Item(String id, String name, String description, String category, String imageURL, List<Price> prices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageURL = imageURL;
        this.prices = prices;
    }

    // Getters

    public String getId() {
        return id;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {

        return category;
    }

    public String getImageURL() {
        return imageURL;
    }

    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    // Equals & Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (category != null ? !category.equals(item.category) : item.category != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (imageURL != null ? !imageURL.equals(item.imageURL) : item.imageURL != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (prices != null ? !prices.equals(item.prices) : item.prices != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (imageURL != null ? imageURL.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }


    // Other

    public Item addPrice(String supermarket, double price) {
        this.prices.add(new Price(supermarket,price));
        return this;
    }


















}
