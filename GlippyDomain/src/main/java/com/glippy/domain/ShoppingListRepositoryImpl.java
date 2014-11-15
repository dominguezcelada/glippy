package com.glippy.domain;

import com.glippy.entity.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


/**
 * Created by oscar on 15/11/2014.
 */
public class ShoppingListRepositoryImpl implements ShoppingListRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateQuantity(Query querySelect, Update queryUpdate) {
        mongoTemplate.updateMulti(querySelect, queryUpdate, ShoppingList.class);
    }

}
