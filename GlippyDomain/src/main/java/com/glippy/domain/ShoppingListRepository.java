package com.glippy.domain;

import com.glippy.entity.ShoppingList;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by oscar on 01/11/2014.
 */

public interface ShoppingListRepository extends CrudRepository<ShoppingList, String> {
    List<ShoppingList> findByName(String name);

    List<ShoppingList> findAllBy(TextCriteria criteria);
}
