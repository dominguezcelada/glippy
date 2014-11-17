package com.glippy.domain;

import com.glippy.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


/**
 * Created by oscar on 15/11/2014.
 */
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Item> findAllTextCriteria(Query querySelect) {
        return mongoTemplate.find(querySelect, Item.class);
    }

}
