package com.glippy.domain;

import com.glippy.entity.ShoppingList;
import com.mongodb.QueryBuilder;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, String>, ShoppingListRepositoryCustom {

    //Read
    List<ShoppingList> findByName(String name);
    List<ShoppingList> findAllBy(TextCriteria criteria);
    List<ShoppingList> findByUsername(String shoppingListUserName);

    //Delete
    List<ShoppingList> deleteByUsername(String username);

    List<ShoppingList> findByUsernameAndName(String username, String listName);

    List<ShoppingList> deleteByUsernameAndName(String username, String listname);


}
