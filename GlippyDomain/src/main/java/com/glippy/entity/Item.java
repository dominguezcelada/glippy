package com.glippy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.List;

public class Item {
    @Id
    private String id;

    @TextIndexed
    private String name;

    @TextIndexed
    private String description;

    private List<Float> prices;

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

    public List<Float> getPrices() {
        return prices;
    }

    public void setPrices(List<Float> prices) {
        this.prices = prices;
    }
}
