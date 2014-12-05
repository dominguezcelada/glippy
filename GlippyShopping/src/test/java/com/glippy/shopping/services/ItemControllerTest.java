package com.glippy.shopping.services;

import com.glippy.domain.ItemRepository;
import com.glippy.entity.Item;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositoryControllerTest-context.xml")
public class ItemControllerTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @After
    public void tearDown() {
        mongoTemplate.remove(new Query(), Item.class);
    }

    @Test
    public void testGetAllItems() throws Exception {

    }

    @Test
    public void testDeleteAllItems() throws Exception {

    }

    @Test
    public void testCreateItem() throws Exception {

    }

    @Test
    public void testGetItem() throws Exception {

    }

    @Test
    public void testDeleteItem() throws Exception {

    }

    @Test
    public void testFindItems() throws Exception {

    }
}