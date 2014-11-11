package com.glippy.scrapper;

import java.util.ArrayList;

public class Item {
    private String id;

    private String name;

    private String description;

    private ArrayList<Price> prices;

    public Item(String id, String name, String description, ArrayList<Price> prices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prices = prices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (!id.equals(item.id)) return false;
        if (!name.equals(item.name)) return false;
        if (prices != null ? !prices.equals(item.prices) : item.prices != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }
}
