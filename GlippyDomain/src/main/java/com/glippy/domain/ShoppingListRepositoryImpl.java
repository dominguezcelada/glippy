package com.glippy.domain;

import com.glippy.entity.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


/**
 * Created by oscar on 15/11/2014.
 */
public class ShoppingListRepositoryImpl implements ShoppingListRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateShoppingList(Query querySelect, Update queryUpdate) {
        mongoTemplate.updateFirst(querySelect, queryUpdate, ShoppingList.class);
    }

    @Override
    public void addItem(Query querySelect, Update queryUpdate) {
        mongoTemplate.updateFirst(querySelect, queryUpdate, ShoppingList.class);
    }

    @Override
    public void removeItem(Query querySelect, Update queryUpdate) {
        mongoTemplate.updateFirst(querySelect, queryUpdate, ShoppingList.class);
    }

    @Override
    public List<ShoppingList> findCustom(Query querySelect) {
        return mongoTemplate.find(querySelect, ShoppingList.class);
    }


}
