package com.glippy.domain;

import com.glippy.entity.ShoppingList;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by oscar on 15/11/2014.
 */
public interface ShoppingListRepositoryCustom {
    public void updateQuantity(Query querySelect, Update queryUpdate);

    public void addItem(Query querySelect, Update queryUpdate);
}
