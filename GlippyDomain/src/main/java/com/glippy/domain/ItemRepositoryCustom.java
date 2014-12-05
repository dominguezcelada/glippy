package com.glippy.domain;

import com.glippy.entity.Item;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 15/11/2014.
 */
public interface ItemRepositoryCustom {
    public List<Item> findAllTextCriteria(String query, String language);
}
