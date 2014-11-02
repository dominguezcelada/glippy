package com.glippy.domain;

import com.glippy.entity.ShoppingListItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by oscar on 02/11/2014.
 */
public interface ShoppingListItemRepository extends CrudRepository<ShoppingListItem, String> {
}
