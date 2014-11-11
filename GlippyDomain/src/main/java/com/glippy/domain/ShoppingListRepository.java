package com.glippy.domain;

import com.glippy.entity.ShoppingList;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, String> {
    //Read
    List<ShoppingList> findByName(String name);
    List<ShoppingList> findByUsername(String shoppingListUserName);
    List<ShoppingList> findByUsernameAndName(String username, String listName);
    List<ShoppingList> findAllBy(TextCriteria criteria);
    //Delete
    List<ShoppingList> deleteByUsername(String username);
    List<ShoppingList> deleteByUsernameAndName(String username, String listname);
}